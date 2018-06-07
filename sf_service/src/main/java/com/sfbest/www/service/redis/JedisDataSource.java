package com.sfbest.www.service.redis;

import redis.clients.jedis.Jedis;

/**
 * Redis链接资源统一接口
 * Created by raisefree on 2017/7/31.
 */
public interface JedisDataSource {
    /**
     * 获取redis客户端实例
     * @return ShardedJedis
     */
    public Jedis getRedisClient(JedisDataSourceImpl.RedisBunch redisBunch);

    /**
     * 回收
     * @param Jedis ShardedJedis
     */
    public void returnResource(Jedis shardedJedis);

    /**
     * 回收
     * @param Jedis ShardedJedis
     */
    public void returnResource(Jedis shardedJedis, boolean broken);
}
