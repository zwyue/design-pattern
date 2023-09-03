package com.zwyue.controller.studymeet;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zwyue.common.BaseController;
import com.zwyue.entity.StudyLog;
import com.zwyue.entity.Teacher;
import com.zwyue.service.StudyLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 学委会日志管理
 * @author yangli
 * @date 2018/12/25
 */
@Controller
@RequestMapping("/studylog")
public class StudyLogController extends BaseController {

    @Autowired
    private StudyLogService studyLogService;

    /**
     * 学委会日志管理列表
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String getList(@RequestParam(required=true,defaultValue="1") Integer page, Model model){
        PageHelper.startPage(page, 3);
        List<StudyLog> list = studyLogService.getList();
        PageInfo<StudyLog> p=new PageInfo<StudyLog>(list);
        model.addAttribute("page", p);
        model.addAttribute("list",list);
        return "studymeet/log/studylog_list";
    }

    /**
     * 学委会日志管理新增
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "add",method = RequestMethod.GET)
    public ModelAndView add(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("studymeet/log/studylog_add");
        return modelAndView;
        //return "roster/log/studylog_add";
    }

    /**
     * 学委会日志管理新增保存
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public String save(StudyLog record, HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        record.setTid(teacher.getId());
        record.setTname(teacher.getTname());
        studyLogService.insert(record);
        return "{ \"msg\":\"success\"}";
    }

    /**
     * 学委会日志管理修改页面
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "query",method = RequestMethod.GET)
    public String query(Integer id,Model model){
        StudyLog record = studyLogService.selectByPrimaryKey(id);
        model.addAttribute("studylog",record);
        return "studymeet/log/studylog_update";
    }

    /**
     * 学委会日志管理修改保存
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public String update(StudyLog record){
        studyLogService.updateByPrimaryKey(record);
        return "{ \"msg\":\"success\"}";
    }

    /**
     * 学委会日志管理删除
     * @author yangli
     * @date 2018/12/25
     */
    //@ResponseBody
    @RequestMapping(value = "delete",method = RequestMethod.GET)
    //public Map delete(Integer id){
    public String delete(Integer id){
        studyLogService.deleteByPrimaryKey(id);
        /*if(studyLogService.deleteByPrimaryKey(id)>0){
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("删除失败");*/
        return "redirect:/studylog/list";
    }
}
