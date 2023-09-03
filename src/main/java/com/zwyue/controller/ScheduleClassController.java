package com.zwyue.controller;

import com.github.pagehelper.PageInfo;
import com.zwyue.common.BaseController;
import com.zwyue.entity.CourseTime;
import com.zwyue.entity.PlanRecord;
import com.zwyue.service.ScheduleClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * copyright    <a href="http://www.qaqavr.com/>中锐</a>
 * <pre>
 *     @author      zwy
 *     @date        2018/12/5 15:07
 *     email        1092478224@qq.com
 *     desc         排课管理
 * </pre>
 */
@Controller
@RequestMapping("scheduleClass")
public class ScheduleClassController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleClassController.class);

    @Autowired
    private ScheduleClassService scheduleClassService ;

    /*
     * *****************************排课预设***********************************
     */

    /**
     *
     *
     * @author zwy
     * @date 2018/12/7 17:39
     */
    @RequestMapping(value = "toPreTimePage",method = RequestMethod.GET)
    public String queryPresetTimeTable(Model model){
        List<CourseTime> courseTimes = scheduleClassService.findCourseTimes();
        model.addAttribute("page", new PageInfo<CourseTime>(courseTimes));
        model.addAttribute("list",courseTimes);
        return "/course/course_preset_manage";
    }

    /**
     * 排课之前的全校课程时间段预设(批量添加)
     *
     * @author zwy
     * @date 2018/12/5 15:23
     */
    @RequestMapping(value = "presetCourseTime",method = RequestMethod.POST)
    public String presetCourseTime(@RequestParam(value = "timeSlot[]") List<CourseTime> timeSlot){
        scheduleClassService.presetCourseTime(timeSlot);
        return null ;
    }

    /**
     * 排课预设
     *
     * @author zwy
     * @date 2018/12/7 16:30
     */
    @RequestMapping(value = "presetCourseTimeByOne",method = RequestMethod.POST)
    public String presetCourseTimeByOne(CourseTime courseTime){
        scheduleClassService.addCourseTime(courseTime);
        return "redirect:toPreTimePage" ;
    }


    /*
     *  ----------               排课               -------------------
     */

    /**
     * 进入排课管理页面
     *
     * @author zwy
     * @date 2018/12/8 12:39
     */
    @RequestMapping(value = "toPlanRecord",method = RequestMethod.GET)
    public String toPlanRecordPage(Model model){
        List<PlanRecord> planRecords = scheduleClassService.queryAllPlanRecord();
        model.addAttribute("list",planRecords);
        model.addAttribute("preset",scheduleClassService.queryAllActiveTimes());
        model.addAttribute("classroom",scheduleClassService.queryClassRoom());
        model.addAttribute("page",new PageInfo<>(planRecords));
        return "course/course_manage";
    }

    /**
     * 排课
     *
     * @author zwy
     * @date 2018/12/6 9:16
     */
    @RequestMapping(value = "ScheduleCourse",method = RequestMethod.POST)
    public String scheduleCourse(PlanRecord planRecord, HttpSession httpSession){
        scheduleClassService.scheduleCourse(planRecord,getLoginUser(httpSession));
        return "redirect:toPlanRecord" ;
    }
}
