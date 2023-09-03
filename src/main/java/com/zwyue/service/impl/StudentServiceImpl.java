package com.zwyue.service.impl;

import com.zwyue.annotation.PagingQuery;
import com.zwyue.common.ResultUtils;
import com.zwyue.dao.ClassesDao;
import com.zwyue.dao.RosterDao;
import com.zwyue.dao.StudentDao;
import com.zwyue.dao.StudentEnterDao;
import com.zwyue.entity.*;
import com.zwyue.service.StudentService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static com.zwyue.common.TimeUtil.calculateAge;
import static com.zwyue.constant.SysConstant.MAP_DEFAULT_SIZE;
import static com.zwyue.constant.SysConstant.Punctuation.COMMA;
import static com.zwyue.exception.ExceptionEnum.*;

/**
 * 学生管理
 * @author yangli
 * @date 2018/12/25
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return studentDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Student record) {
        return studentDao.insert(record);
    }

    @Override
    public int insertSelective(Student record) {
        return studentDao.insertSelective(record);
    }

    @Override
    public Student selectByPrimaryKey(Integer id) {
        return studentDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Student record) {
        return studentDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Student record) {
        return studentDao.updateByPrimaryKey(record);
    }

    @Override
    public List<Student> getStudentList() {
        return studentDao.getStudentList();
    }

    /**
     * copyright    <a href="http://www.qaqavr.com/>中锐</a>
     * <pre>
     *     author        zwy
     *     @date        2018/12/21 9:26
     *     email        1092478224@qq.com
     *     desc         学生管理
     * </pre>
     */

    private final static Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    /**
     * 报名信息表
     *
     * @date 2018/12/24 17:15
     */
    @Autowired
    private StudentEnterDao studentEnterDao ;

    /**
     * 学生花名册表
     *
     * @date 2018/12/24 17:16
     */
    @Autowired
    private RosterDao rosterDao ;

    @Autowired
    private TransactionTemplate transactionTemplate ;

    @Autowired
    private RedisTemplate redisTemplate ;

    @Autowired
    private ClassesDao classesDao ;

    /**
     * 学生信息录入
     *
     *  todo - 校验报名班级时间是否冲突
     *
     * @author zwy
     * @date 2018/12/21 9:28
     */
    @Override
    public Map enterStudentInfo(Student student, StudentEnter studentEnter) {
        //查询学生是否存在
        Student existStudent = studentDao.queryStudentByIdCard(student.getSfzh());

        if(existStudent!=null){
           //查询是否重复报名，即所报班级中是否已存在该学生id
           //可以报同一专业的不同班级
           Map<String,Object> map = new HashMap<>(MAP_DEFAULT_SIZE);
           map.put("classids", Arrays.asList(studentEnter.getClassid().split(COMMA)));
           map.put("stuid",existStudent.getId());
           List<Roster> existRoster = rosterDao.queryRosterInfoByClsIdAndStuId(map);
           if(existRoster.size()!=0){
               logger.error("........该生已注册过其中{}门课程........",existRoster.size());
               return ResultUtils.error(ALREADY_SIGNED.errorCode, String.format(ALREADY_SIGNED.errorMessage,existRoster.size()));
           }
        }

        //报名几个班
        Map<String,Object> results = formatStuNum(Arrays.asList(studentEnter.getClassid().split(COMMA)));

        Map<String,String> map = (Map<String, String>) results.get("map");

        //获取以逗号隔开的学号
        String studentNumbers = (String) results.get("string");

        student.setStunumber(studentNumbers);
        student.setAge(calculateAge(student.getSfzh().substring(6,14)));

        Roster roster = new Roster();
        roster.setStuname(student.getStuname());
        roster.setBirthdate(student.getSfzh().substring(6,14));
        roster.setAge(calculateAge(student.getSfzh().substring(6,14)));

        return startToEnter(existStudent,student,roster,map,studentEnter);
    }

    @PagingQuery
    @Override
    public List<Student> queryAllStudents() {
        return studentDao.queryAllStudents();
    }

    @Override
    public Integer updateStudentInfo(Student student) {

        logger.info("........更新学生信息........");

        return studentDao.updateByPrimaryKeySelective(student);
    }

    @Override
    public Integer deleteStuInfo(Integer stuId) {
        return transactionTemplate.execute(delete->{
            //查询该生基本信息
            Student student = studentDao.selectByPrimaryKey(stuId);

            //删除学生信息
            int isDel = studentDao.deleteByPrimaryKey(stuId);
            isDel += studentEnterDao.deleteByStuId(stuId);
            isDel += rosterDao.delByStuId(stuId);

            //删除redis缓存信息
            Arrays.asList(student.getStunumber().split(COMMA)).forEach(number->{
                List<String> nums = (List<String>) redisTemplate.opsForValue().get(number.substring(0,10));
                nums.removeIf(n->n.equals(number.substring(10,12)));
                redisTemplate.opsForValue().set(number.substring(0,10),nums);
            });

            return isDel ;
        });
    }

    @Override
    public Map<String,Object> updateEnterInfo(StudentEnter studentEnter) {
        return transactionTemplate.execute(trans->{
            Integer isUpdate = 0 ;
            if(StringUtils.isNotBlank(studentEnter.getClassid())){
                //查询该生基本信息
                Student student = studentDao.selectByPrimaryKey(studentEnter.getStuid());
                //查询该生原来的报名信息，对比报名班级是否改变
                StudentEnter originEnter = studentEnterDao.queryEnterByEnterId(studentEnter.getId());
                if(!studentEnter.getClassid().equals(originEnter.getClassid())){
                    //删除redis学号信息和花名册信息
                    String stringStuNos = student.getStunumber();
                    Arrays.asList(stringStuNos.split(COMMA)).forEach(number->{
                        List<String> nums = (List<String>) redisTemplate.opsForValue().get(number.substring(0,10));
                        nums.removeIf(n->n.equals(number.substring(10,12)));
                        redisTemplate.opsForValue().set(number.substring(0,10),nums);
                        rosterDao.delByStuNo(number);
                    });

                    Map results = formatStuNum(Arrays.asList(studentEnter.getClassid().split(COMMA)));
                    Map<String,String> classInfo = (Map<String, String>) results.get("map");
                    //插入学生信息的学生编号，以逗号隔开
                    String studentNumbers = (String) results.get("string");
                    //修改学生信息表
                    Map map = new HashMap();
                    map.put("studentNumbers",studentNumbers);
                    map.put("className",studentEnter.getClassname());
                    map.put("classId",studentEnter.getClassid());
                    map.put("id",studentEnter.getStuid());
                    isUpdate += studentDao.updateStudentNumbers(map);
                    //删除原有花名册学生信息
                    isUpdate += rosterDao.deleteByClassIdAndStuId(Arrays.asList(stringStuNos.split(COMMA)));

                    //新增花名册信息
                    Roster roster = new Roster();
                    roster.setStuid(originEnter.getStuid());
                    roster.setStuname(originEnter.getStuname());
                    roster.setAge(student.getAge());
                    roster.setBirthdate(student.getSfzh().substring(6,14));
                    redisTemplateInsert(classInfo,roster);
                }
            }
            isUpdate += studentEnterDao.updateByPrimaryKeySelective(studentEnter);
            if(isUpdate>0){
                return ResultUtils.success(SUCCESS) ;
            }
            return ResultUtils.error(UPDATE_FAILED.errorCode,UPDATE_FAILED.errorMessage);
        });
    }

    /**
     * redis存储
     *
     * @author zwy
     * @date 2018/12/28 9:47
     */
    private void redisTemplateInsert(Map<String,String> map,Roster roster){
        //插入花名册
        map.keySet().forEach(key->{
            roster.setStunumber(key);
            roster.setClassname(map.get(key).substring(0,map.get(key).lastIndexOf(COMMA)));
            roster.setClassid(Integer.parseInt(map.get(key).substring(map.get(key).lastIndexOf(COMMA)+1)));
            rosterDao.insert(roster);
            //更新缓存
            String no = key.substring(0,10) ;
            List classifyNos = (List) redisTemplate.opsForValue().get(no);
            classifyNos = classifyNos==null ? new ArrayList() : classifyNos ;
            classifyNos.add(key.substring(10,12));
            List sortList = (List) classifyNos.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            redisTemplate.opsForValue().set(no,sortList);
        });
    }

    /**
     * 格式化学生学号
     *
     * @author zwy
     * @date 2018/12/26 11:35
     */
    private Map formatStuNum(List<String> classIds){
        //缓存中的专业信息
        List<Profession> professions = (List<Profession>) redisTemplate.opsForValue().get("professions");

        Map<String,Object> results = new HashMap<>(MAP_DEFAULT_SIZE);

        Map<String,String> map = new HashMap<>(MAP_DEFAULT_SIZE);

        //插入学生信息的学生编号，以逗号隔开
        StringBuilder studentNumbers = new StringBuilder();

        classIds.forEach(cls->{
            //查询分类、专业编号
            Classes classes = classesDao.selectByPrimaryKey(Integer.parseInt(cls));
            professions.forEach(p -> {
                if(p.getId().equals(classes.getMajorid())){
                    //对于一个专业来说，只会有一个分类id吧？不管了，这里我只当成一个来写
                    String cateNum = p.getNumbers().length()== 2 ? p.getNumbers() : "0" + p.getNumbers();
                    String majorNum = p.getMajornumber().length() == 2 ? p.getMajornumber() : "0" + p.getMajornumber() ;
                    //班级编号，注意此处不是班级id
                    String classNum = classes.getNumber().length() == 2 ? classes.getNumber() : "0" + classes.getNumber() ;
                    //学号组成 年份 + 分类 + 专业 + 班级
                    String prefixNo = LocalDate.now().getYear() + cateNum + majorNum + classNum ;

                    List stuNos = (List) redisTemplate.opsForValue().get(prefixNo);
                    stuNos = stuNos!=null ? stuNos : new ArrayList();

                    String lastNo = "01" ;

                    if(stuNos.size()!=0){
                        lastNo =(String) stuNos.get(0);
                        int no = Integer.parseInt(lastNo) + 1 ;
                        lastNo = no/10 == 0?"0"+no:""+no ;
                    }
                    //学号为key,班级名加班级id为value
                    map.put(prefixNo+lastNo,classes.getClassname() + COMMA + classes.getId());
                    studentNumbers.append(prefixNo).append(lastNo).append(COMMA);
                }
            });
        });
        results.put("map",map);
        //以逗号连接学号
        results.put("string",studentNumbers.substring(0,studentNumbers.lastIndexOf(COMMA)));
        return results ;
    }

    /**
     * 报名开始
     *
     * @author zwy
     * @date 2018/12/26 11:35
     */
    private Map startToEnter(Student existStudent
            ,Student student
            ,Roster roster
            ,Map<String,String> map
            ,StudentEnter studentEnter){

        return transactionTemplate.execute(status->{
            if(existStudent==null){
                //插入学生信息
                studentDao.insert(student);
                studentEnter.setStuid(student.getId());
                //设置花名册学生id
                roster.setStuid(student.getId());
            }else {
                student.setId(existStudent.getId());
                //更新学生信息(学号覆盖)
                studentDao.updateByPrimaryKeySelective(student);
                studentEnter.setStuid(existStudent.getId());
                //更新花名册学生id
                roster.setStuid(existStudent.getId());
            }
            //插入学生报名信息
            studentEnterDao.insert(studentEnter);
            //插入花名册
            redisTemplateInsert(map,roster);
            return ResultUtils.success(SUCCESS);
        });
    }
}
