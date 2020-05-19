/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import java.util.List;

import com.thinkgem.hys.pd.entity.PdDosage;

/**
 * 器械用量DAO接口
 * @author wg
 * @version 2018-03-06
 */
@MyBatisDao
public interface PdDosageDao extends CrudDao<PdDosage> {

	List<PdDosage> findDosageSubsidiary(PdDosage pdDosage);
	
}