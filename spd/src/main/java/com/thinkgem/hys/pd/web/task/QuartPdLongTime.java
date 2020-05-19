package com.thinkgem.hys.pd.web.task;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class QuartPdLongTime {
	private Logger logger = LoggerFactory.getLogger(QuartPdLongTime.class);
	@Autowired
	private PdProductStockService pdProductStockService;
	@Autowired
	private PdProductStockTotalService pdProductStockTotalService;
	@Autowired
	private PdStoreroomService pdStoreroomService;
	public void lonTimeUpdate() {
		logger.info("---------------------更新产品是否久存状态开始---------------------");
		PdProductStockTotal stockTotal = new PdProductStockTotal();
		StringBuilder sb= new StringBuilder("GROUP BY a.storeroom_id");
		Map<String, String> map=new HashMap<String, String>();
		map.put("dsf", sb.toString());
		stockTotal.setSqlMap(map);
		List<PdProductStockTotal> slist = pdProductStockTotalService.findList(stockTotal);
		if(slist!=null&&!slist.isEmpty()){
			for (PdProductStockTotal t : slist) {
				String storeroomId = t.getStoreroomId();
				PdProductStock ps = new PdProductStock();
				ps.setStoreroomId(storeroomId);
				List<PdProductStock> pids = pdProductStockService.findList(ps);
				for (PdProductStock pds : pids) {
					String productId = pds.getProductId();
					PdProductStock psk = new PdProductStock();
					psk.setProductId(productId);
					psk.setStoreroomId(storeroomId);
					StringBuilder s= new StringBuilder("ORDER BY a.create_date ASC");
					Map<String, String> p=new HashMap<String, String>();
					p.put("dsf", s.toString());
					psk.setSqlMap(p);
					List<PdProductStock> vList = pdProductStockService.findList(psk);
					Date createDate = vList.get(0).getCreateDate();
					Date date = new Date();
					//date = PlatDateUtils.getAfterDay(date,1);
					Integer longtimeRemind = pdStoreroomService.get(storeroomId).getLongtimeRemind();
					Date afterMonth = PlatDateUtils.getAfterMonth(createDate, longtimeRemind);
					if((!PlatDateUtils.sameDate(date,afterMonth))&&date.after(afterMonth)){
						PdProductStockTotal ST = new PdProductStockTotal();
						ST.setStoreroomId(storeroomId);
						ST.setIsLong(MinaGlobalConstants.IS_LONG_1);
						ST.setProductId(productId);
						pdProductStockTotalService.updateProductStock(ST);
					}
					
					
				}
				
			}
		}
		logger.info("---------------------更新产品是否久存状态结束---------------------");
	}
}
