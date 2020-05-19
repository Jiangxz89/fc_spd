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
import com.thinkgem.hys.pd.entity.PdStockRecordProduct;
import com.thinkgem.hys.pd.service.PdStockRecordProductService;

/**
 * 出入库记录关联产品记录Controller
 * @author changjundong
 * @version 2018-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdStockRecordProduct")
public class PdStockRecordProductController extends BaseController {

	@Autowired
	private PdStockRecordProductService pdStockRecordProductService;
	
	@ModelAttribute
	public PdStockRecordProduct get(@RequestParam(required=false) String id) {
		PdStockRecordProduct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdStockRecordProductService.get(id);
		}
		if (entity == null){
			entity = new PdStockRecordProduct();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:pdStockRecordProduct:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdStockRecordProduct pdStockRecordProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdStockRecordProduct> page = pdStockRecordProductService.findPage(new Page<PdStockRecordProduct>(request, response), pdStockRecordProduct); 
		model.addAttribute("page", page);
		return "hys/pd/pdStockRecordProductList";
	}

	@RequiresPermissions("pd:pdStockRecordProduct:view")
	@RequestMapping(value = "form")
	public String form(PdStockRecordProduct pdStockRecordProduct, Model model) {
		model.addAttribute("pdStockRecordProduct", pdStockRecordProduct);
		return "hys/pd/pdStockRecordProductForm";
	}

	@RequiresPermissions("pd:pdStockRecordProduct:edit")
	@RequestMapping(value = "save")
	public String save(PdStockRecordProduct pdStockRecordProduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pdStockRecordProduct)){
			return form(pdStockRecordProduct, model);
		}
		pdStockRecordProductService.save(pdStockRecordProduct);
		addMessage(redirectAttributes, "保存出入库记录关联产品记录成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdStockRecordProduct/?repage";
	}
	
	@RequiresPermissions("pd:pdStockRecordProduct:edit")
	@RequestMapping(value = "delete")
	public String delete(PdStockRecordProduct pdStockRecordProduct, RedirectAttributes redirectAttributes) {
		pdStockRecordProductService.delete(pdStockRecordProduct);
		addMessage(redirectAttributes, "删除出入库记录关联产品记录成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdStockRecordProduct/?repage";
	}

}