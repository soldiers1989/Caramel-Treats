package com.otc.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisTemplet {

	@Autowired
	private RedisTemplate redisTemplate;

	@Value("${REDIS_HERDER}")
	private String REDIS_HERDER;
	/**
	 * redis string类型 set 方法
	 * @param key
	 * @param obj
	 */
	public void setStr(String key,Object obj){
		redisTemplate.opsForValue().set(REDIS_HERDER+key, obj);
	}
	/**
	 * redis string类型 get 方法
	 * @param key
	 * @return
	 */
	public Object getStr(String key){
		return redisTemplate.opsForValue().get(REDIS_HERDER+key);
	}
	/**
	 * redis string类型 设置过期时间   单位秒
	 * @param key
	 * @param obj
	 * @param time
	 */
	public void setExpire(String key,Object obj,long time){
		redisTemplate.opsForValue().set(REDIS_HERDER+key, obj, time,TimeUnit.SECONDS);
	} 
	
	public void setHashSet(String baseKey,String key,Object obj){
		redisTemplate.opsForHash().put(baseKey, REDIS_HERDER+key, obj);
	}
	public Object getHashget(String baseKey,String key){
		return redisTemplate.opsForHash().get(baseKey, REDIS_HERDER+key);
	}
	public Object hashEntries(String baseKey){
		return redisTemplate.opsForHash().entries(baseKey);
	}
	public void delete(String key){
		redisTemplate.delete(REDIS_HERDER+key);
	}
}
