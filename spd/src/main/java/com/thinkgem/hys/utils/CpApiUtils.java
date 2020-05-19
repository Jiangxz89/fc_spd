package com.thinkgem.hys.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.hys.pd.entity.MedstoYpcgzd;
import com.thinkgem.hys.pd.entity.vo.MedstoYpcdmlkVo;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.HttpUtil;

/**
 * spd调取spd-cp接口，同步数据
 * @author Mr.Wang
 */
public class CpApiUtils {
	
	private static String BASE_URL = "";
	private static String HOSP_CODE = "";
	
	static {
		BASE_URL = Global.getConfig("SPD_CP_BASE_PRE_URL");
		HOSP_CODE = Global.getConfig("HOSPITAL_NUMBER");
	}

	/**
	 * 药品基础数据
	 * @param medstoYpcdmlk
	 * @return
	 */
	public static JSONObject syncDrugInfo(List<MedstoYpcdmlkVo> medstoYpcdmlk) {
		JSONObject json = new JSONObject();
		if (medstoYpcdmlk != null && medstoYpcdmlk.size() > 0) {
			String medstoYpcdmlkJson = JSON.toJSONString(medstoYpcdmlk);
			json = HttpUtil.httpPost(BASE_URL + Global.getConfig("DRUG_INFO_URL"), medstoYpcdmlkJson);
		} else {
			json.put("statusCode", "400");
			json.put("msg", "同步数据为空");
		}
		return json;
	}
	
	/**
	 * 药品订单数据
	 * @param medstoYpcgzd
	 * @return
	 */
	public static JSONObject syncDrugOrder(List<MedstoYpcgzd> medstoYpcgzd) {
		JSONObject json = new JSONObject();
		if (medstoYpcgzd != null && medstoYpcgzd.size() > 0) {
			String medstoYpcgzdJson = JSONArray.toJSONString(medstoYpcgzd);
			json = HttpUtil.httpPost(BASE_URL + Global.getConfig("DRUG_ORDER_URL"), medstoYpcgzdJson);
		} else {
			json.put("statusCode", "400");
			json.put("msg", "同步数据为空");
		}
		return json;
	}
	
	/**
	 * 药品总库存数据
	 * @param medstoYkzkc
	 * @return
	 */
	public static JSONObject syncDrugTotalStock(String medstoYkzkc) {
		JSONObject json = new JSONObject();
		if (medstoYkzkc != null && !"".equals(medstoYkzkc.trim())) {
			json = HttpUtil.httpPost(BASE_URL + Global.getConfig("DRUG_TOTALSTOCK_URL"), medstoYkzkc);
		} else {
			json.put("statusCode", "400");
			json.put("msg", "同步数据为空");
		}
		return json;
	}
	
	/**
	 * 药品入库
	 * @param medstoYkzkc
	 * @return
	 */
	public static JSONObject syncDrugInStock(String medstoYprkzd) {
		JSONObject json = new JSONObject();
		if (medstoYprkzd != null && !"".equals(medstoYprkzd.trim())) {
			json = HttpUtil.httpPost(BASE_URL + Global.getConfig("DRUG_INSTOCK_URL"), medstoYprkzd);
		} else {
			json.put("statusCode", "400");
			json.put("msg", "同步数据为空");
		}
		return json;
	}
	/**
	 * 药品入库明细
	 * @param medstoYkzkc
	 * @return
	 */
	public static JSONObject syncDrugInStockDetail(String medstoYprkmx) {
		JSONObject json = new JSONObject();
		if (medstoYprkmx != null && !"".equals(medstoYprkmx.trim())) {
			json = HttpUtil.httpPost(BASE_URL + Global.getConfig("DRUG_INSTOCK_DETAIL_URL"), medstoYprkmx);
		} else {
			json.put("statusCode", "400");
			json.put("msg", "同步数据为空");
		}
		return json;
	}
	/**
	 * 药品退货
	 * @param medstoYkzkc
	 * @return
	 */
	public static JSONObject syncDrugRefund(String medstoYpthzd) {
		JSONObject json = new JSONObject();
		if (medstoYpthzd != null && !"".equals(medstoYpthzd.trim())) {
			json = HttpUtil.httpPost(BASE_URL + Global.getConfig("DRUG_REFUND_URL"), medstoYpthzd);
		} else {
			json.put("statusCode", "400");
			json.put("msg", "同步数据为空");
		}
		return json;
	}
	/**
	 * 药品退货明细
	 * @param medstoYkzkc
	 * @return
	 */
	public static JSONObject syncDrugRefundDetail(String medstoYpthmx) {
		JSONObject json = new JSONObject();
		if (medstoYpthmx != null && !"".equals(medstoYpthmx.trim())) {
			json = HttpUtil.httpPost(BASE_URL + Global.getConfig("DRUG_REFUND_DETAIL_URL"), medstoYpthmx);
		} else {
			json.put("statusCode", "400");
			json.put("msg", "同步数据为空");
		}
		return json;
	}
	
	//同步药品采购订单状态
	public static JSONObject syncDrugCgzdStatus(String dayTime) {
		JSONObject json = new JSONObject();
		if (dayTime != null && !"".equals(dayTime.trim())) {
			Map<String,String> map = new HashMap<String,String>();
			map.put("hospitalCode", HOSP_CODE);
			map.put("dayTime", dayTime);
			json = HttpUtil.doPost(BASE_URL + Global.getConfig("DRUG_ORDER_STATUS_SYNC_URL"), map, "utf-8");
		} else {
			json.put("statusCode", "400");
			json.put("msg", "同步数据为空");
		}
		return json;
	}
	
	/**
	 * 调取中心平台同步spd耗材采购订单状态
	 * @param today
	 * @return
	 */
	public static JSONObject syncPdoductStatus(String dayTime) {
		JSONObject json = new JSONObject();
		if (dayTime != null && !"".equals(dayTime.trim())) {
			Map<String,String> map = new HashMap<String,String>();
			map.put("hospitalCode", HOSP_CODE);
			map.put("dayTime", dayTime);
			json = HttpUtil.doPost(BASE_URL + Global.getConfig("PRODUCT_ORDER_STATUS_SYNC_URL"), map, "utf-8");
		} else {
			json.put("statusCode", "400");
			json.put("msg", "同步数据为空");
		}
		return json;
	}
	
	
	
}
