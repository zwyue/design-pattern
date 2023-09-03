package com.zwyue.service.impl;

import com.zwyue.annotation.PagingQuery;
import com.zwyue.dao.ClassrecordDao;
import com.zwyue.dao.ClassroomDao;
import com.zwyue.dao.ClasstypeDao;
import com.zwyue.entity.Classrecord;
import com.zwyue.entity.Classroom;
import com.zwyue.entity.Classtype;
import com.zwyue.service.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * copyright    <a href="http://www.qaqavr.com/>中锐</a>
 * <pre>
 *     @author      zwy
 *     @date        2018/12/3 15:56
 *     email        1092478224@qq.com
 *     desc
 * </pre>
 */
@Service
public class ClassRoomServiceImpl implements ClassRoomService {

    @Autowired
    private ClassroomDao classroomDao ;

    @Autowired
    private ClasstypeDao classtypeDao ;

    @Autowired
    private ClassrecordDao classrecordDao ;

    @Override
    public Integer addNewClassRoom(Classroom classroom) {
        return classroomDao.insert(classroom);
    }

    @Override
    public Integer addNewCategory(Classtype classtype) {
        return classtypeDao.insert(classtype);
    }

    @Override
    @PagingQuery
    public List<Classroom> queryClassroomList() {
        return classroomDao.queryClassroomList();
    }

    @Override
    @PagingQuery
    public List<Classtype> queryClassTypeList() {
        return classtypeDao.queryClassTypeList();
    }

    @Override
    public Integer updateClassRoomType(Classtype classtype) {
        return classtypeDao.updateByPrimaryKey(classtype);
    }

    @Override
    public Integer updateClassRoom(Classroom classroom) {
        return classroomDao.updateByPrimaryKeySelective(classroom);
    }

    @Override
    public List<Classrecord> clsRmUsageHistory(Classrecord classrecord) {
        return classrecordDao.clsRmUsageHistory(classrecord);
    }
}
