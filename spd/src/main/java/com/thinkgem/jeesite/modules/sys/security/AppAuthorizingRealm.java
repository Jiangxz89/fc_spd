package com.thinkgem.jeesite.modules.sys.security;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.servlet.ValidateCodeServlet;
import com.thinkgem.jeesite.common.utils.Encodes;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.sys.web.LoginController;

@Service
public class AppAuthorizingRealm extends AuthorizingRealm {

	private Logger logger = LoggerFactory.getLogger( getClass( ) );

	private SystemService systemService;

	public AppAuthorizingRealm( ) {
		this.setCachingEnabled( false );
	}

	/**
	 * 认证回调函数, 登录时调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo( AuthenticationToken authcToken ) {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

		int activeSessionSize = getSystemService( ).getSessionDao( ).getActiveSessions( false ).size( );
		if( logger.isDebugEnabled( ) ) {
			logger.debug( "login submit, active session size: {}, username: {}", activeSessionSize, token.getUsername( ) );
		}

		// 校验登录验证码
		if( LoginController.isValidateCodeLogin( token.getUsername( ), false, false ) ) {
			Session session = UserUtils.getSession( );
			String code = (String) session.getAttribute( ValidateCodeServlet.VALIDATE_CODE );
			if( token.getCaptcha( ) == null || !token.getCaptcha( ).toUpperCase( ).equals( code ) ) {
				throw new AuthenticationException( "msg:验证码错误, 请重试." );
			}
		}

		// 校验用户名密码
		User user = getSystemService().getUserByLoginName(token.getUsername());
		if( user != null ) {
			if( Global.NO.equals( user.getLoginFlag( ) ) ) {
				throw new AuthenticationException( "msg:该已帐号禁止登录." );
			}
			byte[] salt = Encodes.decodeHex( user.getPassword( ).substring( 0, 16 ) );
			return new SimpleAuthenticationInfo( new Principal( user, token.isMobileLogin( ) ), user.getPassword( ).substring( 16 ),
					ByteSource.Util.bytes( salt ), getName( ) );
		} else {
			return null;
		}
		
	}

	/**
	 * 设定密码校验的Hash算法与迭代次数
	 */
	@PostConstruct
	public void initCredentialsMatcher( ) {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher( SystemService.HASH_ALGORITHM );
		matcher.setHashIterations( SystemService.HASH_INTERATIONS );
		setCredentialsMatcher( matcher );
	}


	/**
	 * 获取系统业务对象
	 */
	public SystemService getSystemService( ) {
		if( systemService == null ) {
			systemService = SpringContextHolder.getBean( SystemService.class );
		}
		return systemService;
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

		public Principal( User user, boolean mobileLogin ) {
			this.id = user.getId( );
			this.loginName = user.getLoginName( );
			this.name = user.getName( );
			this.mobileLogin = mobileLogin;
		}

		public String getId( ) {
			return id;
		}

		public String getLoginName( ) {
			return loginName;
		}

		public String getName( ) {
			return name;
		}

		public boolean isMobileLogin( ) {
			return mobileLogin;
		}

		/**
		 * 获取SESSIONID
		 */
		public String getSessionid( ) {
			try {
				return (String) UserUtils.getSession( ).getId( );
			} catch( Exception e ) {
				return "";
			}
		}

		@Override
		public String toString( ) {
			return id;
		}
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo( PrincipalCollection principals ) {
		// TODO Auto-generated method stub
		return null;
	}
}
