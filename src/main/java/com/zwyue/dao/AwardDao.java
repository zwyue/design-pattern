package com.zwyue.dao;

import com.zwyue.entity.Award;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AwardDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Award record);

    int insertSelective(Award record);

    Award selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Award record);

    int updateByPrimaryKey(Award record);

    List<Award> getList();

    List<Award> getTitleList(String title);
}