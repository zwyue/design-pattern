package com.zwyue.dao;

import com.zwyue.entity.WorkPlan;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface WorkPlanDao {
    int deleteByPrimaryKey(Integer id);

    int insert(WorkPlan record);

    int insertSelective(WorkPlan record);

    WorkPlan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WorkPlan record);

    int updateByPrimaryKey(WorkPlan record);

    List<WorkPlan> getList(Map map);

    List<WorkPlan> getclassList(Map map,String classname);
}