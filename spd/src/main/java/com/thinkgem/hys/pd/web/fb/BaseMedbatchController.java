/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web.fb;

import com.thinkgem.hys.pd.entity.BaseMedbatch;
import com.thinkgem.hys.pd.service.BaseMedbatchService;
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

/**
 * 药品库存明细Controller
 * @author zxh
 * @version 2019-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/baseMedbatch")
public class BaseMedbatchController extends BaseController {

	@Autowired
	private BaseMedbatchService baseMedbatchService;
	
	@ModelAttribute
	public BaseMedbatch get(@RequestParam(required=false) String id) {
		BaseMedbatch entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = baseMedbatchService.get(id);
		}
		if (entity == null){
			entity = new BaseMedbatch();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:baseMedbatch:view")
	@RequestMapping(value = {"list", ""})
	public String list(BaseMedbatch baseMedbatch, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BaseMedbatch> page = baseMedbatchService.findPage(new Page<BaseMedbatch>(request, response), baseMedbatch); 
		model.addAttribute("page", page);
		return "hys/pd/query/fb/baseMedbatchList";
	}

	@RequiresPermissions("pd:baseMedbatch:view")
	@RequestMapping(value = "form")
	public String form(BaseMedbatch baseMedbatch, Model model) {
		model.addAttribute("baseMedbatch", baseMedbatch);
		return "hys/pd/baseMedbatchForm";
	}

	@RequiresPermissions("pd:baseMedbatch:edit")
	@RequestMapping(value = "save")
	public String save(BaseMedbatch baseMedbatch, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, baseMedbatch)){
			return form(baseMedbatch, model);
		}
		baseMedbatchService.save(baseMedbatch);
		addMessage(redirectAttributes, "保存药品库存明细成功");
		return "redirect:"+Global.getAdminPath()+"/pd/baseMedbatch/?repage";
	}
	
	@RequiresPermissions("pd:baseMedbatch:edit")
	@RequestMapping(value = "delete")
	public String delete(BaseMedbatch baseMedbatch, RedirectAttributes redirectAttributes) {
		baseMedbatchService.delete(baseMedbatch);
		addMessage(redirectAttributes, "删除药品库存明细成功");
		return "redirect:"+Global.getAdminPath()+"/pd/baseMedbatch/?repage";
	}

}