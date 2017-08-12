package com.gunn.common.shiro.cache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class JedisManager {

    private JedisPool jedisPool;

    public Jedis getJedis() {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return jedis;
    }

    /**
     * 释放jedis资源
     * @param jedis
     * @param isBroken
     */
    public void returnResource(Jedis jedis, boolean isBroken) {
        if (jedis == null) {
            return;
        }
        jedis.close();
    }

    public byte[] getValueByKey(int dbIndex,byte[] key) throws Exception {
        Jedis jedis = null;
        byte[] result = null;
        boolean isBroken = false;
        try {
            jedis = getJedis();
            jedis.select(dbIndex);
            result = jedis.get(key);
        } catch (Exception e) {
            isBroken = true;
            throw e;
        }finally {
            returnResource(jedis, isBroken);
        }
        return result;
    }

    public void deleteByKey(int dbIndex,byte[] key) throws Exception {
        Jedis jedis = null;
        boolean isBorken = false;
        try {
            jedis = getJedis();
            jedis.select(dbIndex);
            jedis.del(key);
        } catch (Exception e) {
            isBorken = true;
            throw e;
        }finally {
            returnResource(jedis, isBorken);
        }
    }

    public void saveValueByKey(int dbIndex, byte[] key, byte[] value, int expireTime) throws Exception {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = getJedis();
            jedis.select(dbIndex);
            jedis.set(key, value);
            if (expireTime > 0) {
                jedis.expire(key, expireTime);
            }
        } catch (Exception e) {
            isBroken = true;
            throw e;
        }finally {
            returnResource(jedis,isBroken);
        }
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
}
