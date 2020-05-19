/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web;

import java.util.List;
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
import com.thinkgem.hys.pd.constants.RspVo;
import com.thinkgem.hys.pd.entity.PdStoreroomAdmin;
import com.thinkgem.hys.pd.entity.PdStoreroomPatrol;
import com.thinkgem.hys.pd.service.PdStoreroomAdminService;
import com.thinkgem.hys.pd.service.PdStoreroomPatrolService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 库房巡查Controller
 * @author wg
 * @version 2018-03-30
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdStoreroomPatrol")
public class PdStoreroomPatrolController extends BaseController {

	@Autowired
	private PdStoreroomPatrolService pdStoreroomPatrolService;
	
	@Autowired
	private PdStoreroomAdminService storeroomAdminService;
	
	@ModelAttribute
	public PdStoreroomPatrol get(@RequestParam(required=false) String id) {
		PdStoreroomPatrol entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdStoreroomPatrolService.get(id);
		}
		if (entity == null){
			entity = new PdStoreroomPatrol();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:pdStoreroomPatrol:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdStoreroomPatrol pdStoreroomPatrol, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		//如果是二级库管登录，只能看本科室的
		User user = UserUtils.getUser();
		if(MinaGlobalConstants.STOREROOM_TYPE_1.equals(user.getStoreroomType())){
			pdStoreroomPatrol.setStoreroomId(user.getStoreroomId());
		}
		Page<PdStoreroomPatrol> page = pdStoreroomPatrolService.findPage(new Page<PdStoreroomPatrol>(request, response), PdStoreroomPatrol.dealDate(pdStoreroomPatrol)); 
		model.addAttribute("page", page);
		model.addAttribute("pdStoreroomPatrol", pdStoreroomPatrol);
		return "hys/pd/pdStoreroomPatrolList";
	}

	@RequiresPermissions("pd:pdStoreroomPatrol:view")
	@RequestMapping(value = "form")
	public String form(PdStoreroomPatrol pdStoreroomPatrol, Model model) {
		model.addAttribute("pdStoreroomPatrol", pdStoreroomPatrol);
		return "hys/pd/pdStoreroomPatrolForm";
	}

	@RequiresPermissions("pd:pdStoreroomPatrol:edit")
	@RequestMapping(value = "save")
	public String save(PdStoreroomPatrol pdStoreroomPatrol, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pdStoreroomPatrol)){
			return form(pdStoreroomPatrol, model);
		}
		pdStoreroomPatrolService.save(pdStoreroomPatrol);
		addMessage(redirectAttributes, "保存库房巡查成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdStoreroomPatrol/?repage";
	}
	//保存巡查
	@RequestMapping(value = "savePatrol")
	@ResponseBody
	public RspVo savePatrol(PdStoreroomPatrol pdStoreroomPatrol,String patrolRecord) {
		return pdStoreroomPatrolService.savePatrol(pdStoreroomPatrol,patrolRecord);
	}
	//修改巡查
	@RequestMapping(value = "updatePatrol")
	@ResponseBody
	public RspVo updatePatrol(PdStoreroomPatrol pdStoreroomPatrol,String patrolRecord) {
		return pdStoreroomPatrolService.updatePatrol(pdStoreroomPatrol,patrolRecord);
	}
	
	@RequiresPermissions("pd:pdStoreroomPatrol:edit")
	@RequestMapping(value = "delete")
	public String delete(PdStoreroomPatrol pdStoreroomPatrol, RedirectAttributes redirectAttributes) {
		pdStoreroomPatrolService.delete(pdStoreroomPatrol);
		addMessage(redirectAttributes, "删除库房巡查记录成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdStoreroomPatrol/?repage";
	}
	
	/**
	 * 根据库房id查询巡逻人---库房动态改变巡查员的下拉菜单
	 * @param pdStoreroomPatrol
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "findpdStoreroomPatrolByStoreroomId")
	@ResponseBody
	public List<PdStoreroomAdmin> findpdStoreroomPatrolByStoreroomId(PdStoreroomAdmin roomAdmin, RedirectAttributes redirectAttributes) {
		List<PdStoreroomAdmin> list = storeroomAdminService.findAdminList(roomAdmin);
		return list;
	}
}