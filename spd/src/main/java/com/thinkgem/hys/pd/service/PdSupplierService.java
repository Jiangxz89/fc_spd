/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import com.thinkgem.hys.pd.dao.PdSupplierDao;
import com.thinkgem.hys.pd.entity.PdSupplier;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 供应商信息Service
 * @author changjundong
 * @version 2018-04-28
 */
@Service
@Transactional(readOnly = true)
public class PdSupplierService extends CrudService<PdSupplierDao, PdSupplier> {
	@Autowired
	private PdSupplierDao pdSupplierDao;
	@Value( "${userfiles.basedir}" )
	protected String userfilesBasedir;
	
	public PdSupplier get(String id) {
		return super.get(id);
	}
	
	public List<PdSupplier> findList(PdSupplier pdSupplier) {
		return super.findList(pdSupplier);
	}
	
	public Page<PdSupplier> findPage(Page<PdSupplier> page, PdSupplier pdSupplier) {
		return super.findPage(page, pdSupplier);
	}
	
	@Transactional(readOnly = false)
	public void save(PdSupplier pdSupplier) {
		super.save(pdSupplier);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdSupplier pdSupplier) {
		super.delete(pdSupplier);
		if(pdSupplier.getImgUrl1()!=null && !"".equals(pdSupplier.getImgUrl1())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl1());
		}
		if(pdSupplier.getImgUrl2()!=null && !"".equals(pdSupplier.getImgUrl2())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl2());
		}
		if(pdSupplier.getImgUrl3()!=null && !"".equals(pdSupplier.getImgUrl3())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl3());
		}
		if(pdSupplier.getImgUrl4()!=null && !"".equals(pdSupplier.getImgUrl4())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl4());
		}
		if(pdSupplier.getImgUrl5()!=null && !"".equals(pdSupplier.getImgUrl5())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl5());
		}
		if(pdSupplier.getImgUrl6()!=null && !"".equals(pdSupplier.getImgUrl6())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl6());
		}
		if(pdSupplier.getImgUrl7()!=null && !"".equals(pdSupplier.getImgUrl7())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl7());
		}
		if(pdSupplier.getImgUrl8()!=null && !"".equals(pdSupplier.getImgUrl8())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl8());
		}
		if(pdSupplier.getImgUrl9()!=null && !"".equals(pdSupplier.getImgUrl9())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl9());
		}
		if(pdSupplier.getImgUrl10()!=null && !"".equals(pdSupplier.getImgUrl10())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl10());
		}
		if(pdSupplier.getImgUrl11()!=null && !"".equals(pdSupplier.getImgUrl11())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl11());
		}
		if(pdSupplier.getImgUrl12()!=null && !"".equals(pdSupplier.getImgUrl12())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl12());
		}
	}
	
	/**
	 * 供应商修改
	 * @param supplier
	 */
	@Transactional(readOnly = false)
	public void update(PdSupplier supplier) {
		supplier.preUpdate();
		PdSupplier pdSupplier = dao.get(supplier.getId());
		if(pdSupplier.getImgUrl1()!=null && !"".equals(pdSupplier.getImgUrl1()) &&"".equals(supplier.getImgUrl1())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl1());
		}
		if(pdSupplier.getImgUrl2()!=null && !"".equals(pdSupplier.getImgUrl2()) &&"".equals(supplier.getImgUrl2())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl2());
		}
		if(pdSupplier.getImgUrl3()!=null && !"".equals(pdSupplier.getImgUrl3()) &&"".equals(supplier.getImgUrl3())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl3());
		}
		if(pdSupplier.getImgUrl4()!=null && !"".equals(pdSupplier.getImgUrl4()) &&"".equals(supplier.getImgUrl4())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl4());
		}
		if(pdSupplier.getImgUrl5()!=null && !"".equals(pdSupplier.getImgUrl5()) &&"".equals(supplier.getImgUrl5())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl5());
		}
		if(pdSupplier.getImgUrl6()!=null && !"".equals(pdSupplier.getImgUrl6()) &&"".equals(supplier.getImgUrl6())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl6());
		}
		if(pdSupplier.getImgUrl7()!=null && !"".equals(pdSupplier.getImgUrl7()) &&"".equals(supplier.getImgUrl7())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl7());
		}
		if(pdSupplier.getImgUrl8()!=null && !"".equals(pdSupplier.getImgUrl8()) &&"".equals(supplier.getImgUrl8())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl8());
		}
		if(pdSupplier.getImgUrl9()!=null && !"".equals(pdSupplier.getImgUrl9()) &&"".equals(supplier.getImgUrl9())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl9());
		}
		if(pdSupplier.getImgUrl10()!=null && !"".equals(pdSupplier.getImgUrl10()) &&"".equals(supplier.getImgUrl10())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl10());
		}
		if(pdSupplier.getImgUrl11()!=null && !"".equals(pdSupplier.getImgUrl11()) &&"".equals(supplier.getImgUrl11())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl11());
		}
		if(pdSupplier.getImgUrl12()!=null && !"".equals(pdSupplier.getImgUrl12()) &&"".equals(supplier.getImgUrl12())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl12());
		}
		pdSupplierDao.update(supplier);
	}

	@Transactional(readOnly = false)
	public void batchDelete(List<String> result) {
		dao.batchDelete(result);
	}
	
	/**
	 * 校验供应商是否存在
	 * @param pdSupplier
	 * @return
	 */
	public List<PdSupplier> verify(PdSupplier pdSupplier) {
		return dao.verify(pdSupplier);
	}
	
	/**
	 * 校验供应商是否填写正确
	 * @param pdSupplier
	 * @return
	 */
	public PdSupplier findByName(PdSupplier pdSupplier) {
		return dao.findByName(pdSupplier);
	}

    public List<PdSupplier> findSelectList(PdSupplier pdSupplier) {
		return dao.findSelectList(pdSupplier);
    }
}