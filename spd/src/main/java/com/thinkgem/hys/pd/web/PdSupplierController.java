/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web;

import com.thinkgem.hys.pd.entity.PdStockRecord;
import com.thinkgem.hys.pd.entity.PdSupplier;
import com.thinkgem.hys.pd.service.PdStockRecordService;
import com.thinkgem.hys.pd.service.PdSupplierService;
import com.thinkgem.hys.utils.SpdUtils;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.UploadUtil;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.utils.SnoGerUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 供应商信息Controller
 * @author changjundong
 * @version 2018-04-28
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdSupplier")
public class PdSupplierController extends BaseController {

	@Autowired
	private PdSupplierService pdSupplierService;
	
	@Autowired
	private PdStockRecordService pdStockRecordService;
	
	@ModelAttribute
	public PdSupplier get(@RequestParam(required=false) String id) {
		PdSupplier entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdSupplierService.get(id);
		}
		if (entity == null){
			entity = new PdSupplier();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:pdSupplier:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdSupplier pdSupplier, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdSupplier> page = pdSupplierService.findPage(new Page<PdSupplier>(request, response), pdSupplier); 
		model.addAttribute("page", page);
		return "hys/pd/pdSupplierList";
	}

	@RequiresPermissions("pd:pdSupplier:view")
	@RequestMapping(value = "form")
	public String form(PdSupplier pdSupplier, Model model,HttpServletRequest request) {
		String flag = request.getParameter("flag");
		if(flag.equals("update")){
			pdSupplier = pdSupplierService.get(pdSupplier.getId());
		}
		model.addAttribute("pdSupplier", pdSupplier);
		request.setAttribute("flag", flag);
		model.addAttribute("pdSupplier", pdSupplier);
		return "hys/pd/pdSupplierForm";
	}
	
	//跳转到修改页面
	@RequestMapping(value = "toUpdate")
	public String toUpdate(PdSupplier pdSupplier, Model model,HttpServletRequest request) {
		model.addAttribute("pdSupplier", pdSupplier);
		String flag = request.getParameter("flag");
		request.setAttribute("flag",flag);
		pdSupplier = pdSupplierService.get(pdSupplier.getId());
		model.addAttribute("pdSupplier", pdSupplier);
		return "hys/pd/pdSupplierUpdate";
	}
	
	
	/**
	 * 保存供应商图片等信息
	 * @param pdSupplier
	 * @param imgUrl1Up
	 * @param imgUrl2Up
	 * @param imgUrl3Up
	 * @param imgUrl4Up
	 * @param imgUrl5Up
	 * @param imgUrl6Up
	 * @param imgUrl7Up
	 * @param imgUrl8Up
	 * @param imgUrl9Up
	 * @param imgUrl10Up
	 * @param imgUrl11Up
	 * @param imgUrl12Up
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @return
	 */
	@RequiresPermissions("pd:pdSupplier:edit")
	@RequestMapping(value = "save")
	public String save(PdSupplier pdSupplier,@RequestParam MultipartFile[] imgUrl1Up,
			@RequestParam MultipartFile[] imgUrl2Up,@RequestParam MultipartFile[] imgUrl3Up,
			@RequestParam MultipartFile[] imgUrl4Up,@RequestParam MultipartFile[] imgUrl5Up,
			@RequestParam MultipartFile[] imgUrl6Up,@RequestParam MultipartFile[] imgUrl7Up,
			@RequestParam MultipartFile[] imgUrl8Up,@RequestParam MultipartFile[] imgUrl9Up,
			@RequestParam MultipartFile[] imgUrl10Up,@RequestParam MultipartFile[] imgUrl11Up,
			@RequestParam MultipartFile[] imgUrl12Up,
			Model model, RedirectAttributes redirectAttributes,MultipartHttpServletRequest request) {
		if (!beanValidator(model, pdSupplier)){
			return form(pdSupplier, model,request);
		}
		//查询供应商名称是否已经存在
		List<PdSupplier>  suppliers = pdSupplierService.verify(pdSupplier);
		if(suppliers!=null && suppliers.size()>0){
			addMessage(redirectAttributes, "保存供应商失败,供应商已存在");
		}else{
			//存入图片
			if(!isImgEmpty(imgUrl1Up)){//产品授权书图片
				pdSupplier.setImgUrl1(saveFile(imgUrl1Up));
			}
			if(!isImgEmpty(imgUrl2Up)){//产品授权书图片
				pdSupplier.setImgUrl2(saveFile(imgUrl2Up));
			}
			if(!isImgEmpty(imgUrl3Up)){//产品授权书图片
				pdSupplier.setImgUrl3(saveFile(imgUrl3Up));
			}
			if(!isImgEmpty(imgUrl4Up)){//产品授权书图片
				pdSupplier.setImgUrl4(saveFile(imgUrl4Up));
			}
			if(!isImgEmpty(imgUrl5Up)){//产品授权书图片
				pdSupplier.setImgUrl5(saveFile(imgUrl5Up));
			}
			if(!isImgEmpty(imgUrl6Up)){//产品授权书图片
				pdSupplier.setImgUrl6(saveFile(imgUrl6Up));
			}
			if(!isImgEmpty(imgUrl7Up)){//产品授权书图片
				pdSupplier.setImgUrl7(saveFile(imgUrl7Up));
			}
			if(!isImgEmpty(imgUrl8Up)){//产品授权书图片
				pdSupplier.setImgUrl8(saveFile(imgUrl8Up));
			}
			if(!isImgEmpty(imgUrl9Up)){//产品授权书图片
				pdSupplier.setImgUrl9(saveFile(imgUrl9Up));
			}
			if(!isImgEmpty(imgUrl10Up)){//产品授权书图片
				pdSupplier.setImgUrl10(saveFile(imgUrl10Up));
			}
			if(!isImgEmpty(imgUrl11Up)){//产品授权书图片
				pdSupplier.setImgUrl11(saveFile(imgUrl11Up));
			}
			if(!isImgEmpty(imgUrl12Up)){//产品授权书图片
				pdSupplier.setImgUrl12(saveFile(imgUrl12Up));
			}
			
			pdSupplierService.save(pdSupplier);
			//更新供应商信息
			SpdUtils.updateSupploerInfo();
			addMessage(redirectAttributes, "保存供应商信息成功");
		}
		return "redirect:"+Global.getAdminPath()+"/pd/pdSupplier/?repage";
	}
	
	/**
	 * 供应商修改
	 * @param pdSupplier
	 * @param imgUrl1Up
	 * @param imgUrl2Up
	 * @param imgUrl3Up
	 * @param imgUrl4Up
	 * @param imgUrl5Up
	 * @param imgUrl6Up
	 * @param imgUrl7Up
	 * @param imgUrl8Up
	 * @param imgUrl9Up
	 * @param imgUrl10Up
	 * @param imgUrl11Up
	 * @param imgUrl12Up
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @return
	 */
	@RequiresPermissions("pd:pdSupplier:edit")
	@RequestMapping(value = "update")
	public String update(PdSupplier pdSupplier,@RequestParam MultipartFile[] imgUrl1Up,
			@RequestParam MultipartFile[] imgUrl2Up,@RequestParam MultipartFile[] imgUrl3Up,
			@RequestParam MultipartFile[] imgUrl4Up,@RequestParam MultipartFile[] imgUrl5Up,
			@RequestParam MultipartFile[] imgUrl6Up,@RequestParam MultipartFile[] imgUrl7Up,
			@RequestParam MultipartFile[] imgUrl8Up,@RequestParam MultipartFile[] imgUrl9Up,
			@RequestParam MultipartFile[] imgUrl10Up,@RequestParam MultipartFile[] imgUrl11Up,
			@RequestParam MultipartFile[] imgUrl12Up,
			Model model, RedirectAttributes redirectAttributes,MultipartHttpServletRequest request) {
		//查询供应商名称是否已经存在
		List<PdSupplier>  suppliers = pdSupplierService.verify(pdSupplier);
		if(suppliers!=null && suppliers.size()>0){
			addMessage(redirectAttributes, "更新供应商失败,供应商已存在");
		}else{
			//存入图片
			if(!isImgEmpty(imgUrl1Up)){//产品授权书图片
				String filePath = saveFile(imgUrl1Up);
				if(pdSupplier.getImgUrl1()!=null && !"".equals(pdSupplier.getImgUrl1())){
					//先删除再更新
					FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl1());
				}
				pdSupplier.setImgUrl1(filePath);
			}
			if(!isImgEmpty(imgUrl2Up)){//产品授权书图片
				String filePath = saveFile(imgUrl2Up);
				if(pdSupplier.getImgUrl2()!=null && !"".equals(pdSupplier.getImgUrl2())){
					//先删除再更新
					FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl2());
				}
				pdSupplier.setImgUrl2(filePath);
			}
			if(!isImgEmpty(imgUrl3Up)){//产品授权书图片
				String filePath = saveFile(imgUrl3Up);
				if(pdSupplier.getImgUrl3()!=null && !"".equals(pdSupplier.getImgUrl3())){
					//先删除再更新
					FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl3());
				}
				pdSupplier.setImgUrl3(filePath);
			}
			if(!isImgEmpty(imgUrl4Up)){//产品授权书图片
				String filePath = saveFile(imgUrl4Up);
				if(pdSupplier.getImgUrl4()!=null && !"".equals(pdSupplier.getImgUrl4())){
					//先删除再更新
					FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl4());
				}
				pdSupplier.setImgUrl4(filePath);
			}
			if(!isImgEmpty(imgUrl5Up)){//产品授权书图片
				String filePath = saveFile(imgUrl5Up);
				if(pdSupplier.getImgUrl5()!=null && !"".equals(pdSupplier.getImgUrl5())){
					//先删除再更新
					FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl5());
				}
				pdSupplier.setImgUrl5(filePath);
			}
			if(!isImgEmpty(imgUrl6Up)){//产品授权书图片
				String filePath = saveFile(imgUrl6Up);
				if(pdSupplier.getImgUrl6()!=null && !"".equals(pdSupplier.getImgUrl6())){
					//先删除再更新
					FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl6());
				}
				pdSupplier.setImgUrl6(filePath);
			}
			if(!isImgEmpty(imgUrl7Up)){//产品授权书图片
				String filePath = saveFile(imgUrl7Up);
				if(pdSupplier.getImgUrl7()!=null && !"".equals(pdSupplier.getImgUrl7())){
					//先删除再更新
					FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl7());
				}
				pdSupplier.setImgUrl7(filePath);
			}
			if(!isImgEmpty(imgUrl8Up)){//产品授权书图片
				String filePath = saveFile(imgUrl8Up);
				if(pdSupplier.getImgUrl8()!=null && !"".equals(pdSupplier.getImgUrl8())){
					//先删除再更新
					FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl8());
				}
				pdSupplier.setImgUrl8(filePath);
			}
			if(!isImgEmpty(imgUrl9Up)){//产品授权书图片
				String filePath = saveFile(imgUrl9Up);
				if(pdSupplier.getImgUrl9()!=null && !"".equals(pdSupplier.getImgUrl9())){
					//先删除再更新
					FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl9());
				}
				pdSupplier.setImgUrl9(filePath);
			}
			if(!isImgEmpty(imgUrl10Up)){//产品授权书图片
				String filePath = saveFile(imgUrl10Up);
				if(pdSupplier.getImgUrl10()!=null && !"".equals(pdSupplier.getImgUrl10())){
					//先删除再更新
					FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl10());
				}
				pdSupplier.setImgUrl10(filePath);
			}
			if(!isImgEmpty(imgUrl11Up)){//产品授权书图片
				String filePath = saveFile(imgUrl11Up);
				if(pdSupplier.getImgUrl11()!=null && !"".equals(pdSupplier.getImgUrl11())){
					//先删除再更新
					FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl11());
				}
				pdSupplier.setImgUrl11(filePath);
			}
			if(!isImgEmpty(imgUrl12Up)){//产品授权书图片
				String filePath = saveFile(imgUrl12Up);
				if(pdSupplier.getImgUrl12()!=null && !"".equals(pdSupplier.getImgUrl12())){
					//先删除再更新
					FileUtils.deleteFile(userfilesBasedir+"\\"+pdSupplier.getImgUrl12());
				}
				pdSupplier.setImgUrl12(filePath);
			}
			
			pdSupplierService.update(pdSupplier);
			//更新供应商信息
			SpdUtils.updateSupploerInfo();
			addMessage(redirectAttributes, "保存供应商信息成功");
		}
		return "redirect:"+Global.getAdminPath()+"/pd/pdSupplier/?repage";
	}
	
	@RequiresPermissions("pd:pdSupplier:edit")
	@RequestMapping(value = "delete")
	public String delete(PdSupplier pdSupplier, RedirectAttributes redirectAttributes) {
		PdStockRecord pdStockRecord = new PdStockRecord();
		pdStockRecord.setSupplierId(pdSupplier.getId());
		//供应商删除校验
		List<PdStockRecord> pdStockRecords = pdStockRecordService.findList(pdStockRecord);
		if(null !=pdStockRecords && pdStockRecords.size()>0){
			addMessage(redirectAttributes, "该供应商已被使用不能删除");
		}else{
			pdSupplierService.delete(pdSupplier);
			addMessage(redirectAttributes, "删除供应商信息成功");
		}
		//更新供应商信息
		SpdUtils.updateSupploerInfo();
		return "redirect:"+Global.getAdminPath()+"/pd/pdSupplier/?repage";
	}
	
	// 保存多个图片
	private String saveFile(MultipartFile[] imgs) {
		StringBuilder urls = new StringBuilder("");
		for (int i=0; i<imgs.length; i++) {
			MultipartFile img = imgs[i];
			String extName = img.getOriginalFilename().substring(img.getOriginalFilename().lastIndexOf("."));
			Long longTime = new Date().getTime();// 获取随机名称// 取时间长整型值
			String serialNumber = SnoGerUtil.getSerialNumber(5);
			String fullName = longTime + serialNumber + extName;
			String fullPath = Global.getUserfilesBaseDir()+UploadUtil.IMAGE_DIR +"spd/"+ fullName;
			String  url = UploadUtil.IMAGE_DIR+"spd/"+ fullName;//文件上传的url
			try {
				createDir(fullPath);
				img.transferTo(new File(fullPath));
				if(0==i){
					urls.append(url);
				}else{
					urls.append("&&&"+url);
				}
			} catch (IllegalStateException e) {
				e.printStackTrace();
				return  null;
			} catch (IOException e) {
				e.printStackTrace();
				return  null;
			}
		}
		return urls.toString();
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
	
	/**
	 * 判断图片非空
	 * @param imgs
	 * @return
	 */
	private boolean isImgEmpty(MultipartFile[] imgs){
		for (int i=0; i<imgs.length; i++) {
			MultipartFile img = imgs[i];
			if(img.isEmpty()){
				return true;
			}
		}
		return false;
	}
	
	@RequiresPermissions("pd:pdSupplier:edit")
	@RequestMapping(value = "batchDelete")
	public String batchDelete(PdSupplier pdSupplier,String pdSupplierIds, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(StringUtils.isNotBlank(pdSupplierIds)){
			List<String> result = Arrays.asList(pdSupplierIds.split(","));
			pdSupplierService.batchDelete(result);
			addMessage(model, "批量删除成功");		
		}else{
			addMessage(model, "未选中供应商");		
		}
		addMessage(model, "批量删除成功");
		Page<PdSupplier> page = pdSupplierService.findPage(new Page<PdSupplier>(request, response), pdSupplier); 
		//更新供应商信息
		SpdUtils.updateSupploerInfo();
		model.addAttribute("page", page);
		return "hys/pd/pdSupplierList";
	}
	
	/**
	 * 供应商三证查询
	 * @param pdSupplier
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "pdSupplierThreeCertificateQuery")
	public String pdSupplierThreeCertificateQuery(PdSupplier pdSupplier, Model model,HttpServletRequest request) {
		model.addAttribute("pdSupplier", pdSupplier);
		pdSupplier = pdSupplierService.get(pdSupplier.getId());
		model.addAttribute("pdSupplier", pdSupplier);
		return "hys/pd/pdSupplierThreeCertificateQuery";
	}
	
	/**
	 * 校验供应商是否存在
	 * @param pdSupplier
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("pd:pdSupplier:edit")
	@RequestMapping(value = "checkSupplierName")
	public String checkSupplierName( Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		//查询供应商名称是否已经存在
		String name = request.getParameter("supplierName");
		String id = request.getParameter("id");
		try {
			PdSupplier pdSupplier = new PdSupplier();
			pdSupplier.setSupplierName(name);
			if(id!=null && !"".equals(id)){
				pdSupplier.setId(id);
			}
			List<PdSupplier>  suppliers = pdSupplierService.verify(pdSupplier);
			if(suppliers!=null && suppliers.size()>0){
				return "false";
			}else{
				return "true";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}

}