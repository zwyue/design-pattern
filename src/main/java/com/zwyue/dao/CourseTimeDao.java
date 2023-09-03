package com.zwyue.dao;

import com.zwyue.entity.CourseTime;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseTimeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CourseTime record);

    int insertSelective(CourseTime record);

    CourseTime selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CourseTime record);

    int updateByPrimaryKey(CourseTime record);
}