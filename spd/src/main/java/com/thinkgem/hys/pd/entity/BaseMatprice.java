/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 本院物资目录Entity
 * @author zxh
 * @version 2019-11-13
 */
public class BaseMatprice extends DataEntity<BaseMatprice> {
	
	private static final long serialVersionUID = 1L;
	private String stockid;		// 本院物资ID
	private String hospitalid;		// 医院ID
	private String hospitalname;		// 医疗机构名称
	private String matid;		// 中心ID
	private String cuscode;		// 自定义码
	private String facid;		// 厂家ID
	private String facname;		// 厂家名称
	private String number;		// 项目编码
	private String inprice;		// 购进价
	private String sellprice;		// 零售价
	private String maxstock;		// 库存上限
	private String minstock;		// 库存下限
	private String position;		// 存放位置
	private String isble;		// 药房可发
	private String isuse;		// 处方可开
	private String isstop;		// 是否停用
	private String sicode;		// 社保编码
	private String modstaff;		// 最后修改人
	private String modstaffname;		// 最后修改人名
	private Date moddate;		// 修改时间
	private String nhcode;		// 农合编码
	private String audstate;		// 是否通过社保审核
	private String invclass;		// 发票分类
	private String invclassname;		// 发票分类名称
	private String auditstatus;		// 审核状态
	private String auditor;		// 审核人
	private String auditorname;		// 审核人名
	private Date audittime;		// 审核时间
	
	public BaseMatprice() {
		super();
	}

	public BaseMatprice(String id){
		super(id);
	}

	@Length(min=1, max=11, message="本院物资ID长度必须介于 1 和 11 之间")
	public String getStockid() {
		return stockid;
	}

	public void setStockid(String stockid) {
		this.stockid = stockid;
	}
	
	@Length(min=0, max=11, message="医院ID长度必须介于 0 和 11 之间")
	public String getHospitalid() {
		return hospitalid;
	}

	public void setHospitalid(String hospitalid) {
		this.hospitalid = hospitalid;
	}
	
	@Length(min=0, max=255, message="医疗机构名称长度必须介于 0 和 255 之间")
	public String getHospitalname() {
		return hospitalname;
	}

	public void setHospitalname(String hospitalname) {
		this.hospitalname = hospitalname;
	}
	
	@Length(min=0, max=11, message="中心ID长度必须介于 0 和 11 之间")
	public String getMatid() {
		return matid;
	}

	public void setMatid(String matid) {
		this.matid = matid;
	}
	
	@Length(min=0, max=10, message="自定义码长度必须介于 0 和 10 之间")
	public String getCuscode() {
		return cuscode;
	}

	public void setCuscode(String cuscode) {
		this.cuscode = cuscode;
	}
	
	@Length(min=0, max=11, message="厂家ID长度必须介于 0 和 11 之间")
	public String getFacid() {
		return facid;
	}

	public void setFacid(String facid) {
		this.facid = facid;
	}
	
	@Length(min=0, max=150, message="厂家名称长度必须介于 0 和 150 之间")
	public String getFacname() {
		return facname;
	}

	public void setFacname(String facname) {
		this.facname = facname;
	}
	
	@Length(min=0, max=20, message="项目编码长度必须介于 0 和 20 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getInprice() {
		return inprice;
	}

	public void setInprice(String inprice) {
		this.inprice = inprice;
	}
	
	public String getSellprice() {
		return sellprice;
	}

	public void setSellprice(String sellprice) {
		this.sellprice = sellprice;
	}
	
	@Length(min=0, max=11, message="库存上限长度必须介于 0 和 11 之间")
	public String getMaxstock() {
		return maxstock;
	}

	public void setMaxstock(String maxstock) {
		this.maxstock = maxstock;
	}
	
	@Length(min=0, max=11, message="库存下限长度必须介于 0 和 11 之间")
	public String getMinstock() {
		return minstock;
	}

	public void setMinstock(String minstock) {
		this.minstock = minstock;
	}
	
	@Length(min=0, max=10, message="存放位置长度必须介于 0 和 10 之间")
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	@Length(min=0, max=4, message="药房可发长度必须介于 0 和 4 之间")
	public String getIsble() {
		return isble;
	}

	public void setIsble(String isble) {
		this.isble = isble;
	}
	
	@Length(min=0, max=4, message="处方可开长度必须介于 0 和 4 之间")
	public String getIsuse() {
		return isuse;
	}

	public void setIsuse(String isuse) {
		this.isuse = isuse;
	}
	
	@Length(min=0, max=4, message="是否停用长度必须介于 0 和 4 之间")
	public String getIsstop() {
		return isstop;
	}

	public void setIsstop(String isstop) {
		this.isstop = isstop;
	}
	
	@Length(min=0, max=255, message="社保编码长度必须介于 0 和 255 之间")
	public String getSicode() {
		return sicode;
	}

	public void setSicode(String sicode) {
		this.sicode = sicode;
	}
	
	@Length(min=0, max=11, message="最后修改人长度必须介于 0 和 11 之间")
	public String getModstaff() {
		return modstaff;
	}

	public void setModstaff(String modstaff) {
		this.modstaff = modstaff;
	}
	
	@Length(min=0, max=20, message="最后修改人名长度必须介于 0 和 20 之间")
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
	
	@Length(min=0, max=20, message="农合编码长度必须介于 0 和 20 之间")
	public String getNhcode() {
		return nhcode;
	}

	public void setNhcode(String nhcode) {
		this.nhcode = nhcode;
	}
	
	@Length(min=0, max=6, message="是否通过社保审核长度必须介于 0 和 6 之间")
	public String getAudstate() {
		return audstate;
	}

	public void setAudstate(String audstate) {
		this.audstate = audstate;
	}
	
	@Length(min=0, max=11, message="发票分类长度必须介于 0 和 11 之间")
	public String getInvclass() {
		return invclass;
	}

	public void setInvclass(String invclass) {
		this.invclass = invclass;
	}
	
	@Length(min=0, max=20, message="发票分类名称长度必须介于 0 和 20 之间")
	public String getInvclassname() {
		return invclassname;
	}

	public void setInvclassname(String invclassname) {
		this.invclassname = invclassname;
	}
	
	@Length(min=0, max=4, message="审核状态长度必须介于 0 和 4 之间")
	public String getAuditstatus() {
		return auditstatus;
	}

	public void setAuditstatus(String auditstatus) {
		this.auditstatus = auditstatus;
	}
	
	@Length(min=0, max=11, message="审核人长度必须介于 0 和 11 之间")
	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	
	@Length(min=0, max=20, message="审核人名长度必须介于 0 和 20 之间")
	public String getAuditorname() {
		return auditorname;
	}

	public void setAuditorname(String auditorname) {
		this.auditorname = auditorname;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAudittime() {
		return audittime;
	}

	public void setAudittime(Date audittime) {
		this.audittime = audittime;
	}
	
}