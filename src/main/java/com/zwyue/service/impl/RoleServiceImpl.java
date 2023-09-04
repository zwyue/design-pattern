package com.zwyue.service.impl;

import com.zwyue.annotation.PagingQuery;
import com.zwyue.dao.initializingDao.RoleInitializingDao;
import com.zwyue.entity.Role;
import com.zwyue.entity.Teacher;
import com.zwyue.enums.StatusEnum;
import com.zwyue.service.RoleService;
import com.zwyue.service.TeacherService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * RoleServiceImpl class
 *
 * @author admin
 * @date 2018/11/23 11:31
 */
@Service
public class RoleServiceImpl implements RoleService {

    /**
     * 初始化密码
     *
     * @date 2018/12/3 10:35
     */
    public static final String INIT_PASSWORD = "123456" ;

    private final static Logger logger = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    private TeacherService teacherService ;

    @Autowired
    private RoleInitializingDao roleInitializingDao ;


    @Autowired
    private TransactionTemplate transactionTemplate ;

    @Override
    public List<Role> queryRoleByRoleIds(List<String> roleList) {
        return roleInitializingDao.queryRoleByRoleIds(roleList);
    }

    /**
     * 查询权限
     *
     * @author zwy
     * @date 2018/12/6 14:13
     */
    @Override
    @PagingQuery
    public List<Role> queryRole(Role roleParam) {
        logger.info("..........查询角色列表.........");
        List<Role> roles = roleInitializingDao.roleList(roleParam);
        roles.forEach(role -> role.setStatusTxt(StatusEnum.returnEnumByCode(role.getStatus()).msg));
        return roles;
    }

    @Override
    public Integer createNewRole(Role role) {
        logger.info("新建角色");
        try {
            return roleInitializingDao.insertSelective(role);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 分配权限
     *
     * @author zwy
     * @date 2018/12/6 14:14
     */
    @Override
    public Integer allocateRole(Integer userId, Integer roleId) {
        logger.info("角色绑定权限");
        //根据用户id查询用户
        Teacher teacher = teacherService.queryTeacherById(userId);
        //根据角色id查询角色
        Role role = roleInitializingDao.selectByPrimaryKey(roleId);

        boolean hasLoginType = false ;

        if(StringUtils.isBlank(teacher.getRoleid())){
            hasLoginType = StringUtils.isNotBlank(role.getPowerid())?true:false ;
            teacher.setRoleid(roleId.toString());

        }else {
            //当前分配的角色是否已包含在用户已有角色中
            if(teacher.getRoleid().contains(roleId.toString())){
                return 0 ;
            }else {
                //已有角色是否包含权限
                List<Role> roles = roleInitializingDao.queryRoleByRoleIds(Arrays.asList(teacher.getRoleid().split(",")));
                if(!hasLoginType){
                    for(Role ro:roles){
                        //但凡拥有的角色包含权限，则更新密码，有登陆权限
                        if(StringUtils.isNotBlank(ro.getPowerid())){
                            hasLoginType = true ;
                            break;
                        }
                    }
                }
                teacher.setRoleid(teacher.getRoleid() + "," + roleId.toString());
            }
        }
        //用户是否无初始密码且当前分配的角色含有登陆权限
        if(StringUtils.isBlank(teacher.getPassword())&&hasLoginType){
            teacher.setPassword(INIT_PASSWORD);
        }
        return teacherService.updateTeacher(teacher);
    }

    @Override
    public Integer deleteRole(Integer roleId) {
        //查询所有绑定该角色的用户
        List<Teacher> teachers = teacherService.queryTeacherByRoleId(roleId);

        return transactionTemplate.execute(new TransactionCallback<Integer>() {
            @Override
            public Integer doInTransaction(TransactionStatus status) {
                //删除用户绑定的角色id(更新操作)
                teachers.forEach(teacher -> {
                    List<String> roleStringList = Arrays.asList(teacher.getRoleid().split(","));
                    //重新组合用户所拥有的权限id
                    teacher.setRoleid(String.join(",",roleStringList.stream().filter(rid->roleId!=Integer.parseInt(rid)).collect(Collectors.toList())));
                    teacherService.updateTeacher(teacher);
                });
                //删除角色
                roleInitializingDao.deleteByPrimaryKey(roleId);
                return null ;
            }
        });
    }

    /**
     * 用户解绑角色
     *
     * @author zwy
     * @date 2018/12/11 18:12
     */
    @Override
    public Integer untiedRole(Teacher teacher) {
        //todo-解绑角色
        return teacherService.updateTeacher(teacher);
    }
}
