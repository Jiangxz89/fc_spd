/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.PdBarcodeImg;
import com.thinkgem.hys.pd.dao.PdBarcodeImgDao;

/**
 * 条形码表Service
 * @author sutianqi
 * @version 2018-03-28
 */
@Service
@Transactional(readOnly = true)
public class PdBarcodeImgService extends CrudService<PdBarcodeImgDao, PdBarcodeImg> {

	public PdBarcodeImg get(String id) {
		return super.get(id);
	}
	
	public List<PdBarcodeImg> findList(PdBarcodeImg pdBarcodeImg) {
		return super.findList(pdBarcodeImg);
	}
	
	public Page<PdBarcodeImg> findPage(Page<PdBarcodeImg> page, PdBarcodeImg pdBarcodeImg) {
		return super.findPage(page, pdBarcodeImg);
	}
	
	@Transactional(readOnly = false)
	public void save(PdBarcodeImg pdBarcodeImg) {
		super.save(pdBarcodeImg);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdBarcodeImg pdBarcodeImg) {
		super.delete(pdBarcodeImg);
	}
	
}