package com.zwyue.service.impl;

import com.zwyue.dao.StudyMeetDao;
import com.zwyue.entity.StudyMeet;
import com.zwyue.service.StudyMeetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学委会管理
 * @author yangli
 * @date 2018/12/25
 */
@Service
public class StudyMeetServiceImpl implements StudyMeetService {

    @Autowired
    private StudyMeetDao studyMeetDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return studyMeetDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(StudyMeet record) {
        return studyMeetDao.insert(record);
    }

    @Override
    public int insertSelective(StudyMeet record) {
        return studyMeetDao.insertSelective(record);
    }

    @Override
    public StudyMeet selectByPrimaryKey(Integer id) {
        return studyMeetDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(StudyMeet record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(StudyMeet record) {
        return studyMeetDao.updateByPrimaryKey(record);
    }

    @Override
    public List<StudyMeet> getList() {
        return studyMeetDao.getList();
    }

    @Override
    public int updateDuties(StudyMeet record) {
        return studyMeetDao.updateDuties(record);
    }
}
