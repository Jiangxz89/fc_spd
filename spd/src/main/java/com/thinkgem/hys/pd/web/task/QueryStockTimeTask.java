package com.thinkgem.hys.pd.web.task;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.thinkgem.hys.pd.entity.PdProductStock;
import com.thinkgem.hys.pd.service.PdProductStockService;
import com.thinkgem.hys.pd.service.PdProductStockTimeService;
import com.thinkgem.jeesite.common.utils.DateUtils;

/**
 * @Date   2018-04-28
 * @author Mr.Wang
 *
 */

public class QueryStockTimeTask {
	
	private static Logger log = Logger.getLogger(QueryStockTimeTask.class);
	
	@Autowired
	private PdProductStockService pdProductStockService;
	@Autowired
	private PdProductStockTimeService pdProductStockTimeService;

	public void saveEveryDayStock() {
		log.info("**********************统计查询（库存查询）任务开始*****************************");
		//当天时间
		Date today = DateUtils.getNowDate();
		List<PdProductStock> pstockList = pdProductStockService.findList(new PdProductStock());
		if (pstockList != null && pstockList.size() > 0) {
			pdProductStockTimeService.batchInsert(pstockList, today);
		}
		
		log.info("**********************统计查询（库存查询）任务结束*****************************");
	}
	
}
