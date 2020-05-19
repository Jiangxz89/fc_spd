/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import java.util.List;

import com.thinkgem.hys.pd.entity.PdRejectedListHead;

/**
 * 退货单单头表DAO接口
 * @author sutianqi
 * @version 2018-03-07
 */
@MyBatisDao
public interface PdRejectedListHeadDao extends CrudDao<PdRejectedListHead> {
	public void isEnd(String id);

	public List<PdRejectedListHead> findHospitaloutsideReturnDetail(PdRejectedListHead pdRejectedListHead);

	public List<PdRejectedListHead> findListByDate(PdRejectedListHead pdRejectedListHead);
}