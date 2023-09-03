package com.zwyue.service;

import com.zwyue.entity.TeachManager;

import java.util.List;

/**
 * 教学管理
 * @author yangli
 * @date 11:31 2018/12/27
 */
public interface TeachManagerService {
    int deleteByPrimaryKey(Integer id);

    int insert(TeachManager record);

    int insertSelective(TeachManager record);

    TeachManager selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TeachManager record);

    int updateByPrimaryKey(TeachManager record);

    List<TeachManager> getList(String type);

    List<TeachManager> getNameList(String name);
}
