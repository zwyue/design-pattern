package com.zwyue.dao.initializingDao;

import com.zwyue.constant.SysConstant;
import com.zwyue.dao.CourseTimeDao;
import com.zwyue.entity.CourseTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * copyright    <a href="http://www.qaqavr.com/>中锐</a>
 * <pre>
 *     @author      zwy
 *     @date        2018/12/8 10:23
 *     email        1092478224@qq.com
 *     desc         课程预设初始化bean作为全局缓存
 * </pre>
 */
@Repository
public class CourseTimeInitializingDao implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(CourseTimeInitializingDao.class);

    /**
     * 全部预设课程时间
     *
     * @date 2018/12/8 11:28
     */
    private static List<CourseTime> courseTimeList = new ArrayList<>() ;

    /**
     * 有效时间段实体类
     *
     * @date 2018/12/8 11:27
     */
    private static List<CourseTime> courseTime= new ArrayList<>();

    /**
     *  有效时间段
     *
     * @date 2018/12/8 11:27
     */
    private static List<String> timeList = new ArrayList<>();

    @Autowired
    private CourseTimeDao courseTimeDao ;

    /**
     * 查询全部预设时间段实体类
     *
     * @author zwy
     * @date 2018/12/8 11:10
     */
    public static List<CourseTime> queryAllCourseTime(){
        return courseTimeList ;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        logger.info(".........初始化预设课时........");

        courseTimeList = courseTimeDao.queryAllTimeSlot();

        //todo-此处不安全，如果同时有另外的请求，因为时间list被清空，其查询为空
        courseTime.clear();
        timeList.clear();
        courseTimeList.forEach(ctl->{
            if(SysConstant.IsEnable.ENABLE.equals(ctl.getStatus())){
                courseTime.add(ctl);
                timeList.add(ctl.getTime());
            }
        });
    }


    public static List<String> getActiveTime(){
        return timeList ;
    }

    public static List<CourseTime> queryActiveCourseTime(){
        return courseTime ;
    }

    public Integer addCourseTime(CourseTime oneTime){
        int i = courseTimeDao.insert(oneTime);
        cacheCourseTime(i);
        return i ;
    }

    /**
     * 批量保存课时时间段
     *
     * @author zwy
     * @date 2018/12/8 11:15
     */
    public Integer savePresetCourseTime(List<CourseTime> timeSlot) {
        int i =  courseTimeDao.savePresetCourseTime(timeSlot);
        cacheCourseTime(i);
        return i ;
    }

    /**
     * 缓存新建时间段
     *
     * @author zwy
     * @date 2018/12/8 11:17
     */
    private void cacheCourseTime(int i){
        if(i!=0){
            try {
                afterPropertiesSet();
            } catch (Exception e) {
                logger.error("..........缓存失败.........");
            }
        }
    }

    /**
     * 根据课时段id查询课时段
     *
     * @author zwy
     * @date 2018/12/10 9:54
     */
    public static CourseTime queryTimeSlotBySlotId(String courseid) {
        List<CourseTime> newCourseTime = courseTime.stream().filter(ct->courseid.equals(ct.getId().toString())).collect(Collectors.toList());
        return newCourseTime.size()==0?null:newCourseTime.get(0);
    }
}
