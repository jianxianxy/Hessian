package com.sfbest.www.service.redis;

import redis.clients.jedis.ShardedJedis;

/**
 * Redis链接资源统一接口
 * Created by raisefree on 2017/7/31.
 */
public interface JedisDataSource {
    /**
     * 获取redis客户端实例
     * @return ShardedJedis
     */
    public ShardedJedis getRedisClient();

    /**
     * 回收
     * @param shardedJedis ShardedJedis
     */
    public void returnResource(ShardedJedis shardedJedis);

    /**
     * 回收
     * @param shardedJedis ShardedJedis
     */
    public void returnResource(ShardedJedis shardedJedis, boolean broken);
}
