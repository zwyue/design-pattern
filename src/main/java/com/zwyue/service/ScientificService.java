package com.zwyue.service;

import com.zwyue.entity.Scientific;

import java.util.List;

/**
 * 科研课题管理
 * @author yangli
 * @date 2018/12/25
 */
public interface ScientificService {
    int deleteByPrimaryKey(Integer id);

    int insert(Scientific record);

    int insertSelective(Scientific record);

    Scientific selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Scientific record);

    int updateByPrimaryKey(Scientific record);

    List<Scientific> getList();

    /**
     * 根据标题查询科研课题列表
     * @author yangli
     * @date 2019/1/4
     * @param title
     * @return list
     */
    List<Scientific> getTitleList(String title);
}
