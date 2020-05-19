/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * HIS数据同步状态表Entity
 * @author wg
 * @version 2018-08-29
 */
public class PdHisSync extends DataEntity<PdHisSync> {
	
	private static final long serialVersionUID = 1L;
	private String dayTime;		// 日期
	private String dataType;		// 数据类型
	
	public PdHisSync() {
		super();
	}

	public PdHisSync(String id){
		super(id);
	}

	@Length(min=0, max=8, message="日期长度必须介于 0 和 8 之间")
	public String getDayTime() {
		return dayTime;
	}

	public void setDayTime(String dayTime) {
		this.dayTime = dayTime;
	}
	
	@Length(min=0, max=2, message="数据类型长度必须介于 0 和 2 之间")
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
}