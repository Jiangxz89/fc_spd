package com.thinkgem.jeesite.modules.sys.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileUtils {
	/**
	 * 将文件写入到zip文件中
	 * 
	 * @param inputFile
	 * @param outputstream
	 * @throws ServletException
	 * @throws Exception
	 */
	public static void zipFile( File inputFile, ZipOutputStream outputstream ) throws IOException, ServletException {
		try {
			if( !inputFile.exists( ) ) {
				throw new ServletException( "文件不存在！" );
			}
			if( inputFile.isFile( ) ) {
				FileInputStream inStream = new FileInputStream( inputFile );
				BufferedInputStream bInStream = new BufferedInputStream( inStream );
				ZipEntry entry = new ZipEntry( inputFile.getName( ) );
				outputstream.putNextEntry( entry );
				final int MAX_BYTE = 10 * 1024 * 1024; // 最大的流为10M
				long streamTotal = 0; // 接受流的容量
				int streamNum = 0; // 流需要分开的数量
				int leaveByte = 0; // 文件剩下的字符数
				byte[] inOutbyte; // byte数组接受文件的数据
				streamTotal = bInStream.available( ); // 通过available方法取得流的最大字符数
				streamNum = (int) Math.floor( streamTotal / MAX_BYTE ); // 取得流文件需要分开的数量
				leaveByte = (int) streamTotal % MAX_BYTE; // 分开文件之后,剩余的数量
				if( streamNum > 0 ) {
					for( int j = 0; j < streamNum; ++j ) {
						inOutbyte = new byte[MAX_BYTE];
						// 读入流,保存在byte数组
						bInStream.read( inOutbyte, 0, MAX_BYTE );
						outputstream.write( inOutbyte, 0, MAX_BYTE ); // 写出流
					}
				}
				// 写出剩下的流数据
				inOutbyte = new byte[leaveByte];
				bInStream.read( inOutbyte, 0, leaveByte );
				outputstream.write( inOutbyte );
				outputstream.closeEntry( );
				bInStream.close( ); // 关闭
				inStream.close( );
			}
		} catch( IOException e ) {
			throw e;
		}
	}

	/**
	 * 压缩文件列表中的文件
	 * @param files
	 * @param outputStream
	 * @throws IOException
	 */
	public static void zipFile( List<File> files, ZipOutputStream outputStream ) throws IOException, ServletException {
		try {
			int size = files.size( );
			// 压缩列表中的文件
			for( int i = 0; i < size; i++ ) {
				File file = (File) files.get( i );
				zipFile( file, outputStream );
			}
		} catch( IOException e ) {
			throw e;
		}
	}

	/**
	 * tomcat 部署路径
	 * @return
	 */
	public static File getWebAppDirectory( ) {
		String currenFilePath = FileUtils.class.getResource( "" ).getPath( );
		File file = new File( currenFilePath );
		while( !file.getName( ).toUpperCase( ).equals( "WEB-INF" ) ) {
			file = file.getParentFile( );
		}
		File webAppDirFile = file.getParentFile( );
		return webAppDirFile;
	}

	/**
	 * 创建文件
	 * @param path
	 * @param fileName
	 */
	public static  void createFile( String path, String fileName ) {
		// path表示你所创建文件的路径
		File f = new File( path );
		File file = new File( f, fileName );
		if( !file.exists( ) ) {
			try {
				file.createNewFile( );
			} catch( IOException e ) {
				e.printStackTrace( );
			}
		}
	}

	/**
	 * 设置响应头信息
	 * @param file
	 * @param response
	 * @param isDelete
	 */
	public static  void downloadFile( File file,HttpServletRequest request, HttpServletResponse response, boolean isDelete ) {
		try {
			BufferedInputStream fis = new BufferedInputStream( new FileInputStream( file.getPath( ) ) );
			byte[] buffer = new byte[fis.available( )];
			String fileName=file.getName( );
			if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {  
				fileName = URLEncoder.encode(fileName, "UTF-8");  
		  } else {  
		  	fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");  
		  }  
			fis.read( buffer );
			fis.close( );
			response.reset( );
			OutputStream toClient = new BufferedOutputStream( response.getOutputStream( ) );
			response.setContentType( "application/octet-stream" );
			response.setHeader( "Content-Disposition", "attachment;filename=" + fileName);
			response.setCharacterEncoding("UTF-8");
			toClient.write( buffer );
			toClient.flush( );
			toClient.close( );
			if( isDelete ) {
				file.delete( ); // 是否将生成的服务器端文件删除
			}
		} catch( IOException ex ) {
			ex.printStackTrace( );
		}
	}

}
