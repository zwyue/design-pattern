package com.zwyue.dao.initializingDao;

import com.zwyue.dao.StudentDao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 
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

    @Autowired
    private RedisTemplate redisTemplate ;

    @Autowired
    private StudentDao studentDao ;

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
            List<String> keys = Arrays.asList(no.split(","));
            keys.forEach(key->{
                key = key.substring(0,10) ;
                List classifyNos = (List) redisTemplate.opsForValue().get(key);
                classifyNos = classifyNos==null ? new ArrayList() : classifyNos ;
                classifyNos.add(no.substring(10,12));
                List sortList = (List) classifyNos.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
                redisTemplate.opsForValue().set(key,sortList);
            });
        });
    }
}
