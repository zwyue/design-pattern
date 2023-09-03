package com.zwyue.dao;

import com.zwyue.entity.StudyMeet;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyMeetDao {
    int deleteByPrimaryKey(Integer id);

    int insert(StudyMeet record);

    int insertSelective(StudyMeet record);

    StudyMeet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudyMeet record);

    int updateByPrimaryKey(StudyMeet record);

    List<StudyMeet> getList();

    int updateDuties(StudyMeet record);
}