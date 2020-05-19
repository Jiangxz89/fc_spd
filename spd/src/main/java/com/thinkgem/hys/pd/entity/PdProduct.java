/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 产品表Entity
 * @author sutianqi
 * @version 2018-03-07
 */
public class PdProduct extends DataEntity<PdProduct> {

	private static final long serialVersionUID = 1L;
	@ExcelField(title = "产品编码",  sort = 1)
	private String number;		// 产品编码
	@ExcelField(title = "产品名称",  sort = 2)
	private String name;		// 产品名称
	private String spm;		// 首拼码

	private String otherName;		// 产品别名   add by jiangxz 20190927
	private String otherSpm;		// 别名首拼码 add by jiangxz 20190927
	@ExcelField(title = "规格",  sort = 3)
	private String spec;		// 规格
	@ExcelField(title = "型号",  sort = 4)
	private String version;		// 型号
	@ExcelField(title = "单位",  sort = 5)
	private String unit;		// 产品单位
	@ExcelField(title = "产品权限",  sort = 6)
	private String power;		// 产品权限字典：公共产品自有产品
	@ExcelField(title = "产品类型",  sort = 7)
	private String type;		// 产品类型词典：低值耗材 0高值耗材 1
	private String categoryId;		// 分类ID
	@ExcelField(title = "产品分类",  sort = 8)
	private String categoryName;	// 分类名称
	private String groupId;		// 组别ID
	@ExcelField(title = "产品组别",  sort = 9)
	private String groupName;	// 组别名称
	private String venderId;		// 生产厂家id
	@ExcelField(title = "生产厂家",  sort = 10)
	private String venderName;		// 生产厂家名称
	private String brandId;		//品牌id
	private String brandName;	//品牌名称
	@ExcelField(title = "产品进价",  sort = 11)
	private Double pruPrice;		// 产品进价
	@ExcelField(title = "供应商",  sort = 13)
	private String supplierId; //供应商id
	@ExcelField(title = "产品出价",  sort = 12)
	private Double sellingPrice;		// 出价
	private String chargeCode;		// 收费项目代码
	@ExcelField(title = "注册证号",  sort = 14)
	private String registration;		// 注册证
	@ExcelField(title = "是否计费",  sort = 15)
	private String isCharge;//是否计费
	@ExcelField(title = "备注",  sort = 16)

	private String description;		// 产品描述
	@ExcelField(title = "交易平台产品ID",  sort = 17)
	private String tpProdId;		// 收费项目代码
	private String imgAuthNumber;	//产品授权书证照号码
	private Date imgAuthDate;		//产品授权书证照有效期
	private String imgAuthSite;		// 产品授权书图片地址
	private String imgRegisterNumber1;	//
	private Date imgRegisterDate1;		//
	private String imgRegisterSite1;		// 产品注册证图片1
	private String imgRegisterNumber2;	//
	private Date imgRegisterDate2;		//
	private String imgRegisterSite2;		// 产品注册证图片2
	private String imgRegisterNumber3;	//
	private Date imgRegisterDate3;		//
	private String imgRegisterSite3;		// 产品注册证图片3
	private String imgLicenseNumber1;
	private Date imgLicenseDate1;
	private String imgLicenseSite1;		// 供应商营业执照图片地址
	private String imgLicenseNumber2;
	private Date imgLicenseDate2;
	private String imgLicenseSite2;		// 供应商经营许可证图片地址
	private String imgLicenseNumber3;
	private Date imgLicenseDate3;
	private String imgLicenseSite3;		// 生产企业营业执照图片地址
	private String imgLicenseNumber4;
	private Date imgLicenseDate4;
	private String imgLicenseSite4;		// 生产企业生产许可证图片地址
	private String imgProduct1;		// 产品图片正面
	private String imgProduct2;		// 产品图片背面
	private String imgProduct3;		// 产品图片侧面
	private String isUrgent;		//是否紧急
	private Integer urgentPurCount;		// 紧急采购量默认值 0为零时为非紧急采购产品
	private Integer surplusPurCount;		// 剩余采购量

	private String storeroomId;//库房
	private String stockNum;//库存
	private String barCode;//条码
	private String batchNo;//批号
	private String validDate;//有效期
	private int productNumber;//产品数量
	private String applyNum;//申领数量
	private String selfStoreroomId;//自身库房id
	private String selfStockNum;//自身库存

	private int synFlag ;   //同步标志，0-未同步；1-已同步

	//--temp
	private int rows;//excel表格行号
	private String rowsKey;//表格行号，用于传参

	private String encodingRule;//绑定编码规则2019年4月26日09:08:09
	private String encodingRuleName;//绑定编码规则2019年4月26日09:08:09


	//查询显示优化
	private String categoryNameShow; //分类
	private String groupNameShow;//组别
	private String venderNameShow;//生产厂家
	private String supplierNameShow;//供应商

	private String isLimitDown;  // add by jiangxz 20190904 用于查询库存下限产品

	public String getTpProdId() {
		return tpProdId;
	}

	public void setTpProdId(String tpProdId) {
		this.tpProdId = tpProdId;
	}

	public String getIsLimitDown() {
		return isLimitDown;
	}

	public void setIsLimitDown(String isLimitDown) {
		this.isLimitDown = isLimitDown;
	}

	public int getSynFlag() {
		return synFlag;
	}

	public void setSynFlag(int synFlag) {
		this.synFlag = synFlag;
	}

	public String getRowsKey() {
		return rowsKey;
	}

	public void setRowsKey(String rowsKey) {
		this.rowsKey = rowsKey;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getIsUrgent() {
		return isUrgent;
	}

	public void setIsUrgent(String isUrgent) {
		this.isUrgent = isUrgent;
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

	public String getVenderName() {
		return venderName;
	}

	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}


	public PdProduct() {
		super();
	}

	public String getOtherName() {
		return otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}

	public String getOtherSpm() {
		return otherSpm;
	}

	public void setOtherSpm(String otherSpm) {
		this.otherSpm = otherSpm;
	}

	@Length(min=0, max=100, message="产品编码长度必须介于 0 和 100 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Length(min=0, max=100, message="产品名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Length(min=0, max=50, message="首拼码长度必须介于 0 和 50 之间")
	public String getSpm() {
		return spm;
	}

	public void setSpm(String spm) {
		this.spm = spm;
	}

	@Length(min=0, max=100, message="规格长度必须介于 0 和 100 之间")
	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	@Length(min=0, max=100, message="型号长度必须介于 0 和 100 之间")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Length(min=0, max=50, message="产品单位长度必须介于 0 和 50 之间")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Length(min=0, max=50, message="产品权限字典：公共产品自有产品长度必须介于 0 和 50 之间")
	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	@Length(min=0, max=50, message="产品类型词典：低值耗材 0高值耗材 1长度必须介于 0 和 50 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Length(min=0, max=64, message="分类ID长度必须介于 0 和 64 之间")
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	@Length(min=0, max=64, message="组别ID长度必须介于 0 和 64 之间")
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Length(min=0, max=64, message="生产厂家id长度必须介于 0 和 64 之间")
	public String getVenderId() {
		return venderId;
	}

	public void setVenderId(String venderId) {
		this.venderId = venderId;
	}



	public Double getPruPrice() {
		return pruPrice;
	}

	public void setPruPrice(Double pruPrice) {
		this.pruPrice = pruPrice;
	}

	public Double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	@Length(min=0, max=100, message="收费项目代码长度必须介于 0 和 100 之间")
	public String getChargeCode() {
		return chargeCode;
	}

	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}

	@Length(min=0, max=200, message="注册证长度必须介于 0 和 200 之间")
	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	@Length(min=0, max=1000, message="产品描述长度必须介于 0 和 1000 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Length(min=0, max=200, message="产品授权书图片地址长度必须介于 0 和 200 之间")
	public String getImgAuthSite() {
		return imgAuthSite;
	}

	public void setImgAuthSite(String imgAuthSite) {
		this.imgAuthSite = imgAuthSite;
	}


	@Length(min=0, max=200, message="供应商营业执照图片地址长度必须介于 0 和 200 之间")
	public String getImgLicenseSite1() {
		return imgLicenseSite1;
	}

	public void setImgLicenseSite1(String imgLicenseSite1) {
		this.imgLicenseSite1 = imgLicenseSite1;
	}

	@Length(min=0, max=200, message="供应商经营许可证图片地址长度必须介于 0 和 200 之间")
	public String getImgLicenseSite2() {
		return imgLicenseSite2;
	}

	public void setImgLicenseSite2(String imgLicenseSite2) {
		this.imgLicenseSite2 = imgLicenseSite2;
	}

	@Length(min=0, max=200, message="生产企业营业执照图片地址长度必须介于 0 和 200 之间")
	public String getImgLicenseSite3() {
		return imgLicenseSite3;
	}

	public void setImgLicenseSite3(String imgLicenseSite3) {
		this.imgLicenseSite3 = imgLicenseSite3;
	}

	@Length(min=0, max=200, message="生产企业生产许可证图片地址长度必须介于 0 和 200 之间")
	public String getImgLicenseSite4() {
		return imgLicenseSite4;
	}

	public void setImgLicenseSite4(String imgLicenseSite4) {
		this.imgLicenseSite4 = imgLicenseSite4;
	}

	public String getImgProduct1() {
		return imgProduct1;
	}

	public void setImgProduct1(String imgProduct1) {
		this.imgProduct1 = imgProduct1;
	}

	public String getImgProduct2() {
		return imgProduct2;
	}

	public void setImgProduct2(String imgProduct2) {
		this.imgProduct2 = imgProduct2;
	}

	public String getImgProduct3() {
		return imgProduct3;
	}

	public void setImgProduct3(String imgProduct3) {
		this.imgProduct3 = imgProduct3;
	}

	public Integer getUrgentPurCount() {
		return urgentPurCount;
	}

	public void setUrgentPurCount(Integer urgentPurCount) {
		this.urgentPurCount = urgentPurCount;
	}

	public Integer getSurplusPurCount() {
		return surplusPurCount;
	}

	public void setSurplusPurCount(Integer surplusPurCount) {
		this.surplusPurCount = surplusPurCount;
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

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getValidDate() {
		return validDate;
	}

	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}

	public int getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(int productNumber) {
		this.productNumber = productNumber;
	}

	public String getApplyNum() {
		return applyNum;
	}

	public void setApplyNum(String applyNum) {
		this.applyNum = applyNum;
	}

	public String getImgAuthNumber() {
		return imgAuthNumber;
	}

	public void setImgAuthNumber(String imgAuthNumber) {
		this.imgAuthNumber = imgAuthNumber;
	}


	public String getImgRegisterNumber1() {
		return imgRegisterNumber1;
	}

	public void setImgRegisterNumber1(String imgRegisterNumber1) {
		this.imgRegisterNumber1 = imgRegisterNumber1;
	}



	public String getImgRegisterSite1() {
		return imgRegisterSite1;
	}

	public void setImgRegisterSite1(String imgRegisterSite1) {
		this.imgRegisterSite1 = imgRegisterSite1;
	}

	public String getImgRegisterNumber2() {
		return imgRegisterNumber2;
	}

	public void setImgRegisterNumber2(String imgRegisterNumber2) {
		this.imgRegisterNumber2 = imgRegisterNumber2;
	}



	public String getImgRegisterSite2() {
		return imgRegisterSite2;
	}

	public void setImgRegisterSite2(String imgRegisterSite2) {
		this.imgRegisterSite2 = imgRegisterSite2;
	}

	public String getImgRegisterNumber3() {
		return imgRegisterNumber3;
	}

	public void setImgRegisterNumber3(String imgRegisterNumber3) {
		this.imgRegisterNumber3 = imgRegisterNumber3;
	}



	public String getImgRegisterSite3() {
		return imgRegisterSite3;
	}

	public void setImgRegisterSite3(String imgRegisterSite3) {
		this.imgRegisterSite3 = imgRegisterSite3;
	}

	public String getImgLicenseNumber1() {
		return imgLicenseNumber1;
	}

	public void setImgLicenseNumber1(String imgLicenseNumber1) {
		this.imgLicenseNumber1 = imgLicenseNumber1;
	}


	public String getImgLicenseNumber2() {
		return imgLicenseNumber2;
	}

	public void setImgLicenseNumber2(String imgLicenseNumber2) {
		this.imgLicenseNumber2 = imgLicenseNumber2;
	}



	public String getImgLicenseNumber3() {
		return imgLicenseNumber3;
	}

	public void setImgLicenseNumber3(String imgLicenseNumber3) {
		this.imgLicenseNumber3 = imgLicenseNumber3;
	}


	public String getImgLicenseNumber4() {
		return imgLicenseNumber4;
	}

	public void setImgLicenseNumber4(String imgLicenseNumber4) {
		this.imgLicenseNumber4 = imgLicenseNumber4;
	}

	public Date getImgAuthDate() {
		return imgAuthDate;
	}

	public void setImgAuthDate(Date imgAuthDate) {
		this.imgAuthDate = imgAuthDate;
	}

	public Date getImgRegisterDate1() {
		return imgRegisterDate1;
	}

	public void setImgRegisterDate1(Date imgRegisterDate1) {
		this.imgRegisterDate1 = imgRegisterDate1;
	}

	public Date getImgRegisterDate2() {
		return imgRegisterDate2;
	}

	public void setImgRegisterDate2(Date imgRegisterDate2) {
		this.imgRegisterDate2 = imgRegisterDate2;
	}

	public Date getImgRegisterDate3() {
		return imgRegisterDate3;
	}

	public void setImgRegisterDate3(Date imgRegisterDate3) {
		this.imgRegisterDate3 = imgRegisterDate3;
	}

	public Date getImgLicenseDate1() {
		return imgLicenseDate1;
	}

	public void setImgLicenseDate1(Date imgLicenseDate1) {
		this.imgLicenseDate1 = imgLicenseDate1;
	}

	public Date getImgLicenseDate2() {
		return imgLicenseDate2;
	}

	public void setImgLicenseDate2(Date imgLicenseDate2) {
		this.imgLicenseDate2 = imgLicenseDate2;
	}

	public Date getImgLicenseDate3() {
		return imgLicenseDate3;
	}

	public void setImgLicenseDate3(Date imgLicenseDate3) {
		this.imgLicenseDate3 = imgLicenseDate3;
	}

	public Date getImgLicenseDate4() {
		return imgLicenseDate4;
	}

	public void setImgLicenseDate4(Date imgLicenseDate4) {
		this.imgLicenseDate4 = imgLicenseDate4;
	}

	public String getSelfStoreroomId() {
		return selfStoreroomId;
	}

	public void setSelfStoreroomId(String selfStoreroomId) {
		this.selfStoreroomId = selfStoreroomId;
	}

	public String getSelfStockNum() {
		return selfStockNum;
	}

	public void setSelfStockNum(String selfStockNum) {
		this.selfStockNum = selfStockNum;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getEncodingRule() {
		return encodingRule;
	}

	public void setEncodingRule(String encodingRule) {
		this.encodingRule = encodingRule;
	}

	public String getEncodingRuleName() {
		return encodingRuleName;
	}

	public void setEncodingRuleName(String encodingRuleName) {
		this.encodingRuleName = encodingRuleName;
	}

	public String getCategoryNameShow() {
		return categoryNameShow;
	}

	public void setCategoryNameShow(String categoryNameShow) {
		this.categoryNameShow = categoryNameShow;
	}

	public String getGroupNameShow() {
		return groupNameShow;
	}

	public void setGroupNameShow(String groupNameShow) {
		this.groupNameShow = groupNameShow;
	}

	public String getVenderNameShow() {
		return venderNameShow;
	}

	public void setVenderNameShow(String venderNameShow) {
		this.venderNameShow = venderNameShow;
	}

	public String getSupplierNameShow() {
		return supplierNameShow;
	}

	public void setSupplierNameShow(String supplierNameShow) {
		this.supplierNameShow = supplierNameShow;
	}

	public String getIsCharge() {
		return isCharge;
	}

	public void setIsCharge(String isCharge) {
		this.isCharge = isCharge;
	}
}