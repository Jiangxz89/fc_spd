package com.thinkgem.hys.pd.entity.excel;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

public class NotGoodProductExcel {

	@ExcelField(title="登记日期",sort=10,align=2)
	private String recordTime;
	
	@ExcelField(title="产品名称",sort=20,align=2)
	private String productName;
	
	@ExcelField(title="产品编号",sort=30,align=2)
	private String productNo;
	
	@ExcelField(title="规格",sort=40,align=2)
	private String productSpec;
	
	@ExcelField(title="型号",sort=50,align=2)
	private String productVersion;
	
	@ExcelField(title="批号",sort=60,align=2)
	private String productBatchNo;
	
	@ExcelField(title="住院号",sort=70,align=2)
	private String inHospitalNo;
	
	@ExcelField(title="严重登记",sort=80,align=2, dictType="bad_prod_level")
	private String seriousLevel;

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getProductSpec() {
		return productSpec;
	}

	public void setProductSpec(String productSpec) {
		this.productSpec = productSpec;
	}

	public String getProductVersion() {
		return productVersion;
	}

	public void setProductVersion(String productVersion) {
		this.productVersion = productVersion;
	}

	public String getProductBatchNo() {
		return productBatchNo;
	}

	public void setProductBatchNo(String productBatchNo) {
		this.productBatchNo = productBatchNo;
	}

	public String getInHospitalNo() {
		return inHospitalNo;
	}

	public void setInHospitalNo(String inHospitalNo) {
		this.inHospitalNo = inHospitalNo;
	}

	public String getSeriousLevel() {
		return seriousLevel;
	}

	public void setSeriousLevel(String seriousLevel) {
		this.seriousLevel = seriousLevel;
	}
	
	
}
