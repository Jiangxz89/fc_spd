package com.thinkgem.jeesite.modules.sys.security;

import java.io.Serializable;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.servlet.ValidateCodeServlet;
import com.thinkgem.jeesite.common.utils.Encodes;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.sys.SysConstant;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.sys.web.LoginController;

@Service
public class UserAuthorizingRealm  extends AuthorizingRealm {
	/**
	 * logger日志
	 */
	protected Logger logger = LoggerFactory.getLogger( getClass( ) );
	
	public UserAuthorizingRealm() {
		this.setCachingEnabled(false);
	}
	
	/**
	 * userMemberServicef服务
	 */
	@Resource
	private SystemService systemService;

	private void setSession( Object key, Object value ) {
		Subject subject = SecurityUtils.getSubject( );
		if( null != subject ) {
			Session session = subject.getSession( );
			logger.debug( "Session默认超时时间为[" + session.getTimeout( ) + "]毫秒" );
			if( null != session ) {
				session.setAttribute( key, value );
			}
		}
	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo( PrincipalCollection principals ) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo( );
		info.addStringPermission( "sys:manager" );
		info.addStringPermission( "user" );
		System.out.println( "开始授权" );
		return info;
	}
	
	/**
	 * 获取系统业务对象
	 */
	public SystemService getSystemService() {
		if (systemService == null){
			systemService = SpringContextHolder.getBean(SystemService.class);
		}
		return systemService;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo( AuthenticationToken authenticationToken ) throws AuthenticationException {
   
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		
		int activeSessionSize = getSystemService().getSessionDao().getActiveSessions(false).size();
		if (logger.isDebugEnabled()){
			logger.debug("login submit, active session size: {}, username: {}", activeSessionSize, token.getUsername());
		}
		
		// 校验登录验证码
		if (LoginController.isValidateCodeLogin(token.getUsername(), false, false)){
			Session session = UserUtils.getSession();
			String code = (String)session.getAttribute(ValidateCodeServlet.VALIDATE_CODE);
			if (token.getCaptcha() == null || !token.getCaptcha().toUpperCase().equals(code)){
				throw new AuthenticationException("msg:验证码错误, 请重试.");
			}
		}
		
		// 校验用户名密码
		User user = getSystemService().getUserByLoginName(token.getUsername());
		if (user != null) {
			if (Global.NO.equals(user.getLoginFlag())){
				throw new AuthenticationException("msg:该已帐号禁止登录.");
			}
			byte[] salt = Encodes.decodeHex(user.getPassword().substring(0,16));
			this.setSession( "shiroLoginUser", user );
			return new SimpleAuthenticationInfo(new Principal(user, token.isMobileLogin()), 
					user.getPassword().substring(16), ByteSource.Util.bytes(salt), getName());
		} else {
			return null;
		}
	}
	
	@Override
	protected void clearCachedAuthorizationInfo( PrincipalCollection principals ) {
		super.clearCachedAuthorizationInfo( principals );
	}

	@Override
	protected void clearCachedAuthenticationInfo( PrincipalCollection principals ) {
		super.clearCachedAuthenticationInfo( principals );
	}

	@Override
	protected void clearCache( PrincipalCollection principals ) {
		super.clearCache( principals );
	}

	public void clearAllCachedAuthorizationInfo( ) {
		getAuthorizationCache( ).clear( );
	}

	public void clearAllCachedAuthenticationInfo( ) {
		getAuthenticationCache( ).clear( );
	}

	public void clearAllCache( ) {
		clearAllCachedAuthorizationInfo( );
		clearAllCachedAuthenticationInfo( );
	}

	/**
	 * 授权用户信息
	 */
	public static class Principal implements Serializable {

		private static final long serialVersionUID = 1L;
		
		private String id; // 编号
		private String loginName; // 登录名
		private String name; // 姓名
		private boolean mobileLogin; // 是否手机登录
		private String logintype;
		
		public Principal(User user, boolean mobileLogin) {
			this.id = user.getId();
			this.loginName = user.getLoginName();
			this.name = user.getName();
			this.mobileLogin = mobileLogin;
		}

		public String getId() {
			return id;
		}

		public String getLoginName() {
			return loginName;
		}

		public String getName() {
			return name;
		}

		public boolean isMobileLogin() {
			return mobileLogin;
		}

		/**
		 * 获取SESSIONID
		 */
		public String getSessionid() {
			try{
				return (String) UserUtils.getSession().getId();
			}catch (Exception e) {
				return "";
			}
		}
		
		@Override
		public String toString() {
			return id;
		}
	}
}
