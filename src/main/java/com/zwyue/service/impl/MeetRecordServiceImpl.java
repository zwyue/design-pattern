package com.zwyue.service.impl;

import com.zwyue.dao.MeetRecordDao;
import com.zwyue.entity.MeetRecord;
import com.zwyue.service.MeetRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: yangli
 * @Description:
 * @Date: Created in 15:32 2018/12/26
 * @Modified by:
 */
@Service
public class MeetRecordServiceImpl implements MeetRecordService {

    @Autowired
    private MeetRecordDao meetRecordDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return meetRecordDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(MeetRecord record) {
        return meetRecordDao.insert(record);
    }

    @Override
    public int insertSelective(MeetRecord record) {
        return meetRecordDao.insertSelective(record);
    }

    @Override
    public MeetRecord selectByPrimaryKey(Integer id) {
        return meetRecordDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(MeetRecord record) {
        return meetRecordDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MeetRecord record) {
        return meetRecordDao.updateByPrimaryKey(record);
    }

    @Override
    public List<MeetRecord> getList(Map map) {
        return meetRecordDao.getList(map);
    }

    @Override
    public List<MeetRecord> getTitleList(Map map, String content) {
        return meetRecordDao.getTitleList(map,content);
    }
}
