package com.zwyue.service;

import com.zwyue.entity.Identitys;

import java.util.List;

/**
 * 学生身份管理
 * @author yangli
 * @date 2018/12/25
 */
public interface IdentityService {

    int deleteByPrimaryKey(Integer id);

    int insert(Identitys record);

    int insertSelective(Identitys record);

    Identitys selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Identitys record);

    int updateByPrimaryKey(Identitys record);

    List<Identitys> getList();
}
