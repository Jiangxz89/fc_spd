/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.constants.RspVo;
import com.thinkgem.hys.pd.dao.PdStoreroomPatrolDao;
import com.thinkgem.hys.pd.dao.PdStoreroomPatrolRecordDao;
import com.thinkgem.hys.pd.entity.PdStoreroomPatrol;
import com.thinkgem.hys.pd.entity.PdStoreroomPatrolRecord;
import com.thinkgem.hys.utils.CommonUtils;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 库房巡查Service
 * @author wg
 * @version 2018-03-30
 */
@Service
@Transactional(readOnly = true)
public class PdStoreroomPatrolService extends CrudService<PdStoreroomPatrolDao, PdStoreroomPatrol> {

	@Autowired
	private PdStoreroomPatrolRecordDao pdStoreroomPatrolRecordDao;
	
	public PdStoreroomPatrol get(String id) {
		return super.get(id);
	}
	
	public List<PdStoreroomPatrol> findList(PdStoreroomPatrol pdStoreroomPatrol) {
		return super.findList(pdStoreroomPatrol);
	}
	
	public Page<PdStoreroomPatrol> findPage(Page<PdStoreroomPatrol> page, PdStoreroomPatrol pdStoreroomPatrol) {
		return super.findPage(page, pdStoreroomPatrol);
	}
	
	@Transactional(readOnly = false)
	public void save(PdStoreroomPatrol pdStoreroomPatrol) {
		super.save(pdStoreroomPatrol);
	}
	@Transactional(readOnly = false)
	public RspVo savePatrol(PdStoreroomPatrol pdStoreroomPatrol, String patrolRecord) {
		RspVo vo = new RspVo();
		if(StringUtils.isEmpty(patrolRecord)){
			vo.setCode("400");
			vo.setInfo("巡查产品不能为空");
			return vo;
		}
		//巡查
		pdStoreroomPatrol.setPatrolCode(CommonUtils.generateOrderNoByType(MinaGlobalConstants.ORDER_NO_FIRST_LETTER_XC));
		
		//详情
		patrolRecord = patrolRecord.replaceAll("&quot;", "\"");
		List<PdStoreroomPatrolRecord> list = JSONArray.parseArray(patrolRecord, PdStoreroomPatrolRecord.class);
		int passCount = 0;
		int failCount = 0;
		for(PdStoreroomPatrolRecord ppd : list){
			ppd.preInsert();
			ppd.setPatrolCode(pdStoreroomPatrol.getPatrolCode());
			if(MinaGlobalConstants.STOREROOM_PATROL_RESULT_PASSED.equals(ppd.getPatrolResult())){
				passCount += 1;
			}else if(MinaGlobalConstants.STOREROOM_PATROL_RESULT_FAILED.equals(ppd.getPatrolResult())){
				failCount += 1;
			}
		}
		pdStoreroomPatrolRecordDao.batchInsert(list);
		//保存总表
		pdStoreroomPatrol.setPatrolCount(String.valueOf(list.size()));
		pdStoreroomPatrol.setPassCount(String.valueOf(passCount));
		pdStoreroomPatrol.setFailCount(String.valueOf(failCount));
		pdStoreroomPatrol.setPatrolMan(UserUtils.getUser().getId());
		pdStoreroomPatrol.setPatrolDate(DateUtils.getNowDate());
		super.save(pdStoreroomPatrol);
		vo.setCode("200");
		vo.setInfo("保存成功");
		vo.setUri("/pd/pdStoreroomPatrol");
		return vo;
	}
	
	@Transactional(readOnly = false)
	public void delete(PdStoreroomPatrol pdStoreroomPatrol) {
		super.delete(pdStoreroomPatrol);
		PdStoreroomPatrolRecord record = new PdStoreroomPatrolRecord();
		record.setPatrolCode(pdStoreroomPatrol.getPatrolCode());
		pdStoreroomPatrolRecordDao.deletePatrolRecord(record);
	}
	
	@Transactional(readOnly = false)
	public RspVo updatePatrol(PdStoreroomPatrol pdStoreroomPatrol, String patrolRecord) {
		RspVo vo = new RspVo();
		patrolRecord = patrolRecord.replaceAll("&quot;", "\"");
		List<PdStoreroomPatrolRecord> list = JSONArray.parseArray(patrolRecord, PdStoreroomPatrolRecord.class);
		if(list == null || list.isEmpty()){
			pdStoreroomPatrol.setUpdateBy(UserUtils.getUser());
			dao.updateDegree(pdStoreroomPatrol);
		}else{
			int passCount = 0;
			int failCount = 0;
			for(PdStoreroomPatrolRecord record: list){
				if(MinaGlobalConstants.STOREROOM_PATROL_RESULT_PASSED.equals(record.getPatrolResult())){
					passCount += 1;
				}else if(MinaGlobalConstants.STOREROOM_PATROL_RESULT_FAILED.equals(record.getPatrolResult())){
					failCount += 1;
				}
				record.setUpdateBy(UserUtils.getUser());
				pdStoreroomPatrolRecordDao.updatePatrolResult(record);
			}
			pdStoreroomPatrol.setPassCount(String.valueOf(passCount));
			pdStoreroomPatrol.setFailCount(String.valueOf(failCount));
			pdStoreroomPatrol.setUpdateBy(UserUtils.getUser());
			dao.updateDegree(pdStoreroomPatrol);
		}
		vo.setUri("/pd/pdStoreroomPatrol/");
		vo.setInfo("保存成功");
		return vo;
	}
}