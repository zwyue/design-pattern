package com.zwyue.timeTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * TermTimeTask class
 * 定时任务
 *
 * @author zwy
 * 2018/11/29 17:13
 */
@Component
public class TermTimeTask {

    private static final Logger logger = LoggerFactory.getLogger(TermTimeTask.class);

//    @Scheduled(cron = "0/2 * * * * ?")//每隔2秒隔行一次
    public void makeTermDisabled(){
        try {

        }catch (Exception e){
            logger.info("............e:{}",e);
        }

        //使过期仍可用学期不可用
//        termService.disableTerm();
        //使到期但未过期学期不可用学期可用
//        termService.enableTerm();
    }
}
