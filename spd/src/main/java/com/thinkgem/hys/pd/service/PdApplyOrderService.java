/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.hys.pd.entity.PdApplyDetail;
import com.thinkgem.hys.pd.entity.PdApplyOrder;
import com.thinkgem.hys.pd.entity.PdProductMPackage;
import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.dao.PdApplyDetailDao;
import com.thinkgem.hys.pd.dao.PdApplyOrderDao;
import com.thinkgem.hys.pd.dao.PdProductMPackageDao;

/**
 * 申领单Service
 * @author wg
 * @version 2018-03-06
 */
@Service
@Transactional(readOnly = true)
public class PdApplyOrderService extends CrudService<PdApplyOrderDao, PdApplyOrder> {
	
	@Autowired
	private PdApplyDetailDao pdApplyDetailDao;
	@Autowired
	private PdProductMPackageDao pdProductMPackageDao;
	@Autowired
	private PdApplyOrderDao pdApplyOrderDao;

	public PdApplyOrder get(String id) {
		return super.get(id);
	}
	
	public List<PdApplyOrder> findList(PdApplyOrder pdApplyOrder) {
		return super.findList(pdApplyOrder);
	}
	
	public Page<PdApplyOrder> findPage(Page<PdApplyOrder> page, PdApplyOrder pdApplyOrder) {
		return super.findPage(page, pdApplyOrder);
	}
	
	@Transactional(readOnly = false)
	public void save(PdApplyOrder pdApplyOrder) {
		super.save(pdApplyOrder);
	}
	/**
	 * 保存申领单
	 */
	@Transactional(readOnly = false)
	public void saveApplyOrder(PdApplyOrder pdApplyOrder) {
		List<PdApplyDetail> applyDetailList = pdApplyOrder.getApplyDetailList();
		if(applyDetailList !=null && applyDetailList.size()>0){
			List<PdApplyDetail> tempArray = new ArrayList<PdApplyDetail>();
			int prodNum = 0;//产品总数
			int packProdNum = 0;//包中产品总数
			for(PdApplyDetail pad : applyDetailList){
				if(StringUtils.isEmpty(pad.getProdId()) && StringUtils.isEmpty(pad.getPackageId()))
					continue;
				if(StringUtils.isNotBlank(pad.getProdId())){
					pad.preInsert();
					pad.setApplyNo(pdApplyOrder.getApplyNo());
					pad.setProdId(pad.getProdId());
					pad.setProdNo(pad.getProdNo());
					pad.setApplyCount(pad.getApplyCount());
					prodNum = prodNum + Integer.valueOf(pad.getApplyCount());
					tempArray.add(pad);
				}else if(StringUtils.isNotBlank(pad.getPackageId())){
					List<PdProductMPackage> pmpList = pdProductMPackageDao.getProdListByPackageId(UserUtils.getUser().getStoreroomId(),null, pad.getPackageId());
					for(PdProductMPackage ppmp : pmpList){
						PdApplyDetail pd = new PdApplyDetail();
						pd.preInsert();
						pd.setApplyNo(pdApplyOrder.getApplyNo());
						pd.setPackageId(pad.getPackageId());
						pd.setProdId(ppmp.getProductId());
						pd.setProdNo(ppmp.getPdProduct().getNumber());
						packProdNum = packProdNum + Integer.valueOf(pad.getPackageCount()) * ppmp.getProductCount();
						pd.setPackageCount(pad.getPackageCount());
						pd.setApplyCount(pad.getPackageCount() * ppmp.getProductCount());
						pd.setStockNum(Integer.valueOf(ppmp.getStockNum()));
						tempArray.add(pd);
					}
				}
				
			}
			//保存申领单详细表
			if(!tempArray.isEmpty())
				pdApplyDetailDao.batchInsert(tempArray);
			//保存申领单表
			pdApplyOrder.setApplyCount(prodNum + packProdNum);
			pdApplyOrder.setApplyBy(UserUtils.getUser().getId());
			pdApplyOrder.setDeptId(UserUtils.getUser().getStoreroomId());
			pdApplyOrder.setDeptName(UserUtils.getUser().getStoreroomName());
			pdApplyOrder.setApplyStatus(MinaGlobalConstants.APPLY_ORDER_STATUS_SENDED);
			pdApplyOrder.setApplyDate(DateUtils.getNowDate());
			super.save(pdApplyOrder);
		}
		
	}
	
	@Transactional(readOnly = false)
	public void delete(PdApplyOrder pdApplyOrder) {
		super.delete(pdApplyOrder);
	}
	
	/**
	 * 更新申领单状态
	 * @param pdApplyOrder
	 * @return
	 */
	@Transactional(readOnly = false)
	public int updateStatus(PdApplyOrder pdApplyOrder){
		return dao.updateStatus(pdApplyOrder);
	}

	public Page<PdApplyOrder> findAppBoxList(Page<PdApplyOrder> page, PdApplyOrder pdApplyOrder) {
		pdApplyOrder.setPage(page);
		page.setList(dao.findAppBoxList(pdApplyOrder));
		return page;
	}	
}