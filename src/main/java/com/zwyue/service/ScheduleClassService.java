package com.zwyue.service;

import com.zwyue.entity.Classroom;
import com.zwyue.entity.CourseTime;
import com.zwyue.entity.PlanRecord;
import com.zwyue.entity.Teacher;

import java.util.List;

/**
 * copyright    <a href="http://www.qaqavr.com/>中锐</a>
 * <pre>
 *     @author      zwy
 *     @date        2018/12/5 15:12
 *     email        1092478224@qq.com
 *     desc
 * </pre>
 */
public interface ScheduleClassService {

    /**
     * 排课之前的时间预设
     *
     * @author zwy
     * @date 2018/12/5 15:47
     * @param timeSlot 时间段
     * @return int
     */
    Integer presetCourseTime(List<CourseTime> timeSlot) ;

    /**
     * 排课记录及生成课程
     *
     * @author zwy
     * @date 2018/12/6 9:16
     * @param planRecord 排课记录
     * @param teacher 当前
     * @return int
     */
    Integer scheduleCourse(PlanRecord planRecord, Teacher teacher);

    /**
     * 时间段预设（单条）
     *
     * @author zwy
     * @date 2018/12/7 16:45
     * @param courseTime 时间段实体
     * @return int
     */
    Integer addCourseTime(CourseTime courseTime);

    /**
     * 查询课程时间预设表
     *
     * @author zwy
     * @date 2018/12/7 17:41
     * @return list
     */
    List<CourseTime> findCourseTimes();

    /**
     * 查询排课记录
     *
     * @author zwy
     * @date 2018/12/8 12:44
     * @return list
     */
    List<PlanRecord> queryAllPlanRecord();

    /**
     * 查询全部可用时间段
     *
     * @author zwy
     * @date 2018/12/8 12:58
     * @return list
     */
    List<CourseTime> queryAllActiveTimes();

    /**
     * 获取教室
     *
     * @author zwy
     * @date 2018/12/8 13:58
     * @return list
     */
    List<Classroom> queryClassRoom();
}
