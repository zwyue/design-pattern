package com.zwyue.service;

import com.zwyue.entity.Resource;

import java.util.List;

/**
 * ResourceService interface
 *
 * @author zwy
 * @date 2018/11/28 15:46
 */
public interface ResourceService {

    /**
     * 建设资源保存
     *
     * @author zwy
     * @date 2018/11/28 16:17
     * @param resource
     * @return Integer
     */
    Integer saveResouce(Resource resource);

    /**
     * 查询资源建设列表
     *
     * @author zwy
     * @date 2018/11/28 16:35
     * @return list
     */
    List<Resource> queryResourceList();

    /**
     * 根据id查询资源信息
     *
     * @author zwy
     * @date 2018/11/28 17:18
     * @param id
     * @return resource
     */
    Resource queryResourceById(Integer id);

    /**
     * 更新资源信息
     *
     * @author zwy
     * @date 2018/11/28 17:34
     * @param resource
     * @return integer
     */
    Integer updateResource(Resource resource);

    /**
     * 根据id删除资源
     *
     * @author zwy
     * @date 2018/11/28 17:39
     */
    Integer deleteResouece(List<Integer> idList);

    /**
     * 根据标题查询资源建设列表
     * @author yangli
     * @date 2019/1/4
     * @param title
     * @return list
     */
    List<Resource> getTitleList(String title);
}
