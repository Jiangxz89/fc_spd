/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * 库房信息Entity
 * @author changjundong
 * @version 2018-03-07
 */
public class PdStoreroom extends DataEntity<PdStoreroom> {
	
	private static final long serialVersionUID = 1L;
	private String storeroomCode;		// 库房编号
	private String storeroomName;		// 库房名称
	private String linkman;		// 联系人
	private String linkmanPhone;		// 联系人手机
	private String linkmanMail;		// 联系人邮箱
	private String linkmanAddss;		// 联系地址
	private String storeroomClass;		// 库房分类（普通；智能柜）
	private String storeroomType;		// 库房类型（一级；二级）
	private Integer dateRemind;		// 效期提醒
	private Integer longtimeRemind;		// 久存提醒
	private String extend1;		// extend1
	private String extend2;		// extend2
	private String extend3;		// extend3
	private List<PdStoreroomAdmin> adminList;	//冗余管理人员列表
	private List<PdStoreroomProduct> productList;	//冗余产品列表
	private String adminId;		// 库房管理员
	public PdStoreroom() {
		super();
	}

	public PdStoreroom(String id){
		super(id);
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	@Length(min=0, max=64, message="库房编号长度必须介于 0 和 64 之间")
	public String getStoreroomCode() {
		return storeroomCode;
	}

	public void setStoreroomCode(String storeroomCode) {
		this.storeroomCode = storeroomCode;
	}
	
	@Length(min=0, max=64, message="库房名称长度必须介于 0 和 64 之间")
	public String getStoreroomName() {
		return storeroomName;
	}

	public void setStoreroomName(String storeroomName) {
		this.storeroomName = storeroomName;
	}
	
	@Length(min=0, max=64, message="联系人长度必须介于 0 和 64 之间")
	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	
	@Length(min=0, max=32, message="联系人手机长度必须介于 0 和 32 之间")
	public String getLinkmanPhone() {
		return linkmanPhone;
	}

	public void setLinkmanPhone(String linkmanPhone) {
		this.linkmanPhone = linkmanPhone;
	}
	
	@Length(min=0, max=100, message="联系人邮箱长度必须介于 0 和 100 之间")
	public String getLinkmanMail() {
		return linkmanMail;
	}

	public void setLinkmanMail(String linkmanMail) {
		this.linkmanMail = linkmanMail;
	}
	
	@Length(min=0, max=255, message="联系地址长度必须介于 0 和 255 之间")
	public String getLinkmanAddss() {
		return linkmanAddss;
	}

	public void setLinkmanAddss(String linkmanAddss) {
		this.linkmanAddss = linkmanAddss;
	}
	
	@Length(min=0, max=1, message="库房分类（普通；智能柜）长度必须介于 0 和 1 之间")
	public String getStoreroomClass() {
		return storeroomClass;
	}

	public void setStoreroomClass(String storeroomClass) {
		this.storeroomClass = storeroomClass;
	}
	
	@Length(min=0, max=1, message="库房类型（一级；二级）长度必须介于 0 和 1 之间")
	public String getStoreroomType() {
		return storeroomType;
	}

	public void setStoreroomType(String storeroomType) {
		this.storeroomType = storeroomType;
	}
	
	public Integer getDateRemind() {
		return dateRemind;
	}

	public void setDateRemind(Integer dateRemind) {
		this.dateRemind = dateRemind;
	}

	public Integer getLongtimeRemind() {
		return longtimeRemind;
	}

	public void setLongtimeRemind(Integer longtimeRemind) {
		this.longtimeRemind = longtimeRemind;
	}

	@Length(min=0, max=200, message="extend1长度必须介于 0 和 200 之间")
	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}
	
	@Length(min=0, max=100, message="extend2长度必须介于 0 和 100 之间")
	public String getExtend2() {
		return extend2;
	}

	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}
	
	@Length(min=0, max=100, message="extend3长度必须介于 0 和 100 之间")
	public String getExtend3() {
		return extend3;
	}

	public void setExtend3(String extend3) {
		this.extend3 = extend3;
	}

	public List<PdStoreroomAdmin> getAdminList() {
		return adminList;
	}

	public void setAdminList(List<PdStoreroomAdmin> adminList) {
		this.adminList = adminList;
	}

	public List<PdStoreroomProduct> getProductList() {
		return productList;
	}

	public void setProductList(List<PdStoreroomProduct> productList) {
		this.productList = productList;
	}

	
}