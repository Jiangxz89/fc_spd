/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 药房发药单明细Entity
 * @author zxh
 * @version 2019-09-20
 */
public class DataYfexedetail extends DataEntity<DataYfexedetail> {
	
	private static final long serialVersionUID = 1L;
	private String masterid;		// 记录ID
	private String feeid;		// 项目ID
	private String feename;		// 项目名称
	private String feespec;		// 项目规格
	private String facid;		// 厂家ID
	private String facname;		// 厂家名称
	private String unit;		// 单位
	private String fuqty;		// 副数
	private String amount;		// 发药数量
	private String price;		// 单价
	private String totalamt;		// 总金额
	private String diagndept;		// 开方科室ID
	private String diagndeptname;		// 开方科室名称
	private String dosage;		// 用量
	private String injecttimes;		// 注射次数
	private String channelid;		// 用法ID
	private String channelname;		// 用法名称
	private String frequencryid;		// 频次ID
	private String frequencryname;		// 频次名称
	private String dosageunit;		// 用量单位
	private String days;		// 天数
	private String groupid;		// 分组
	private String num;		// 中药剂数
	private String listno;		// 方号
	private String status;		// 状态
	private String batch;		// 批次
	private String dspt;		// 备注
	private String istake;		// 是否自备药
	private String presamount;		// 发药数量
	private String presamountunit;		// 发药单位
	
	public DataYfexedetail() {
		super();
	}

	public DataYfexedetail(String id){
		super(id);
	}

	@Length(min=1, max=11, message="记录ID长度必须介于 1 和 11 之间")
	public String getMasterid() {
		return masterid;
	}

	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}
	
	@Length(min=1, max=11, message="项目ID长度必须介于 1 和 11 之间")
	public String getFeeid() {
		return feeid;
	}

	public void setFeeid(String feeid) {
		this.feeid = feeid;
	}
	
	@Length(min=1, max=60, message="项目名称长度必须介于 1 和 60 之间")
	public String getFeename() {
		return feename;
	}

	public void setFeename(String feename) {
		this.feename = feename;
	}
	
	@Length(min=1, max=60, message="项目规格长度必须介于 1 和 60 之间")
	public String getFeespec() {
		return feespec;
	}

	public void setFeespec(String feespec) {
		this.feespec = feespec;
	}
	
	@Length(min=1, max=11, message="厂家ID长度必须介于 1 和 11 之间")
	public String getFacid() {
		return facid;
	}

	public void setFacid(String facid) {
		this.facid = facid;
	}
	
	@Length(min=1, max=150, message="厂家名称长度必须介于 1 和 150 之间")
	public String getFacname() {
		return facname;
	}

	public void setFacname(String facname) {
		this.facname = facname;
	}
	
	@Length(min=1, max=20, message="单位长度必须介于 1 和 20 之间")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	@Length(min=1, max=6, message="副数长度必须介于 1 和 6 之间")
	public String getFuqty() {
		return fuqty;
	}

	public void setFuqty(String fuqty) {
		this.fuqty = fuqty;
	}
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getTotalamt() {
		return totalamt;
	}

	public void setTotalamt(String totalamt) {
		this.totalamt = totalamt;
	}
	
	@Length(min=0, max=10, message="开方科室ID长度必须介于 0 和 10 之间")
	public String getDiagndept() {
		return diagndept;
	}

	public void setDiagndept(String diagndept) {
		this.diagndept = diagndept;
	}
	
	@Length(min=0, max=20, message="开方科室名称长度必须介于 0 和 20 之间")
	public String getDiagndeptname() {
		return diagndeptname;
	}

	public void setDiagndeptname(String diagndeptname) {
		this.diagndeptname = diagndeptname;
	}
	
	@Length(min=1, max=10, message="用量长度必须介于 1 和 10 之间")
	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	
	@Length(min=1, max=11, message="注射次数长度必须介于 1 和 11 之间")
	public String getInjecttimes() {
		return injecttimes;
	}

	public void setInjecttimes(String injecttimes) {
		this.injecttimes = injecttimes;
	}
	
	@Length(min=1, max=11, message="用法ID长度必须介于 1 和 11 之间")
	public String getChannelid() {
		return channelid;
	}

	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}
	
	@Length(min=1, max=100, message="用法名称长度必须介于 1 和 100 之间")
	public String getChannelname() {
		return channelname;
	}

	public void setChannelname(String channelname) {
		this.channelname = channelname;
	}
	
	@Length(min=1, max=11, message="频次ID长度必须介于 1 和 11 之间")
	public String getFrequencryid() {
		return frequencryid;
	}

	public void setFrequencryid(String frequencryid) {
		this.frequencryid = frequencryid;
	}
	
	@Length(min=1, max=40, message="频次名称长度必须介于 1 和 40 之间")
	public String getFrequencryname() {
		return frequencryname;
	}

	public void setFrequencryname(String frequencryname) {
		this.frequencryname = frequencryname;
	}
	
	@Length(min=1, max=10, message="用量单位长度必须介于 1 和 10 之间")
	public String getDosageunit() {
		return dosageunit;
	}

	public void setDosageunit(String dosageunit) {
		this.dosageunit = dosageunit;
	}
	
	@Length(min=1, max=11, message="天数长度必须介于 1 和 11 之间")
	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}
	
	@Length(min=1, max=11, message="分组长度必须介于 1 和 11 之间")
	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	
	@Length(min=1, max=11, message="中药剂数长度必须介于 1 和 11 之间")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	@Length(min=1, max=11, message="方号长度必须介于 1 和 11 之间")
	public String getListno() {
		return listno;
	}

	public void setListno(String listno) {
		this.listno = listno;
	}
	
	@Length(min=0, max=11, message="状态长度必须介于 0 和 11 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=100, message="批次长度必须介于 0 和 100 之间")
	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}
	
	@Length(min=0, max=100, message="备注长度必须介于 0 和 100 之间")
	public String getDspt() {
		return dspt;
	}

	public void setDspt(String dspt) {
		this.dspt = dspt;
	}
	
	@Length(min=0, max=4, message="是否自备药长度必须介于 0 和 4 之间")
	public String getIstake() {
		return istake;
	}

	public void setIstake(String istake) {
		this.istake = istake;
	}
	
	public String getPresamount() {
		return presamount;
	}

	public void setPresamount(String presamount) {
		this.presamount = presamount;
	}
	
	@Length(min=0, max=20, message="发药单位长度必须介于 0 和 20 之间")
	public String getPresamountunit() {
		return presamountunit;
	}

	public void setPresamountunit(String presamountunit) {
		this.presamountunit = presamountunit;
	}
	
}