/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import jersey.repackaged.com.google.common.collect.Sets;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 库存记录Entity
 * @author changjundong
 * @version 2018-03-07
 */
public class PdProductStock extends DataEntity<PdProductStock> {
	
	private static final long serialVersionUID = 1L;
	private String productId;		// 产品主键
	private String productNo;		//产品编号
	private String batchNo;		// 批次号
	private String productBarCode;		// 产品条码
	private Integer stockNum;		// 数量
	private String storeroomId;		// 仓库id
	private Date produceDate;		// 生产日期	
	private Date validDate;		    // 有效期
	private String extend1;		// extend1
	private String extend2;		// extend2
	private String extend3;		// extend3
	private String supplierId;
	private String productName;//产品名称
	private String isLong;//是否久存

	//-temp
	private PdProduct pdProduct;//产品信息
	private PdStoreroom pdStoreroom;//库房信息
	
	private String pdState;
	private String isSel;
	private String inHospitalNo;//住院号
	
	//
	private String storeroomName;//库房名称
	private String name;//产品名称
	private String number;//产品编号
	private String spec;//产品规格
	private String version;//产品型号
	private String unit;// 产品单位
	private String venderName;//生产厂家
	private String registration;// 注册证
	private String showValidDate;//页面展示有效期
	private Double sellingPrice;

	private Set<String> batchNoList = Sets.newHashSet();//批号列表
	private String stockMap;//批号对应的库存数量
	private String validDateMap;//批号对应的有效期	
	private String validDateMore;//有效期模糊查询
	private String validDateBegin;//有限期区间值开始
	private String validDateEnd;//有效期区间值结束
	
	private List<String> chargeCodeList; //收费代码


	private Double inPrice;		    // 产品进价 add by jiangxz 20190911
//	private Double outPrice;        // 产品出价 add by jiangxz 20190911
	private BigDecimal pdTotalPrice;

	private String supplierName;   //供应商名称 add by jiangxz 20190925

	@ExcelField(title="价格", align=2, sort=10)
    public BigDecimal getPdTotalPrice() {
        if(inPrice != null){
            pdTotalPrice = BigDecimal.valueOf(inPrice).multiply(new BigDecimal(stockNum));
        }
        return pdTotalPrice;
    }

    public void setPdTotalPrice(BigDecimal pdTotalPrice) {
        this.pdTotalPrice = pdTotalPrice;
    }

    public List<String> getChargeCodeList() {
		return chargeCodeList;
	}

	@ExcelField(title="入库单价", align=2, sort=11)
	public Double getInPrice() {
		return inPrice;
	}

	public void setInPrice(Double inPrice) {
		this.inPrice = inPrice;
	}

//	public Double getOutPrice() {
//		return outPrice;
//	}
//
//	public void setOutPrice(Double outPrice) {
//		this.outPrice = outPrice;
//	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public void setChargeCodeList(List<String> chargeCodeList) {
		this.chargeCodeList = chargeCodeList;
	}

	public Double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getValidDateBegin() {
		return validDateBegin;
	}

	public void setValidDateBegin(String validDateBegin) {
		this.validDateBegin = validDateBegin;
	}

	public String getValidDateEnd() {
		return validDateEnd;
	}

	public void setValidDateEnd(String validDateEnd) {
		this.validDateEnd = validDateEnd;
	}

	public String getValidDateMore() {
		return validDateMore;
	}

	public void setValidDateMore(String validDateMore) {
		this.validDateMore = validDateMore;
	}

	public PdProductStock() {
		super();
	}

	public PdProductStock(String id){
		super(id);
	}

	@Length(min=0, max=64, message="产品主键长度必须介于 0 和 64 之间")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	@Length(min=0, max=64, message="批次号长度必须介于 0 和 64 之间")
	@ExcelField(title="批号", align=2, sort=7)
	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	@Length(min=0, max=64, message="产品条码长度必须介于 0 和 64 之间")
	@ExcelField(title="产品条码", align=2, sort=4)
	public String getProductBarCode() {
		return productBarCode;
	}

	public void setProductBarCode(String productBarCode) {
		this.productBarCode = productBarCode;
	}
	
	@ExcelField(title="数量", align=2, sort=9)
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
	
	public Date getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate; 
	}
	
	@ExcelField(title="有效期", align=2, sort=8,type=0,dateFormat="yyyy-MM-dd")
	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
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

	public PdProduct getPdProduct() {
		return pdProduct;
	}

	public void setPdProduct(PdProduct pdProduct) {
		this.pdProduct = pdProduct;
	}

	public PdStoreroom getPdStoreroom() {
		return pdStoreroom;
	}

	public void setPdStoreroom(PdStoreroom pdStoreroom) {
		this.pdStoreroom = pdStoreroom;
	}
	
	//@ExcelField(title="库房名称", align=2, sort=1)
	public String getStoreroomName() {
		return storeroomName;
	}

	public void setStoreroomName(String storeroomName) {
		this.storeroomName = storeroomName;
	}
	@ExcelField(title="产品名称", align=2, sort=2)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@ExcelField(title="产品编号", align=2, sort=3)
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	@ExcelField(title="产品规格", align=2, sort=5)
	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}
	@ExcelField(title="产品型号", align=2, sort=6)
	public String getVersion() {
		return version;
	}

	public void setVersion(String spec) {
		this.version = spec;
	}
	
	@ExcelField(title="单位", align=2, sort=12)
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	@ExcelField(title="生产厂家", align=2, sort=13)
	public String getVenderName() {
		return venderName;
	}

	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}
	@ExcelField(title="注册证号", align=2, sort=14)
	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}
	@ExcelField(title="是否过期", align=2, sort=15,dictType="pd_state")
	public String getPdState() {
		return pdState;
	}

	public void setPdState(String pdState) {
		this.pdState = pdState;
	}

	public String getIsSel() {
		return isSel;
	}

	public void setIsSel(String isSel) {
		this.isSel = isSel;
	}


	public String getInHospitalNo() {
		return inHospitalNo;
	}

	public void setInHospitalNo(String inHospitalNo) {
		this.inHospitalNo = inHospitalNo;
	}

	public Set<String> getBatchNoList() {
		return batchNoList;
	}

	public void setBatchNoList(Set<String> batchNoList) {
		this.batchNoList = batchNoList;
	}

	public String getStockMap() {
		return stockMap;
	}

	public void setStockMap(String stockMap) {
		this.stockMap = stockMap;
	}

	public String getValidDateMap() {
		return validDateMap;
	}

	public void setValidDateMap(String validDateMap) {
		this.validDateMap = validDateMap;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getShowValidDate() {
		if (this.validDate != null)
			return DateUtils.formatDate(this.validDate, "yyyy-MM-dd");
		return showValidDate;
	}

	public void setShowValidDate(String showValidDate) {
		this.showValidDate = showValidDate;
	}

	public String getIsLong() {
		return isLong;
	}

	public void setIsLong(String isLong) {
		this.isLong = isLong;
	}	
}