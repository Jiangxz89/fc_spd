package com.thinkgem.jeesite.common.web;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.impl.util.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.HysFileUploadUtils;
import com.thinkgem.jeesite.common.utils.HysImageUploadUtils;
import com.thinkgem.jeesite.common.utils.JacksonJsonUtil;
import com.thinkgem.jeesite.common.utils.UploadUtil;




/**
 * KindEditor控件对应的文件上传类
 * 文件上传Controller
 * @author JiaSong
 * @version 2016-12-23
 */
@Controller
@RequestMapping(value = "${adminPath}/file")
public class FileUploadController extends BaseController {
	
	@RequestMapping(value = "uploadImages")
	@ResponseBody
	public void uploadImages(String dir, Model model, MultipartFile imgFile, RedirectAttributes redirectAttributes,HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		String extName = imgFile.getOriginalFilename().substring(imgFile.getOriginalFilename().lastIndexOf("."));
		Long longTime = new Date().getTime();// 获取随机名称// 取时间长整型值
		String fullName = longTime + extName;
		String fullPath = Global.getUserfilesBaseDir() + UploadUtil.IMAGE_DIR + HysFileUploadUtils.BASE_DIR + fullName;
		String urls = Global.getUserfilesBaseUrl()+UploadUtil.IMAGE_DIR + HysFileUploadUtils.BASE_DIR + fullName;// 文件上传的url
		HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "jpg,jpeg,png,bmp");
		// 检查扩展名
        String fileExt = imgFile.getOriginalFilename().substring(imgFile.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
        if (!Arrays.<String> asList(extMap.get("image").split(",")).contains(fileExt)) {
        	JSONObject obj = new JSONObject();
    		obj.put("error", UPLOAD_FAILURE);
    		obj.put("message", "不支持此上传文件扩展名。\n只允许"+ extMap.get("image") + "格式。");
    		//return obj;
    		response.setContentType("text/html; charset=UTF-8");
    		try {
				response.getWriter().write(JacksonJsonUtil.beanToJson(obj));
			} catch (IOException e) {
				e.printStackTrace();
			}
        }else{
        	try {
        		HysImageUploadUtils.createDir(fullPath);
        		imgFile.transferTo(new File(fullPath));
        		result.put("error", UPLOAD_SUCCESS);
        		result.put("url", urls);
        		response.getWriter().write(com.alibaba.fastjson.JSONObject.toJSON(result).toString());
        	} catch (IllegalStateException e) {
        		e.printStackTrace();
        	} catch (IOException e) {
        		e.printStackTrace();
        	}
        }
	}
	
	public final static String DIR_IMG = "image";
	public final static String DIR_FILE = "file";
	public final static String DIR_VIDEO = "media";
	public final static int UPLOAD_SUCCESS = 0;
	public final static int UPLOAD_FAILURE = 1;
	
	@RequestMapping(value = "upload")
	public void upload(String dir, Model model, MultipartFile imgFile, RedirectAttributes redirectAttributes, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String url = "";
			if(DIR_FILE.equals(dir)) {
				url = Global.getUserfilesBaseUrl() + HysFileUploadUtils.upload(imgFile, HysFileUploadUtils.ARTICLE);
			} else if(DIR_IMG.equals(dir)) {
				url = Global.getUserfilesBaseUrl() + HysImageUploadUtils.upload(imgFile, HysImageUploadUtils.TYPE_ARTICLE);
			}else if(DIR_VIDEO.equals(dir)){
				url = Global.getUserfilesBaseUrl() + HysImageUploadUtils.upload(imgFile, HysImageUploadUtils.TYPE_ARTICLE);
			}
			result.put("error", 0);
			result.put("url", url);
			System.out.println(com.alibaba.fastjson.JSONObject.toJSON(result).toString());
			response.getWriter().write(com.alibaba.fastjson.JSONObject.toJSON(result).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}