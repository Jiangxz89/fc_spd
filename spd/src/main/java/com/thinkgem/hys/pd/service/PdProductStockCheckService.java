/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.PdProductStockCheck;
import com.thinkgem.hys.pd.entity.PdProductStockCheckChild;
import com.thinkgem.hys.pd.dao.PdProductStockCheckDao;
import com.thinkgem.hys.pd.dao.PdProductStockCheckChildDao;

/**
 * 盘点表Service
 * @author sutianqi
 * @version 2018-03-07
 */
@Service
@Transactional(readOnly = true)
public class PdProductStockCheckService extends CrudService<PdProductStockCheckDao, PdProductStockCheck> {
	@Autowired
	PdProductStockCheckDao pdProductStockCheckDao;
	@Autowired
	PdProductStockCheckChildDao PdProductStockCheckChildDao;

	public PdProductStockCheck get(String id) {
		return super.get(id);
	}
	
	public List<PdProductStockCheck> findList(PdProductStockCheck pdProductStockCheck) {
		return super.findList(pdProductStockCheck);
	}
	
	public Page<PdProductStockCheck> findPage(Page<PdProductStockCheck> page, PdProductStockCheck pdProductStockCheck) {
		Page<PdProductStockCheck> findPage = super.findPage(page, pdProductStockCheck);
		
		/*if(pdProductStockCheck.getCheckDate()!= null && pdProductStockCheck.getCheckDate().length() >= 11){
			String checkDate = pdProductStockCheck.getCheckDate();
			String[] split = checkDate.split(",");
			
			List<PdProductStockCheck> list = new ArrayList<PdProductStockCheck>();
			for(int i = 0 ; i < split.length ; i ++){
				pdProductStockCheck.setCheckDate(split[i]);
				List<PdProductStockCheck> findList = super.findList(pdProductStockCheck);
				list.addAll(findList);
			}
			findPage.setList(list);
			return findPage;
		}else{
			return findPage;
		}*/
		
		return findPage;
	}
	
	@Transactional(readOnly = false)
	public void save(PdProductStockCheck pdProductStockCheck) {
		super.save(pdProductStockCheck);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdProductStockCheck pdProductStockCheck) {
		super.delete(pdProductStockCheck);
	}
	
	@Transactional(readOnly = false)
	public void insertNoneId(PdProductStockCheck pdProductStockCheck){
		pdProductStockCheckDao.insertNoneId(pdProductStockCheck);
	}
	
	//修改
	@Transactional(readOnly = false)
	public void update(PdProductStockCheck pdProductStockCheck) {
		pdProductStockCheckDao.update(pdProductStockCheck);
	}
	
	/**
	 * 盘点详细表
	 * */
	@Transactional(readOnly = false)
	public PdProductStockCheck getHeadAndChild(String id){
		
		PdProductStockCheck pdProductStockCheck = pdProductStockCheckDao.get(id);
		//根据主表id查询子集合
		List<PdProductStockCheckChild> list = PdProductStockCheckChildDao.getAll(id);
		
		pdProductStockCheck.setChild(list);
		
		return pdProductStockCheck;
	}
}