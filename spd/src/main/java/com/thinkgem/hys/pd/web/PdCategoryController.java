/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.hys.pd.web.task.SyncProductDataTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.hys.pd.entity.PdCategory;
import com.thinkgem.hys.pd.entity.PdProduct;
import com.thinkgem.hys.pd.service.PdCategoryService;
import com.thinkgem.hys.pd.service.PdProductService;

/**
 * 类别表Controller
 * @author sutianqi
 * @version 2018-03-07
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdCategory")
public class PdCategoryController extends BaseController {

	@Autowired
	private PdCategoryService pdCategoryService;
	@Autowired
	private PdProductService pdProductService;
	@Autowired
	private SyncProductDataTask syncProductDataTask;
	
	@ModelAttribute
	public PdCategory get(@RequestParam(required=false) String id) {
		PdCategory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdCategoryService.get(id);
		}
		if (entity == null){
			entity = new PdCategory();
		}
		return entity;
	}
	
	//@RequiresPermissions("pd:pdCategory:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdCategory pdCategory, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<PdCategory> list = pdCategoryService.findList(pdCategory);
		final String ifPlatform = Global.getConfig("IF_PLATFORM");//是否有中心平台标识1=没有，0=有
		String delKey = request.getParameter("delKey") == null ? "" : request.getParameter("delKey");
		
		request.setAttribute("delKey", delKey);
		model.addAttribute("ifPlatform", ifPlatform);
		model.addAttribute("list", list);
		return "hys/pd/pdCategoryList";
	}

	//@RequiresPermissions("pd:pdCategory:view")
	@RequestMapping(value = "form")
	public String form(PdCategory pdCategory, Model model) {
		model.addAttribute("pdCategory", pdCategory);
		return "hys/pd/pdCategoryForm";
	}

	//@RequiresPermissions("pd:pdCategory:edit")
	@RequestMapping(value = "save")
	public String save(PdCategory pdCategory, Model model, RedirectAttributes redirectAttributes , HttpServletRequest request) {
		if (!beanValidator(model, pdCategory)){
			return form(pdCategory, model);
		}
		//校验分类是否重复
		List<PdCategory>  pdCategorys = pdCategoryService.verify(pdCategory);
		if(pdCategorys!=null && pdCategorys.size()>0){
			addMessage(redirectAttributes, "保存分类失败,分类已存在");
		}else{
			pdCategoryService.save(pdCategory);
			addMessage(redirectAttributes, "保存分类成功");
		}
		return "redirect:"+Global.getAdminPath()+"/pd/pdCategory/?list";
	}
	
	/**
	 * ajax的保存
	 * */
	@RequestMapping(value = "saveAjax")
	public void saveAjax(PdCategory pdCategory, Model model, RedirectAttributes redirectAttributes , HttpServletRequest request, HttpServletResponse response) {
		
		String name = request.getParameter("name") != null ? request.getParameter("name") : "";
		if(!name.equals("")){
			pdCategory.setName(name);
		}
		String type = request.getParameter("type");
		pdCategory.setType(type);
		//校验分类是否重复
		List<PdCategory>  pdCategorys = pdCategoryService.verify(pdCategory);
		Map<String,Object> map = new HashMap<String,Object>();
		if(pdCategorys!=null && pdCategorys.size()>0){
			map.put("statusCode", "500");
			map.put("msg", "保存失败分类名称不能重复");
		}else{
			pdCategoryService.save(pdCategory);
			map.put("statusCode", "200");
			map.put("id", pdCategory.getId());
			map.put("name", pdCategory.getName());
			map.put("msg", "保存成功");
		}
		try {
			response.getWriter().write(JSONObject.toJSONString(map));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//@RequiresPermissions("pd:pdCategory:edit")
	@RequestMapping(value = "delete")
	public String delete(RedirectAttributes redirectAttributes, HttpServletRequest request) {
		PdCategory pdCategory = new PdCategory();
		String id = request.getParameter("id");
		pdCategory.setId(id);
		
		PdProduct prod = new PdProduct();
		prod.setCategoryId(id);
		List<PdProduct> findList = pdProductService.findList(prod);
		
		if(findList.size() != 0){//判断是否有产品关联该分类
			return "redirect:"+Global.getAdminPath()+"/pd/pdCategory/?delKey=error";
		}else if(findList.size() == 0){
			pdCategoryService.delete(pdCategory);
		}
		
		addMessage(redirectAttributes, "删除类别表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdCategory/";
	}
	
	@RequestMapping(value = "update")
	public String update(RedirectAttributes redirectAttributes, HttpServletRequest request){
		PdCategory pdCategory = new PdCategory();
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		String name = request.getParameter("name");
		pdCategory.setId(id);
		pdCategory.setType(type);
		pdCategory.setName(name);
		pdCategory.setUpdateDate(new Date());
		//校验分类是否重复
		List<PdCategory>  pdCategorys = pdCategoryService.verify(pdCategory);
		if(pdCategorys!=null && pdCategorys.size()>0){
			addMessage(redirectAttributes, "修改分类失败,分类已存在");
		}else{
			pdCategoryService.update(pdCategory);
			addMessage(redirectAttributes, "修改类别表成功");
		}
		return "redirect:"+Global.getAdminPath()+"/pd/pdCategory/?repage";
	}

	@RequestMapping(value = "synPdCategory")
	@ResponseBody
	public JSONObject synPdCategory(Model model,HttpServletRequest request, HttpServletResponse response) {
		return syncProductDataTask.synPdCategoryToCentralPlatform();
	}
}