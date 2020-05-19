/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.hys.pd.entity.PdPurchaseDetail;
import com.thinkgem.hys.pd.entity.PdPurchaseOrder;
import com.thinkgem.hys.pd.entity.vo.PurchaseOrderVo;
import com.thinkgem.hys.pd.service.PdPurchaseDetailService;
import com.thinkgem.hys.pd.service.PdPurchaseOrderService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 申购单详细Controller
 * @author wg
 * @version 2018-03-06
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdPurchaseDetail")
public class PdPurchaseDetailController extends BaseController {

	@Autowired
	private PdPurchaseDetailService pdPurchaseDetailService;
	@Autowired
	private PdPurchaseOrderService pdPurchaseOrderService;
	
	@ModelAttribute
	public PdPurchaseDetail get(@RequestParam(required=false) String id) {
		PdPurchaseDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdPurchaseDetailService.get(id);
		}
		if (entity == null){
			entity = new PdPurchaseDetail();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:pdPurchaseDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdPurchaseDetail pdPurchaseDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdPurchaseDetail> page = pdPurchaseDetailService.findPage(new Page<PdPurchaseDetail>(request, response), pdPurchaseDetail); 
		model.addAttribute("page", page);
		return "hys/pd/pdPurchaseDetailList";
	}
	/**
	 * 申购管理分类-科室-品名分类
	 * @param purchaseOrderVo
	 * @param flag
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "gatherList")
	@ResponseBody
	public Object gatherList(PurchaseOrderVo purchaseOrderVo,String curTab, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PurchaseOrderVo> page = pdPurchaseDetailService.queryGroupListByDept(new Page<PurchaseOrderVo>(request, response), purchaseOrderVo,curTab); 
		return page;
	}

	@RequiresPermissions("pd:pdPurchaseDetail:view")
	@RequestMapping(value = "form")
	public String form(PdPurchaseDetail pdPurchaseDetail, Model model) {
		if(StringUtils.isNotEmpty(pdPurchaseDetail.getOrderNo())){
			PdPurchaseOrder ppo = pdPurchaseOrderService.get(pdPurchaseDetail.getOrderNo());
			model.addAttribute("pdPurchaseOrder", ppo);
		}
		//采购单详细
		List<PdPurchaseDetail> detailList = pdPurchaseDetailService.findList(pdPurchaseDetail);
		model.addAttribute("detailList", detailList);
		model.addAttribute("exportDataList", JsonMapper.toJsonString(detailList));
		return "hys/pd/pdPurchaseDetailForm";
	}

	@RequiresPermissions("pd:pdPurchaseDetail:edit")
	@RequestMapping(value = "save")
	public String save(PdPurchaseDetail pdPurchaseDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pdPurchaseDetail)){
			return form(pdPurchaseDetail, model);
		}
		pdPurchaseDetailService.save(pdPurchaseDetail);
		addMessage(redirectAttributes, "保存申购单详细成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdPurchaseDetail/?repage";
	}
	
	@RequiresPermissions("pd:pdPurchaseDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(PdPurchaseDetail pdPurchaseDetail, RedirectAttributes redirectAttributes) {
		pdPurchaseDetailService.delete(pdPurchaseDetail);
		addMessage(redirectAttributes, "删除申购单详细成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdPurchaseDetail/?repage";
	}

	/**
	 * 查询订单中产品列表
	 */
	@RequestMapping(value = "queryProducts")
	@ResponseBody
	public String queryProducts(String orderNo, HttpServletRequest request,HttpServletResponse response){
		JSONObject json = new JSONObject();
		String msg = "成功";
		String code = "0";
		Object result = null;
		PdPurchaseDetail pdPurchaseDetail = new PdPurchaseDetail();
		if(StringUtils.isNotBlank(orderNo)){
			pdPurchaseDetail.setOrderNo(orderNo);
			List<PdPurchaseDetail> list = pdPurchaseDetailService.findList(pdPurchaseDetail);
			if(list != null && list.size()>0){
				JSONArray jsons = new JSONArray();
				JSONObject jsonTmp = null;
				for (PdPurchaseDetail pdPurchaseDetail2 : list) {
					jsonTmp = new JSONObject();
					jsonTmp.put("orderNo", orderNo);
					jsonTmp.put("productId", pdPurchaseDetail2.getProdId());
					jsonTmp.put("productName", pdPurchaseDetail2.getProdName());
					jsonTmp.put("productCode", pdPurchaseDetail2.getProdNo());
					jsonTmp.put("productUnit", pdPurchaseDetail2.getProdUnit());
					jsonTmp.put("productSpec", pdPurchaseDetail2.getProdSpec());
					jsonTmp.put("productVersion", pdPurchaseDetail2.getProdVersion());
					jsonTmp.put("productPrice", pdPurchaseDetail2.getProdPrice());
					jsonTmp.put("applyCount", pdPurchaseDetail2.getApplyCount());
					
					jsons.add(jsonTmp);
				}
				result = jsons;
			}
			
		}else{
			code = "1";
			msg = "参数不完整";
		}
		
		json = new JSONObject();
		json.put("code", code);
		json.put("msg", msg);
		json.put("result", result);
		return json.toString();	
	}
	
	
}