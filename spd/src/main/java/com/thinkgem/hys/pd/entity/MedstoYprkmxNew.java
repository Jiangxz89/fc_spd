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
 * 药品入库明细Entity
 * @author zxh
 * @version 2019-05-14
 */
public class MedstoYprkmxNew extends DataEntity<MedstoYprkmxNew> {
	
	private static final long serialVersionUID = 1L;
	private String xh;		// 序号
	private String zdXh;		// 账单序号
	private String fph;		// 发票号
	private Date kprq;		// 开票日期
	private Date dprq;		// 到票日期
	private String czdm;		// 操作代码
	private String cdIdm;		// 产地idm
	private String ggIdm;		// 规格idm
	private String drugName;		// 药品名称
	private String drugSpec;		// 药品规格
	private String ypdm;		// 药品代码
	private String pcxh;		// 批次序号
	private Date expiry;		// 失效日期
	private String ph;		// 批号
	private String ykxs;		// 药库系数
	private String ypdw;		// 药品单位
	private String dwxs;		// 单位系数
	private String rksl;		// 入库数量
	private String czsl;		// 操作数量
	private String ypkl;		// 药品扣率
	private String ypjj;		// 药品进价
	private String ypfj;		// 批发价
	private String ylsj;		// 零售价
	private String jjje;		// 进价金额
	private String jxce;		// 进销差额
	private String xgxh;		// 相关序号
	private String dpbz;		// 到票标志
	private String mrzbj;		// 默认招标价
	private String zbqh;		// 招标期号
	private String zbdw;		// 中标单位
	private String ypmrkl;		// 药品默认扣率
	private Date scrq;		// 上传日期
	
	public MedstoYprkmxNew() {
		super();
	}

	public MedstoYprkmxNew(String id){
		super(id);
	}

	@Length(min=1, max=60, message="序号长度必须介于 1 和 60 之间")
	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}
	
	@Length(min=1, max=60, message="账单序号长度必须介于 1 和 60 之间")
	public String getZdXh() {
		return zdXh;
	}

	public void setZdXh(String zdXh) {
		this.zdXh = zdXh;
	}
	
	@Length(min=0, max=60, message="发票号长度必须介于 0 和 60 之间")
	public String getFph() {
		return fph;
	}

	public void setFph(String fph) {
		this.fph = fph;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getKprq() {
		return kprq;
	}

	public void setKprq(Date kprq) {
		this.kprq = kprq;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="到票日期不能为空")
	public Date getDprq() {
		return dprq;
	}

	public void setDprq(Date dprq) {
		this.dprq = dprq;
	}
	
	@Length(min=1, max=60, message="操作代码长度必须介于 1 和 60 之间")
	public String getCzdm() {
		return czdm;
	}

	public void setCzdm(String czdm) {
		this.czdm = czdm;
	}
	
	@Length(min=1, max=60, message="产地idm长度必须介于 1 和 60 之间")
	public String getCdIdm() {
		return cdIdm;
	}

	public void setCdIdm(String cdIdm) {
		this.cdIdm = cdIdm;
	}
	
	@Length(min=1, max=60, message="规格idm长度必须介于 1 和 60 之间")
	public String getGgIdm() {
		return ggIdm;
	}

	public void setGgIdm(String ggIdm) {
		this.ggIdm = ggIdm;
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
	
	@Length(min=1, max=60, message="药品代码长度必须介于 1 和 60 之间")
	public String getYpdm() {
		return ypdm;
	}

	public void setYpdm(String ypdm) {
		this.ypdm = ypdm;
	}
	
	@Length(min=1, max=60, message="批次序号长度必须介于 1 和 60 之间")
	public String getPcxh() {
		return pcxh;
	}

	public void setPcxh(String pcxh) {
		this.pcxh = pcxh;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="失效日期不能为空")
	public Date getExpiry() {
		return expiry;
	}

	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}
	
	@Length(min=1, max=60, message="批号长度必须介于 1 和 60 之间")
	public String getPh() {
		return ph;
	}

	public void setPh(String ph) {
		this.ph = ph;
	}
	
	public String getYkxs() {
		return ykxs;
	}

	public void setYkxs(String ykxs) {
		this.ykxs = ykxs;
	}
	
	@Length(min=1, max=60, message="药品单位长度必须介于 1 和 60 之间")
	public String getYpdw() {
		return ypdw;
	}

	public void setYpdw(String ypdw) {
		this.ypdw = ypdw;
	}
	
	public String getDwxs() {
		return dwxs;
	}

	public void setDwxs(String dwxs) {
		this.dwxs = dwxs;
	}
	
	@Length(min=1, max=11, message="入库数量长度必须介于 1 和 11 之间")
	public String getRksl() {
		return rksl;
	}

	public void setRksl(String rksl) {
		this.rksl = rksl;
	}
	
	@Length(min=1, max=11, message="操作数量长度必须介于 1 和 11 之间")
	public String getCzsl() {
		return czsl;
	}

	public void setCzsl(String czsl) {
		this.czsl = czsl;
	}
	
	public String getYpkl() {
		return ypkl;
	}

	public void setYpkl(String ypkl) {
		this.ypkl = ypkl;
	}
	
	public String getYpjj() {
		return ypjj;
	}

	public void setYpjj(String ypjj) {
		this.ypjj = ypjj;
	}
	
	public String getYpfj() {
		return ypfj;
	}

	public void setYpfj(String ypfj) {
		this.ypfj = ypfj;
	}
	
	public String getYlsj() {
		return ylsj;
	}

	public void setYlsj(String ylsj) {
		this.ylsj = ylsj;
	}
	
	public String getJjje() {
		return jjje;
	}

	public void setJjje(String jjje) {
		this.jjje = jjje;
	}
	
	public String getJxce() {
		return jxce;
	}

	public void setJxce(String jxce) {
		this.jxce = jxce;
	}
	
	@Length(min=0, max=11, message="相关序号长度必须介于 0 和 11 之间")
	public String getXgxh() {
		return xgxh;
	}

	public void setXgxh(String xgxh) {
		this.xgxh = xgxh;
	}
	
	@Length(min=1, max=11, message="到票标志长度必须介于 1 和 11 之间")
	public String getDpbz() {
		return dpbz;
	}

	public void setDpbz(String dpbz) {
		this.dpbz = dpbz;
	}
	
	public String getMrzbj() {
		return mrzbj;
	}

	public void setMrzbj(String mrzbj) {
		this.mrzbj = mrzbj;
	}
	
	@Length(min=0, max=32, message="招标期号长度必须介于 0 和 32 之间")
	public String getZbqh() {
		return zbqh;
	}

	public void setZbqh(String zbqh) {
		this.zbqh = zbqh;
	}
	
	@Length(min=0, max=64, message="中标单位长度必须介于 0 和 64 之间")
	public String getZbdw() {
		return zbdw;
	}

	public void setZbdw(String zbdw) {
		this.zbdw = zbdw;
	}
	
	public String getYpmrkl() {
		return ypmrkl;
	}

	public void setYpmrkl(String ypmrkl) {
		this.ypmrkl = ypmrkl;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getScrq() {
		return scrq;
	}

	public void setScrq(Date scrq) {
		this.scrq = scrq;
	}
	
}