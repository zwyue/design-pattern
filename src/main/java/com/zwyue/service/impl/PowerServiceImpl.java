package com.zwyue.service.impl;

import com.zwyue.annotation.PagingQuery;
import com.zwyue.dao.PowerDao;
import com.zwyue.dao.RoleDao;
import com.zwyue.dao.initializingDao.RoleInitializingDao;
import com.zwyue.entity.Power;
import com.zwyue.entity.Role;
import com.zwyue.service.PowerService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.zwyue.constant.SysConstant.PowerType.MENU;
import static com.zwyue.constant.SysConstant.Punctuation.COMMA;
import static com.zwyue.constant.SysConstant.ZERO;

/**
 * PowerServiceImpl class
 *
 * @author zwy
 * @date 2018/12/1 10:46
 */
@Service
public class PowerServiceImpl implements PowerService {

    private final static Logger logger = LoggerFactory.getLogger(PowerServiceImpl.class);

    @Autowired
    private PowerDao powerDao ;

    @Autowired
    private RoleDao roleDao ;

    @Autowired
    private RoleInitializingDao roleInitializingDao;

    @Override
    public Map<String,List<Power>> queryPowerByPowerIds(List<String> powerIds) {
        logger.info("***********查询权限***********");
        List<Power> powers = powerDao.queryPowerByPowerIds(powerIds);
        List<Power> menuPowers = powers.stream().filter(power -> ZERO .equals (power.getCategory())
                &&MENU.equals(power.getTypes())).collect(Collectors.toList());
        //只有两级菜单的子节点
        menuPowers.forEach(menu->{
            menu.setLeaf(true);
            List<Power> powerList = new ArrayList<>();
            powers.forEach(power -> {
                if( power.getCategory().equals(String.valueOf(menu.getId())) &&
                        MENU.equals(power.getTypes())){
                    power.setLeaf(true);
                    menu.setLeaf(false);
                    powerList.add(power);
                }
            });
            menu.setChildPowers(powerList);
        });

        Map<String,List<Power>> map = new HashMap<>();
        map.put("allRights",powers);
        map.put("menuRights",menuPowers);
        return map ;
    }

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
    public Power queryPowerByPowerName(String loginPower) {
        return powerDao.queryPowerByPowerName(loginPower);
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
                role.setPowerid(role.getPowerid() + COMMA + powerId);
                role.setPowername(role.getPowername() + COMMA + power.getPowername());
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
