package com.gunn.common.shiro.cache;

import com.gunn.common.util.LoggerUtils;
import com.gunn.common.util.SerializeUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * Created by Gunn on 2017/8/12.
 */
public class RedisCache<K,V> implements Cache<K,V>{

	private static final int DB_INDEX = 1;


	private JedisManager jedisManager;

	public JedisManager getJedisManager() {
		return jedisManager;
	}

	public void setJedisManager(JedisManager jedisManager) {
		this.jedisManager = jedisManager;
	}

	@Override
	public V get(K k) throws CacheException {
		byte[] key = SerializeUtil.serialize(k);
		byte[] value = new byte[0];

		try {
			value = jedisManager.getValueByKey(DB_INDEX, key);
		} catch (Exception e) {
			LoggerUtils.error(getClass(),"get value by cache throw exception",e);
		}
		return (V) SerializeUtil.deserialize(value);
	}

	@Override
	public V put(K k, V v) throws CacheException {
		return null;
	}

	@Override
	public V remove(K k) throws CacheException {
		return null;
	}

	@Override
	public void clear() throws CacheException {

	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public Set<K> keys() {
		return null;
	}

	@Override
	public Collection<V> values() {
		return null;
	}
}
