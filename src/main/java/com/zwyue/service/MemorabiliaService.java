package com.zwyue.service;

import com.zwyue.entity.Memorabilia;

import java.util.List;

/**
 * 大事记
 *
 * @author zwy
 * @date 2018/11/28 14:03
 */
public interface MemorabiliaService {

    int deleteByPrimaryKey(Integer id);

    /**
     * 保存大事记
     *
     * @author zwy
     * @date 2018/11/28 14:03
     */
    Integer saveMemorabilia(Memorabilia memorabilia);

    /**
     * 删除大事记
     *
     * @author zwy
     * @date 2018/11/28 15:28
     */
    Integer deleteMemorabilia(List<Integer> ids);

    /**
     * 更新大事记
     *
     * @author admin
     * @date 2018/11/28 14:04
     */
    Integer updateMemorabilia(Memorabilia memorabilia);

    /**
     * 根据id查询大事记
     *
     * @author zwy
     * @date 2018/11/28 15:28
     */
    Memorabilia queryMemorabiliaById(Integer id );

    /**
     * 查询大事记列表
     *
     * @author zwy
     * @date 2018/11/28 15:28
     */
    List<Memorabilia> queryAllMemorabilia(Memorabilia memorabilia);

    /**
     * 根据标题查询大事记列表
     * @author yangli
     * @date 2019/1/4
     * @param title
     * @return list
     */
    List<Memorabilia> getTitleList(String title);
}
