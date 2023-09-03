package com.zwyue.dao;

import com.zwyue.entity.PlanRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PlanRecord record);

    int insertSelective(PlanRecord record);

    PlanRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PlanRecord record);

    int updateByPrimaryKey(PlanRecord record);

}