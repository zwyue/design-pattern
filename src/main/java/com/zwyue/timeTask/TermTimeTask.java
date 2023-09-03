package com.zwyue.timeTask;

import com.zwyue.service.TermService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * TermTimeTask class
 * 定时任务
 *
 * @author zwy
 * @date 2018/11/29 17:13
 */
@Component
public class TermTimeTask {

    private static final Logger logger = LoggerFactory.getLogger(TermTimeTask.class);

    @Autowired
    private TermService termService ;

//    @Scheduled(cron = "0/2 * * * * ?")//每隔2秒隔行一次
    public void makeTermDisabled(){
        try {

            termService.updateTermStatus() ;
        }catch (Exception e){
            logger.info("............e:{}",e);
        }

        //使过期仍可用学期不可用
//        termService.disableTerm();
        //使到期但未过期学期不可用学期可用
//        termService.enableTerm();
    }
}
