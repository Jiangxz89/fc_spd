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
import com.thinkgem.hys.pd.entity.PdAllocationProduct;
import com.thinkgem.hys.pd.service.PdAllocationProductService;

/**
 * 调拨产品中间表Controller
 * @author zhengjinlei
 * @version 2018-03-24
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdAllocationProduct")
public class PdAllocationProductController extends BaseController {

	@Autowired
	private PdAllocationProductService pdAllocationProductService;
	
	@ModelAttribute
	public PdAllocationProduct get(@RequestParam(required=false) String id) {
		PdAllocationProduct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdAllocationProductService.get(id);
		}
		if (entity == null){
			entity = new PdAllocationProduct();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:pdAllocationProduct:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdAllocationProduct pdAllocationProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdAllocationProduct> page = pdAllocationProductService.findPage(new Page<PdAllocationProduct>(request, response), pdAllocationProduct); 
		model.addAttribute("page", page);
		return "hys/pd/pdAllocationProductList";
	}

	@RequiresPermissions("pd:pdAllocationProduct:view")
	@RequestMapping(value = "form")
	public String form(PdAllocationProduct pdAllocationProduct, Model model) {
		model.addAttribute("pdAllocationProduct", pdAllocationProduct);
		return "hys/pd/pdAllocationProductForm";
	}

	@RequiresPermissions("pd:pdAllocationProduct:edit")
	@RequestMapping(value = "save")
	public String save(PdAllocationProduct pdAllocationProduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pdAllocationProduct)){
			return form(pdAllocationProduct, model);
		}
		pdAllocationProductService.save(pdAllocationProduct);
		addMessage(redirectAttributes, "保存调拨产品中间表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdAllocationProduct/?repage";
	}
	
	@RequiresPermissions("pd:pdAllocationProduct:edit")
	@RequestMapping(value = "delete")
	public String delete(PdAllocationProduct pdAllocationProduct, RedirectAttributes redirectAttributes) {
		pdAllocationProductService.delete(pdAllocationProduct);
		addMessage(redirectAttributes, "删除调拨产品中间表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdAllocationProduct/?repage";
	}

}