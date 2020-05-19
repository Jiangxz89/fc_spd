/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 退货信息Entity
 * @author yueguoyun
 * @version 2018-04-29
 */
public class PdRejected extends DataEntity<PdRejected> {

	private static final long serialVersionUID = 1L;
	private String rejectedNo;		// rejected_no
	private String rejectedDate;		// rejected_date
	private String storeroomId;		// storeroom_id
	private String storeroomName;		// storeroom_name
	private String supplierId;		// supplier_id
	private String supplierName;		// supplier_name
	private String operPerson;		// oper_person
	private String isEnd;		// is_end
		
	private List<PdRejectedDetail> productList;	  //产品记录list
	
	public PdRejected() {
		super();
	}

	public PdRejected(String id){
		super(id);
	}

	@Length(min=0, max=100, message="rejected_no长度必须介于 0 和 100 之间")
	public String getRejectedNo() {
		return rejectedNo;
	}

	public void setRejectedNo(String rejectedNo) {
		this.rejectedNo = rejectedNo;
	}
	
	@Length(min=0, max=50, message="rejected_date长度必须介于 0 和 50 之间")
	public String getRejectedDate() {
		return rejectedDate;
	}

	public void setRejectedDate(String rejectedDate) {
		this.rejectedDate = rejectedDate;
	}
	
	@Length(min=0, max=64, message="storeroom_id长度必须介于 0 和 64 之间")
	public String getStoreroomId() {
		return storeroomId;
	}

	public void setStoreroomId(String storeroomId) {
		this.storeroomId = storeroomId;
	}
	
	@Length(min=0, max=100, message="storeroom_name长度必须介于 0 和 100 之间")
	public String getStoreroomName() {
		return storeroomName;
	}

	public void setStoreroomName(String storeroomName) {
		this.storeroomName = storeroomName;
	}
	
	@Length(min=0, max=64, message="supplier_id长度必须介于 0 和 64 之间")
	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	
	@Length(min=0, max=100, message="supplier_name长度必须介于 0 和 100 之间")
	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	@Length(min=0, max=100, message="oper_person长度必须介于 0 和 100 之间")
	public String getOperPerson() {
		return operPerson;
	}

	public void setOperPerson(String operPerson) {
		this.operPerson = operPerson;
	}
	
	@Length(min=0, max=11, message="is_end长度必须介于 0 和 11 之间")
	public String getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(String isEnd) {
		this.isEnd = isEnd;
	}
	
	public List<PdRejectedDetail> getProductList() {
		return productList;
	}

	public void setProductList(List<PdRejectedDetail> productList) {
		this.productList = productList;
	}

}