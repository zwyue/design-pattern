package com.zwyue.service.impl;

import com.zwyue.dao.StudyLogDao;
import com.zwyue.entity.StudyLog;
import com.zwyue.service.StudyLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学委会日志管理
 * @author yangli
 * @date 2018/12/25
 */
@Service
public class StudyLogServiceImpl implements StudyLogService {

    @Autowired
    private StudyLogDao studyLogDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return studyLogDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(StudyLog record) {
        return studyLogDao.insert(record);
    }

    @Override
    public int insertSelective(StudyLog record) {
        return studyLogDao.insertSelective(record);
    }

    @Override
    public StudyLog selectByPrimaryKey(Integer id) {
        return studyLogDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(StudyLog record) {
        return studyLogDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(StudyLog record) {
        return studyLogDao.updateByPrimaryKey(record);
    }

    @Override
    public List<StudyLog> getList() {
        return studyLogDao.getList();
    }
}
