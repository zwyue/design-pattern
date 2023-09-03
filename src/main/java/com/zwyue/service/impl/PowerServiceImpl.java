package com.zwyue.service.impl;

import com.zwyue.annotation.PagingQuery;
import com.zwyue.dao.PowerDao;
import com.zwyue.dao.initializingDao.RoleInitializingDao;
import com.zwyue.entity.Power;
import com.zwyue.entity.Role;
import com.zwyue.service.PowerService;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PowerServiceImpl class
 *
 * @author zwy
 * @date 2018/12/1 10:46
 */
@Service
public class PowerServiceImpl implements PowerService {

    @Autowired
    private PowerDao powerDao ;

    @Autowired
    private RoleInitializingDao roleInitializingDao;

    @Override
    @PagingQuery
    public List<Power> queryPowerList() {
        return powerDao.queryPowerList();
    }

    @Override
    public void createNewPower(Power power) {
        powerDao.insert(power);
    }

    @Override
    public Integer allocatePower(Integer roleId, Integer powerId) {
        //根据角色id查询角色
        Role role = roleInitializingDao.selectByPrimaryKey(roleId);
        //根据权限id查询权限
        Power power = powerDao.selectByPrimaryKey(powerId);
        if(StringUtils.isNotBlank(role.getPowerid())){
            if(role.getPowerid().contains(powerId.toString())){
                return 0 ;
            }else {
                role.setPowerid(role.getPowerid() + "," + powerId);
                role.setPowername(role.getPowername() + "," + power.getPowername());
            }
        }else {
            role.setPowerid(powerId.toString());
            role.setPowername(power.getPowername());
        }
        return roleInitializingDao.updateByPrimaryKeySelective(role);
    }

    /**
     * 更新权限信息
     *
     * @author zwy
     * @date 2018/12/11 18:32
     * @param power
     * @return int
     */
    @Override
    public void updatePower(Power power) {
        powerDao.updateByPrimaryKeySelective(power);
    }

    @Override
    public void deletePowerByIds(List<Integer> idList) {
        powerDao.deletePowerByIds(idList);
    }
}
