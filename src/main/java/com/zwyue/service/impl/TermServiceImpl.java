package com.zwyue.service.impl;

import com.zwyue.dao.TermDao;
import com.zwyue.entity.Teacher;
import com.zwyue.entity.Term;
import com.zwyue.enums.StatusEnum;
import com.zwyue.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TermServiceImpl class
 *
 * @author zwy
 * @date 2018/11/29 14:48
 */
@Service
public class TermServiceImpl implements TermService{

    @Autowired
    private TermDao termDao ;

    @Resource(name = "transactionTemplate")
    private TransactionTemplate transactionTemplate ;

    @Autowired
    private RedisTemplate redisTemplate ;

    /**
     * 学期是否可用
     *
     * @author zwy
     * @date 2018/11/29 17:03
     */
    public interface IsEnable {

        /**
         *  可用
         */
        String ENABLE = "0" ;

        /**
         *  不可用
         */
        String DISABLED = "1";
    }

    /**
     * 新建学期，复制学期
     *
     * @author zwy
     * @date 2018/11/29 16:08
     */
    @Override
    public Integer createNewTerm(Term term) {
        //校验日期
        validateDate(term);
        //初始化学期为不可用状态
        term.setIsenable(IsEnable.DISABLED);

//        int isCreate = termDao.insert(term) ;
//
//        if(isCreate>0){
//
//            Term thisTerm = (Term) redisTemplate.opsForValue().get("thisTerm");
//
//            if(thisTerm==null || compareDate(LocalDate.now().toString(),thisTerm.getEndtime())){
//                redisTemplate.opsForValue().set("thisTerm",term);
//            }
//        }

        return termDao.insert(term) ;
    }

    @Override
    public Integer disableTerm() {
        return termDao.disableTerm();
    }

    @Override
    public Integer enableTerm() {
        return termDao.enableTerm();
    }

    @Override
    public List<Term> queryTermList() {
        List<Term> terms = termDao.queryTermList();
        terms.forEach(term -> term.setIsEnableTxt(StatusEnum.returnEnumByCode(term.getIsenable()).msg));
        return terms ;
    }

    @Override
    public Map updateTermStatus() {
        Map map = transactionTemplate.execute(new TransactionCallback<Map>() {
            @Override
            public Map doInTransaction(TransactionStatus status) {
                Integer disableItems = termDao.disableTerm();
                Integer enableItems = termDao.enableTerm();
                Map map = new HashMap(134);
                map.put("disableItems",disableItems);
                map.put("enableItems",enableItems);
                return map;
            }
        });
        return map;
    }

    @Override
    public Integer updateTerm(Term term) {
        //校验日期
        validateDate(term);
        return termDao.updateByPrimaryKey(term);
    }

    @Override
    public Integer deleteTerm(List<Integer> idList) {
//        Term thisTerm = (Term) redisTemplate.opsForValue().get("thisTerm");
//        if(idList.contains(thisTerm.getId())){
//            thisTerm = termDao.queryThisTerm();
//            if(thisTerm==null){
//                thisTerm = termDao.queryThisTermByOrder();
//            }
//            redisTemplate.opsForValue().set("thisTerm",thisTerm);
//        }
        return termDao.deleteTermByIdList(idList);
    }

    @Override
    public Integer copyTerm(Integer id, Teacher teacher) {
        Term oldTerm = termDao.selectByPrimaryKey(id);
        Assert.notNull(oldTerm);
        oldTerm.setIsenable(IsEnable.DISABLED);
        oldTerm.setUsername(teacher.getTname());
        oldTerm.setUserid(teacher.getId());
        oldTerm.setStarttime(plusYear(oldTerm.getStarttime(),1));
        oldTerm.setEndtime(plusYear(oldTerm.getEndtime(),1));
        return createNewTerm(oldTerm);
    }

    /**
     * 日期校验
     *
     * @author zwy
     * @date 2018/11/30 14:29
     * @param term
     */
    private void validateDate(Term term){
        String startTime = term.getStarttime();
        String endTime = term.getEndtime() ;

        //时间判断
        Assert.notNull(startTime,"开始日期不能为空");
        Assert.notNull(endTime,"结束日期不能为空");

        Assert.isTrue(compareDate(endTime,startTime),"结束日期应大于开始日期");

        //查询上学期
        Term lastTerm = termDao.selectLastTerm(term);

        if (lastTerm==null){
            //如果当前没有学期，则新建学期应大于当前日期
            Assert.isTrue(compareDate(startTime, LocalDate.now().toString()),"开始日期应大于当前日期");
        }else {
            Assert.isTrue(compareDate(startTime,lastTerm.getEndtime()),"开始日期应大于上学期结束时间");
        }
    }

    /**
     * 在当前日期的基础上添加年份
     *
     * @author zwy
     * @date 2018/11/30 18:33
     */
    private String plusYear(String date,int year){
        LocalDate localDate = LocalDate.parse(date);
        date = localDate.plusYears(year).toString();
        return date ;
    }

    /**
     * 日期比较（形如 2018-11-29 的日期）
     *
     * @author zwy
     * @date 2018/11/29 16:23
     */
    private boolean compareDate(String date1,String date2) {
        LocalDate localDate1 = LocalDate.parse(date1);
        LocalDate localDate2 = LocalDate.parse(date2);
        return localDate1.compareTo(localDate2) > 0  ;
    }
}
