package com.zrtjoa.service.impl;

import com.zrtjoa.dao.TeacherDao;
import com.zrtjoa.entity.Teacher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath*:spring-mvc.xml"
//                                  ,"classpath*:spring-mybatis.xml"
//                                  ,"classpath*:spring-task.xml"
//                                  ,"classpath*:jdbc.properties"
//                                  ,"classpath*:redis.properties"
//                                    })
public class TeacherServiceImplTest {

    @Autowired
    private RedisTemplate redisTemplate ;

//    private ApplicationContext applicationContext;




    @Test
    public void queryAllTeacher() {
        redisTemplate.opsForValue().set("aaa","aaa");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void queryTeacherByRoleId() {
    }

//    @Autowired(required = false)
//    private ServletContext servletContext;
//
//    @PostConstruct
//    public void startup() {
//        if (Optional.fromNullable(servletContext).isPresent()) {
//            servletContext.setAttribute(PAGE_SIZE, "ssssss");
//        }
//    }
}