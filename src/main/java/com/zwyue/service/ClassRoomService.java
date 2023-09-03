package com.zwyue.service;

import com.zwyue.entity.Classrecord;
import com.zwyue.entity.Classroom;
import com.zwyue.entity.Classtype;

import java.util.List;

/**
 * copyright    <a href="http://www.qaqavr.com/>中锐</a>
 * <pre>
 *     @author zwy
 *     @date 2018/12/3 15:55
 *     email       1092478224@qq.com
 *     desc
 * </pre>
 */
public interface ClassRoomService {

    /**
     * 添加新课程
     *
     * @author zwy
     * @date 2018/12/3 16:31
     * @param classroom 教师信息
     * @return int
     */
    Integer  addNewClassRoom(Classroom classroom);

    /**
     * 添加教室分类
     *
     * @author zwy
     * @date 2018/12/3 18:46
     * @param classtype 教室分类信息
     * @return int
     */
    Integer addNewCategory(Classtype classtype);

    /**
     * 查询教室列表
     *
     * @author zwy
     * @date 2018/12/5 10:54
     * @return classroom list
     */
    List<Classroom> queryClassroomList();

    /**
     * 查询教师类别列表
     *
     * @author zwy
     * @date 2018/12/5 11:07
     * @return list
     */
    List<Classtype> queryClassTypeList();

    /**
     * 更新教室类别
     *
     * @author zwy
     * @date 2018/12/5 14:23
     * @return int
     * @param classtype 教室类别
     */
    Integer updateClassRoomType(Classtype classtype);

    /**
     * 更新教室
     *
     * @author zwy
     * @date 2018/12/5 14:28
     * @param classroom 教室信息
     * @return int
     */
    Integer updateClassRoom(Classroom classroom);

    /**
     * 教室使用历史
     *
     * @author zwy
     * @date 2018/12/5 14:42
     * @param classrecord 使用历史查询条件
     * @return list
     */
    List<Classrecord> clsRmUsageHistory(Classrecord classrecord);
}
