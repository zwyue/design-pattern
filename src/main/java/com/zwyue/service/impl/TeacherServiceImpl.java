package com.zwyue.service.impl;

import com.zwyue.annotation.PagingQuery;
import com.zwyue.dao.TeacherDao;
import com.zwyue.entity.Teacher;
import com.zwyue.enums.StatusEnum;
import com.zwyue.service.TeacherService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TeacherServiceImpl class
 *
 * @author zwy
 * @date 2018/11/28 13:13
 */
@Service
public class TeacherServiceImpl implements TeacherService , InitializingBean {

    private final static Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);

    private static final StringBuilder LAST_NUMBER = new StringBuilder() ;

    @Autowired
    private TeacherDao teacherDao ;

    /**
     * 根据教师编号查询教师
     *
     * @author zwy
     * @date 2018/11/28 13:19
     */
    @Override
    public Teacher queryTeacherByNumber(String tnumber) {
        return teacherDao.queryTeacherByNumber(tnumber);
    }

    /**
     * 根据教师id查询教师
     *
     * @author zwy
     * @date 2018/12/11 16:41
     */
    @Override
    public Teacher queryTeacherById(Integer userId) {
        return teacherDao.selectByPrimaryKey(userId);
    }

    /**
     * 更新教师信息
     *
     * @author zwy
     * @date 2018/12/11 16:42
     */
    @Override
    public Integer updateTeacher(Teacher teacher) {
        return teacherDao.updateByPrimaryKeySelective(teacher);
    }

    /**
     * 查询全部教师
     *
     * @author zwy
     * @date 2018/12/11 16:42
     */
    @Override
    @PagingQuery
    public List<Teacher> queryAllTeacher() {
        List<Teacher> teachers = teacherDao.queryAllTeacher();
        teachers.forEach(teacher -> teacher.setStatusTxt(StatusEnum.returnEnumByCode(teacher.getStatus()).msg));
        return teachers ;
    }

    /**
     * 根据角色id查询拥有该角色的用户
     *
     * @author zwy
     * @date 2018/12/11 18:00
     */
    @Override
    public List<Teacher> queryTeacherByRoleId(Integer roleId) {
        return teacherDao.queryTeacherByRoleId(roleId);
    }

    /**
     * 插入教师信息（当前支持的教师编号最多四位）
     *
     * @author zwy
     * @date 2018/12/20 14:10
     */
    @Override
    public Integer enterTeacher(Teacher teacher) {
        //根据身份证号校验是否存在该教师
        Integer ifTeacherExist = teacherDao.queryTeacherByIdCard(teacher.getSfzh());
        if(ifTeacherExist>0){
            return ifTeacherExist ;
        }
        //生成教师编号
        int a = Integer.parseInt(LAST_NUMBER.toString());
        int b = a + 1 ;
        String c = Integer.toString(b);
        teacher.setTnumber(c.length()==1?"000"+c:c.length()==2?"00"+c:c.length()==3?"0"+c:c);
        Integer ifSuccess = teacherDao.insert(teacher);

        //如果插入成功则更新缓存
        if(ifSuccess>0){

            try {
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return ifTeacherExist ;
    }

    /**
     * 删除教师信息
     *
     * @author zwy
     * @date 2018/12/20 18:14
     */
    @Override
    public Integer deleteTeacher(Integer id){
        int ifDelete = teacherDao.deleteByPrimaryKey(id);
        if(ifDelete>0){

            try {
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return ifDelete;
    }

    @Override
    public void afterPropertiesSet(){

        logger.info("...........初始化查询数据库最后的教师编号..........");

        //查询数据库最后的教师编号
        String lastTnum = teacherDao.queryLastTnumber();
        //清空缓存教师编号
        LAST_NUMBER.setLength(0);
        //加入新的教师编号
        LAST_NUMBER.append(StringUtils.isNotBlank(lastTnum)?lastTnum:"01");
    }
}
