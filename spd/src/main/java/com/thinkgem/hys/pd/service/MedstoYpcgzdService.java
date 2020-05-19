/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.dao.MedstoYpcgmxDao;
import com.thinkgem.hys.pd.dao.MedstoYpcgzdDao;
import com.thinkgem.hys.pd.entity.MedstoYpcgmx;
import com.thinkgem.hys.pd.entity.MedstoYpcgzd;
import com.thinkgem.hys.pd.entity.vo.TempEntity;
import com.thinkgem.hys.utils.CpApiUtils;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.sys.utils.SnoGerUtil;

/**
 * 药库采购单Service
 * @author sutianqi
 * @version 2018-07-31
 */
@Service
@Transactional(readOnly = true)
public class MedstoYpcgzdService extends CrudService<MedstoYpcgzdDao, MedstoYpcgzd> {
	
	@Autowired
	private MedstoYpcgmxDao medstoYpcgmxDao;

	public MedstoYpcgzd get(String id) {
		return super.get(id);
	}
	
	public List<MedstoYpcgzd> findList(MedstoYpcgzd medstoYpcgzd) {
		return super.findList(medstoYpcgzd);
	}
	
	public Page<MedstoYpcgzd> findPage(Page<MedstoYpcgzd> page, MedstoYpcgzd medstoYpcgzd) {
		return super.findPage(page, medstoYpcgzd);
	}
	
	@Transactional(readOnly = false)
	public void save(MedstoYpcgzd medstoYpcgzd) {
		super.save(medstoYpcgzd);
	}
	
	@Transactional(readOnly = false)
	public void saveYpcgzd(MedstoYpcgzd medstoYpcgzd) {
		medstoYpcgzd.setXh(SnoGerUtil.getSerialNumber(12));
		medstoYpcgzd.setCjrq(DateUtils.formatDate(DateUtils.getNowDate(), "yyyy-MM-dd"));
		medstoYpcgzd.setJlzt(MinaGlobalConstants.DRUG_ORDER_STATUS_WAITING_ACCEPT);
		BigDecimal pifa_money = new BigDecimal("0");
		BigDecimal jinhuo_money = new BigDecimal("0");
		BigDecimal lingshou_money = new BigDecimal("0");
		
		//保存药品明细
		List<MedstoYpcgmx> mxList = Lists.newArrayList();
		List<MedstoYpcgmx> detailList = medstoYpcgzd.getPurchaseDetailList();
		if (detailList != null && detailList.size() > 0) {
			for (MedstoYpcgmx mx:detailList) {
				if (StringUtils.isEmpty(mx.getYpdm())) {
					continue;
				}
				pifa_money = pifa_money.add(new BigDecimal(mx.getYpfj()).multiply(new BigDecimal(mx.getCgsl())));//批发价
				jinhuo_money = jinhuo_money.add(new BigDecimal(mx.getYpjj()).multiply(new BigDecimal(mx.getCgsl())));//进价
				lingshou_money = lingshou_money.add(new BigDecimal(mx.getYlsj()).multiply(new BigDecimal(mx.getCgsl())));//零售价
				mx.setXh(SnoGerUtil.getSerialNumber(12));
				mx.setCgxh(medstoYpcgzd.getXh());
				mxList.add(mx);
			}
			if (!mxList.isEmpty())
				medstoYpcgmxDao.batchInsert(mxList);
		}
		//同步到中心平台
		List<MedstoYpcgzd> orderList = Lists.newArrayList();
		medstoYpcgzd.setJjje(jinhuo_money.toString());
		medstoYpcgzd.setLsje(lingshou_money.toString());
		medstoYpcgzd.setPfje(pifa_money.toString());
		medstoYpcgzd.setPurchaseDetailList(mxList);
		orderList.add(medstoYpcgzd);
		JSONObject json = CpApiUtils.syncDrugOrder(orderList);
		if ("200".equals(json.get("statusCode"))) {
			medstoYpcgzd.setStatus(MinaGlobalConstants.SYNC_STATE_1);//同步成功
		} else {
			medstoYpcgzd.setStatus(MinaGlobalConstants.SYNC_STATE_0);//同步失败
		}
		super.save(medstoYpcgzd);
	}
	
	@Transactional(readOnly = false)
	public void delete(MedstoYpcgzd medstoYpcgzd) {
		super.delete(medstoYpcgzd);
	}
	
	//同步数据
	@Transactional(readOnly = false)
	public String syncData(MedstoYpcgzd medstoYpcgzd) {
		//同步到中心平台
		List<MedstoYpcgzd> orderList = Lists.newArrayList();
		orderList.add(medstoYpcgzd);
		JSONObject json = CpApiUtils.syncDrugOrder(orderList);
		if ("200".equals(json.get("statusCode"))) {
			medstoYpcgzd.setStatus(MinaGlobalConstants.SYNC_STATE_1);//同步成功
		} else {
			medstoYpcgzd.setStatus(MinaGlobalConstants.SYNC_STATE_0);//同步失败
			return "上传失败";
		}
		super.save(medstoYpcgzd);
		return "上传成功";
	}
	
	@Transactional(readOnly = false)
	public int batchUpdate(List<TempEntity> list){
		return dao.batchUpdate(list);
	}
}