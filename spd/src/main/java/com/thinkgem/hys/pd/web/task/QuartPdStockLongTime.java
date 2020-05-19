package com.thinkgem.hys.pd.web.task;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.entity.PdProductStock;
import com.thinkgem.hys.pd.entity.PdProductStockTotal;
import com.thinkgem.hys.pd.service.PdProductStockService;
import com.thinkgem.hys.pd.service.PdProductStockTotalService;
import com.thinkgem.hys.pd.service.PdStoreroomService;
import com.thinkgem.hys.utils.PlatDateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;

public class QuartPdStockLongTime {
	private Logger logger = LoggerFactory.getLogger(QuartPdStockLongTime.class);
	@Autowired
	private PdProductStockTotalService pdProductStockTotalService;
	@Autowired
	private PdProductStockService pdProductStockService;
	@Autowired
	private PdStoreroomService pdStoreroomService;
	public void lonTimeUpdate() {
		PdProductStock pds = new PdProductStock();
		pds.setIsLong(MinaGlobalConstants.IS_LONG_0);
		logger.info("-------------------更新产品明细久存信息开始-------------------");
		List<PdProductStock> list = pdProductStockService.findList(pds);
		Map<String, Set<String>> m=new HashMap<String, Set<String>>();
		for (PdProductStock pdProductStock : list) {
			Date createDate = pdProductStock.getCreateDate();
			Date date = new Date();
			String storeroomId = pdProductStock.getStoreroomId();
			String productId = pdProductStock.getProductId();
			Integer longtimeRemind = pdStoreroomService.get(storeroomId).getLongtimeRemind();
			Date afterMonth = PlatDateUtils.getAfterMonth(createDate, longtimeRemind);
			if((!PlatDateUtils.sameDate(date,afterMonth))&&date.after(afterMonth)){
				PdProductStockTotal ST = new PdProductStockTotal();
				ST.setStoreroomId(storeroomId);
				ST.setIsLong(MinaGlobalConstants.IS_LONG_1);
				ST.setProductId(productId);
				pdProductStockTotalService.updateProductStock(ST);
				if(StringUtils.isNotEmpty(ST.getIsLong())){
					if(m.containsKey(storeroomId)){
						Set<String> pids = m.get(storeroomId);
						if(pids.contains(productId)){
							continue;
						}else{
							pids.add(productId);
							m.put(storeroomId, pids);
						}
					}else{
						Set<String> pids = new HashSet<String>();
						pids.add(productId);
						m.put(storeroomId, pids);
					}
				}
				
			}
			
		}
		logger.info("-------------------更新产品明细久存信息结束-------------------");
		logger.info(m.toString());
		Iterator<String> iter = m.keySet().iterator();
		logger.info("-------------------更新产品久存信息开始-------------------");
		while(iter.hasNext()){
			String storeroomId = iter.next();
			Set<String> pids = m.get(storeroomId);
			for (String pid : pids) {
				PdProductStockTotal ps = new PdProductStockTotal();
				ps.setStoreroomId(storeroomId);
				ps.setProductId(pid);
				ps.setIsLong(MinaGlobalConstants.IS_LONG_1);
				pdProductStockTotalService.updateProductStock(ps);				
			}
		}
		logger.info("-------------------更新产品久存状态结束-------------------");
	}
	
	
}
