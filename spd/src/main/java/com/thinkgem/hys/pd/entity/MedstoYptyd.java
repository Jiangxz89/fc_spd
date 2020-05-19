/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 医院药品退药单表Entity
 * @author zxh
 * @version 2019-05-09
 */
public class MedstoYptyd extends DataEntity<MedstoYptyd> {
	
	private static final long serialVersionUID = 1L;
	private String xh;		// 序号
	private String storeName;		// 药库名称
	private String drugId;		// 药品编码
	private String ybtybm;		// 医保统一编码
	private String isjbyw;		// 是否基药
	private String kss;		// 是否抗生素药品
	private String cmedCode;		// 中药使用类别代码
	private String drugDetailType;		// 药物类型
	private String doseCode;		// 药物剂型代码
	private String drugName;		// 药品名称
	private String drugSpec;		// 药品规格
	private String packageUnit;		// 大包装单位
	private String packageQty;		// 大包装数量
	private String salesUnit;		// 小包装单位
	private String salesQty;		// 小包装数量
	private String salesRelation;		// 包装单位数量关系
	private String producerName;		// 供货商名称
	private String costPrice;		// 进价单价
	private String salePrice;		// 零售单价
	private String costAmt;		// 进价金额合计
	private String saleAmt;		// 零售金额合计
	private String batchNo;		// 生产批号
	private String sdrugmanufacturers;		// 药品生产企业
	private String sdrughabitat;		// 药品产地
	private String sratificationno;		// 批准文号
	private Date ddateproduce;		// 生产日期
	private Date expiry;		// 失效日期
	private String inventorystorage;		// 盘点库房
	private String storagenumber;		// 库存数量
	private String inventorynumber;		// 退货数量
	private Date thrq;		// 退货时间
	private String thyy;		// 退货原因
	private Date scrq;		// 上传日期
	
	public MedstoYptyd() {
		super();
	}

	public MedstoYptyd(String id){
		super(id);
	}

	@Length(min=1, max=60, message="序号长度必须介于 1 和 60 之间")
	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}
	
	@Length(min=1, max=30, message="药库名称长度必须介于 1 和 30 之间")
	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
	@Length(min=1, max=60, message="药品编码长度必须介于 1 和 60 之间")
	public String getDrugId() {
		return drugId;
	}

	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}
	
	@Length(min=1, max=60, message="医保统一编码长度必须介于 1 和 60 之间")
	public String getYbtybm() {
		return ybtybm;
	}

	public void setYbtybm(String ybtybm) {
		this.ybtybm = ybtybm;
	}
	
	@Length(min=1, max=1, message="是否基药长度必须介于 1 和 1 之间")
	public String getIsjbyw() {
		return isjbyw;
	}

	public void setIsjbyw(String isjbyw) {
		this.isjbyw = isjbyw;
	}
	
	@Length(min=1, max=1, message="是否抗生素药品长度必须介于 1 和 1 之间")
	public String getKss() {
		return kss;
	}

	public void setKss(String kss) {
		this.kss = kss;
	}
	
	@Length(min=0, max=6, message="中药使用类别代码长度必须介于 0 和 6 之间")
	public String getCmedCode() {
		return cmedCode;
	}

	public void setCmedCode(String cmedCode) {
		this.cmedCode = cmedCode;
	}
	
	@Length(min=0, max=6, message="药物类型长度必须介于 0 和 6 之间")
	public String getDrugDetailType() {
		return drugDetailType;
	}

	public void setDrugDetailType(String drugDetailType) {
		this.drugDetailType = drugDetailType;
	}
	
	@Length(min=0, max=6, message="药物剂型代码长度必须介于 0 和 6 之间")
	public String getDoseCode() {
		return doseCode;
	}

	public void setDoseCode(String doseCode) {
		this.doseCode = doseCode;
	}
	
	@Length(min=1, max=100, message="药品名称长度必须介于 1 和 100 之间")
	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	
	@Length(min=1, max=100, message="药品规格长度必须介于 1 和 100 之间")
	public String getDrugSpec() {
		return drugSpec;
	}

	public void setDrugSpec(String drugSpec) {
		this.drugSpec = drugSpec;
	}
	
	@Length(min=1, max=20, message="大包装单位长度必须介于 1 和 20 之间")
	public String getPackageUnit() {
		return packageUnit;
	}

	public void setPackageUnit(String packageUnit) {
		this.packageUnit = packageUnit;
	}
	
	public String getPackageQty() {
		return packageQty;
	}

	public void setPackageQty(String packageQty) {
		this.packageQty = packageQty;
	}
	
	@Length(min=1, max=20, message="小包装单位长度必须介于 1 和 20 之间")
	public String getSalesUnit() {
		return salesUnit;
	}

	public void setSalesUnit(String salesUnit) {
		this.salesUnit = salesUnit;
	}
	
	public String getSalesQty() {
		return salesQty;
	}

	public void setSalesQty(String salesQty) {
		this.salesQty = salesQty;
	}
	
	public String getSalesRelation() {
		return salesRelation;
	}

	public void setSalesRelation(String salesRelation) {
		this.salesRelation = salesRelation;
	}
	
	@Length(min=0, max=100, message="供货商名称长度必须介于 0 和 100 之间")
	public String getProducerName() {
		return producerName;
	}

	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}
	
	public String getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(String costPrice) {
		this.costPrice = costPrice;
	}
	
	public String getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}
	
	public String getCostAmt() {
		return costAmt;
	}

	public void setCostAmt(String costAmt) {
		this.costAmt = costAmt;
	}
	
	public String getSaleAmt() {
		return saleAmt;
	}

	public void setSaleAmt(String saleAmt) {
		this.saleAmt = saleAmt;
	}
	
	@Length(min=1, max=30, message="生产批号长度必须介于 1 和 30 之间")
	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	@Length(min=0, max=100, message="药品生产企业长度必须介于 0 和 100 之间")
	public String getSdrugmanufacturers() {
		return sdrugmanufacturers;
	}

	public void setSdrugmanufacturers(String sdrugmanufacturers) {
		this.sdrugmanufacturers = sdrugmanufacturers;
	}
	
	@Length(min=0, max=100, message="药品产地长度必须介于 0 和 100 之间")
	public String getSdrughabitat() {
		return sdrughabitat;
	}

	public void setSdrughabitat(String sdrughabitat) {
		this.sdrughabitat = sdrughabitat;
	}
	
	@Length(min=0, max=50, message="批准文号长度必须介于 0 和 50 之间")
	public String getSratificationno() {
		return sratificationno;
	}

	public void setSratificationno(String sratificationno) {
		this.sratificationno = sratificationno;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="生产日期不能为空")
	public Date getDdateproduce() {
		return ddateproduce;
	}

	public void setDdateproduce(Date ddateproduce) {
		this.ddateproduce = ddateproduce;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="失效日期不能为空")
	public Date getExpiry() {
		return expiry;
	}

	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}
	
	@Length(min=1, max=100, message="盘点库房长度必须介于 1 和 100 之间")
	public String getInventorystorage() {
		return inventorystorage;
	}

	public void setInventorystorage(String inventorystorage) {
		this.inventorystorage = inventorystorage;
	}
	
	@Length(min=1, max=11, message="库存数量长度必须介于 1 和 11 之间")
	public String getStoragenumber() {
		return storagenumber;
	}

	public void setStoragenumber(String storagenumber) {
		this.storagenumber = storagenumber;
	}
	
	@Length(min=1, max=11, message="退货数量长度必须介于 1 和 11 之间")
	public String getInventorynumber() {
		return inventorynumber;
	}

	public void setInventorynumber(String inventorynumber) {
		this.inventorynumber = inventorynumber;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getThrq() {
		return thrq;
	}

	public void setThrq(Date thrq) {
		this.thrq = thrq;
	}
	
	@Length(min=0, max=200, message="退货原因长度必须介于 0 和 200 之间")
	public String getThyy() {
		return thyy;
	}

	public void setThyy(String thyy) {
		this.thyy = thyy;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getScrq() {
		return scrq;
	}

	public void setScrq(Date scrq) {
		this.scrq = scrq;
	}
	
}