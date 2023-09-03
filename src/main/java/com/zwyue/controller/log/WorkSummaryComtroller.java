package com.zwyue.controller.log;

import com.github.pagehelper.PageInfo;
import com.zwyue.common.BaseController;
import com.zwyue.common.ResultUtils;
import com.zwyue.entity.Teacher;
import com.zwyue.entity.WorkSummary;
import com.zwyue.service.WorkSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 日志管理-班主任工作总结管理
 * @author yangli
 * @date 2018/12/25
 */
@RestController
@RequestMapping("/worksummary")
public class WorkSummaryComtroller extends BaseController {

    @Autowired
    private WorkSummaryService workSummaryService;

    /**
     * 日志管理-班主任工作总结列表
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public Map list(){
        PageInfo<WorkSummary> pageInfo = new PageInfo<>(workSummaryService.getList());
        return ResultUtils.success(pageInfo);
    }

    /**
     * 日志管理-班主任工作总结按标题查询
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "getTitle")
    public Map getTitle(String title){
        PageInfo<WorkSummary> pageInfo = new PageInfo<>(workSummaryService.getTitleList(title));
        return ResultUtils.success(pageInfo);
    }

    /**
     * 日志管理-班主任工作总结新增保存
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public Map save(WorkSummary record, HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        record.setTid(teacher.getId());
        record.setTname(teacher.getTname());
        workSummaryService.insert(record);
        if(workSummaryService.insert(record)>0){
            return ResultUtils.success("保存成功");
        }else{
            return ResultUtils.error("保存失败");
        }
    }

    /**
     * 日志管理-班主任工作总结修改保存
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public Map update(WorkSummary record,HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        record.setTid(teacher.getId());
        record.setTname(teacher.getTname());
        workSummaryService.updateByPrimaryKey(record);
        if(workSummaryService.updateByPrimaryKey(record)>0){
            return ResultUtils.success("修改成功");
        }else{
            return ResultUtils.error("修改失败");
        }
    }

    /**
     * 日志管理-班主任工作总结删除
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public Map delete(Integer id){
        Integer delete = workSummaryService.deleteByPrimaryKey(id);
        if(delete>0){
            return ResultUtils.success("删除失败");
        }else {
            return ResultUtils.error("删除成功");
        }
    }

    /**
     * 查看详情
     * @author yangli
     * @date 2018/12/26
     */
    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public Map detail(Integer id){
        WorkSummary work = workSummaryService.selectByPrimaryKey(id);
        return ResultUtils.success(work);
    }
}
