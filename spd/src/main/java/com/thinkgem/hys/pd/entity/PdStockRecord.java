/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 出入库记录Entity
 * @author changjundong
 * @version 2018-03-07
 */
public class PdStockRecord extends DataEntity<PdStockRecord> implements Cloneable{
	
	private static final long serialVersionUID = 1L;
	private String recordNo;		// 出入库单号
	private String recodeType;		// 出入库类型 : （出库，入库）
	private String outType;		// 出库类型 : 正常出库，调拨出库，退货出库
	private String inType;		// 入库类型 : 正常入库，退货入库，调拨入库
	private String orderNo;		// 订单编号
	private String allocationNo;		// 调拨单号
	private String applyNo;		// 申领单号
	private String dosagertNo;	//退货单号
	private String recordPeople;		// 操作人
	private Date recordDate;		// 出入库时间
	private String recordState;		// 记录状态 : 待审核；已通过；已拒绝
	private String rejectReason;		// 驳回原因
	private String extend1;		// extend1
	private String extend2;		// extend2
	private String extend3;		// extend3
	private String testResult;		// 验收结果 : 合格；不合格
	private String storageResult;		// 储运状态 : 合格；不合格
	private String temperature;		// 温度
	private String humidity;		// 湿度
	private String outId;		// 出库方主键
	private String inId;		// 入库方主键
	private String supplierId;	//供应商主键
	private String checkPeople; //审核人
	private Date checkTime;	//审核时间
	private String returnState;//退货单状态
	

	private List<PdStockRecordProduct> productList;	//产品记录list
	private List<PdStockRecordInvoice> invoiceList;	//发票记录list
	
	//冗余
	private Integer productTotNum;		//产品数量合计
	
	private String productName;
	private String sectionDate; //选择日期
	
	private String outName;		// 出库方名称
	private String inName;		// 入库方名称
	private String number;		// 产品编码
	private String barCode;     //条码
	private String outReCordNo; //调入明细时出库单号
	
	private String pdjson;
	
	private String pdName;
	private String inStoreId;
	
	private PdProduct pdProduct;
	private String batchNo;
	private String productBarCode;		// 产品条码
	private Date validDate;		    // 有效期
	private String stockNum;
	private String venderName;     //生产厂家
	private String unit;		// 产品单位
	
	private String version;		// 型号
	private String spec;		// 规格
	private String storeroomId;		// 仓库id
	
	private String saleDate;     //供应商销售日期
	private String invoiceDate;     //供应商发票日期
	private String invoiceNo;       //供应商发票号
	
	private Integer productNum;		// 出入库数量
	private BigDecimal sellingPrice;		// 出价
	private BigDecimal pdTotalPrice;
	private BigDecimal sumPdTotalPrice;    //总价
	private BigDecimal sumPdOutTotalPrice;    //入库总价
	private Date limitDate;		// 有效期
	private String supplierName;	//供应商名称


	public String getSectionDate() {
		return sectionDate;
	}

	public void setSectionDate(String sectionDate) {
		this.sectionDate = sectionDate;
	}

	public Object clone() {   

        try {   

            return super.clone();   

        } catch (CloneNotSupportedException e) {   

            return null;   

        }   

    } 
	
	public PdStockRecord() {
		super();
	}

	public PdStockRecord(String id){
		super(id);
	}

	public BigDecimal getSumPdTotalPrice() {
		return sumPdTotalPrice;
	}

	public void setSumPdTotalPrice(BigDecimal sumPdTotalPrice) {
		this.sumPdTotalPrice = sumPdTotalPrice;
	}

	public BigDecimal getSumPdOutTotalPrice() {
		return sumPdOutTotalPrice;
	}

	public void setSumPdOutTotalPrice(BigDecimal sumPdOutTotalPrice) {
		this.sumPdOutTotalPrice = sumPdOutTotalPrice;
	}

	@Length(min=0, max=64, message="出入库单号长度必须介于 0 和 64 之间")
	public String getRecordNo() {
		return recordNo;
	}

	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}
	
	@Length(min=0, max=1, message="出入库类型 : （出库，入库）长度必须介于 0 和 1 之间")
	public String getRecodeType() {
		return recodeType;
	}

	public void setRecodeType(String recodeType) {
		this.recodeType = recodeType;
	}
	
	@Length(min=0, max=1, message="出库类型 : 正常出库，调拨出库，退货出库长度必须介于 0 和 1 之间")
	public String getOutType() {
		return outType;
	}

	public void setOutType(String outType) {
		this.outType = outType;
	}
	
	@Length(min=0, max=1, message="入库类型 : 正常入库，退货入库，调拨入库长度必须介于 0 和 1 之间")
	public String getInType() {
		return inType;
	}

	public void setInType(String inType) {
		this.inType = inType;
	}
	
	@Length(min=0, max=64, message="订单编号长度必须介于 0 和 64 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=0, max=64, message="调拨单号长度必须介于 0 和 64 之间")
	public String getAllocationNo() {
		return allocationNo;
	}

	public void setAllocationNo(String allocationNo) {
		this.allocationNo = allocationNo;
	}
	
	@Length(min=0, max=64, message="申领单号长度必须介于 0 和 64 之间")
	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}
	
	
	
	@Length(min=0, max=64, message="操作人长度必须介于 0 和 64 之间")
	public String getRecordPeople() {
		return recordPeople;
	}

	public void setRecordPeople(String recordPeople) {
		this.recordPeople = recordPeople;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	
	@Length(min=0, max=1, message="记录状态 : 待审核；已通过；已拒绝长度必须介于 0 和 1 之间")
	public String getRecordState() {
		return recordState;
	}

	public void setRecordState(String recordState) {
		this.recordState = recordState;
	}
	
	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
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
	
	@Length(min=0, max=1, message="验收结果 : 合格；不合格长度必须介于 0 和 1 之间")
	public String getTestResult() {
		return testResult;
	}

	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}
	
	@Length(min=0, max=1, message="储运状态 : 合格；不合格长度必须介于 0 和 1 之间")
	public String getStorageResult() {
		return storageResult;
	}

	public void setStorageResult(String storageResult) {
		this.storageResult = storageResult;
	}
	
	@Length(min=0, max=11, message="温度长度必须介于 0 和 11 之间")
	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	
	@Length(min=0, max=11, message="湿度长度必须介于 0 和 11 之间")
	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	
	@Length(min=0, max=64, message="出库方主键长度必须介于 0 和 64 之间")
	public String getOutId() {
		return outId;
	}

	public void setOutId(String outId) {
		this.outId = outId;
	}
	
	@Length(min=0, max=64, message="入库方主键长度必须介于 0 和 64 之间")
	public String getInId() {
		return inId;
	}

	public void setInId(String inId) {
		this.inId = inId;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public List<PdStockRecordProduct> getProductList() {
		return productList;
	}

	public void setProductList(List<PdStockRecordProduct> productList) {
		this.productList = productList;
	}

	public Integer getProductTotNum() {
		return productTotNum;
	}

	public void setProductTotNum(Integer productTotNum) {
		this.productTotNum = productTotNum;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public List<PdStockRecordInvoice> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(List<PdStockRecordInvoice> invoiceList) {
		this.invoiceList = invoiceList;
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getPdjson() {
		return pdjson;
	}

	public void setPdjson(String pdjson) {
		this.pdjson = pdjson;
	}
	
	public String getPdName() {
		return pdName;
	}

	public void setPdName(String pdName) {
		this.pdName = pdName;
	}

	public String getDosagertNo() {
		return dosagertNo;
	}

	public void setDosagertNo(String dosagertNo) {
		this.dosagertNo = dosagertNo;
	}

	public String getInStoreId() {
		return inStoreId;
	}

	public void setInStoreId(String inStoreId) {
		this.inStoreId = inStoreId;
	}	

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public PdProduct getPdProduct() {
		return pdProduct;
	}

	public void setPdProduct(PdProduct pdProduct) {
		this.pdProduct = pdProduct;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getProductBarCode() {
		return productBarCode;
	}

	public void setProductBarCode(String productBarCode) {
		this.productBarCode = productBarCode;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
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

	public String getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getVenderName() {
		return venderName;
	}

	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}

	public Integer getProductNum() {
		return productNum;
	}

	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}
	
	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public BigDecimal getPdTotalPrice() {
		if(sellingPrice != null){
			pdTotalPrice = sellingPrice.multiply(new BigDecimal(productNum));
		}
		return pdTotalPrice;
	}

	public void setPdTotalPrice(BigDecimal pdTotalPrice) {
		this.pdTotalPrice = pdTotalPrice;
	}
	
	@Length(min=0, max=50, message="产品单位长度必须介于 0 和 50 之间")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Date getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}

	public String getOutReCordNo() {
		return outReCordNo;
	}

	public void setOutReCordNo(String outReCordNo) {
		this.outReCordNo = outReCordNo;
	}	
	
	
	public String getCheckPeople() {
		return checkPeople;
	}

	public void setCheckPeople(String checkPeople) {
		this.checkPeople = checkPeople;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getReturnState() {
		return returnState;
	}

	public void setReturnState(String returnState) {
		this.returnState = returnState;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
}