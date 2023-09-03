package com.zwyue.dao;

import com.zwyue.entity.Classroom;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Classroom record);

    int insertSelective(Classroom record);

    Classroom selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Classroom record);

    int updateByPrimaryKey(Classroom record);

    /**
     * 查询教室列表
     *
     * @author zwy
     * @date 2018/12/5 11:13
     * @return list
     */
    List<Classroom> queryClassroomList();
}