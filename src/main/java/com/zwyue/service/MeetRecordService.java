package com.zwyue.service;

import com.zwyue.entity.MeetRecord;

import java.util.List;
import java.util.Map;

/**
 * 班会记录管理
 * @author yangli
 * @date 2018/12/26
 */
public interface MeetRecordService {
    int deleteByPrimaryKey(Integer id);

    int insert(MeetRecord record);

    int insertSelective(MeetRecord record);

    MeetRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MeetRecord record);

    int updateByPrimaryKey(MeetRecord record);

    List<MeetRecord> getList(Map map);

    List<MeetRecord> getTitleList(Map map,String content);
}
