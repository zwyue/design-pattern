package com.zwyue.controller;

import com.github.pagehelper.PageInfo;
import com.zwyue.common.BaseController;
import com.zwyue.common.ResultUtils;
import com.zwyue.entity.Teacher;
import com.zwyue.entity.User;
import com.zwyue.service.TeacherService;
import com.zwyue.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yangli
 * @date 2018/12/25
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private TeacherService teacherService ;

    @RequestMapping(value ="index")
    public String index() {
        return "index";
    }

    @RequestMapping(value ="/list")
    public String getAllUser(Model model, HttpSession httpSession){
        Teacher loginUser = getLoginUser(httpSession);
        List<User> user = userService.selectAllUser();
        model.addAttribute("userList",user);
        logger.info("userInfo......{}......",user.get(0).getId());
        return "alluser";
    }

    /**
     * 查看教职工列表
     *
     * @author zwy
     * @date 2018/12/11 16:36
     */
    @RequestMapping(value = "scanStaff",method = RequestMethod.GET)
    public String scanStaff(Model model){
        List<Teacher> teachers = teacherService.queryAllTeacher();
        model.addAttribute("teacher",teachers);
        model.addAttribute("page",new PageInfo<Teacher>(teachers));
        return "auth/teacher/teacher_list" ;
    }

    /**
     * 跳转到添加用户界面
     */
    @RequestMapping("toAddUser")
    public String toAddUser(){
        return "adduser";
    }

    /**
     * 添加用户并重定向
     */
    @RequestMapping("addUser")
    public String addUser(Model model, User user){
        if (user != null){
            userService.saveUser(user);
        }
        return "redirect:/user/list";
    }

    /**
     * 删除用户
     */
    @RequestMapping("delUser")
    public String removeUser(Model model,Long id){
        model.addAttribute("user",userService.deleteUser(id));
        //userService.deleteUser(id);
        return "redirect:/user/list";
    }


    /**
     * 跳转到更新用户页面
     */
    @RequestMapping("toUpdate")
    public String toUpdate(@RequestParam(value = "id")Long id, Model model){
        model.addAttribute("user",userService.findUserById(id));
        return "updateuser";
    }

    /**
     * 更新用户
     */
    @RequestMapping(value = "updateUser",method = RequestMethod.POST)
    public String updateUser(User user){
        userService.updateUser(user);
        return "redirect:/user/list";
    }

    @RequestMapping("test")
    @POST
    @ResponseBody
    public Map testPost(String string){
        logger.info("==========enter==========");
        return ResultUtils.success(0);
    }
}
