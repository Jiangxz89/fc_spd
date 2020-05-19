package com.thinkgem.jeesite.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.hys.pd.dao.MedstoYkzkcDao;
import com.thinkgem.hys.pd.dao.MedstoYpcdmlkDao;
import com.thinkgem.hys.pd.dao.MedstoYprkmxDao;
import com.thinkgem.hys.pd.dao.MedstoYprkzdDao;
import com.thinkgem.hys.pd.dao.MedstoYpthmxDao;
import com.thinkgem.hys.pd.dao.MedstoYpthzdDao;
import com.thinkgem.hys.pd.entity.MedstoYkzkc;
import com.thinkgem.hys.pd.entity.MedstoYpcdmlk;
import com.thinkgem.hys.pd.entity.MedstoYprkmx;
import com.thinkgem.hys.pd.entity.MedstoYprkzd;
import com.thinkgem.hys.pd.entity.MedstoYpthmx;
import com.thinkgem.hys.pd.entity.MedstoYpthzd;
import com.thinkgem.hys.pd.web.task.DealSyncFailedTask;
import com.thinkgem.hys.pd.web.task.QueryStockTimeTask;
import com.thinkgem.hys.pd.web.task.SyncDrugDataTask;
import com.thinkgem.hys.utils.AxisUtils;

public class Test {
	
	private static ApplicationContext context;
 
	public static void main(String[] args) throws InterruptedException {
		/*Map<String, Object> dataMap=PostClient.getPostProcess( "yuantong", null, null, "12345678" );//获取物流信息
		//判断物流状态只有状态为3（签收）的时候才能延长收货
		if(dataMap != null && dataMap.size() > 0){
			String state = dataMap.get("state").toString(); //物流状态
			if("3".equals(state)){ //物流配送成功，已经签收
				List<Object> object = (List<Object>) dataMap.get("data");
			}
		}*/
		context = new ClassPathXmlApplicationContext("classpath*:/spring-context*.xml", "classpath*:/spring-quart.xml");
		//QueryStockTimeTask task = (QueryStockTimeTask) context.getBean("stockTimeJob");
		//task.saveEveryDayStock();
		
		/*MedstoYprkmxDao mdao = context.getBean(MedstoYprkmxDao.class);
		
		JSONObject json = AxisUtils.getDrugStorageDetail("201801", "201808");
		if ("200".equals(json.get("code"))) {
			Object o = json.get("data");
			List<MedstoYprkmx> list = JSONArray.parseArray(String.valueOf(o), MedstoYprkmx.class);
			mdao.batchInsert(list);
		}*/
		
		SyncDrugDataTask task = (SyncDrugDataTask) context.getBean("syncDrugDataFromHIS");
		
		task.startDrugInfo();
		/*DealSyncFailedTask task = (DealSyncFailedTask) context.getBean("hello");
		
		task.checkAndSync();*/
	}
}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             