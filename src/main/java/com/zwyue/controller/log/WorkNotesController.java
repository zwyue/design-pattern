package com.zwyue.controller.log;

import com.github.pagehelper.PageInfo;
import com.zwyue.common.BaseController;
import com.zwyue.common.ResultUtils;
import com.zwyue.entity.Teacher;
import com.zwyue.entity.WorkNotes;
import com.zwyue.service.WorkNotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 日志管理-班主任工作手记管理
 * @author yangli
 * @date 2018/12/25
 */
@RestController
@RequestMapping("worknotes")
public class WorkNotesController extends BaseController {

    @Autowired
    private WorkNotesService workNotesService;

    /**
     * 日志管理-班主任工作手记管理列表
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public Map list(){
        PageInfo<WorkNotes> pageInfo=new PageInfo<WorkNotes>(workNotesService.getList());
        return ResultUtils.success(pageInfo);
    }

    /**
     * 日志管理-班主任手记按标题查询
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "getTitle")
    public Map getTitle(String title){
        PageInfo<WorkNotes> pageInfo = new PageInfo<>(workNotesService.getTitleList(title));
        return ResultUtils.success(pageInfo);
    }

    /**
     * 日志管理-班主任工作手记新增保存
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public Map save(WorkNotes record, HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        record.setTid(teacher.getId());
        record.setTname(teacher.getTname());
        workNotesService.insert(record);
        if(workNotesService.insert(record)>0){
            return ResultUtils.success("保存成功");
        }else{
            return ResultUtils.success("保存失败");
        }
    }

    /**
     * 日志管理-班主任工作手记修改页面
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "query",method = RequestMethod.GET)
    public Map query(Integer id){
        WorkNotes workNotes = workNotesService.selectByPrimaryKey(id);
        return ResultUtils.success(workNotes);
    }

    /**
     * 日志管理-班主任工作手记修改保存
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public Map update(WorkNotes workNotes){
        workNotesService.updateByPrimaryKey(workNotes);
        if(workNotesService.updateByPrimaryKey(workNotes)>0){
            return ResultUtils.success("修改成功");
        }else{
            return ResultUtils.success("修改失败");
        }
    }

    /**
     * 日志管理-班主任工作手记删除
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public Map delete(Integer id){
        Integer delete = workNotesService.deleteByPrimaryKey(id);
        if(delete>0){
            return ResultUtils.success("删除失败");
        }else{
            return ResultUtils.success("删除成功");
        }
    }
}
