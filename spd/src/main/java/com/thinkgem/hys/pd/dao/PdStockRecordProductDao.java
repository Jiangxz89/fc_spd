/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import java.util.List;

import com.thinkgem.hys.pd.entity.PdStockRecordProduct;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * 出入库记录关联产品记录DAO接口
 * @author changjundong
 * @version 2018-03-13
 */
@MyBatisDao
public interface PdStockRecordProductDao extends CrudDao<PdStockRecordProduct> {
	
	public void deleteByRecordId(PdStockRecordProduct pdStockRecordProduct);
	
	public void saveGroupByRecordId(List<PdStockRecordProduct> list);

	public List<PdStockRecordProduct> findPdList(PdStockRecordProduct pdStockRecordProduct);

	public List<PdStockRecordProduct> stockInAndOutRecordDetailQuery(PdStockRecordProduct pdStockRecordProduct);
}