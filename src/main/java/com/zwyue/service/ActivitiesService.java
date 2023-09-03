package com.zwyue.service;

import com.zwyue.entity.Activities;

import java.util.List;

/**
 * 校园活动管理
 * @author yangli
 * @date 2018/12/25
 */
public interface ActivitiesService {
    int deleteByPrimaryKey(Integer id);

    int insert(Activities record);

    int insertSelective(Activities record);

    Activities selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Activities record);

    int updateByPrimaryKey(Activities record);

    List<Activities> getList();

    List<Activities> getTitleList(String title);
}
