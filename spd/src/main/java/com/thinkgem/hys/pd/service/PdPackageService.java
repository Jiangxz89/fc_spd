/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.PdPackage;
import com.thinkgem.hys.pd.entity.PdProduct;
import com.thinkgem.hys.pd.entity.PdProductMPackage;
import com.thinkgem.hys.pd.dao.PdPackageDao;
import com.thinkgem.hys.pd.dao.PdProductDao;
import com.thinkgem.hys.pd.dao.PdProductMPackageDao;

/**
 * 定数包表Service
 * @author sutianqi
 * @version 2018-03-07
 */
@Service
@Transactional(readOnly = true)
public class PdPackageService extends CrudService<PdPackageDao, PdPackage> {
	
	@Autowired
	PdPackageDao pdPackageDao;
	@Autowired
	PdProductDao pdProductDao;
	@Autowired
	PdProductMPackageDao pdProductMPackageDao;
	@Autowired
	PdProductMPackageService pdProductMPackageService;
	@Autowired
	PdProductService pdProductService;

	public PdPackage get(String id) {
		return super.get(id);
	}
	
	public List<PdPackage> findList(PdPackage pdPackage) {
		return super.findList(pdPackage);
	}
	
	public Page<PdPackage> findPage(Page<PdPackage> page, PdPackage pdPackage) {
		return super.findPage(page, pdPackage);
	}
	
	@Transactional(readOnly = false)
	public void save(PdPackage pdPackage) {
		super.save(pdPackage);
	}
	
	@Transactional(readOnly = false)
	public void saveReturnId(PdPackage pdPackage) {
		pdPackageDao.insertReturnId(pdPackage);
	}
	
	@Transactional(readOnly = false)
	public void update(PdPackage pdPackage) {
		pdPackageDao.update(pdPackage);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdPackage pdPackage) {
		super.delete(pdPackage);
	}
	
	@Transactional(readOnly = false)
	public List<PdProduct> getProductByPackageId(String packageId,String storeroomId,String selfStoreId){
		//取定数包产品id集
		PdProductMPackage pdProductMPackage = new PdProductMPackage();
		pdProductMPackage.setPackageId(packageId);
		List<PdProductMPackage> findList = pdProductMPackageService.findList(pdProductMPackage);
		//取产品列表
		List<PdProduct> productList = Lists.newArrayList();  //产品列表
		if(null!=findList && findList.size()>0){
			for(int i = 0 ; i < findList.size() ; i ++){
				PdProduct pro = new PdProduct();
				pro.setId(findList.get(i).getProductId());
				pro.setStoreroomId(storeroomId);
				pro.setSelfStoreroomId(selfStoreId);
				PdProduct pdProduct = pdProductService.getProduct(pro);
				if(null!=pdProduct){
					pdProduct.setProductNumber(findList.get(i).getProductCount());
					productList.add(pdProduct);
				}
			}
		}
		
		return productList;
	}
	
	//
	public List<PdProductMPackage> getAllByPackageId(String packageId){
		return pdProductMPackageDao.getAllByPackageId(packageId);
	}
	
	//
	@Transactional(readOnly = false)
	public void removeChildren(List<PdProductMPackage> pdProductMPackageList, String packageId){
		if(pdProductMPackageList.size() != 0){
			PdProductMPackage childTemp = new PdProductMPackage();
			
			childTemp.setPackageId(packageId);
			List<PdProductMPackage> findList = pdProductMPackageDao.findList(childTemp);
			for(int i = 0 ; i < findList.size(); i ++){
				childTemp = findList.get(i);
				int delFlag = 0;
				for(int j = 0 ; j < pdProductMPackageList.size() ; j ++){
					String prodId = pdProductMPackageList.get(j).getProductId();
					if(prodId != null && !prodId.equals("")){
						boolean b = childTemp.getProductId().equals(prodId);
						if(b){
							delFlag = 1;
						}
					}
				}
				if(delFlag == 0){
					pdProductMPackageDao.delete(childTemp);
				}
			}
		}
		
	}
}