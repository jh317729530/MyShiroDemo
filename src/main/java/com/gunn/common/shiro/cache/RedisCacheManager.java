package com.gunn.common.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * Created by Gunn on 2017/8/12.
 */
public class RedisCacheManager implements CacheManager {

	private JedisManager jedisManager;

	public JedisManager getJedisManager() {
		return jedisManager;
	}

	public void setJedisManager(JedisManager jedisManager) {
		this.jedisManager = jedisManager;
	}

	@Override
	public <K, V> Cache<K, V> getCache(String s) throws CacheException {
		return null;
	}
}
