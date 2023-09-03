package com.zwyue.service.impl;

import com.zwyue.dao.ClassesDao;
import com.zwyue.dao.RosterDao;
import com.zwyue.entity.Classes;
import com.zwyue.entity.Roster;
import com.zwyue.service.ClassesService;
import com.zwyue.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zwyue.common.TimeUtil.calculateAge;
import static com.zwyue.constant.SysConstant.MAP_DEFAULT_SIZE;
import static com.zwyue.constant.SysConstant.SheetTitle.*;
import static com.zwyue.util.FileUtil.sentResponseHeader;

/**
 * 班级管理
 * @author yangli
 * @date 2018/12/25
 */
@Service
public class ClassesServiceImpl implements ClassesService {

    @Autowired
    private ClassesDao classesDao;

    @Autowired
    private RosterDao rosterDao ;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return classesDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Classes record) {
        return classesDao.insert(record);
    }

    @Override
    public int insertSelective(Classes record) {
        return classesDao.insertSelective(record);
    }

    @Override
    public Classes selectByPrimaryKey(Integer id) {
        return classesDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Classes record) {
        return classesDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Classes record) {
        return classesDao.updateByPrimaryKey(record);
    }

    @Override
    public List<Classes> getList() {
        return classesDao.getList();
    }

    @Override
    public List<Classes> byNameList(String name) {
        return classesDao.byNameList(name);
    }

    @Override
    public List<Classes> getCList(Integer majorid) {
        return classesDao.getCList(majorid);
    }

    @Override
    public List<Classes> getTeaClasslist(Integer headmaster) {
        return classesDao.getTeaClasslist(headmaster);
    }

    @Override
    public void exportTeacherAndCadre(HttpServletResponse response, Classes classes) {

        List<Classes> teacherAndCadres =  classesDao.queryClasses(classes);
        //excel文件名
        String fileName = "科任教师、班干部名单"+System.currentTimeMillis()+".xls";

        //sheet名
        String sheetName = "科任教师、班干部名单";

        //excel标题
        String[] title = {SERIAL_NUMBER,CLAZZ,TEACHER,MONITOR,STUDY_COMMITTEE,SAFE_COMMITTEE,CONTACT,REMARKS};

        String[][] values = new String[teacherAndCadres.size()][];

        for (int i = 0; i < teacherAndCadres.size(); i++) {
            values[i] = new String[title.length];
            Classes obj = teacherAndCadres.get(i);
            values[i][0] = i+1+"" ;
            values[i][1] = obj.getClassname();
            values[i][2] = obj.getTname();
            values[i][3] = obj.getMonitor();
            values[i][4] = obj.getStudyer();
            values[i][5] = obj.getSafer();
            values[i][6] = obj.getPhone();
            values[i][7] = "";
        }
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName,"科任教师、班干部名单",title,values);
        HSSFSheet sheet = wb.getSheet(sheetName);
        sheet.setColumnWidth(6,15*256);
        CellRangeAddress callRangeAddress = new CellRangeAddress(0,0,0,7);
        sheet.addMergedRegion(callRangeAddress);
        RegionUtil.setBorderLeft(BorderStyle.NONE,callRangeAddress,sheet);
        RegionUtil.setBorderTop(BorderStyle.NONE,callRangeAddress,sheet);

        //响应到客户端
        sentResponseHeader(response, fileName,wb);
    }

    @Override
    public void exportCheckInForm(HttpServletResponse response, Integer classesId,Integer termId) {

        Map<String,Integer> map = new HashMap<>(MAP_DEFAULT_SIZE) ;
        map.put("classId",classesId);
        map.put("termId",termId);

        List<Roster> rosters = rosterDao.queryRosterByClassIdAndTerm(map);

        //excel文件名
        String fileName = "考勤表"+System.currentTimeMillis()+".xls";

        //sheet名
        String sheetName = "考勤表";

        //excel标题
        String[] title = {SERIAL_NUMBER,STUDENT_ID,NAME,SEX,AGE,PHONE,FAM_PHONE};

        String[][] values = new String[rosters.size()][22];

        for (int i = 0; i < rosters.size(); i++) {
            values[i] = new String[title.length];
            Roster obj = rosters.get(i);
            String birthDate = obj.getBirthdate();
            values[i][0] = i+1+"" ;
            values[i][1] = obj.getStunumber();
            values[i][2] = obj.getStuname();
            values[i][3] = obj.getSex();
            values[i][4] = calculateAge(birthDate)+"";
            values[i][5] = obj.getPhone();
            values[i][6] = obj.getFamPhone();
            for (int j = 7 ;j<22 ;j ++ ){
                values[i][j] = "" ;
            }
        }

        HSSFWorkbook wb = ExcelUtil.getAttendanceSheet(sheetName,"考勤表",title,values);
        HSSFSheet sheet = wb.getSheet(sheetName);

        sheet.setColumnWidth(0,5*256);
        sheet.setColumnWidth(1,8*256);
        sheet.setColumnWidth(2,8*256);
        sheet.setColumnWidth(3,5*256);
        sheet.setColumnWidth(4,5*256);
        sheet.setColumnWidth(5,14*256);
        sheet.setColumnWidth(6,14*256);
        for(int i = 7 ;i<22;i++){
            sheet.setColumnWidth(i,3*256);
        }

        CellRangeAddress callRangeAddress = new CellRangeAddress(0,0,0,21);
        sheet.addMergedRegion(callRangeAddress);
        RegionUtil.setBorderLeft(BorderStyle.NONE,callRangeAddress,sheet);
        RegionUtil.setBorderTop(BorderStyle.NONE,callRangeAddress,sheet);

        callRangeAddress = new CellRangeAddress(1,1,0,4);
        sheet.addMergedRegion(callRangeAddress);
        RegionUtil.setBorderLeft(BorderStyle.NONE,callRangeAddress,sheet);
        RegionUtil.setBorderTop(BorderStyle.NONE,callRangeAddress,sheet);

        callRangeAddress = new CellRangeAddress(1,1,5,8);
        sheet.addMergedRegion(callRangeAddress);
        RegionUtil.setBorderLeft(BorderStyle.NONE,callRangeAddress,sheet);
        RegionUtil.setBorderTop(BorderStyle.NONE,callRangeAddress,sheet);

        callRangeAddress = new CellRangeAddress(1,1,9,21);
        sheet.addMergedRegion(callRangeAddress);
        RegionUtil.setBorderLeft(BorderStyle.NONE,callRangeAddress,sheet);
        RegionUtil.setBorderTop(BorderStyle.NONE,callRangeAddress,sheet);

        //响应到客户端
        sentResponseHeader(response, fileName,wb);
    }
}
