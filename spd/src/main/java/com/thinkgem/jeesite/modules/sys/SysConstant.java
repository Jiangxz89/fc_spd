package com.thinkgem.jeesite.modules.sys;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Repository;
import com.thinkgem.jeesite.common.config.Global;
/**
 * 系统常量类
 * @author lenovo
 */
@Repository
public abstract  class SysConstant {
 
	/**
	 * 前台登录路由
	 */
	public static  String FRONT_LOGIN=Global.getPortal( );
	
	/**
	 * 后天登录路由
	 */
	public  static String ADMIN_LOGIN=Global.getAdmin( );
	
	/**
	 * app登录路由
	 */
	public  static String APP_LOGIN=Global.getAppPath( );
		
	/**
	 *前台Cookie path
	 */
	public static final String FRONT_LOGIN_COOKIE="/"+FRONT_LOGIN;
	
	/**
	 * 后台cookie path
	 */
	public static final String ADMIN_LOGIN_COOKIE="/"+ADMIN_LOGIN;
	
	/**
	 * 获取前台首页地址
	 */
	public  static String portal_view_index=Global.getPortalIndex( );
	
	
	/**
	 * 根据域名或路径判断是前台登录或后台登录
	 * @param request
	 * @return
	 */
	public  static String getLoginType( HttpServletRequest request ) {
		String path=request.getServletPath( );
		String [] pathArr=path.split( "/" );
		List<String> pathList=Arrays.asList( pathArr );
		if(pathList.size( )>1 && pathList.contains( SysConstant.FRONT_LOGIN ) &&   SysConstant.FRONT_LOGIN.equals( pathList.get( 1 ) )){
			return SysConstant.FRONT_LOGIN;
		}else if(pathList.size( )>1 && pathList.contains( SysConstant.ADMIN_LOGIN ) &&  SysConstant.ADMIN_LOGIN.equals( pathList.get( 1 ) )){
			return SysConstant.ADMIN_LOGIN;
		}else if(pathList.size( )>1 && pathList.contains( SysConstant.APP_LOGIN ) &&  SysConstant.APP_LOGIN.equals( pathList.get( 1 ) )){
			return SysConstant.APP_LOGIN;
		}
		return null;
	}
}
