package com.zwyue.dao.initializingDao;

import com.zwyue.dao.RoleDao;
import com.zwyue.entity.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * copyright    <a href="http://www.qaqavr.com/>中锐</a>
 * <pre>
 *     @author      zwy
 *     @date        2018/12/13 11:19
 *     email        1092478224@qq.com
 *     desc         初始化缓存角色
 * </pre>
 */
@Repository
public class RoleInitializingDao implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(RoleInitializingDao.class);

    //缓存数据库全部角色（角色较多时不建议缓存）
    private static List<Role> roles = new ArrayList<>() ;

    @Autowired
    private RoleDao roleDao ;

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("..........初始化角色........");
        roles = roleDao.roleList() ;
    }

    /**
     * 根据roleId查询角色
     *
     * @author zwy
     * @date 2018/12/13 16:37
     */
    public List<Role> queryRoleByRoleIds(List<String> roleIds) {

        List<Integer> intRoleIds = new ArrayList<>();
        //将String转化为Integer
        roleIds.forEach(ri->intRoleIds.add(Integer.parseInt(ri)));
        //将角色包括roleIds的角色存入list并返回
        List<Role> roleList = roles.stream().filter(r->intRoleIds.contains(r.getId())).collect(Collectors.toList());

        return roleList ;
    }

    /**
     * 查询角色列表(查询条件包括角色名、权限名、状态)
     *
     * @author zwy
     * @date 2018/12/13 16:37
     */
    public List<Role> roleList(Role role) {
        return role!=null?roles.stream().filter(r->
                r.getRolename().equals(role.getRolename())
                        ||r.getPowername().contains(role.getPowername())
                        ||r.getStatus().equals(role.getStatus())
        ).collect(Collectors.toList()):roles;
    }

    /**
     * 新建角色
     *
     * @author zwy
     * @date 2018/12/13 16:38
     */
    public Integer insertSelective(Role role) throws Exception {
        int returnInsert = roleDao.insertSelective(role);
        return returnInsert ;
    }

    /**
     * 根据角色id查询角色
     *
     * @author zwy
     * @date 2018/12/13 16:40
     */
    public Role selectByPrimaryKey(Integer roleId) {
        List<Role> roleList = roles.stream().filter(role->roleId.equals(role.getId())).collect(Collectors.toList());
        return roleList.size()==0?null:roleList.get(0);
    }

    /**
     * 删除角色
     *
     * @author zwy
     * @date 2018/12/13 16:49
     */
    public Integer deleteByPrimaryKey(Integer roleId) {
        logger.info("...........删除角色.........");
        int delete = roleDao.deleteByPrimaryKey(roleId);
        if(delete>0){
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return delete ;
    }

    public Integer updateByPrimaryKeySelective(Role role){
        logger.info("......更新角色信息.......");
        int update = roleDao.updateByPrimaryKey(role);
        if(update>0){
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return update ;
    }
}
