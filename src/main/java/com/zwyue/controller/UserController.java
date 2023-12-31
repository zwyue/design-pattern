package com.zwyue.controller;

import com.zwyue.calculate.Metrics;
import com.zwyue.dto.Result;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    record User(String name,String psw) {}

    private final Metrics metrics = new Metrics();


    Map<String,User> userStore = new HashMap<>() ;

    public UserController() {
        metrics.startRepeatedReport(5, TimeUnit.SECONDS);
    }

    @PostMapping("register")
    public void register(String name,String psw) {

        long startTimestamp = System.currentTimeMillis() ;
        metrics.recordTimestamps("register",startTimestamp);

        User user = new User(name,psw);
        userStore.put(name,user);

        long resTime = System.currentTimeMillis()-startTimestamp;
        metrics.recordResponseTimes("register",resTime);
    }

    @GetMapping("login")
    public Result<User> login(String name,String password) {
        long startTimestamp = System.currentTimeMillis() ;
        metrics.recordTimestamps("login",startTimestamp);

        User user = null ;
        if(userStore.containsKey(name)) {
            String psw = userStore.get(name).psw ;

            if(StringUtils.equals(password,psw)) {
                user = userStore.get(name) ;
            }
        } else {
            register(name,password);
            user = userStore.get(name) ;
        }

        long resTime = System.currentTimeMillis()-startTimestamp;
        metrics.recordResponseTimes("login",resTime);

        if(user==null) {
            return new Result<>(Result.ResCode.FIELD.getCode(),"密码错误",null);
        }

        return new Result<>(Result.ResCode.SUCCEED.getCode(),Result.ResCode.SUCCEED.getMsg(),user);
    }
}
