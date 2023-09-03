package com.zwyue.dao;

import com.zwyue.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 * @author sys
 * @date 2018/12/26 14:11
 */
@Repository
public interface StudentDao {

    /**
     * 根据主键删除记录
     *
     * @author zwy
     * @date 2018/12/26 14:10
     * @param id 主键
     * @return int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入记录返回主键
     *
     * @author zwy
     * @date 2018/12/26 14:08
     * @param record 学生信息
     * @return int
     */
    Integer insert(Student record);

    /**
     * 有条件的插入
     *
     * @author zwy
     * @date 2018/12/26 14:09
     * @param record 学生信息
     * @return int
     */
    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    /**
     * 根据身份证号查询存在的学生
     *
     * @author zwy
     * @date 2018/12/21 9:47
     * @param sfzh 身份证号
     * @return student
     */
    Student queryStudentByIdCard(String sfzh);

    /**
     * 查询全部学生
     *
     * @author zwy
     * @date 2018/12/21 15:40
     * @return 学生列表
     */
    List<Student> queryAllStudents();

    /**
     * 查询全部学生编号
     *
     * @author zwy
     * @date 2018/12/22 14:16
     * @return 学生编号列表
     */
    List<String> queryStuNo();

    List<Student> getStudentList();

    /**
     * 更新学生表学生学号信息
     *
     * @author zwy
     * @date 2018/12/27 13:26
     * @param map 包括学号和学生id
     * @return int
     */
    Integer updateStudentNumbers(Map map);
}