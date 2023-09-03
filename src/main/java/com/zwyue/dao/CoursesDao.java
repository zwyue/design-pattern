package com.zwyue.dao;

import com.zwyue.entity.Courses;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursesDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Courses record);

    int insertSelective(Courses record);

    Courses selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Courses record);

    int updateByPrimaryKey(Courses record);

    /**
     * 查询课程列表
     *
     * @author zwy
     * @date 2018/12/6 17:00
     * @param courses
     * @return list
     */
    List<Courses> queryCourseList(Courses courses);
}