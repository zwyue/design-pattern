package com.zwyue.service;

import com.zwyue.entity.ActivityRecord;

import java.util.List;

/**
 * 日志管理-学校活动记录管理
 * @author yangli
 * @date 2018/12/25
 */
public interface ActivityRecordService {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityRecord record);

    int insertSelective(ActivityRecord record);

    ActivityRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityRecord record);

    int updateByPrimaryKey(ActivityRecord record);

    List<ActivityRecord> getList();

    List<ActivityRecord> getTitleList(String title);
}
