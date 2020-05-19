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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.constants.MinaMsgCode;
import com.thinkgem.hys.pd.constants.RspVo;
import com.thinkgem.hys.pd.entity.PdApplyOrder;
import com.thinkgem.hys.pd.service.PdApplyOrderService;
import com.thinkgem.hys.utils.CommonUtils;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 申领单Controller
 * @author wg
 * @version 2018-03-06
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdApplyOrder")
public class PdApplyOrderController extends BaseController {

	@Autowired
	private PdApplyOrderService pdApplyOrderService;
	
	@ModelAttribute
	public PdApplyOrder get(@RequestParam(required=false) String id) {
		PdApplyOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdApplyOrderService.get(id);
		}
		if (entity == null){
			entity = new PdApplyOrder();
		}
		return entity;
	}
	/**
	 * 领用管理列表
	 * @param pdApplyOrder
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pd:pdApplyOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdApplyOrder pdApplyOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		//二级库房只查看本库房的
		if(MinaGlobalConstants.STOREROOM_TYPE_1.equals(user.getStoreroomType())){
			pdApplyOrder.setDeptId(user.getStoreroomId());
		}
		Page<PdApplyOrder> page = pdApplyOrderService.findPage(new Page<PdApplyOrder>(request, response), pdApplyOrder); 
		model.addAttribute("page", page);
		return "hys/pd/pdApplyOrderList";
	}

	/**
	 * 申请领用
	 * @param pdApplyOrder
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pd:pdApplyOrder:view")
	@RequestMapping(value = "form")
	public String form(PdApplyOrder pdApplyOrder, Model model) {
		pdApplyOrder.setApplyNo(CommonUtils.generateOrderNoByType(MinaGlobalConstants.ORDER_NO_FIRST_LETTER_SL));
		pdApplyOrder.setApplyDate(DateUtils.getNowDate());
		model.addAttribute("pdApplyOrder", pdApplyOrder);
		return "hys/pd/pdApplyOrderForm";
	}

	@RequiresPermissions("pd:pdApplyOrder:edit")
	@RequestMapping(value = "save")
	public String save(PdApplyOrder pdApplyOrder, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pdApplyOrder)){
			return form(pdApplyOrder, model);
		}
		pdApplyOrderService.saveApplyOrder(pdApplyOrder);
		addMessage(redirectAttributes, "保存申领单成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdApplyOrder/?repage&pageNo=1";
	}
	
	@RequiresPermissions("pd:pdApplyOrder:edit")
	@RequestMapping(value = "delete")
	public String delete(PdApplyOrder pdApplyOrder, RedirectAttributes redirectAttributes) {
		pdApplyOrderService.delete(pdApplyOrder);
		addMessage(redirectAttributes, "删除申领单成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdApplyOrder/?repage";
	}

	/**
	 * 领用审核列表
	 * @param pdApplyOrder
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pd:pdApplyOrder:view")
	@RequestMapping(value = "auditList")
	public String auditList(PdApplyOrder pdApplyOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		pdApplyOrder.setApplyStatus(MinaGlobalConstants.APPLY_ORDER_STATUS_SENDED);
		Page<PdApplyOrder> page = pdApplyOrderService.findPage(new Page<PdApplyOrder>(request, response), pdApplyOrder); 
		model.addAttribute("page", page);
		return "hys/pd/pdApplyOrderAuditList";
	}
	
	/**
	 * 领用审核详情
	 * @param pdApplyOrder
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pd:pdApplyOrder:view")
	@RequestMapping(value = "auditForm")
	public String auditForm(PdApplyOrder pdApplyOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("pdApplyOrder", pdApplyOrder);
		return "hys/pd/pdApplyOrderAuditForm";
	}
	
	/**
	 * 审核申领单
	 * @param pdApplyOrder
	 * @return
	 */
	@RequestMapping(value = "auditApplyOrder", method=RequestMethod.POST)
	@ResponseBody
	public RspVo auditApplyOrder(PdApplyOrder pdApplyOrder){
		RspVo vo = new RspVo();
		try {
			//--开始判断库存够不，然后操作
			
			//结束
			pdApplyOrder.setAuditBy(UserUtils.getUser().getId());
			int flag = pdApplyOrderService.updateStatus(pdApplyOrder);
			if(flag == 0)
				vo.setCode(MinaMsgCode.OPERATOR_ERROR.getKey());
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode(MinaMsgCode.OPERATOR_ERROR.getKey());
		}
		return vo;
	}
	/**
	 * 出库导入申领单
	 * @param pdApplyOrder
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"pdAddApplyOrderBox"})
	public String pdAddApplyOrderBox(PdApplyOrder pdApplyOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(StringUtils.isNotEmpty(pdApplyOrder.getApplyStatus()) ||
				StringUtils.isNotEmpty(pdApplyOrder.getApplyStatuses())){
			Page<PdApplyOrder> page = pdApplyOrderService.findAppBoxList(new Page<PdApplyOrder>(request, response), pdApplyOrder); 
			model.addAttribute("page", page);			
		}
		model.addAttribute("pdApplyOrder", pdApplyOrder);
		return "hys/pd/pdAddApplyOrderBox";
	}
}