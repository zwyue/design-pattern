package com.zwyue.service;

import com.zwyue.entity.Profession;

import java.util.List;

/**
 * 专业管理
 * @author yangli
 * @date 2018/12/25
 */
public interface ProfessionService {
    int deleteByPrimaryKey(Integer id);

    int insert(Profession record);

    int insertSelective(Profession record);

    Profession selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Profession record);

    int updateByPrimaryKey(Profession record);

    List<Profession> getprolist(Integer cateid);
}
