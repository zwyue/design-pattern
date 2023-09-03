package com.zwyue.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.zwyue.common.BaseController;
import com.zwyue.common.ResultUtils;
import com.zwyue.entity.Category;
import com.zwyue.entity.Classes;
import com.zwyue.entity.Profession;
import com.zwyue.entity.Teacher;
import com.zwyue.service.CategoryService;
import com.zwyue.service.ClassesService;
import com.zwyue.service.ProfessionService;
import com.zwyue.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import java.util.*;

import static com.zwyue.constant.SysConstant.MAP_DEFAULT_SIZE;
import static com.zwyue.exception.ExceptionEnum.SUCCESS;

/**
 * 班级管理
 * @author yangli
 * @date 2018/12/25
 */
@RestController
@RequestMapping("/classes")
public class ClassesController extends BaseController {

    @Autowired
    private ClassesService classesService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProfessionService professionService;
    @Autowired
    private TeacherService teacherService;

    /**
     * 班级管理列表
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public Map getList(){
        PageInfo<Classes> p=new PageInfo<Classes>(classesService.getList());
        return ResultUtils.success(p);
    }

    /**
     * 查询类别列表
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "catelist",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getcateList(){
        //获取从service传来的list数据
        List<Category> list = categoryService.getcatelist();
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(list));
        Map<String,Object> returnMap = new HashMap<>(MAP_DEFAULT_SIZE);
        returnMap.put("catelist",jsonArray);
        return ResultUtils.success(returnMap,SUCCESS.errorMessage);
    }

    /**
     * 查询专业列表
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "prolist",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getprolist(Integer cateid){
        //获取从service传来的list数据
        //cateid=1;
        List<Profession> list = professionService.getprolist(cateid);
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(list));
        Map<String,Object> returnMap = new HashMap<>(MAP_DEFAULT_SIZE);
        returnMap.put("prolist",jsonArray);
        return ResultUtils.success(returnMap,SUCCESS.errorMessage);
    }

    /**
     * 查询班级列表
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "clist")
    @ResponseBody
    public Map<String,Object> getclist(Integer majorid){
        //majorid=1;
        List<Classes> list = classesService.getCList(majorid);
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(list));
        Map<String,Object> returnMap = new HashMap<>(MAP_DEFAULT_SIZE);
        returnMap.put("clist",jsonArray);
        return ResultUtils.success(returnMap,SUCCESS.errorMessage);
    }

    /**
     * 查询教师列表
     * @author yangli
     * @date 2019/1/3
     */
    @RequestMapping(value = "tealist")
    @ResponseBody
    public Map<String,Object> gettealist(Integer majorid){
        List<Teacher> list = teacherService.getteaList(majorid);
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(list));
        Map<String,Object> returnMap = new HashMap<>(MAP_DEFAULT_SIZE);
        returnMap.put("tealist",jsonArray);
        return ResultUtils.success(returnMap,SUCCESS.errorMessage);
    }

    /**
     * 班级管理查询
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "serarch")
    public Map serarch(String name, Model model){
        PageInfo<Classes> p=new PageInfo<Classes>(classesService.byNameList(name));
        return ResultUtils.success(p);
    }

    /**
     * 班级管理新增信息保存
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public Map save(Classes classes){
        int sorts = 1;
        List<Classes> list = classesService.getCList(classes.getMajorid());
        if(list.size()<1){
            sorts=1;
        }else{
            List<Integer> lisst=new ArrayList<Integer>();
            for(int i=0;i<list.size();i++){
                int edition = Integer.parseInt(list.get(i).getNumber());
                lisst.add(edition);
            }
            int max = Collections.max(lisst);
            int sort1=max+1;
            sorts=sort1;
        }
        classes.setNumber(String.valueOf(sorts));
        classes.setActualsize(0);
        classesService.insert(classes);
        if(classesService.insert(classes)>0){
            return ResultUtils.success("保存成功");
        }else{
            return ResultUtils.success("保存失败");
        }
    }

    /**
     * 班级管理修改页面
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "query",method = RequestMethod.GET)
    public Map query(Integer id) {
        Classes classes = classesService.selectByPrimaryKey(id);
        return ResultUtils.success(classes);
    }

    /**
     * 班级管理修改
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public Map update(Classes classes){
        classesService.updateByPrimaryKey(classes);
        if(classesService.updateByPrimaryKey(classes)>0){
            return ResultUtils.success("修改成功");
        }else {
            return ResultUtils.success("修改失败");
        }
    }

    /**
     * 班级管理删除
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "delete",method =RequestMethod.GET)
    public Map delete(Integer id){
        Integer delete = classesService.deleteByPrimaryKey(id);
        if(delete>0){
            return ResultUtils.success("删除失败");
        }else {
            return ResultUtils.success("删除成功");
        }
    }

    /**
     * 科任教师、班干部名单excel导出
     *
     * @author zwy
     * @date 2019/1/10 14:20
     */
    @RequestMapping("/export-teacher-cadre")
    @GET
    @ResponseBody
    public Map exportTeacherAndCadre(HttpServletResponse response, Classes classes){
        classesService.exportTeacherAndCadre(response,classes);
        return null ;
    }

    /**
     * 导出签到表
     *
     * @author zwy
     * @date 2019/1/11 11:28
     */
    @RequestMapping("/exportcheckinform")
    public Map exportCheckInForm(HttpServletResponse response, Integer classesId,Integer termId){
        classesService.exportCheckInForm(response,classesId,termId);
        return null ;
    }
}
