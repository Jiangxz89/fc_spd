/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 药库采购明细Entity
 * @author sutianqi
 * @version 2018-07-31
 */
public class MedstoYpcgmx extends DataEntity<MedstoYpcgmx> {
	
	private static final long serialVersionUID = 1L;
	private String xh;		// 序号
	private String cgxh;		// 采购序号
	private String ggIdm;		// 规格idm
	private String cdIdm;		// 产地idm
	private String ypmc;		// 药品名称
	private String ypgg;		// 药品规格
	private String ypdm;		// 药品代码
	private String ykdw;		// 药库单位
	private String ykxs;		// 药库系数
	private String ypfj;		// 批发价
	private String ylsj;		// 零售价
	private String kcsl;		// 库存数量
	private String cgsl;		// 采购数量
	private String ghdwMc;		// 供货单位
	private String ghdwId;		// 单位代码
	private String ypjj;		// 药品进价
	private String memo;		// memo
	private String ckjj;		// ckjj
	private String cgbz;		// 采购标志
	private String qldh;		// 请领单号
	private String shks;		// 审核科室
	private String rksl;		// 入库数量
	private String cksl;		// 出库数量
	private String cfwz;		// 存放位置
	private String lrxh;		// 录入序号
	
	public MedstoYpcgmx() {
		super();
	}

	public MedstoYpcgmx(String id){
		super(id);
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}
	
	public String getCgxh() {
		return cgxh;
	}

	public void setCgxh(String cgxh) {
		this.cgxh = cgxh;
	}
	
	public String getGgIdm() {
		return ggIdm;
	}

	public void setGgIdm(String ggIdm) {
		this.ggIdm = ggIdm;
	}
	
	public String getCdIdm() {
		return cdIdm;
	}

	public void setCdIdm(String cdIdm) {
		this.cdIdm = cdIdm;
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
	
	@Length(min=1, max=8, message="药库单位长度必须介于 1 和 8 之间")
	public String getYkdw() {
		return ykdw;
	}

	public void setYkdw(String ykdw) {
		this.ykdw = ykdw;
	}
	
	public String getYkxs() {
		return ykxs;
	}

	public void setYkxs(String ykxs) {
		this.ykxs = ykxs;
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
	
	public String getKcsl() {
		return kcsl;
	}

	public void setKcsl(String kcsl) {
		this.kcsl = kcsl;
	}
	
	public String getCgsl() {
		return cgsl;
	}

	public void setCgsl(String cgsl) {
		this.cgsl = cgsl;
	}
	
	@Length(min=0, max=32, message="供货单位长度必须介于 0 和 32 之间")
	public String getGhdwMc() {
		return ghdwMc;
	}

	public void setGhdwMc(String ghdwMc) {
		this.ghdwMc = ghdwMc;
	}
	
	@Length(min=0, max=8, message="单位代码长度必须介于 0 和 8 之间")
	public String getGhdwId() {
		return ghdwId;
	}

	public void setGhdwId(String ghdwId) {
		this.ghdwId = ghdwId;
	}
	
	public String getYpjj() {
		return ypjj;
	}

	public void setYpjj(String ypjj) {
		this.ypjj = ypjj;
	}
	
	@Length(min=0, max=24, message="memo长度必须介于 0 和 24 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public String getCkjj() {
		return ckjj;
	}

	public void setCkjj(String ckjj) {
		this.ckjj = ckjj;
	}
	
	@Length(min=0, max=6, message="采购标志长度必须介于 0 和 6 之间")
	public String getCgbz() {
		return cgbz;
	}

	public void setCgbz(String cgbz) {
		this.cgbz = cgbz;
	}
	
	public String getQldh() {
		return qldh;
	}

	public void setQldh(String qldh) {
		this.qldh = qldh;
	}
	
	@Length(min=0, max=4, message="审核科室长度必须介于 0 和 4 之间")
	public String getShks() {
		return shks;
	}

	public void setShks(String shks) {
		this.shks = shks;
	}
	
	public String getRksl() {
		return rksl;
	}

	public void setRksl(String rksl) {
		this.rksl = rksl;
	}
	
	public String getCksl() {
		return cksl;
	}

	public void setCksl(String cksl) {
		this.cksl = cksl;
	}
	
	@Length(min=0, max=32, message="存放位置长度必须介于 0 和 32 之间")
	public String getCfwz() {
		return cfwz;
	}

	public void setCfwz(String cfwz) {
		this.cfwz = cfwz;
	}
	
	public String getLrxh() {
		return lrxh;
	}

	public void setLrxh(String lrxh) {
		this.lrxh = lrxh;
	}
	
}