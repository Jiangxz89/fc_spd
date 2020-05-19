/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 定数包表Entity
 * @author sutianqi
 * @version 2018-03-07
 */
public class PdPackage extends DataEntity<PdPackage> {
	private static final long serialVersionUID = 1L;
	private String id;		// _id
	private String number;		// 定数包编号
	private String name;		// 定数包名称
	private String description;		// 定数包说明
	private int sum;		//总数
	
	private List<PdProductMPackage> child;
	private List<PdProduct> pdProductList;
	private String jsonStr;//产品和包的实体json串
	//查询用
	private String storeroomId;//仓库id
	private String applyNum;//申领数量
	
	private Set<String> packageIds;
	
	
	public List<PdProductMPackage> getChild() {
		return child;
	}

	public void setChild(List<PdProductMPackage> child) {
		this.child = child;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public PdPackage() {
		super();
	}

	public PdPackage(String id){
		super(id);
	}

	@Length(min=1, max=64, message="_id长度必须介于 1 和 64 之间")
	public String getId() {
		return id;
	}

	public void setId(String Id) {
		this.id = Id;
	}
	
	@Length(min=0, max=100, message="定数包编号长度必须介于 0 和 100 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Length(min=0, max=100, message="定数包名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=1000, message="定数包说明长度必须介于 0 和 1000 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJsonStr() {
		return jsonStr;
	}

	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}

	public List<PdProduct> getPdProductList() {
		return pdProductList;
	}

	public void setPdProductList(List<PdProduct> pdProductList) {
		this.pdProductList = pdProductList;
	}

	public String getStoreroomId() {
		return storeroomId;
	}

	public void setStoreroomId(String storeroomId) {
		this.storeroomId = storeroomId;
	}

	public String getApplyNum() {
		return applyNum;
	}

	public void setApplyNum(String applyNum) {
		this.applyNum = applyNum;
	}

	public Set<String> getPackageIds() {
		return packageIds;
	}

	public void setPackageIds(Set<String> packageIds) {
		this.packageIds = packageIds;
	}
	
}