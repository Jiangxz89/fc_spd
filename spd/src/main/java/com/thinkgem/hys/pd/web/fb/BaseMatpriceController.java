/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web.fb;

import com.thinkgem.hys.pd.entity.BaseMatprice;
import com.thinkgem.hys.pd.service.BaseMatpriceService;
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
 * 本院物资目录Controller
 * @author zxh
 * @version 2019-11-13
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/baseMatprice")
public class BaseMatpriceController extends BaseController {

	@Autowired
	private BaseMatpriceService baseMatpriceService;
	
	@ModelAttribute
	public BaseMatprice get(@RequestParam(required=false) String id) {
		BaseMatprice entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = baseMatpriceService.get(id);
		}
		if (entity == null){
			entity = new BaseMatprice();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:baseMatprice:view")
	@RequestMapping(value = {"list", ""})
	public String list(BaseMatprice baseMatprice, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BaseMatprice> page = baseMatpriceService.findPage(new Page<BaseMatprice>(request, response), baseMatprice); 
		model.addAttribute("page", page);
		return "hys/pd/query/fb/baseMatpriceList";
	}

	@RequiresPermissions("pd:baseMatprice:view")
	@RequestMapping(value = "form")
	public String form(BaseMatprice baseMatprice, Model model) {
		model.addAttribute("baseMatprice", baseMatprice);
		return "hys/pd/baseMatpriceForm";
	}

	@RequiresPermissions("pd:baseMatprice:edit")
	@RequestMapping(value = "save")
	public String save(BaseMatprice baseMatprice, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, baseMatprice)){
			return form(baseMatprice, model);
		}
		baseMatpriceService.save(baseMatprice);
		addMessage(redirectAttributes, "保存本院物资目录成功");
		return "redirect:"+Global.getAdminPath()+"/pd/baseMatprice/?repage";
	}
	
	@RequiresPermissions("pd:baseMatprice:edit")
	@RequestMapping(value = "delete")
	public String delete(BaseMatprice baseMatprice, RedirectAttributes redirectAttributes) {
		baseMatpriceService.delete(baseMatprice);
		addMessage(redirectAttributes, "删除本院物资目录成功");
		return "redirect:"+Global.getAdminPath()+"/pd/baseMatprice/?repage";
	}

}