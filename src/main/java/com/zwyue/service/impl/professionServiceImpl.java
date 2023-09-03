package com.zwyue.service.impl;

import com.zwyue.dao.ProfessionDao;
import com.zwyue.entity.Profession;
import com.zwyue.service.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 专业管理
 * @author yangli
 * @date 2018/12/25
 */
@Service
public class professionServiceImpl implements ProfessionService {

    @Autowired
    private ProfessionDao professionDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return professionDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Profession record) {
        return professionDao.insert(record);
    }

    @Override
    public int insertSelective(Profession record) {
        return professionDao.insertSelective(record);
    }

    @Override
    public Profession selectByPrimaryKey(Integer id) {
        return professionDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Profession record) {
        return professionDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Profession record) {
        return professionDao.updateByPrimaryKey(record);
    }

    @Override
    public List<Profession> getprolist(Integer cateid) {
        return professionDao.getprolist(cateid);
    }
}
