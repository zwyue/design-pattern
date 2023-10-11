package com.zwyue.service;

import com.zwyue.entity.Teacher;

import java.util.List;

/**
 * TeacherService interface
 *
 * @author zwy
 * @date 2018/11/28 13:13
 */
public interface TeacherService {

    /**
     * 根据教师编号查询教师
     *
     * @author zwy
     * @date 2018/11/28 13:16
     * @param tnumber 教师编号
     * @return Teacher
     */
    Teacher queryTeacherByNumber(String tnumber);

    /**
     * 根据id查询人员信息
     *
     * @author zwy
     * @date 2018/12/3 10:02
     * @param userId 教师id
     * @return int
     */
    Teacher queryTeacherById(Integer userId);

    /**
     * 更新用户信息
     *
     * @author zwy
     * @date 2018/12/3 11:37
     * @param teacher 教师
     * @return int
     */
    Integer updateTeacher(Teacher teacher);

    /**
     * 查询全部教职工
     *
     * @author zwy
     * @date 2018/12/11 16:40
     * @return list
     */
    List<Teacher> queryAllTeacher();

    /**
     * 根据角色id模糊查询用户
     *
     * @author zwy
     * @date 2018/12/11 17:58
     * @param roleId 角色id
     * @return list
     */
    List<Teacher> queryTeacherByRoleId(Integer roleId);

    /**
     * 录入教师信息
     *
     * @author zwy
     * @date 2018/12/20 14:08
     * @param teacher 教师信息
     * @return int
     */
    Integer enterTeacher(Teacher teacher) ;

    /**
     * 删除人员信息
     *
     * @author zwy
     * @date 2018/12/20 18:10
     * @param id 人员id
     * @return int
     */
    Integer deleteTeacher(Integer id);
}
