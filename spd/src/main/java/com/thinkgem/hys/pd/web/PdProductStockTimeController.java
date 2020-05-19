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
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.entity.PdProductStockTime;
import com.thinkgem.hys.pd.service.PdProductStockTimeService;

/**
 * 库存时刻表Controller
 * @author wg
 * @version 2018-04-25
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdProductStockTime")
public class PdProductStockTimeController extends BaseController {

	@Autowired
	private PdProductStockTimeService pdProductStockTimeService;
	
	@ModelAttribute
	public PdProductStockTime get(@RequestParam(required=false) String id) {
		PdProductStockTime entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdProductStockTimeService.get(id);
		}
		if (entity == null){
			entity = new PdProductStockTime();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:pdProductStockTime:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdProductStockTime pdProductStockTime, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if( MinaGlobalConstants.STOREROOM_TYPE_1.equals(user.getStoreroomType())) {
			pdProductStockTime.setStoreroomId(user.getStoreroomId());
		}
		//--默认当天
		if (null == pdProductStockTime.getStockDate()) {
			pdProductStockTime.setStockDate(DateUtils.getNowDate());
		}
		if (DateUtils.isEqualsFromTwoDate(pdProductStockTime.getStockDate(), DateUtils.getNowDate())) {
			//导出excel需要的数据
			model.addAttribute("exportDataList", JsonMapper.toJsonString(pdProductStockTimeService.findTodayList(pdProductStockTime)));
			Page<PdProductStockTime> page = pdProductStockTimeService.findTodayPage(new Page<PdProductStockTime>(request, response), pdProductStockTime); 
			model.addAttribute("page", page);
		} else {
			//导出excel需要的数据
			model.addAttribute("exportDataList", JsonMapper.toJsonString(pdProductStockTimeService.findList(pdProductStockTime)));
			Page<PdProductStockTime> page = pdProductStockTimeService.findPage(new Page<PdProductStockTime>(request, response), pdProductStockTime); 
			model.addAttribute("page", page);
		}
		model.addAttribute("pdProductStockTime", pdProductStockTime);
		return "hys/pd/query/stockTimeQuery";
		//return "hys/pd/pdProductStockTimeList";
	}

	@RequiresPermissions("pd:pdProductStockTime:view")
	@RequestMapping(value = "form")
	public String form(PdProductStockTime pdProductStockTime, Model model) {
		model.addAttribute("pdProductStockTime", pdProductStockTime);
		return "hys/pd/pdProductStockTimeForm";
	}

	@RequiresPermissions("pd:pdProductStockTime:edit")
	@RequestMapping(value = "save")
	public String save(PdProductStockTime pdProductStockTime, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pdProductStockTime)){
			return form(pdProductStockTime, model);
		}
		pdProductStockTimeService.save(pdProductStockTime);
		addMessage(redirectAttributes, "保存库存时刻表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdProductStockTime/?repage";
	}
	
	@RequiresPermissions("pd:pdProductStockTime:edit")
	@RequestMapping(value = "delete")
	public String delete(PdProductStockTime pdProductStockTime, RedirectAttributes redirectAttributes) {
		pdProductStockTimeService.delete(pdProductStockTime);
		addMessage(redirectAttributes, "删除库存时刻表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdProductStockTime/?repage";
	}

}