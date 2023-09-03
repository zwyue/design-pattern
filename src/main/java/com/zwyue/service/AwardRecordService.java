package com.zwyue.service;

import com.zwyue.entity.AwardRecord;

import java.util.List;

/**
 * 日志管理-获奖情况记录管理
 * @author yangli
 * @date 2018/12/25
 */
public interface AwardRecordService {
    int deleteByPrimaryKey(Integer id);

    int insert(AwardRecord record);

    int insertSelective(AwardRecord record);

    AwardRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AwardRecord record);

    int updateByPrimaryKey(AwardRecord record);

    List<AwardRecord> getList();

    List<AwardRecord> getTitleList(String title);
}
