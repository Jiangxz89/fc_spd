package com.thinkgem.hys.pd.entity.bo;

import java.util.Date;

/**
 * 产品BO
 * @author jiangxz
 * @description
 * @date 2019-4-30
 */
public class PdProductBO {

    private static final long serialVersionUID = 1L;
    private String id;		// 产品编码
    private String number;		// 产品编码
    private String name;		// 产品名称
    private String spm;		// 首拼码
    private String spec;		// 规格
    private String version;		// 型号
    private String unit;		// 产品单位
    private String power;		// 产品权限字典：公共产品自有产品
    private String type;		// 产品类型词典：低值耗材 0高值耗材 1
    private String categoryId;		// 分类ID
    private String categoryName;	// 分类名称
    private String groupId;		// 组别ID
    private String groupName;	// 组别名称
    private String venderId;		// 生产厂家id
    private String venderName;		// 生产厂家名称
    private String brandId;		//品牌id
    private String brandName;	//品牌名称
    private Double pruPrice;		// 产品进价
    private String supplierId; //供应商id
    private Double sellingPrice;		// 出价
    private String chargeCode;		// 收费项目代码
    private String registration;		// 注册证
    private String description;		// 产品描述
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
    private String delFlag; 	// 删除标记（0：正常；1：删除；2：审核）
    private int synFlag ;   //同步标志，0-未同步；1-已同步

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpm() {
        return spm;
    }

    public void setSpm(String spm) {
        this.spm = spm;
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

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getVenderId() {
        return venderId;
    }

    public void setVenderId(String venderId) {
        this.venderId = venderId;
    }

    public String getVenderName() {
        return venderName;
    }

    public void setVenderName(String venderName) {
        this.venderName = venderName;
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

    public Double getPruPrice() {
        return pruPrice;
    }

    public void setPruPrice(Double pruPrice) {
        this.pruPrice = pruPrice;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getChargeCode() {
        return chargeCode;
    }

    public void setChargeCode(String chargeCode) {
        this.chargeCode = chargeCode;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgAuthNumber() {
        return imgAuthNumber;
    }

    public void setImgAuthNumber(String imgAuthNumber) {
        this.imgAuthNumber = imgAuthNumber;
    }

    public Date getImgAuthDate() {
        return imgAuthDate;
    }

    public void setImgAuthDate(Date imgAuthDate) {
        this.imgAuthDate = imgAuthDate;
    }

    public String getImgAuthSite() {
        return imgAuthSite;
    }

    public void setImgAuthSite(String imgAuthSite) {
        this.imgAuthSite = imgAuthSite;
    }

    public String getImgRegisterNumber1() {
        return imgRegisterNumber1;
    }

    public void setImgRegisterNumber1(String imgRegisterNumber1) {
        this.imgRegisterNumber1 = imgRegisterNumber1;
    }

    public Date getImgRegisterDate1() {
        return imgRegisterDate1;
    }

    public void setImgRegisterDate1(Date imgRegisterDate1) {
        this.imgRegisterDate1 = imgRegisterDate1;
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

    public Date getImgRegisterDate2() {
        return imgRegisterDate2;
    }

    public void setImgRegisterDate2(Date imgRegisterDate2) {
        this.imgRegisterDate2 = imgRegisterDate2;
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

    public Date getImgRegisterDate3() {
        return imgRegisterDate3;
    }

    public void setImgRegisterDate3(Date imgRegisterDate3) {
        this.imgRegisterDate3 = imgRegisterDate3;
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

    public Date getImgLicenseDate1() {
        return imgLicenseDate1;
    }

    public void setImgLicenseDate1(Date imgLicenseDate1) {
        this.imgLicenseDate1 = imgLicenseDate1;
    }

    public String getImgLicenseSite1() {
        return imgLicenseSite1;
    }

    public void setImgLicenseSite1(String imgLicenseSite1) {
        this.imgLicenseSite1 = imgLicenseSite1;
    }

    public String getImgLicenseNumber2() {
        return imgLicenseNumber2;
    }

    public void setImgLicenseNumber2(String imgLicenseNumber2) {
        this.imgLicenseNumber2 = imgLicenseNumber2;
    }

    public Date getImgLicenseDate2() {
        return imgLicenseDate2;
    }

    public void setImgLicenseDate2(Date imgLicenseDate2) {
        this.imgLicenseDate2 = imgLicenseDate2;
    }

    public String getImgLicenseSite2() {
        return imgLicenseSite2;
    }

    public void setImgLicenseSite2(String imgLicenseSite2) {
        this.imgLicenseSite2 = imgLicenseSite2;
    }

    public String getImgLicenseNumber3() {
        return imgLicenseNumber3;
    }

    public void setImgLicenseNumber3(String imgLicenseNumber3) {
        this.imgLicenseNumber3 = imgLicenseNumber3;
    }

    public Date getImgLicenseDate3() {
        return imgLicenseDate3;
    }

    public void setImgLicenseDate3(Date imgLicenseDate3) {
        this.imgLicenseDate3 = imgLicenseDate3;
    }

    public String getImgLicenseSite3() {
        return imgLicenseSite3;
    }

    public void setImgLicenseSite3(String imgLicenseSite3) {
        this.imgLicenseSite3 = imgLicenseSite3;
    }

    public String getImgLicenseNumber4() {
        return imgLicenseNumber4;
    }

    public void setImgLicenseNumber4(String imgLicenseNumber4) {
        this.imgLicenseNumber4 = imgLicenseNumber4;
    }

    public Date getImgLicenseDate4() {
        return imgLicenseDate4;
    }

    public void setImgLicenseDate4(Date imgLicenseDate4) {
        this.imgLicenseDate4 = imgLicenseDate4;
    }

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

    public String getIsUrgent() {
        return isUrgent;
    }

    public void setIsUrgent(String isUrgent) {
        this.isUrgent = isUrgent;
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

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public int getSynFlag() {
        return synFlag;
    }

    public void setSynFlag(int synFlag) {
        this.synFlag = synFlag;
    }
}
