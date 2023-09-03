package com.zwyue.dao;

import com.zwyue.entity.StudentEnter;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sys
 * @date 2018/12/27 9:01
 */
@Repository
public interface StudentEnterDao {

    /**
     * 根据主键删除报名信息
     *
     * @author zwy
     * @date 2018/12/27 8:56
     * @param id 主键
     * @return int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入报名信息
     *
     * @author zwy
     * @date 2018/12/27 8:57
     * @param record 记录
     * @return int
     */
    int insert(StudentEnter record);

    /**
     * 根据条件插入
     *
     * @author zwy
     * @date 2018/12/27 8:58
     * @param record 记录
     * @return int
     */
    int insertSelective(StudentEnter record);

    /**
     * 根据主键查询报名信息
     *
     * @author zwy
     * @date 2018/12/27 8:58
     * @param id 主键
     * @return studentEnter
     */
    StudentEnter selectByPrimaryKey(Integer id);

    /**
     * 有条件更新
     *
     * @author zwy
     * @date 2018/12/27 8:59
     * @param record 记录
     * @return int
     */
    int updateByPrimaryKeySelective(StudentEnter record);

    /**
     * 更新报名信息
     *
     * @author zwy
     * @date 2018/12/27 9:00
     * @param record 记录
     * @return int
     */
    int updateByPrimaryKey(StudentEnter record);

    /**
     * 根据学生id删除学生信息
     *
     * @author zwy
     * @date 2018/12/27 8:55
     * @param stuId 学生id
     * @return int
     */
    Integer deleteByStuId(Integer stuId);

    /**
     * 根据报名信息id查询报名的classid
     *
     * @author zwy
     * @date 2018/12/27 11:21
     * @param id 报名信息id
     * @return id
     */
    StudentEnter queryEnterByEnterId(Integer id);
}