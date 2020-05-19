/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 药品临床目录库Entity
 * @author sutianqi
 * @version 2018-07-31
 */
public class MedstoYplcmlk extends DataEntity<MedstoYplcmlk> {
	
	private static final long serialVersionUID = 1L;
	private String idm;		// Idm
	private String ypmc;		// 药品名称
	private String xwmc;		// xwmc
	private String py;		// 拼音
	private String wb;		// 五笔
	private String ypjx;		// 药品剂型
	private String ggdw;		// 规格单位
	private String jlzt;		// 记录状态
	private String memo;		// 备注
	private String flm;		// 分类码
	private String ypdm;		// 药品代码
	private String lcyplx;		// 临床药品类型
	
	public MedstoYplcmlk() {
		super();
	}

	public MedstoYplcmlk(String id){
		super(id);
	}

	public String getIdm() {
		return idm;
	}

	public void setIdm(String idm) {
		this.idm = idm;
	}
	
	@Length(min=0, max=64, message="药品名称长度必须介于 0 和 64 之间")
	public String getYpmc() {
		return ypmc;
	}

	public void setYpmc(String ypmc) {
		this.ypmc = ypmc;
	}
	
	@Length(min=1, max=32, message="xwmc长度必须介于 1 和 32 之间")
	public String getXwmc() {
		return xwmc;
	}

	public void setXwmc(String xwmc) {
		this.xwmc = xwmc;
	}
	
	@Length(min=1, max=8, message="拼音长度必须介于 1 和 8 之间")
	public String getPy() {
		return py;
	}

	public void setPy(String py) {
		this.py = py;
	}
	
	@Length(min=1, max=8, message="五笔长度必须介于 1 和 8 之间")
	public String getWb() {
		return wb;
	}

	public void setWb(String wb) {
		this.wb = wb;
	}
	
	@Length(min=1, max=2, message="药品剂型长度必须介于 1 和 2 之间")
	public String getYpjx() {
		return ypjx;
	}

	public void setYpjx(String ypjx) {
		this.ypjx = ypjx;
	}
	
	@Length(min=1, max=8, message="规格单位长度必须介于 1 和 8 之间")
	public String getGgdw() {
		return ggdw;
	}

	public void setGgdw(String ggdw) {
		this.ggdw = ggdw;
	}
	
	@Length(min=0, max=6, message="记录状态长度必须介于 0 和 6 之间")
	public String getJlzt() {
		return jlzt;
	}

	public void setJlzt(String jlzt) {
		this.jlzt = jlzt;
	}
	
	@Length(min=1, max=24, message="备注长度必须介于 1 和 24 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Length(min=1, max=16, message="分类码长度必须介于 1 和 16 之间")
	public String getFlm() {
		return flm;
	}

	public void setFlm(String flm) {
		this.flm = flm;
	}
	
	@Length(min=1, max=18, message="药品代码长度必须介于 1 和 18 之间")
	public String getYpdm() {
		return ypdm;
	}

	public void setYpdm(String ypdm) {
		this.ypdm = ypdm;
	}
	
	@Length(min=1, max=6, message="临床药品类型长度必须介于 1 和 6 之间")
	public String getLcyplx() {
		return lcyplx;
	}

	public void setLcyplx(String lcyplx) {
		this.lcyplx = lcyplx;
	}
	
}