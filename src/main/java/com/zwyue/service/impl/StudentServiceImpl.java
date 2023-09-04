package com.zwyue.service.impl;

import static com.zwyue.exception.ExceptionEnum.SUCCESS;
import static com.zwyue.exception.ExceptionEnum.UPDATE_FAILED;

import com.zwyue.annotation.PagingQuery;
import com.zwyue.common.ResultUtils;
import com.zwyue.dao.StudentDao;
import com.zwyue.dao.StudentEnterDao;
import com.zwyue.entity.Student;
import com.zwyue.entity.StudentEnter;
import com.zwyue.service.StudentService;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

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

    /**
     * 
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

    @Autowired
    private TransactionTemplate transactionTemplate ;

    @Autowired
    private RedisTemplate redisTemplate ;

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

            //删除redis缓存信息
            Arrays.asList(student.getStunumber().split(",")).forEach(number -> {
                List<String> nums =
                    (List<String>) redisTemplate.opsForValue().get(number.substring(0, 10));
                nums.removeIf(n -> n.equals(number.substring(10, 12)));
                redisTemplate.opsForValue().set(number.substring(0, 10), nums);
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
                    Arrays.asList(stringStuNos.split(",")).forEach(number -> {
                        List<String> nums =
                            (List<String>) redisTemplate.opsForValue().get(number.substring(0, 10));
                        nums.removeIf(n -> n.equals(number.substring(10, 12)));
                        redisTemplate.opsForValue().set(number.substring(0, 10), nums);
                    });

                    Map results = formatStuNum(Arrays.asList(studentEnter.getClassid().split(",")));
                    Map<String, String> classInfo = (Map<String, String>) results.get("map");
                    //插入学生信息的学生编号，以逗号隔开
                    String studentNumbers = (String) results.get("string");
                    //修改学生信息表
                    Map map = new HashMap();
                    map.put("studentNumbers",studentNumbers);
                    map.put("className",studentEnter.getClassname());
                    map.put("classId",studentEnter.getClassid());
                    map.put("id",studentEnter.getStuid());
                    isUpdate += studentDao.updateStudentNumbers(map);
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
     * 格式化学生学号
     *
     * @author zwy
     * @date 2018/12/26 11:35
     */
    private Map formatStuNum(List<String> classIds){
        //缓存中的专业信息
        Map<String, Object> results = new HashMap<>();

        Map<String, String> map = new HashMap<>();

        //插入学生信息的学生编号，以逗号隔开
        StringBuilder studentNumbers = new StringBuilder();


        results.put("map",map);
        //以逗号连接学号
        results.put("string", studentNumbers.substring(0, studentNumbers.lastIndexOf(",")));
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
            ,Map<String,String> map
            ,StudentEnter studentEnter){

        return transactionTemplate.execute(status->{
            if(existStudent==null){
                //插入学生信息
                studentDao.insert(student);
                studentEnter.setStuid(student.getId());
            }else {
                student.setId(existStudent.getId());
                //更新学生信息(学号覆盖)
                studentDao.updateByPrimaryKeySelective(student);
                studentEnter.setStuid(existStudent.getId());
            }
            //插入学生报名信息
            studentEnterDao.insert(studentEnter);
            return ResultUtils.success(SUCCESS);
        });
    }
}
