/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 药品规格库Entity
 * @author sutianqi
 * @version 2018-07-31
 */
public class MedstoYpggmlk extends DataEntity<MedstoYpggmlk> {
	
	private static final long serialVersionUID = 1L;
	private String idm;		// idm
	private String ypmc;		// 药品名称
	private String yplh;		// 药品类号
	private String ypdm;		// 药品代码
	private String ypdm1;		// 药品代码
	private String ypdm2;		// 药品代码2
	private String py;		// 拼音
	private String wb;		// 五笔
	private String ypgg;		// 药品规格
	private String lcIdm;		// 临床idm
	private String jxdm;		// 剂型代码
	private String fldm;		// 分类代码
	private String zxdw;		// 最小单位
	private String kcsl;		// 库存数量
	private String ggdw;		// ggdw
	private String ggxs;		// 规格系数
	private String kcsx;		// 库存上限
	private String kcxx;		// kcxx
	private String tybz;		// 停用标志
	private String memo;		// 备注
	private String fysm;		// 反应说明
	private String cfsm;		// 存放说明
	
	public MedstoYpggmlk() {
		super();
	}

	public MedstoYpggmlk(String id){
		super(id);
	}

	public String getIdm() {
		return idm;
	}

	public void setIdm(String idm) {
		this.idm = idm;
	}
	
	@Length(min=0, max=64, message="药品名称长度必须介于 0 和 64 之间")
	public String getYpmc() {
		return ypmc;
	}

	public void setYpmc(String ypmc) {
		this.ypmc = ypmc;
	}
	
	@Length(min=0, max=6, message="药品类号长度必须介于 0 和 6 之间")
	public String getYplh() {
		return yplh;
	}

	public void setYplh(String yplh) {
		this.yplh = yplh;
	}
	
	@Length(min=1, max=18, message="药品代码长度必须介于 1 和 18 之间")
	public String getYpdm() {
		return ypdm;
	}

	public void setYpdm(String ypdm) {
		this.ypdm = ypdm;
	}
	
	@Length(min=1, max=18, message="药品代码长度必须介于 1 和 18 之间")
	public String getYpdm1() {
		return ypdm1;
	}

	public void setYpdm1(String ypdm1) {
		this.ypdm1 = ypdm1;
	}
	
	@Length(min=1, max=18, message="药品代码2长度必须介于 1 和 18 之间")
	public String getYpdm2() {
		return ypdm2;
	}

	public void setYpdm2(String ypdm2) {
		this.ypdm2 = ypdm2;
	}
	
	@Length(min=1, max=8, message="拼音长度必须介于 1 和 8 之间")
	public String getPy() {
		return py;
	}

	public void setPy(String py) {
		this.py = py;
	}
	
	@Length(min=1, max=8, message="五笔长度必须介于 1 和 8 之间")
	public String getWb() {
		return wb;
	}

	public void setWb(String wb) {
		this.wb = wb;
	}
	
	@Length(min=0, max=32, message="药品规格长度必须介于 0 和 32 之间")
	public String getYpgg() {
		return ypgg;
	}

	public void setYpgg(String ypgg) {
		this.ypgg = ypgg;
	}
	
	public String getLcIdm() {
		return lcIdm;
	}

	public void setLcIdm(String lcIdm) {
		this.lcIdm = lcIdm;
	}
	
	@Length(min=0, max=2, message="剂型代码长度必须介于 0 和 2 之间")
	public String getJxdm() {
		return jxdm;
	}

	public void setJxdm(String jxdm) {
		this.jxdm = jxdm;
	}
	
	@Length(min=0, max=6, message="分类代码长度必须介于 0 和 6 之间")
	public String getFldm() {
		return fldm;
	}

	public void setFldm(String fldm) {
		this.fldm = fldm;
	}
	
	@Length(min=0, max=8, message="最小单位长度必须介于 0 和 8 之间")
	public String getZxdw() {
		return zxdw;
	}

	public void setZxdw(String zxdw) {
		this.zxdw = zxdw;
	}
	
	public String getKcsl() {
		return kcsl;
	}

	public void setKcsl(String kcsl) {
		this.kcsl = kcsl;
	}
	
	@Length(min=0, max=8, message="ggdw长度必须介于 0 和 8 之间")
	public String getGgdw() {
		return ggdw;
	}

	public void setGgdw(String ggdw) {
		this.ggdw = ggdw;
	}
	
	public String getGgxs() {
		return ggxs;
	}

	public void setGgxs(String ggxs) {
		this.ggxs = ggxs;
	}
	
	public String getKcsx() {
		return kcsx;
	}

	public void setKcsx(String kcsx) {
		this.kcsx = kcsx;
	}
	
	public String getKcxx() {
		return kcxx;
	}

	public void setKcxx(String kcxx) {
		this.kcxx = kcxx;
	}
	
	@Length(min=0, max=6, message="停用标志长度必须介于 0 和 6 之间")
	public String getTybz() {
		return tybz;
	}

	public void setTybz(String tybz) {
		this.tybz = tybz;
	}
	
	@Length(min=1, max=24, message="备注长度必须介于 1 和 24 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public String getFysm() {
		return fysm;
	}

	public void setFysm(String fysm) {
		this.fysm = fysm;
	}
	
	public String getCfsm() {
		return cfsm;
	}

	public void setCfsm(String cfsm) {
		this.cfsm = cfsm;
	}
	
}