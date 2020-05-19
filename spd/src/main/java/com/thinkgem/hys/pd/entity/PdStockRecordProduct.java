/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.DateUtils;

/**
 * 出入库记录关联产品记录Entity
 * @author changjundong
 * @version 2018-03-13
 */
public class PdStockRecordProduct extends DataEntity<PdStockRecordProduct> {
	
	private static final long serialVersionUID = 1L;
	private String recordId;		// 记录主键
	private String productId;		// 产品主键
	private String batchNo;		// 产品批次号
	private Integer productNum;		// 出入库数量
	private Date limitDate;		// 有效期
	private String numberPackageId;		// 定数包主键
	private String highLowSupplies;		// 高低值耗材
	private String productBarCode;		//	产品条码
	private String stockPosition;		//库位
	private String importNo;		// 导入单号
	private String extend1;		// extend1
	private String extend2;		// extend2
	private String extend3;		// extend3

	private Double inPrice; //产品进价 add by jiangxz 20190830
	private Double outPrice; //产品出价 add by jiangxz 20190910
	
	//冗余
	private String spec;		// 规格
	private String version;		// 型号
	private String unit;		// 产品单位
	private String registration;	// 注册证
	private String venderName;		// 生产厂家名称
	private String productName;		// 产品名称
	private String number;			// 产品编码
	private BigDecimal sellingPrice;		// 出价
	private BigDecimal pdTotalPrice;
	private BigDecimal pdOutTotalPrice;    //出价金额  add by jiangxz 20190910
	private String isUrgent;		 //是否紧急
	private Integer surplusPurCount; // 剩余采购量
	private String stockNum;//库存
	
	private String recodeType;		// 出入库类型 : （出库，入库）
	private String allocationNo;		// 调拨单号
	private String applyNo;		// 申领单号
	private Date validDate;		    // 有效期
	
	private String limitDateStr;		// 有效期字符串
	private String storeroomId;		// 仓库id

	private String venderNameShow;		// 生产厂家名称

	private String outName;		// 出库方名称
	private String inName;		// 入库方名称
	private Date checkTime;	    //审核时间

	public PdStockRecordProduct() {
		super();
	}

	public PdStockRecordProduct(String id){
		super(id);
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

	@Length(min=0, max=64, message="记录主键长度必须介于 0 和 64 之间")
	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	
	@Length(min=0, max=64, message="产品主键长度必须介于 0 和 64 之间")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	@Length(min=0, max=64, message="产品批次号长度必须介于 0 和 64 之间")
	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	public Integer getProductNum() {
		return productNum;
	}

	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}
	
	public Date getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}

	@Length(min=0, max=64, message="定数包主键长度必须介于 0 和 64 之间")
	public String getNumberPackageId() {
		return numberPackageId;
	}

	public void setNumberPackageId(String numberPackageId) {
		this.numberPackageId = numberPackageId;
	}
	
	@Length(min=0, max=1, message="高低值耗材长度必须介于 0 和 1 之间")
	public String getHighLowSupplies() {
		return highLowSupplies;
	}

	public void setHighLowSupplies(String highLowSupplies) {
		this.highLowSupplies = highLowSupplies;
	}
	
	public String getImportNo() {
		return importNo;
	}

	public void setImportNo(String importNo) {
		this.importNo = importNo;
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

	public String getProductBarCode() {
		return productBarCode;
	}

	public void setProductBarCode(String productBarCode) {
		this.productBarCode = productBarCode;
	}

	public String getStockPosition() {
		return stockPosition;
	}

	public void setStockPosition(String stockPosition) {
		this.stockPosition = stockPosition;
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

	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public BigDecimal getPdTotalPrice() {
		if(inPrice != null){
			pdTotalPrice = BigDecimal.valueOf(inPrice).multiply(new BigDecimal(productNum));
		}
		return pdTotalPrice;
	}

	public void setPdTotalPrice(BigDecimal pdTotalPrice) {
		this.pdTotalPrice = pdTotalPrice;
	}

	public BigDecimal getPdOutTotalPrice() {
		if(outPrice != null){
			pdOutTotalPrice = BigDecimal.valueOf(outPrice).multiply(new BigDecimal(productNum));
		}
		return pdOutTotalPrice;
	}

	public void setPdOutTotalPrice(BigDecimal pdOutTotalPrice) {
		this.pdOutTotalPrice = pdOutTotalPrice;
	}

	public String getIsUrgent() {
		return isUrgent;
	}

	public void setIsUrgent(String isUrgent) {
		this.isUrgent = isUrgent;
	}

	public Integer getSurplusPurCount() {
		return surplusPurCount;
	}

	public void setSurplusPurCount(Integer surplusPurCount) {
		this.surplusPurCount = surplusPurCount;
	}
	
	public String getStockNum() {
		return stockNum;
	}

	public void setStockNum(String stockNum) {
		this.stockNum = stockNum;
	}

	public String getRecodeType() {
		return recodeType;
	}

	public void setRecodeType(String recodeType) {
		this.recodeType = recodeType;
	}

	public String getAllocationNo() {
		return allocationNo;
	}

	public void setAllocationNo(String allocationNo) {
		this.allocationNo = allocationNo;
	}

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}	
	
	public String getLimitDateStr() {
		if(this.limitDate != null){
			this.limitDateStr = DateUtils.formatDate(this.limitDate, null);
		}
		return limitDateStr;
	}

	public void setLimitDateStr(String limitDateStr) {
		this.limitDateStr = limitDateStr;
	}

	public String getStoreroomId() {
		return storeroomId;
	}

	public void setStoreroomId(String storeroomId) {
		this.storeroomId = storeroomId;
	}

	public String getVenderNameShow() {
		return venderNameShow;
	}

	public void setVenderNameShow(String venderNameShow) {
		this.venderNameShow = venderNameShow;
	}

	public String getOutName() {
		return outName;
	}

	public void setOutName(String outName) {
		this.outName = outName;
	}

	public String getInName() {
		return inName;
	}

	public void setInName(String inName) {
		this.inName = inName;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
}