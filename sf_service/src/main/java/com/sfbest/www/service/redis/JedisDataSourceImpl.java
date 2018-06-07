package com.sfbest.www.service.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Create by RaiseFree on 10/01/2018 3:45 PM.
 */
@Component("jedisDataSourceImpl")
public class JedisDataSourceImpl implements JedisDataSource{

    @Autowired
    private JedisPool masterJedisPool;
    @Autowired
    private JedisPool slaveJedisPool;

    @Override
    public Jedis getRedisClient(RedisBunch redisBunch) {
        Jedis jedis = null;
        try {
            switch (redisBunch.getName()){
                case "master":
                    jedis = masterJedisPool.getResource();
                    break;
                case "slave":
                    jedis = slaveJedisPool.getResource();
                    break;
            }
            return jedis;
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public void returnResource(Jedis jedis) {
        jedis.close();
    }

    @Override
    public void returnResource(Jedis jedis, boolean broken) {
        jedis.close();
    }

    public enum RedisBunch
    {
        MASTER("master"),
        SLAVE("slave");

        private String name;

        RedisBunch(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}