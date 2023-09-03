package com.zwyue.service;

import com.zwyue.entity.StudyLog;

import java.util.List;

/**
 * 学委会日志管理
 * @author yangli
 * @date 2018/12/25
 */
public interface StudyLogService {
    int deleteByPrimaryKey(Integer id);

    int insert(StudyLog record);

    int insertSelective(StudyLog record);

    StudyLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudyLog record);

    int updateByPrimaryKey(StudyLog record);

    List<StudyLog> getList();
}
