/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao;

import java.util.List;

import com.thinkgem.hys.pd.entity.PdStoreroomAdmin;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 用户DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface UserDao extends CrudDao<User> {
	
	/**
	 * 根据登录名称查询用户 登录类型
	 * @param loginName
	 * @return
	 */
	public User getByLoginName(User user);
	
	
	/**
	 * 根据登录名查询
	 * 前后台登录名可以相同
	 * @param user
	 * @return
	 */
	public List<User> getByLoginNames(User user);

	
	/**
	 * 通过OfficeId获取用户列表，仅返回用户id和name（树查询用户时用）
	 * @param user
	 * @return
	 */
	public List<User> findUserByOfficeId(User user);
	
	/**
	 * 查询全部用户数目
	 * @return
	 */
	public long findAllCount(User user);
	
	/**
	 * 更新用户密码
	 * @param user
	 * @return
	 */
	public int updatePasswordById(User user);
	
	/**
	 * 更新登录信息，如：登录IP、登录时间
	 * @param user
	 * @return
	 */
	public int updateLoginInfo(User user);

	/**
	 * 删除用户角色关联数据
	 * @param user
	 * @return
	 */
	public int deleteUserRole(User user);
	
	/**
	 * 插入用户角色关联数据
	 * @param user
	 * @return
	 */
	public int insertUserRole(User user);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public int updateUserInfo(User user);
	
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	public User getByLoginNamePwd(User user);
	
	/**
	 * 删除指定用户的下级账号
	 * @param id
	 */
	public void deleteUsersByAccountId(String id);
	
	/**
	 * 根据手机号查询用户
	 * @param user
	 * @return
	 */
	List<User> getUserByModel(User user);

	/**
	 * 更新设备号
	 * @param user
	 * @return
	 */
	public int updateFixCard(User user);
	
	/**
	 * 根据用户Id 获取手机号集合
	 * @param user
	 * @return
	 */
	public List<User> getMobileByUserId(User user);
	
	/**
	 * 根据状态获取手机号
	 * @param user
	 * @return
	 */
	public List<User> getMobileByStatus(User user);

    List<User> newFindList(User user);

	List<User> check(User user);

    void batchUpdateState(String adminList);

    List<User> getNewUser(User user);
}
