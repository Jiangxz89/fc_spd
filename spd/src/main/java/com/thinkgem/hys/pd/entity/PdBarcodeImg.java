/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 条形码表Entity
 * @author sutianqi
 * @version 2018-03-28
 */
public class PdBarcodeImg extends DataEntity<PdBarcodeImg> {
	
	private static final long serialVersionUID = 1L;
	private String number;		// 编码内容
	private String site;		// 条形码地址
	
	public PdBarcodeImg() {
		super();
	}

	public PdBarcodeImg(String id){
		super(id);
	}

	@Length(min=0, max=100, message="编码内容长度必须介于 0 和 100 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Length(min=0, max=200, message="条形码地址长度必须介于 0 和 200 之间")
	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}
	
}