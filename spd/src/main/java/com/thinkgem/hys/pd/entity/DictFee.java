/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 中心收费项目目录Entity
 * @author zxh
 * @version 2019-11-13
 */
public class DictFee extends DataEntity<DictFee> {
	
	private static final long serialVersionUID = 1L;
	private String feeid;		// 中心ID
	private String xname;		// 名称
	private String bname;		// 标准名称
	private String feenumber;		// 项目编码
	private String pycode;		// 拼音码
	private String wbcode;		// 五笔码
	private String cuscode;		// 自定义码
	private String unit;		// 单位
	private String price;		// 价格
	private String contain;		// 项目说明
	private String cwcontent;		// 除外内容
	private String explain;		// 解释说明
	private String mretype;		// 是否医保类型
	private String isstop;		// 是否停用
	private String modstaff;		// 修改人
	private String modstaffname;		// 修改人名
	private Date moddate;		// 修改时间
	private String sicode;		// 社保代码
	private String nhcode;		// 农合编码
	private String zxauditstatus;		// 状态
	private String zxauditor;		// 审核人
	private String zxauditorname;		// 审核人名
	private Date zxaudittime;		// 审核时间
	private String oneLevel;		// 一级价格
	private String twoLevel;		// 二级价格
	private String threeLevel;		// 三级价格
	
	public DictFee() {
		super();
	}

	public DictFee(String id){
		super(id);
	}

	@Length(min=1, max=11, message="中心ID长度必须介于 1 和 11 之间")
	public String getFeeid() {
		return feeid;
	}

	public void setFeeid(String feeid) {
		this.feeid = feeid;
	}
	
	@Length(min=0, max=100, message="名称长度必须介于 0 和 100 之间")
	public String getXname() {
		return xname;
	}

	public void setXname(String xname) {
		this.xname = xname;
	}
	
	@Length(min=0, max=100, message="标准名称长度必须介于 0 和 100 之间")
	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}
	
	@Length(min=0, max=20, message="项目编码长度必须介于 0 和 20 之间")
	public String getFeenumber() {
		return feenumber;
	}

	public void setFeenumber(String feenumber) {
		this.feenumber = feenumber;
	}
	
	@Length(min=0, max=50, message="拼音码长度必须介于 0 和 50 之间")
	public String getPycode() {
		return pycode;
	}

	public void setPycode(String pycode) {
		this.pycode = pycode;
	}
	
	@Length(min=0, max=50, message="五笔码长度必须介于 0 和 50 之间")
	public String getWbcode() {
		return wbcode;
	}

	public void setWbcode(String wbcode) {
		this.wbcode = wbcode;
	}
	
	@Length(min=0, max=10, message="自定义码长度必须介于 0 和 10 之间")
	public String getCuscode() {
		return cuscode;
	}

	public void setCuscode(String cuscode) {
		this.cuscode = cuscode;
	}
	
	@Length(min=0, max=20, message="单位长度必须介于 0 和 20 之间")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	@Length(min=0, max=500, message="项目说明长度必须介于 0 和 500 之间")
	public String getContain() {
		return contain;
	}

	public void setContain(String contain) {
		this.contain = contain;
	}
	
	@Length(min=0, max=500, message="除外内容长度必须介于 0 和 500 之间")
	public String getCwcontent() {
		return cwcontent;
	}

	public void setCwcontent(String cwcontent) {
		this.cwcontent = cwcontent;
	}
	
	@Length(min=0, max=500, message="解释说明长度必须介于 0 和 500 之间")
	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
	
	@Length(min=0, max=4, message="是否医保类型长度必须介于 0 和 4 之间")
	public String getMretype() {
		return mretype;
	}

	public void setMretype(String mretype) {
		this.mretype = mretype;
	}
	
	@Length(min=0, max=4, message="是否停用长度必须介于 0 和 4 之间")
	public String getIsstop() {
		return isstop;
	}

	public void setIsstop(String isstop) {
		this.isstop = isstop;
	}
	
	@Length(min=0, max=11, message="修改人长度必须介于 0 和 11 之间")
	public String getModstaff() {
		return modstaff;
	}

	public void setModstaff(String modstaff) {
		this.modstaff = modstaff;
	}
	
	@Length(min=0, max=20, message="修改人名长度必须介于 0 和 20 之间")
	public String getModstaffname() {
		return modstaffname;
	}

	public void setModstaffname(String modstaffname) {
		this.modstaffname = modstaffname;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getModdate() {
		return moddate;
	}

	public void setModdate(Date moddate) {
		this.moddate = moddate;
	}
	
	@Length(min=0, max=20, message="社保代码长度必须介于 0 和 20 之间")
	public String getSicode() {
		return sicode;
	}

	public void setSicode(String sicode) {
		this.sicode = sicode;
	}
	
	@Length(min=0, max=20, message="农合编码长度必须介于 0 和 20 之间")
	public String getNhcode() {
		return nhcode;
	}

	public void setNhcode(String nhcode) {
		this.nhcode = nhcode;
	}
	
	@Length(min=0, max=4, message="状态长度必须介于 0 和 4 之间")
	public String getZxauditstatus() {
		return zxauditstatus;
	}

	public void setZxauditstatus(String zxauditstatus) {
		this.zxauditstatus = zxauditstatus;
	}
	
	@Length(min=0, max=20, message="审核人长度必须介于 0 和 20 之间")
	public String getZxauditor() {
		return zxauditor;
	}

	public void setZxauditor(String zxauditor) {
		this.zxauditor = zxauditor;
	}
	
	@Length(min=0, max=255, message="审核人名长度必须介于 0 和 255 之间")
	public String getZxauditorname() {
		return zxauditorname;
	}

	public void setZxauditorname(String zxauditorname) {
		this.zxauditorname = zxauditorname;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getZxaudittime() {
		return zxaudittime;
	}

	public void setZxaudittime(Date zxaudittime) {
		this.zxaudittime = zxaudittime;
	}
	
	public String getOneLevel() {
		return oneLevel;
	}

	public void setOneLevel(String oneLevel) {
		this.oneLevel = oneLevel;
	}
	
	public String getTwoLevel() {
		return twoLevel;
	}

	public void setTwoLevel(String twoLevel) {
		this.twoLevel = twoLevel;
	}
	
	public String getThreeLevel() {
		return threeLevel;
	}

	public void setThreeLevel(String threeLevel) {
		this.threeLevel = threeLevel;
	}
	
}