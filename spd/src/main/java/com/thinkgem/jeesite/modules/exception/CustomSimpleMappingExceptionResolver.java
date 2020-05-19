package com.thinkgem.jeesite.modules.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * sring mvc 提供的异常处理类，继承并扩展了该类，对 普通的exception 自定义的BusinessException,SystemException,
 * 运行期产生了Unchecked Exception等异常，包括ajax 异常统一拦截
 * @author Administrator
 */
public class CustomSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver {
	@Override
	protected ModelAndView doResolveException( HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex ) {
		String viewName = determineViewName( ex, request );
		if( viewName == null ) {
			return null;
		}
	 // 如果不是异步请求
		if( !( request.getHeader( "accept" ).indexOf( "application/json" ) > -1 || ( request.getHeader( "X-Requested-With" ) != null && request
				.getHeader( "X-Requested-With" ).indexOf( "XMLHttpRequest" ) > -1 ) ) ) {
			Map<String, Object> map = new HashMap<String, Object>( );
			StringPrintWriter strintPrintWriter = new StringPrintWriter( );
			ex.printStackTrace( strintPrintWriter );
			map.put( "code", strintPrintWriter.getString( ) );// 将错误信息传递给view
			map.put( "message", ex.getMessage( ) );
			Integer statusCode = determineStatusCode( request, viewName );
			if( statusCode != null ) {
				applyStatusCodeIfPossible( request, response, statusCode );
			}
			return new ModelAndView( viewName, map );
		} else {
			// JSON格式返回
			try {
				PrintWriter writer = response.getWriter( );
				writer.write( ex.getMessage( ) );
				writer.flush( );
			} catch( IOException e ) {
				e.printStackTrace( );
			}
			return null;
		}
	}
}
