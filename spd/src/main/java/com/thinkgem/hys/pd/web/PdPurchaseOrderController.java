/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.constants.MinaMsgCode;
import com.thinkgem.hys.pd.constants.RspVo;
import com.thinkgem.hys.pd.entity.PdPurchaseDetail;
import com.thinkgem.hys.pd.entity.PdPurchaseOrder;
import com.thinkgem.hys.pd.service.PdPurchaseDetailService;
import com.thinkgem.hys.pd.service.PdPurchaseOrderMergeService;
import com.thinkgem.hys.pd.service.PdPurchaseOrderService;
import com.thinkgem.hys.utils.CommonUtils;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 申购单Controller
 * @author wg
 * @version 2018-03-06
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdPurchaseOrder")
public class PdPurchaseOrderController extends BaseController {

	@Autowired
	private PdPurchaseOrderService pdPurchaseOrderService;
	@Autowired
	private PdPurchaseDetailService pdPurchaseDetailService;
	@Autowired
	private PdPurchaseOrderMergeService pdPurchaseOrderMergeService;
	
	@ModelAttribute
	public PdPurchaseOrder get(@RequestParam(required=false) String id) {
		PdPurchaseOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdPurchaseOrderService.get(id);
		}
		if (entity == null){
			entity = new PdPurchaseOrder();
		}
		return entity;
	}
	/**
	 * 采购管理列表
	 * @param pdPurchaseOrder
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pd:pdPurchaseOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdPurchaseOrder pdPurchaseOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		/*Page<PdPurchaseOrder> page = pdPurchaseOrderService.findPage(new Page<PdPurchaseOrder>(request, response), pdPurchaseOrder); 
		model.addAttribute("page", page);*/
		return "hys/pd/pdPurchaseOrderList";
	}
	//异步加载申购单
	@RequestMapping(value = "getData")
	@ResponseBody
	public Object getData(PdPurchaseOrder pdPurchaseOrder, HttpServletRequest request, HttpServletResponse response) {
		Page<PdPurchaseOrder> page = pdPurchaseOrderService.findPage(new Page<PdPurchaseOrder>(request, response), pdPurchaseOrder);
		return page;
	}

	/**
	 * 申请采购
	 * @param pdPurchaseOrder
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pd:pdPurchaseOrder:view")
	@RequestMapping(value = "form")
	public String form(PdPurchaseOrder pdPurchaseOrder, Model model) {
		//随机生成采购单
		pdPurchaseOrder.setOrderNo(CommonUtils.generateOrderNoByType(MinaGlobalConstants.ORDER_NO_FIRST_LETTER_SG));
		pdPurchaseOrder.setDeptId(UserUtils.getUser().getStoreroomId());
		pdPurchaseOrder.setDeptName(UserUtils.getUser().getStoreroomName());
		model.addAttribute("pdPurchaseOrder", pdPurchaseOrder);
		model.addAttribute("userInfo", UserUtils.getUser());
		return "hys/pd/pdPurchaseOrderForm";
	}

	/**
	 * 保存申请采购单
	 * @param pdPurchaseOrder
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("pd:pdPurchaseOrder:edit")
	@RequestMapping(value = "save")
	public String save(PdPurchaseOrder pdPurchaseOrder, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pdPurchaseOrder)){
			return form(pdPurchaseOrder, model);
		}
		pdPurchaseOrderService.savePurchaseOrder(pdPurchaseOrder);
		addMessage(redirectAttributes, "保存申购单成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdPurchaseOrder/?repage";
	}
	
	@RequiresPermissions("pd:pdPurchaseOrder:edit")
	@RequestMapping(value = "delete")
	public String delete(PdPurchaseOrder pdPurchaseOrder, RedirectAttributes redirectAttributes) {
		pdPurchaseOrderService.delete(pdPurchaseOrder);
		addMessage(redirectAttributes, "删除申购单成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdPurchaseOrder/?repage";
	}
	
	/**
	 * 采购单审核列表
	 * @param pdPurchaseOrder
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pd:pdPurchaseOrderAudit:view")
	@RequestMapping(value = "auditList")
	public String auditList(PdPurchaseOrder pdPurchaseOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		return "hys/pd/pdPurchaseOrderAuditList";
	}

	/**
	 * 采购单审核详情
	 * @param pdPurchaseOrder
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pd:pdPurchaseOrder:view")
	@RequestMapping(value = "auditForm")
	public String auditForm(PdPurchaseDetail pdPurchaseDetail, String oprt, Model model) {
		if(StringUtils.isNotEmpty(pdPurchaseDetail.getOrderNo())){
			PdPurchaseOrder ppo = pdPurchaseOrderService.get(pdPurchaseDetail.getOrderNo());
			model.addAttribute("pdPurchaseOrder", ppo);
		}
		if("audit".equals(oprt)){
			pdPurchaseDetail.setStoreroomId(UserUtils.getUser().getStoreroomId());
		}
		//采购单详细
		List<PdPurchaseDetail> detailList = pdPurchaseDetailService.findList(pdPurchaseDetail);
		model.addAttribute("detailList", detailList);
		model.addAttribute("oprt", oprt);
		return "hys/pd/pdPurchaseOrderAuditForm";
	}
	/**
	 * 审核采购单
	 * @param pdPurchaseDetail
	 * @param oprt
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "audit")
	@ResponseBody
	public RspVo audit(String orderNos, String orderStatus, String refuseReason, String oprtSource) {
		RspVo vo = new RspVo();
		Map<String,Object> retMap = new HashMap<String,Object>();
		StringBuilder msg = new StringBuilder("");
		try {
			int changeNum = pdPurchaseOrderService.audit(orderNos, orderStatus, refuseReason);
			if(changeNum > 0){
				vo.setInfo(MinaMsgCode.OPERATOR_SUCCESS.getInfo());
				if(MinaGlobalConstants.PURCHASE_ORDER_STATUS_PASSED.equals(orderStatus)){//合并并且提交
					vo.setUri("/pd/pdPurchaseOrderMerge");
					if("0".equals(oprtSource)){//合并提交的请求
						retMap = pdPurchaseOrderMergeService.saveMergeOrder(orderNos);
						if(retMap != null
								&& retMap.get("statusCode") != null && retMap.get("statusCode").equals(MinaGlobalConstants.SYNC_STATE_ERROR)
								&& retMap.get("message") != null){
							msg.append(retMap.get("message"));
						}
					}else if("1".equals(oprtSource)){//一代表批量保存
						String[] strarr = orderNos.split(",");
						for(String str:strarr){
							retMap = pdPurchaseOrderMergeService.saveMergeOrder(str);
							if(retMap != null
									&& retMap.get("statusCode") != null && retMap.get("statusCode").equals(MinaGlobalConstants.SYNC_STATE_ERROR)
									&& retMap.get("message") != null){
								msg.append(retMap.get("message"));
							}
						}
					}
				}else if(MinaGlobalConstants.PURCHASE_ORDER_STATUS_REFUSED.equals(orderStatus)){//拒绝
					vo.setUri("/pd/pdPurchaseOrder/auditList");
				}
				vo.setInfo(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
			}else{
				vo.setCode(MinaMsgCode.SYSTEM_EXCEPTION.getKey());
				vo.setInfo(MinaMsgCode.SYSTEM_EXCEPTION.getInfo());
			}
		} catch (Exception e) {
			vo.setCode(MinaMsgCode.SYSTEM_EXCEPTION.getKey());
			vo.setInfo(MinaMsgCode.SYSTEM_EXCEPTION.getInfo());
		}

		if(msg != null && !msg.toString().equals("")){
			vo.setCode(MinaMsgCode.SYSTEM_EXCEPTION.getKey());
			vo.setInfo(msg.toString());
		}

		return vo;
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
	public String chooseOrderList(PdPurchaseOrder pdPurchaseOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdPurchaseOrder> page = pdPurchaseOrderService.findPage(new Page<PdPurchaseOrder>(request, response), pdPurchaseOrder); 
		model.addAttribute("page", page);
		return "hys/pd/pdAddOrderBox";
	}

	/**
	 * 通过合并后订单号，查询订单中产品列表
	 */
	@RequestMapping(value = "queryProducts")
	@ResponseBody
	public String queryProducts(String orderNo, HttpServletRequest request,HttpServletResponse response){
		JSONObject json = new JSONObject();
		PdPurchaseDetail pdPurchaseDetail = new PdPurchaseDetail();
		if(StringUtils.isBlank(orderNo)){
			json.put("code", "201");
			json.put("msg", "参数不完整");
			return json.toString();
		}

		PdPurchaseOrder queryOrder = new PdPurchaseOrder();
		queryOrder.setMergeOrderNo(orderNo);
		List<PdPurchaseOrder> pdPurchaseOrders = pdPurchaseOrderService.findList(queryOrder);
		if(pdPurchaseOrders == null || pdPurchaseOrders.size() == 0){
			json.put("code", "202");
			json.put("msg", "采购单[单号："+orderNo+ "]信息不存在！");
			return json.toString();
		}

		List<String> orderNos = new ArrayList<String>();
		for(PdPurchaseOrder purchaseOrder:pdPurchaseOrders){
			orderNos.add(purchaseOrder.getOrderNo());
		}
		
		pdPurchaseDetail.setOrderNos(orderNos);
		List<PdPurchaseDetail> list = pdPurchaseDetailService.findList(pdPurchaseDetail);
		if(list == null || list.size() == 0){
			json.put("code", "202");
			json.put("msg", "采购单[单号："+orderNos+ "]明细信息不存在！");
			return json.toString();
		}
		list = dealRepeatData(list);
		Object result = null;
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

		json.put("code", "200");
		json.put("result", result);
		return json.toString();	
	}

	//合并相同的订单
	private List<PdPurchaseDetail> dealRepeatData(final List<PdPurchaseDetail> list){
		List<PdPurchaseDetail> tempArray = new ArrayList<>();
		Set<String> pids = new HashSet<String>();
		if(list != null && list.size() > 0){
			for(PdPurchaseDetail temp : list){
				if (temp == null || StringUtils.isEmpty(temp.getProdId()) || StringUtils.isEmpty(temp.getProdNo())
						) {
					continue;
				}
				StringBuilder sb = new StringBuilder();
				sb.append(temp.getProdId()).append(temp.getProdNo());
				if(pids.contains(sb.toString())){
					continue;
				}
				int applyCount = 0;
				for(PdPurchaseDetail tp : list){
					if ( tp != null) {
						if(temp.getProdNo().equals(tp.getProdNo())
								&& temp.getProdId().equals(tp.getProdId())){
							pids.add(sb.toString());
							applyCount += tp.getApplyCount();
						}
					}
				}
				temp.setApplyCount(applyCount);
				tempArray.add(temp);
			}
		}
		return tempArray;
	}
}