package com.zwyue.dao;

import com.zwyue.entity.Activities;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivitiesDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Activities record);

    int insertSelective(Activities record);

    Activities selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Activities record);

    int updateByPrimaryKey(Activities record);

    List<Activities> getList();

    List<Activities> getTitleList(String title);
}