/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.PdPackage;
import com.thinkgem.hys.pd.entity.PdProductMPackage;
import com.thinkgem.hys.pd.dao.PdPackageDao;
import com.thinkgem.hys.pd.dao.PdProductMPackageDao;

/**
 * 产品定数包中间表Service
 * @author sutianqi
 * @version 2018-03-07
 */
@Service
@Transactional(readOnly = true)
public class PdProductMPackageService extends CrudService<PdProductMPackageDao, PdProductMPackage> {
	
	@Autowired
	PdProductMPackageDao pdProductMPackageDao;
	@Autowired
	PdPackageDao pdPackageDao;

	public PdProductMPackage get(String id) {
		return super.get(id);
	}
	
	public List<PdProductMPackage> findList(PdProductMPackage pdProductMPackage) {
		return super.findList(pdProductMPackage);
	}
	
	public Page<PdProductMPackage> findPage(Page<PdProductMPackage> page, PdProductMPackage pdProductMPackage) {
		return super.findPage(page, pdProductMPackage);
	}
	
	@Transactional(readOnly = false)
	public void save(PdProductMPackage pdProductMPackage) {
		super.save(pdProductMPackage);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdProductMPackage pdProductMPackage) {
		
		PdProductMPackage pdProductMPackage2 = pdProductMPackageDao.get(pdProductMPackage);
		super.delete(pdProductMPackage);
		PdPackage pdPackage = pdPackageDao.get(pdProductMPackage2.getPackageId());
		PdProductMPackage packMiddle  = new PdProductMPackage();
		packMiddle.setPackageId(pdProductMPackage2.getPackageId());
		List<PdProductMPackage> findList = pdProductMPackageDao.findList(packMiddle);
		
		int sum = 0;
		for(int i = 0 ; i < findList.size() ; i ++){
			sum += findList.get(i).getProductCount();
		}
		pdPackage.setSum(sum);
		pdPackageDao.update(pdPackage);
	}
	
	@Transactional(readOnly = false)
	public void update(PdProductMPackage pdProductMPackage) {
		pdProductMPackageDao.update(pdProductMPackage);
	}
	
	public List<PdProductMPackage> getProdListByPackageId(String storeroomId,String otherDeptId, String packageId){
		return dao.getProdListByPackageId(storeroomId,otherDeptId, packageId);
	}
	
	
}