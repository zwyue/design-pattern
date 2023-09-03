package com.zwyue.service;

import com.zwyue.entity.Courses;
import com.zwyue.entity.WeekClass;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * copyright    <a href="http://www.qaqavr.com/>中锐</a>
 * <pre>
 *     @author      zwy
 *     @date        2018/12/6 15:31
 *     email        1092478224@qq.com
 *     desc         课程管理接口
 * </pre>
 */
@SuppressWarnings("ALL")
public interface CourseService {

    /**
     * 查询课程列表
     *
     * @author zwy
     * @date 2018/12/6 15:40
     * @param courses 课程
     * @return list
     */
    List<WeekClass> queryCourseList(Courses courses);

    /**
     * 导出课程表 - Excel
     *
     * @author zwy
     * @date 2019/1/7 14:56
     * @param courses 课程查询条件
     * @param response 请求响应
     */
    void exportCourse(HttpServletResponse response, Courses courses);
}
