package com.thinkgem.hys.utils;

import java.util.UUID;

public class OrderNoUtils {

	public static String getOrderIdByUUId(String machineId) {
		//String machineId = "9";// 最大支持1-9个集群机器部署
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if (hashCodeV < 0) {// 有可能是负数
			hashCodeV = -hashCodeV;
		}
		// 0 代表前面补充0
		// 4 代表长度为4
		// d 代表参数为正数型
		return machineId + String.format("%011d", hashCodeV);
	}
	
	public static String getOrderNo() {
		return "M" + getOrderIdByUUId("6");
	}
	
	public static String getRefundNo() {
		return "R" + getOrderIdByUUId("7");
	}
	
	//生成仓库编号
	public static String getWarehouseNo() {
		return "W" + getOrderIdByUUId("8");
	}
	
	//生成供应商编号
	public static String getSupplierNo() {
		return "W" + getOrderIdByUUId("9");
	}
	
	//生成入库编号
	public static String getStockInNo() {
		return "SI" + getOrderIdByUUId("9");
	}
	
	//生成出库编号
	public static String getStockOutNo() {
		return "SO" + getOrderIdByUUId("9");
	}
	
	//生成属性编号
	public static String getArrtNo() {
		return "At" + getOrderIdByUUId("9");
	}
	
	//生成产品编号
	public static String getProductNo() {
		return "PR" + getOrderIdByUUId("30");
	}
	
	//生成商品编号
	public static String getGoodsNo() {
		return "GD" + getOrderIdByUUId("30");
	}
	
	//生成供应商编号
	public static String getGoodsClassNo() {
		return  getOrderIdByUUId("30");
	}
	
	//生成盘点编号
	public static String getStockCheckNo() {
		return  "PD" + getOrderIdByUUId("30");
	}
	//生成系统盘点编号
	public static String getSysStockCheckNo() {
		return  "SPD" + getOrderIdByUUId("30");
	}
}
