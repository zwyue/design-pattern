package com.zwyue.controller.log;

import com.github.pagehelper.PageInfo;
import com.zwyue.common.BaseController;
import com.zwyue.common.ResultUtils;
import com.zwyue.entity.MeetRecord;
import com.zwyue.entity.Teacher;
import com.zwyue.service.MeetRecordService;
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
 * 班会记录管理
 * @author yangli
 * @date 2018/12/26
 */
@RestController
@RequestMapping("/meetrecord")
public class MeetRecordController extends BaseController {

    @Autowired
    private MeetRecordService meetRecordService;

    /**
     * 查询班会记录列表
     * @author yangli
     * @date 2018/12/26
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public Map list(HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        Map map = new HashMap();
        map.put("classids", Arrays.asList(teacher.getClassid().split(COMMA)));
        PageInfo<MeetRecord> pageInfo=new PageInfo<MeetRecord>(meetRecordService.getList(map));
        return ResultUtils.success(pageInfo);
    }

    /**
     * 日志管理-班会记录按标题查询
     * @author yangli
     * @date 2018/12/26
     */
    @RequestMapping(value = "getTitle")
    public Map getclassList(HttpSession httpSession,String content){
        Teacher teacher = getLoginUser(httpSession);
        Map map = new HashMap();
        map.put("classids", Arrays.asList(teacher.getClassid().split(COMMA)));
        PageInfo<MeetRecord> pageInfo = new PageInfo<>(meetRecordService.getTitleList(map,content));
        return ResultUtils.success(pageInfo);
    }

    /**
     * 保存班会记录
     * @author yangli
     * @date 2018/12/26
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public Map save(MeetRecord record,HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        record.setTid(teacher.getId());
        record.setTname(teacher.getTname());
        meetRecordService.insert(record);
        if (meetRecordService.insert(record)>0){
            return ResultUtils.success("保存成功");
        }else{
            return ResultUtils.success("保存失败");
        }
    }

    /**
     * 查询一条班会记录详情
     * @author yangli
     * @date 2018/12/27
     */
    @RequestMapping(value = "query",method = RequestMethod.GET)
    public Map query(Integer id){
        MeetRecord record = meetRecordService.selectByPrimaryKey(id);
        return ResultUtils.success(record);
    }

    /**
     * 班会记录修改保存
     * @author yangli
     * @date 2018/12/27
     */
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public Map update(MeetRecord record){
        meetRecordService.updateByPrimaryKey(record);
        if(meetRecordService.updateByPrimaryKey(record)>0){
            return ResultUtils.success("修改成功");
        }else{
            return ResultUtils.success("修改失败");
        }
    }

    /**
     * 删除一条班会记录
     * @author yangli
     * @date 2018/12/27
     */
    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public Map delete(Integer id){
        Integer delete = meetRecordService.deleteByPrimaryKey(id);
        if(delete>0){
            return ResultUtils.success("删除失败");
        }else{
            return ResultUtils.success("删除成功");
        }
    }
}
