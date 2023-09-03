package com.zwyue.service;

import com.zwyue.entity.Roster;

import java.util.List;

/**
 * 花名册管理
 * @author yangli
 * @date 2018/12/25
 */
public interface RosterService {
    int deleteByPrimaryKey(Integer id);

    int insert(Roster record);

    int insertSelective(Roster record);

    Roster selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Roster record);

    int updateByPrimaryKey(Roster record);

    List<Roster> getList(Integer classid);

    Roster selectByIsleader(String isleader,Integer classid);
}
