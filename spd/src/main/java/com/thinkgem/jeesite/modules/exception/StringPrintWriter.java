package com.thinkgem.jeesite.modules.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 打印辅助类
 * 
 * @author Administrator
 */
public class StringPrintWriter extends PrintWriter {

	public StringPrintWriter( ) {
		super( new StringWriter( ) );
	}

	public StringPrintWriter( int initialSize ) {
		super( new StringWriter( initialSize ) );
	}

	public String getString( ) {
		flush( );
		return ( (StringWriter) this.out ).toString( );
	}

	@Override
	public String toString( ) {
		return getString( );
	}
}
