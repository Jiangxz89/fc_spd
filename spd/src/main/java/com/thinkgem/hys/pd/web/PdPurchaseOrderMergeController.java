/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web;

import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.entity.PdPurchaseOrder;
import com.thinkgem.hys.pd.entity.PdPurchaseOrderMerge;
import com.thinkgem.hys.pd.service.PdPurchaseOrderMergeService;
import com.thinkgem.hys.pd.service.PdPurchaseOrderService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 合并申购单Controller
 * @author wg
 * @version 2018-03-08
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdPurchaseOrderMerge")
public class PdPurchaseOrderMergeController extends BaseController {

	@Autowired
	private PdPurchaseOrderMergeService pdPurchaseOrderMergeService;
	@Autowired
	private PdPurchaseOrderService pdPurchaseOrderService;
	
	@ModelAttribute
	public PdPurchaseOrderMerge get(@RequestParam(required=false) String id) {
		PdPurchaseOrderMerge entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdPurchaseOrderMergeService.get(id);
		}
		if (entity == null){
			entity = new PdPurchaseOrderMerge();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:pdPurchaseOrderMerge:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdPurchaseOrderMerge pdPurchaseOrderMerge, HttpServletRequest request, HttpServletResponse response, Model model) {
		final String ifPlatform = Global.getConfig("IF_PLATFORM");//是否有中心平台标识1=没有，0=有
		Page<PdPurchaseOrderMerge> page = pdPurchaseOrderMergeService.findPage(new Page<PdPurchaseOrderMerge>(request, response), pdPurchaseOrderMerge); 
		model.addAttribute("page", page);
		model.addAttribute("ifPlatform", ifPlatform);
		return "hys/pd/pdPurchaseOrderMergeList";
	}

	@RequiresPermissions("pd:pdPurchaseOrderMerge:view")
	@RequestMapping(value = "form")
	public String form(PdPurchaseOrder pdPurchaseOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		/*Page<PdPurchaseOrder> page = pdPurchaseOrderService.findPage(new Page<PdPurchaseOrder>(request,response), pdPurchaseOrder);
		model.addAttribute("page", page);*/
		List<PdPurchaseOrder> orderList = pdPurchaseOrderService.findList(pdPurchaseOrder);
		model.addAttribute("orderList", orderList);
		return "hys/pd/pdPurchaseOrderMergeForm";
	}

	@RequiresPermissions("pd:pdPurchaseOrderMerge:edit")
	@RequestMapping(value = "save")
	public String save(PdPurchaseOrderMerge pdPurchaseOrderMerge, Model model, RedirectAttributes redirectAttributes) {
		/*if (!beanValidator(model, pdPurchaseOrderMerge)){
			return form(pdPurchaseOrderMerge, model);
		}*/
		pdPurchaseOrderMergeService.save(pdPurchaseOrderMerge);
		addMessage(redirectAttributes, "保存合并申购单成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdPurchaseOrderMerge/?repage";
	}
	
	@RequiresPermissions("pd:pdPurchaseOrderMerge:edit")
	@RequestMapping(value = "delete")
	public String delete(PdPurchaseOrderMerge pdPurchaseOrderMerge, RedirectAttributes redirectAttributes) {
		pdPurchaseOrderMergeService.delete(pdPurchaseOrderMerge);
		addMessage(redirectAttributes, "删除合并申购单成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdPurchaseOrderMerge/?repage";
	}

	/**
	 * 入库申请-订单选择
	 * @param pdProduct
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "chooseOrderList")
	public String chooseOrderList(PdPurchaseOrderMerge pdPurchaseOrderMerge, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		Page<PdPurchaseOrderMerge> page = pdPurchaseOrderMergeService.findChooseOrderList(new Page<PdPurchaseOrderMerge>(request, response), pdPurchaseOrderMerge);
		model.addAttribute("page", page);
		
		return "hys/pd/box/pdAddOrderMergeBox";
	}
	
	/**
	 * 采购单上传中心平台
	 * @param pdPurchaseOrderMerge
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "uploadPlatform")
	public String uploadPlatform(PdPurchaseOrder pdPurchaseOrder, RedirectAttributes redirectAttributes,HttpServletRequest request, Model model) {
		Map<String,Object> retMap = pdPurchaseOrderMergeService.uploadPlatform(pdPurchaseOrder);
		if(MinaGlobalConstants.PURCHASE_ORDER_SUPPLIER_STATUS_1.equals(retMap.get("supplierStatus"))){
			addMessage(redirectAttributes, "上传成功");
		}else{
			addMessage(redirectAttributes, "上传失败"+retMap.get("message"));
		}
		return "redirect:"+Global.getAdminPath()+"/pd/pdPurchaseOrderMerge/?repage";
	}
	
}