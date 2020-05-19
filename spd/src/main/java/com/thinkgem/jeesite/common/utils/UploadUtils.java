package com.thinkgem.jeesite.common.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传工具类
 * 
 * @author yangdc
 * @date Apr 18, 2012
 * 
 * <pre>
 * </pre>
 */
public class UploadUtils {
	
	private static final Logger  Log=org.slf4j.LoggerFactory.getLogger( UploadUtils.class );
	
	/**
	 * 表单字段常量
	 */
	public static final String FORM_FIELDS = "form_fields";
	/**
	 * 文件域常量
	 */
	public static final String FILE_FIELDS = "file_fields";

	// 最大文件大小
	private long maxSize = 10000000;
	// 定义允许上传的文件扩展名
	private Map<String, String> extMap = new HashMap<String, String>();
	// 文件保存目录相对路径
	private String basePath = "upload";
	// 文件的目录名
	private String dirName = "images";
	// 上传临时路径
	private static final String TEMP_PATH = "/temp";
	private String tempPath = basePath + TEMP_PATH;
	// 若不指定则文件名默认为 yyyyMMddHHmmss_xyz
	private String fileName;

	// 文件保存目录路径
	private String savePath;
	// 文件保存目录url
	private String saveUrl;
	// 文件最终的url包括文件名
	private String fileUrl;

	public UploadUtils() {
		// 其中images,flashs,medias,files,对应文件夹名称,对应dirName
		// key文件夹名称
		// value该文件夹内可以上传文件的后缀名
		extMap.put("images", "gif,jpg,jpeg,png,bmp");
		extMap.put("flashs", "swf,flv");
		extMap.put("medias", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("files", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
	}
	
	/**
	 * 获取该目录下所有文件
	 * @param fileArr
	 * @param file
	 */
	public static void getlistFile( List<File> fileArr, File file ) {
		if( file.isDirectory( ) )// 判断file是否是目录
		{
			File[] lists = file.listFiles( );
			if( lists != null ) {
				for( int i = 0; i < lists.length; i++ ) {
					getlistFile( fileArr, lists[i] );// 是目录就递归进入目录内再进行判断
				}
			}
		}
		fileArr.add( file );
	}
	
	/**
	 * 1、验证上传的文件格式
	 * 2、上传并返回路径
	 * @param request
	 * @param file
	 * @return
	 */
	public String  uploadAptitude(HttpServletRequest request,MultipartFile file, String  modules ,String fileNameExt) {
		String contentType = request.getContentType();
		int contentLength = request.getContentLength();
		String rootPath = com.thinkgem.jeesite.common.config.Global.getUserfilesBaseDir();
		if(StringUtils.isBlank( rootPath )){
			// 文件保存目录路径
			savePath = request.getSession().getServletContext().getRealPath("/")  + File.separator;
			saveUrl="";
			// 文件保存目录URL
		}else{
			savePath=com.thinkgem.jeesite.common.config.Global.getUserfilesBaseDir();
			saveUrl="";
		}
		File uploadDir = new File(savePath);
		if (contentType == null || !contentType.startsWith("multipart")) {
			Log.info( "请求不包含multipart/form-data流" );
			return null;
		} else if (maxSize < contentLength) {
			Log.info( "上传文件大小超出文件最大大小[" + maxSize + "]" );
			return null;
		} else if (!ServletFileUpload.isMultipartContent(request)) {
			Log.info( "请选择文件" );
			return null;
		} else if (!uploadDir.isDirectory()) {
			Log.info( "上传目录[" + savePath + "]不存在" );
			return null;
		} else if (!uploadDir.canWrite()) {
			Log.info( "上传目录[" + savePath + "]没有写权限");
			return null;
		} else if (!extMap.containsKey(dirName)) {
			Log.info( "目录名不正确");
			return null;
		} else {
			savePath += dirName + File.separator;
			saveUrl += dirName + File.separator;
			File saveDirFile = new File(savePath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
			savePath += modules + File.separator;
			saveUrl += modules + File.separator;
			File dirFile = new File(savePath);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
		}
		List<File> files = new ArrayList<File>( );
		//删除已经存在的
		File sourceDir = new File( savePath );
		getlistFile( files, sourceDir );
		if(files!=null && files.size( )>0){
			for(File f:files){
				if(f!=null && StringUtils.isNoneBlank( f.getName( ) )){
					String [] ll=f.getName( ).split( "_" );
					if(ll!=null && ll.length==2 && fileNameExt.equals( ll[0] )){
						f.delete( );
					}
				}
			}
		}
		String fileName=file.getOriginalFilename( );
		String  [] exts=fileName.split( "\\." );
		fileName=new Date( ).getTime( )+"."+exts[1];
		fileName=fileNameExt+"_"+fileName;
		savePath+=fileName;
    Log.info( "保存图片路径："+savePath );
    saveUrl += fileName;
    Log.info( "图片访问路径："+saveUrl );
		File parent = new File(savePath).getParentFile();
		if (parent != null && !parent.exists()) {
			parent.mkdirs();
		}
		if (!file.isEmpty()) {  
      try {  
      	file.transferTo(new File(savePath));  
      } catch (Exception e) {  
        e.printStackTrace();  
        Log.info( "上传图片异常！" );
        return null;
      }
    }
		return saveUrl;
	}
	
	
	public String[] uploadFile(HttpServletRequest request,MultipartFile file) {
		String[] infos = new String[4];
		infos[0] = validateFields(request);
				
		String fileName=file.getOriginalFilename( );
		String  [] exts=fileName.split( "\\." );
		fileName=new Date( ).getTime( )+"."+exts[1];
		fileName="c_"+fileName;
		
    String filePath = com.thinkgem.jeesite.common.config.Global.getUserfilesBaseDir()+UploadUtil.IMAGE_DIR + fileName;
    Log.info( "保存图片路径："+filePath );
    fileUrl = UploadUtil.IMAGE_DIR+ fileName;//文件上传的url
		File parent = new File(filePath).getParentFile();
		if (parent != null && !parent.exists()) {
			parent.mkdirs();
		}
		if (!file.isEmpty()) {  
      try {  
      	file.transferTo(new File(filePath));  
      } catch (Exception e) {  
        e.printStackTrace();  
        Log.info( "上传图片异常！" );
      }
      infos[1] = savePath;
			infos[2] = saveUrl;
			infos[3] = fileUrl;
    }
		return infos;
	}

	/**
	 * 文件上传
	 * 
	 * @param request
	 * @return infos info[0] 验证文件域返回错误信息 info[1] 上传文件错误信息 info[2] savePath info[3] saveUrl info[4] fileUrl
	 */
	@SuppressWarnings("unchecked")
	public String[] uploadFile(HttpServletRequest request) {
		String[] infos = new String[5];
		// 验证
		infos[0] = this.validateFields(request);
		// 初始化表单元素
		Map<String, Object> fieldsMap = new HashMap<String, Object>();
		if (infos[0].equals("true")) {
			fieldsMap = this.initFields(request);
		}
		// 上传
		List<FileItem> fiList = (List<FileItem>) fieldsMap.get(UploadUtils.FILE_FIELDS);
		if (fiList != null) {
			for (FileItem item : fiList) {
				infos[1] = this.saveFile(item);
			}
			infos[2] = savePath;
			infos[3] = saveUrl;
			infos[4] = fileUrl;
		}
		return infos;
	}

	/**
	 * 上传验证,并初始化文件目录
	 * 
	 * @param request
	 */
	private String validateFields(HttpServletRequest request) {
		String errorInfo = "true";
		// boolean errorFlag = true;
		// 获取内容类型
		String contentType = request.getContentType();
		int contentLength = request.getContentLength();
		// 文件保存目录路径
		savePath = request.getSession().getServletContext().getRealPath("/") + basePath + "/";
		// 文件保存目录URL
		saveUrl = request.getContextPath() + "/" + basePath + "/";
		File uploadDir = new File(savePath);
		if (contentType == null || !contentType.startsWith("multipart")) {
			// TODO
			System.out.println("请求不包含multipart/form-data流");
			errorInfo = "请求不包含multipart/form-data流";
		} else if (maxSize < contentLength) {
			// TODO
			System.out.println("上传文件大小超出文件最大大小");
			errorInfo = "上传文件大小超出文件最大大小[" + maxSize + "]";
		} else if (!ServletFileUpload.isMultipartContent(request)) {
			// TODO
			errorInfo = "请选择文件";
		} else if (!uploadDir.isDirectory()) {// 检查目录
			// TODO
			errorInfo = "上传目录[" + savePath + "]不存在";
		} else if (!uploadDir.canWrite()) {
			// TODO
			errorInfo = "上传目录[" + savePath + "]没有写权限";
		} else if (!extMap.containsKey(dirName)) {
			// TODO
			errorInfo = "目录名不正确";
		} else {
			// .../basePath/dirName/
			// 创建文件夹
			savePath += dirName + "/";
			saveUrl += dirName + "/";
			File saveDirFile = new File(savePath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
			// .../basePath/dirName/yyyyMMdd/
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String ymd = sdf.format(new Date());
			savePath += ymd + "/";
			saveUrl += ymd + "/";
			File dirFile = new File(savePath);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}

			// 获取上传临时路径
			tempPath = request.getSession().getServletContext().getRealPath("/") + tempPath + "/";
			File file = new File(tempPath);
			if (!file.exists()) {
				file.mkdirs();
			}
		}

		return errorInfo;
	}

	/**
	 * 处理上传内容
	 * 
	 * @param request
	 * @param maxSize
	 * @return
	 */
//	@SuppressWarnings("unchecked")
	private Map<String, Object> initFields(HttpServletRequest request) {

		// 存储表单字段和非表单字段
		Map<String, Object> map = new HashMap<String, Object>();

		// 第一步：判断request
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// 第二步：解析request
		if (isMultipart) {
			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// 阀值,超过这个值才会写到临时目录,否则在内存中
			factory.setSizeThreshold(1024 * 1024 * 10);
			factory.setRepository(new File(tempPath));

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

			upload.setHeaderEncoding("UTF-8");

			// 最大上传限制
			upload.setSizeMax(maxSize);

			/* FileItem */
			List<FileItem> items = null;
			// Parse the request
			try {
				items = upload.parseRequest(request);
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 第3步：处理uploaded items
			if (items != null && items.size() > 0) {
				Iterator<FileItem> iter = items.iterator();
				// 文件域对象
				List<FileItem> list = new ArrayList<FileItem>();
				// 表单字段
				Map<String, String> fields = new HashMap<String, String>();
				while (iter.hasNext()) {
					FileItem item = iter.next();
					// 处理所有表单元素和文件域表单元素
					if (item.isFormField()) { // 表单元素
						String name = item.getFieldName();
						String value = item.getString();
						fields.put(name, value);
					} else { // 文件域表单元素
						list.add(item);
					}
				}
				map.put(FORM_FIELDS, fields);
				map.put(FILE_FIELDS, list);
			}
		}
		return map;
	}

	/**
	 * 保存文件
	 * 
	 * @param obj
	 *            要上传的文件域
	 * @param file
	 * @return
	 */
	private String saveFile(FileItem item) {
		String error = "true";
		String fileName = item.getName();
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

		if (item.getSize() > maxSize) { // 检查文件大小
			// TODO
			error = "上传文件大小超过限制";
		} else if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(fileExt)) {// 检查扩展名
			error = "上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。";
		} else {
			String newFileName;
			if ("".equals(fileName.trim())) {
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
			} else {
				newFileName = fileName + "." + fileExt;
			}
			// .../basePath/dirName/yyyyMMdd/yyyyMMddHHmmss_xxx.xxx
			fileUrl = saveUrl + newFileName;
			try {
				File uploadedFile = new File(savePath, newFileName);

				item.write(uploadedFile);

				/*
				 * FileOutputStream fos = new FileOutputStream(uploadFile); // 文件全在内存中 if (item.isInMemory()) { fos.write(item.get()); } else { InputStream is = item.getInputStream(); byte[] buffer =
				 * new byte[1024]; int len; while ((len = is.read(buffer)) > 0) { fos.write(buffer, 0, len); } is.close(); } fos.close(); item.delete();
				 */
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("上传失败了！！！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return error;
	}

	/** **********************get/set方法********************************* */

	public String getSavePath() {
		return savePath;
	}

	public String getSaveUrl() {
		return saveUrl;
	}

	public long getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(long maxSize) {
		this.maxSize = maxSize;
	}

	public Map<String, String> getExtMap() {
		return extMap;
	}

	public void setExtMap(Map<String, String> extMap) {
		this.extMap = extMap;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
		tempPath = basePath + TEMP_PATH;
	}

	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
	}

	public String getTempPath() {
		return tempPath;
	}

	public void setTempPath(String tempPath) {
		this.tempPath = tempPath;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
	
	/**
	 * 字节转换为图片
	 * @param buff 字节
	 * @param path 图片保持地址
	 * @return
	 */
	public static void bufftoImg(byte[] buff ,String path){
		try {
			ByteArrayInputStream imageStream = new ByteArrayInputStream(buff);
			BufferedImage image = ImageIO.read(imageStream);
			ImageIO.write(image, "jpg", new File(path));  
		} catch (IOException e) {
			e.printStackTrace( );
		}
	}
}
