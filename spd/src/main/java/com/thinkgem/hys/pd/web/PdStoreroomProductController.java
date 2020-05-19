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
import com.thinkgem.hys.pd.entity.PdStoreroomProduct;
import com.thinkgem.hys.pd.service.PdStoreroomProductService;

/**
 * 库房产品信息Controller
 * @author changjundong
 * @version 2018-03-07
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdStoreroomProduct")
public class PdStoreroomProductController extends BaseController {

	@Autowired
	private PdStoreroomProductService pdStoreroomProductService;
	
	@ModelAttribute
	public PdStoreroomProduct get(@RequestParam(required=false) String id) {
		PdStoreroomProduct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdStoreroomProductService.get(id);
		}
		if (entity == null){
			entity = new PdStoreroomProduct();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:pdStoreroomProduct:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdStoreroomProduct pdStoreroomProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdStoreroomProduct> page = pdStoreroomProductService.findPage(new Page<PdStoreroomProduct>(request, response), pdStoreroomProduct); 
		model.addAttribute("page", page);
		return "hys/pd/pdStoreroomProductList";
	}

	@RequiresPermissions("pd:pdStoreroomProduct:view")
	@RequestMapping(value = "form")
	public String form(PdStoreroomProduct pdStoreroomProduct, Model model) {
		model.addAttribute("pdStoreroomProduct", pdStoreroomProduct);
		return "hys/pd/pdStoreroomProductForm";
	}

	@RequiresPermissions("pd:pdStoreroomProduct:edit")
	@RequestMapping(value = "save")
	public String save(PdStoreroomProduct pdStoreroomProduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pdStoreroomProduct)){
			return form(pdStoreroomProduct, model);
		}
		pdStoreroomProductService.save(pdStoreroomProduct);
		addMessage(redirectAttributes, "保存库房产品信息成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdStoreroomProduct/?repage";
	}
	
	@RequiresPermissions("pd:pdStoreroomProduct:edit")
	@RequestMapping(value = "delete")
	public String delete(PdStoreroomProduct pdStoreroomProduct, RedirectAttributes redirectAttributes) {
		pdStoreroomProductService.delete(pdStoreroomProduct);
		addMessage(redirectAttributes, "删除库房产品信息成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdStoreroomProduct/?repage";
	}

}