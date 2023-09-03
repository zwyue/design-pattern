package com.zwyue.service;

import com.zwyue.entity.Award;

import java.util.List;

/**
 * 获奖信息管理
 * @author yangli
 * @date 2018/12/25
 */
public interface AwardServie {
    int deleteByPrimaryKey(Integer id);

    int insert(Award record);

    int insertSelective(Award record);

    Award selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Award record);

    int updateByPrimaryKey(Award record);

    List<Award> getList();

    List<Award> getTitleList(String title);
}
