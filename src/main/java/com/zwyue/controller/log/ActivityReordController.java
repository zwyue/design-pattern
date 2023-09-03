package com.zwyue.controller.log;

import com.github.pagehelper.PageInfo;
import com.zwyue.common.BaseController;
import com.zwyue.common.ResultUtils;
import com.zwyue.entity.ActivityRecord;
import com.zwyue.entity.Teacher;
import com.zwyue.service.ActivityRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 日志管理-校园活动记录管理
 * @author yangli
 * @date 2018/12/25
 */
@RestController
@RequestMapping("/activityrecord")
public class ActivityReordController extends BaseController {

    @Autowired
    private ActivityRecordService recordService;

    /**
     * 日志管理-校园活动记录列表
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public Map list(){
        PageInfo<ActivityRecord> pageInfo = new PageInfo<>(recordService.getList());
        return ResultUtils.success(pageInfo);
    }

    /**
     * 日志管理-校园活动记录新增保存
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public Map save(ActivityRecord record, HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        record.setTid(teacher.getId());
        record.setTname(teacher.getTname());
        recordService.insert(record);
        if(recordService.insert(record)>0){
            return ResultUtils.success("保存成功");
        }else{
            return ResultUtils.error("保存失败");
        }
    }

    /**
     * 日志管理-校园活动记录修改页面
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "query",method = RequestMethod.GET)
    public Map query(Integer id){
        ActivityRecord record = recordService.selectByPrimaryKey(id);
        return ResultUtils.success(record);
    }

    /**
     * 日志管理-校园活动记录修改提交
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public Map update(ActivityRecord record, HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        record.setTid(teacher.getId());
        record.setTname(teacher.getTname());
        recordService.updateByPrimaryKey(record);
        if(recordService.updateByPrimaryKey(record)>0){
            return ResultUtils.success("保存成功");
        }else{
            return ResultUtils.error("保存失败");
        }
    }

    /**
     * 日志管理-校园活动记录删除
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public Map delete(Integer id){
        Integer delete = recordService.deleteByPrimaryKey(id);
        if(delete>0){
            return ResultUtils.success("删除失败");
        }else {
            return ResultUtils.error("删除成功");
        }
    }
}
