/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.Area;

/**
 * 区域DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface AreaDao extends TreeDao<Area> {
	public List<Area> findListFormBase(Area area);
	
	public List<Area> findCityListByProvinceCodeFormBase(Area area);
	
	
	public List<Area> getAllToRedis();
	
	public List<Area> findByParentCode(Area area);
	
	public List<Area> queryByName(Area area);

	//public List<Area> findArea(Area area);
	
	public Area findByCodeFormBase(String code);
}
