package com.thinkgem.jeesite.modules.sys.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.modules.sys.SysConstant;

@Service
public class LogoutFilter extends org.apache.shiro.web.filter.authc.LogoutFilter {
	@Override
	protected boolean preHandle( ServletRequest request, ServletResponse response ) throws Exception {
		// TODO Auto-generated method stub
		Subject subject = getSubject( request, response );
		String redirectUrl = getRedirectUrl( request, response, subject );
		try {
			HttpServletRequest req=(HttpServletRequest)request;
			if(SysConstant.ADMIN_LOGIN.equals(SysConstant.getLoginType( req ))){
				redirectUrl= SysConstant.ADMIN_LOGIN + "/login";
			}else if(SysConstant.FRONT_LOGIN.equals(SysConstant.getLoginType( req ))){
				redirectUrl= SysConstant.FRONT_LOGIN + "/login";
			}
			subject.logout( );
		} catch( SessionException e ) {
			e.printStackTrace( );
		}
		issueRedirect( request, response, redirectUrl );
		return false;
	}
}
