package com.zwyue.service.impl;

import com.zwyue.dao.WorkPlanDao;
import com.zwyue.entity.WorkPlan;
import com.zwyue.service.WorkPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 班级工作计划管理
 * @author yangli
 * @date 2018/12/25
 */
@Service
public class WorkPlanServiceImpl implements WorkPlanService {

    @Autowired
    private WorkPlanDao workPlanDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return workPlanDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(WorkPlan record) {
        return workPlanDao.insert(record);
    }

    @Override
    public int insertSelective(WorkPlan record) {
        return workPlanDao.insertSelective(record);
    }

    @Override
    public WorkPlan selectByPrimaryKey(Integer id) {
        return workPlanDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(WorkPlan record) {
        return workPlanDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WorkPlan record) {
        return workPlanDao.updateByPrimaryKey(record);
    }

    @Override
    public List<WorkPlan> getList(Map map) {
        return workPlanDao.getList(map);
    }

    @Override
    public List<WorkPlan> getTitleList(Map map, String content) {
        return workPlanDao.getclassList(map,content);
    }
}
