package com.zwyue.dao;

import com.zwyue.entity.Identitys;

import java.util.List;

/**
 * @Author: yangli
 * @Description:
 * @Date: Created in 13:43 2018/12/20
 * @Modified by:
 */
public interface IdentitysDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Identitys record);

    int insertSelective(Identitys record);

    Identitys selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Identitys record);

    int updateByPrimaryKey(Identitys record);

    List<Identitys> getList();
}
