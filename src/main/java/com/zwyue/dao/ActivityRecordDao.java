package com.zwyue.dao;

import com.zwyue.entity.ActivityRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityRecord record);

    int insertSelective(ActivityRecord record);

    ActivityRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityRecord record);

    int updateByPrimaryKey(ActivityRecord record);

    List<ActivityRecord> getList();

    List<ActivityRecord> getTitleList(String title);
}