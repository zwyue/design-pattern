package com.zwyue.service.impl;

import com.zwyue.dao.ClassSuggestDao;
import com.zwyue.entity.ClassSuggest;
import com.zwyue.service.ClassSuggestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 班级问题和建议清单管理
 * @author yangli
 * @date 2018/12/25
 */
@Service
public class ClassSuggestServiceImpl implements ClassSuggestService {

    @Autowired
    private ClassSuggestDao classSuggestDao;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return classSuggestDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ClassSuggest record) {
        return classSuggestDao.insert(record);
    }

    @Override
    public int insertSelective(ClassSuggest record) {
        return classSuggestDao.insertSelective(record);
    }

    @Override
    public ClassSuggest selectByPrimaryKey(Integer id) {
        return classSuggestDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ClassSuggest record) {
        return classSuggestDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ClassSuggest record) {
        return classSuggestDao.updateByPrimaryKey(record);
    }

    @Override
    public List<ClassSuggest> getList(Map map) {
        return classSuggestDao.getList(map);
    }

    @Override
    public List<ClassSuggest> getTitleList(String title) {
        return classSuggestDao.getTitleList(title);
    }
}
