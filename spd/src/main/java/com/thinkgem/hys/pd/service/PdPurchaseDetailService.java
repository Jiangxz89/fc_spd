/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.hys.pd.dao.PdPurchaseDetailDao;
import com.thinkgem.hys.pd.entity.PdPurchaseDetail;
import com.thinkgem.hys.pd.entity.vo.PurchaseOrderVo;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

/**
 * 申购单详细Service
 * @author wg
 * @version 2018-03-06
 */
@Service
@Transactional(readOnly = true)
public class PdPurchaseDetailService extends CrudService<PdPurchaseDetailDao, PdPurchaseDetail> {
	@Autowired
	PdPurchaseDetailDao pdPurchaseDetailDao;

	public PdPurchaseDetail get(String id) {
		return super.get(id);
	}
	
	public List<PdPurchaseDetail> findList(PdPurchaseDetail pdPurchaseDetail) {
		return super.findList(pdPurchaseDetail);
	}
	
	public Page<PdPurchaseDetail> findPage(Page<PdPurchaseDetail> page, PdPurchaseDetail pdPurchaseDetail) {
		return super.findPage(page, pdPurchaseDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(PdPurchaseDetail pdPurchaseDetail) {
		super.save(pdPurchaseDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdPurchaseDetail pdPurchaseDetail) {
		super.delete(pdPurchaseDetail);
	}
	
	/**
	 * 按科室汇总
	 * @param map
	 * @return
	 */
	public Page<PurchaseOrderVo> queryGroupListByDept(Page<PurchaseOrderVo> page, PurchaseOrderVo vo, String flag){
		vo.setPage(page);
		if("1".equals(flag)){//按科室分类
			page.setList(dao.queryGroupListByDept(vo));
		}else if("2".equals(flag)){//按品名分类
			page.setList(dao.queryGroupListByProduct(vo));
		}
		return page;
	}
	
	/**
	 * 
	 * */
	public List<PdPurchaseDetail> findAllList(){
		return pdPurchaseDetailDao.findAllList();
	}
}