package com.zwyue.dao;

import com.zwyue.entity.WorkSummary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkSummaryDao {
    int deleteByPrimaryKey(Integer id);

    int insert(WorkSummary record);

    int insertSelective(WorkSummary record);

    WorkSummary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WorkSummary record);

    int updateByPrimaryKey(WorkSummary record);

    List<WorkSummary> getList();

    List<WorkSummary> getTitleList(String title);
}