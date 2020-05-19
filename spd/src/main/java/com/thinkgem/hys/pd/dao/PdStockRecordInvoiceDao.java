/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import java.util.List;

import com.thinkgem.hys.pd.entity.PdStockRecordInvoice;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * 出入库发票记录DAO接口
 * @author changjundong
 * @version 2018-03-07
 */
@MyBatisDao
public interface PdStockRecordInvoiceDao extends CrudDao<PdStockRecordInvoice> {
	
	public void deleteByRecordId(PdStockRecordInvoice pdStockRecordInvoice);
	
	public void saveGroupByRecordId(List<PdStockRecordInvoice> list);
}