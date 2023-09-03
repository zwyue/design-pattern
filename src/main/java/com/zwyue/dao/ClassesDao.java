package com.zwyue.dao;

import com.zwyue.entity.Classes;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassesDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Classes record);

    int insertSelective(Classes record);

    Classes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Classes record);

    int updateByPrimaryKey(Classes record);

    List<Classes> getList();

    List<Classes> byNameList(String name);

    List<Classes> getCList(Integer majorid);

    List<Classes> getTeaClasslist(Integer headmaster);

    /**
     * 根据条件查询班级classes
     *
     * @author zwy
     * @date 2019/1/10 14:45
     * @param classes 班级查询条件
     * @return list
     */
    List<Classes> queryClasses(Classes classes);
}