package com.zwyue.util;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.Objects;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * 使用第三方内存数据库Redis作为二级缓存
 *
 * @version     Copyright (c) 2016
 * @author      xiad
 * @version     1.0
 * @date        2016年3月2日下午8:02:57
 */
public class RedisCache implements Cache  {

    private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);

    private static JedisConnectionFactory jedisConnectionFactory;

    private final String id;

    /**
     * The {@code ReadWriteLock}.
     */
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public RedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        logger.debug("MybatisRedisCache:id=" + id);
        this.id = id;
    }

    @Override
    public void clear() {
        try (RedisConnection connection = jedisConnectionFactory.getConnection()) {
            connection.flushDb();
            connection.flushAll();
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getId()
    {
        return this.id;
    }

    @Override
    public Object getObject(Object key) {
        Object result = null;
        try (RedisConnection connection = jedisConnectionFactory.getConnection()) {
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();

            result = serializer.deserialize(connection.get(Objects.requireNonNull(serializer.serialize(key))));
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ReadWriteLock getReadWriteLock()
    {
        return this.readWriteLock;
    }

    @Override
    public int getSize() {
        int result = 0;
        try (RedisConnection connection = jedisConnectionFactory.getConnection()) {
            result = Integer.parseInt(Objects.requireNonNull(connection.dbSize()).toString());
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void putObject(Object key, Object value)
    {
        try (RedisConnection connection = jedisConnectionFactory.getConnection()) {
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
            connection.set(Objects.requireNonNull(serializer.serialize(key)), Objects.requireNonNull(serializer.serialize(value)));
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object removeObject(Object key)
    {
        RedisConnection connection = null;
        Object result = null;
        try
        {
            connection = jedisConnectionFactory.getConnection();
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
            result =connection.expire(Objects.requireNonNull(serializer.serialize(key)), 0);
        }
        catch (JedisConnectionException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }

    public static void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
        RedisCache.jedisConnectionFactory = jedisConnectionFactory;
    }

}
