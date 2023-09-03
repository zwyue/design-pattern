package com.zwyue.controller;

import com.github.pagehelper.PageInfo;
import com.zwyue.common.ResultUtils;
import com.zwyue.entity.Classrecord;
import com.zwyue.entity.Classroom;
import com.zwyue.entity.Classtype;
import com.zwyue.service.ClassRoomService;
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
 * copyright    <a href="http://www.qaqavr.com/>中锐</a>
 * <pre>
 *     @author      zwy
 *     @date        2018/12/3 15:42
 *     email        1092478224@qq.com
 *     desc         教室管理
 * </pre>
 */
@Controller
@RequestMapping("classroom")
public class ClassRoomController {

    private static final Logger logger = LoggerFactory.getLogger(ClassRoomController.class);

    @Autowired
    private ClassRoomService classRoomService;


    /*
     *     ***************************教室类别管理********************************
     */

    /**
     * 新增教室类别
     *
     * @author zwy
     * @date 2018/12/3 16:39
     */
    @RequestMapping(value = "addNewCategory")
    public String addNewCategory(Classtype classtype){
        classRoomService.addNewCategory(classtype);
        return "redirect:classroom/toClassroomCategoryManage" ;
    }

    /**
     * 更新教室类别
     *
     * @author zwy
     * @date 2018/12/5 14:19
     */
    @RequestMapping("updateClassRoomType")
    public String updateClassRoomType(Classtype classtype){
        logger.info("..........更新教室类别，教室id:{}..........",classtype.getId());
        classRoomService.updateClassRoomType(classtype);
        return "redirect:toClassroomCategoryManage" ;
    }

    /**
     * 进入教室类别管理页面
     *
     * @author zwy
     * @date 2018/12/5 10:51
     */
    @RequestMapping(value = "toClassroomCategoryManage")
    public String toClassroomManage(Model model){
        //查询教室类别列表
        List<Classtype> classtypes = classRoomService.queryClassTypeList();
        model.addAttribute("list",classtypes);
        return "classroom/classroom_category_manage";
    }

    /**
     * 获取教室类别列表
     *
     * @author zwy
     * @date 2019/1/4 14:47
     */
    @RequestMapping("type-list")
    @GET
    @ResponseBody
    public Map categoryList(){
        PageInfo<Classtype> pageInfo = new PageInfo<>(classRoomService.queryClassTypeList());
        return ResultUtils.success(pageInfo);
    }

    /*
     *      *********************************教室管理************************************
     */

    @RequestMapping(value = "toClassroomManage",method = RequestMethod.GET)
    public String toClassManage(Model model){
        //查询教室列表
        List<Classroom> classrooms = classRoomService.queryClassroomList();
        model.addAttribute("list",classrooms);
        return "classroom/class_manage";
    }

    /**
     * 获取教室列表
     *
     * @author zwy
     * @date 2019/1/4 14:38
     */
    @RequestMapping("/list")
    @GET
    @ResponseBody
    public Map classRoomList(){
        PageInfo<Classroom> pageInfo = new PageInfo<>(classRoomService.queryClassroomList());
        return ResultUtils.success(pageInfo);
    }

    /**
     * 添加新教室
     *
     * @author zwy
     * @date 2018/12/3 15:59
     */
    @RequestMapping(value = "addNewClassRoom",method = RequestMethod.POST)
    public String addNewClassRoom(Classroom classroom){
        classRoomService.addNewClassRoom(classroom) ;
        return "redirect:classroom/class_manage" ;
    }

    /**
     * 更新教室
     *
     * @author zwy
     * @date 2018/12/5 14:30
     */
    @RequestMapping("updateClassroom")
    public String updataClassRoom(Classroom classroom){
        classRoomService.updateClassRoom(classroom);
        return "redirect:classroom/toClassroomCategoryManage";
    }

    /*
     *    *****************教室使用历史************************
     */

    /**
     * 教室使用历史
     *
     * @author zwy
     * @date 2018/12/5 14:40
     */
    @RequestMapping(value = "clsRmUsageHistory",method = RequestMethod.GET)
    public String clsRmUsageHistory(Classrecord classrecord,Model model){
        List<Classrecord> classrecords = classRoomService.clsRmUsageHistory(classrecord);
        model.addAttribute("list",classrecords);
        return null;
    }
}
