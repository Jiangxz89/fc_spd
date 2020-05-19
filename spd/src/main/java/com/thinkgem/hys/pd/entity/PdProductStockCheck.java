/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 盘点表Entity
 * @author sutianqi
 * @version 2018-03-07
 */
public class PdProductStockCheck extends DataEntity<PdProductStockCheck> {
	
	private static final long serialVersionUID = 1L;
	private String id;		// _id
	private String number;		// number
	private String repoId;		// repo_id
	private String repoName;	// 库房名称
	private String checkDate;		// check_date
	private String operPerson;		// oper_person
	private Integer shouldCount;		// should_count
	private Integer alreadyCount;		// already_count
	private Integer profitLossCount;		// profit_loss_count
	private String status;		// 词典：临时保存  0盘点完成  1
	
	private List<PdProductStockCheckChild> child;
	
	
	
	
	public String getRepoName() {
		return repoName;
	}

	public void setRepoName(String repoName) {
		this.repoName = repoName;
	}

	public List<PdProductStockCheckChild> getChild() {
		return child;
	}

	public void setChild(List<PdProductStockCheckChild> child) {
		this.child = child;
	}

	public PdProductStockCheck() {
		super();
	}

	public PdProductStockCheck(String id){
		super(id);
	}

	@Length(min=1, max=64, message="_id长度必须介于 1 和 64 之间")
	public String getId() {
		return id;
	}

	public void setId(String Id) {
		this.id = Id;
	}
	
	@Length(min=0, max=100, message="number长度必须介于 0 和 100 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Length(min=0, max=64, message="repo_id长度必须介于 0 和 64 之间")
	public String getRepoId() {
		return repoId;
	}

	public void setRepoId(String repoId) {
		this.repoId = repoId;
	}
	
	@Length(min=0, max=50, message="check_date长度必须介于 0 和 50 之间")
	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	
	@Length(min=0, max=50, message="oper_person长度必须介于 0 和 50 之间")
	public String getOperPerson() {
		return operPerson;
	}

	public void setOperPerson(String operPerson) {
		this.operPerson = operPerson;
	}
	
	
	
	public Integer getShouldCount() {
		return shouldCount;
	}

	public void setShouldCount(Integer shouldCount) {
		this.shouldCount = shouldCount;
	}

	public Integer getAlreadyCount() {
		return alreadyCount;
	}

	public void setAlreadyCount(Integer alreadyCount) {
		this.alreadyCount = alreadyCount;
	}

	public Integer getProfitLossCount() {
		return profitLossCount;
	}

	public void setProfitLossCount(Integer profitLossCount) {
		this.profitLossCount = profitLossCount;
	}

	@Length(min=0, max=50, message="词典：临时保存  0盘点完成  1长度必须介于 0 和 50 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}