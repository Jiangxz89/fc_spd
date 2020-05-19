/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 库房产品信息Entity
 * @author changjundong
 * @version 2018-03-07
 */
public class PdStoreroomProduct extends DataEntity<PdStoreroomProduct> {
	
	private static final long serialVersionUID = 1L;
	private String productId;		// 产品主键
	private String storeroomId;		// 库房主键
	private Integer highStock;		// 最高库存
	private Integer lowStock;		// 最低库存
	private String extend1;		// extend1
	private String extend2;		// extend2
	private String extend3;		// extend3
	//冗余
	private String spec;		// 规格
	private String version;		// 型号
	private String unit;		// 产品单位
	private String registration;	// 注册证
	private String venderName;		// 生产厂家名称
	private String productName;		// 产品名称
	private String number;			// 产品编码
	private String categoryName;	// 分类名称
	private String groupName;	// 组别名称
	
	public PdStoreroomProduct() {
		super();
	}

	public PdStoreroomProduct(String id){
		super(id);
	}

	@Length(min=0, max=64, message="产品主键长度必须介于 0 和 64 之间")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	@Length(min=0, max=64, message="库房主键长度必须介于 0 和 64 之间")
	public String getStoreroomId() {
		return storeroomId;
	}

	public void setStoreroomId(String storeroomId) {
		this.storeroomId = storeroomId;
	}

	public Integer getHighStock() {
		return highStock;
	}

	public void setHighStock(Integer highStock) {
		this.highStock = highStock;
	}

	public Integer getLowStock() {
		return lowStock;
	}

	public void setLowStock(Integer lowStock) {
		this.lowStock = lowStock;
	}

	@Length(min=0, max=200, message="extend1长度必须介于 0 和 200 之间")
	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}
	
	@Length(min=0, max=100, message="extend2长度必须介于 0 和 100 之间")
	public String getExtend2() {
		return extend2;
	}

	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}
	
	@Length(min=0, max=100, message="extend3长度必须介于 0 和 100 之间")
	public String getExtend3() {
		return extend3;
	}

	public void setExtend3(String extend3) {
		this.extend3 = extend3;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public String getVenderName() {
		return venderName;
	}

	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
}