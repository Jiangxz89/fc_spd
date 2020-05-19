/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web.fb;

import com.thinkgem.hys.pd.entity.BaseMedprice;
import com.thinkgem.hys.pd.service.BaseMedpriceService;
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
 * 本院药品目录Controller
 * @author zxh
 * @version 2019-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/baseMedprice")
public class BaseMedpriceController extends BaseController {

	@Autowired
	private BaseMedpriceService baseMedpriceService;
	
	@ModelAttribute
	public BaseMedprice get(@RequestParam(required=false) String id) {
		BaseMedprice entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = baseMedpriceService.get(id);
		}
		if (entity == null){
			entity = new BaseMedprice();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:baseMedprice:view")
	@RequestMapping(value = {"list", ""})
	public String list(BaseMedprice baseMedprice, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BaseMedprice> page = baseMedpriceService.findPage(new Page<BaseMedprice>(request, response), baseMedprice); 
		model.addAttribute("page", page);
		return "hys/pd/query/fb/baseMedpriceList";
	}

	@RequiresPermissions("pd:baseMedprice:view")
	@RequestMapping(value = "form")
	public String form(BaseMedprice baseMedprice, Model model) {
		model.addAttribute("baseMedprice", baseMedprice);
		return "hys/pd/baseMedpriceForm";
	}

	@RequiresPermissions("pd:baseMedprice:edit")
	@RequestMapping(value = "save")
	public String save(BaseMedprice baseMedprice, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, baseMedprice)){
			return form(baseMedprice, model);
		}
		baseMedpriceService.save(baseMedprice);
		addMessage(redirectAttributes, "保存本院药品目录成功");
		return "redirect:"+Global.getAdminPath()+"/pd/baseMedprice/?repage";
	}
	
	@RequiresPermissions("pd:baseMedprice:edit")
	@RequestMapping(value = "delete")
	public String delete(BaseMedprice baseMedprice, RedirectAttributes redirectAttributes) {
		baseMedpriceService.delete(baseMedprice);
		addMessage(redirectAttributes, "删除本院药品目录成功");
		return "redirect:"+Global.getAdminPath()+"/pd/baseMedprice/?repage";
	}

}