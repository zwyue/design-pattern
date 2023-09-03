package com.zwyue.dao;

import com.zwyue.entity.Term;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TermDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Term record);

    int insertSelective(Term record);

    Term selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Term record);

    int updateByPrimaryKey(Term record);

    /**
     * 查询上学期
     *
     * @author zwy
     * @date 2018/11/29 16:49
     * @param term 学期信息
     * @return term
     */
    Term selectLastTerm(Term term);

    /**
     * 使学期不可用
     *
     * @author zwy
     * @date 2018/11/29 17:58
     * @return int
     */
    Integer disableTerm();

    /**
     * 使学期可用
     *
     * @author zwy
     * @date 2018/11/29 18:12
     * @return int
     */
    Integer enableTerm();

    /**
     * 查询学期列表
     *
     * @author zwy
     * @date 2018/11/30 9:28
     * @return list
     */
    List<Term> queryTermList();

    /**
     * 删除学期
     *
     * @author zwy
     * @date 2018/11/30 14:51
     * @param idList 学期id列表
     * @return int
     */
    Integer deleteTermByIdList(List<Integer> idList);
}