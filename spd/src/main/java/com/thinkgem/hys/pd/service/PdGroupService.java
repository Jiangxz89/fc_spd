/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.entity.bo.PdGroupBO;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.PdGroup;
import com.thinkgem.hys.pd.dao.PdGroupDao;

import static com.thinkgem.jeesite.common.persistence.BaseEntity.DEL_FLAG_NORMAL;

/**
 * 组别表Service
 * @author sutianqi
 * @version 2018-03-07
 */
@Service
@Transactional(readOnly = true)
public class PdGroupService extends CrudService<PdGroupDao, PdGroup> {

	private static String BASE_URL = "";
	static {
		BASE_URL = Global.getConfig("SPD_CP_BASE_PRE_URL");
	}

	@Autowired
	PdGroupDao pdGroupDao;

	public PdGroup get(String id) {
		return super.get(id);
	}
	
	public List<PdGroup> findList(PdGroup pdGroup) {
		return super.findList(pdGroup);
	}

	public List<PdGroupBO> findUnsynchronizedList(PdGroupBO pdGroupBO) {
		return pdGroupDao.findUnsynchronizedList(pdGroupBO);
	}

	public Page<PdGroup> findPage(Page<PdGroup> page, PdGroup pdGroup) {
		return super.findPage(page, pdGroup);
	}
	
	@Transactional(readOnly = false)
	public void save(PdGroup pdGroup) {
		pdGroup.setSynFlag(MinaGlobalConstants.SYN_NONE);
		super.save(pdGroup);
	}
	
	@Transactional(readOnly = false)
	public void update(PdGroup pdGroup) {
		pdGroup.setSynFlag(MinaGlobalConstants.SYN_NONE);
		pdGroupDao.update(pdGroup);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdGroup pdGroup) {
		super.delete(pdGroup);
	}
	
	public PdGroup getByName(PdGroup pdGroup) {
		return pdGroupDao.getByName(pdGroup);
	}

	/**
	 * 校验组别是否唯一
	 * @param pdGroup
	 * @return
	 */
	public List<PdGroup> verify(PdGroup pdGroup) {
		return dao.verify(pdGroup);
	}

	/**
	 * 产品组别
	 * @param pdGroup
	 * @return
	 */
	public PdGroup findByName(PdGroup pdGroup) {
		return pdGroupDao.findByName(pdGroup);
	}

	/**
	 * 更新同步状态
	 * @param groupBO
	 */
	@Transactional(readOnly = false)
	public void updateSynFlag(PdGroupBO groupBO){
		pdGroupDao.updateSynFlag(groupBO);
	}

//	/**
//	 * 同步组别数据到中心平台
//	 * @return
//	 */
//	public boolean synToCentralPlatform(){
//		PdGroup pdGroup = new PdGroup();
//		pdGroup.setDelFlag(DEL_FLAG_NORMAL);
//		List<PdGroup> pdGroupList = this.findList(pdGroup);
////
////		for(PdGroup group : pdGroupList){
////			group.setCreateBy(null);
////			group.setUpdateBy(null);
////			group.setPage(null);
////			group.setCurrentUser(null);
////		}
//
//		String jsonPar = JSONArray.toJSONString(pdGroupList);
//		//同步接口
//		JSONObject json = HttpUtil.httpPost(BASE_URL + Global.getConfig("SYNC_PLATFORM_GROUP_URL"), jsonPar);
//
//		if(json != null && json.containsKey("statusCode")){
//			if(MinaGlobalConstants.SYNC_STATE_SUCCESS.equals(json.get("statusCode"))){
//				//成功
//				return true;
//			}else{
//				//失败
//				return false;
//			}
//		}else{
//			//失败
//			return false;
//		}
//	}

}