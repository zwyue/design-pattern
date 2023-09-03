package com.zwyue.dao;

import com.zwyue.entity.TeachManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeachManagerDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TeachManager record);

    int insertSelective(TeachManager record);

    TeachManager selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TeachManager record);

    int updateByPrimaryKey(TeachManager record);

    List<TeachManager> getList(String type);

    List<TeachManager> getNameList(String name);
}