package com.zwyue.controller;

import com.zwyue.common.ResultUtils;
import com.zwyue.entity.Role;
import com.zwyue.entity.Teacher;
import com.zwyue.service.PowerService;
import com.zwyue.service.RoleService;
import com.zwyue.service.TeacherService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import java.util.*;

import static com.zwyue.exception.ExceptionEnum.*;

/**
 * IndexController class
 *
 * @author zwy
 * @date 2018/11/22 10:22
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    /**
     * 教师是否可用状态
     *
     * @author zwy
     * @date 2018/11/28 13:27
     */
    public interface TeacherStatus{

        /**
         * 启用
         */
        String ACTIVE = "0" ;

        /**
         * 停用
         */
        String FORBIDDEN = "1" ;
    }

    /**
     * 管理员用户名与密码
     *
     * @author zwy
     * @date 2018/11/22 18:07
     */
    public interface Admin{

        Boolean IS_ADMIN = true ;

        Boolean IS_NOT_ADMIN = false ;

        String NAME = "admin" ;

        String PASS = "123456" ;

        String SALT = "salt" ;
    }

    /**
     *缓存角色
     *
     * @date 2018/11/23 13:17
     */
    public static final String CACHE_ROLE = "roles";

    /**
     * 缓存权限
     *
     * @date 2018/11/23 13:39
     */
    public static final String CACHE_PERMISSION = "permissions" ;

    /**
     *  缓存菜单
     *
     * @date 2019/1/2 13:22
     */
    public static final String CACHE_MENU = "menu" ;

    public static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private RoleService roleService ;

    @Autowired
    private PowerService powerService ;

    @Autowired
    private TeacherService teacherService ;

    /**
     * 缓存管理员
     *
     * @date 2018/12/1 12:16
     */
    public static final String CACHE_ADMIN = "is_admin" ;

    /**
     * 跳转登陆页面
     *
     * @author zwy
     * @date 2018/12/6 14:14
     */
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String toLogin(){
        logger.info("======进入登陆页面======");
        return "login";
    }

    /**
     * 用户登陆，并保存用户信息至session
     *
     * @author zwy
     * @date 2018/12/6 14:22
     */
    @RequestMapping("/login")
    @POST
    @ResponseBody
    public Map<String,Object> login(Teacher teacher, HttpSession httpSession){

        logger.info("=======用户登陆=======");

        if (teacher==null|| StringUtils.isBlank(teacher.getTnumber())||StringUtils.isBlank(teacher.getPassword())){

            logger.error("=======用户名或密码为空=======");

            return ResultUtils.error(NULL_EXCEPT.errorCode,NULL_EXCEPT.errorMessage);
        }
        String tnumber =teacher.getTnumber();

        if(Admin.NAME.equals(tnumber)
                &&Admin.PASS.equals(teacher.getPassword())){

            logger.info("======管理员穿透======",tnumber);
            httpSession.setAttribute(CACHE_ADMIN, Admin.IS_ADMIN);
            return ResultUtils.success(0);
        }

        //查询数据库是否存在该账号
        Teacher dbTeacher = teacherService.queryTeacherByNumber(tnumber);

        if (dbTeacher==null){
            return ResultUtils.error(NOT_EXIST.errorCode,NOT_EXIST.errorMessage);
        }

        if(TeacherStatus.FORBIDDEN.equals(dbTeacher.getStatus())){
            return ResultUtils.error(ACCOUNT_FORBID.errorCode,ACCOUNT_FORBID.errorMessage);
        }

        if(teacher.getPassword().equals(dbTeacher.getPassword())){

            //根据角色id获取角色并保存
            if(StringUtils.isBlank(dbTeacher.getRoleid())){
                return ResultUtils.error(NO_RIGHT.errorCode,NO_RIGHT.errorMessage);
            }
            List<Role> roles = roleService.queryRoleByRoleIds(Arrays.asList(dbTeacher.getRoleid().split(",")));
            Set<String> powerIds = new HashSet<>();
            roles.stream().filter(p->StringUtils.isNotBlank(p.getPowerid())).forEach(role -> powerIds.addAll(Arrays.asList(role.getPowerid().split(","))));

            //根据权限id获取权限信息
            if(powerIds.size()==0){
                return ResultUtils.error(NO_RIGHT.errorCode,NO_RIGHT.errorMessage);
            }
            logger.info("........{}登陆成功........",dbTeacher.getTname());

            Map<String,Object> returnMap = new HashMap<>();
            returnMap.put("userInfo",dbTeacher);
            return ResultUtils.success(returnMap,SUCCESS.errorMessage);
        }

        logger.error("=======密码错误=======");
        logger.error("=======用户名:{}=======",teacher.getTnumber());
        logger.error("=======密码:{}=======", teacher.getPassword());

        return ResultUtils.error(LOGIN_FAILED.errorCode,LOGIN_FAILED.errorMessage);
    }

    /**
     * 登出，即注销当前用户
     *
     * @author zwy
     * @date 2018/12/6 14:15
     */
    @RequestMapping(value = "/logout")
    @POST
    @ResponseBody
    public Map logout(HttpSession httpSession) {
        logger.info(".........注销当前用户...........");
        httpSession.invalidate();
        return ResultUtils.success("用户已注销！");
    }
}
