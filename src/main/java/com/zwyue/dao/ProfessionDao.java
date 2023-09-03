package com.zwyue.dao;

import com.zwyue.entity.Profession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Profession record);

    int insertSelective(Profession record);

    Profession selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Profession record);

    int updateByPrimaryKey(Profession record);

    List<Profession> getprolist(Integer cateid);
}