package com.zwyue.dao.initializingDao;

import com.zwyue.dao.CategoryDao;
import com.zwyue.dao.ProfessionDao;
import com.zwyue.dao.StudentDao;
import com.zwyue.dao.TermDao;
import com.zwyue.entity.Category;
import com.zwyue.entity.Profession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static com.zwyue.constant.SysConstant.Punctuation.COMMA;

/**
 * copyright    <a href="http://www.qaqavr.com/">中锐</a>
 * <pre>
 *     @author      zwy
 *     @date        2018/12/22 12:19
 *     email        1092478224@qq.com
 *     desc
 * </pre>
 */
@Component
public class RedisInitializing implements InitializingBean {

    private final static Logger logger = LoggerFactory.getLogger(RedisInitializing.class);
//
//    @Autowired
//    private JedisClient jedisClient;
//
//
//    @Value("${REDIS_ITEM_PRE}")
//    private String REDIS_ITEM_PRE;
//
//    @Value("${ITEM_CACHE_EXPIRE}")
//    private Integer ITEM_CACHE_EXPIRE;


    @Autowired
    private RedisTemplate redisTemplate ;

    @Autowired
    private StudentDao studentDao ;

    @Autowired
    private CategoryDao categoryDao ;

    @Autowired
    private ProfessionDao professionDao ;

    @Autowired
    private TermDao termDao ;

    @Override
    public void afterPropertiesSet() throws Exception {

        logger.info("..........初始化存储学生学号信息.........");

        //查询学生学号到redis
        List<String> stuNo = studentDao.queryStuNo();

        //清空redis学号缓存
        if(stuNo.size()!=0){
            Set<String> keys = redisTemplate.keys(stuNo.get(0).substring(0,1) + "*");
            redisTemplate.delete(keys);
        }

        //存入缓存
        stuNo.forEach(no->{
            List<String> keys = Arrays.asList(no.split(COMMA));
            keys.forEach(key->{
                key = key.substring(0,10) ;
                List classifyNos = (List) redisTemplate.opsForValue().get(key);
                classifyNos = classifyNos==null ? new ArrayList() : classifyNos ;
                classifyNos.add(no.substring(10,12));
                List sortList = (List) classifyNos.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
                redisTemplate.opsForValue().set(key,sortList);
            });
        });

        //查询类别存入到redis
        List<Category> categories = categoryDao.getcatelist();
        redisTemplate.opsForValue().set("categories",categories);

        //查询专业存入到redis
        List<Profession> professions = professionDao.getprolist(null);
        redisTemplate.opsForValue().set("professions",professions);

        //初始化存储当前学期id
//        Term thisTerm = termDao.queryThisTerm();
//        if(thisTerm==null){
//            thisTerm = termDao.queryThisTermByOrder();
//        }
//        redisTemplate.opsForValue().set("thisTerm",thisTerm);
    }
}
