package com.zwyue.dao;

import com.zwyue.entity.ClassSuggest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ClassSuggestDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ClassSuggest record);

    int insertSelective(ClassSuggest record);

    ClassSuggest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClassSuggest record);

    int updateByPrimaryKey(ClassSuggest record);

    List<ClassSuggest> getList(Map map);

    List<ClassSuggest> getTitleList(String title);
}