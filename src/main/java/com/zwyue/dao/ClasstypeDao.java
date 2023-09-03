package com.zwyue.dao;

import com.zwyue.entity.Classtype;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClasstypeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Classtype record);

    int insertSelective(Classtype record);

    Classtype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Classtype record);

    int updateByPrimaryKey(Classtype record);

    /**
     * 查询教室类别表
     *
     * @author zwy
     * @date 2018/12/5 11:13
     * @return list
     */
    List<Classtype> queryClassTypeList();
}