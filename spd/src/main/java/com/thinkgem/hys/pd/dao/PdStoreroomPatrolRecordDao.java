/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.hys.pd.entity.PdStoreroomPatrolRecord;

/**
 * 库房巡查记录DAO接口
 * @author changjundong
 * @version 2018-03-07
 */
@MyBatisDao
public interface PdStoreroomPatrolRecordDao extends CrudDao<PdStoreroomPatrolRecord> {
	public int updatePatrolResult(PdStoreroomPatrolRecord pdStoreroomPatrolRecord);
	public int batchInsert(List<PdStoreroomPatrolRecord> list);
	public int deletePatrolRecord(PdStoreroomPatrolRecord pdStoreroomPatrolRecord);
}