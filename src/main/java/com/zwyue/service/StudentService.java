package com.zwyue.service;

import com.zwyue.entity.Student;
import com.zwyue.entity.StudentEnter;

import java.util.List;
import java.util.Map;

/**
 * 學生管理
 * @author yangli
 * @date 2018/12/21 11:31
 */
public interface StudentService {

    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    List<Student> getStudentList();


    /*
     * copyright    <a href="http://www.qaqavr.com/>中锐</a>
     * <pre>
     *     @author      zwy
     *     @date        2018/12/21 9:25
     *     email       1092478224@qq.com
     *     desc
     * </pre>
     */

    /**
     * 录入学生信息
     *
     * @author zwy
     * @date 2018/12/21 9:27
     * @param student 学生
     * @param studentEnter 报名表
     * @return map
     */
    Map enterStudentInfo(Student student, StudentEnter studentEnter);

    /**
     * 查询全部学生信息
     *
     * @author zwy
     * @date 2018/12/21 15:39
     * @return 学生列表
     */
    List<Student> queryAllStudents();

    /**
     * 更新学生信息，学生报名信息，花名册信息
     *
     * @author zwy
     * @date 2018/12/26 15:30
     * @param student 学生基本信息
     * @return map
     */
    Integer updateStudentInfo(Student student);

    /**
     * 学生id
     *
     * @author zwy
     * @date 2018/12/27 8:51
     * @param stuId 学生id
     * @return int
     */
    Integer deleteStuInfo(Integer stuId);

    /**
     * 更新学生报名信息
     *
     * @author zwy
     * @date 2018/12/27 10:05
     * @param studentEnter 学生报名信息
     * @return int
     */
    Map<String,Object> updateEnterInfo(StudentEnter studentEnter);
}

