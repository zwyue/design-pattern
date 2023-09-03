package com.zwyue.controller.archive;

import com.github.pagehelper.PageInfo;
import com.zwyue.common.BaseController;
import com.zwyue.common.ResultUtils;
import com.zwyue.common.TimeUtil;
import com.zwyue.entity.Scientific;
import com.zwyue.entity.Teacher;
import com.zwyue.service.ScientificService;
import com.zwyue.util.DocUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 科研课题管理
 * @author yangli
 * @date 2018/12/25
 */
@CrossOrigin
@RestController
@RequestMapping("/scientific")
public class ScientificController extends BaseController {

    @Autowired
    private ScientificService scientificService;

    /**
     * 科研课题列表
     * @author yangli
     * @date 2018/11/28 14:10
     */
    @RequestMapping(value = "list" ,method = RequestMethod.GET)
    public Map getList(){
        PageInfo<Scientific> pageInfo = new PageInfo<>(scientificService.getList());
        return ResultUtils.success(pageInfo);
    }

    /**
     * 科研课题保存
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "save" ,method = RequestMethod.POST)
    @ResponseBody
    public Map save(Scientific scientific, HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        scientific.setUserid(teacher.getId());
        scientific.setUsername(teacher.getTname());
        scientific.setCrttime(TimeUtil.dateToStrLong(new Date()));
        scientificService.insert(scientific);
        if(scientificService.insert(scientific)>0){
            return ResultUtils.success("保存成功");
        }else{
            return ResultUtils.error("保存失败");
        }
    }

    /**
     * 科研课题修改
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "update" ,method = RequestMethod.POST)
    @ResponseBody
    public Map update(Scientific scientific,HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        scientific.setUserid(teacher.getId());
        scientific.setUsername(teacher.getTname());
        scientificService.updateByPrimaryKey(scientific);
        if(scientificService.updateByPrimaryKey(scientific)>0){
            return ResultUtils.success("保存成功");
        }else{
            return ResultUtils.error("保存失败");
        }
    }

    /**
     * 删除
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "delete" ,method = RequestMethod.GET)
    public Map delete(Integer id){
        Integer delete = scientificService.deleteByPrimaryKey(id);
        if(delete>0){
            return ResultUtils.success("删除失败");
        }else {
            return ResultUtils.error("删除成功");
        }
    }

    /**
     * 查询
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "query" ,method = RequestMethod.GET)
    public Map query(Integer id){
        Scientific scientific = scientificService.selectByPrimaryKey(id);
        return ResultUtils.success(scientific);
    }

    /**
     * 根据标题查询科研课题列表
     * @author yangli
     * @date 2019/1/3
     * @param
     * @return
     */
    @RequestMapping(value = "getTitle")
    public Map getTitle(String title){
        PageInfo<Scientific> pageInfo = new PageInfo<>(scientificService.getTitleList(title));
        return ResultUtils.success(pageInfo);
    }

    /**
     * 导出科研活动并下载word文档
     * @author yangli
     * @date 2019/1/9
     */
    @RequestMapping(value = "export")
    public void getDoc(HttpServletRequest request, HttpServletResponse response, Integer id) {
        String names="scientific.xml";
        Scientific scientific=scientificService.selectByPrimaryKey(id);
        Map<String,String> dataMap = new HashMap<String,String>();
        dataMap.put("time", TimeUtil.getUserDate(scientific.getCrttime()));
        dataMap.put("content", scientific.getContent());
        String newWordName = "科研活动记录.doc";
        //调用打印word的函数
        DocUtil.download(request, response, newWordName, dataMap,names);
    }
}
