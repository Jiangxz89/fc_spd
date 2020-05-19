/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.hys.pd.entity.PdCategory;
import com.thinkgem.hys.pd.entity.PdProduct;
import com.thinkgem.hys.pd.service.PdProductService;
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
import com.thinkgem.hys.pd.entity.PdProductCoding;
import com.thinkgem.hys.pd.service.PdProductCodingService;

import java.util.List;

/**
 * 产品赋码表Controller
 * @author zxh
 * @version 2019-05-16
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdProductCoding")
public class PdProductCodingController extends BaseController {

	@Autowired
	private PdProductCodingService pdProductCodingService;

	@Autowired
    private PdProductService pdProductService;
	
	@ModelAttribute
	public PdProductCoding get(@RequestParam(required=false) String id) {
		PdProductCoding entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdProductCodingService.get(id);
		}
		if (entity == null){
			entity = new PdProductCoding();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:pdProductCoding:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdProductCoding pdProductCoding, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdProductCoding> page = pdProductCodingService.findPage(new Page<PdProductCoding>(request, response), pdProductCoding); 
		model.addAttribute("page", page);
		return "hys/pd/pdProductCodingListOther";
	}

    @RequiresPermissions("pd:pdProduct:view")
    @RequestMapping(value = "productList")
    public String productList(PdProductCoding pdProductCoding, HttpServletRequest request, HttpServletResponse response, Model model) {
        PdProduct pdProduct = new PdProduct();
        pdProduct.setNumber(pdProductCoding.getNumber());
        pdProduct.setName(pdProductCoding.getProductName());
        Page<PdProduct> page = pdProductService.basicFindPage(new Page<PdProduct>(request, response), pdProduct);
        model.addAttribute("page", page);
        model.addAttribute("pdProduct", pdProduct);
        return "hys/pd/pdProductCodingList";
    }

	@RequiresPermissions("pd:pdProductCoding:view")
	@RequestMapping(value = "form")
	public String form(PdProductCoding pdProductCoding, Model model) {
		model.addAttribute("pdProductCoding", pdProductCoding);
		return "hys/pd/pdProductCodingForm";
	}

	@RequiresPermissions("pd:pdProductCoding:edit")
	@RequestMapping(value = "save")
	public String save(PdProductCoding pdProductCoding, Model model, RedirectAttributes redirectAttributes) {
		pdProductCodingService.save(pdProductCoding);
		addMessage(redirectAttributes, "保存产品赋码表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdProductCoding/productList?repage";
	}
	
	@RequiresPermissions("pd:pdProductCoding:edit")
	@RequestMapping(value = "delete")
	public String delete(PdProductCoding pdProductCoding, RedirectAttributes redirectAttributes) {
		pdProductCodingService.delete(pdProductCoding);
		addMessage(redirectAttributes, "删除产品赋码表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdProductCoding/?repage";
	}

    /**
     * 批量删除
     * @param pdProductCoding
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("pd:pdProductCoding:edit")
    @RequestMapping(value = "deleteAll")
    public String deleteAll(PdProductCoding pdProductCoding, RedirectAttributes redirectAttributes,HttpServletRequest request) {
        String ids = request.getParameter("ids");
        String[] id = ids.substring(1).split(",");
		pdProductCodingService.deleteAll(id);
		addMessage(redirectAttributes, "取消产品赋码成功");
        return "redirect:"+Global.getAdminPath()+"/pd/pdProductCoding";
    }
}