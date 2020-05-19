/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import com.alibaba.fastjson.annotation.JSONField;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 药库批次库Entity
 * @author sutianqi
 * @version 2018-07-31
 */
public class MedstoYppck extends DataEntity<MedstoYppck> {
	
	private static final long serialVersionUID = 1L;
	private String xh;		// 序号
	private String cdIdm;		// 产地idm
	private String ksdm;		// 科室代码
	private String ph;		// 批号
	private String ypjj;		// ypjj
	private String pcsl;		// 批次数量
	private String kcsl;		// 库存数量
	private String rkrq;		// 入库日期
	private String sxrq;		// 失效日期
	private String jlzt;		// 记录状态
	private String memo;		// 说明
	private String djsl;		// 冻结数量
	private String jxje;		// 进销金额
	private String scrq;		// 生产日期
	private String kzbz;		// 控制标志0:不控制，1：控制
	private String ylsj;		// 零售价
	private String ghdwdm;		// 供货单位代码
	private String ghdwmc;		// 供货单位名称
	private String isxtzdsc;		// isxtzdsc
	
	public MedstoYppck() {
		super();
	}

	public MedstoYppck(String id){
		super(id);
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}
	
	public String getCdIdm() {
		return cdIdm;
	}

	@JSONField(name="cd_idm")
	public void setCdIdm(String cdIdm) {
		this.cdIdm = cdIdm;
	}
	
	@Length(min=0, max=4, message="科室代码长度必须介于 0 和 4 之间")
	public String getKsdm() {
		return ksdm;
	}

	public void setKsdm(String ksdm) {
		this.ksdm = ksdm;
	}
	
	@Length(min=0, max=16, message="批号长度必须介于 0 和 16 之间")
	public String getPh() {
		return ph;
	}

	public void setPh(String ph) {
		this.ph = ph;
	}
	
	public String getYpjj() {
		return ypjj;
	}

	public void setYpjj(String ypjj) {
		this.ypjj = ypjj;
	}
	
	public String getPcsl() {
		return pcsl;
	}

	public void setPcsl(String pcsl) {
		this.pcsl = pcsl;
	}
	
	public String getKcsl() {
		return kcsl;
	}

	public void setKcsl(String kcsl) {
		this.kcsl = kcsl;
	}
	
	@Length(min=0, max=16, message="入库日期长度必须介于 0 和 16 之间")
	public String getRkrq() {
		return rkrq;
	}

	public void setRkrq(String rkrq) {
		this.rkrq = rkrq;
	}
	
	@Length(min=0, max=8, message="失效日期长度必须介于 0 和 8 之间")
	public String getSxrq() {
		return sxrq;
	}

	public void setSxrq(String sxrq) {
		this.sxrq = sxrq;
	}
	
	@Length(min=0, max=6, message="记录状态长度必须介于 0 和 6 之间")
	public String getJlzt() {
		return jlzt;
	}

	public void setJlzt(String jlzt) {
		this.jlzt = jlzt;
	}
	
	@Length(min=1, max=24, message="说明长度必须介于 1 和 24 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public String getDjsl() {
		return djsl;
	}

	public void setDjsl(String djsl) {
		this.djsl = djsl;
	}
	
	public String getJxje() {
		return jxje;
	}

	public void setJxje(String jxje) {
		this.jxje = jxje;
	}
	
	@Length(min=1, max=8, message="生产日期长度必须介于 1 和 8 之间")
	public String getScrq() {
		return scrq;
	}

	public void setScrq(String scrq) {
		this.scrq = scrq;
	}
	
	@Length(min=1, max=6, message="控制标志0:不控制，1：控制长度必须介于 1 和 6 之间")
	public String getKzbz() {
		return kzbz;
	}

	public void setKzbz(String kzbz) {
		this.kzbz = kzbz;
	}
	
	public String getYlsj() {
		return ylsj;
	}

	public void setYlsj(String ylsj) {
		this.ylsj = ylsj;
	}
	
	@Length(min=1, max=50, message="供货单位代码长度必须介于 1 和 50 之间")
	public String getGhdwdm() {
		return ghdwdm;
	}

	public void setGhdwdm(String ghdwdm) {
		this.ghdwdm = ghdwdm;
	}
	
	@Length(min=1, max=1000, message="供货单位名称长度必须介于 1 和 1000 之间")
	public String getGhdwmc() {
		return ghdwmc;
	}

	public void setGhdwmc(String ghdwmc) {
		this.ghdwmc = ghdwmc;
	}
	
	@Length(min=1, max=11, message="isxtzdsc长度必须介于 1 和 11 之间")
	public String getIsxtzdsc() {
		return isxtzdsc;
	}

	public void setIsxtzdsc(String isxtzdsc) {
		this.isxtzdsc = isxtzdsc;
	}
	
}