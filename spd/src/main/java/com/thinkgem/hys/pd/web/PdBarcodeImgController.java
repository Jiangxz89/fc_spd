/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.hys.pd.entity.PdBarcodeImg;
import com.thinkgem.hys.pd.service.PdBarcodeImgService;

/**
 * 条形码表Controller
 * @author sutianqi
 * @version 2018-03-28
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdBarcodeImg")
public class PdBarcodeImgController extends BaseController {

	@Autowired
	private PdBarcodeImgService pdBarcodeImgService;
	
	@ModelAttribute
	public PdBarcodeImg get(@RequestParam(required=false) String id) {
		PdBarcodeImg entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdBarcodeImgService.get(id);
		}
		if (entity == null){
			entity = new PdBarcodeImg();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:pdBarcodeImg:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdBarcodeImg pdBarcodeImg, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdBarcodeImg> page = pdBarcodeImgService.findPage(new Page<PdBarcodeImg>(request, response), pdBarcodeImg); 
		model.addAttribute("page", page);
		return "hys/pd/pdBarcodeImgList";
	}

	@RequiresPermissions("pd:pdBarcodeImg:view")
	@RequestMapping(value = "form")
	public String form(PdBarcodeImg pdBarcodeImg, Model model) {
		model.addAttribute("pdBarcodeImg", pdBarcodeImg);
		return "hys/pd/pdBarcodeImgForm";
	}

	@RequiresPermissions("pd:pdBarcodeImg:edit")
	@RequestMapping(value = "save")
	public String save(PdBarcodeImg pdBarcodeImg, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pdBarcodeImg)){
			return form(pdBarcodeImg, model);
		}
		pdBarcodeImgService.save(pdBarcodeImg);
		addMessage(redirectAttributes, "保存条形码表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdBarcodeImg/?repage";
	}
	
	@RequiresPermissions("pd:pdBarcodeImg:edit")
	@RequestMapping(value = "delete")
	public String delete(PdBarcodeImg pdBarcodeImg, RedirectAttributes redirectAttributes) {
		pdBarcodeImgService.delete(pdBarcodeImg);
		addMessage(redirectAttributes, "删除条形码表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdBarcodeImg/?repage";
	}

}