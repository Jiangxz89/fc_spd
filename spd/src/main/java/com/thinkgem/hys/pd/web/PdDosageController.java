/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.thinkgem.hys.utils.HisApiUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.entity.PdDosage;
import com.thinkgem.hys.pd.response.CommonRspVo;
import com.thinkgem.hys.pd.service.PdDosageService;
import com.thinkgem.hys.pd.service.PdStoreroomService;
import com.thinkgem.hys.utils.AxisUtils;
import com.thinkgem.hys.utils.CommonUtils;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

import java.util.List;

/**
 * 器械用量Controller
 * @author wg
 * @version 2018-03-06
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdDosage")
public class PdDosageController extends BaseController {

	@Autowired
	private PdDosageService pdDosageService;
	@Autowired
	private PdStoreroomService pdStoreroomService;
	
	@ModelAttribute
	public PdDosage get(@RequestParam(required=false) String id) {
		PdDosage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdDosageService.get(id);
		}
		if (entity == null){
			entity = new PdDosage();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:pdDosageAdd:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdDosage pdDosage, HttpServletRequest request, HttpServletResponse response, Model model) {
		//如果his接口有则继续调用如果没有需要手动填写true:有接口，false：没有
		String displayFlag = "1";
		if("".equals(Global.getConfig("HIS_DEFAULT_NAME_SPACE"))){
			displayFlag ="0";
		}
		//查询本仓库的用量
		pdDosage.setWarehouseId(UserUtils.getUser().getStoreroomId());
		Page<PdDosage> page = pdDosageService.findPage(new Page<PdDosage>(request, response), pdDosage); 
		model.addAttribute("page", page);
		model.addAttribute("pdDosage", pdDosage);
		model.addAttribute("displayFlag", displayFlag);
		return "hys/pd/pdDosageList";
	}

	@RequiresPermissions("pd:pdDosageAdd:view")
	@RequestMapping(value = "form")
	public String form(PdDosage pdDosage, Model model, HttpServletRequest request) {
		//如果his接口有则继续调用如果没有需要手动填写true:有接口，false：没有
		String displayFlag = "1";
		if("".equals(Global.getConfig("HIS_DEFAULT_NAME_SPACE"))){
			displayFlag ="0";
		}
		pdDosage.setDosageNo(CommonUtils.generateOrderNoByType(MinaGlobalConstants.ORDER_NO_FIRST_LETTER_YL));
		pdDosage.setWarehouseName(UserUtils.getUser().getStoreroomName());
		pdDosage.setWarehouseId(UserUtils.getUser().getStoreroomId());
		model.addAttribute("pdDosage", pdDosage);
		model.addAttribute("displayFlag", displayFlag);
		request.setAttribute("hospitalNumber", Global.getConfig("HOSPITAL_NUMBER"));
		return "hys/pd/pdDosageForm";
	}

	@RequiresPermissions("pd:pdDosage:edit")
	@RequestMapping(value = "save")
	//@ResponseBody
	public String save(PdDosage pdDosage, Model model, RedirectAttributes redirectAttributes) {
		CommonRspVo vo = new CommonRspVo();
		try {
			vo = pdDosageService.saveDosage(pdDosage);
		} catch (Exception e) {
			vo.setCode("-400");
			vo.setMessage("系统异常，请稍后重试！");
			e.printStackTrace();
		}
		if(vo.getMessage()!=null && !"".equals(vo.getMessage())){
			addMessage(redirectAttributes, vo.getMessage());
		}else{
			addMessage(redirectAttributes, "保存用量成功");
		}
		return "redirect:"+Global.getAdminPath()+"/pd/pdDosage/?repage";
	}
	
	@RequiresPermissions("pd:pdDosage:edit")
	@RequestMapping(value = "delete")
	public String delete(PdDosage pdDosage, RedirectAttributes redirectAttributes) {
		pdDosageService.delete(pdDosage);
		addMessage(redirectAttributes, "删除器械用量成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdDosage/?repage";
	}
	
	
	/**
	 * 用量明细
	 * */
	@RequiresPermissions("pd:pdDosage:view")
	@RequestMapping(value = "detailList")
	public String detailList(PdDosage pdDosage, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if(!StringUtils.isNotEmpty(pdDosage.getWarehouseId())){
			pdDosage.setWarehouseId(user.getStoreroomId());  //所属库房
		}
		//导出excel需要的数据
		model.addAttribute("exportDataList", JsonMapper.toJsonString(pdDosageService.findDosageSubsidiary(pdDosage)));
		Page<PdDosage> page = pdDosageService.findDosageSubsidiary(new Page<PdDosage>(request, response), pdDosage); 
		model.addAttribute("page", page);
		model.addAttribute("user", user);
		return "hys/pd/query/dosageQuery";
	}
	
	/**
	 * 供应商用量查询
	 * @param pdDosage
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pd:pdDosage:view")
	@RequestMapping(value = "supplierDosageQuery")
	public String supplierDosageQuery(PdDosage pdDosage, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if(!StringUtils.isNotEmpty(pdDosage.getWarehouseId())){
			pdDosage.setWarehouseId(user.getStoreroomId());  //所属库房
		}
		if(StringUtils.isNotEmpty(user.getSupplierId())){
			pdDosage.setSupplierId(user.getSupplierId());
		}
		Page<PdDosage> page = pdDosageService.findDosageSubsidiary(new Page<PdDosage>(request, response), pdDosage); 
		model.addAttribute("page", page);
		model.addAttribute("user", user);
		return "hys/pd/query/supplierDosageQuery";
	}
	

	/**
	 * HIS接口获取信息---用量使用
	 * @param pdDosage
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getBaseInfo")
	public JSONObject getBaseInfo(@RequestParam(value="queryType",required=true)String queryType, 
							  @RequestParam(value="queryCode",required=true)String queryCode, 
							  @RequestParam(value="remark",required=false, defaultValue="")String remark) {
		return AxisUtils.getBaseInfo(queryType, queryCode, remark);
	}

	/**
	 * 根据住院号和手术编号查询病人信息
	 * @param inHospital
	 * @param operativeNumber
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getBaseInfoByInHospital")
	public JSONObject getBaseInfoByInHospital(@RequestParam(value="inHospital",required=true)String inHospital,String operativeNumber) {

		JSONObject result = HisApiUtils.queryHisPatientInfo(inHospital);

		return result;
	}
}