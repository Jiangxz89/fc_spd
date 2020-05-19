/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import java.util.List;

import com.thinkgem.hys.pd.entity.PdDosagertDetail;

/**
 * 用量退回产品信息DAO接口
 * @author yueguoyun
 * @version 2018-03-31
 */
@MyBatisDao
public interface PdDosagertDetailDao extends CrudDao<PdDosagertDetail> {
	
	/**
	 * 批量插入
	 * @param tempArray
	 */
	void batchInsert(List<PdDosagertDetail> tempArray);
	
}