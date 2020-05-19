/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.entity.PdProductStockTotal;
import com.thinkgem.hys.pd.entity.PdStoreroom;
import com.thinkgem.hys.pd.entity.PdStoreroomAdmin;
import com.thinkgem.hys.pd.entity.PdStoreroomProduct;
import com.thinkgem.hys.pd.service.PdProductStockTotalService;
import com.thinkgem.hys.pd.service.PdStoreroomAdminService;
import com.thinkgem.hys.pd.service.PdStoreroomProductService;
import com.thinkgem.hys.pd.service.PdStoreroomService;
import com.thinkgem.hys.utils.OrderNoUtils;
import com.thinkgem.hys.utils.SpdUtils;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 库房信息Controller
 * @author changjundong
 * @version 2018-03-07
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdStoreroom")
public class PdStoreroomController extends BaseController {

	@Autowired
	private PdStoreroomService pdStoreroomService;
	@Autowired
	private PdProductStockTotalService pdProductStockTotalService;

	@Autowired
	private PdStoreroomAdminService pdStoreroomAdminService;
	
	@Autowired
	private PdStoreroomProductService pdStoreroomProductService;
	
	@ModelAttribute
	public PdStoreroom get(@RequestParam(required=false) String id) {
		PdStoreroom entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdStoreroomService.get(id);
		}
		if (entity == null){
			entity = new PdStoreroom();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:pdStoreroom:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdStoreroom pdStoreroom, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdStoreroom> page = pdStoreroomService.findPage(new Page<PdStoreroom>(request, response), pdStoreroom); 
		model.addAttribute("page", page);
		return "hys/pd/pdStoreroomList";
	}

	@RequiresPermissions("pd:pdStoreroom:view")
	@RequestMapping(value = "form")
	public String form(PdStoreroom pdStoreroom, Model model) {
		if(pdStoreroom != null && StringUtils.isNotBlank(pdStoreroom.getId())){
			//获取用户
			PdStoreroomAdmin pdStoreroomAdmin = new PdStoreroomAdmin();
			pdStoreroomAdmin.setStoreroomId(pdStoreroom.getId());
			List<PdStoreroomAdmin> pdStoreroomAdminList = pdStoreroomAdminService.findAdminList(pdStoreroomAdmin);
			pdStoreroom.setAdminList(pdStoreroomAdminList);
			//获取产品
			PdStoreroomProduct pdStoreroomProduct = new PdStoreroomProduct();
			pdStoreroomProduct.setStoreroomId(pdStoreroom.getId());
			List<PdStoreroomProduct> proList = pdStoreroomProductService.findList(pdStoreroomProduct);
			pdStoreroom.setProductList(proList);
		}
		if(StringUtils.isBlank(pdStoreroom.getStoreroomCode())){
			pdStoreroom.setStoreroomCode(OrderNoUtils.getWarehouseNo());
			pdStoreroom.setDateRemind(3);
		}
		model.addAttribute("pdStoreroom", pdStoreroom);
		return "hys/pd/pdStoreroomForm";
	}
	

	@RequiresPermissions("pd:pdStoreroom:edit")
	@RequestMapping(value = "save")
	public String save(PdStoreroom pdStoreroom, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pdStoreroom)){
			return form(pdStoreroom, model);
		}
		//判断是否是一级库房
		if(StringUtils.isEmpty(pdStoreroom.getId()) && MinaGlobalConstants.STOREROOM_TYPE_0.equals(pdStoreroom.getStoreroomType())){
			String sid = pdStoreroomService.findFirstStoreroom();
			if (sid != null && sid.trim().length() > 0){
				addMessage(redirectAttributes, "一级库房已存在，无法创建！");
				return "redirect:"+Global.getAdminPath()+"/pd/pdStoreroom/?repage";
			}
		}
		if(pdStoreroom.getDateRemind() == null){
			pdStoreroom.setDateRemind(0);
		}
		if(pdStoreroom.getLongtimeRemind() == null){
			pdStoreroom.setLongtimeRemind(0);
		}
		pdStoreroomService.save(pdStoreroom);
		//更新缓存
		SpdUtils.updateStoreroomInfo();
		addMessage(redirectAttributes, "保存库房信息成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdStoreroom/?repage";
	}
	
	@RequiresPermissions("pd:pdStoreroom:edit")
	@RequestMapping(value = "delete")
	public String delete(PdStoreroom pdStoreroom, RedirectAttributes redirectAttributes) {
		PdProductStockTotal pst = new PdProductStockTotal();
		pst.setStoreroomId(pdStoreroom.getId());
		List<PdProductStockTotal> pstlist = pdProductStockTotalService.findList(pst);
		if (pstlist != null && pstlist.size() > 0){
			addMessage(redirectAttributes, "该库房已产生业务数据，无法删除！");
			return "redirect:"+Global.getAdminPath()+"/pd/pdStoreroom/?repage";
		}
		pdStoreroomService.delete(pdStoreroom);
		//更新缓存
		SpdUtils.updateStoreroomInfo();
		addMessage(redirectAttributes, "删除库房信息成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdStoreroom/?repage";
	}
	
	/**
	 * 验证用户是否存在库管信息
	 * @param oldLoginName
	 * @param loginName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkIsAdmin")
	public String checkLoginName(String userId) {
		if(StringUtils.isEmpty(userId)){
			return "true";
		}
		PdStoreroomAdmin pdStoreroomAdmin = new PdStoreroomAdmin();
		pdStoreroomAdmin.setAdminId(userId);
		List<PdStoreroomAdmin> pdStoreroomAdminList = pdStoreroomAdminService.findList(pdStoreroomAdmin);
		if(pdStoreroomAdminList.size() > 0){
			return "true";
		}
		
		return "false";
	}
}