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
 * 药品出库单Entity
 * @author zxh
 * @version 2019-05-07
 */
public class MedstoYpckzd extends DataEntity<MedstoYpckzd> {
	
	private static final long serialVersionUID = 1L;
	private String xh;		// 序号
	private String rkdm;		// 出库代码
	private Date ny;		// 年月
	private String rkdh;		// 出库单号
	private String ksdm;		// 科室代码
	private String fph;		// 发票号
	private Date kprq;		// 开票日期
	private Date dprq;		// 到票日期
	private Date rkrq;		// 出库日期
	private String rkczyh;		// 出库操作员
	private Date jzrq;		// 记账日期
	private String jzczyh;		// 记账操作员
	private String lsje;		// 零售金额
	private String pfje;		// 批发金额
	private String jjje;		// 进价金额
	private String yhje;		// 优惠金额
	private String ghdwdm;		// 供货单位
	private String ghdwmc;		// 单位名称
	private String jzbz;		// 记账标志
	private String gzbz;		// 贵重标志
	private String dpbz;		// 到票标志
	private String cxbz;		// 撤销标志
	private String jlzt;		// 记录状态
	private String xgxh;		// 相关序号
	private String memo;		// 备注
	private String ypzlsm;		// 药品质量说明
	private String cgxh;		// 采购序号
	private String ypbz;		// 药品标志
	private String fkxh;		// 付款序号
	private String dybz;		// 打印标志
	private String psdh;		// 配送单号
	private Date scrq;		// 上传日期

	//冗余
	private String drugName;//药品名称
	private String drugSpec;		// 药品规格

	private MedstoYpckmx medstoYpckmx;//药品出库明细

	public MedstoYpckzd() {
		super();
	}

	public MedstoYpckzd(String id){
		super(id);
	}

	@Length(min=1, max=60, message="序号长度必须介于 1 和 60 之间")
	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}
	
	@Length(min=1, max=60, message="出库代码长度必须介于 1 和 60 之间")
	public String getRkdm() {
		return rkdm;
	}

	public void setRkdm(String rkdm) {
		this.rkdm = rkdm;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="年月不能为空")
	public Date getNy() {
		return ny;
	}

	public void setNy(Date ny) {
		this.ny = ny;
	}
	
	@Length(min=1, max=60, message="出库单号长度必须介于 1 和 60 之间")
	public String getRkdh() {
		return rkdh;
	}

	public void setRkdh(String rkdh) {
		this.rkdh = rkdh;
	}
	
	@Length(min=1, max=60, message="科室代码长度必须介于 1 和 60 之间")
	public String getKsdm() {
		return ksdm;
	}

	public void setKsdm(String ksdm) {
		this.ksdm = ksdm;
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="出库日期不能为空")
	public Date getRkrq() {
		return rkrq;
	}

	public void setRkrq(Date rkrq) {
		this.rkrq = rkrq;
	}
	
	@Length(min=1, max=60, message="出库操作员长度必须介于 1 和 60 之间")
	public String getRkczyh() {
		return rkczyh;
	}

	public void setRkczyh(String rkczyh) {
		this.rkczyh = rkczyh;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getJzrq() {
		return jzrq;
	}

	public void setJzrq(Date jzrq) {
		this.jzrq = jzrq;
	}
	
	@Length(min=0, max=60, message="记账操作员长度必须介于 0 和 60 之间")
	public String getJzczyh() {
		return jzczyh;
	}

	public void setJzczyh(String jzczyh) {
		this.jzczyh = jzczyh;
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
	
	public String getJjje() {
		return jjje;
	}

	public void setJjje(String jjje) {
		this.jjje = jjje;
	}
	
	public String getYhje() {
		return yhje;
	}

	public void setYhje(String yhje) {
		this.yhje = yhje;
	}
	
	@Length(min=0, max=60, message="供货单位长度必须介于 0 和 60 之间")
	public String getGhdwdm() {
		return ghdwdm;
	}

	public void setGhdwdm(String ghdwdm) {
		this.ghdwdm = ghdwdm;
	}
	
	@Length(min=0, max=200, message="单位名称长度必须介于 0 和 200 之间")
	public String getGhdwmc() {
		return ghdwmc;
	}

	public void setGhdwmc(String ghdwmc) {
		this.ghdwmc = ghdwmc;
	}
	
	@Length(min=1, max=11, message="记账标志长度必须介于 1 和 11 之间")
	public String getJzbz() {
		return jzbz;
	}

	public void setJzbz(String jzbz) {
		this.jzbz = jzbz;
	}
	
	@Length(min=1, max=11, message="贵重标志长度必须介于 1 和 11 之间")
	public String getGzbz() {
		return gzbz;
	}

	public void setGzbz(String gzbz) {
		this.gzbz = gzbz;
	}
	
	@Length(min=1, max=11, message="到票标志长度必须介于 1 和 11 之间")
	public String getDpbz() {
		return dpbz;
	}

	public void setDpbz(String dpbz) {
		this.dpbz = dpbz;
	}
	
	@Length(min=1, max=11, message="撤销标志长度必须介于 1 和 11 之间")
	public String getCxbz() {
		return cxbz;
	}

	public void setCxbz(String cxbz) {
		this.cxbz = cxbz;
	}
	
	@Length(min=1, max=11, message="记录状态长度必须介于 1 和 11 之间")
	public String getJlzt() {
		return jlzt;
	}

	public void setJlzt(String jlzt) {
		this.jlzt = jlzt;
	}
	
	@Length(min=0, max=11, message="相关序号长度必须介于 0 和 11 之间")
	public String getXgxh() {
		return xgxh;
	}

	public void setXgxh(String xgxh) {
		this.xgxh = xgxh;
	}
	
	@Length(min=0, max=200, message="备注长度必须介于 0 和 200 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Length(min=0, max=200, message="药品质量说明长度必须介于 0 和 200 之间")
	public String getYpzlsm() {
		return ypzlsm;
	}

	public void setYpzlsm(String ypzlsm) {
		this.ypzlsm = ypzlsm;
	}
	
	@Length(min=0, max=11, message="采购序号长度必须介于 0 和 11 之间")
	public String getCgxh() {
		return cgxh;
	}

	public void setCgxh(String cgxh) {
		this.cgxh = cgxh;
	}
	
	@Length(min=0, max=11, message="药品标志长度必须介于 0 和 11 之间")
	public String getYpbz() {
		return ypbz;
	}

	public void setYpbz(String ypbz) {
		this.ypbz = ypbz;
	}
	
	@Length(min=0, max=11, message="付款序号长度必须介于 0 和 11 之间")
	public String getFkxh() {
		return fkxh;
	}

	public void setFkxh(String fkxh) {
		this.fkxh = fkxh;
	}
	
	@Length(min=0, max=11, message="打印标志长度必须介于 0 和 11 之间")
	public String getDybz() {
		return dybz;
	}

	public void setDybz(String dybz) {
		this.dybz = dybz;
	}
	
	@Length(min=0, max=50, message="配送单号长度必须介于 0 和 50 之间")
	public String getPsdh() {
		return psdh;
	}

	public void setPsdh(String psdh) {
		this.psdh = psdh;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getScrq() {
		return scrq;
	}

	public void setScrq(Date scrq) {
		this.scrq = scrq;
	}

	public MedstoYpckmx getMedstoYpckmx() {
		return medstoYpckmx;
	}

	public void setMedstoYpckmx(MedstoYpckmx medstoYpckmx) {
		this.medstoYpckmx = medstoYpckmx;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getDrugSpec() {
		return drugSpec;
	}

	public void setDrugSpec(String drugSpec) {
		this.drugSpec = drugSpec;
	}
}