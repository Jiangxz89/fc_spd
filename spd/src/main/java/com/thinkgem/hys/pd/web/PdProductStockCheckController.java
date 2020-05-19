/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.entity.PdProduct;
import com.thinkgem.hys.pd.entity.PdProductStock;
import com.thinkgem.hys.pd.entity.PdProductStockCheck;
import com.thinkgem.hys.pd.entity.PdProductStockCheckChild;
import com.thinkgem.hys.pd.entity.PdStoreroom;
import com.thinkgem.hys.pd.entity.PdStoreroomAdmin;
import com.thinkgem.hys.pd.entity.excel.ProductExcel;
import com.thinkgem.hys.pd.entity.excel.ProductExcelList;
import com.thinkgem.hys.pd.entity.excel.StockCheckExcel;
import com.thinkgem.hys.pd.entity.excel.StockCheckExcelList;
import com.thinkgem.hys.pd.entity.vo.ProductStockVo;
import com.thinkgem.hys.pd.service.PdProductService;
import com.thinkgem.hys.pd.service.PdProductStockCheckChildService;
import com.thinkgem.hys.pd.service.PdProductStockCheckService;
import com.thinkgem.hys.pd.service.PdProductStockService;
import com.thinkgem.hys.pd.service.PdStoreroomAdminService;
import com.thinkgem.hys.pd.service.PdStoreroomService;
import com.thinkgem.hys.utils.CommonUtils;

/**
 * 盘点表Controller
 * @author sutianqi
 * @version 2018-03-07
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdProductStockCheck")
public class PdProductStockCheckController extends BaseController {

	@Autowired
	private PdProductStockCheckService pdProductStockCheckService;
	@Autowired
	private PdStoreroomService pdStoreroomService;
	@Autowired
	private PdProductStockService pdProductStockService;
	@Autowired
	private PdProductStockCheckChildService pdProductStockCheckChildService;
	@Autowired
	private PdProductService pdProductService;
	@Autowired
	private PdStoreroomAdminService pdStoreroomAdminService;
	
	@ModelAttribute
	public PdProductStockCheck get(@RequestParam(required=false) String id) {
		PdProductStockCheck entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdProductStockCheckService.get(id);
		}
		if (entity == null){
			entity = new PdProductStockCheck();
		}
		return entity;
	}
	
	//@RequiresPermissions("pd:pdProductStockCheck:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdProductStockCheck pdProductStockCheck, HttpServletRequest request, HttpServletResponse response, Model model) {
		String param = request.getParameter("date") == null ? "" : request.getParameter("date");
		
		if(param.equals("today")){
			pdProductStockCheck.setCheckDate(CommonUtils.formatDate("SHORT"));
		}else if(param.equals("yesterday")){
			pdProductStockCheck.setCheckDate(CommonUtils.formatDate("SHORT",CommonUtils.getAppointedDate(param)));
		}else if(param.equals("thisWeek")){
			pdProductStockCheck.setCheckDate(CommonUtils.getFormatDates(CommonUtils.getAppointedDate(param), null));
		}else if(param.equals("")){
			
		}else{
			String custom = param;
			request.setAttribute("custom", custom);
			pdProductStockCheck.setCheckDate(CommonUtils.getFormatDates(CommonUtils.stringToDate(param.split(" - ")[0]), CommonUtils.stringToDate(param.split(" - ")[1])));
		}
		
		//库房权限
		User u = UserUtils.getUser();
		String storeroomType = u.getStoreroomType();
		String storeroomId = u.getStoreroomId();
		String loginName = u.getLoginName();
		List<PdStoreroom> storeroomList = pdStoreroomService.findList(new PdStoreroom());
		if(!loginName.equals("admin")){
			if(storeroomType.equals("1")){
				for(PdStoreroom p : storeroomList){
					String id = p.getId();
					if(id.equals(storeroomId)){
						ArrayList<PdStoreroom> arrayList = new ArrayList<PdStoreroom>();
						arrayList.add(p);
						model.addAttribute("storeroomList", arrayList);
						pdProductStockCheck.setRepoId(id);
					}
				}
			}else if(storeroomType.equals("0")){
				model.addAttribute("storeroomList", storeroomList);
			}
			
		}else{
			model.addAttribute("storeroomList", storeroomList);
		}
		
		
		
		Page<PdProductStockCheck> page = pdProductStockCheckService.findPage(new Page<PdProductStockCheck>(request, response), pdProductStockCheck);
		
		
		if(pdProductStockCheck.getOperPerson() != null && !pdProductStockCheck.getOperPerson().equals("") && pdProductStockCheck.getOperPerson().length() > 10){
			String adminId = pdProductStockCheck.getOperPerson();
			User user = UserUtils.get(adminId);
			String name = user.getName();
			pdProductStockCheck.setOperPerson(name);
		}
		
		
	//	model.addAttribute("userList",userList);
		model.addAttribute("page", page);
		request.setAttribute("pdProductStockCheck", pdProductStockCheck);
		request.setAttribute("dateFlag", param);
		return "hys/pd/pdProductStockCheckList";
	}

	//@RequiresPermissions("pd:pdProductStockCheck:view")
	@RequestMapping(value = "form")
	public String form(PdProductStockCheck pdProductStockCheck, Model model, HttpServletRequest request) {
		
		String id = request.getParameter("id");
		
		if(id != null && id != ""){
			PdProductStockCheck headAndChild = pdProductStockCheckService.getHeadAndChild(id);
			request.setAttribute("checkBean", headAndChild);
		}
		
		model.addAttribute("pdProductStockCheck", pdProductStockCheck);
		return "hys/pd/pdProductStockCheckForm";
	}

	
	/**
	 * 保存
	 * 临时保存：0
	 * 盘点完成：1
	 * */
	//@RequiresPermissions("pd:pdProductStockCheck:edit")
	@RequestMapping(value = "save")
	public String save(PdProductStockCheck pdProductStockCheck, Model model, RedirectAttributes redirectAttributes , HttpServletRequest request) {
		/*if (!beanValidator(model, pdProductStockCheck)){
			return form(pdProductStockCheck, model, request);
		}*/
		
		//更改数据信息
		pdProductStockCheck.setUpdateDate(new Date());
		
		
		String flag = request.getParameter("flag");	//保存类型标记
		String id = pdProductStockCheck.getId();
		//取子盘点表
		List<PdProductStockCheckChild> child = pdProductStockCheck.getChild();
		//新建盘点
		if(id == null || "".equals(id)){
			//生成id
			id = CommonUtils.getRandomString(32);
			
			//取当前时间
			String date = CommonUtils.formatDate("LONG");
			//当前登录用户名称
			String loginName = UserUtils.getUser().getName();
			String userId = UserUtils.getUser().getId();
			//生成编号
			String number = CommonUtils.generateNumber("PD");
			
			int alreadyCount = 0;	//已盘产品
			int profitLossCount = 0;	//盘盈盘亏
			
			//计算已盘和盈亏 并且 保存子表
			for(int i = 0 ; i < child.size(); i ++){
				alreadyCount += child.get(i).getStockCount();
				profitLossCount += child.get(i).getProfitLossCount();
				
				child.get(i).setParentId(id);
				pdProductStockCheckChildService.save(child.get(i));
			}
			
			//封装盘点主实体类
			pdProductStockCheck.setId(id);
			pdProductStockCheck.setNumber(number);
			pdProductStockCheck.setCheckDate(date);
			pdProductStockCheck.setOperPerson(userId);
			pdProductStockCheck.setAlreadyCount(alreadyCount);
			pdProductStockCheck.setProfitLossCount(profitLossCount);
			pdProductStockCheck.setStatus(flag);
			
			pdProductStockCheckService.insertNoneId(pdProductStockCheck);
		}else{
			
			int alreadyCount = 0;	//已盘产品
			int profitLossCount = 0;	//盘盈盘亏
			
			
			//计算已盘和盈亏 并且 保存子表
			for(int i = 0 ; i < child.size(); i ++){
				PdProductStockCheckChild ch = child.get(i);
				alreadyCount += ch.getStockCount();
				profitLossCount += ch.getProfitLossCount();
				ch.setParentId(id);
				//判断子表是否存在，存在修改，否则添加
				if(ch.getId()!=null && ch.getId()!=""){
					pdProductStockCheckChildService.update(ch);
				}else{
					pdProductStockCheckChildService.save(ch);
				}
			}
			
			pdProductStockCheck.setAlreadyCount(alreadyCount);
			pdProductStockCheck.setProfitLossCount(profitLossCount);
			pdProductStockCheck.setStatus(flag);
			pdProductStockCheckService.update(pdProductStockCheck);
		}
		
		
		addMessage(redirectAttributes, "保存盘点表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdProductStockCheck/?repage";
	}
	/**
	 * AJAX请求仓库列表
	 * */
	@RequestMapping(value = "findStoreroomList")
	public void findStoreroomList(HttpServletRequest request , HttpServletResponse response){
		User u = UserUtils.getUser();
		String storeroomType = u.getStoreroomType();
		String storeroomId = u.getStoreroomId();
		
		List<PdStoreroom> findList = new ArrayList<PdStoreroom>();
		PdStoreroom pdStoreroom = new PdStoreroom();
		
		if(storeroomType.equals("0")){
			findList = pdStoreroomService.findList(pdStoreroom);
		}else{
			pdStoreroom.setId(storeroomId);
			PdStoreroom pdStoreroom2 = pdStoreroomService.get(pdStoreroom);
			findList.add(pdStoreroom2);
		}
		
		
		String json = JSONArray.toJSONString(findList);
		
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * AJAX请求库存列表
	 * */
	@RequestMapping(value = "findStockByStoreroomId")
	public void findStockByStoreroomId( HttpServletRequest request , HttpServletResponse response){
		
		PdProductStock pdProductStock = new PdProductStock();
		//模糊查询参数
		String name = request.getParameter("name") != null ? request.getParameter("name") : "";
		String number = request.getParameter("number") != null ? request.getParameter("number") : "";
		String spec = request.getParameter("spec") != null ? request.getParameter("spec") : "";
		String version = request.getParameter("version") != null ? request.getParameter("version") : "";
		String batchNo = request.getParameter("batchNo") != null ? request.getParameter("batchNo") : "";
		String validDate = request.getParameter("validDate") != null ? request.getParameter("validDate") : "";
		PdProduct prod = new PdProduct();
		prod.setName(name);
		prod.setNumber(number);
		prod.setSpec(spec);
		prod.setVersion(version);
		pdProductStock.setPdProduct(prod);
		pdProductStock.setBatchNo(batchNo);
		if(!validDate.equals("")){
			pdProductStock.setValidDate(new Date(validDate));
		}
		
		String storeroomId = request.getParameter("storeroomId");
		
		pdProductStock.setStoreroomId(storeroomId);
		
		List<PdProductStock> findList = pdProductStockService.findListForQuery(pdProductStock);
		
		//vo数据
		List<ProductStockVo> voList = new ArrayList<ProductStockVo>();
		
		ProductStockVo productStockVo ;
		
		for(int i = 0 ; i < findList.size() ; i++){
			productStockVo = new ProductStockVo();
			PdProductStock p = findList.get(i);
			PdProduct pdProduct = pdProductService.get(p.getProductId());
			productStockVo.setPdProduct(pdProduct);
			productStockVo.setPdProductStock(p);
			voList.add(productStockVo);
		}
		
		//json
		String json = JSONObject.toJSONString(voList);
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * FORM表单请求库存列表
	 * */
	@RequestMapping(value = "findStockCheckList")
	public String findStockCheckList(PdProductStock pdProductStock, HttpServletRequest request, HttpServletResponse response, Model model){
		String validDateMore = pdProductStock.getValidDateMore();
		if(validDateMore != null && !validDateMore.equals("") && !validDateMore.equals(" ")){
			//	pdProductStock.setValidDateMore(CommonUtils.getFormatDates(CommonUtils.stringToDate(validDateMore.split(" - ")[0]), CommonUtils.stringToDate(validDateMore.split(" - ")[1])));
			pdProductStock.setValidDateBegin(validDateMore.split(" - ")[0]);
			pdProductStock.setValidDateEnd(validDateMore.split(" - ")[1]);
		}
		
		String storeroomId = request.getParameter("storeroomId");
		
		pdProductStock.setStoreroomId(storeroomId);
		
		Page<PdProductStock> page = pdProductStockService.findListAndProduct(new Page<PdProductStock>(request, response), pdProductStock);
		
		//vo数据
		List<ProductStockVo> voList = new ArrayList<ProductStockVo>();
		
		ProductStockVo productStockVo ;
		
		for(int i = 0 ; i < page.getList().size() ; i++){
			productStockVo = new ProductStockVo();
			PdProductStock p = page.getList().get(i);
			PdProduct pdProduct = pdProductService.get(p.getProductId());
			productStockVo.setPdProduct(pdProduct);
			productStockVo.setPdProductStock(p);
			voList.add(productStockVo);
		}
		
		pdProductStock.setValidDateMore(validDateMore);
		request.setAttribute("stock", pdProductStock);
		request.setAttribute("voList", voList);
		request.setAttribute("storeroomId", storeroomId);
	//	model.addAttribute(voList);
		model.addAttribute(page);
		
		return "hys/pd/box/pdProductStockCheckBox";
	}
	
	/**
	 * AJAX请求单条库存
	 * */
	@RequestMapping(value = "getStock")
	public void getStock(HttpServletRequest request, HttpServletResponse response){
		
		String id = request.getParameter("id");
		PdProductStock pdProductStock = pdProductStockService.get(id);
		PdProduct pdProduct = pdProductService.get(pdProductStock.getProductId());
		
		ProductStockVo vo = new ProductStockVo();
		vo.setPdProduct(pdProduct);
		vo.setPdProductStock(pdProductStock);
		
		String json = JSONObject.toJSONString(vo);
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 到盘点详情页
	 * */
	@RequestMapping(value = "detail")
	public String detail(HttpServletRequest request , HttpServletResponse response){
		
		String id = request.getParameter("id");
		//String status = request.getParameter("status");
		
		//取盘点头和子列表
		PdProductStockCheck checkBean = pdProductStockCheckService.getHeadAndChild(id);
		
		request.setAttribute("checkBean", checkBean);
		
		return "hys/pd/pdProductStockCheckDetail";
	}
	
	
	/**
	 * 删除
	 * */
	@RequestMapping(value = "delete")
	public String delete(HttpServletRequest request, RedirectAttributes redirectAttributes){
		String id = request.getParameter("id");
		PdProductStockCheck pdProductStockCheck = new PdProductStockCheck();
		pdProductStockCheck.setId(id);
		pdProductStockCheckService.delete(pdProductStockCheck);
		addMessage(redirectAttributes, "删除盘点表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdProductStockCheck/list";
	}
	
	

	
	/**
	 * AJAX响应方法：
	 * 导出全部数据
	 * */
	@RequestMapping(value = "exportData")
	public void exportData(StockCheckExcelList stockCheckExcelList , HttpServletRequest request , HttpServletResponse response){
		String head = request.getParameter("head");
		
		
		ExportExcel exportExcel = new ExportExcel(head,StockCheckExcel.class);
		
		List<StockCheckExcel> list = stockCheckExcelList.getList();
		exportExcel.setDataList(list);
		String name = head + ".xlsx";
		try {
			exportExcel.write(response, name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 库房用户级联
	 * ajax响应
	 * */
	@RequestMapping(value = "getUserList")
	public void getUserList(HttpServletRequest request , HttpServletResponse response){
		String storeroomId = request.getParameter("storeroomId");
		
		PdStoreroomAdmin storeroomAdmin = new PdStoreroomAdmin();
		storeroomAdmin.setStoreroomId(storeroomId);
		
		List<PdStoreroomAdmin> adminList = pdStoreroomAdminService.findAdminList(storeroomAdmin);
		
		String json = JSONObject.toJSONString(adminList);
		
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}