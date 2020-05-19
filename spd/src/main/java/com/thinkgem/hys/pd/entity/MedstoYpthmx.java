/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import com.alibaba.fastjson.annotation.JSONField;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 药品退货明细Entity
 * @author wg
 * @version 2018-08-14
 */
public class MedstoYpthmx extends DataEntity<MedstoYpthmx> {
	
	private static final long serialVersionUID = 1L;
	private String xh;		// 主键：序号
	private String zdXh;		// 账单序号
	private String czdm;		// 操作代码
	private String cdIdm;		// 产地idm
	private String ggIdm;		// 规格idm
	private String ypmc;		// 药品名称
	private String ypgg;		// 药品规格
	private String ypdm;		// 药品代码
	private String ykxs;		// 药库系数
	private String ypdw;		// 药品单位
	private String dwxs;		// 单位系数
	private String thsl;		// 退货数量
	private String ypfj;		// 批发价
	private String ylsj;		// 零售价
	private String thdj;		// 退货单价
	private String thje;		// 退货金额
	private String jxce;		// 进销差额
	private String memo;		// memo
	private String pcxh;		// 批次序号
	private String ph;		// 批号
	private String sxrq;		// 失效日期
	private String fph;		// 发票号
	private String ypjj;		// 药品进价
	private String thpfj;		// 退货批发价
	private String remark;		// 备注
	
	public MedstoYpthmx() {
		super();
	}

	public MedstoYpthmx(String id){
		super(id);
	}

	@Length(min=1, max=12, message="主键：序号长度必须介于 1 和 12 之间")
	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}
	
	@Length(min=0, max=12, message="账单序号长度必须介于 0 和 12 之间")
	public String getZdXh() {
		return zdXh;
	}

	@JSONField(name="zd_xh")
	public void setZdXh(String zdXh) {
		this.zdXh = zdXh;
	}
	
	@Length(min=0, max=9, message="操作代码长度必须介于 0 和 9 之间")
	public String getCzdm() {
		return czdm;
	}

	public void setCzdm(String czdm) {
		this.czdm = czdm;
	}
	
	@Length(min=0, max=9, message="产地idm长度必须介于 0 和 9 之间")
	public String getCdIdm() {
		return cdIdm;
	}

	@JSONField(name="cd_idm")
	public void setCdIdm(String cdIdm) {
		this.cdIdm = cdIdm;
	}
	
	@Length(min=0, max=9, message="规格idm长度必须介于 0 和 9 之间")
	public String getGgIdm() {
		return ggIdm;
	}

	@JSONField(name="gg_idm")
	public void setGgIdm(String ggIdm) {
		this.ggIdm = ggIdm;
	}
	
	@Length(min=0, max=64, message="药品名称长度必须介于 0 和 64 之间")
	public String getYpmc() {
		return ypmc;
	}

	public void setYpmc(String ypmc) {
		this.ypmc = ypmc;
	}
	
	@Length(min=0, max=32, message="药品规格长度必须介于 0 和 32 之间")
	public String getYpgg() {
		return ypgg;
	}

	public void setYpgg(String ypgg) {
		this.ypgg = ypgg;
	}
	
	@Length(min=0, max=18, message="药品代码长度必须介于 0 和 18 之间")
	public String getYpdm() {
		return ypdm;
	}

	public void setYpdm(String ypdm) {
		this.ypdm = ypdm;
	}
	
	@Length(min=0, max=12, message="药库系数长度必须介于 0 和 12 之间")
	public String getYkxs() {
		return ykxs;
	}

	public void setYkxs(String ykxs) {
		this.ykxs = ykxs;
	}
	
	@Length(min=0, max=8, message="药品单位长度必须介于 0 和 8 之间")
	public String getYpdw() {
		return ypdw;
	}

	public void setYpdw(String ypdw) {
		this.ypdw = ypdw;
	}
	
	@Length(min=0, max=12, message="单位系数长度必须介于 0 和 12 之间")
	public String getDwxs() {
		return dwxs;
	}

	public void setDwxs(String dwxs) {
		this.dwxs = dwxs;
	}
	
	@Length(min=0, max=10, message="退货数量长度必须介于 0 和 10 之间")
	public String getThsl() {
		return thsl;
	}

	public void setThsl(String thsl) {
		this.thsl = thsl;
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
	
	public String getThdj() {
		return thdj;
	}

	public void setThdj(String thdj) {
		this.thdj = thdj;
	}
	
	public String getThje() {
		return thje;
	}

	public void setThje(String thje) {
		this.thje = thje;
	}
	
	public String getJxce() {
		return jxce;
	}

	public void setJxce(String jxce) {
		this.jxce = jxce;
	}
	
	@Length(min=0, max=24, message="memo长度必须介于 0 和 24 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Length(min=0, max=12, message="批次序号长度必须介于 0 和 12 之间")
	public String getPcxh() {
		return pcxh;
	}

	public void setPcxh(String pcxh) {
		this.pcxh = pcxh;
	}
	
	@Length(min=0, max=16, message="批号长度必须介于 0 和 16 之间")
	public String getPh() {
		return ph;
	}

	public void setPh(String ph) {
		this.ph = ph;
	}
	
	@Length(min=0, max=8, message="失效日期长度必须介于 0 和 8 之间")
	public String getSxrq() {
		return sxrq;
	}

	public void setSxrq(String sxrq) {
		this.sxrq = sxrq;
	}
	
	@Length(min=0, max=18, message="发票号长度必须介于 0 和 18 之间")
	public String getFph() {
		return fph;
	}

	public void setFph(String fph) {
		this.fph = fph;
	}
	
	public String getYpjj() {
		return ypjj;
	}

	public void setYpjj(String ypjj) {
		this.ypjj = ypjj;
	}
	
	public String getThpfj() {
		return thpfj;
	}

	public void setThpfj(String thpfj) {
		this.thpfj = thpfj;
	}
	
	@Length(min=0, max=200, message="备注长度必须介于 0 和 200 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}