package com.zwyue.service.impl;

import com.zwyue.annotation.PagingQuery;
import com.zwyue.dao.CoursesDao;
import com.zwyue.entity.Courses;
import com.zwyue.entity.WeekClass;
import com.zwyue.enums.WeekEnum;
import com.zwyue.service.CourseService;
import com.zwyue.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.zwyue.constant.SysConstant.Punctuation.COMMA;
import static com.zwyue.constant.SysConstant.SheetTitle.CLASS_ROOM;
import static com.zwyue.constant.SysConstant.SheetTitle.TIME;
import static com.zwyue.enums.WeekEnum.*;
import static com.zwyue.util.FileUtil.sentResponseHeader;

/**
 * copyright    <a href="http://www.qaqavr.com/>中锐</a>
 * <pre>
 *     @author      zwy
 *     @date        2018/12/6 15:33
 *     email        1092478224@qq.com
 *     desc         课程管理接口实现类
 * </pre>
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CoursesDao coursesDao ;


    @Override
    @PagingQuery
    public List<WeekClass> queryCourseList(Courses courses) {

        List<Courses> coursesList = coursesDao.queryCourseList(courses);

        List<WeekClass> weekList = new ArrayList<>();

        //先放时间段，再放教室
        Map<String,List<Courses>> clistMap = coursesList.stream().collect(Collectors.groupingBy(Courses::getDate));
        clistMap.keySet().stream().sorted().forEach(key->{
            List<Courses> clist = coursesList.stream().filter(cl->cl.getDate().equals(key)).collect(Collectors.toList());
            Map<String,List<Courses>> mapList = clist.stream().collect(Collectors.groupingBy(Courses::getClassroom));
            mapList.keySet().stream().sorted().forEach(k->{
                WeekClass weekCls = new WeekClass();
                weekCls.setClassroom(k);
                weekCls.setTimeSlot(key);
                weekList.add(weekCls);
            });
        });

        //最后放置课程
        weekList.forEach(wl->{
            coursesList.forEach(cl->{
                if(wl.getClassroom().equals(cl.getClassroom())&&wl.getTimeSlot().equals(cl.getDate())){
                    assembleEnum(wl,cl);
                }
            });
        });
        return weekList;
    }

    /**
     * 讲课程放入对应的星期中
     *
     * @author zwy
     * @date 2019/1/10 13:52
     */
    private void assembleEnum(WeekClass weekClass,Courses c){
        switch (WeekEnum.returnDayByCode(c.getWeek())){
            case MONDAY:weekClass.setMonday(c.getClasses()+ COMMA +c.getTeacher());break;
            case TUESDAY:weekClass.setTuesday(c.getClasses()+ COMMA +c.getTeacher());break;
            case WEDNESDAY:weekClass.setWednesday(c.getClasses()+ COMMA +c.getTeacher());break;
            case THURSDAY:weekClass.setThursday(c.getClasses()+ COMMA +c.getTeacher());break;
            case FRIDAY:weekClass.setFriday(c.getClasses()+ COMMA +c.getTeacher());break;
            default:case SATURDAY:
        }
    }

    @Override
    public void exportCourse(HttpServletResponse response, Courses courses){

        List<WeekClass> weekClasses = queryCourseList(courses);

        //excel文件名
        String fileName = "南开区老年大学课表"+System.currentTimeMillis()+".xls";

        //sheet名
        String sheetName = "2018秋课表";

        //excel标题
        String[] title = {TIME,CLASS_ROOM,MONDAY.day,TUESDAY.day,WEDNESDAY.day,THURSDAY.day,FRIDAY.day};

        String values[][] = new String[weekClasses.size()][];

        for (int i = 0; i < weekClasses.size(); i++) {
            values[i] = new String[title.length];
            WeekClass obj = weekClasses.get(i);
            values[i][0] = obj.getTimeSlot();
            values[i][1] = obj.getClassroom();
            values[i][2] = obj.getMonday();
            values[i][3] = obj.getTuesday();
            values[i][4] = obj.getWednesday();
            values[i][5] = obj.getThursday();
            values[i][6] = obj.getFriday();
        }

        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName,"课表",title,values, null);

        HSSFSheet sheet = wb.getSheet(sheetName);

        //时间列5个字符宽
        sheet.setColumnWidth(0,9*256);

        //教室列
        sheet.setColumnWidth(1,10*256);

        //星期列
        for(int n = 2 ;n<=6; n++){
            sheet.setColumnWidth(n,15*256);
        }

        CellRangeAddress callRangeAddress = new CellRangeAddress(0,0,0,6);
        sheet.addMergedRegion(callRangeAddress);

        RegionUtil.setBorderLeft(BorderStyle.NONE,callRangeAddress,sheet);
        RegionUtil.setBorderTop(BorderStyle.NONE,callRangeAddress,sheet);

        //按时间段分类
        Map<String,List<WeekClass>> mapWeekClass = weekClasses.stream().collect(Collectors.groupingBy(WeekClass::getTimeSlot));

        int j = 2 ;
        for (int i = mapWeekClass.keySet().size() -1 ;i >=0 ; i--){
            List<WeekClass> wc = mapWeekClass.get(mapWeekClass.keySet().toArray()[i]);
            //创建合并单元格对象(起始行,结束行,起始列,结束列)
            if(wc.size()>1){
                callRangeAddress = new CellRangeAddress(j,wc.size()+j-1,0,0);
                sheet.addMergedRegion(callRangeAddress);
            }
            j += wc.size() ;
        }

        //响应到客户端
        sentResponseHeader(response, fileName,wb);
    }
}
