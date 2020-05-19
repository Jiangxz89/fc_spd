/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.PdSyncState;
import com.thinkgem.hys.pd.dao.PdSyncStateDao;

/**
 * 同步中心平台状态表Service
 * @author zxh
 * @version 2018-07-30
 */
@Service
@Transactional(readOnly = true)
public class PdSyncStateService extends CrudService<PdSyncStateDao, PdSyncState> {

	public PdSyncState get(String id) {
		return super.get(id);
	}
	
	public List<PdSyncState> findList(PdSyncState pdSyncState) {
		return super.findList(pdSyncState);
	}
	
	public Page<PdSyncState> findPage(Page<PdSyncState> page, PdSyncState pdSyncState) {
		return super.findPage(page, pdSyncState);
	}
	
	@Transactional(readOnly = false)
	public void save(PdSyncState pdSyncState) {
		super.save(pdSyncState);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdSyncState pdSyncState) {
		super.delete(pdSyncState);
	}
	
	/**
	 * 根据同步时间查询同步失败的数据
	 * @param pdSyncState
	 * @return
	 */
	public PdSyncState findByDate(PdSyncState pdSyncState) {
		
		return dao.findByDate(pdSyncState);
	}
	
	/**
	 * 更新同步表的数据
	 * @param ss
	 */
	public void update(PdSyncState ss) {
		dao.update(ss);
	}
	
	/**
	 * 根据同步状态查询
	 * @param syncState
	 * @return
	 */
	public List<PdSyncState> findListByState(PdSyncState syncState) {
		
		return dao.findListByState(syncState);
	}
	
}