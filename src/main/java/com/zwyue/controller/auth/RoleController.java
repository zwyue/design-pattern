package com.zwyue.controller.auth;

import com.github.pagehelper.PageInfo;
import com.zwyue.common.ResultUtils;
import com.zwyue.entity.Role;
import com.zwyue.entity.Teacher;
import com.zwyue.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.GET;
import java.util.List;
import java.util.Map;

/**
 * RoleController class
 *
 * @author admin
 * @date 2018/11/23 11:30
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    private final static Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService ;

    @RequestMapping(value = "toRolePage",method = RequestMethod.GET)
    public String toRolePage(Model model){
        List<Role> roles = roleService.queryRole(null);
        model.addAttribute("list",roles);
        logger.info("-----||进入角色管理页面---------");
        return "auth/role/role_manage";
    }

    /**
     * 查看角色列表
     *
     * @author zwy
     * @date 2019/1/3 9:26
     */
    @RequestMapping("/role-list")
    @ResponseBody
    @GET
    public Map queryRoleList(Role role){
        List<Role> roles = roleService.queryRole(role);
        PageInfo<Role> pageInfo = new PageInfo<>(roles);
        return ResultUtils.success(pageInfo);
    }

    /**
     * 创建角色
     *
     * @author zwy
     * @date 2018/12/11 17:02
     */
    @RequestMapping(value = "createNewRole",method = RequestMethod.POST)
    public String createNewRole(Role role){
        roleService.createNewRole(role);
        return "redirect:/role/toRolePage" ;
    }

    /**
     * 删除角色
     *
     * @author zwy
     * @date 2018/12/11 17:04
     */
    @RequestMapping(value = "deleteRole",method = RequestMethod.POST)
    public String deleteRole(Integer roleId){
        roleService.deleteRole(roleId);
        return "redirect:/role/toRolePage" ;
    }

    /**
     * 用户分配角色
     *
     * @author zwy
     * @date 2018/12/11 17:02
     */
    @RequestMapping(value = "allocateRole",method = RequestMethod.POST)
    public String allocateRole(Integer userId,Integer roleId){
        roleService.allocateRole(userId,roleId);
        return "redirect:../user/list";
    }

    /**
     * 用户角色解绑
     *
     * @author zwy
     * @date 2018/12/11 18:07
     */
    @RequestMapping(value = "untiedRole",method = RequestMethod.POST)
    public String untiedRole(Teacher teacher){
        roleService.untiedRole(teacher) ;
        return "redirect:../user/list";
    }
}
