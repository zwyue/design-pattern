package com.zwyue.service.impl;

import com.zwyue.dao.AwardRecordDao;
import com.zwyue.entity.AwardRecord;
import com.zwyue.service.AwardRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 获奖情况记录管理
 * @author yangli
 * @date 2018/12/25
 */
@Service
public class AwardRecordServiceImpl implements AwardRecordService {

    @Autowired
    private AwardRecordDao awardRecordDao;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return awardRecordDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(AwardRecord record) {
        return awardRecordDao.insert(record);
    }

    @Override
    public int insertSelective(AwardRecord record) {
        return awardRecordDao.insertSelective(record);
    }

    @Override
    public AwardRecord selectByPrimaryKey(Integer id) {
        return awardRecordDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(AwardRecord record) {
        return awardRecordDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AwardRecord record) {
        return awardRecordDao.updateByPrimaryKey(record);
    }

    @Override
    public List<AwardRecord> getList() {
        return awardRecordDao.getList();
    }

    @Override
    public List<AwardRecord> getTitleList(String title) {
        return awardRecordDao.getTitleList(title);
    }
}
