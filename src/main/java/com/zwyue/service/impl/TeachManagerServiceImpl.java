package com.zwyue.service.impl;

import com.zwyue.dao.TeachManagerDao;
import com.zwyue.entity.TeachManager;
import com.zwyue.service.TeachManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 教学管理
 * @author yangli
 * @date 11:33 2018/12/27
 */
@Service
public class TeachManagerServiceImpl implements TeachManagerService {

    @Autowired
    private TeachManagerDao teachManagerDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return teachManagerDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TeachManager record) {
        return teachManagerDao.insert(record);
    }

    @Override
    public int insertSelective(TeachManager record) {
        return teachManagerDao.insertSelective(record);
    }

    @Override
    public TeachManager selectByPrimaryKey(Integer id) {
        return teachManagerDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TeachManager record) {
        return teachManagerDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TeachManager record) {
        return teachManagerDao.updateByPrimaryKey(record);
    }

    @Override
    public List<TeachManager> getList(String type) {
        return teachManagerDao.getList(type);
    }

    @Override
    public List<TeachManager> getNameList(String name) {
        return teachManagerDao.getNameList(name);
    }
}
