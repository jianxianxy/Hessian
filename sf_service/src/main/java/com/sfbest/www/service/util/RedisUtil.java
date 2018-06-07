package com.sfbest.www.service.util;

import com.sfbest.www.service.redis.JedisDataSourceImpl;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.Set;

/**
 * Create by RaiseFree on 10/01/2018 5:53 PM.
 */
@Repository("redisUtil")
public class RedisUtil {

    @Resource(name = "jedisDataSourceImpl")
    private JedisDataSourceImpl jedisDataSource;

    public void disconnect() {
        Jedis jedis = jedisDataSource.getRedisClient(JedisDataSourceImpl.RedisBunch.MASTER);
        jedis.disconnect();
        jedis = jedisDataSource.getRedisClient(JedisDataSourceImpl.RedisBunch.SLAVE);
        jedis.disconnect();
    }

    public String set(String key, String value){
        String result = null;
        Jedis jedis = jedisDataSource.getRedisClient(JedisDataSourceImpl.RedisBunch.MASTER);
        if (jedis == null){
            return null;
        }

        boolean broken = false;
        try {
            result = jedis.set(key, value);
        } catch (Exception e) {
            LogUtil.getLogger(LogUtil.LoggerName.REDIS).error(e.getMessage());
            broken = true;
        } finally {
            jedisDataSource.returnResource(jedis, broken);
        }
        return result;
    }


    public String set(String key, String value, int seconds) {
        String result = null;
        Jedis jedis = jedisDataSource.getRedisClient(JedisDataSourceImpl.RedisBunch.MASTER);
        if (jedis == null){
            return null;
        }

        boolean broken = false;
        try {
            result = jedis.set(key, value);
            jedis.expire(key, seconds);
        } catch (Exception e) {
            LogUtil.getLogger(LogUtil.LoggerName.REDIS).error(e.getMessage());
            broken = true;
        } finally {
            jedisDataSource.returnResource(jedis, broken);
        }
        return result;
    }

    public long expire(String key, int expireSeconds) {
        long result = 0;
        Jedis jedis = jedisDataSource.getRedisClient(JedisDataSourceImpl.RedisBunch.MASTER);
        if (jedis == null) {
            return 0;
        }
        boolean broken = false;
        try {
            result = jedis.expire(key, expireSeconds);
        } catch (Exception e) {
            LogUtil.getLogger(LogUtil.LoggerName.REDIS).error(e.getMessage());
            broken = true;
        } finally {
            jedisDataSource.returnResource(jedis, broken);
        }

        return result;
    }

    public String get(String key){
        String result = null;
        Jedis jedis = jedisDataSource.getRedisClient(JedisDataSourceImpl.RedisBunch.SLAVE);
        if (jedis == null){
            return null;
        }
        boolean broken = false;

        try {
            result = jedis.get(key);
        } catch (Exception e) {
            LogUtil.getLogger(LogUtil.LoggerName.REDIS).error(e.getMessage());
            broken = true;
        } finally {
            jedisDataSource.returnResource(jedis, broken);
        }
        return result;
    }

    public Set<String> keys(String key) {
        Set<String> result = null;
        Jedis jedis = jedisDataSource.getRedisClient(JedisDataSourceImpl.RedisBunch.SLAVE);
        if (jedis == null){
            return null;
        }
        boolean broken = false;

        try {
             result = jedis.keys(key);
        } catch (Exception e) {
            LogUtil.getLogger(LogUtil.LoggerName.REDIS).error(e.getMessage());
            broken = true;
        } finally {
            jedisDataSource.returnResource(jedis, broken);
        }
        return result;
    }

    public long del(String... keys) {
        Jedis jedis = jedisDataSource.getRedisClient(JedisDataSourceImpl.RedisBunch.MASTER);
        if (jedis == null) {
            return 0;
        }
        long result = 0;
        boolean broken = false;

        try {
            if (keys.length > 0)
                return 0;
            result = jedis.del(keys);
            return result;
        } catch (Exception e ) {
            LogUtil.getLogger(LogUtil.LoggerName.REDIS).error(e.getMessage());
            broken = true;
        } finally {
            jedisDataSource.returnResource(jedis, broken);
        }
        return result;
    }

    public long del(String key) {
        Jedis jedis = jedisDataSource.getRedisClient(JedisDataSourceImpl.RedisBunch.MASTER);
        if (jedis == null) {
            return 0;
        }
        long result = 0;
        boolean broken = false;

        try {
            result = jedis.del(key);
        } catch (Exception e ) {
            LogUtil.getLogger(LogUtil.LoggerName.REDIS).error(e.getMessage());
            broken = true;
        } finally {
            jedisDataSource.returnResource(jedis, broken);
        }
        return result;
    }
}
