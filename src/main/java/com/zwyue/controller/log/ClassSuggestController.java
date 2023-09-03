package com.zwyue.controller.log;

import com.github.pagehelper.PageInfo;
import com.zwyue.common.BaseController;
import com.zwyue.common.ResultUtils;
import com.zwyue.entity.ClassSuggest;
import com.zwyue.entity.Teacher;
import com.zwyue.service.ClassSuggestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.zwyue.constant.SysConstant.Punctuation.COMMA;

/**
 * 日志管理-班级问题和建议清单管理
 * @author yangli
 * @date 2018/12/25
 */
@RestController
@RequestMapping("classsuggest")
public class ClassSuggestController extends BaseController {

    @Autowired
    private ClassSuggestService classSuggestService;

    /**
     * 日志管理-班级问题和建议清单列表
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public Map list(HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        Map map = new HashMap();
        map.put("classids", Arrays.asList(teacher.getClassid().split(COMMA)));
        PageInfo<ClassSuggest> pageInfo=new PageInfo<ClassSuggest>(classSuggestService.getList(map));
        return ResultUtils.success(pageInfo);
    }

    /**
     * 日志管理-班级问题和建议清单按标题查询
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "getTitle")
    public Map getTitle(String title){
        PageInfo<ClassSuggest> pageInfo = new PageInfo<>(classSuggestService.getTitleList(title));
        return ResultUtils.success(pageInfo);
    }

    /**
     * 日志管理-班级问题和建议清单新增保存
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public Map save(ClassSuggest record, HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        record.setTid(teacher.getId());
        record.setTname(teacher.getTname());
        classSuggestService.insert(record);
        if(classSuggestService.insert(record)>0){
            return ResultUtils.success("保存成功");
        }else{
            return ResultUtils.success("保存失败");
        }
    }

    /**
     * 日志管理-班级问题和建议清单修改页面
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "query",method = RequestMethod.GET)
    public Map query(Integer id){
        ClassSuggest classSuggest = classSuggestService.selectByPrimaryKey(id);
        return ResultUtils.success(classSuggest);
    }

    /**
     * 日志管理-班级问题和建议清单修改保存
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public Map update(ClassSuggest record){
        classSuggestService.updateByPrimaryKey(record);
        if(classSuggestService.updateByPrimaryKey(record)>0){
            return ResultUtils.success("修改成功");
        }else{
            return ResultUtils.success("修改失败");
        }
    }

    /**
     * 日志管理-班级问题和建议清单删除
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public Map delete(Integer id){
        Integer delete = classSuggestService.deleteByPrimaryKey(id);
        if(delete>0){
            return ResultUtils.success("删除失败");
        }else{
            return ResultUtils.success("删除成功");
        }
    }
}
