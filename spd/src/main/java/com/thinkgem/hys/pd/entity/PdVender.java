/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 生产厂家表Entity
 * @author sutianqi
 * @version 2018-03-07
 */
public class PdVender extends DataEntity<PdVender> {
	
	private static final long serialVersionUID = 1L;
	private String id;		// _id
	@ExcelField(title = "生产厂家名称",  sort = 1)
	private String name;		// 厂家名称
	private String pinyin;   //拼音简码
	private int synFlag ;   //同步标志，0-未同步；1-已同步
	
	private String licenceName1;	//证件名称
	private String licenceNum1;		//证件号码
	private String licenceDate1;	//有效期至
	private String licenceSite1;	//图片地址
	
	private String licenceName2;	//证件名称
	private String licenceNum2;		//证件号码
	private String licenceDate2;	//有效期至
	private String licenceSite2;	//图片地址

	private String licenceName3;	//证件名称
	private String licenceNum3;		//证件号码
	private String licenceDate3;	//有效期至
	private String licenceSite3;	//图片地址
	
	private String licenceName4;	//证件名称
	private String licenceNum4;		//证件号码
	private String licenceDate4;	//有效期至
	private String licenceSite4;	//图片地址
	
	private String licenceName5;	//证件名称
	private String licenceNum5;		//证件号码
	private String licenceDate5;	//有效期至
	private String licenceSite5;	//图片地址
	
	private String licenceName6;	//证件名称
	private String licenceNum6;		//证件号码
	private String licenceDate6;	//有效期至
	private String licenceSite6;	//图片地址
	
	private String licenceName7;	//证件名称
	private String licenceNum7;		//证件号码
	private String licenceDate7;	//有效期至
	private String licenceSite7;	//图片地址
	
	private String licenceName8;	//证件名称
	private String licenceNum8;		//证件号码
	private String licenceDate8;	//有效期至
	private String licenceSite8;	//图片地址
	
	private String licenceName9;	//证件名称
	private String licenceNum9;		//证件号码
	private String licenceDate9;	//有效期至
	private String licenceSite9;	//图片地址
	
	private String licenceName10;	//证件名称
	private String licenceNum10;		//证件号码
	private String licenceDate10;	//有效期至
	private String licenceSite10;	//图片地址
	
	private String licenceName11;	//证件名称
	private String licenceNum11;		//证件号码
	private String licenceDate11;	//有效期至
	private String licenceSite11;	//图片地址
	
	private String licenceName12;	//证件名称
	private String licenceNum12;		//证件号码
	private String licenceDate12;	//有效期至
	private String licenceSite12;	//图片地址

	private String avlType;   //有效期标识
    private String nameAndpinyin;   //用于下拉列表

	public String getAvlType() {
		return avlType;
	}

	public void setAvlType(String avlType) {
		this.avlType = avlType;
	}

	public PdVender() {
		super();
	}

	public PdVender(String id){
		super(id);
	}

	@Length(min=1, max=64, message="_id长度必须介于 1 和 64 之间")
	public String getId() {
		return id;
	}

	public void setId(String Id) {
		this.id = Id;
	}

    public String getNameAndpinyin() {
        return nameAndpinyin;
    }

    public void setNameAndpinyin(String nameAndpinyin) {
        this.nameAndpinyin = nameAndpinyin;
    }

    public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	@Length(min=0, max=100, message="厂家名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLicenceName1() {
		return licenceName1;
	}

	public void setLicenceName1(String licenceName1) {
		this.licenceName1 = licenceName1;
	}

	public String getLicenceNum1() {
		return licenceNum1;
	}

	public void setLicenceNum1(String licenceNum1) {
		this.licenceNum1 = licenceNum1;
	}

	public String getLicenceDate1() {
		return licenceDate1;
	}

	public void setLicenceDate1(String licenceDate1) {
		this.licenceDate1 = licenceDate1;
	}

	public String getLicenceSite1() {
		return licenceSite1;
	}

	public void setLicenceSite1(String licenceSite1) {
		this.licenceSite1 = licenceSite1;
	}

	public String getLicenceName2() {
		return licenceName2;
	}

	public void setLicenceName2(String licenceName2) {
		this.licenceName2 = licenceName2;
	}

	public String getLicenceNum2() {
		return licenceNum2;
	}

	public void setLicenceNum2(String licenceNum2) {
		this.licenceNum2 = licenceNum2;
	}

	public String getLicenceDate2() {
		return licenceDate2;
	}

	public void setLicenceDate2(String licenceDate2) {
		this.licenceDate2 = licenceDate2;
	}

	public String getLicenceSite2() {
		return licenceSite2;
	}

	public void setLicenceSite2(String licenceSite2) {
		this.licenceSite2 = licenceSite2;
	}

	public String getLicenceName3() {
		return licenceName3;
	}

	public void setLicenceName3(String licenceName3) {
		this.licenceName3 = licenceName3;
	}

	public String getLicenceNum3() {
		return licenceNum3;
	}

	public void setLicenceNum3(String licenceNum3) {
		this.licenceNum3 = licenceNum3;
	}

	public String getLicenceDate3() {
		return licenceDate3;
	}

	public void setLicenceDate3(String licenceDate3) {
		this.licenceDate3 = licenceDate3;
	}

	public String getLicenceSite3() {
		return licenceSite3;
	}

	public void setLicenceSite3(String licenceSite3) {
		this.licenceSite3 = licenceSite3;
	}

	public String getLicenceName4() {
		return licenceName4;
	}

	public void setLicenceName4(String licenceName4) {
		this.licenceName4 = licenceName4;
	}

	public String getLicenceNum4() {
		return licenceNum4;
	}

	public void setLicenceNum4(String licenceNum4) {
		this.licenceNum4 = licenceNum4;
	}

	public String getLicenceDate4() {
		return licenceDate4;
	}

	public void setLicenceDate4(String licenceDate4) {
		this.licenceDate4 = licenceDate4;
	}

	public String getLicenceSite4() {
		return licenceSite4;
	}

	public void setLicenceSite4(String licenceSite4) {
		this.licenceSite4 = licenceSite4;
	}

	public String getLicenceName5() {
		return licenceName5;
	}

	public void setLicenceName5(String licenceName5) {
		this.licenceName5 = licenceName5;
	}

	public String getLicenceNum5() {
		return licenceNum5;
	}

	public void setLicenceNum5(String licenceNum5) {
		this.licenceNum5 = licenceNum5;
	}

	public String getLicenceDate5() {
		return licenceDate5;
	}

	public void setLicenceDate5(String licenceDate5) {
		this.licenceDate5 = licenceDate5;
	}

	public String getLicenceSite5() {
		return licenceSite5;
	}

	public void setLicenceSite5(String licenceSite5) {
		this.licenceSite5 = licenceSite5;
	}

	public String getLicenceName6() {
		return licenceName6;
	}

	public void setLicenceName6(String licenceName6) {
		this.licenceName6 = licenceName6;
	}

	public String getLicenceNum6() {
		return licenceNum6;
	}

	public void setLicenceNum6(String licenceNum6) {
		this.licenceNum6 = licenceNum6;
	}

	public String getLicenceDate6() {
		return licenceDate6;
	}

	public void setLicenceDate6(String licenceDate6) {
		this.licenceDate6 = licenceDate6;
	}

	public String getLicenceSite6() {
		return licenceSite6;
	}

	public void setLicenceSite6(String licenceSite6) {
		this.licenceSite6 = licenceSite6;
	}

	public String getLicenceName7() {
		return licenceName7;
	}

	public void setLicenceName7(String licenceName7) {
		this.licenceName7 = licenceName7;
	}

	public String getLicenceNum7() {
		return licenceNum7;
	}

	public void setLicenceNum7(String licenceNum7) {
		this.licenceNum7 = licenceNum7;
	}

	public String getLicenceDate7() {
		return licenceDate7;
	}

	public void setLicenceDate7(String licenceDate7) {
		this.licenceDate7 = licenceDate7;
	}

	public String getLicenceSite7() {
		return licenceSite7;
	}

	public void setLicenceSite7(String licenceSite7) {
		this.licenceSite7 = licenceSite7;
	}

	public String getLicenceName8() {
		return licenceName8;
	}

	public void setLicenceName8(String licenceName8) {
		this.licenceName8 = licenceName8;
	}

	public String getLicenceNum8() {
		return licenceNum8;
	}

	public void setLicenceNum8(String licenceNum8) {
		this.licenceNum8 = licenceNum8;
	}

	public String getLicenceDate8() {
		return licenceDate8;
	}

	public void setLicenceDate8(String licenceDate8) {
		this.licenceDate8 = licenceDate8;
	}

	public String getLicenceSite8() {
		return licenceSite8;
	}

	public void setLicenceSite8(String licenceSite8) {
		this.licenceSite8 = licenceSite8;
	}

	public String getLicenceName9() {
		return licenceName9;
	}

	public void setLicenceName9(String licenceName9) {
		this.licenceName9 = licenceName9;
	}

	public String getLicenceNum9() {
		return licenceNum9;
	}

	public void setLicenceNum9(String licenceNum9) {
		this.licenceNum9 = licenceNum9;
	}

	public String getLicenceDate9() {
		return licenceDate9;
	}

	public void setLicenceDate9(String licenceDate9) {
		this.licenceDate9 = licenceDate9;
	}

	public String getLicenceSite9() {
		return licenceSite9;
	}

	public void setLicenceSite9(String licenceSite9) {
		this.licenceSite9 = licenceSite9;
	}

	public String getLicenceName10() {
		return licenceName10;
	}

	public void setLicenceName10(String licenceName10) {
		this.licenceName10 = licenceName10;
	}

	public String getLicenceNum10() {
		return licenceNum10;
	}

	public void setLicenceNum10(String licenceNum10) {
		this.licenceNum10 = licenceNum10;
	}

	public String getLicenceDate10() {
		return licenceDate10;
	}

	public void setLicenceDate10(String licenceDate10) {
		this.licenceDate10 = licenceDate10;
	}

	public String getLicenceSite10() {
		return licenceSite10;
	}

	public void setLicenceSite10(String licenceSite10) {
		this.licenceSite10 = licenceSite10;
	}

	public String getLicenceName11() {
		return licenceName11;
	}

	public void setLicenceName11(String licenceName11) {
		this.licenceName11 = licenceName11;
	}

	public String getLicenceNum11() {
		return licenceNum11;
	}

	public void setLicenceNum11(String licenceNum11) {
		this.licenceNum11 = licenceNum11;
	}

	public String getLicenceDate11() {
		return licenceDate11;
	}

	public void setLicenceDate11(String licenceDate11) {
		this.licenceDate11 = licenceDate11;
	}

	public String getLicenceSite11() {
		return licenceSite11;
	}

	public void setLicenceSite11(String licenceSite11) {
		this.licenceSite11 = licenceSite11;
	}

	public String getLicenceName12() {
		return licenceName12;
	}

	public void setLicenceName12(String licenceName12) {
		this.licenceName12 = licenceName12;
	}

	public String getLicenceNum12() {
		return licenceNum12;
	}

	public void setLicenceNum12(String licenceNum12) {
		this.licenceNum12 = licenceNum12;
	}

	public String getLicenceDate12() {
		return licenceDate12;
	}

	public void setLicenceDate12(String licenceDate12) {
		this.licenceDate12 = licenceDate12;
	}

	public String getLicenceSite12() {
		return licenceSite12;
	}

	public void setLicenceSite12(String licenceSite12) {
		this.licenceSite12 = licenceSite12;
	}

	public int getSynFlag() {
		return synFlag;
	}

	public void setSynFlag(int synFlag) {
		this.synFlag = synFlag;
	}
}