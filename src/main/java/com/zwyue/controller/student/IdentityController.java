package com.zwyue.controller.student;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zwyue.common.BaseController;
import com.zwyue.entity.Identitys;
import com.zwyue.service.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author: yangli
 * @Description:
 * @Date: Created in 14:27 2018/12/20
 * @Modified by:
 */
@Controller
@RequestMapping("/identity")
public class IdentityController extends BaseController {

    @Autowired
    private IdentityService identityService;

   /**
     * 查询学生身份维护列表
     * @author yangli
     * @date 2019/1/11
     * @param page,model
     * @return list
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(@RequestParam(required=true,defaultValue="1") Integer page, Model model){
        PageHelper.startPage(page, 3);
        List<Identitys> list = identityService.getList();
        PageInfo<Identitys> p=new PageInfo<Identitys>(list);
        model.addAttribute("page", p);
        model.addAttribute("list",list);
        return "student/identity/identity_list";
    }

    /**
      * 学生身份维护新增页面
      * @author yangli
      * @date 2019/1/11
      */
    @RequestMapping(value = "add",method = RequestMethod.GET)
    public ModelAndView add(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student/identity/identity_add");
        return modelAndView;
        //return "student/identity/identity_add";
    }

    /**
      * 学生身份新增保存
      * @author yangli
      * @date 2019/1/11
      * @param record
      * @return record
      */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public String save(Identitys record){
        identityService.insert(record);
        return "{ \"msg\":\"success\"}";
    }

    /**
      * 学生身份修改页面
      * @author yangli
      * @date 2019/1/11
      * @param id,model
      * @return record
      */
    @RequestMapping(value = "query",method = RequestMethod.GET)
    public String query(Integer id,Model model){
        Identitys identitys = identityService.selectByPrimaryKey(id);
        model.addAttribute("identity",identitys);
        return "student/identity/identity_update";
    }

    /**
      * 学生身份修改保存
      * @author yangli
      * @date 2019/1/11
      * @param identitys
      * @return identitys
      */
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public String update(Identitys identitys){
        identityService.updateByPrimaryKey(identitys);
        return "{ \"msg\":\"success\"}";
    }

    /**
      * 学生身份维护信息删除
      * @author yangli
      * @date 2019/1/11
      * @param id
      */
    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public String delete(Integer id){
        identityService.deleteByPrimaryKey(id);
        return "redirect:/identity/list";
    }

}
