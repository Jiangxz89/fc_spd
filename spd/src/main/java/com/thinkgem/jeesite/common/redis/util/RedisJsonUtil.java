package com.thinkgem.jeesite.common.redis.util;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 功能: 
 */
public class RedisJsonUtil {
	private static Logger log = LoggerFactory.getLogger(RedisJsonUtil.class);
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	static{
		//遇到未知的属性时忽略
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
		//允许空字符串可以等价于JSON null
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		
		//null或者空字符串不序列化，可以节省redis空间
		objectMapper.setSerializationInclusion(Include.NON_EMPTY);
		//允许单引号
		objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		
	}
	
	/**
	 * 功能: bean序列化JSON字符串
	 * @author wushqd
	 * @version 创建时间: 2015年7月23日 上午11:03:28
	 *
	 * @param bean
	 * @return
	 */
	public static String beanToJson(Object bean) {
		try {
			return objectMapper.writeValueAsString(bean);
		} catch (JsonProcessingException e) {
			log.info("对象序列化成JSON时异常!",e);
			throw new RuntimeException("对象序列化成JSON时异常："+e.getMessage());
		}
	}
	
	/**
	 * 功能: JSON反序列化
	 * @author wushqd
	 * @version 创建时间: 2015年7月23日 上午11:03:47
	 *
	 * @param json
	 * @param t
	 * @return
	 */
	public static <T> T jsonToBean(String json,Class<T> t) {
		try {
			return  objectMapper.readValue(json, t);
		} catch (Exception e) {
			log.info("JSON数据反序列化时异常!",e);
			throw new RuntimeException("JSON数据反序列化时异常："+e.getMessage());
		}
	}
	/**
	 * 功能: json反序列化 List<bean> 类型的数据
	 * @author wushqd
	 * @version 创建时间: 2015年8月31日 下午5:16:55
	 *
	 * @param json
	 * @param t
	 * @param collectionsClass
	 * @return
	 */
	public static <T> List<T> jsonToListBean(String json,Class<T> t) {
		JavaType tp = objectMapper.getTypeFactory().constructParametricType(List.class, t);
		try {
			return objectMapper.readValue(json, tp);
		} catch (Exception e) {
			log.info("对象序列化成JSON时异常!",e);
			throw new RuntimeException("对象序列化成JSON时异常："+e.getMessage());
		} 
	}
	/**
	 * 功能: json反序列化Map<String,bean> 类型的数据
	 * @author wushqd
	 * @version 创建时间: 2015年8月31日 下午5:32:44
	 *
	 * @param json
	 * @param t
	 * @return
	 */
	public static <T> Map<String,T> jsonToMapBean(String json,Class<T> t) {
		JavaType tp = objectMapper.getTypeFactory().constructParametricType(Map.class, String.class,t);
		try {
			return objectMapper.readValue(json, tp);
		} catch (Exception e) {
			log.info("对象序列化成JSON时异常!",e);
			throw new RuntimeException("对象序列化成JSON时异常："+e.getMessage());
		} 
	}
}
