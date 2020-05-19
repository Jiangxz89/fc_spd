/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.dao.AreaDao;
import com.thinkgem.jeesite.modules.sys.entity.Area;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 区域Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class AreaService extends TreeService<AreaDao, Area> {
	@Autowired
	private AreaDao areaDao;
	
	
    //地区编码根Code
    public final static String AREA_CODE_ROOT = "0";

	public List<Area> findAll(){
		return UserUtils.getAreaList();
	}

	@Transactional(readOnly = false)
	public void save(Area area) {
		super.save(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}
	
	@Transactional(readOnly = false)
	public void delete(Area area) {
		super.delete(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}
	
	//获取省份列表
	public List<Area> findListFormBase(Area area){
		return areaDao.findListFormBase(area);
	}
	
	//根据省份获取市区列表
	public List<Area> findCityListByProvinceCodeFormBase(Area area){
		return areaDao.findCityListByProvinceCodeFormBase(area);
	}
	
	@Transactional(readOnly = true)
	public List<Area> getAllToRedis(){
		return dao.getAllToRedis();
	}
	
	@Transactional(readOnly = true)
	public List<Area> findByParentCode(Area area){

		String type = area.getType();
		//如果为省级别则其pid为0
		if("2".equals(type)){
			area.setParentId(AREA_CODE_ROOT);
		}
		String parentCode = area.getParentId();
		if(StringUtils.isEmpty(parentCode)){
			return null;
		}
		return dao.findByParentCode(area);
	}
	
	//根据code获取地区
	public Area findByCodeFormBase(String code){
		return areaDao.findByCodeFormBase(code);
	}
	
	/**
	 * 获取区域信息
	 * @param area
	 * @return
	 */
	/*public List<Area> findAllByRedis(Area area){
		if(!com.thinkgem.jeesite.modules.hcy.mail.utils.JedisUtils.isCacheArea( )){
			List<Area> areaList=areaDao.findList( new Area() );
			if(areaList!=null && areaList.size( )>0){
				com.thinkgem.jeesite.modules.hcy.mail.utils.JedisUtils.setArea( areaList );
			}
		}
		return com.thinkgem.jeesite.modules.hcy.mail.utils.JedisUtils.getArea( area );
	}*/
	
	/**
	 * 根据区域编号获取区域信息
	 * @param area
	 * @return
	 */
	/*public String getAreaName(Area area){
		if(!com.thinkgem.jeesite.modules.hcy.mail.utils.JedisUtils.isCacheArea( )){
			List<Area> areaList=areaDao.findList( new Area() );
			if(areaList!=null && areaList.size( )>0){
				com.thinkgem.jeesite.modules.hcy.mail.utils.JedisUtils.setArea( areaList );
			}
		}
		return com.thinkgem.jeesite.modules.hcy.mail.utils.JedisUtils.getAreaName(area);
	}*/
	
	/*public Area getAreaModel(Area area){
		if(!com.thinkgem.jeesite.modules.hcy.mail.utils.JedisUtils.isCacheArea( )){
			List<Area> areaList=areaDao.findList( new Area() );
			if(areaList!=null && areaList.size( )>0){
				com.thinkgem.jeesite.modules.hcy.mail.utils.JedisUtils.setArea( areaList );
			}
		}
		return com.thinkgem.jeesite.modules.hcy.mail.utils.JedisUtils.getAreaModel(area);
	}*/
	
	/**
	 * 根据区域编号集合获取
	 * @param codeArr
	 * @return
	 */
	/*public String [] getAreaName(String ... codeArr){
		if(!com.thinkgem.jeesite.modules.hcy.mail.utils.JedisUtils.isCacheArea( )){
			List<Area> areaList=areaDao.findList( new Area() );
			if(areaList!=null && areaList.size( )>0){
				com.thinkgem.jeesite.modules.hcy.mail.utils.JedisUtils.setArea( areaList );
			}
		}
		return com.thinkgem.jeesite.modules.hcy.mail.utils.JedisUtils.getAreaName(codeArr);
	}*/
}
