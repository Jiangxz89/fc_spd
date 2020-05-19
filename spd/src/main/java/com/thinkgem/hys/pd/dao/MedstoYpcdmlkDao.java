/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import java.util.List;
import java.util.Map;

import com.thinkgem.hys.pd.entity.MedstoYpcdmlk;
import com.thinkgem.hys.pd.entity.vo.MedstoYpcdmlkVo;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * 药品厂家库DAO接口
 * @author sutianqi
 * @version 2018-07-31
 */
@MyBatisDao
public interface MedstoYpcdmlkDao extends CrudDao<MedstoYpcdmlk> {
	public int batchInsert(List<MedstoYpcdmlkVo> list);
	public int clearData();
	public int updateSupplier(Map<String,Object> map);
}