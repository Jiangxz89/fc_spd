/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 库存总Entity
 * @author changjundong
 * @version 2018-03-07
 */
public class PdProductStockTotal extends DataEntity<PdProductStockTotal> {
	
	private static final long serialVersionUID = 1L;
	private String productId;		// 产品主键
	private Integer stockNum;		// 数量
	private String storeroomId;		// 仓库id
	private String limitUp;		// 上限
	private String limitDown;		//下限
	private String expire;		//过期标识 
	private String extend3;		// extend3
	private String isLong;		//是否久存
	private String supplierId;  //供应商ID

	//-temp
	private PdProduct pdProduct;//产品信息	
	private String isSel;
	
	//	
	private String name;//产品名称
	private String number;//产品编号
	private String spec;//产品规格
	private String version;//产品型号
	private String unit;// 产品单位

	private String categoryStr; //add by jiangxz 20190903 用于多选条件查询'
	private List<String> categoryList = new ArrayList<String>();
	private String groupStr;    //add by jiangxz 20190903 用于多选条件查询
	private List<String> groupList = new ArrayList<String>();

	public PdProductStockTotal() {
		super();
	}

	public PdProductStockTotal(String id){
		super(id);
	}

	public List<String> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<String> categoryList) {
		this.categoryList = categoryList;
	}

	public List<String> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<String> groupList) {
		this.groupList = groupList;
	}

	public String getCategoryStr() {
		return categoryStr;
	}

	public void setCategoryStr(String categoryStr) {
		this.categoryStr = categoryStr;
	}

	public String getGroupStr() {
		return groupStr;
	}

	public void setGroupStr(String groupStr) {
		this.groupStr = groupStr;
	}

	@Length(min=0, max=64, message="产品主键长度必须介于 0 和 64 之间")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}	
	
	@ExcelField(title="数量", align=2, sort=8)
	public Integer getStockNum() {
		return stockNum;
	}

	public void setStockNum(Integer stockNum) {
		this.stockNum = stockNum;
	}
	
	@Length(min=0, max=64, message="仓库id长度必须介于 0 和 64 之间")
	public String getStoreroomId() {
		return storeroomId;
	}

	public void setStoreroomId(String storeroomId) {
		this.storeroomId = storeroomId;
	}		

	public PdProduct getPdProduct() {
		return pdProduct;
	}

	public void setPdProduct(PdProduct pdProduct) {
		this.pdProduct = pdProduct;
	}
	
	@ExcelField(title="产品名称", align=2, sort=1)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@ExcelField(title="产品编号", align=2, sort=2)
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	@ExcelField(title="产品规格", align=2, sort=3)
	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}
	@ExcelField(title="产品型号", align=2, sort=4)
	public String getVersion() {
		return version;
	}

	public void setVersion(String spec) {
		this.version = spec;
	}
	
	@ExcelField(title="单位", align=2, sort=5)
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	@ExcelField(title="库存上限", align=2, sort=6)
	public String getLimitUp() {
		return limitUp;
	}

	public void setLimitUp(String limitUp) {
		this.limitUp = limitUp;
	}
	@ExcelField(title="库存下限", align=2, sort=7)
	public String getLimitDown() {
		return limitDown;
	}

	public void setLimitDown(String limitDown) {
		this.limitDown = limitDown;
	}
	@ExcelField(title="是否过期", align=2, sort=9,dictType="pd_state")
	public String getExpire() {
		return expire;
	}

	public void setExpire(String expire) {
		this.expire = expire;
	}

	public String getIsSel() {
		return isSel;
	}

	public void setIsSel(String isSel) {
		this.isSel = isSel;
	}	
		
	@Length(min=0, max=100, message="extend3长度必须介于 0 和 100 之间")
	public String getExtend3() {
		return extend3;
	}

	public void setExtend3(String extend3) {
		this.extend3 = extend3;
	}
	@ExcelField(title="是否久存", align=2, sort=10,dictType="is_long")
	public String getIsLong() {
		return isLong;
	}

	public void setIsLong(String isLong) {
		this.isLong = isLong;
	}
	
	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
}