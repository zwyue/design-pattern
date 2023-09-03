package com.zwyue.service;

import com.zwyue.entity.Classes;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 班级管理
 * @author yangli
 * @date 2018/12/25
 */
public interface ClassesService {
    int deleteByPrimaryKey(Integer id);

    int insert(Classes record);

    int insertSelective(Classes record);

    Classes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Classes record);

    int updateByPrimaryKey(Classes record);

    List<Classes> getList();

    List<Classes> byNameList(String name);

    List<Classes> getCList(Integer majorid);

    List<Classes> getTeaClasslist(Integer headmaster);

    /**
     * 科任教师、班干部名单excel导出
     *
     * @author zwy
     * @date 2019/1/10 14:23
     * @param classes 班级查询条件
     * @param response 响应
     */
    void exportTeacherAndCadre(HttpServletResponse response,Classes classes);

    /**
     *  导出班级签到表
     *
     * @author zwy
     * @date 2019/1/11 11:30
     * @param httpServletResponse 响应
     * @param classesId 班级
     * @param termId 学期id
     */
    void exportCheckInForm(HttpServletResponse httpServletResponse,Integer classesId,Integer termId);
}
