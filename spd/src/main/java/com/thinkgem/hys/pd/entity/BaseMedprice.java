/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 本院药品目录Entity
 * @author zxh
 * @version 2019-09-20
 */
public class BaseMedprice extends DataEntity<BaseMedprice> {
	
	private static final long serialVersionUID = 1L;
	private String stockid;		// 本院药品ID
	private String hospitalid;		// 医院ID
	private String hospitalname;		// 医疗机构名称
	private String medid;		// 中心ID
	private String cuscode;		// 自定义码
	private String sicode;		// 社保编码
	private String facid;		// 厂家ID
	private String facname;		// 厂家名称
	private String number;		// 批准文号
	private String inprice;		// 购进价
	private String sellprice;		// 零售价
	private String usgid;		// 默认用法
	private String usgname;		// 用法名称
	private String fcyid;		// 默认频次
	private String fcyname;		// 频次名称
	private String taboo;		// 禁忌
	private String maxstock;		// 库存上限
	private String minstock;		// 库存下限
	private String position;		// 存放位置
	private String userange;		// 使用范围
	private String isstop;		// 是否停用
	private String modstaff;		// 修改人
	private String modstaffname;		// 修改人名
	private Date moddate;		// 修改时间
	private String split;		// 是否可拆
	private String injection;		// 注射室取药
	private String hospitalized;		// 住院取整发药
	private String freestrike;		// 住院免发药
	private String outpatient;		// 门诊免发药
	private String label;		// 门诊不打标签
	private String detail;		// 药品资料
	private String explain;		// 说明
	private String limit;		// 剂量限制
	private String limited;		// 用法限定
	private String prompt;		// 特别提示
	private String mzstop;		// 门诊停用
	private String zystop;		// 住院停用
	private String skinmethod;		// 皮试方法
	private String competence;		// 权限
	private String nhcode;		// 农合编码
	private String audstate;		// 是否通过社保审核
	private String invclass;		// 项目分类
	private String auditstatus;		// 审核状态
	private String auditor;		// 审核人
	private String auditorname;		// 审核人名
	private Date audittime;		// 审核时间
	private String adviceprintclass;		// 医嘱打印分类
	private String directions;		// 说明书
	private Date dirupdatetime;		// 说明书更新时间
	
	public BaseMedprice() {
		super();
	}

	public BaseMedprice(String id){
		super(id);
	}

	@Length(min=1, max=11, message="本院药品ID长度必须介于 1 和 11 之间")
	public String getStockid() {
		return stockid;
	}

	public void setStockid(String stockid) {
		this.stockid = stockid;
	}
	
	@Length(min=1, max=11, message="医院ID长度必须介于 1 和 11 之间")
	public String getHospitalid() {
		return hospitalid;
	}

	public void setHospitalid(String hospitalid) {
		this.hospitalid = hospitalid;
	}
	
	@Length(min=1, max=50, message="医疗机构名称长度必须介于 1 和 50 之间")
	public String getHospitalname() {
		return hospitalname;
	}

	public void setHospitalname(String hospitalname) {
		this.hospitalname = hospitalname;
	}
	
	@Length(min=1, max=11, message="中心ID长度必须介于 1 和 11 之间")
	public String getMedid() {
		return medid;
	}

	public void setMedid(String medid) {
		this.medid = medid;
	}
	
	@Length(min=1, max=10, message="自定义码长度必须介于 1 和 10 之间")
	public String getCuscode() {
		return cuscode;
	}

	public void setCuscode(String cuscode) {
		this.cuscode = cuscode;
	}
	
	@Length(min=1, max=20, message="社保编码长度必须介于 1 和 20 之间")
	public String getSicode() {
		return sicode;
	}

	public void setSicode(String sicode) {
		this.sicode = sicode;
	}
	
	@Length(min=0, max=11, message="厂家ID长度必须介于 0 和 11 之间")
	public String getFacid() {
		return facid;
	}

	public void setFacid(String facid) {
		this.facid = facid;
	}
	
	@Length(min=1, max=150, message="厂家名称长度必须介于 1 和 150 之间")
	public String getFacname() {
		return facname;
	}

	public void setFacname(String facname) {
		this.facname = facname;
	}
	
	@Length(min=1, max=20, message="批准文号长度必须介于 1 和 20 之间")
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
	
	@Length(min=1, max=11, message="默认用法长度必须介于 1 和 11 之间")
	public String getUsgid() {
		return usgid;
	}

	public void setUsgid(String usgid) {
		this.usgid = usgid;
	}
	
	@Length(min=1, max=100, message="用法名称长度必须介于 1 和 100 之间")
	public String getUsgname() {
		return usgname;
	}

	public void setUsgname(String usgname) {
		this.usgname = usgname;
	}
	
	@Length(min=1, max=11, message="默认频次长度必须介于 1 和 11 之间")
	public String getFcyid() {
		return fcyid;
	}

	public void setFcyid(String fcyid) {
		this.fcyid = fcyid;
	}
	
	@Length(min=1, max=100, message="频次名称长度必须介于 1 和 100 之间")
	public String getFcyname() {
		return fcyname;
	}

	public void setFcyname(String fcyname) {
		this.fcyname = fcyname;
	}
	
	@Length(min=1, max=20, message="禁忌长度必须介于 1 和 20 之间")
	public String getTaboo() {
		return taboo;
	}

	public void setTaboo(String taboo) {
		this.taboo = taboo;
	}
	
	@Length(min=1, max=11, message="库存上限长度必须介于 1 和 11 之间")
	public String getMaxstock() {
		return maxstock;
	}

	public void setMaxstock(String maxstock) {
		this.maxstock = maxstock;
	}
	
	@Length(min=1, max=11, message="库存下限长度必须介于 1 和 11 之间")
	public String getMinstock() {
		return minstock;
	}

	public void setMinstock(String minstock) {
		this.minstock = minstock;
	}
	
	@Length(min=1, max=10, message="存放位置长度必须介于 1 和 10 之间")
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	@Length(min=1, max=4, message="使用范围长度必须介于 1 和 4 之间")
	public String getUserange() {
		return userange;
	}

	public void setUserange(String userange) {
		this.userange = userange;
	}
	
	@Length(min=1, max=4, message="是否停用长度必须介于 1 和 4 之间")
	public String getIsstop() {
		return isstop;
	}

	public void setIsstop(String isstop) {
		this.isstop = isstop;
	}
	
	@Length(min=1, max=11, message="修改人长度必须介于 1 和 11 之间")
	public String getModstaff() {
		return modstaff;
	}

	public void setModstaff(String modstaff) {
		this.modstaff = modstaff;
	}
	
	@Length(min=1, max=20, message="修改人名长度必须介于 1 和 20 之间")
	public String getModstaffname() {
		return modstaffname;
	}

	public void setModstaffname(String modstaffname) {
		this.modstaffname = modstaffname;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="修改时间不能为空")
	public Date getModdate() {
		return moddate;
	}

	public void setModdate(Date moddate) {
		this.moddate = moddate;
	}
	
	@Length(min=1, max=4, message="是否可拆长度必须介于 1 和 4 之间")
	public String getSplit() {
		return split;
	}

	public void setSplit(String split) {
		this.split = split;
	}
	
	@Length(min=1, max=4, message="注射室取药长度必须介于 1 和 4 之间")
	public String getInjection() {
		return injection;
	}

	public void setInjection(String injection) {
		this.injection = injection;
	}
	
	@Length(min=1, max=4, message="住院取整发药长度必须介于 1 和 4 之间")
	public String getHospitalized() {
		return hospitalized;
	}

	public void setHospitalized(String hospitalized) {
		this.hospitalized = hospitalized;
	}
	
	@Length(min=1, max=4, message="住院免发药长度必须介于 1 和 4 之间")
	public String getFreestrike() {
		return freestrike;
	}

	public void setFreestrike(String freestrike) {
		this.freestrike = freestrike;
	}
	
	@Length(min=1, max=4, message="门诊免发药长度必须介于 1 和 4 之间")
	public String getOutpatient() {
		return outpatient;
	}

	public void setOutpatient(String outpatient) {
		this.outpatient = outpatient;
	}
	
	@Length(min=1, max=4, message="门诊不打标签长度必须介于 1 和 4 之间")
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	@Length(min=1, max=1000, message="药品资料长度必须介于 1 和 1000 之间")
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	@Length(min=1, max=250, message="说明长度必须介于 1 和 250 之间")
	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
	
	@Length(min=1, max=50, message="剂量限制长度必须介于 1 和 50 之间")
	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}
	
	@Length(min=1, max=250, message="用法限定长度必须介于 1 和 250 之间")
	public String getLimited() {
		return limited;
	}

	public void setLimited(String limited) {
		this.limited = limited;
	}
	
	@Length(min=1, max=250, message="特别提示长度必须介于 1 和 250 之间")
	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
	
	@Length(min=1, max=4, message="门诊停用长度必须介于 1 和 4 之间")
	public String getMzstop() {
		return mzstop;
	}

	public void setMzstop(String mzstop) {
		this.mzstop = mzstop;
	}
	
	@Length(min=1, max=4, message="住院停用长度必须介于 1 和 4 之间")
	public String getZystop() {
		return zystop;
	}

	public void setZystop(String zystop) {
		this.zystop = zystop;
	}
	
	@Length(min=1, max=11, message="皮试方法长度必须介于 1 和 11 之间")
	public String getSkinmethod() {
		return skinmethod;
	}

	public void setSkinmethod(String skinmethod) {
		this.skinmethod = skinmethod;
	}
	
	@Length(min=1, max=11, message="权限长度必须介于 1 和 11 之间")
	public String getCompetence() {
		return competence;
	}

	public void setCompetence(String competence) {
		this.competence = competence;
	}
	
	@Length(min=1, max=255, message="农合编码长度必须介于 1 和 255 之间")
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
	
	@Length(min=0, max=11, message="项目分类长度必须介于 0 和 11 之间")
	public String getInvclass() {
		return invclass;
	}

	public void setInvclass(String invclass) {
		this.invclass = invclass;
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
	
	@Length(min=0, max=4, message="医嘱打印分类长度必须介于 0 和 4 之间")
	public String getAdviceprintclass() {
		return adviceprintclass;
	}

	public void setAdviceprintclass(String adviceprintclass) {
		this.adviceprintclass = adviceprintclass;
	}
	
	public String getDirections() {
		return directions;
	}

	public void setDirections(String directions) {
		this.directions = directions;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDirupdatetime() {
		return dirupdatetime;
	}

	public void setDirupdatetime(Date dirupdatetime) {
		this.dirupdatetime = dirupdatetime;
	}
	
}