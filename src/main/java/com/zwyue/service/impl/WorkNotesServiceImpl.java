package com.zwyue.service.impl;

import com.zwyue.dao.WorkNotesDao;
import com.zwyue.entity.WorkNotes;
import com.zwyue.service.WorkNotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 班主任工作手记管理
 * @author yangli
 * @date 2018/12/25
 */
@Service
public class WorkNotesServiceImpl implements WorkNotesService {

    @Autowired
    private WorkNotesDao workNotesDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return workNotesDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(WorkNotes record) {
        return workNotesDao.insert(record);
    }

    @Override
    public int insertSelective(WorkNotes record) {
        return workNotesDao.insertSelective(record);
    }

    @Override
    public WorkNotes selectByPrimaryKey(Integer id) {
        return workNotesDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(WorkNotes record) {
        return workNotesDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WorkNotes record) {
        return workNotesDao.updateByPrimaryKey(record);
    }

    @Override
    public List<WorkNotes> getList() {
        return workNotesDao.getList();
    }

    @Override
    public List<WorkNotes> getTitleList(String title) {
        return workNotesDao.getTitleList(title);
    }
}
