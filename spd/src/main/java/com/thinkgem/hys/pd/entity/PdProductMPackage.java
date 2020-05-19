/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 产品定数包中间表Entity
 * @author sutianqi
 * @version 2018-03-07
 */
public class PdProductMPackage extends DataEntity<PdProductMPackage> {
	
	private static final long serialVersionUID = 1L;
	private String id;		// _id
	private String productId;		// 产品编号
	private String packageId;		// 定数包编号
	private int productCount;		// 产品数量
	private String udpateBy;		// udpate_by
	
	//-temp
	private PdProduct pdProduct;//产品
	private String storeroomId;
	private String stockNum;//库存
	private String yourStockNum;//你的库存
	private String otherDeptId;
	
	public PdProductMPackage() {
		super();
	}

	public PdProductMPackage(String id){
		super(id);
	}

	@Length(min=1, max=64, message="_id长度必须介于 1 和 64 之间")
	public String getId() {
		return id;
	}

	public void setId(String Id) {
		this.id = Id;
	}
	
	@Length(min=0, max=100, message="产品编号长度必须介于 0 和 100 之间")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	@Length(min=0, max=100, message="定数包编号长度必须介于 0 和 100 之间")
	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	
	
	
	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	@Length(min=0, max=100, message="udpate_by长度必须介于 0 和 100 之间")
	public String getUdpateBy() {
		return udpateBy;
	}

	public void setUdpateBy(String udpateBy) {
		this.udpateBy = udpateBy;
	}

	public PdProduct getPdProduct() {
		return pdProduct;
	}

	public void setPdProduct(PdProduct pdProduct) {
		this.pdProduct = pdProduct;
	}

	public String getStoreroomId() {
		return storeroomId;
	}

	public void setStoreroomId(String storeroomId) {
		this.storeroomId = storeroomId;
	}

	public String getStockNum() {
		return stockNum;
	}

	public void setStockNum(String stockNum) {
		this.stockNum = stockNum;
	}

	public String getYourStockNum() {
		return yourStockNum;
	}

	public void setYourStockNum(String yourStockNum) {
		this.yourStockNum = yourStockNum;
	}

	public String getOtherDeptId() {
		return otherDeptId;
	}

	public void setOtherDeptId(String otherDeptId) {
		this.otherDeptId = otherDeptId;
	}
	
}