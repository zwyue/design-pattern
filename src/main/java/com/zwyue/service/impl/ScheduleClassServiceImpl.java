package com.zwyue.service.impl;

import com.zwyue.annotation.PagingQuery;
import com.zwyue.dao.CoursesDao;
import com.zwyue.dao.PlanRecordDao;
import com.zwyue.dao.initializingDao.CourseTimeInitializingDao;
import com.zwyue.entity.*;
import com.zwyue.service.ClassRoomService;
import com.zwyue.service.ScheduleClassService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Arrays;
import java.util.List;

import static com.zwyue.common.TimeUtil.compareTime;
import static com.zwyue.constant.SysConstant.Punctuation.COMMA;
import static com.zwyue.constant.SysConstant.Punctuation.SHORT_LINE;
import static com.zwyue.dao.initializingDao.CourseTimeInitializingDao.*;
import static com.zwyue.dao.initializingDao.CourseTimeInitializingDao.queryTimeSlotBySlotId;

/**
 * copyright    <a href="http://www.qaqavr.com/>中锐</a>
 * <pre>
 *     @author      zwy
 *     @date        2018/12/5 15:15
 *     email        1092478224@qq.com
 *     desc
 * </pre>
 */
@Service
public class ScheduleClassServiceImpl implements ScheduleClassService {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleClassServiceImpl.class);

    @Autowired
    private CourseTimeInitializingDao courseTimeInitializingDao ;

    @Autowired
    private PlanRecordDao planRecordDao ;

    @Autowired
    private CoursesDao coursesDao ;

    @Autowired
    private TransactionTemplate transactionTemplate ;

    @Autowired
    private ClassRoomService classRoomService ;

    /**
     * 课时预设
     *
     * @author zwy
     * @date 2018/12/6 15:15
     */
    @Override
    public Integer presetCourseTime(List<CourseTime> timeSlot){

        logger.info("--||--课时预设-------------------||-----");

        Integer n = 1 ;
        //时间对比
        for(int i=0;i<timeSlot.size()-1;i++) {
            String[] strings = timeSlot.get(i).getTime().split(SHORT_LINE);
            if(compareTime(strings[0],strings[1])){
                logger.error("-------||-----结束时间小于开始时间-----||---");
                throw new IllegalArgumentException("结束时间小于开始时间");
            }
            for (int j=i+1;j<timeSlot.size();j++){
                String[] strings1 = timeSlot.get(j).getTime().split(SHORT_LINE);
                if(compareTime(strings1[0],strings1[1])){
                    logger.error("-------||-----结束时间小于开始时间-----||---");
                    throw new IllegalArgumentException("结束时间小于开始时间");
                }
                if(compareTime(strings[1],strings1[0])){
                    logger.error("-------||-----开始时间小于上段结束时间-----||---");
                    throw new IllegalArgumentException("开始时间小于上段结束时间");
                }
            }
            //设置时间段代表
            timeSlot.get(i).setType(n.toString());
            n++;
        }
        timeSlot.get(timeSlot.size()-1).setType(n.toString());
        return courseTimeInitializingDao.savePresetCourseTime(timeSlot);
    }

    /**
     * 排课
     *
     * @author zwy
     * @date 2018/12/7 15:40
     */
    @Override
    public Integer scheduleCourse(PlanRecord planRecord, Teacher teacher) {
        //查询排课表中是否有时间冲突
        //全局缓存有效时间id list
        List<CourseTime> courseTimeList = queryActiveCourseTime();

        //是否选择全天
        if(StringUtils.isBlank(planRecord.getCourseid())){
            logger.info("........选择全天........");
            //选择全天
            List<String> planRecordList = planRecordDao.queryPlanRecordByRecord(planRecord);
            StringBuilder wholeDayTimeSlots = new StringBuilder();
            courseTimeList.forEach(ct->wholeDayTimeSlots.append(COMMA).append(ct.getTime()));

            planRecordList.forEach(pr->{
                //如果全天时间段包含已被安排过的时间段，则删除该时间段
                if (wholeDayTimeSlots.toString().contains(pr)){
                    wholeDayTimeSlots.delete(wholeDayTimeSlots.indexOf(pr),wholeDayTimeSlots.indexOf(pr)+2);
                }
            });
            logger.info(".......可用时间段,[{}]...........",wholeDayTimeSlots);
            List<String> lastTimeSlots = Arrays.asList(wholeDayTimeSlots.toString().split(COMMA));
            if(lastTimeSlots.size()>0){
                planRecord.setTname(lastTimeSlots.get(0));
            }
        }else {
            logger.info(".............选择具体时间段............");
            int existPlanRecord = planRecordDao.queryIfExist(planRecord);
            if(existPlanRecord>0){
                logger.error("-------||-----该排课时间有冲突-----||---");
                throw new IllegalArgumentException("该排课时间有冲突");
            }
        }

        CourseTime newCourseTime = queryTimeSlotBySlotId(planRecord.getCourseid());

        //排课人
        planRecord.setTname(teacher.getTname());
        planRecord.setTid(teacher.getId());

        //课程表实体
        Courses courses = new Courses();
        //教室名称，视情况决定后续是否添加教室id
        courses.setClassroom(planRecord.getClassroom());
        courses.setWeek(planRecord.getWeek());
        courses.setClasses(planRecord.getClassname());
        courses.setClassesid(planRecord.getClassid());
        //授课老师名称，授课老师id后期视情况决定是否添加
        courses.setTeacher(planRecord.getTeacher());
        courses.setTid(teacher.getId());
        courses.setTname(teacher.getTname());
        courses.setDate(newCourseTime.getTime());

        return  transactionTemplate.execute(insert-> {
            int isSuccess = 0;
            logger.info("---||---保存排课记录--------------");
            //保存排课记录
            isSuccess += planRecordDao.insertSelective(planRecord);
            //保存课程，形成课表
            logger.info("---||---保存课程表---------");
            isSuccess += coursesDao.insertSelective(courses);
            return isSuccess;
        });
    }

    /**
     * 排课前时间段预设（单条）
     *
     * @author zwy
     * @date 2018/12/7 16:46
     */
    @Override
    public Integer addCourseTime(CourseTime courseTime) {
        List<String> courseTimes = getActiveTime();
        String startTime = courseTime.getTime().split(SHORT_LINE)[0];
        String endTime = courseTime.getTime().split(SHORT_LINE)[1];
        if(compareTime(startTime,endTime)){
            throw new IllegalArgumentException("时间冲突");
        }
        if(courseTimes.size()!=0){
            courseTimes.forEach(ct->{
                String savedStartTime = ct.split(SHORT_LINE)[0];
                String savedEndTime = ct.split(SHORT_LINE)[1];
                if(compareTime(savedStartTime,startTime)
                    ||compareTime(savedEndTime,startTime)
                    ||compareTime(savedEndTime,endTime)
                ) {
                    throw new IllegalArgumentException("时间冲突");
                }
            });
        }
        return courseTimeInitializingDao.addCourseTime(courseTime);
    }

    @Override
    @PagingQuery
    public List<CourseTime> findCourseTimes() {
        return queryAllCourseTime();
    }

    @Override
    @PagingQuery
    public List<PlanRecord> queryAllPlanRecord() {
        //根据时间段id查询时间段
        List<PlanRecord> planRecords = planRecordDao.queryAllPlanRecord();
        planRecords.forEach(pr->{
            CourseTime courseTime = queryTimeSlotBySlotId(pr.getCourseid());
            pr.setTimeSlot(courseTime.getTime());
        });
        return planRecords ;
    }

    @Override
    public List<CourseTime> queryAllActiveTimes() {
        return queryActiveCourseTime();
    }

    @Override
    public List<Classroom> queryClassRoom() {
        return classRoomService.queryClassroomList();
    }

}
