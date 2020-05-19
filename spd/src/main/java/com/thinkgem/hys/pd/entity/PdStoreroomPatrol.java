/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.DateUtils;

/**
 * 库房巡查Entity
 * @author wg
 * @version 2018-03-30
 */
public class PdStoreroomPatrol extends DataEntity<PdStoreroomPatrol> {
	
	private static final long serialVersionUID = 1L;
	private String patrolCode;		// 巡查单号
	private String storeroomId;		// 库房ID
	private String patrolCount;		// 巡查产品量
	private String passCount;		// 合格产品量
	private String failCount;		// 不合格产品量
	private String temperature;		// 温度
	private String wetness;		// 湿度
	private String patrolMan;		// 巡查员
	private Date patrolDate;		// 巡查日期
	
	//冗余
	private String patrolManName;
	
	//-temp
	private String showFlag;//显示tab页
	
	public PdStoreroomPatrol() {
		super();
	}

	public PdStoreroomPatrol(String id){
		super(id);
	}

	@Length(min=1, max=64, message="巡查单号长度必须介于 1 和 64 之间")
	public String getPatrolCode() {
		return patrolCode;
	}

	public void setPatrolCode(String patrolCode) {
		this.patrolCode = patrolCode;
	}
	
	@Length(min=0, max=64, message="库房ID长度必须介于 0 和 64 之间")
	public String getStoreroomId() {
		return storeroomId;
	}

	public void setStoreroomId(String storeroomId) {
		this.storeroomId = storeroomId;
	}
	
	@Length(min=0, max=11, message="巡查产品量长度必须介于 0 和 11 之间")
	public String getPatrolCount() {
		return patrolCount;
	}

	public void setPatrolCount(String patrolCount) {
		this.patrolCount = patrolCount;
	}
	
	@Length(min=0, max=11, message="合格产品量长度必须介于 0 和 11 之间")
	public String getPassCount() {
		return passCount;
	}

	public void setPassCount(String passCount) {
		this.passCount = passCount;
	}
	
	@Length(min=0, max=11, message="不合格产品量长度必须介于 0 和 11 之间")
	public String getFailCount() {
		return failCount;
	}

	public void setFailCount(String failCount) {
		this.failCount = failCount;
	}
	
	@Length(min=0, max=10, message="温度长度必须介于 0 和 10 之间")
	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	
	@Length(min=0, max=10, message="湿度长度必须介于 0 和 10 之间")
	public String getWetness() {
		return wetness;
	}

	public void setWetness(String wetness) {
		this.wetness = wetness;
	}
	
	@Length(min=0, max=64, message="巡查员长度必须介于 0 和 64 之间")
	public String getPatrolMan() {
		return patrolMan;
	}

	public void setPatrolMan(String patrolMan) {
		this.patrolMan = patrolMan;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPatrolDate() {
		return patrolDate;
	}

	public void setPatrolDate(Date patrolDate) {
		this.patrolDate = patrolDate;
	}

	public String getShowFlag() {
		return showFlag;
	}

	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}
	
	public static PdStoreroomPatrol dealDate(PdStoreroomPatrol pp) throws Exception{
		//今天
		if("1".equals(pp.getShowFlag())){
			String today = DateUtils.getDate();
			pp.setStartDate(DateUtils.parseDate(today, "yyyy-MM-dd"));
			pp.setEndDate(DateUtils.parseDate(today + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
		}
		//昨天
		if("2".equals(pp.getShowFlag())){
			String yestoday = DateUtils.formatDate(DateUtils.subDay(DateUtils.getNowDate(), 1), "yyyy-MM-dd");
			pp.setStartDate(DateUtils.parseDate(yestoday, "yyyy-MM-dd"));
			pp.setEndDate(DateUtils.parseDate(yestoday + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
		}
		//本周
		if("3".equals(pp.getShowFlag())){
			String currentMonday = DateUtils.formatDate(DateUtils.getCurrentWeekDate(DateUtils.getNowDate()), "yyyy-MM-dd");
			pp.setStartDate(DateUtils.parseDate(currentMonday, "yyyy-MM-dd"));
			pp.setEndDate(DateUtils.parseDate(DateUtils.getDate() + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
		}
		return pp;
	}

	public String getPatrolManName() {
		return patrolManName;
	}

	public void setPatrolManName(String patrolManName) {
		this.patrolManName = patrolManName;
	}
	
	
}