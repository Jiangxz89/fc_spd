/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;


import com.thinkgem.hys.pd.entity.PdProduct;
import com.thinkgem.hys.pd.entity.bo.PdProductBO;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import java.util.HashMap;
import java.util.List;

/**
 * 产品表DAO接口
 * @author sutianqi
 * @version 2018-03-07
 */
@MyBatisDao
public interface PdProductDao extends CrudDao<PdProduct> {
	public void updateChargeCode(PdProduct pdProduct);
	
	PdProduct getProduct(PdProduct pdProduct);
	
	public List<PdProduct> getProductByPackageId(String packageId);
	
	public List<PdProduct> findAllNumber();	//取所有number
	
	public List<PdProduct> basicFindList(PdProduct pdProduct);//产品表基础查询
	
	public void minusSurplusPurCount(PdProduct pdProduct);
	
	public void importSave(List<PdProduct> pdProduct);	//导入插入

	public int updateSupplier(HashMap<String, Object> map);

	public List<PdProduct> checkCorrelation(PdProduct pdProduct);

	public PdProduct getOne(String id);

	List<PdProductBO> findUnsynchronizedList(PdProductBO pdProductBO);

	int updateSynFlag(PdProductBO pdProductBO);

	PdProduct findByNumber(String productNumber);

    List<PdProduct> findSelectList(PdProduct pdProduct);

    List<PdProduct> check(PdProduct prod);

    List<PdProduct> basicFindListOne(PdProduct pdProduct);
}