package com.zwyue.controller.teacher;

import com.github.pagehelper.PageInfo;
import com.zwyue.common.BaseController;
import com.zwyue.common.ResultUtils;
import com.zwyue.entity.Teacher;
import com.zwyue.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.util.Map;

import static com.zwyue.exception.ExceptionEnum.*;

/**
 * copyright    <a href="http://www.qaqavr.com/>中锐</a>
 * <pre>
 *     @author      zwy
 *     @date        2018/12/20 13:47
 *     email        1092478224@qq.com
 *     desc         教师管理
 * </pre>
 */
@RestController
@RequestMapping("teacher")
public class TeacherController extends BaseController {

    @Autowired
    private TeacherService teacherService ;

    /**
     * 获取教师列表
     *
     * @author zwy
     * @date 2018/12/20 13:58
     */
    @RequestMapping("list")
    @GET
    public Map teacherInfo(){
        PageInfo<Teacher> pageInfo = new PageInfo<>(teacherService.queryAllTeacher());
        return ResultUtils.success(pageInfo);
    }

    /**
     * 录入教师人员信息
     *
     * @author zwy
     * @date 2018/12/20 18:07
     */
    @RequestMapping("enter")
    @POST
    public Map enterTeacherInfo(Teacher teacher){
        Integer ifEnter = teacherService.enterTeacher(teacher);
        if(ifEnter>0){
            return ResultUtils.error(TEACHER_EXIST.errorCode,TEACHER_EXIST.errorMessage);
        }
        return ResultUtils.error(SUCCESS.errorCode,SUCCESS.errorMessage) ;
    }

    /**
     * 删除人员信息 todo - 批量删除
     *
     * @author zwy
     * @date 2018/12/20 18:08
     */
    @RequestMapping("delete")
    public Map deleteTeacher(Integer id){
        Integer ifDelete = teacherService.deleteTeacher(id);
        if(ifDelete>0){
            return ResultUtils.success(SUCCESS.errorCode,SUCCESS.errorMessage);
        }else {
            return ResultUtils.error(DELETE_FAILED.errorCode,DELETE_FAILED.errorMessage);
        }
    }

    /**
     * 更新教师信息
     *
     * @author zwy
     * @date 2018/12/20 18:30
     */
    @RequestMapping("update")
    @POST
    public Map updateTeacher(Teacher teacher){
        Integer ifUpdate = teacherService.updateTeacher(teacher);
        if(ifUpdate>0){
            return ResultUtils.success(SUCCESS.errorCode,SUCCESS.errorMessage);
        }else {
            return ResultUtils.error(UPDATE_FAILED.errorCode,UPDATE_FAILED.errorMessage);
        }
    }
}
