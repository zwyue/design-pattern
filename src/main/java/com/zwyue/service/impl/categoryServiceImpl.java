package com.zwyue.service.impl;

import com.zwyue.dao.CategoryDao;
import com.zwyue.entity.Category;
import com.zwyue.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类别管理
 * @author yangli
 * @date 2018/12/25
 */
@Service
public class categoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return categoryDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Category record) {
        return categoryDao.insert(record);
    }

    @Override
    public int insertSelective(Category record) {
        return categoryDao.insertSelective(record);
    }

    @Override
    public Category selectByPrimaryKey(Integer id) {
        return categoryDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Category record) {
        return categoryDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Category record) {
        return categoryDao.updateByPrimaryKey(record);
    }

    @Override
    public List<Category> getcatelist() {
        return categoryDao.getcatelist();
    }
}
