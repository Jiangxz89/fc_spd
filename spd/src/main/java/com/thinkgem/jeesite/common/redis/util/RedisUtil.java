package com.thinkgem.jeesite.common.redis.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.thinkgem.jeesite.common.utils.JacksonJsonUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;



public class RedisUtil {
	
	private static JedisPool jedisPool;

//	static {
//		Properties pro = new Properties();
//		try {
//			pro.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config/project.properties"));
//			JedisPoolConfig config = new JedisPoolConfig();  
//			config.setMaxTotal(Integer.parseInt(pro.getProperty("redis.pool.maxActive"))); 
//	        config.setMaxIdle(Integer.parseInt(pro.getProperty("redis.pool.maxIdle"))); 
//	        config.setMaxWaitMillis(Long.parseLong(pro.getProperty("redis.pool.maxWait"))); 
//	        config.setTestOnBorrow(true); 
//	        jedisPool = new JedisPool( config, pro.getProperty("redis.ip"),Integer.parseInt(pro.getProperty("redis.port")));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
		
       
//	}
	
	public static boolean set(String key,String value){
		boolean flag = false;
		Jedis jedis = null;
			try{
				jedis = jedisPool.getResource();
				jedis.set(key, value);
				flag = true;
			}catch( Exception e){
				e.printStackTrace();
				return false;
			}finally{
				jedisPool.returnResourceObject(jedis);
			}
		return flag;
	}
	
	/**
	 * redis listLpop操作
	 * @author wangjian
	 * @param key 
	 * @return rtnStr
	 */
	public static String listLpop(String key){
		Jedis jedis = null;
		String rtnStr= null;
		try{
			jedis = jedisPool.getResource();
		   rtnStr= jedis.lpop(key);
		}catch( Exception e){
			e.printStackTrace();
		}finally{
			jedisPool.returnResourceObject(jedis);
		}
		return rtnStr;
	}
	
	/**
	 * redis listLpush
	 * @author wangjian
	 * @param key
	 * @param value
	 * @return
	 */
	public static String listLpush(String key,String value){
		Jedis jedis = null;
		String rtnStr= null;
		try{
			jedis = jedisPool.getResource();
		   jedis.lpush(key, value);
		}catch( Exception e){
			e.printStackTrace();
		}finally{
			jedisPool.returnResourceObject(jedis);
		}
		return rtnStr;
	}
	
	public static boolean set(String key,Object value){
		boolean flag = false;
		Jedis jedis = null;
			try{
				String objectJson = JacksonJsonUtil.beanToJson(value);
				jedis = jedisPool.getResource();
				jedis.set(key, objectJson);
				flag = true;
			}catch( Exception e){
				e.printStackTrace();
				return false;
			}finally{
				jedisPool.returnResourceObject(jedis);
			}
		return flag;
	}
	
	public static boolean del( String key ){
		boolean flag = false;
		Jedis jedis = null;
			try{
				jedis = jedisPool.getResource();
				jedis.del(key);
				flag = true;
			}catch( Exception e){
				e.printStackTrace();
				return false;
			}finally{
				jedisPool.returnResourceObject(jedis);
			}
		return flag;
	}
	
	public static boolean del( String[] keys ){
		boolean flag = false;
		Jedis jedis = null;
			try{
				jedis = jedisPool.getResource();
				jedis.del(keys);
				flag = true;
			}catch( Exception e){
				e.printStackTrace();
				return false;
			}finally{
				jedisPool.returnResourceObject(jedis);
			}
		return flag;
	}
	
	public static Object get(String key){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			Object value = jedis.get(key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			jedisPool.returnResourceObject(jedis);
		}
	}
	
	public static <T> Object get(String key,Class<T> clazz){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String value = jedis.get(key);
			return JacksonJsonUtil.jsonToBean(value, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			jedisPool.returnResourceObject(jedis);
		}
	}
	
	public InputStream  getFileIn(){
		return this.getClass().getResourceAsStream("/redis.properties");
	}
}
