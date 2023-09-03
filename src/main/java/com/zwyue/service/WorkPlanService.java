package com.zwyue.service;

import com.zwyue.entity.WorkPlan;

import java.util.List;
import java.util.Map;

/**
 * 班级工作计划管理
 * @author yangli
 * @date 2018/12/25
 */
public interface WorkPlanService {

    int deleteByPrimaryKey(Integer id);

    int insert(WorkPlan record);

    int insertSelective(WorkPlan record);

    WorkPlan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WorkPlan record);

    int updateByPrimaryKey(WorkPlan record);

    List<WorkPlan> getList(Map map);

    List<WorkPlan> getTitleList(Map map,String classname);
}
