package com.zwyue.controller;

import com.zwyue.common.BaseController;
import com.zwyue.entity.Classes;
import com.zwyue.entity.TeachManager;
import com.zwyue.entity.Teacher;
import com.zwyue.service.ClassesService;
import com.zwyue.service.TeachManagerService;
import com.zwyue.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 教学管理
 * @author yangli
 * @date 11:40 2018/12/27
 */
@Controller
@RequestMapping("/teachmanager")
public class TeachManagerController extends BaseController {

    @Autowired
    private TeachManagerService teachManagerService;

    @Autowired
    private ClassesService classesService;

    @Autowired
    private TeacherService teacherService;


    /**
     * 查询教学管理列表
     * @author yangli
     * @date 2018/12/27
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(String types,Model model){
        List<TeachManager> list = teachManagerService.getList(types);
        model.addAttribute("list",list);
        return "";
    }

    /**
     * 根据姓名查询
     * @author yangli
     * @date 2018/12/27
     */
    @RequestMapping(value = "getNameList",method = RequestMethod.GET)
    public List<TeachManager> getNameList(String name){
        return teachManagerService.getNameList(name);
    }

    /**
     * 跳转至新增页面
     * @author yangli
     * @date 2018/12/27
     */
    @RequestMapping(value = "add",method = RequestMethod.GET)
    public String add(){
        return "";
    }

    /**
     * 根据教师id查询班级列表
     * @author yangli
     * @date 2018/12/27
     */
    @RequestMapping(value = "classlist",method = RequestMethod.GET)
    public List<Classes> classlist(HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        return classesService.getTeaClasslist(teacher.getId());
    }

    /**
     * 查询所有教师列表
     * @author yangli
     * @date 2018/12/27
     */
    @RequestMapping(value = "tealist",method = RequestMethod.GET)
    public List<Teacher> tealist(){
        return teacherService.queryAllTeacher();
    }

    /**
     * 教学管理保存
     * @author yangli
     * @date 2018/12/27
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public String save(TeachManager record){
        teachManagerService.insert(record);
        return "";
    }

    /**
     * 查看一条请假记录
     * @author yangli
     * @date 2018/12/27
     */
    @RequestMapping(value = "query",method = RequestMethod.GET)
    public String query(Integer id,Model model){
        TeachManager record = teachManagerService.selectByPrimaryKey(id);
        model.addAttribute("record",record);
        return "";
    }


}
