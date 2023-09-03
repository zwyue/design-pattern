package com.zwyue.dao;

import com.zwyue.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    /**
     * 根据角色id list查询角色（角色包含权限id）
     *
     * @author zwy
     * @date 2018/12/1 10:22
     * @param roleList 角色id list
     * @return list
     */
    List<Role> queryRoleByRoleIds(List<String> roleList);

    /**
     *  查询角色列表
     *
     * @author zwy
     * @date 2018/12/1 16:21
     * @return list
     */
    List<Role> roleList();
}