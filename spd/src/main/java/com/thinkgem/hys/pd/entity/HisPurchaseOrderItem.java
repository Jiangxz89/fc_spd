/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * his接口过来的采购订单详情Entity
 * @author jiangxz
 * @version 2019-05-06
 */
public class HisPurchaseOrderItem extends DataEntity<HisPurchaseOrderItem> {
	
	private static final long serialVersionUID = 1L;
	private String pId;		// 主表ID
	private String drugId;		// 药品编码
	private String ybtybm;		// 医保统一编码
	private String isjbyw;		// 是否基药
	private String kss;		// 是否抗生素药品
	private String cmedCode;		// 中药使用类别代码
	private String drugType;		// 药物类型
	private String doseCode;		// 药物剂型代码
	private String drugName;		// 药品名称
	private String drugSpec;		// 药品规格
	private String packageUnit;		// 大包装单位
	private String packageQty;		// 大包装数量
	private String salesUnit;		// 小包装单位
	private String salesQty;		// 小包装数量
	private String putQty;		// 采购数量
	private String drugManft;		// 药品生产企业
	private String drugHabitat;		// 药品产地
	private String producerName;		// 供货商名称
	private int synFlag ;   //同步标志，0-未同步；1-已同步

	public HisPurchaseOrderItem() {
		super();
		this.synFlag = MinaGlobalConstants.SYN_NONE;
	}

	public HisPurchaseOrderItem(String id){
		super(id);
	}

	@Length(min=1, max=64, message="主表ID长度必须介于 1 和 64 之间")
	public String getPId() {
		return pId;
	}

	public void setPId(String pId) {
		this.pId = pId;
	}
	
	@Length(min=0, max=64, message="药品编码长度必须介于 0 和 64 之间")
	public String getDrugId() {
		return drugId;
	}

	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}
	
	@Length(min=0, max=64, message="医保统一编码长度必须介于 0 和 64 之间")
	public String getYbtybm() {
		return ybtybm;
	}

	public void setYbtybm(String ybtybm) {
		this.ybtybm = ybtybm;
	}
	
	@Length(min=0, max=32, message="是否基药长度必须介于 0 和 32 之间")
	public String getIsjbyw() {
		return isjbyw;
	}

	public void setIsjbyw(String isjbyw) {
		this.isjbyw = isjbyw;
	}
	
	@Length(min=0, max=32, message="是否抗生素药品长度必须介于 0 和 32 之间")
	public String getKss() {
		return kss;
	}

	public void setKss(String kss) {
		this.kss = kss;
	}
	
	@Length(min=0, max=64, message="中药使用类别代码长度必须介于 0 和 64 之间")
	public String getCmedCode() {
		return cmedCode;
	}

	public void setCmedCode(String cmedCode) {
		this.cmedCode = cmedCode;
	}
	
	@Length(min=0, max=32, message="药物类型长度必须介于 0 和 32 之间")
	public String getDrugType() {
		return drugType;
	}

	public void setDrugType(String drugType) {
		this.drugType = drugType;
	}
	
	@Length(min=0, max=64, message="药物剂型代码长度必须介于 0 和 64 之间")
	public String getDoseCode() {
		return doseCode;
	}

	public void setDoseCode(String doseCode) {
		this.doseCode = doseCode;
	}
	
	@Length(min=0, max=255, message="药品名称长度必须介于 0 和 255 之间")
	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	
	@Length(min=0, max=64, message="药品规格长度必须介于 0 和 64 之间")
	public String getDrugSpec() {
		return drugSpec;
	}

	public void setDrugSpec(String drugSpec) {
		this.drugSpec = drugSpec;
	}
	
	@Length(min=0, max=32, message="大包装单位长度必须介于 0 和 32 之间")
	public String getPackageUnit() {
		return packageUnit;
	}

	public void setPackageUnit(String packageUnit) {
		this.packageUnit = packageUnit;
	}
	
	@Length(min=0, max=8, message="大包装数量长度必须介于 0 和 8 之间")
	public String getPackageQty() {
		return packageQty;
	}

	public void setPackageQty(String packageQty) {
		this.packageQty = packageQty;
	}
	
	@Length(min=0, max=32, message="小包装单位长度必须介于 0 和 32 之间")
	public String getSalesUnit() {
		return salesUnit;
	}

	public void setSalesUnit(String salesUnit) {
		this.salesUnit = salesUnit;
	}
	
	@Length(min=0, max=8, message="小包装数量长度必须介于 0 和 8 之间")
	public String getSalesQty() {
		return salesQty;
	}

	public void setSalesQty(String salesQty) {
		this.salesQty = salesQty;
	}
	
	@Length(min=0, max=8, message="采购数量长度必须介于 0 和 8 之间")
	public String getPutQty() {
		return putQty;
	}

	public void setPutQty(String putQty) {
		this.putQty = putQty;
	}
	
	@Length(min=0, max=255, message="药品生产企业长度必须介于 0 和 255 之间")
	public String getDrugManft() {
		return drugManft;
	}

	public void setDrugManft(String drugManft) {
		this.drugManft = drugManft;
	}
	
	@Length(min=0, max=255, message="药品产地长度必须介于 0 和 255 之间")
	public String getDrugHabitat() {
		return drugHabitat;
	}

	public void setDrugHabitat(String drugHabitat) {
		this.drugHabitat = drugHabitat;
	}
	
	@Length(min=0, max=255, message="供货商名称长度必须介于 0 和 255 之间")
	public String getProducerName() {
		return producerName;
	}

	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}

	public int getSynFlag() {
		return synFlag;
	}

	public void setSynFlag(int synFlag) {
		this.synFlag = synFlag;
	}
}