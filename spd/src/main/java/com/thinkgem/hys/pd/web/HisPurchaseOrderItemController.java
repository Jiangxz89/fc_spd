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
import com.thinkgem.hys.pd.entity.HisPurchaseOrderItem;
import com.thinkgem.hys.pd.service.HisPurchaseOrderItemService;

/**
 * his采购订单详情Controller
 * @author jiangxz
 * @version 2019-05-17
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/hisPurchaseOrderItem")
public class HisPurchaseOrderItemController extends BaseController {

	@Autowired
	private HisPurchaseOrderItemService hisPurchaseOrderItemService;
	
	@ModelAttribute
	public HisPurchaseOrderItem get(@RequestParam(required=false) String id) {
		HisPurchaseOrderItem entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = hisPurchaseOrderItemService.get(id);
		}
		if (entity == null){
			entity = new HisPurchaseOrderItem();
		}
		return entity;
	}
	
//	@RequiresPermissions("pd:hisPurchaseOrderItem:view")
	@RequestMapping(value = {"list", ""})
	public String list(HisPurchaseOrderItem hisPurchaseOrderItem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<HisPurchaseOrderItem> page = hisPurchaseOrderItemService.findPage(new Page<HisPurchaseOrderItem>(request, response), hisPurchaseOrderItem); 
		model.addAttribute("page", page);
		model.addAttribute("hisPurchaseOrderItem", hisPurchaseOrderItem);
		return "hys/pd/box/hisPurchaseOrderItemBox";
	}

//	@RequiresPermissions("pd:hisPurchaseOrderItem:view")
//	@RequestMapping(value = "form")
//	public String form(HisPurchaseOrderItem hisPurchaseOrderItem, Model model) {
//		model.addAttribute("hisPurchaseOrderItem", hisPurchaseOrderItem);
//		return "hys/pd/hisPurchaseOrderItemForm";
//	}
//
//	@RequiresPermissions("pd:hisPurchaseOrderItem:edit")
//	@RequestMapping(value = "save")
//	public String save(HisPurchaseOrderItem hisPurchaseOrderItem, Model model, RedirectAttributes redirectAttributes) {
//		if (!beanValidator(model, hisPurchaseOrderItem)){
//			return form(hisPurchaseOrderItem, model);
//		}
//		hisPurchaseOrderItemService.save(hisPurchaseOrderItem);
//		addMessage(redirectAttributes, "保存his采购订单详情成功");
//		return "redirect:"+Global.getAdminPath()+"/pd/hisPurchaseOrderItem/?repage";
//	}
//
//	@RequiresPermissions("pd:hisPurchaseOrderItem:edit")
//	@RequestMapping(value = "delete")
//	public String delete(HisPurchaseOrderItem hisPurchaseOrderItem, RedirectAttributes redirectAttributes) {
//		hisPurchaseOrderItemService.delete(hisPurchaseOrderItem);
//		addMessage(redirectAttributes, "删除his采购订单详情成功");
//		return "redirect:"+Global.getAdminPath()+"/pd/hisPurchaseOrderItem/?repage";
//	}

}