/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import com.alibaba.fastjson.annotation.JSONField;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 药品退货账单Entity
 * @author wg
 * @version 2018-08-14
 */
public class MedstoYpthzd extends DataEntity<MedstoYpthzd> {
	
	private static final long serialVersionUID = 1L;
	private String xh;		// 主键：序号
	private String ny;		// 年月
	private String djh;		// 单据号
	private String lrrq;		// 录入日期
	private String ksdm;		// 科室代码
	private String czyh;		// 操作员
	private String jzczyh;		// 记账员
	private String jzrq;		// 记账日期
	private String ghdwdm;		// 供货单位代码
	private String ghdwmc;		// 供货单位名称
	private String ypjePf;		// 批发金额
	private String ypjeLs;		// 零售金额
	private String thje;		// 退货金额
	private String jzbz;		// 记账标志
	private String jlzt;		// 记录状态
	private String fph;		// 发票号
	private String fprq;		// 发票日期
	private String memo;		// memo
	private String thfs;		// 退货方式
	private String fkxh;		// 付款序号
	private String jxje;		// 进销金额
	private String gzbz;		// 贵重标志
	private String dpbz;		// 到票标志
	private String dprq;		// 到票日期
	private String dpczyh;		// 到票员
	private String ysczyh;		// ysczyh
	private String remark;		// 备注
	
	public MedstoYpthzd() {
		super();
	}

	public MedstoYpthzd(String id){
		super(id);
	}

	@Length(min=1, max=12, message="主键：序号长度必须介于 1 和 12 之间")
	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}
	
	@Length(min=0, max=16, message="年月长度必须介于 0 和 16 之间")
	public String getNy() {
		return ny;
	}

	public void setNy(String ny) {
		this.ny = ny;
	}
	
	@Length(min=0, max=18, message="单据号长度必须介于 0 和 18 之间")
	public String getDjh() {
		return djh;
	}

	public void setDjh(String djh) {
		this.djh = djh;
	}
	
	@Length(min=0, max=16, message="录入日期长度必须介于 0 和 16 之间")
	public String getLrrq() {
		return lrrq;
	}

	public void setLrrq(String lrrq) {
		this.lrrq = lrrq;
	}
	
	@Length(min=0, max=4, message="科室代码长度必须介于 0 和 4 之间")
	public String getKsdm() {
		return ksdm;
	}

	public void setKsdm(String ksdm) {
		this.ksdm = ksdm;
	}
	
	@Length(min=0, max=6, message="操作员长度必须介于 0 和 6 之间")
	public String getCzyh() {
		return czyh;
	}

	public void setCzyh(String czyh) {
		this.czyh = czyh;
	}
	
	@Length(min=0, max=6, message="记账员长度必须介于 0 和 6 之间")
	public String getJzczyh() {
		return jzczyh;
	}

	public void setJzczyh(String jzczyh) {
		this.jzczyh = jzczyh;
	}
	
	@Length(min=0, max=16, message="记账日期长度必须介于 0 和 16 之间")
	public String getJzrq() {
		return jzrq;
	}

	public void setJzrq(String jzrq) {
		this.jzrq = jzrq;
	}
	
	@Length(min=0, max=8, message="供货单位代码长度必须介于 0 和 8 之间")
	public String getGhdwdm() {
		return ghdwdm;
	}

	public void setGhdwdm(String ghdwdm) {
		this.ghdwdm = ghdwdm;
	}
	
	@Length(min=0, max=32, message="供货单位名称长度必须介于 0 和 32 之间")
	public String getGhdwmc() {
		return ghdwmc;
	}

	public void setGhdwmc(String ghdwmc) {
		this.ghdwmc = ghdwmc;
	}
	
	public String getYpjePf() {
		return ypjePf;
	}

	@JSONField(name="ypje_pf")
	public void setYpjePf(String ypjePf) {
		this.ypjePf = ypjePf;
	}
	
	public String getYpjeLs() {
		return ypjeLs;
	}

	@JSONField(name="ypje_ls")
	public void setYpjeLs(String ypjeLs) {
		this.ypjeLs = ypjeLs;
	}
	
	public String getThje() {
		return thje;
	}

	public void setThje(String thje) {
		this.thje = thje;
	}
	
	@Length(min=0, max=6, message="记账标志长度必须介于 0 和 6 之间")
	public String getJzbz() {
		return jzbz;
	}

	public void setJzbz(String jzbz) {
		this.jzbz = jzbz;
	}
	
	@Length(min=0, max=6, message="记录状态长度必须介于 0 和 6 之间")
	public String getJlzt() {
		return jlzt;
	}

	public void setJlzt(String jlzt) {
		this.jlzt = jlzt;
	}
	
	@Length(min=0, max=18, message="发票号长度必须介于 0 和 18 之间")
	public String getFph() {
		return fph;
	}

	public void setFph(String fph) {
		this.fph = fph;
	}
	
	@Length(min=0, max=8, message="发票日期长度必须介于 0 和 8 之间")
	public String getFprq() {
		return fprq;
	}

	public void setFprq(String fprq) {
		this.fprq = fprq;
	}
	
	@Length(min=0, max=24, message="memo长度必须介于 0 和 24 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Length(min=0, max=2, message="退货方式长度必须介于 0 和 2 之间")
	public String getThfs() {
		return thfs;
	}

	public void setThfs(String thfs) {
		this.thfs = thfs;
	}
	
	@Length(min=0, max=12, message="付款序号长度必须介于 0 和 12 之间")
	public String getFkxh() {
		return fkxh;
	}

	public void setFkxh(String fkxh) {
		this.fkxh = fkxh;
	}
	
	public String getJxje() {
		return jxje;
	}

	public void setJxje(String jxje) {
		this.jxje = jxje;
	}
	
	@Length(min=0, max=6, message="贵重标志长度必须介于 0 和 6 之间")
	public String getGzbz() {
		return gzbz;
	}

	public void setGzbz(String gzbz) {
		this.gzbz = gzbz;
	}
	
	@Length(min=0, max=6, message="到票标志长度必须介于 0 和 6 之间")
	public String getDpbz() {
		return dpbz;
	}

	public void setDpbz(String dpbz) {
		this.dpbz = dpbz;
	}
	
	@Length(min=0, max=8, message="到票日期长度必须介于 0 和 8 之间")
	public String getDprq() {
		return dprq;
	}

	public void setDprq(String dprq) {
		this.dprq = dprq;
	}
	
	@Length(min=0, max=6, message="到票员长度必须介于 0 和 6 之间")
	public String getDpczyh() {
		return dpczyh;
	}

	public void setDpczyh(String dpczyh) {
		this.dpczyh = dpczyh;
	}
	
	@Length(min=0, max=6, message="ysczyh长度必须介于 0 和 6 之间")
	public String getYsczyh() {
		return ysczyh;
	}

	public void setYsczyh(String ysczyh) {
		this.ysczyh = ysczyh;
	}
	
	@Length(min=0, max=200, message="备注长度必须介于 0 和 200 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}