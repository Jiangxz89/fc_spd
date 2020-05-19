/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.supcan.annotation.treelist.cols.SupCol;
import com.thinkgem.jeesite.common.utils.Collections3;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.common.utils.excel.fieldtype.RoleListType;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户Entity
 * @author ThinkGem
 * @version 2013-12-05
 */
public class User extends DataEntity<User> {

	private static final long serialVersionUID = 1L;
	private String imNo;
	private Office company;	// 归属公司
	private Office office;	// 归属部门
	private String loginName;// 登录名
	private String password;// 密码
	private String no;		// 工号
	private String name;	// 姓名
	private String email;	// 邮箱
	private String phone;	// 电话
	private String mobile;	// 手机
	private String userType;// 用户类型
	private String loginIp;	// 最后登陆IP
	private Date loginDate;	// 最后登陆日期
	private String loginFlag;	// 是否允许登陆
	private String photo;	// 头像
	private String oldLoginName;// 原登录名
	private String newPassword;	// 新密码
	private String oldLoginIp;	// 上次登陆IP
	private Date oldLoginDate;	// 上次登陆日期
	private Role role;	// 根据角色查询用户条件
	private String storeroom_type;

	private List<Role> roleList = Lists.newArrayList(); // 拥有角色列表
	private Area area;
	private String imPwd;
	private Integer  status;
	private String verfiyResult;
	//==================================
	private List<Integer> statusList;
	
	private String  smsCode;
	private String tokenId;
	
	private List<String> ids=new ArrayList<String>( );
	
	private String companyName;		    // 机构名称
	private String companyNumber;		    // 机构代码
	private String storeroomId;		    // 库房id
	private String storeroomName;		// 库房名称
	private String storeroomCode;		// 库房编号
	private String storeroomType;		// 库房类型（一级；二级）

	private List<String> storeroomIds;		    // 库房id集合
	private List<String> storeroomNames;		// 库房名称集合
	private List<String> storeroomCodes;		// 库房编号集合
	private List<String> storeroomTypes;		// 库房类型集合（一级；二级）
	private String supplierId;  //供应商id
	private String supplierName;  //供应商名称

	private String hisUserNo;	// his系统用户编号

	public User() {
		super();
		this.loginFlag = Global.YES;
	}

	public List<String> getStoreroomIds() {
		return storeroomIds;
	}

	public void setStoreroomIds(List<String> storeroomIds) {
		this.storeroomIds = storeroomIds;
	}

	public List<String> getStoreroomNames() {
		return storeroomNames;
	}

	public void setStoreroomNames(List<String> storeroomNames) {
		this.storeroomNames = storeroomNames;
	}

	public List<String> getStoreroomCodes() {
		return storeroomCodes;
	}

	public void setStoreroomCodes(List<String> storeroomCodes) {
		this.storeroomCodes = storeroomCodes;
	}

	public List<String> getStoreroomTypes() {
		return storeroomTypes;
	}

	public void setStoreroomTypes(List<String> storeroomTypes) {
		this.storeroomTypes = storeroomTypes;
	}

	public String getHisUserNo() {
		return hisUserNo;
	}

	public void setHisUserNo(String hisUserNo) {
		this.hisUserNo = hisUserNo;
	}

	public User(String id){
		super(id);
	}

	public User(String id, String loginName){
		super(id);
		this.loginName = loginName;
	}
	
	public User(String mobile, String password,Integer status){
		this.mobile=mobile;
		this.password = password;
		this.status=status;
	}
	
	public User(Role role){
		super();
		this.role = role;
	}
	

	public String getImNo( ) {
		return imNo;
	}

	public void setImNo( String imNo ) {
		this.imNo = imNo;
	}

	public Area getArea( ) {
		return area;
	}

	public void setArea( Area area ) {
		this.area = area;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@JsonIgnore
	public String getLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(String loginFlag) {
		this.loginFlag = loginFlag;
	}

	@SupCol(isUnique="true", isHide="true")
	@ExcelField(title="ID", type=1, align=2, sort=1)
	public String getId() {
		return id;
	}

	@JsonIgnore
	@NotNull(message="归属公司不能为空")
	@ExcelField(title="归属公司", align=2, sort=20)
	public Office getCompany() {
		return company;
	}

	public void setCompany(Office company) {
		this.company = company;
	}
	
	@JsonIgnore
	@NotNull(message="归属部门不能为空")
	@ExcelField(title="归属部门", align=2, sort=25)
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	@Length(min=1, max=100, message="登录名长度必须介于 1 和 100 之间")
	@ExcelField(title="登录名", align=2, sort=30)
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@JsonIgnore
	@Length(min=1, max=100, message="密码长度必须介于 1 和 100 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Length(min=1, max=100, message="姓名长度必须介于 1 和 100 之间")
	@ExcelField(title="姓名", align=2, sort=40)
	public String getName() {
		return name;
	}
	
	@Length(min=1, max=100, message="工号长度必须介于 1 和 100 之间")
	@ExcelField(title="工号", align=2, sort=45)
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Email(message="邮箱格式不正确")
	@Length(min=0, max=200, message="邮箱长度必须介于 1 和 200 之间")
	@ExcelField(title="邮箱", align=1, sort=50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=200, message="电话长度必须介于 1 和 200 之间")
	@ExcelField(title="电话", align=2, sort=60)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Length(min=0, max=200, message="手机长度必须介于 1 和 200 之间")
	@ExcelField(title="手机", align=2, sort=70)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@ExcelField(title="备注", align=1, sort=900)
	public String getRemarks() {
		return remarks;
	}
	
	@Length(min=0, max=100, message="用户类型长度必须介于 1 和 100 之间")
	@ExcelField(title="用户类型", align=2, sort=80, dictType="sys_user_type")
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@ExcelField(title="创建时间", type=0, align=1, sort=90)
	public Date getCreateDate() {
		return createDate;
	}

	@ExcelField(title="最后登录IP", type=1, align=1, sort=100)
	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="最后登录日期", type=1, align=1, sort=110)
	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getOldLoginName() {
		return oldLoginName;
	}

	public void setOldLoginName(String oldLoginName) {
		this.oldLoginName = oldLoginName;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getOldLoginIp() {
		if (oldLoginIp == null){
			return loginIp;
		}
		return oldLoginIp;
	}

	public void setOldLoginIp(String oldLoginIp) {
		this.oldLoginIp = oldLoginIp;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOldLoginDate() {
		if (oldLoginDate == null){
			return loginDate;
		}
		return oldLoginDate;
	}

	public void setOldLoginDate(Date oldLoginDate) {
		this.oldLoginDate = oldLoginDate;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@JsonIgnore
	@ExcelField(title="拥有角色", align=1, sort=800, fieldType=RoleListType.class)
	public List<Role> getRoleList() {
		return roleList;
	}
	
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	@JsonIgnore
	public List<String> getRoleIdList() {
		List<String> roleIdList = Lists.newArrayList();
		for (Role role : roleList) {
			roleIdList.add(role.getId());
		}
		return roleIdList;
	}

	public void setRoleIdList(List<String> roleIdList) {
		roleList = Lists.newArrayList();
		for (String roleId : roleIdList) {
			Role role = new Role();
			role.setId(roleId);
			roleList.add(role);
		}
	}
	
	/**
	 * 用户拥有的角色名称字符串, 多个角色名称用','分隔.
	 */
	@JsonIgnore
	public String getRoleNames() {
		return Collections3.extractToString(roleList, "name", ",");
	}
	
	public boolean isAdmin(){
		return isAdmin(this.id);
	}
	
	@JsonIgnore
	public static boolean isAdmin(String id){
		return id != null && "1".equals(id);
	}
	
	@Override
	public String toString() {
		return id;
	}

	public String getImPwd( ) {
		return imPwd;
	}

	public void setImPwd( String imPwd ) {
		this.imPwd = imPwd;
	}

	public Integer getStatus( ) {
		return status;
	}

	public void setStatus( Integer status ) {
		this.status = status;
	}

	public String getSmsCode( ) {
		return smsCode;
	}

	public void setSmsCode( String smsCode ) {
		this.smsCode = smsCode;
	}
	
	@JsonIgnore
	public List<Integer> getStatusList( ) {
		return statusList;
	}

	public void setStatusList( List<Integer> statusList ) {
		this.statusList = statusList;
	}

	public String getTokenId( ) {
		return tokenId;
	}

	public void setTokenId( String tokenId ) {
		this.tokenId = tokenId;
	}

	public String getVerfiyResult( ) {
		return verfiyResult;
	}

	public void setVerfiyResult( String verfiyResult ) {
		this.verfiyResult = verfiyResult;
	}

	public List<String> getIds( ) {
		return ids;
	}

	public void setIds( List<String> ids ) {
		this.ids = ids;
	}
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getStoreroomId() {
		return storeroomId;
	}

	public void setStoreroomId(String storeroomId) {
		this.storeroomId = storeroomId;
	}
	
	public String getStoreroomName() {
		return storeroomName;
	}

	public void setStoreroomName(String storeroomName) {
		this.storeroomName = storeroomName;
	}
	public String getStoreroomCode() {
		return storeroomCode;
	}
	public void setStoreroomCode(String storeroomCode) {
		this.storeroomCode = storeroomCode;
	}
	public String getStoreroomType() {
		return storeroomType;
	}
	public void setStoreroomType(String storeroomType) {
		this.storeroomType = storeroomType;
	}
	
	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	
	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getCompanyNumber() {
		return companyNumber;
	}

	public void setCompanyNumber(String companyNumber) {
		this.companyNumber = companyNumber;
	}

	public String getStoreroom_type() {
		return storeroom_type;
	}

	public void setStoreroom_type(String storeroom_type) {
		this.storeroom_type = storeroom_type;
	}
}