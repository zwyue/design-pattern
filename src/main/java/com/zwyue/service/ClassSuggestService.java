package com.zwyue.service;

import com.zwyue.entity.ClassSuggest;

import java.util.List;
import java.util.Map;

/**
 * 班级问题和建议清单管理
 * @author yangli
 * @date 2018/12/25
 */
public interface ClassSuggestService {
    int deleteByPrimaryKey(Integer id);

    int insert(ClassSuggest record);

    int insertSelective(ClassSuggest record);

    ClassSuggest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClassSuggest record);

    int updateByPrimaryKey(ClassSuggest record);

    List<ClassSuggest> getList(Map map);

    List<ClassSuggest> getTitleList(String title);
}
