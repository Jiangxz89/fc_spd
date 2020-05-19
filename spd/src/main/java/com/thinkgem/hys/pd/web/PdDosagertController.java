/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web;

import java.io.IOException;
import java.util.ArrayList;
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
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.entity.PdDosage;
import com.thinkgem.hys.pd.entity.PdDosageDetail;
import com.thinkgem.hys.pd.entity.PdDosagert;
import com.thinkgem.hys.pd.entity.PdDosagertDetail;
import com.thinkgem.hys.pd.entity.excel.DosagertExcel;
import com.thinkgem.hys.pd.entity.excel.DosagertExcelList;
import com.thinkgem.hys.pd.service.PdDosageDetailService;
import com.thinkgem.hys.pd.service.PdDosageService;
import com.thinkgem.hys.pd.service.PdDosagertDetailService;
import com.thinkgem.hys.pd.service.PdDosagertService;
import com.thinkgem.hys.utils.CommonUtils;

/**
 * 用量退回信息Controller
 * @author yueguoyun
 * @version 2018-03-31
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdDosagert")
public class PdDosagertController extends BaseController {

	@Autowired
	private PdDosagertService pdDosagertService;
	@Autowired
	private PdDosageService pdDosageService;
	@Autowired
	private PdDosageDetailService pdDosageDetailService;
	@Autowired
	private PdDosagertDetailService pdDosagertDetailService;
	
	@ModelAttribute
	public PdDosagert get(@RequestParam(required=false) String id) {
		PdDosagert entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdDosagertService.get(id);
		}
		if (entity == null){
			entity = new PdDosagert();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:pdDosagertAdd:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdDosagert pdDosagert, HttpServletRequest request, HttpServletResponse response, Model model) {
		pdDosagert.setDosagertInroomId(UserUtils.getUser().getStoreroomId());
		Page<PdDosagert> page = pdDosagertService.findPage(new Page<PdDosagert>(request, response), pdDosagert); 
		model.addAttribute("page", page);
		return "hys/pd/pdDosagertList";
	}

	@RequiresPermissions("pd:pdDosagertAdd:view")
	@RequestMapping(value = "form")
	public String form(PdDosagert pdDosagert, Model model, HttpServletRequest request) {
		String number = CommonUtils.generateOrderNoByType(MinaGlobalConstants.ORDER_NO_FIRST_LETTER_TYL);//生成编号
		String date = CommonUtils.formatDate("SHORT");	//当前日期
		String storeroomId = UserUtils.getUser().getStoreroomId();
		request.setAttribute("number", number);
		request.setAttribute("date", date);
		request.setAttribute("storeroomId", storeroomId);
		request.setAttribute("flag","save");
		model.addAttribute("pdDosagert", pdDosagert);
		return "hys/pd/pdDosagertForm";
	}
	
	@RequiresPermissions("pd:pdDosagert:view")
	@RequestMapping(value = "detail")
	public String detail(HttpServletRequest request , HttpServletResponse response){
		String id = request.getParameter("id");

		PdDosagert pdDosagert = pdDosagertService.get(id);
		
		request.setAttribute("flag", "detail");
		request.setAttribute("pdDosagert", pdDosagert);
		
		return "hys/pd/pdDosagertForm";
	}

	@RequiresPermissions("pd:pdDosagert:edit")
	@RequestMapping(value = "save")
	public String save(PdDosagert pdDosagert, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!beanValidator(model, pdDosagert)){
			return form(pdDosagert, model, request);
		}
		//保存信息及库存加减
		pdDosagertService.saveAll(pdDosagert);
		addMessage(redirectAttributes, "保存用量退回信息成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdDosagert/list";
	}
	
	@RequiresPermissions("pd:pdDosagert:edit")
	@RequestMapping(value = "delete")
	public String delete(PdDosagert pdDosagert, RedirectAttributes redirectAttributes) {
		pdDosagertService.delete(pdDosagert);
		addMessage(redirectAttributes, "删除用量退回信息成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdDosagert/?repage";
	}
	
	//查询病人姓名
	@RequiresPermissions("pd:pdDosagert:edit")
	@RequestMapping(value = "searchPatientName")
	public void searchPatientName(HttpServletRequest request , HttpServletResponse response) {
		String inHospitalNo = request.getParameter("hospitalNo") == null ? "" : request.getParameter("hospitalNo");//病房编号
		PdDosage pdDosage = new PdDosage();
		pdDosage.setInHospitalNo(inHospitalNo);
		List<PdDosage> findList = pdDosageService.findList(pdDosage);
		String patientInfo = "";
		if(findList.size() > 0){
			PdDosage dosage = findList.get(0);
			patientInfo = dosage.getPatientInfo();
		}
		
		try {
			if(patientInfo == null || patientInfo.equals("")){
				response.getWriter().write("none");
			}
			response.getWriter().write(patientInfo.split("	")[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//病人用量查询
	@RequiresPermissions("pd:pdDosagert:edit")
	@RequestMapping(value = "searchInfo")
	public void searchInfo(HttpServletRequest request , HttpServletResponse response) {
		String inHospitalNo = request.getParameter("hospitalNo") == null ? "" : request.getParameter("hospitalNo");//病房编号
		String patientName = request.getParameter("patientName");//病人姓名
		String storeroomId = UserUtils.getUser().getStoreroomId();//库房id
		
		if(inHospitalNo.equals("")){
			inHospitalNo = "none No";
		}
		
		PdDosage pdDosage = new PdDosage();
		pdDosage.setInHospitalNo(inHospitalNo);
		//pdDosage.setPatientInfo(patientName);不根据名称查询，只根据住院号查询使用明细
		pdDosage.setWarehouseId(storeroomId);
		
		List<PdDosage> findList = pdDosageService.findList(pdDosage);
		
		List<PdDosageDetail> list = new ArrayList<PdDosageDetail>();
		
		for(int i = 0 ; i < findList.size() ; i ++){//查询出的所有单头的子单赋值到list
			PdDosage p = findList.get(i);
			
			PdDosageDetail pdDosageDetail = new PdDosageDetail();
			pdDosageDetail.setDosageNo(p.getDosageNo());
			List<PdDosageDetail> l = pdDosageDetailService.findListByLeftRefundNum(pdDosageDetail);
			list.addAll(l);
		}
		
		String json = JSONObject.toJSONString(list);
		
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 用户退回明细查询
	 */
	@RequiresPermissions("pd:pdDosagert:view")
	@RequestMapping(value = "detailList")
	public String detailList(PdDosagert pdDosagert, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();		
		if(!StringUtils.isNotEmpty(pdDosagert.getDosagertRoomId())){
			pdDosagert.setDosagertRoomId(user.getStoreroomId());  //所属库房
		}
		model.addAttribute("exportDataList", JsonMapper.toJsonString(pdDosagertService.detailList(pdDosagert)));
		Page<PdDosagert> page = pdDosagertService.detailList(new Page<PdDosagert>(request, response),pdDosagert);
		model.addAttribute("page", page);
		model.addAttribute("user", user);
		return "hys/pd/query/dosagertQuery";
	}
	
	@RequestMapping(value = {"pdAddDosagertBox"})
	public String pdAddDosagertBox(PdDosagert pdDosagert, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		pdDosagert.setDosagertRoomId(user.getStoreroomId());
		StringBuffer sql = new StringBuffer(" AND a.dosagert_state is null");
		Map<String, String> sqlMap=new HashMap<String, String>();
		sqlMap.put("dsf", sql.toString());
		pdDosagert.setSqlMap(sqlMap);
		Page<PdDosagert> page = pdDosagertService.findPage(new Page<PdDosagert>(request, response), pdDosagert); 
		model.addAttribute("page", page);
		return "hys/pd/pdAddDosagertBox";
	}
	
	@ResponseBody
	@RequestMapping(value = "getData")
	public Object getData(HttpServletRequest request , HttpServletResponse response){
		String id = request.getParameter("id");
		PdDosagert pdDosagert = pdDosagertService.get(id);		
		return pdDosagert;		
	}
	
	@ResponseBody
	@RequestMapping(value = "getDetlData")
	public Object getDetlData(PdDosagert pdDosagert, HttpServletRequest request, HttpServletResponse response){
		String dosagertId = pdDosagert.getId();
		PdDosagertDetail dosagertDetail = new PdDosagertDetail();
		dosagertDetail.setDosagertId(dosagertId);
		List<PdDosagertDetail> pdDosagertDetls = pdDosagertDetailService.findList(dosagertDetail);
		return pdDosagertDetls;		
	}
	
	
	/**
	 * AJAX响应方法：
	 * 导出全部数据
	 * */
	@RequestMapping(value = "exportData")
	public void exportData(DosagertExcelList dosagertExcelList , HttpServletRequest request , HttpServletResponse response){
		//String head = request.getParameter("head");
		
		//String[] split = head.split(",");
		
		ExportExcel exportExcel = new ExportExcel("用户退回明细",DosagertExcel.class);
		
		List<DosagertExcel> list = dosagertExcelList.getList();
		exportExcel.setDataList(list);
		try {
			exportExcel.write(response, "用户退回明细.xlsx");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}