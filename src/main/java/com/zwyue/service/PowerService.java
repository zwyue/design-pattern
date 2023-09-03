package com.zwyue.service;

import com.zwyue.entity.Power;

import java.util.List;
import java.util.Map;

/**
 * PowerService interface
 *
 * @author zwy
 * @date 2018/12/1 10:45
 */
public interface PowerService {

    /**
     * 查询权限列表
     *
     * @author zwy
     * @date 2018/12/1 15:36
     * @return list
     */
    List<Power> queryPowerList();

    /**
     * 创建权限
     *
     * @author zwy
     * @date 2018/12/1 15:50
     * @param power 权限实体
     */
    void createNewPower(Power power);

    /**
     * 角色分配权限
     *
     * @author zwy
     * @date 2018/12/3 12:27
     * @param roleId 角色id
     * @param powerId 权限id
     * @return int
     */
    Integer allocatePower(Integer roleId, Integer powerId);

    /**
     * 更新权限信息
     *
     * @author zwy
     * @date 2018/12/11 18:31
     * @param power 权限
     * @return int
     */
    void updatePower(Power power);

    /**
     * 删除权限
     *
     * @author zwy
     * @date 2019/1/4 13:43
     * @param idList 权限id列表
     */
    void deletePowerByIds(List<Integer> idList);
}
