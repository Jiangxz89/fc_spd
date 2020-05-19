/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.utils;

import com.thinkgem.hys.pd.dao.PdStoreroomDao;
import com.thinkgem.hys.pd.entity.PdStoreroom;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.dao.*;
import com.thinkgem.jeesite.modules.sys.entity.*;
import com.thinkgem.jeesite.modules.sys.security.SystemAuthorizingRealm.Principal;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户工具类
 * @author ThinkGem
 * @version 2013-12-05
 */
public class UserUtils {

	private static final  Logger log=LoggerFactory.getLogger( UserUtils.class);
	
	private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);
	private static RoleDao roleDao = SpringContextHolder.getBean(RoleDao.class);
	private static MenuDao menuDao = SpringContextHolder.getBean(MenuDao.class);
	private static AreaDao areaDao = SpringContextHolder.getBean(AreaDao.class);
	private static OfficeDao officeDao = SpringContextHolder.getBean(OfficeDao.class);
	private static PdStoreroomDao storeroomDao = SpringContextHolder.getBean(PdStoreroomDao.class);
	
	public static final String USER_CACHE = "userCache";
	public static final String USER_CACHE_ID_ = "id_";
	public static final String USER_CACHE_LOGIN_NAME_ = "ln";
	public static final String USER_CACHE_LIST_BY_OFFICE_ID_ = "oid_";
	
	public static final String CACHE_AUTH_INFO = "authInfo";
	public static final String CACHE_ROLE_LIST = "roleList";
	public static final String CACHE_MENU_LIST = "menuList";
	public static final String CACHE_AREA_LIST = "areaList";
	public static final String CACHE_OFFICE_LIST = "officeList";
	public static final String CACHE_OFFICE_ALL_LIST = "officeAllList";
	
	/**
	 * 根据ID获取用户
	 * @param id
	 * @return 取不到返回null
	 */
	public static User get(String id){
		User user = (User)CacheUtils.get(USER_CACHE, USER_CACHE_ID_ + id);
		if (user ==  null){
			user = userDao.get(id);
			if (user == null){
				return null;
			}
			
			setLoginUser(user);			
			user.setRoleList(roleDao.findList(new Role(user)));
			//CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
			//String loginName=StringUtils.isNoneBlank( user.getLoginName() )?user.getLoginName():user.getMobile( );
			//CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + loginName, user);
		}
		return user;
	}
				
	/**
	 * 通过登录名获取用户信息
	 * @param loginName
	 * @return
	 */
	public static User getByLoginName(String loginName){
		User user = (User)CacheUtils.get(USER_CACHE, USER_CACHE_LOGIN_NAME_ + loginName);
		if (user == null){
			List<User> users = userDao.getByLoginNames(new User(null, loginName));
			if(users != null && users.size() > 0){
				user = users.get(0);
			}
			if (user == null || StringUtils.isBlank(user.getId())){
				return null;
			}
			
			setLoginUser(user);
			//CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
			//CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
		}

		return user;
	}
	
	/**
	 * 登录查询
	 * @param   user
	 * @return  List<User>
	 */
	public static List<User> findByLoginName(User user){
		if(user == null || StringUtils.isBlank(user.getLoginName())){
			return null;
		}
		List<User> users = userDao.getByLoginNames(user);
		if(users == null || users.size() == 0){
			return null;
		}
		return users;
	}
	
	/**
	 * 根据用户信息设置登录用户
	 * @param   user  用户信息
	 * @return 取不到返回null
	 */
	public static User setLoginUser(User user){
		if (user == null || StringUtils.isBlank(user.getId())){
			return null;
		}
		user.setCompanyName(Global.getConfig("HOSPITAL_NAME"));//设置用户医院名称
		user.setCompanyNumber(Global.getConfig("HOSPITAL_NUMBER"));//设置用户医院代码
		String userId = user.getId();
		List<PdStoreroom> storerooms = storeroomDao.findByUserid(userId);
		if(storerooms != null && storerooms.size() > 0){
			if(storerooms.size()>1){
				PdStoreroom pdStoreroom=new PdStoreroom();
				pdStoreroom.setAdminId(userId);
				pdStoreroom.setStoreroomClass("1");//如果有多个库房，只查询普通库房的数据
				 storerooms = storeroomDao.findByStoreroom(pdStoreroom);
			}
			PdStoreroom storeroom = storerooms.get(0);
			user.setStoreroomId(storeroom.getId());
			user.setStoreroomName(storeroom.getStoreroomName());  // 库房名称
			user.setStoreroomCode(storeroom.getStoreroomCode());
			user.setStoreroomType(storeroom.getStoreroomType());

			List<String> ids=new ArrayList<>();
			List<String> names=new ArrayList<>();
			List<String> types=new ArrayList<>();
			List<String> codes=new ArrayList<>();
			for(PdStoreroom room:storerooms){
                      ids.add(room.getId());
                      names.add(room.getStoreroomName());
                      types.add(room.getStoreroomType());
                      codes.add(room.getStoreroomCode());
			}
			user.setStoreroomIds(ids);
			user.setStoreroomNames(names);
			user.setStoreroomTypes(types);
			user.setStoreroomCodes(codes);
		}
		Role role=new Role();
		role.setEnname("twoStoreroomChief");
		user.setRole(role);
		List<Role> roleList=roleDao.findList(new Role(user));
		if(roleList!=null && roleList.size()>0){
			user.setRole(roleList.get(0));
		}else{
			user.setRole(null);
		}
		//user.setRoleList(roleDao.findList(new Role(user)));
		CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
		CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
		
		return user;
	}
	
	/**
	 * 清除当前用户缓存
	 */
	public static void clearCache(){
		removeCache(CACHE_AUTH_INFO);
		removeCache(CACHE_ROLE_LIST);
		removeCache(CACHE_MENU_LIST);
		removeCache(CACHE_AREA_LIST);
		removeCache(CACHE_OFFICE_LIST);
		removeCache(CACHE_OFFICE_ALL_LIST);
		UserUtils.clearCache(getUser());
	}
	
	/**
	 * 清除指定用户缓存
	 * @param user
	 */
	public static void clearCache(User user){
		String loginName=user.getLoginName();
		String mobile=user.getMobile( );
		CacheUtils.remove(USER_CACHE, USER_CACHE_ID_ + user.getId());
		CacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + loginName);
		//CacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + mobile);
		//CacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getOldLoginName()+"_"+user.getLoginType( ));
		if (user.getOffice() != null && user.getOffice().getId() != null){
			CacheUtils.remove(USER_CACHE, USER_CACHE_LIST_BY_OFFICE_ID_ + user.getOffice().getId());
		}
	}
	
	/**
	 * 获取当前用户
	 * @return 取不到返回 new User()
	 */
	public static User getUser(){
		Principal principal = getPrincipal();
		if (principal!=null){
			User user = get(principal.getId());
			if (user != null){
				return user;
			}
			return new User();
		}
		// 如果没有登录，则返回实例化空的User对象。
		return new User();
	}

	/**
	 * 获取当前用户角色列表
	 * @return
	 */
	public static List<Role> getRoleList(){
		@SuppressWarnings("unchecked")
		List<Role> roleList = (List<Role>)getCache(CACHE_ROLE_LIST);
		if (roleList == null){
			User user = getUser();
			if (user.isAdmin()){
				roleList = roleDao.findAllList(new Role());
			}else{
				Role role = new Role();
				role.getSqlMap().put("dsf", BaseService.dataScopeFilter(user.getCurrentUser(), "o", "u"));
				roleList = roleDao.findList(role);
			}
			putCache(CACHE_ROLE_LIST, roleList);
		}
		return roleList;
	}
	
	/**
	 * 获取当前用户授权菜单
	 * @return
	 */
	public static List<Menu> getMenuList(){
		@SuppressWarnings("unchecked")
		List<Menu> menuList = (List<Menu>)getCache(CACHE_MENU_LIST);
		if (menuList == null){
			User user = getUser();
			if (user.isAdmin()){
				menuList = menuDao.findAllList(new Menu());
			}else{
				Menu m = new Menu();
				m.setUserId(user.getId());
				menuList = menuDao.findByUserId(m);
			}
			putCache(CACHE_MENU_LIST, menuList);
		}
		
		return menuList;
	}
	
	/**
	 * 获取当前用户授权的区域
	 * @return
	 */
	public static List<Area> getAreaList(){
		@SuppressWarnings("unchecked")
		List<Area> areaList = (List<Area>)getCache(CACHE_AREA_LIST);
		if (areaList == null){
			areaList = areaDao.findAllList(new Area());
			putCache(CACHE_AREA_LIST, areaList);
		}
		return areaList;
	}
	
	/**
	 * 获取当前用户有权限访问的部门
	 * @return
	 */
	public static List<Office> getOfficeList(){
		@SuppressWarnings("unchecked")
		List<Office> officeList = (List<Office>)getCache(CACHE_OFFICE_LIST);
		if (officeList == null){
			User user = getUser();
			if (user.isAdmin()){
				officeList = officeDao.findAllList(new Office());
			}else{
				Office office = new Office();
				office.getSqlMap().put("dsf", BaseService.dataScopeFilter(user, "a", ""));
				officeList = officeDao.findList(office);
			}
			putCache(CACHE_OFFICE_LIST, officeList);
		}
		return officeList;
	}

	/**
	 * 获取当前用户有权限访问的部门
	 * @return
	 */
	public static List<Office> getOfficeAllList(){
		@SuppressWarnings("unchecked")
		List<Office> officeList = (List<Office>)getCache(CACHE_OFFICE_ALL_LIST);
		if (officeList == null){
			officeList = officeDao.findAllList(new Office());
		}
		return officeList;
	}
	
	/**
	 * 获取授权主要对象
	 */
	public static Subject getSubject(){
		return SecurityUtils.getSubject();
	}
	
	/**
	 * 获取当前登录者对象
	 */
	public static Principal getPrincipal(){
		try{
			Subject subject = SecurityUtils.getSubject();
			Principal principal = (Principal)subject.getPrincipal();
			if (principal != null){
				return principal;
			}
//			subject.logout();
		}catch (UnavailableSecurityManagerException e) {
			
		}catch (InvalidSessionException e){
			
		}
		return null;
	}
	
	public static Session getSession(){
		try{
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null){
				session = subject.getSession();
			}
			if (session != null){
				log.info( "getSession（） ->"+session.getId( ));
				return session;
			}
//			subject.logout();
		}catch (InvalidSessionException e){
			
		}
		return null;
	}
	
	// ============== User Cache ==============
	
	public static Object getCache(String key) {
		return getCache(key, null);
	}
	
	public static Object getCache(String key, Object defaultValue) {
//		Object obj = getCacheMap().get(key);
		Object obj = getSession().getAttribute(key);
		return obj==null?defaultValue:obj;
	}

	public static void putCache(String key, Object value) {
//		getCacheMap().put(key, value);
		getSession().setAttribute(key, value);
	}

	public static void removeCache(String key) {
//		getCacheMap().remove(key);
		getSession().removeAttribute(key);
	}

	public static List<Role> findUpRoleList() {
		List<Role> roleList  = roleDao.findAllList(new Role());
		/*Iterator<Role> it = roleList.iterator();
		while(it.hasNext()){
			Role role = it.next();
			if(!role.getEnname().equals("twostorerommAdmin")&&!role.getEnname().equals("oneStoreroomAdmin")){
				it.remove();
			}
		}*/
		return roleList;
	}
}
