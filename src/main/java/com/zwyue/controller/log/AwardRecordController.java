package com.zwyue.controller.log;

import com.github.pagehelper.PageInfo;
import com.zwyue.common.BaseController;
import com.zwyue.common.ResultUtils;
import com.zwyue.entity.AwardRecord;
import com.zwyue.entity.Teacher;
import com.zwyue.service.AwardRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 日志管理-获奖情况记录
 * @author yangli
 * @date 2018/12/25
 */
@RestController
@RequestMapping("/awardrecord")
public class AwardRecordController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(WorkPlanController.class);

    @Autowired
    private AwardRecordService awardRecordService;

    /**
     * 日志管理-获奖情况记录列表
     * @author yangli
     * @date 2018/11/28 14:10
     */
    @RequestMapping(value = "list" ,method = RequestMethod.GET)
    public Map getList(){
        PageInfo<AwardRecord> pageInfo=new PageInfo<AwardRecord>(awardRecordService.getList());
        return ResultUtils.success(pageInfo);
    }

    /**
     * 日志管理-获奖情况记录按标题查询
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "getTitle")
    public Map getTitle(String title){
        PageInfo<AwardRecord> pageInfo = new PageInfo<>(awardRecordService.getTitleList(title));
        return ResultUtils.success(pageInfo);
    }

    /**
     * 日志管理-获奖情记录保存
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public Map save(AwardRecord record, HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        record.setTid(teacher.getId());
        record.setTname(teacher.getTname());
        awardRecordService.insert(record);
        if(awardRecordService.insert(record)>0){
            return ResultUtils.success("保存成功");
        }else{
            return ResultUtils.success("保存失败");
        }
    }

    /**
     * 日志管理-获奖情记录修改页面
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "query",method = RequestMethod.GET)
    public Map query(Integer id){
        AwardRecord record = awardRecordService.selectByPrimaryKey(id);
        return ResultUtils.success(record);
    }

    /**
     * 日志管理-获奖情况记录修改
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public Map update(AwardRecord record, HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        record.setTid(teacher.getId());
        record.setTname(teacher.getTname());
        awardRecordService.updateByPrimaryKey(record);
        if(awardRecordService.updateByPrimaryKey(record)>0){
            return ResultUtils.success("修改成功");
        }else{
            return ResultUtils.success("修改失败");
        }
    }

    /**
     * 日志管理-获奖情况记录删除
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public Map delete(Integer id){
        Integer delete = awardRecordService.deleteByPrimaryKey(id);
        if(delete>0){
            return ResultUtils.success("删除失败");
        }else{
            return ResultUtils.success("删除成功");
        }
    }
}
