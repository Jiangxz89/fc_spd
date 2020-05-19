/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.ArrayList;
import java.util.List;

import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.dao.PdStoreroomAdminDao;
import com.thinkgem.hys.pd.dao.PdStoreroomDao;
import com.thinkgem.hys.pd.dao.PdStoreroomProductDao;
import com.thinkgem.hys.pd.entity.PdStoreroom;
import com.thinkgem.hys.pd.entity.PdStoreroomAdmin;
import com.thinkgem.hys.pd.entity.PdStoreroomProduct;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

/**
 * 库房信息Service
 * @author changjundong
 * @version 2018-03-07
 */
@Service
@Transactional(readOnly = true)
public class PdStoreroomService extends CrudService<PdStoreroomDao, PdStoreroom> {
	@Autowired
	private PdStoreroomAdminDao pdStoreroomAdminDao;
	@Autowired
	private PdStoreroomProductDao pdStoreroomProductDao;
	@Autowired
	private UserDao userDao;
	public PdStoreroom get(String id) {
		return super.get(id);
	}
	
	public List<PdStoreroom> findList(PdStoreroom pdStoreroom) {
		return super.findList(pdStoreroom);
	}
	
	public Page<PdStoreroom> findPage(Page<PdStoreroom> page, PdStoreroom pdStoreroom) {
		return super.findPage(page, pdStoreroom);
	}
	
	@Transactional(readOnly = false)
	public void save(PdStoreroom pdStoreroom) {
		super.save(pdStoreroom);
		//删除已有管理员
		pdStoreroomAdminDao.deleteByStoreroomId(pdStoreroom.getId());
		//添加管理员
		if(pdStoreroom.getAdminList() != null && pdStoreroom.getAdminList().size() >0){
			List<PdStoreroomAdmin>  adminList = new ArrayList<PdStoreroomAdmin>();
			for (PdStoreroomAdmin pdStoreroomAdmin : pdStoreroom.getAdminList()) {
				pdStoreroomAdmin.preInsert();
				pdStoreroomAdmin.setStoreroomId(pdStoreroom.getId());
				adminList.add(pdStoreroomAdmin);
				//设置人员为可登陆
				userDao.batchUpdateState(pdStoreroomAdmin.getAdminId());
			}
			pdStoreroomAdminDao.saveGroupByStoreroomId(adminList);
		}
		if(MinaGlobalConstants.STOREROOM_CLASS_0.equals(pdStoreroom.getStoreroomClass())){
			//删除已有产品列
			pdStoreroomProductDao.deleteByStoreroomId(pdStoreroom.getId());
			//添加产品列
			if(pdStoreroom.getProductList() != null && pdStoreroom.getProductList().size()>0){
				List<PdStoreroomProduct> proList = pdStoreroom.getProductList();
				for (PdStoreroomProduct pdStoreroomProduct : proList) {
					pdStoreroomProduct.preInsert();
					pdStoreroomProduct.setStoreroomId(pdStoreroom.getId());
				}
				pdStoreroomProductDao.saveGroupByStoreroomId(proList);
			}
		}
		
	}
	
	@Transactional(readOnly = false)
	public void delete(PdStoreroom pdStoreroom) {
		super.delete(pdStoreroom);
		//删除库房管理员
		pdStoreroomAdminDao.deleteByStoreroomId(pdStoreroom.getId());
	}
	
	public String findFirstStoreroom() {
		return dao.findFirstStoreroom();
	}
}