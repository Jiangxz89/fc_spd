/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.PdProductCoding;
import com.thinkgem.hys.pd.dao.PdProductCodingDao;

/**
 * 产品赋码表Service
 * @author zxh
 * @version 2019-05-16
 */
@Service
@Transactional(readOnly = true)
public class PdProductCodingService extends CrudService<PdProductCodingDao, PdProductCoding> {

	@Autowired
	private SqlSession sqlSession;

	public PdProductCoding get(String id) {
		return super.get(id);
	}
	
	public List<PdProductCoding> findList(PdProductCoding pdProductCoding) {
		return super.findList(pdProductCoding);
	}
	
	public Page<PdProductCoding> findPage(Page<PdProductCoding> page, PdProductCoding pdProductCoding) {
		return super.findPage(page, pdProductCoding);
	}
	
	@Transactional(readOnly = false)
	public void save(PdProductCoding pdProductCoding) {
		pdProductCoding.preInsert();
		//pdProductCoding.setRfidCode(String.valueOf(System.currentTimeMillis()));
		dao.insert(pdProductCoding);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdProductCoding pdProductCoding) {
		super.delete(pdProductCoding);
	}

	/**
	 * 批量取消
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void deleteAll(String[] id) {
		PdProductCodingDao productCodingDao = sqlSession.getMapper(PdProductCodingDao.class);
		for(String index:id){
			PdProductCoding productCoding = new PdProductCoding();
			productCoding.setId(index);
			productCodingDao.delete(productCoding);
		}
	}
}