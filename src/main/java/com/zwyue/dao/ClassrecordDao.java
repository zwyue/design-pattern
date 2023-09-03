package com.zwyue.dao;

import com.zwyue.entity.Classrecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassrecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Classrecord record);

    int insertSelective(Classrecord record);

    Classrecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Classrecord record);

    int updateByPrimaryKey(Classrecord record);

    /**
     * 查询教室使用历史
     *
     * @author zwy
     * @date 2018/12/5 14:47
     * @param classrecord 查询使用条件
     * @return list
     */
    List<Classrecord> clsRmUsageHistory(Classrecord classrecord);
}