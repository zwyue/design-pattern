package com.zwyue.dao;

import com.zwyue.entity.Memorabilia;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 大事记
 *
 * @author zwy
 * @date 2018/11/28 14:07
 */
@Component
public interface MemorabiliaDao {

    int deleteByPrimaryKey(Integer id);

    /**
     * 新增档案，主要为档案内容,插入返回主键
     *
     * @author zwy
     * @date 2018/11/28 15:30
     */
    Integer saveMemorabilia(Memorabilia memorabilia);

   /**
    * 大事记删除（物理删除）
    *
    * @author zwy
    * @date 2018/11/28 15:30
    */
    Integer deleteMemorabilia(List<Integer> idList);

    /**
     * 档大事记更新
     *
     * @author zwy
     * @date 2018/11/28 15:31
     */
    Integer updateMemorabilia(Memorabilia memorabilia);

    /**
     * 查询大事记列表
     *
     * @author zwy
     * @date 2018/11/28 15:31
     */
    List<Memorabilia> queryMemorabilia(Memorabilia memorabilia);

    /**
     * 根据id查询大事记
     *
     * @author zwy
     * @date 2018/11/28 15:31
     * @param id
     * @return Memorabilia
     */
    Memorabilia queryMemorabiliaById(Integer id);

    /**
      * 根据标题查询大事记列表
      * @author yangli
      * @date 2019/1/4
      * @param title
      * @return list
      */
    List<Memorabilia> getTitleList(String title);
}
