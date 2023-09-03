package com.zwyue.service;

import com.zwyue.entity.SpecialManager;

import java.util.List;
import java.util.Map;

/**
 * 特殊学员管理
 * @author yangli
 * @date 2018/12/25
 */
public interface SpecialManagerService {

    int deleteByPrimaryKey(Integer id);

    int insert(SpecialManager record);

    int insertSelective(SpecialManager record);

    SpecialManager selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpecialManager record);

    int updateByPrimaryKey(SpecialManager record);

    List<SpecialManager> getList(Map map);

    List<SpecialManager> getNameTypes(Map map,String name,String types);
}
