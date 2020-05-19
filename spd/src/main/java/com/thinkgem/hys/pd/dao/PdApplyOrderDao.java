/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.hys.pd.entity.PdApplyOrder;

/**
 * 申领单DAO接口
 * @author wg
 * @version 2018-03-06
 */
@MyBatisDao
public interface PdApplyOrderDao extends CrudDao<PdApplyOrder> {
	public int updateStatus(PdApplyOrder pdApplyOrder);
	//从申领单入库时更新实际领用数量
	public int updateFactCount(@Param("applyNo")String applyNo,
							   @Param("factCount")Integer factCount,
							   @Param("userId")String userId);
	public int updateApplyState(PdApplyOrder applyOrder);
	public List<PdApplyOrder> findAppBoxList(PdApplyOrder pdApplyOrder);
}