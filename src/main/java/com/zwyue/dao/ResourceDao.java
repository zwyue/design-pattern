package com.zwyue.dao;

import com.zwyue.entity.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);

    /**
     * 查询资源建设列表
     *
     * @author zwy
     * @date 2018/11/28 16:36
     * @return list
     */
    List<Resource> queryResourceList();

    /**
     * 可批量删除资源建设
     *
     * @author zwy
     * @date 2018/11/28 17:41
     * @param idList
     * @return int
     */
    Integer deleteResourceByIds(List<Integer> idList);

    /**
     * 根据标题查询资源建设列表
     * @author yangli
     * @date 2019/1/4
     * @param title
     * @return list
     */
    List<Resource> getTitleList(String title);
}