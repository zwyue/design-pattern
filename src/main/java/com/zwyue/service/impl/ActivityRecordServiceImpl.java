package com.zwyue.service.impl;

import com.zwyue.dao.ActivityRecordDao;
import com.zwyue.entity.ActivityRecord;
import com.zwyue.service.ActivityRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 日志管理-学校活动记录管理
 * @author yangli
 * @date 2018/12/25
 */
@Service
public class ActivityRecordServiceImpl implements ActivityRecordService {

    @Autowired
    private ActivityRecordDao recordDao;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return recordDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ActivityRecord record) {
        return recordDao.insert(record);
    }

    @Override
    public int insertSelective(ActivityRecord record) {
        return recordDao.insertSelective(record);
    }

    @Override
    public ActivityRecord selectByPrimaryKey(Integer id) {
        return recordDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ActivityRecord record) {
        return recordDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ActivityRecord record) {
        return recordDao.updateByPrimaryKey(record);
    }

    @Override
    public List<ActivityRecord> getList() {
        return recordDao.getList();
    }

    @Override
    public List<ActivityRecord> getTitleList(String title) {
        return recordDao.getTitleList(title);
    }
}
