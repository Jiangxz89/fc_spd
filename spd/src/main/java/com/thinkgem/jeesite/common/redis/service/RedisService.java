package com.thinkgem.jeesite.common.redis.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.druid.util.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.Area;
import com.thinkgem.jeesite.modules.sys.service.AreaService;

@Service("redisService")
@Transactional(readOnly = true)
public class RedisService {

	private static Logger logger = LoggerFactory.getLogger(RedisService.class);
	
	@Autowired
	private AreaService areaService;
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplateService redisTemplate;
	
	public void flushArea(){
		//获取所有的区域
		List<Area> areas = areaService.getAllToRedis();
		Map<String, Map<String, Area>> map = new HashMap<String, Map<String, Area>>();
		Map<String, Area> tmap =  null;
		
		//地区深度
		String deep;
		String key = "";
		for(Area vo:areas){
			//获取区域所对应的深度，2表示省级，3地市级  4 区县级  5  乡镇 ，街道
			deep = vo.getType();
			/**
			 * 根据地区深度判断地区级别
			 */
			if("2".equals(deep)) {//省级
				key = "area:province";
			} else if("3".equals(deep)) {//地市级
				key = "area:city:" + vo.getParentCode();
			} else if("4".equals(deep)) {//区县级
				key = "area:county:" + vo.getParentCode();
			}else if("5".equals(deep)){//乡镇，街道
				key = "area:street:" + vo.getParentCode();
			}
			if(map.containsKey(key)) {
				map.get(key).put(vo.getCode(), vo);
			} else {
				tmap = new HashMap<String, Area>();
				tmap.put(vo.getCode(), vo);//获取所有的数据 
				map.put(key, tmap);
			}
		}
		redisTemplate.deleteByKey(map.keySet());
		for (Map.Entry<String, Map<String, Area>> entry : map.entrySet()) {
			redisTemplate.mapOpsPutAll(entry.getKey(), entry.getValue());
		}
	}
	/**
	 * 获取所有的省市地区
	 * @param deep  编码深度
	 * @param totalCode
	 * @param parentCode
	 * @return
	 */
	public List<Area> getAreasCache(String deep,String totalCode,String parentCode) {
    	List<Area> areas = new ArrayList<Area>();
    	String key = null;
    	try {
    		if("2".equals(deep)) { //省份
    			key = "area:province";
        		if(StringUtils.isEmpty(totalCode)) { //省份编码为空，获取省份列表
        			Map<String,Area> areaMap = redisTemplate.mapOpsEntries(key, Area.class);
        			
	        		//排序  20161118
        			ArrayList<Entry<String, Area>> arrayList = new ArrayList<Entry<String, Area>>(  
        					areaMap.entrySet());  
	        		Collections.sort(arrayList, new Comparator<Map.Entry<String, Area>>() {  
	        		    public int compare(Map.Entry<String, Area> map1,  
	        		                Map.Entry<String, Area> map2) {  
	        		        return (map1.getKey().compareTo(map2.getKey()));  
	        		    }  
	        		});
	        		for(Entry<String, Area> entry:arrayList){
	        			areas.add(entry.getValue());
	        		}
        			//areas.addAll(areaMap.values());

        		} else { //根据省份编码获取省份信息
        			Area area = (Area) redisTemplate.mapOpsGet(key, totalCode, Area.class);
        			areas.add(area);
        		}
        	} else if("3".equals(deep)) { //地市
        		if(StringUtils.isEmpty(totalCode)) { //totalCode为空，返回parentCode省份所辖地市列表
        			key = "area:city:"+parentCode;
        			Map<String,Area> areaMap = redisTemplate.mapOpsEntries(key, Area.class);
        			if(!CollectionUtils.isEmpty(areaMap)) {
        				areas.addAll(areaMap.values());
        			}
        		} else { //返回totalCode地市信息
        			key = "area:city:"+parentCode;
        			Area area = (Area) redisTemplate.mapOpsGet(key, totalCode, Area.class);
        			areas.add(area);
        		}
        	} else if("4".equals(deep)){ //区县
        		if(StringUtils.isEmpty(totalCode)) { //区县编码totalCode为空，返回parentCode地市所辖区县列表
        			key = "area:county:"+parentCode;
        			Map<String,Area> areaMap = redisTemplate.mapOpsEntries(key, Area.class);
        			if(!CollectionUtils.isEmpty(areaMap)) {
        				areas.addAll(areaMap.values());
        			}
        		} else { //返回totalCode区县信息
        			key = "area:county:"+parentCode;
        			Area area = (Area) redisTemplate.mapOpsGet(key, totalCode, Area.class);
        			areas.add(area);
        		}
        	} else if("5".equals(deep)){ //街道，乡镇
        		if(StringUtils.isEmpty(totalCode)) { //区县编码totalCode为空，返回parentCode地市所辖区县列表
        			key = "area:street:"+parentCode;
        			Map<String,Area> areaMap = redisTemplate.mapOpsEntries(key, Area.class);
        			if(!CollectionUtils.isEmpty(areaMap)) {
        				areas.addAll(areaMap.values());
        			}
        		} else { //返回totalCode区县信息
        			key = "area:street:"+parentCode;
        			Area area = (Area) redisTemplate.mapOpsGet(key, totalCode, Area.class);
        			areas.add(area);
        		}
        	}
		} catch (Exception e) {
			logger.error("从redis获取地区信息时异常", e);
			if(StringUtils.isEmpty(deep)){
	    		return null;
	    	}
	    	if(!"2".equals(deep) && !"3".equals(deep) && !"4".equals(deep) && !"5".equals(deep)){
	    		return null;
	    	}
	    	if(("3".equals(deep) || "4".equals(deep) || "5".equals(deep)) && StringUtils.isEmpty(parentCode)){
	    		return null;
	    	}
	    	if("2".equals(deep)){
	    		parentCode = null;
	    	}
	    	Area area = new Area();
    		area.setType(deep);
    		area.setParentId(parentCode);
    		areas = areaService.findByParentCode(area);
    		return areas;
		}
    	if(null == areas || areas.size()==0){
    		Area area = new Area();
    		area.setType(deep);
    		area.setParentId(parentCode);
    		areas = areaService.findByParentCode(area);
    		return areas;
    	}
    	return areas;
    }
	

}
