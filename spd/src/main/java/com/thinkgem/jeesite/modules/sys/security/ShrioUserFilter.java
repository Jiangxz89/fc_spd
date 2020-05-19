/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights
 * reserved.
 */
package com.thinkgem.jeesite.modules.sys.security;

import java.util.Collection;
import java.util.HashSet;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;

import com.alibaba.druid.support.json.JSONUtils;
import com.thinkgem.jeesite.modules.sys.SysConstant;

/**
 * 用户和密码（包含验证码）令牌类
 * 
 * @author ThinkGem
 * @version 2013-5-19
 */
public class ShrioUserFilter extends UserFilter {

	@Override
	protected boolean isAccessAllowed( ServletRequest request, ServletResponse response, Object mappedValue ) {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		//System.out.println( WebUtils.getSavedRequest( request )!=null?WebUtils.getSavedRequest( request ).getRequestUrl( ):"");
		String path = req.getServletPath( );
		Collection<String> hashSet = new HashSet<String>( );
		hashSet.add( SysConstant.FRONT_LOGIN );
		hashSet.add( SysConstant.FRONT_LOGIN + "/" );
		hashSet.add( "/" + SysConstant.FRONT_LOGIN );
		hashSet.add( "/" + SysConstant.FRONT_LOGIN + "/" );
		if( hashSet.contains( path ) ) {
			this.setLoginUrl( SysConstant.portal_view_index );
		} else {
			this.setLoginUrl( "/" + SysConstant.FRONT_LOGIN + "/login" );
		}
		return super.isAccessAllowed( request, response, mappedValue );
	}

	@Override
	protected boolean onAccessDenied( ServletRequest request, ServletResponse response ) throws Exception {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		Subject subject = getSubject( request, response );
		if( subject.getPrincipal( ) == null ) {
			if( !( httpRequest.getHeader( "accept" ).indexOf( "application/json" ) > -1 || ( httpRequest.getHeader( "X-Requested-With" ) != null && httpRequest
					.getHeader( "X-Requested-With" ).indexOf( "XMLHttpRequest" ) > -1 ) ) ) {
				saveRequestAndRedirectToLogin( httpRequest, httpResponse );
			} else {
				httpResponse.getWriter( ).print( JSONUtils.toJSONString( "error" ) );
			}
		} else {
			if( !( httpRequest.getHeader( "accept" ).indexOf( "application/json" ) > -1 || ( httpRequest.getHeader( "X-Requested-With" ) != null && httpRequest
					.getHeader( "X-Requested-With" ).indexOf( "XMLHttpRequest" ) > -1 ) ) ) {
				String unauthorizedUrl = "";
				if( org.springframework.util.StringUtils.hasText( unauthorizedUrl ) ) {
					WebUtils.issueRedirect( httpRequest, httpResponse, unauthorizedUrl );
				} else {
					WebUtils.toHttp( response ).sendError( 401 );
				}
			} else {
				httpResponse.getWriter( ).print( JSONUtils.toJSONString( "error") );
			}
		}
		return false;
	}
}
