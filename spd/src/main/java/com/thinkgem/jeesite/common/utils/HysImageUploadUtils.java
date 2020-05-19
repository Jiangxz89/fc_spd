package com.thinkgem.jeesite.common.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.encoder.BASE64Decoder;


/**
 * 图片上传工具类
 * @author JiaSong
 * @date 2017-01-18
 *
 */
public class HysImageUploadUtils {
	
	/**
	 * 目前路径为: /mnt/sdc/tomcat-hys-controller/webapps/ROOT/demo/images/lollipop/type/文件名
	 * 
	 * type
			轮播图	:	carousel
			文章		:	article
	 */
	public static final String BASE_DIR = "huicy";
	public static final String TYPE_CAROUSEL = "carousel";
	public static final String TYPE_PHOTO = "photoimg";//会员头像图
	public static final String TYPE_WX_QRCODE = "wx/createQRCode";//微信商品图片
	public static final String TYPE_ARTICLE = "article";
	public static final String TYPE_ARTIVITY = "artivity";
	public static final String ONLINE_TEACH = "teach";
	
	/**
	 * 保存图片 返回相对路径的url
	 * @param img
	 * @param type
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public static String upload(MultipartFile img, String type) throws IllegalStateException, IOException {
		String urls=null;
		if(img.getSize( )>0){
			String extName = img.getOriginalFilename().substring(img.getOriginalFilename().lastIndexOf("."));
			Long longTime = new Date().getTime();// 获取随机名称// 取时间长整型值
			String fullName = longTime+TenpayUtil.buildRandom(4)+ extName;
			urls= UploadUtil.IMAGE_DIR + BASE_DIR + "/" + type + "/" + fullName;
			String fullPath = Global.getUserfilesBaseDir() + urls;
			createDir(fullPath);
			img.transferTo(new File(fullPath));
		}
		return urls;
	}
	
	/**
	 * 根据图片URL,把图片保存到图片服务器
	 * @param picUrl	图片路径
	 * @param picLoadName	保存到图片服务器的名字
	 * @throws Exception 
	 */
	public static String savePicToFile(String picUrl, String type) throws Exception{
		//String extName = picUrl.substring(picUrl.lastIndexOf("."));
		Long longTime = new Date().getTime();// 获取随机名称// 取时间长整型值
		String fullName = longTime + ".jpg";
		HttpURLConnection httpUrl = null; 	//url链接
		URL url = null;
		byte[] buf = new byte[1024];	//分配1024个字节大小的内存给buf 1KB的缓存
		int size = 0; 
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		String urlImg = null;
		try {
			url = new URL(picUrl);
			httpUrl = (HttpURLConnection) url.openConnection();	//打开链接
			httpUrl.connect();
			bis = new BufferedInputStream(httpUrl.getInputStream());
			String fullPath = Global.getUserfilesBaseDir() + UploadUtil.IMAGE_DIR + BASE_DIR + "/" + type +"/";
			urlImg = UploadUtil.IMAGE_DIR + BASE_DIR + "/" + TYPE_WX_QRCODE +"/" + fullName;
			File sf=new File(fullPath);  
			if(!sf.exists()){  
				sf.mkdirs();  
			}  
			fos = new FileOutputStream(fullPath+fullName);
			while ((size = bis.read(buf)) != -1){
				fos.write(buf,0,size);
			}
			fos.flush();
			return urlImg;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fos.close();
			bis.close();
			httpUrl.disconnect();
		}
		return urlImg;
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
		return Global.getUserfilesBaseUrl() + upload(img, type);
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
	/***
	 * 判断上传文件是否为空
	 * @param imgs
	 * @return
	 */
	public static boolean isImgEmpty(MultipartFile[] imgs){
		for (int i=0; i<imgs.length; i++) {
			MultipartFile img = imgs[i];
			if(img.isEmpty()){
				return true;
			}
		}
		return false;
	}

	
	/**
	 * 保存图片 返回相对路径的url
	 * @param img
	 * @param shopCode
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public static String registerUploadImage(String imgData, String shopCode)
			throws IllegalStateException, IOException {
		Long longTime = new Date().getTime();// 获取随机名称// 取时间长整型值
		String fullName = longTime + TenpayUtil.buildRandom(4) + "";
		String urls = UploadUtil.IMAGE_DIR + TYPE_PHOTO + "/" + shopCode + "/" + fullName+".png";
		String fullPath = Global.getUserfilesBaseDir() + urls;
		createDir(fullPath);
		BASE64Decoder d = new BASE64Decoder();
		byte[] bs = d.decodeBuffer(imgData.split(",")[1]);
		FileOutputStream os = new FileOutputStream(fullPath);
		os.write(bs);
		os.close();
		return urls;
	}
	
}