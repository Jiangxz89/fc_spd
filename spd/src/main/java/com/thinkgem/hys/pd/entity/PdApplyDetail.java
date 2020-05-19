/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 申领单详细Entity
 * @author wg
 * @version 2018-03-06
 */
public class PdApplyDetail extends DataEntity<PdApplyDetail> {
	
	private static final long serialVersionUID = 1L;
	private String applyNo;		// 申领单号
	private String prodId;		// 产品ID
	private String prodNo;		// 产品编码
	private String batchNo;		// 批号
	private String packageId;	//	产品所属包
	private Integer packageCount;	//	包的个数
	private Date expireDate;		// 有效期
	private Integer applyCount;		// 申领数量
	private Integer stockNum;//库存
	private Integer refundCount;		//退货数量
	
	//temp
	private PdProduct pdProduct;//产品对象
	private String packageName;//定数包名
	private String packageNumber;//定数包编号
	
	private String storeroomId;//仓库ID
	private String deptId;//申领科室
	private String mineStockNum;//本科室库存
	private String yourStockNum;//申领科室库存
	private String productCount;//查看申领单详情是展示包中的产品个数

	private Double inPrice; //产品进价 add by jiangxz 20190910
	private Double outPrice; //产品出价 add by jiangxz 20190910
	private BigDecimal pdTotalPrice;
	private BigDecimal pdOutTotalPrice;    //出价金额  add by jiangxz 20190910

	public PdApplyDetail() {
		super();
	}

	public PdApplyDetail(String id){
		super(id);
	}

	public BigDecimal getPdTotalPrice() {
		if(inPrice != null && applyCount != null){
			pdTotalPrice = BigDecimal.valueOf(inPrice).multiply(new BigDecimal(applyCount));
		}
		return pdTotalPrice;
	}

	public void setPdTotalPrice(BigDecimal pdTotalPrice) {
		this.pdTotalPrice = pdTotalPrice;
	}

	public BigDecimal getPdOutTotalPrice() {
		if(outPrice != null && applyCount != null){
			pdOutTotalPrice = BigDecimal.valueOf(outPrice).multiply(new BigDecimal(applyCount));
		}
		return pdOutTotalPrice;
	}

	public void setPdOutTotalPrice(BigDecimal pdOutTotalPrice) {
		this.pdOutTotalPrice = pdOutTotalPrice;
	}

	public Double getInPrice() {
		return inPrice;
	}

	public void setInPrice(Double inPrice) {
		this.inPrice = inPrice;
	}

	public Double getOutPrice() {
		return outPrice;
	}

	public void setOutPrice(Double outPrice) {
		this.outPrice = outPrice;
	}

	@Length(min=0, max=64, message="申领单号长度必须介于 0 和 64 之间")
	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}
	
	@Length(min=0, max=64, message="产品ID长度必须介于 0 和 64 之间")
	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	
	@Length(min=0, max=100, message="产品编码长度必须介于 0 和 100 之间")
	public String getProdNo() {
		return prodNo;
	}

	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}
	
	@Length(min=0, max=100, message="批号长度必须介于 0 和 100 之间")
	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	
	public Integer getPackageCount() {
		return packageCount;
	}

	public void setPackageCount(Integer packageCount) {
		this.packageCount = packageCount;
	}

	public Integer getApplyCount() {
		return applyCount;
	}

	public void setApplyCount(Integer applyCount) {
		this.applyCount = applyCount;
	}

	public Integer getRefundCount() {
		return refundCount;
	}

	public void setRefundCount(Integer refundCount) {
		this.refundCount = refundCount;
	}

	public PdProduct getPdProduct() {
		return pdProduct;
	}

	public void setPdProduct(PdProduct pdProduct) {
		this.pdProduct = pdProduct;
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

	public String getStoreroomId() {
		return storeroomId;
	}

	public void setStoreroomId(String storeroomId) {
		this.storeroomId = storeroomId;
	}

	public Integer getStockNum() {
		return stockNum;
	}

	public void setStockNum(Integer stockNum) {
		this.stockNum = stockNum;
	}

	public String getMineStockNum() {
		return mineStockNum;
	}

	public void setMineStockNum(String mineStockNum) {
		this.mineStockNum = mineStockNum;
	}

	public String getYourStockNum() {
		return yourStockNum;
	}

	public void setYourStockNum(String yourStockNum) {
		this.yourStockNum = yourStockNum;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getProductCount() {
		return productCount;
	}

	public void setProductCount(String productCount) {
		this.productCount = productCount;
	}
	
}