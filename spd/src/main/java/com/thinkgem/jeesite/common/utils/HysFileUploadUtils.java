package com.thinkgem.jeesite.common.utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.thinkgem.jeesite.common.config.Global;


/**
 * 文件上传工具类
 * @author JiaSong
 * @date 2017-01-18
 *
 */
public class HysFileUploadUtils {
	
	/**
	 * 目前路径为: /mnt/sdc/tomcat-hys-controller/webapps/ROOT/demo/images/lollipop/type/文件名
	 * 
	 * type
			文章		:	article
	 */
	public static final String BASE_DIR = "huicy";
	public static final String ARTICLE = "article";
	
	/**
	 * 保存图片 返回相对路径的url
	 * @param file
	 * @param type
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public static String upload(MultipartFile file, String type) throws IllegalStateException, IOException {
		String extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		Long longTime = new Date().getTime();// 获取随机名称// 取时间长整型值
		String fullName = longTime + extName;
		String urls = UploadUtil.FILE_DIR + BASE_DIR + "/" + type + "/" + fullName;
		String fullPath = getUrlHead() + urls;
		createDir(fullPath);
		file.transferTo(new File(fullPath));
		return urls;
	}
	
	/**
	 * 保存图片 返回绝对路径的url
	 * @param img
	 * @param type
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public static String uploadAndReturnFullUrl(MultipartFile img, String type) throws IllegalStateException, IOException {
		return getUrlHead() + upload(img, type);
	}
	
	private static String getUrlHead() {
		return Global.getUserfilesBaseDir();
	}

	/**
	 * @功能描述:创建文件夹
	 */
	public static void createDir(String path) {
		File file = new File(path);
		File parent = file.getParentFile();
		if (parent != null && !parent.exists()) {
			parent.mkdirs();
		}
	}
}