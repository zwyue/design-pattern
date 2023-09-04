package com.zwyue.util;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

/**
 * 
 * <pre>
 *     @author      zwy
 *     @date        2018/12/21 17:01
 *     email        1092478224@qq.com
 *     desc         redis结合mybatis做二级缓存 <a href="http://www.pianshen.com/article/105752835/">参考</a>
 * </pre>
 */
@Component
public class RedisMapperCache implements Cache {

    private static final Logger logger = LoggerFactory.getLogger(RedisMapperCache.class);

    @Autowired
    private static JedisConnectionFactory jedisConnectionFactory;

    private String id;

    private static final ReadWriteLock READ_WRITE_LOCK = new ReentrantReadWriteLock();

    public RedisMapperCache(String id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        this.id = id;
    }

    public RedisMapperCache() {

    }

    @Autowired
    public void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
        RedisMapperCache.jedisConnectionFactory = jedisConnectionFactory;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        try (RedisConnection connection = jedisConnectionFactory.getConnection()) {
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
            connection.set(Objects.requireNonNull(serializer.serialize(key)), Objects.requireNonNull(serializer.serialize(value)));
            connection.lPush(Objects.requireNonNull(serializer.serialize(id)), serializer.serialize(key));
            logger.info("写入缓存：" + key + "," + value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getObject(Object key) {
        Object res = null;
        try (RedisConnection connection = jedisConnectionFactory.getConnection()) {
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
            res = serializer.deserialize(connection.get(Objects.requireNonNull(serializer.serialize(key))));
            if (res != null) {
                logger.info("获取缓存数据：" + res.toString());
            } else {
                logger.info("当前没有缓存:" + key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public Object removeObject(Object key) {
        Object res = null;
        try (RedisConnection connection = jedisConnectionFactory.getConnection()) {
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
            res = connection.expire(Objects.requireNonNull(serializer.serialize(key)), 0);
            connection.lRem(Objects.requireNonNull(serializer.serialize(id)), 0, Objects.requireNonNull(serializer.serialize(key)));
            logger.info("删除缓存：" + key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public void clear() {
        try (RedisConnection connection = jedisConnectionFactory.getConnection()) {
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();

            Long length = connection.lLen(Objects.requireNonNull(serializer.serialize(id)));
            if (length==null || 0 == length) {
                return;
            }
            List<byte[]> keys = connection.lRange(Objects.requireNonNull(serializer.serialize(id)), 0, length - 1);
            assert keys != null;
            for (byte[] key : keys) {
                connection.expireAt(key, 0);
                System.out.println("删除缓存:" + Objects.requireNonNull(serializer.deserialize(key)).toString());
            }
            connection.expireAt(Objects.requireNonNull(serializer.serialize(id)), 0);
            keys.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getSize() {
        int result = 0;
        try (RedisConnection connection = jedisConnectionFactory.getConnection()) {
            result = Integer.parseInt(Objects.requireNonNull(connection.dbSize()).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return READ_WRITE_LOCK;
    }
}
