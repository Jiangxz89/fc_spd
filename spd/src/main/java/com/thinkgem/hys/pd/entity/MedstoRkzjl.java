/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 入库主记录Entity
 * @author zxh
 * @version 2019-05-14
 */
public class MedstoRkzjl extends DataEntity<MedstoRkzjl> {
	
	private static final long serialVersionUID = 1L;
	private String xh;		// 序号
	private String jgbm;		// 机构编码
	private String storeId;		// 药库编码
	private String storeName;		// 药库名称
	private String entryNo;		// 入库单编号
	private String deliveryNo;		// 配送单编号
	private String operName;		// 经手人
	private String checkName;		// 审核员
	private String checkFlag;		// 审核标记:0未审核/1已审核
	private String fph;		// 发票号
	private Date rkrq;		// 入库日期
	private String jjje;		// 进价金额
	private String lsje;		// 零售金额
	private String producerName;		// 供货商名称
	private Date scrq;		// 业务数据更新到前置库的时间，时间格式(24小时制)：yyyy-mm-dd hh:mm:ss

	//冗余
	private MedstoYprkmxNew medstoYprkmxNew;//药品入库明细
	private String drugName;//药品名称
	private String drugSpec;		// 药品规格

	public MedstoRkzjl() {
		super();
	}

	public MedstoRkzjl(String id){
		super(id);
	}

	@Length(min=1, max=60, message="序号长度必须介于 1 和 60 之间")
	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}
	
	@Length(min=1, max=60, message="机构编码长度必须介于 1 和 60 之间")
	public String getJgbm() {
		return jgbm;
	}

	public void setJgbm(String jgbm) {
		this.jgbm = jgbm;
	}
	
	@Length(min=1, max=30, message="药库编码长度必须介于 1 和 30 之间")
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=1, max=30, message="药库名称长度必须介于 1 和 30 之间")
	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
	@Length(min=1, max=30, message="入库单编号长度必须介于 1 和 30 之间")
	public String getEntryNo() {
		return entryNo;
	}

	public void setEntryNo(String entryNo) {
		this.entryNo = entryNo;
	}
	
	@Length(min=1, max=30, message="配送单编号长度必须介于 1 和 30 之间")
	public String getDeliveryNo() {
		return deliveryNo;
	}

	public void setDeliveryNo(String deliveryNo) {
		this.deliveryNo = deliveryNo;
	}
	
	@Length(min=0, max=30, message="经手人长度必须介于 0 和 30 之间")
	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}
	
	@Length(min=0, max=30, message="审核员长度必须介于 0 和 30 之间")
	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}
	
	@Length(min=1, max=1, message="审核标记:0未审核/1已审核长度必须介于 1 和 1 之间")
	public String getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}
	
	@Length(min=0, max=60, message="发票号长度必须介于 0 和 60 之间")
	public String getFph() {
		return fph;
	}

	public void setFph(String fph) {
		this.fph = fph;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRkrq() {
		return rkrq;
	}

	public void setRkrq(Date rkrq) {
		this.rkrq = rkrq;
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
	
	@Length(min=0, max=100, message="供货商名称长度必须介于 0 和 100 之间")
	public String getProducerName() {
		return producerName;
	}

	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getScrq() {
		return scrq;
	}

	public void setScrq(Date scrq) {
		this.scrq = scrq;
	}

	public MedstoYprkmxNew getMedstoYprkmxNew() {
		return medstoYprkmxNew;
	}

	public void setMedstoYprkmxNew(MedstoYprkmxNew medstoYprkmxNew) {
		this.medstoYprkmxNew = medstoYprkmxNew;
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