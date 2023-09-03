package com.zwyue.dao;

import com.zwyue.entity.CourseTime;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseTimeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CourseTime record);

    int insertSelective(CourseTime record);

    CourseTime selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CourseTime record);

    int updateByPrimaryKey(CourseTime record);

    /**
     * 批量存入排课前的预设时间
     *
     * @author zwy
     * @date 2018/12/5 16:57
     * @param timeSlot 时间段
     * @return int
     */
    Integer savePresetCourseTime(List<CourseTime> timeSlot);

    /**
     * 根据时间段id查询时间段
     *
     * @author zwy
     * @date 2018/12/6 10:56
     * @param idList 时间段idlist
     * @return list
     */
    List<String> queryTimeSlotBySlotIds(List<String> idList);

    /**
     * 查询全部可用时间段
     *
     * @author zwy
     * @date 2018/12/7 16:50
     * @return list
     */
    List<String> queryActiveTimeSlot();

    /**
     * 查询全部时间预设表
     *
     * @author zwy
     * @date 2018/12/7 17:44
     * @return list
     */
    List<CourseTime> queryAllTimeSlot();
}