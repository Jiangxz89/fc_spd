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
import com.thinkgem.hys.pd.entity.HisPurchaseOrder;
import com.thinkgem.hys.pd.service.HisPurchaseOrderService;

/**
 * his采购订单Controller
 * @author jiangxz
 * @version 2019-05-17
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/hisPurchaseOrder")
public class HisPurchaseOrderController extends BaseController {

	@Autowired
	private HisPurchaseOrderService hisPurchaseOrderService;
	
	@ModelAttribute
	public HisPurchaseOrder get(@RequestParam(required=false) String id) {
		HisPurchaseOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = hisPurchaseOrderService.get(id);
		}
		if (entity == null){
			entity = new HisPurchaseOrder();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:hisPurchaseOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(HisPurchaseOrder hisPurchaseOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<HisPurchaseOrder> page = new Page<HisPurchaseOrder>(request, response);
		page.setOrderBy("create_date");
		Page<HisPurchaseOrder> list = hisPurchaseOrderService.findPage(page, hisPurchaseOrder);
		model.addAttribute("page", list);
		return "hys/pd/hisPurchaseOrderList";
	}

//	@RequiresPermissions("pd:hisPurchaseOrder:view")
//	@RequestMapping(value = "form")
//	public String form(HisPurchaseOrder hisPurchaseOrder, Model model) {
//		model.addAttribute("hisPurchaseOrder", hisPurchaseOrder);
//		return "hys/pd/hisPurchaseOrderForm";
//	}
//
//	@RequiresPermissions("pd:hisPurchaseOrder:edit")
//	@RequestMapping(value = "save")
//	public String save(HisPurchaseOrder hisPurchaseOrder, Model model, RedirectAttributes redirectAttributes) {
//		if (!beanValidator(model, hisPurchaseOrder)){
//			return form(hisPurchaseOrder, model);
//		}
//		hisPurchaseOrderService.save(hisPurchaseOrder);
//		addMessage(redirectAttributes, "保存his采购订单成功");
//		return "redirect:"+Global.getAdminPath()+"/pd/hisPurchaseOrder/?repage";
//	}
//
//	@RequiresPermissions("pd:hisPurchaseOrder:edit")
//	@RequestMapping(value = "delete")
//	public String delete(HisPurchaseOrder hisPurchaseOrder, RedirectAttributes redirectAttributes) {
//		hisPurchaseOrderService.delete(hisPurchaseOrder);
//		addMessage(redirectAttributes, "删除his采购订单成功");
//		return "redirect:"+Global.getAdminPath()+"/pd/hisPurchaseOrder/?repage";
//	}

}