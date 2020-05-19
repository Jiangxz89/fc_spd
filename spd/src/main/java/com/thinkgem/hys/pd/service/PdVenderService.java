/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.dao.PdProductDao;
import com.thinkgem.hys.pd.dao.PdVenderDao;
import com.thinkgem.hys.pd.entity.PdProduct;
import com.thinkgem.hys.pd.entity.PdVender;
import com.thinkgem.hys.pd.entity.bo.PdVenderBO;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.FileUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 生产厂家表Service
 * @author sutianqi
 * @version 2018-03-07
 */
@Service
@Transactional(readOnly = true)
public class PdVenderService extends CrudService<PdVenderDao, PdVender> {
	
	@Autowired
	PdVenderDao pdVenderDao;
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private PdProductDao pdProductDao;

	@Value( "${userfiles.basedir}" )
	protected String userfilesBasedir;

	public PdVender get(String id) {
		return super.get(id);
	}
	
	public List<PdVender> findList(PdVender pdVender) {
		return super.findList(pdVender);
	}
	
	public Page<PdVender> findPage(Page<PdVender> page, PdVender pdVender) {
		return super.findPage(page, pdVender);
	}
	
	@Transactional(readOnly = false)
	public void save(PdVender vender) {
		vender.setSynFlag(MinaGlobalConstants.SYN_NONE);
		if (vender.getIsNewRecord()){
			vender.preInsert();
			dao.insert(vender);
		}else{
			vender.preUpdate();
			//更新前对比数据删除文件
			PdVender pdVender = dao.get(vender.getId());
			if(pdVender.getLicenceSite1()!=null && !"".equals(pdVender.getLicenceSite1())&&"".equals(vender.getLicenceSite1())){
				//先删除再更新
				FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite1());
			}
			if(pdVender.getLicenceSite2()!=null && !"".equals(pdVender.getLicenceSite2())&&"".equals(vender.getLicenceSite2())){
				//先删除再更新
				FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite2());
			}
			if(pdVender.getLicenceSite3()!=null && !"".equals(pdVender.getLicenceSite3())&&"".equals(vender.getLicenceSite3())){
				//先删除再更新
				FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite3());
			}
			if(pdVender.getLicenceSite4()!=null && !"".equals(pdVender.getLicenceSite4())&&"".equals(vender.getLicenceSite4())){
				//先删除再更新
				FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite4());
			}
			if(pdVender.getLicenceSite5()!=null && !"".equals(pdVender.getLicenceSite5())&&"".equals(vender.getLicenceSite5())){
				//先删除再更新
				FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite5());
			}
			if(pdVender.getLicenceSite6()!=null && !"".equals(pdVender.getLicenceSite6())&&"".equals(vender.getLicenceSite6())){
				//先删除再更新
				FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite6());
			}
			if(pdVender.getLicenceSite7()!=null && !"".equals(pdVender.getLicenceSite7())&&"".equals(vender.getLicenceSite7())){
				//先删除再更新
				FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite7());
			}
			if(pdVender.getLicenceSite8()!=null && !"".equals(pdVender.getLicenceSite8())&&"".equals(vender.getLicenceSite8())){
				//先删除再更新
				FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite8());
			}
			if(pdVender.getLicenceSite9()!=null && !"".equals(pdVender.getLicenceSite9())&&"".equals(vender.getLicenceSite9())){
				//先删除再更新
				FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite9());
			}
			if(pdVender.getLicenceSite10()!=null && !"".equals(pdVender.getLicenceSite10())&&"".equals(vender.getLicenceSite10())){
				//先删除再更新
				FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite10());
			}
			if(pdVender.getLicenceSite11()!=null && !"".equals(pdVender.getLicenceSite11())&&"".equals(vender.getLicenceSite11())){
				//先删除再更新
				FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite11());
			}
			if(pdVender.getLicenceSite12()!=null && !"".equals(pdVender.getLicenceSite12())&&"".equals(vender.getLicenceSite12())){
				//先删除再更新
				FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite12());
			}
			dao.update(vender);
		}

	}
	
	@Transactional(readOnly = false)
	public void delete(PdVender pdVender) {
		super.delete(pdVender);
		if(pdVender.getLicenceSite1()!=null && !"".equals(pdVender.getLicenceSite1())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite1());
		}
		if(pdVender.getLicenceSite2()!=null && !"".equals(pdVender.getLicenceSite2())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite2());
		}
		if(pdVender.getLicenceSite3()!=null && !"".equals(pdVender.getLicenceSite3())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite3());
		}
		if(pdVender.getLicenceSite4()!=null && !"".equals(pdVender.getLicenceSite4())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite4());
		}
		if(pdVender.getLicenceSite5()!=null && !"".equals(pdVender.getLicenceSite5())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite5());
		}
		if(pdVender.getLicenceSite6()!=null && !"".equals(pdVender.getLicenceSite6())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite6());
		}
		if(pdVender.getLicenceSite7()!=null && !"".equals(pdVender.getLicenceSite7())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite7());
		}
		if(pdVender.getLicenceSite8()!=null && !"".equals(pdVender.getLicenceSite8())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite8());
		}
		if(pdVender.getLicenceSite9()!=null && !"".equals(pdVender.getLicenceSite9())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite9());
		}
		if(pdVender.getLicenceSite10()!=null && !"".equals(pdVender.getLicenceSite10())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite10());
		}
		if(pdVender.getLicenceSite11()!=null && !"".equals(pdVender.getLicenceSite11())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite11());
		}
		if(pdVender.getLicenceSite12()!=null && !"".equals(pdVender.getLicenceSite12())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite12());
		}
	}
	
	@Transactional(readOnly = false)
	public void update(PdVender pdVender) {
		pdVender.setSynFlag(MinaGlobalConstants.SYN_NONE);
		pdVenderDao.update(pdVender);
	}
	
	//批量添加
	@Transactional(readOnly = false)
	public void batchSave(List<PdVender> pdVender) {
		for(PdVender v : pdVender){
			if(v.getName() != null && !v.getName().equals("")){
				super.save(v);
			}
		}
	}
	
	public PdVender getByName(PdVender pdVender) {
		return pdVenderDao.getByName(pdVender);
	}
	
	public List<PdVender> findSimpleList(PdVender pdVender){
		return pdVenderDao.findSimpleList(pdVender);
	}

	/**
	 * 校验生产厂家名称是否唯一
	 * @param pdVender
	 * @return
	 */
	public List<PdVender> verify(PdVender pdVender) {
		return pdVenderDao.verify(pdVender);
		}
	
	/**
	 * 检查生产厂家与产品是否有关联
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = false)
	public List<String> checkCorrelation(String[] id) {
		PdProduct pdProduct = new PdProduct();
		List<String> failVenderNameList = new ArrayList<String>();
		for(int i = 0 ; i < id.length ; i ++){
			pdProduct.setVenderId(id[i]);
			List<PdProduct> prodList = pdProductDao.checkCorrelation(pdProduct);
			if(prodList.size() <= 0){//与产品无关联,可删除
				this.deleteFile(id[i]);
				pdVenderDao.deleteById(id[i]);
			}else{
				PdVender pdVender = pdVenderDao.get(id[i]);
				failVenderNameList.add(pdVender.getName());
			}
		}
		if(failVenderNameList.size() == 0){
			failVenderNameList.add("all-successful");
		}
		
		
		
		return failVenderNameList;
	}

	//删除生产厂家文件
	@Transactional(readOnly = false)
	void deleteFile(String id){
		PdVender pdVender = dao.get(id);
		if(pdVender.getLicenceSite1()!=null && !"".equals(pdVender.getLicenceSite1())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite1());
		}
		if(pdVender.getLicenceSite2()!=null && !"".equals(pdVender.getLicenceSite2())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite2());
		}
		if(pdVender.getLicenceSite3()!=null && !"".equals(pdVender.getLicenceSite3())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite3());
		}
		if(pdVender.getLicenceSite4()!=null && !"".equals(pdVender.getLicenceSite4())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite4());
		}
		if(pdVender.getLicenceSite5()!=null && !"".equals(pdVender.getLicenceSite5())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite5());
		}
		if(pdVender.getLicenceSite6()!=null && !"".equals(pdVender.getLicenceSite6())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite6());
		}
		if(pdVender.getLicenceSite7()!=null && !"".equals(pdVender.getLicenceSite7())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite7());
		}
		if(pdVender.getLicenceSite8()!=null && !"".equals(pdVender.getLicenceSite8())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite8());
		}
		if(pdVender.getLicenceSite9()!=null && !"".equals(pdVender.getLicenceSite9())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite9());
		}
		if(pdVender.getLicenceSite10()!=null && !"".equals(pdVender.getLicenceSite10())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite10());
		}
		if(pdVender.getLicenceSite11()!=null && !"".equals(pdVender.getLicenceSite11())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite11());
		}
		if(pdVender.getLicenceSite12()!=null && !"".equals(pdVender.getLicenceSite12())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdVender.getLicenceSite12());
		}
	}

	/**
	 * 批量导入数据
	 * @param dataList
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean saveBatchPdVender(List<PdVender> dataList) {
		PdVenderDao dao =  sqlSession.getMapper(PdVenderDao.class);
		try{
			for (PdVender pdVender : dataList) {
				pdVender.preInsert();
				dao.insert(pdVender);
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 校验生产厂家是否输入正确
	 * @param pdVender
	 * @return
	 */
	public PdVender findByName(PdVender pdVender) {
		return pdVenderDao.findByName(pdVender);
	}

	/**
	 * 查询位同步列表
	 * @author jiangxz
	 * @date
	 * @param
	 * @return
	 */
	public List<PdVenderBO> findUnsynchronizedList(PdVenderBO pdVenderBO){
		return pdVenderDao.findUnsynchronizedList(pdVenderBO);
	}

	/**
	 * 更新同步标识
	 * @param pdVenderBO
	 */
	@Transactional(readOnly = false)
	public void updateSynFlag(PdVenderBO pdVenderBO){
		pdVenderDao.updateSynFlag(pdVenderBO);
	}
}