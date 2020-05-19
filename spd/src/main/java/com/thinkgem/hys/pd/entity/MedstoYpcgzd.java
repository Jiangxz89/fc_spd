/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 药库采购单Entity
 * @author sutianqi
 * @version 2018-07-31
 */
public class MedstoYpcgzd extends DataEntity<MedstoYpcgzd> {
	
	private static final long serialVersionUID = 1L;
	private String xh;		// 序号
	private String czyh;		// 操作员号
	private String cjrq;		// 创建日期
	private String djh;		// 单据号
	private String ksdm;		// 科室代码
	private String shry;		// 审核人员
	private String shrq;		// 审核日期
	private String jlzt;		// 记录状态
	private String jzbz;		// 记账标志
	private String memo;		// 备注
	private String jjje;		// 进价金额
	private String lsje;		// 零售金额
	private String pfje;		// 批发金额
	private String tsypglbz;		// 特殊药品贵重标志
	private String hospitalCode;//医院代码
	private String hospitalName;//医院名称
	private String status;//药品同步状态（同步到中心平台）
	
	
	private List<MedstoYpcgmx> purchaseDetailList;
	
	public MedstoYpcgzd() {
		super();
		this.hospitalCode = Global.getConfig("HOSPITAL_NUMBER");
		this.hospitalName = Global.getConfig("HOSPITAL_NAME");
	}

	public MedstoYpcgzd(String id){
		super(id);
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}
	
	@Length(min=1, max=6, message="操作员号长度必须介于 1 和 6 之间")
	public String getCzyh() {
		return czyh;
	}

	public void setCzyh(String czyh) {
		this.czyh = czyh;
	}
	
	public String getCjrq() {
		return cjrq;
	}

	public void setCjrq(String cjrq) {
		this.cjrq = cjrq;
	}
	
	@Length(min=0, max=18, message="单据号长度必须介于 0 和 18 之间")
	public String getDjh() {
		return djh;
	}

	public void setDjh(String djh) {
		this.djh = djh;
	}
	
	@Length(min=1, max=4, message="科室代码长度必须介于 1 和 4 之间")
	public String getKsdm() {
		return ksdm;
	}

	public void setKsdm(String ksdm) {
		this.ksdm = ksdm;
	}
	
	@Length(min=0, max=6, message="审核人员长度必须介于 0 和 6 之间")
	public String getShry() {
		return shry;
	}

	public void setShry(String shry) {
		this.shry = shry;
	}
	
	@Length(min=0, max=16, message="审核日期长度必须介于 0 和 16 之间")
	public String getShrq() {
		return shrq;
	}

	public void setShrq(String shrq) {
		this.shrq = shrq;
	}
	
	@Length(min=1, max=6, message="记录状态长度必须介于 1 和 6 之间")
	public String getJlzt() {
		return jlzt;
	}

	public void setJlzt(String jlzt) {
		this.jlzt = jlzt;
	}
	
	@Length(min=1, max=6, message="记账标志长度必须介于 1 和 6 之间")
	public String getJzbz() {
		return jzbz;
	}

	public void setJzbz(String jzbz) {
		this.jzbz = jzbz;
	}
	
	@Length(min=0, max=24, message="备注长度必须介于 0 和 24 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public String getJjje() {
		return jjje;
	}

	public void setJjje(String jjje) {
		this.jjje = jjje;
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
	
	@Length(min=0, max=6, message="特殊药品贵重标志长度必须介于 0 和 6 之间")
	public String getTsypglbz() {
		return tsypglbz;
	}

	public void setTsypglbz(String tsypglbz) {
		this.tsypglbz = tsypglbz;
	}

	public List<MedstoYpcgmx> getPurchaseDetailList() {
		return purchaseDetailList;
	}

	public void setPurchaseDetailList(List<MedstoYpcgmx> purchaseDetailList) {
		this.purchaseDetailList = purchaseDetailList;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}