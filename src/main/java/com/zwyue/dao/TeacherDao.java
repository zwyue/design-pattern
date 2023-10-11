package com.zwyue.dao;

import com.zwyue.entity.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author sys
 * @date 2018/12/26 15:15
 */
@Repository
public interface TeacherDao {
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入教师信息
     *
     * @author zwy
     * @date 2018/12/26 15:14
     * @param record 记录
     * @return int
     */
    Integer insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    /**
     * 根据教师编号查询教师
     *
     * @author zwy
     * @date 2018/11/28 13:20
     * @param tnumber 教师编号
     * @return teacher
     */
    Teacher queryTeacherByNumber(String tnumber);

    /**
     * 查询全部教师
     *
     * @author zwy
     * @date 2018/12/11 16:42
     * @return list
     */
    List<Teacher> queryAllTeacher();

    /**
     * 根据权限id模糊查询哟个有该权限的用户
     *
     * @author zwy
     * @date 2018/12/11 17:59
     * @param roleId 角色id
     * @return list
     */
    List<Teacher> queryTeacherByRoleId(Integer roleId);

    /**
     * 根据身份证查询是否存在该用户
     *
     * @author zwy
     * @date 2018/12/20 14:25
     * @param sfzh 身份证号
     * @return int
     */
    Integer queryTeacherByIdCard(String sfzh);

    /**
     * 查询最后一位教师编号
     *
     * @author zwy
     * @date 2018/12/20 15:35
     * @return StringBuilder
     */
    String queryLastTnumber();
}