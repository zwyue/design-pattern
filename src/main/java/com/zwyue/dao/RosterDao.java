package com.zwyue.dao;

import com.zwyue.entity.Roster;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RosterDao {

    int deleteByPrimaryKey(Integer id);

    int insert(Roster record);

    int insertSelective(Roster record);

    Roster selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Roster record);

    int updateByPrimaryKey(Roster record);

    List<Roster> getList(Integer classid);

    Roster selectByIsleader(String isleader,Integer classid);

    /**
     * 查询学生是否已存在
     *
     * @author zwy
     * @date 2018/12/24 13:50
     * @param map 包含学生id和班级id
     * @return roster
     */
    List<Roster> queryRosterInfoByClsIdAndStuId(Map map);

    /**
     * 根据学生id 删除
     *
     * @author zwy
     * @date 2018/12/27 9:03
     * @param stuId 学生id
     * @return int
     */
    Integer delByStuId(Integer stuId);

    /**
     * 根据班级id和学生id删除花名册学生信息
     *
     * @author zwy
     * @date 2018/12/27 13:49
     * @param stuNos 学生学号
     * @return int
     */
    int deleteByClassIdAndStuId(List<String> stuNos);

    /**
     * 根据学号删除花名册学生信息
     *
     * @author zwy
     * @date 2018/12/28 15:49
     * @param number 学号
     * @return int
     */
    Integer delByStuNo(String number);

    /**
     * 根据班级id导出班级花名册
     *
     * @author zwy
     * @date 2019/1/11 11:39
     * @param map 包括班级id和学期id
     * @return roster 花名册名单
     */
    List<Roster> queryRosterByClassIdAndTerm(Map map);
}