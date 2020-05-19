/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import java.util.List;
import com.thinkgem.hys.pd.entity.PdDosage;
import com.thinkgem.hys.pd.entity.PdDosagert;
import com.thinkgem.hys.pd.entity.PdProductStockTime;
import com.thinkgem.hys.pd.entity.PdRejectedListHead;
import com.thinkgem.hys.pd.entity.PdRpInventory;
import com.thinkgem.hys.pd.entity.PdStockRecord;

/**
 * 进销存明细DAO接口
 * @author zxh
 * @version 2018-05-10
 */
@MyBatisDao
public interface PdRpInventoryDao extends CrudDao<PdRpInventory> {

	void batchInsert(List<PdRpInventory> pdRpInventorys);
	
	List<PdRpInventory> initialInventoryQuery(PdProductStockTime pdProductStockTime);

	List<PdRpInventory> inStockRecordQuery(PdStockRecord pdStockRecord);

	List<PdRpInventory> bringUpTheQuery(PdStockRecord pdStockRecord);

	List<PdRpInventory> equipmentUseQuery(PdDosage pdDosage);

	List<PdRpInventory> amountOfReturnQuery(PdDosagert pdDosagert);

	List<PdRpInventory> hospitalReturnTheGoodsQuery(PdRejectedListHead pdRejectedListHead);

	void updateRpInventoryByData(PdRpInventory pdRpInventory);

	List<PdRpInventory> findEntersSellsQuery(PdRpInventory pdRpInventory);

	List<PdRpInventory> findListByDate(PdRpInventory newpdRpInventory);

	
}