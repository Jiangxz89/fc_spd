/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import java.util.List;
import com.thinkgem.hys.pd.entity.PdConsumablesOrderTime;
import com.thinkgem.hys.pd.entity.PdStockRecord;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * 出入库记录DAO接口
 * @author changjundong
 * @version 2018-03-07
 */
@MyBatisDao
public interface PdStockRecordDao extends CrudDao<PdStockRecord> {
	
	public int updateRecordState(PdStockRecord pdStockRecord);
	
	public int examineIn(PdStockRecord pdStockRecord);
		
	public void rejectAll(PdStockRecord pdStockRecord);

	public List<PdStockRecord> findCensusQuery(PdStockRecord pdStockRecord);

	public List<PdStockRecord> findOutQuery(PdStockRecord pdStockRecord);

	public List<PdStockRecord> findCallInQuery(PdStockRecord pdStockRecord);

	public void updateReturnState(PdStockRecord newPdStockRecord);

	public List<PdConsumablesOrderTime> findPdConsumablesOrderTime(PdStockRecord pdStockRecord);

}