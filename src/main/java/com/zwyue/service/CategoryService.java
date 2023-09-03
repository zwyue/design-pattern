package com.zwyue.service;

import com.zwyue.entity.Category;

import java.util.List;

/**
 * 类别管理
 * @author yangli
 * @date 2018/12/25
 */
public interface CategoryService {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List<Category> getcatelist();
}
