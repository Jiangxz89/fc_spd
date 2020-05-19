/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import com.alibaba.fastjson.annotation.JSONField;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 药库入库明细Entity
 * @author sutianqi
 * @version 2018-07-31
 */
public class MedstoYprkmx extends DataEntity<MedstoYprkmx> {

	private static final long serialVersionUID = 1L;
	private String xh;		// 序号
	private String zdXh;		// 账单序号
	private String fph;		// 发票号
	private String kprq;		// 开票日期
	private String dprq;		// 到票日期
	private String czdm;		// 操作代码
	private String cdIdm;		// 产地idm
	private String ggIdm;		// 规格idm
	private String ypmc;		// 药品名称
	private String ypgg;		// 药品规格
	private String ypdm;		// 药品代码
	private String pcxh;		// 批次序号
	private String sxrq;		// 失效日期c
	private String ph;		// 批号
	private String ykxs;		// 药库系数
	private String ypdw;		// 药品单位
	private String dwxs;		// 单位系数
	private String rksl;		// 入库数量
	private String czsl;		// 操作数量
	private String ypkl;		// 药品扣率
	private String ypjj;		// 药品进价
	private String ypfj;		// ypfj
	private String ylsj;		// 零售价
	private String jjje;		// 进价金额
	private String jxce;		// 进销差额
	private String xgxh;		// 相关序号
	private String dpbz;		// 到票标志
	private String memo;		// 备注
	private String mrzbj;		// 默认招标价
	private String zbqh;		// 招标期号
	private String zbdw;		// 中标单位
	private String ypmrkl;		// 药品默认扣率
	private String cjxh;		// cjxh
	private String scrq;		// 上传日期

	public MedstoYprkmx() {
		super();
	}

	public MedstoYprkmx(String id){
		super(id);
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getZdXh() {
		return zdXh;
	}

	@JSONField(name="zd_xh")
	public void setZdXh(String zdXh) {
		this.zdXh = zdXh;
	}

	@Length(min=0, max=18, message="发票号长度必须介于 0 和 18 之间")
	public String getFph() {
		return fph;
	}

	public void setFph(String fph) {
		this.fph = fph;
	}

	@Length(min=0, max=8, message="开票日期长度必须介于 0 和 8 之间")
	public String getKprq() {
		return kprq;
	}

	public void setKprq(String kprq) {
		this.kprq = kprq;
	}

	@Length(min=1, max=8, message="到票日期长度必须介于 1 和 8 之间")
	public String getDprq() {
		return dprq;
	}

	public void setDprq(String dprq) {
		this.dprq = dprq;
	}

	@Length(min=1, max=2, message="操作代码长度必须介于 1 和 2 之间")
	public String getCzdm() {
		return czdm;
	}

	public void setCzdm(String czdm) {
		this.czdm = czdm;
	}

	public String getCdIdm() {
		return cdIdm;
	}

	@JSONField(name="cd_idm")
	public void setCdIdm(String cdIdm) {
		this.cdIdm = cdIdm;
	}

	public String getGgIdm() {
		return ggIdm;
	}

	@JSONField(name="gg_idm")
	public void setGgIdm(String ggIdm) {
		this.ggIdm = ggIdm;
	}

	@Length(min=1, max=64, message="药品名称长度必须介于 1 和 64 之间")
	public String getYpmc() {
		return ypmc;
	}

	public void setYpmc(String ypmc) {
		this.ypmc = ypmc;
	}

	@Length(min=1, max=32, message="药品规格长度必须介于 1 和 32 之间")
	public String getYpgg() {
		return ypgg;
	}

	public void setYpgg(String ypgg) {
		this.ypgg = ypgg;
	}

	@Length(min=1, max=18, message="药品代码长度必须介于 1 和 18 之间")
	public String getYpdm() {
		return ypdm;
	}

	public void setYpdm(String ypdm) {
		this.ypdm = ypdm;
	}

	public String getPcxh() {
		return pcxh;
	}

	public void setPcxh(String pcxh) {
		this.pcxh = pcxh;
	}

	@Length(min=1, max=8, message="失效日期c长度必须介于 1 和 8 之间")
	public String getSxrq() {
		return sxrq;
	}

	public void setSxrq(String sxrq) {
		this.sxrq = sxrq;
	}

	@Length(min=1, max=16, message="批号长度必须介于 1 和 16 之间")
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

	@Length(min=1, max=8, message="药品单位长度必须介于 1 和 8 之间")
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

	public String getRksl() {
		return rksl;
	}

	public void setRksl(String rksl) {
		this.rksl = rksl;
	}

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

	public String getXgxh() {
		return xgxh;
	}

	public void setXgxh(String xgxh) {
		this.xgxh = xgxh;
	}

	@Length(min=1, max=6, message="到票标志长度必须介于 1 和 6 之间")
	public String getDpbz() {
		return dpbz;
	}

	public void setDpbz(String dpbz) {
		this.dpbz = dpbz;
	}

	@Length(min=0, max=24, message="备注长度必须介于 0 和 24 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	public String getCjxh() {
		return cjxh;
	}

	public void setCjxh(String cjxh) {
		this.cjxh = cjxh;
	}

	@Length(min=0, max=8, message="上传日期长度必须介于 0 和 8 之间")
	public String getScrq() {
		return scrq;
	}

	public void setScrq(String scrq) {
		this.scrq = scrq;
	}
	
}