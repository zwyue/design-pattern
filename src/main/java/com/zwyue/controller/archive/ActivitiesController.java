package com.zwyue.controller.archive;

import com.github.pagehelper.PageInfo;
import com.zwyue.common.BaseController;
import com.zwyue.common.ResultUtils;
import com.zwyue.entity.Activities;
import com.zwyue.entity.Teacher;
import com.zwyue.service.ActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 校园活动管理
 * @author yangli
 * @date 2018/12/25
 */
@RestController
@RequestMapping("/activities")
public class ActivitiesController extends BaseController {

    @Autowired
    private ActivitiesService activitiesService;

    /**
     * 校园活动列表
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public Map getList(){
        PageInfo<Activities> pageInfo = new PageInfo<>(activitiesService.getList());
        return ResultUtils.success(pageInfo);
    }

    /**
     * 根据标题查询校园活动列表
     * @author yangli
     * @date 2019/1/3
     * @param title
     * @return list
     */
    @RequestMapping(value = "getTitle")
    public Map getTitle(String title){
        PageInfo<Activities> pageInfo = new PageInfo<>(activitiesService.getTitleList(title));
        return ResultUtils.success(pageInfo);
    }

    /**
     * 校园活动新增保存
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "save" ,method = RequestMethod.POST)
    @ResponseBody
    public Map save(Activities activities, HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        activities.setUserid(teacher.getId());
        activities.setUsername(teacher.getTname());
        activitiesService.insert(activities);
        if(activitiesService.insert(activities)>0){
            return ResultUtils.success("保存成功");
        }else{
            return ResultUtils.error("保存失败");
        }
    }

    /**
     * 校园活动详情
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "query" ,method = RequestMethod.GET)
    public Map query(Integer id){
        Activities activities = activitiesService.selectByPrimaryKey(id);
        return ResultUtils.success(activities);
    }

    /**
     * 校园活动修改保存
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "update" ,method = RequestMethod.POST)
    @ResponseBody
    public Map update(Activities activities, HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        activities.setUserid(teacher.getId());
        activities.setUsername(teacher.getTname());
        activitiesService.updateByPrimaryKey(activities);
        if(activitiesService.updateByPrimaryKey(activities)>0){
            return ResultUtils.success("修改成功");
        }else{
            return ResultUtils.error("修改失败");
        }
    }

    /**
     * 校园活动删除
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "delete" ,method = RequestMethod.GET)
    public Map delete(Integer id){
        Integer delete = activitiesService.deleteByPrimaryKey(id);
        if(delete>0){
            return ResultUtils.success("删除失败");
        }else {
            return ResultUtils.error("删除成功");
        }
    }
}
