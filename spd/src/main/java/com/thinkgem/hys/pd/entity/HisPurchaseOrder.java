/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * his_purchase_orderEntity
 * @author jiangxz
 * @version 2019-05-06
 */
public class HisPurchaseOrder extends DataEntity<HisPurchaseOrder> {
	
	private static final long serialVersionUID = 1L;
	private String jgbm;		// 机构编码
	private String purNo;		// 采购单号
	private String purDate;		// 采购日期
	private String operName;		// 经手人
	private String checkName;		// 审核人
	private int synFlag ;   //同步标志，0-未同步；1-已同步

	List<HisPurchaseOrderItem> itemList;//明细列表

	public HisPurchaseOrder() {
		super();
		this.synFlag = MinaGlobalConstants.SYN_NONE;
	}

	public HisPurchaseOrder(String id){
		super(id);
	}

	@Length(min=0, max=64, message="机构编码长度必须介于 0 和 64 之间")
	public String getJgbm() {
		return jgbm;
	}

	public void setJgbm(String jgbm) {
		this.jgbm = jgbm;
	}
	
	@Length(min=0, max=64, message="采购单号长度必须介于 0 和 64 之间")
	public String getPurNo() {
		return purNo;
	}

	public void setPurNo(String purNo) {
		this.purNo = purNo;
	}
	
	@Length(min=0, max=50, message="采购日期长度必须介于 0 和 50 之间")
	public String getPurDate() {
		return purDate;
	}

	public void setPurDate(String purDate) {
		this.purDate = purDate;
	}
	
	@Length(min=0, max=50, message="经手人长度必须介于 0 和 50 之间")
	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}
	
	@Length(min=0, max=50, message="审核人长度必须介于 0 和 50 之间")
	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	public List<HisPurchaseOrderItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<HisPurchaseOrderItem> itemList) {
		this.itemList = itemList;
	}

	public int getSynFlag() {
		return synFlag;
	}

	public void setSynFlag(int synFlag) {
		this.synFlag = synFlag;
	}
}