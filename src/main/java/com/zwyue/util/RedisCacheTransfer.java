package com.zwyue.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Component;

/**
 *
 * 静态注入中间类 Copyright (c) 2016
 *
 * @author  xiad
 * @version : 1.0
 * @date : 2016年3月2日 下午8:02:57
 */
@Component
public class RedisCacheTransfer
{

    @Autowired
    public void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
        RedisCache.setJedisConnectionFactory(jedisConnectionFactory);
    }

}
