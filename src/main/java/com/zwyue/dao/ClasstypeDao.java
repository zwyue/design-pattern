package com.zwyue.dao;

import com.zwyue.entity.Classtype;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClasstypeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Classtype record);

    int insertSelective(Classtype record);

    Classtype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Classtype record);

    int updateByPrimaryKey(Classtype record);
}