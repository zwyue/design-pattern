package com.zwyue.controller.log;

import com.github.pagehelper.PageInfo;
import com.zwyue.common.BaseController;
import com.zwyue.common.ResultUtils;
import com.zwyue.entity.Teacher;
import com.zwyue.entity.WorkPlan;
import com.zwyue.service.WorkPlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.zwyue.constant.SysConstant.Punctuation.COMMA;

/**
 * 日志管理-工作计划管理
 * @author yangli
 * @date 2018/12/25
 */
@RestController
@RequestMapping("/workplan")
public class WorkPlanController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(WorkPlanController.class);

    @Autowired
    private WorkPlanService workPlanService;

    /**
     * 日志管理-工作计划列表
     * @author yangli
     * @date 2018/11/28 14:10
     */
    @RequestMapping(value = "list" ,method = RequestMethod.GET)
    public Map getList(HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        Map map = new HashMap();
        map.put("classids", Arrays.asList(teacher.getClassid().split(COMMA)));
        PageInfo<WorkPlan> pageInfo = new PageInfo<>(workPlanService.getList(map));
        return ResultUtils.success(pageInfo);
    }

    /**
      * 根据班级名称查询工作计划列表
      * @author yangli
      * @date 2019/1/4
      * @return list
      */
    @RequestMapping(value = "classlist",method = RequestMethod.GET)
    public Map getclassList(HttpSession httpSession,String classname){
        Teacher teacher = getLoginUser(httpSession);
        Map map = new HashMap();
        map.put("classids", Arrays.asList(teacher.getClassid().split(COMMA)));
        PageInfo<WorkPlan> pageInfo = new PageInfo<>(workPlanService.getTitleList(map,classname));
        return ResultUtils.success(pageInfo);
    }

    /**
     * 工作计划新增保存
     * @author yangli
     * @date 2018/11/28 14:10
     */
    @RequestMapping(value = "save" ,method = RequestMethod.POST)
    @ResponseBody
    public Map save(WorkPlan workPlan, HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        workPlan.setTid(teacher.getId());
        workPlan.setTname(teacher.getTname());
        workPlanService.insert(workPlan);
        if(workPlanService.insert(workPlan)>0){
            return ResultUtils.success("保存成功");
        }else{
            return ResultUtils.success("保存失败");
        }
    }

    /**
     * 日志管理-工作计划修改页面
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "detail" ,method = RequestMethod.GET)
    public Map detail(Integer id){
        WorkPlan workPlan = workPlanService.selectByPrimaryKey(id);
        return ResultUtils.success(workPlan);
    }

    /**
     * 日志管理-工作计划修改
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "update" ,method = RequestMethod.POST)
    @ResponseBody
    public Map update(WorkPlan workPlan, HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        workPlan.setTid(teacher.getId());
        workPlan.setTname(teacher.getTname());
        workPlanService.updateByPrimaryKey(workPlan);
        if(workPlanService.updateByPrimaryKey(workPlan)>0){
            return ResultUtils.success("修改成功");
        }else {
            return ResultUtils.success("修改失败");
        }
    }

    /**
     * 日志管理-工作计划删除
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public Map delete(Integer id){
        int delete = workPlanService.deleteByPrimaryKey(id);
        if(delete>0){
            return ResultUtils.success("删除失败");
        }else {
            return ResultUtils.error("删除成功");
        }
    }
}
