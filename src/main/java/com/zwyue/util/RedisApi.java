package com.zwyue.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author luzhewu
 */
public class RedisApi {

    /**
     * redis连接池对象
     *
     * @date 2018/12/21 13:39
     */
    private JedisPool jedisPool;

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * set key and value tp redis
     * @param key 键
     * @param value 值
     * @return 布尔值
     */
    public boolean set(String key, String value) {
        Jedis jedis = null;
        try {
            // 获取jedis对象
            jedis = jedisPool.getResource();
            jedis.set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedisPool, jedis);
        }
        return false;
    }

    /**
     * 判断某个key是否存在
     * @param key 键
     * @return 布尔值
     */
    public boolean exist(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedisPool, jedis);
        }
        return false;
    }

    /**
     * 通过key获取value
     * @param key 键
     * @return 缓存值
     */
    public String get(String key) {
        String value = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedisPool, jedis);
        }
        return value;
    }

    /**
     * 返还到连接池
     * @param jedisPool 连接池
     * @param jedis Java redis 连接
     */
    private static void returnResource(JedisPool jedisPool, Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }
}