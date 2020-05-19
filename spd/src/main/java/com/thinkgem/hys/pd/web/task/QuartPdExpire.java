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

public class QuartPdExpire {
	private Logger logger = LoggerFactory.getLogger(QuartPdExpire.class);
	@Autowired
	private PdProductStockTotalService pdProductStockTotalService;
	@Autowired
	private PdProductStockService pdProductStockService;
	@Autowired
	private PdStoreroomService pdStoreroomService;
	public void start() {
		PdProductStock pds = new PdProductStock();
		/*StringBuilder sb= new StringBuilder("AND a.pd_state != 1");
		Map<String, String> map=new HashMap<String, String>();
		map.put("dsf", sb.toString());
		pds.setSqlMap(map);*/
		logger.info("-------------------更新过期状态开始-------------------");
		List<PdProductStock> list = pdProductStockService.findList(pds);
		Map<String, Set<String>> m=new HashMap<String, Set<String>>();
		for (PdProductStock pdProductStock : list) {		
			PdProductStock pd=new PdProductStock();
			String storeroomId = pdProductStock.getStoreroomId();
				Integer remind = pdStoreroomService.get(storeroomId).getDateRemind();				
				Date validDate = pdProductStock.getValidDate();
				Date ndate = new Date();				
				if((!PlatDateUtils.sameDate(ndate,validDate))&&ndate.after(validDate)){//过期
					pd.setPdState(MinaGlobalConstants.PD_STATE_1);					
				}				
				Date afterMonthDate = PlatDateUtils.getBeforeMonth(validDate, remind);					
				if((ndate.before(validDate)&&ndate.after(afterMonthDate))||(PlatDateUtils.sameDate(ndate,validDate)||PlatDateUtils.sameDate(ndate,afterMonthDate))){//近效期
					pd.setPdState(MinaGlobalConstants.PD_STATE_0);						
				}
				
				String pdState = pd.getPdState();
				if(StringUtils.isNotEmpty(pdState)){					
					pd.setId(pdProductStock.getId());
					pdProductStockService.updatePdState(pd);
					if(m.containsKey(storeroomId)){
						Set<String> pids  = (Set<String>) m.get(storeroomId);
						String pid = pdProductStock.getProductId();
						if(pids.contains(pid)){
							continue;
						}else{
							pids.add(pid);
							m.put(storeroomId, pids);
						}						
					}else{
						Set<String> pids=new HashSet<String>();
						String pid = pdProductStock.getProductId();	
						pids.add(pid);
						m.put(storeroomId, pids);
					}
				}						
		}
		logger.info("-------------------更新的主表产品状态-------------------");
		logger.info(m.toString());
		Iterator<String> iter = m.keySet().iterator();
		while(iter.hasNext()){	
			String storeroomId = iter.next();
			Set<String> set = m.get(storeroomId);
			for (String pid : set) {
				PdProductStock ps = new PdProductStock();
				ps.setStoreroomId(storeroomId);
				ps.setProductId(pid);
				StringBuilder s= new StringBuilder("AND (a.pd_state = '0' OR a.pd_state = '1') ORDER BY a.pd_state DESC");
				Map<String, String> p=new HashMap<String, String>();
				p.put("dsf", s.toString());
				ps.setSqlMap(p);
				List<PdProductStock> l = pdProductStockService.findList(ps);
				PdProductStock pk = l.get(0);
				PdProductStockTotal stockTotal = new PdProductStockTotal();
				stockTotal.setExpire(pk.getPdState());
				stockTotal.setProductId(pid);
				stockTotal.setStoreroomId(storeroomId);
				pdProductStockTotalService.updateProductStock(stockTotal);
			}
			
		}
		logger.info("-------------------更新过期状态结束-------------------");
	}
	
	
}
