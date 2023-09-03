package com.zwyue.controller;

import com.github.pagehelper.PageInfo;
import com.zwyue.common.BaseController;
import com.zwyue.common.ResultUtils;
import com.zwyue.entity.SpecialManager;
import com.zwyue.entity.Teacher;
import com.zwyue.service.SpecialManagerService;
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
 * 特殊学员管理
 * @author yangli
 * @date 2018/12/25
 */
@RestController
@RequestMapping("/specialmanager")
public class SpecialManagerController extends BaseController {

    @Autowired
    private SpecialManagerService specialManagerService;

    /**
     * 查询特殊学员列表
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public Map list(HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        Map map = new HashMap();
        map.put("classids", Arrays.asList(teacher.getClassid().split(COMMA)));
        PageInfo<SpecialManager> pageInfo = new PageInfo<>(specialManagerService.getList(map));
        return ResultUtils.success(pageInfo);
    }

    /**
      * 根据名称和异动原因查询特殊学员记录
      * @author yangli
      * @date 2019/1/10
      * @param name,types
      */
    @RequestMapping(value = "getStuByName",method = RequestMethod.GET)
    public Map getStuByName(String name,String types,HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        Map map = new HashMap();
        map.put("classids", Arrays.asList(teacher.getClassid().split(COMMA)));
        PageInfo<SpecialManager> pageInfo = new PageInfo<>(specialManagerService.getNameTypes(map,name,types));
        return ResultUtils.success(pageInfo);
    }

    /**
     * 特殊学员新增保存
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public Map save(SpecialManager record, HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        record.setTid(teacher.getId());
        record.setTname(teacher.getTname());
        specialManagerService.insert(record);
        if(specialManagerService.insert(record)>0){
            return ResultUtils.success("保存成功");
        }else{
            return ResultUtils.success("保存失败");
        }
    }

    /**
     * 查询某一条的特殊学员信息
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "query",method = RequestMethod.GET)
    public Map query(Integer id){
        SpecialManager record = specialManagerService.selectByPrimaryKey(id);
        return ResultUtils.success(record);
    }

    /**
     * 修改特殊学员信息
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public Map update(SpecialManager record){
        specialManagerService.updateByPrimaryKey(record);
        if(specialManagerService.updateByPrimaryKey(record)>0){
            return ResultUtils.success("修改成功");
        }else{
            return ResultUtils.success("修改失败");
        }
    }

    /**
     * 删除一条特殊学员
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public Map delete(Integer id){
        Integer delete = specialManagerService.deleteByPrimaryKey(id);
        if(delete>0){
            return ResultUtils.success("删除失败");
        }else {
            return ResultUtils.success("删除成功");
        }
    }
}
