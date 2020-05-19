package com.thinkgem.hys.pd.entity.vo;

import java.util.List;

import com.thinkgem.hys.pd.entity.PdApplyDetail;
import com.thinkgem.hys.pd.entity.PdProductMPackage;

public class ApplyDetailVo {

	
	private String packageId;
	private String packageName;
	private String packageNumber;
	private Integer packageCount;
	private List<PdProductMPackage> list;
	private List<PdApplyDetail> detail;
	
	
	public String getPackageId() {
		return packageId;
	}
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	public String getPackageNumber() {
		return packageNumber;
	}
	public void setPackageNumber(String packageNumber) {
		this.packageNumber = packageNumber;
	}
	public Integer getPackageCount() {
		return packageCount;
	}
	public void setPackageCount(Integer packageCount) {
		this.packageCount = packageCount;
	}
	public List<PdProductMPackage> getList() {
		return list;
	}
	public void setList(List<PdProductMPackage> list) {
		this.list = list;
	}
	public List<PdApplyDetail> getDetail() {
		return detail;
	}
	public void setDetail(List<PdApplyDetail> detail) {
		this.detail = detail;
	}
	
	
}
