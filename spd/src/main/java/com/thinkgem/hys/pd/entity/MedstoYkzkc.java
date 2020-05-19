/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import com.alibaba.fastjson.annotation.JSONField;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 药库总库存Entity
 * @author sutianqi
 * @version 2018-07-31
 */
public class MedstoYkzkc extends DataEntity<MedstoYkzkc> {
	
	private static final long serialVersionUID = 1L;
	private String cdIdm;		// 产地idm
	private String ksdm;		// 科室代码
	private String ggIdm;		// 规格idm
	private String lcIdm;		// lc_idm
	private String kcsl;		// 库存数量
	private String lsje;		// 零售金额
	private String pfje;		// 批发金额
	private String jxje;		// 进销金额
	private String cfwz;		// 存放位置
	private String kzbz;		// 控制标志0:不控制，1：控制
	private String tybz;		// 停用标志0：不停用，1：停用
	private String tsbz;		// tsbz
	private String gzbz;		// 贵重标志0:普通1：贵重
	private String memo;		// 备注
	private String djsl;		// 冻结数量
	private String czyh;		// 操作员号
	private String czrq;		// 操作日期
	private String gldj;		// gldj
	private String kzry;		// kzry
	
	public MedstoYkzkc() {
		super();
	}

	public MedstoYkzkc(String id){
		super(id);
	}

	public String getCdIdm() {
		return cdIdm;
	}
	@JSONField(name="cd_idm")
	public void setCdIdm(String cdIdm) {
		this.cdIdm = cdIdm;
	}
	
	@Length(min=1, max=4, message="科室代码长度必须介于 1 和 4 之间")
	public String getKsdm() {
		return ksdm;
	}

	public void setKsdm(String ksdm) {
		this.ksdm = ksdm;
	}
	
	public String getGgIdm() {
		return ggIdm;
	}

	@JSONField(name="gg_idm")
	public void setGgIdm(String ggIdm) {
		this.ggIdm = ggIdm;
	}
	
	public String getLcIdm() {
		return lcIdm;
	}

	@JSONField(name="lc_idm")
	public void setLcIdm(String lcIdm) {
		this.lcIdm = lcIdm;
	}
	
	public String getKcsl() {
		return kcsl;
	}

	public void setKcsl(String kcsl) {
		this.kcsl = kcsl;
	}
	
	public String getLsje() {
		return lsje;
	}

	public void setLsje(String lsje) {
		this.lsje = lsje;
	}
	
	public String getPfje() {
		return pfje;
	}

	public void setPfje(String pfje) {
		this.pfje = pfje;
	}
	
	public String getJxje() {
		return jxje;
	}

	public void setJxje(String jxje) {
		this.jxje = jxje;
	}
	
	@Length(min=0, max=32, message="存放位置长度必须介于 0 和 32 之间")
	public String getCfwz() {
		return cfwz;
	}

	public void setCfwz(String cfwz) {
		this.cfwz = cfwz;
	}
	
	@Length(min=1, max=6, message="控制标志0:不控制，1：控制长度必须介于 1 和 6 之间")
	public String getKzbz() {
		return kzbz;
	}

	public void setKzbz(String kzbz) {
		this.kzbz = kzbz;
	}
	
	@Length(min=0, max=6, message="停用标志0：不停用，1：停用长度必须介于 0 和 6 之间")
	public String getTybz() {
		return tybz;
	}

	public void setTybz(String tybz) {
		this.tybz = tybz;
	}
	
	@Length(min=0, max=6, message="tsbz长度必须介于 0 和 6 之间")
	public String getTsbz() {
		return tsbz;
	}

	public void setTsbz(String tsbz) {
		this.tsbz = tsbz;
	}
	
	@Length(min=0, max=6, message="贵重标志0:普通1：贵重长度必须介于 0 和 6 之间")
	public String getGzbz() {
		return gzbz;
	}

	public void setGzbz(String gzbz) {
		this.gzbz = gzbz;
	}
	
	@Length(min=0, max=24, message="备注长度必须介于 0 和 24 之间")
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
	
	@Length(min=0, max=6, message="操作员号长度必须介于 0 和 6 之间")
	public String getCzyh() {
		return czyh;
	}

	public void setCzyh(String czyh) {
		this.czyh = czyh;
	}
	
	@Length(min=0, max=16, message="操作日期长度必须介于 0 和 16 之间")
	public String getCzrq() {
		return czrq;
	}

	public void setCzrq(String czrq) {
		this.czrq = czrq;
	}
	
	@Length(min=0, max=6, message="gldj长度必须介于 0 和 6 之间")
	public String getGldj() {
		return gldj;
	}

	public void setGldj(String gldj) {
		this.gldj = gldj;
	}
	
	@Length(min=0, max=32, message="kzry长度必须介于 0 和 32 之间")
	public String getKzry() {
		return kzry;
	}

	public void setKzry(String kzry) {
		this.kzry = kzry;
	}
	
}