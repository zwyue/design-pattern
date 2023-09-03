package com.zwyue.dao;

import com.zwyue.entity.SpecialManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SpecialManagerDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SpecialManager record);

    int insertSelective(SpecialManager record);

    SpecialManager selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpecialManager record);

    int updateByPrimaryKey(SpecialManager record);

    List<SpecialManager> getList(Map map);

    List<SpecialManager> getNameTypes(Map map,String name,String types);
}