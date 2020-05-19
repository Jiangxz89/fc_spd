package com.thinkgem.jeesite.common.redis.service;

//import static com.util.UPlatStringUtils.GetUUID;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.thinkgem.jeesite.common.redis.util.RedisJsonUtil;
import com.thinkgem.jeesite.common.utils.StringUtils;

@Service("platformCatchManager")
public class RedisTemplateService<RedisBaseModel> {
	public RedisTemplateService() {
		super();
	}
	
	private Class<RedisBaseModel> persistentClass;
	@Autowired
    private RedisTemplate<String, String> redisTemplate;
	
	public RedisTemplateService(final Class<RedisBaseModel> persistentClass) {
        this.persistentClass = persistentClass;
    }

    /**
     * Constructor that takes in a class and sessionFactory for easy creation of DAO.
     *
     * @param persistentClass the class type you'd like to persist
     * @param sessionFactory  the pre-configured Hibernate SessionFactory
     */
    public RedisTemplateService(final Class<RedisBaseModel> persistentClass, RedisTemplate<String,String> redisTemplate) {
        this.persistentClass = persistentClass;
        this.redisTemplate = redisTemplate;
    }

	public void save(final RedisBaseModel persistentObject) {
		
	    redisTemplate.execute(new RedisCallback<Object>() {  
	        public Object doInRedis(RedisConnection connection)  
	                throws DataAccessException { 
	        	byte[] strkey =  redisTemplate.getStringSerializer().serialize("");
	        	Field fieldid = null;
	        	try {
					fieldid = persistentObject.getClass().getSuperclass().getDeclaredField("id");
				} catch (NoSuchFieldException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SecurityException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
  					fieldid.setAccessible(true);
					try {
						connection.set( strkey, redisTemplate.getStringSerializer().serialize(fieldid.get(persistentObject).toString()));
						System.out.println(redisTemplate.getStringSerializer().serialize(fieldid.get(persistentObject).toString()));
					} catch (SerializationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalArgumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					fieldid.setAccessible(false);
  				
	        	Field[] fields=persistentObject.getClass().getDeclaredFields();
		   		 for (Field field : fields) {
		   				field.setAccessible(true);
		   				 String fieldvalue = null;
						try {
							fieldvalue = field.get(persistentObject).toString();
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		   				field.setAccessible(false);
//		   				 System.out.println(field.getName()+":"+fieldvalue);
		   		}
	            return null;  
	        }  
	    });  
	} 
	
	
	public RedisBaseModel read(final String id) {
		System.out.println("-----------------"+id+"----------------------------");
	    return redisTemplate.execute(new RedisCallback<RedisBaseModel>() {
	        public RedisBaseModel doInRedis(RedisConnection connection)  
	                throws DataAccessException {  
	        	RedisBaseModel obj = null;
	        	byte[] strkey =  redisTemplate.getStringSerializer().serialize(id);
	            if (connection.exists(strkey)) {  
	            	try {
		        		obj = persistentClass.newInstance();
						Field[] fields=persistentClass.getDeclaredFields();
						for (Field field : fields) {
							field.setAccessible(true); 
							field.set(obj, String.valueOf(new String(connection.hMGet(strkey, redisTemplate.getStringSerializer().serialize(field.getName())).get(0),"UTF-8")));
							field.setAccessible(false); 
						}
					} catch (SerializationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	return obj;
	            }  
	            return null;  
	        }  
	    });  
	} 
	
	
	public void delete(final String id) {  
	    redisTemplate.execute(new RedisCallback<Object>() {  
	        public Object doInRedis(RedisConnection connection) {  
	            connection.del(redisTemplate.getStringSerializer().serialize(id));  
	            return null;  
	        }  
	    });  
	}  
	
	/**
	 * 功能: 根据给定key删除对应内容
	 * 
	 *
	 * @param key
	 */
	public void deleteByKey(String key) {
		redisTemplate.delete(key);
	}
	/**
	 * 功能:根据给定key删除对应内容
	 * 
	 *
	 * @param keys
	 */
	public void deleteByKey(Collection<String> keys) {
		redisTemplate.delete(keys);
	}
	/**
	 * 功能: 查询符合pattern格式的所有key
	 * 
	 *
	 * @param pattern
	 * @return
	 */
	public Set<String> keys(String pattern) {
		return redisTemplate.keys(pattern);
	}
	
	/**
	 * 功能: 设置key 有效时间
	 * 
	 * @version 创建时间: 2015年8月21日 下午1:41:15
	 *
	 * @param key key值
	 * @param timeout 有效时间
	 * @param unit 单位，默认秒 s
	 */
	public boolean expire(String key, long timeout, TimeUnit unit) {
		unit = unit==null?TimeUnit.SECONDS:unit;
		return redisTemplate.expire(key, timeout, unit);
	}
	/**
	 * 功能: 设置key固定的失效时间
	 * 
	 *
	 * @param key key值
	 * @param date 失效日期
	 */
	public boolean expireAt(String key, Date date) {
		return redisTemplate.expireAt(key, date);
	}
	
	/* string begin */
	/**
	 * 功能: String - set
	 * 
	 *
	 * @param key
	 * @param value
	 */
	public void strOpsSet(String key,String value) {
		redisTemplate.opsForValue().set(key, value);
	}
	/**
	 * 功能: String - set
	 * 
	 * @param key
	 * @param value
	 */
	public void strOpsSet(String key,Object value) {
		redisTemplate.opsForValue().set(key, RedisJsonUtil.beanToJson(value));
	}
	/**
	 * 功能: String - get
	 * 
	 * @param key
	 */
	public String strOpsGet(String key) {
		return redisTemplate.opsForValue().get(key);
	}
	/**
	 * 功能: String - multi get
	 * 
	 * @param key
	 */
	public List<String> strOpsMGet(Collection<String> keys) {
		return redisTemplate.opsForValue().multiGet(keys);
	}
	/**
	 * 功能: String - get 返回bean数据
	 * 
	 *
	 * @param key
	 * @param clazz
	 */
	public <E> E strOpsGet(String key,Class<E> clazz) {
		String val = redisTemplate.opsForValue().get(key);
		if(StringUtils.isEmpty(val)) {
			return RedisJsonUtil.jsonToBean(val, clazz);
		} else {
			return null;
		}
	}
	/**
	 * 功能: String - get 转化成list数据
	 * 
	 * @version 创建时间: 2015年8月31日 下午5:27:42
	 *
	 * @param key
	 * @param clazz
	 * @return
	 */
	public <E> List<E> strOpsGetList(String key,Class<E> clazz) {
		String val = redisTemplate.opsForValue().get(key);
		if(StringUtils.isEmpty(val)) {
			return RedisJsonUtil.jsonToListBean(val, clazz);
		} else {
			return null;
		}
	}
	/**
	 * 功能: 
	 * 
	 *
	 * @param key
	 * @param num
	 */
	public Long strOpsIncr(String key,long num) {
		return redisTemplate.opsForValue().increment(key, num);
	}
	
	/* string end */
	/* list begin */
	/**
	 * 功能: List - pop
	 * 
	 *
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <E> E listOpsPop(String key,Class<E> clazz) {
		String result = redisTemplate.opsForList().leftPop(key);
		E target = null;
		if(clazz.equals(String.class)) {
			target = (E)result;
		} else {
			target = RedisJsonUtil.jsonToBean(result, clazz);
		}
		return target;
	}
	/**
	 * 功能: list - getAll
	 * 
	 * @param key
	 * @param clazz 指定class进行反序列化
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <E> List<E> listOpsGetAll(String key,Class<E> clazz) {
		List<E> list = new ArrayList<E>(0);
		List<String> tmp = redisTemplate.opsForList().range(key, 0, redisTemplate.opsForList().size(key));
		/*//此处取出的tmp 是全部数据序列化成一个数组类型的json串，而不是对每条数据单独序列化
		if(!CollectionUtils.isEmpty(tmp)) {
			list = RedisJsonUtil.jsonToBean(tmp.get(0), list.getClass());
		}*/
		if(clazz.equals(String.class)) {
			for(String json : tmp) {
				list.add((E)json);
			}
		} else {
			for(String json : tmp) {
				list.add(RedisJsonUtil.jsonToBean(json, clazz));
			}
		}
		
		return list;
	}
	/**
	 * 功能: list - range，获取指定区间的数据
	 * 
	 *
	 * @param key
	 * @param from
	 * @param to
	 * @param clazz 指定class进行反序列化
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <E> List<E> listOpsRange(String key,int from,int to,Class<E> clazz) {
		List<E> list = new ArrayList<E>(0);
		List<String> tmp = redisTemplate.opsForList().range(key, from, to);
		/*//此处取出的tmp 是全部数据序列化成一个数组类型的json串，而不是对每条数据单独序列化
		if(!CollectionUtils.isEmpty(tmp)) {
			list = RedisJsonUtil.jsonToBean(tmp.get(0), list.getClass());
		}*/
		if(clazz.equals(String.class)) {
			for(String json : tmp) {
				list.add((E)json);
			}
		} else {
			for(String json : tmp) {
				list.add(RedisJsonUtil.jsonToBean(json, clazz));
			}
		}
		return list;
	}
	
	/**
	 * 功能: List - push
	 * 
	 *
	 * @param key
	 * @return
	 */
	public Long listOpsPush(boolean isLeft,String key, Object value) {
		String target = (value instanceof String)?value.toString():RedisJsonUtil.beanToJson(value);
		if(isLeft) {
			return redisTemplate.opsForList().leftPush(key, target);
		} else {
			return redisTemplate.opsForList().rightPush(key, target);
		}
	}

	/**
	 * 功能: List - push all
	 * 
	 * @param key
	 * @return
	 */
	public Long listOpsPushAll(boolean isLeft, String key, Object[] values) {
		String[] targets = new String[values.length];
		for(int i=0;i<values.length;i++) {
			targets[i] = (values[i] instanceof String)?values[i].toString():RedisJsonUtil.beanToJson(values[i]);
		}
		/*for(int i=0;i<list.size();i++) {
			list.set(i, (list.get(i) instanceof String)?list.get(i).toString():RedisJsonUtil.beanToJson(list.get(i)));
		}*/
		if(isLeft) {
			return redisTemplate.opsForList().leftPushAll(key, targets);
		} else {
			return redisTemplate.opsForList().rightPushAll(key, targets);
		}
	}
	/* list end */
	
	/* map begin */
	/**
	 * 功能: Map数据类型 - put
	 * 
	 * @param key redis-key
	 * @param hashKey map-key
	 * @param value map-value 默认使用Jackson进行序列化存储，取值时需指定class进行反序列化
	 */
	public void mapOpsPut(String key, String hashKey, Object value) {
		if(value instanceof String) {
			redisTemplate.opsForHash().put(key, hashKey, value);
		} else {
			String json = RedisJsonUtil.beanToJson(value);
			redisTemplate.opsForHash().put(key, hashKey, json);
		}
	}
	/**
	 * 功能: Map数据类型 - put All
	 * 
	 * @param key redis-key
	 * @param maps value默认使用Jackson进行序列化存储，取值时需指定class进行反序列化
	 */
	public void mapOpsPutAll(String key, Map<String,? extends Object> maps) {
		Map<String,String> t = new HashMap<String, String>();
		for(Entry<String, ? extends Object> entry : maps.entrySet()) {
			t.put(entry.getKey(), RedisJsonUtil.beanToJson(entry.getValue()));
		}
		redisTemplate.opsForHash().putAll(key, t);
	}
	/**
	 * 功能: Map数据类型  get
	 * 
	 *
	 * @param key redis-key
	 * @param hashKey map-key
	 * @param clazz class 指定class进行反序列化操作
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public <T> T mapOpsGet(String key, String hashKey, Class<T> clazz) {
		Object obj = redisTemplate.opsForHash().get(key, hashKey);
		if(clazz==null || clazz.equals(String.class)) {
			return (T)obj;
		} else {
			if(obj!=null) {
				T t = RedisJsonUtil.jsonToBean(obj.toString(), clazz);
				return t;
			} else {
				return null;
			}
		}
	}
	/**
	 * 功能: Map数据类型  获取key对应所有数据
	 * @param key key值
	 * @param clazz 指定class进行反序列化操作
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <E> Map<String,E> mapOpsEntries(String key,Class<E> clazz) {
		Map<Object,Object> maps = redisTemplate.opsForHash().entries(key);
		if(MapUtils.isEmpty(maps)) {
			return null;
		} else {
			Map<String,E> result = new HashMap<String,E>();
			if(clazz.equals(String.class)) {
				for(Entry<Object, Object> entry : maps.entrySet()) {
					result.put(entry.getKey().toString(), (E)entry.getValue());
				}
			} else {
				for(Entry<Object, Object> entry : maps.entrySet()) {
					result.put(entry.getKey().toString(), RedisJsonUtil.jsonToBean(entry.getValue().toString(), clazz));
				}
			}
			
			return result;
		}
	}
	/**
	 * 功能: map - 删除
	 * 
	 * @param key redis key
	 * @param hashKeys map key
	 */
	public void mapOpsDelete(String key,Object... hashKeys) {
		redisTemplate.opsForHash().delete(key, hashKeys);
	}
	/* map end */
	
	/* set begin */
	/**
	 * 功能: Set数据类型  添加元素
	 * 
	 *
	 * @param key key值
	 * @param objects 
	 */
	public Long setOpsAdd(String key, Object...objects) {
		String[] objs = new String[objects.length];
		for(int i=0;i<objects.length;i++) {
			if(objects[i] instanceof String) {
				objs[i] = objects[i].toString();
			} else {
				objs[i] = RedisJsonUtil.beanToJson(objects[i]);
			}
		}
		return redisTemplate.opsForSet().add(key, objs);
	}
	/**
	 * 功能: Set数据类型  获取Set所有成员数据
	 * 
	 *
	 * @param key
	 * @param clazz 指定class进行反序列化操作
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <E> Set<E> setOpsGet(String key, Class<E> clazz) {
		Set<String> results = redisTemplate.opsForSet().members(key);
		//TODO 反序列化操作待验证，参考list操作
		if(CollectionUtils.isEmpty(results)) {
			return null;
		} else {
			Set<E> sets = new HashSet<E>();
			if(clazz.equals(String.class)) {
				for(String json : results) {
					sets.add((E) json);
				}
			} else {
				for(String json : results) {
					sets.add(RedisJsonUtil.jsonToBean(json, clazz));
				}
			}
			return sets;
		}
	}
	/**
	 * 功能: Set数据类型  判断是否集合元素
	 * 
	 * @param key
	 * @param obj
	 * @return
	 */
	public boolean setOpsIsMember(String key, Object obj) {
		String target = (obj instanceof String)?obj.toString():RedisJsonUtil.beanToJson(obj);
		
		return redisTemplate.opsForSet().isMember(key, target);
	}
	
	/* set end */


}
