package com.zwyue.dao;

import com.zwyue.entity.Power;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PowerDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Power record);

    int insertSelective(Power record);

    Power selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Power record);

    int updateByPrimaryKey(Power record);

    /**
     * 根据权限id查询权限
     *
     * @author zwy
     * @date 2018/12/1 10:54
     * @param powerIds 权限信息
     * @return list
     */
    List<Power> queryPowerByPowerIds(List<String> powerIds);

    /**
     * 查询权限列表
     *
     * @author zwy
     * @date 2018/12/1 15:37
     * @return list
     */
    List<Power> queryPowerList();

    /**
     * 根据登陆权限名称查询登陆权限信息
     *
     * @author zwy
     * @date 2018/12/3 11:00
     * @param loginPower 登陆权限名称
     * @return power
     */
    Power queryPowerByPowerName(String loginPower);

    /**
     * 根据id列表删除权限
     *
     * @author zwy
     * @date 2019/1/4 13:45
     * @param idList 权限id列表
     */
    void deletePowerByIds(List<Integer> idList);
}