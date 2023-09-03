package com.zwyue.service;

import com.zwyue.entity.WorkSummary;

import java.util.List;

/**
 * 班主任工作总结管理
 * @author yangli
 * @date 2018/12/25
 */
public interface WorkSummaryService {

    int deleteByPrimaryKey(Integer id);

    int insert(WorkSummary record);

    int insertSelective(WorkSummary record);

    WorkSummary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WorkSummary record);

    int updateByPrimaryKey(WorkSummary record);

    List<WorkSummary> getList();

    List<WorkSummary> getTitleList(String title);
}
