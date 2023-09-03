package com.zwyue.dao;

import com.zwyue.entity.Classes;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassesDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Classes record);

    int insertSelective(Classes record);

    Classes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Classes record);

    int updateByPrimaryKey(Classes record);

    List<Classes> getList();
}