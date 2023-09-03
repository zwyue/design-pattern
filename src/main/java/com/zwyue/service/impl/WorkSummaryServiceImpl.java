package com.zwyue.service.impl;

import com.zwyue.dao.WorkSummaryDao;
import com.zwyue.entity.WorkSummary;
import com.zwyue.service.WorkSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 班主任工作总结管理
 * @author yangli
 * @date 2018/12/25
 */
@Service
public class WorkSummaryServiceImpl implements WorkSummaryService {

    @Autowired
    private WorkSummaryDao workSummaryDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return workSummaryDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(WorkSummary record) {
        return workSummaryDao.insert(record);
    }

    @Override
    public int insertSelective(WorkSummary record) {
        return workSummaryDao.insertSelective(record);
    }

    @Override
    public WorkSummary selectByPrimaryKey(Integer id) {
        return workSummaryDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(WorkSummary record) {
        return workSummaryDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WorkSummary record) {
        return workSummaryDao.updateByPrimaryKey(record);
    }

    @Override
    public List<WorkSummary> getList() {
        return workSummaryDao.getList();
    }

    @Override
    public List<WorkSummary> getTitleList(String title) {
        return workSummaryDao.getTitleList(title);
    }
}
