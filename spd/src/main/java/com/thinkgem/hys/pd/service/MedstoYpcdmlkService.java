/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.dao.MedstoYpcdmlkDao;
import com.thinkgem.hys.pd.dao.PdDrugSupplierDao;
import com.thinkgem.hys.pd.entity.MedstoYpcdmlk;
import com.thinkgem.hys.pd.entity.PdDrugSupplier;
import com.thinkgem.hys.pd.entity.vo.MedstoYpcdmlkVo;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

/**
 * 药品厂家库Service
 * @author sutianqi
 * @version 2018-07-31
 */
@Service
@Transactional(readOnly = true)
public class MedstoYpcdmlkService extends CrudService<MedstoYpcdmlkDao, MedstoYpcdmlk> {
	
	@Autowired
	private PdDrugSupplierDao pdDrugSupplierDao;

	public MedstoYpcdmlk get(String id) {
		return super.get(id);
	}
	
	public List<MedstoYpcdmlk> findList(MedstoYpcdmlk medstoYpcdmlk) {
		return super.findList(medstoYpcdmlk);
	}
	
	public Page<MedstoYpcdmlk> findPage(Page<MedstoYpcdmlk> page, MedstoYpcdmlk medstoYpcdmlk) {
		return super.findPage(page, medstoYpcdmlk);
	}
	
	@Transactional(readOnly = false)
	public void save(MedstoYpcdmlk medstoYpcdmlk) {
		super.save(medstoYpcdmlk);
	}
	
	//保存药品
	@Transactional(readOnly = false)
	public void saveDrug(MedstoYpcdmlk medstoYpcdmlk) {
		super.save(medstoYpcdmlk);
		//保存药品供应商中间表
		if (!StringUtils.isEmpty(medstoYpcdmlk.getGhdwId())) {
			PdDrugSupplier pds = pdDrugSupplierDao.getOne(medstoYpcdmlk.getYpdm(), MinaGlobalConstants.IS_DRUG_OR_CONSUMABLE_0);
			if (pds == null) {
				pds = new PdDrugSupplier();
				pds.setYpdm(medstoYpcdmlk.getYpdm());
				pds.setGhdwId(medstoYpcdmlk.getGhdwId());
				pds.setGhdwMc(medstoYpcdmlk.getGhdwMc());
				pds.setType(MinaGlobalConstants.IS_DRUG_OR_CONSUMABLE_0);
				pdDrugSupplierDao.insert(pds);
			} else {
				pds.setGhdwId(medstoYpcdmlk.getGhdwId());
				pds.setGhdwMc(medstoYpcdmlk.getGhdwMc());
				pdDrugSupplierDao.update(pds);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(MedstoYpcdmlk medstoYpcdmlk) {
		super.delete(medstoYpcdmlk);
	}
	
	@Transactional(readOnly = false)
	public int batchInsert(List<MedstoYpcdmlkVo> list) {
		return dao.batchInsert(list);
	}
	
	@Transactional(readOnly = false)
	public int clearData() {
		return dao.clearData();
	}
	
	@Transactional(readOnly = false)
	public int updateSupplier(String ids, String supplierId, String supplierName) {
		if (StringUtils.isEmpty(ids) 
				|| StringUtils.isEmpty(supplierId) 
				|| StringUtils.isEmpty(supplierName) ) {
			return 0;
		}
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("supplierId", supplierId);
		map.put("supplierName", supplierName);
		map.put("ids", ids.split(","));
		map.put("type", MinaGlobalConstants.IS_DRUG_OR_CONSUMABLE_0);
		//先做批量删除
		pdDrugSupplierDao.batchDelete(map);
		List<PdDrugSupplier> drugSuppliers =  new ArrayList<PdDrugSupplier>();
		for(String id:ids.split(",")){
			PdDrugSupplier drugSupplier = new PdDrugSupplier();
			drugSupplier.setYpdm(id);
			drugSupplier.setGhdwId(supplierId);
			drugSupplier.setGhdwMc(supplierName);
			drugSupplier.setType(MinaGlobalConstants.IS_DRUG_OR_CONSUMABLE_0);
			drugSuppliers.add(drugSupplier);
		}
		//批量添加产品中间表
		if(drugSuppliers.size()>0){
			pdDrugSupplierDao.batchSave(drugSuppliers);
		}
		return dao.updateSupplier(map);
	}
	
}