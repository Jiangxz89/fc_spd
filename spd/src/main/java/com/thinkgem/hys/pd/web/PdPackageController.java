/**
* * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.entity.PdAllocationProduct;
import com.thinkgem.hys.pd.entity.PdApplyDetail;
import com.thinkgem.hys.pd.entity.PdBarcodeImg;
import com.thinkgem.hys.pd.entity.PdCategory;
import com.thinkgem.hys.pd.entity.PdGroup;
import com.thinkgem.hys.pd.entity.PdPackage;
import com.thinkgem.hys.pd.entity.PdProduct;
import com.thinkgem.hys.pd.entity.PdProductMPackage;
import com.thinkgem.hys.pd.entity.PdProductStockTotal;
import com.thinkgem.hys.pd.entity.PdStoreroom;
import com.thinkgem.hys.pd.entity.PdVender;
import com.thinkgem.hys.pd.service.PdAllocationProductService;
import com.thinkgem.hys.pd.service.PdApplyDetailService;
import com.thinkgem.hys.pd.service.PdBarcodeImgService;
import com.thinkgem.hys.pd.service.PdCategoryService;
import com.thinkgem.hys.pd.service.PdGroupService;
import com.thinkgem.hys.pd.service.PdPackageService;
import com.thinkgem.hys.pd.service.PdProductMPackageService;
import com.thinkgem.hys.pd.service.PdProductService;
import com.thinkgem.hys.pd.service.PdProductStockTotalService;
import com.thinkgem.hys.pd.service.PdStoreroomService;
import com.thinkgem.hys.pd.service.PdVenderService;
import com.thinkgem.hys.utils.BarcodeUtils;
import com.thinkgem.hys.utils.CommonUtils;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 定数包表Controller
 * @author sutianqi
 * @version 2018-03-07
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdPackage")
public class PdPackageController extends BaseController {

	@Autowired
	private PdPackageService pdPackageService;
	@Autowired
	private PdProductService pdProductService;
	@Autowired
	private PdCategoryService pdCategoryService;
	@Autowired
	private PdGroupService pdGroupService;
	@Autowired
	private PdVenderService pdVenderService;
	@Autowired
	private PdProductMPackageService pdProductMPackageService ;
	@Autowired
	private PdBarcodeImgService pdBarcodeImgService;
	@Autowired
	private PdStoreroomService pdStoreroomService;
	@Autowired
	private PdProductStockTotalService pdProductStockTotalService;
	@Autowired
	private PdAllocationProductService pdAllocationProductService;
	@Autowired
	private PdApplyDetailService pdApplyDetailService;
	
	@ModelAttribute
	public PdPackage get(@RequestParam(required=false) String id) {
		PdPackage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdPackageService.get(id);
		}
		if (entity == null){
			entity = new PdPackage();
		}
		return entity;
	}
	
	//@RequiresPermissions("pd:pdPackage:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdPackage pdPackage, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		Page<PdPackage> page = pdPackageService.findPage(new Page<PdPackage>(request, response), pdPackage); 
		model.addAttribute("page", page);
		model.addAttribute(pdPackage);
		return "hys/pd/pdPackageList";
	}
	
	/**
	 * 添加申领单-选择定数包
	 * @param pdPackage
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "choosePackage")
	public String choosePackage(PdPackage pdPackage, HttpServletRequest request, HttpServletResponse response, Model model) {
		//申领排除一级库房没有的定数包
		String storeId = pdStoreroomService.findFirstStoreroom();
		PdProductStockTotal pskt = new PdProductStockTotal();
		pskt.setStoreroomId(storeId);
		pskt.getSqlMap().put("dsf", " AND a.stock_num > 0 ");
		List<PdProductStockTotal> totalList = pdProductStockTotalService.findList(pskt);
		Set<String> sb = new HashSet<String>();
		for (PdProductStockTotal pt : totalList) {
			sb.add(pt.getProductId());
		}
		Set<String> sbsb = new HashSet<String>();
		List<PdProductMPackage> pmpList = pdProductMPackageService.findList(new PdProductMPackage());
		for (PdProductMPackage pdProductMPackage : pmpList) {
			if (sbsb.contains(pdProductMPackage.getPackageId()))
				continue;
			if (!sb.contains(pdProductMPackage.getProductId())) {
				sbsb.add(pdProductMPackage.getPackageId());
			}
		}
		pdPackage.setPackageIds(sbsb);
		
		Page<PdPackage> page = pdPackageService.findPage(new Page<PdPackage>(request, response), pdPackage); 
		if(page!=null && page.getList()!=null && page.getList().size()>0){
			PdProductMPackage ppm = new PdProductMPackage();
			ppm.setStoreroomId(UserUtils.getUser().getStoreroomId());
			for(PdPackage pp : page.getList()){
				ppm.setPackageId(pp.getId());
				List<PdProductMPackage> mypp = pdProductMPackageService.findList(ppm);
				pp.setChild(mypp);
				String s = JsonMapper.toJsonString(mypp);
				pp.setJsonStr(s);
			}
		}
		model.addAttribute("page", page);
		return "hys/pd/pdAddPackageBox";
	}
 
	//@RequiresPermissions("pd:pdPackage:view")
	@RequestMapping(value = "form")
	public String form(PdPackage pdPackage, Model model , HttpServletRequest request) {
		
		//获取产品分类
		List<PdCategory> pdCategoryList = pdCategoryService.findList(new PdCategory());
		//获取产品组别
		List<PdGroup> pdGroupList = pdGroupService.findList(new PdGroup());
		//获取生产厂家
		List<PdVender> pdVenderList = pdVenderService.findSimpleList(new PdVender());
		
		model.addAttribute("pdPackage", pdPackage);
		model.addAttribute("pdCategoryList", pdCategoryList);
		model.addAttribute("pdGroupList", pdGroupList);
		model.addAttribute("pdVenderList", pdVenderList);
		request.setAttribute("flag", "save");
		return "hys/pd/pdPackageForm";
	}

	
	/**
	 * 添加定数包 / 修改定数包
	 * */
	//@RequiresPermissions("pd:pdPackage:edit")
	@RequestMapping(value = "save")
	public String save(PdPackage pdPackage, Model model, RedirectAttributes redirectAttributes , HttpServletRequest request) {
		/*if (!beanValidator(model, pdPackage)){
			return form(pdPackage, model , request);
		}*/
		String flag = request.getParameter("flag");//标记 ： 新增 save ： 更新 update
		String id = pdPackage.getId();
		List<PdProductMPackage> child = pdPackage.getChild();//取子列表
		if(child == null){
			return "redirect:"+Global.getAdminPath()+"/pd/pdPackage/?list";
		}
		
		if(flag.equals("save")){
			//生成id
			if(id == null || id.equals("")){
				id = CommonUtils.getRandomString(32);
				pdPackage.setId(id);
			}
			int count = 0;//计算总量
			//循环插入子单
			for(int i = 0 ; i < child.size() ; i ++){
				PdProductMPackage c = child.get(i);
				if( c.getProductId() == null || c.getProductId().equals("")){
					child.remove(i);
					i = i-1;
					continue;
				}
				c.setPackageId(id);
				count += c.getProductCount();
				c.setCreateBy(UserUtils.getUser());
				c.setCreateDate(new Date());
				pdProductMPackageService.save(c);
			}
			pdPackage.setSum(count);//插入主表
			pdPackage.setCreateBy(UserUtils.getUser());
			pdPackage.setCreateDate(new Date());
			pdPackage.setUpdateBy(UserUtils.getUser());
			pdPackage.setUpdateDate(new Date());
			pdPackageService.saveReturnId(pdPackage);
			
		}else if(flag.equals("update")){
			//删除子单
			pdPackageService.removeChildren(child,id);
			//计算总量
			int count = 0;
			//循环插入子单
			for(int i = 0 ; i < child.size() ; i ++){
				PdProductMPackage c = child.get(i);
				if( c.getProductId() == null || c.getProductId().equals("")){
					child.remove(i);
					i = i-1;
					continue;
				}
				c.setPackageId(id);
				count += c.getProductCount();
				//判断是否 是已存在子单
				if(c.getId() != null && c.getId() != ""){
					pdProductMPackageService.update(c);
				}else{
					pdProductMPackageService.save(c);
				}
				
			}
			//插入主表
			pdPackage.setSum(count);
			pdPackage.setUpdateBy(UserUtils.getUser());
			pdPackage.setUpdateDate(new Date());
			pdPackageService.update(pdPackage);
		}
		
		
		addMessage(redirectAttributes, "保存定数包表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdPackage/?list";
	}
	
	//@RequiresPermissions("pd:pdPackage:edit")
	@RequestMapping(value = "delete")
	public void delete(PdPackage pdPackage, RedirectAttributes redirectAttributes, HttpServletResponse response) {
		
		String id = pdPackage.getId();
		
		//调拨单校验
		PdAllocationProduct allocationProduct = new PdAllocationProduct();
		allocationProduct.setProductCode(id);
		List<PdAllocationProduct> alloList = pdAllocationProductService.findList(allocationProduct);
		if(alloList.size() > 0){
			try {
				response.getWriter().print("alloError");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		//	return "redirect:"+Global.getAdminPath()+"/pd/pdPackage/?repage&delKey=alloError";
		}
		
		//申领单校验
		PdApplyDetail applyDetail = new PdApplyDetail();
		applyDetail.setPackageId(id);
		List<PdApplyDetail> applyList = pdApplyDetailService.findList(applyDetail);
		if(applyList.size() > 0){
			try {
				response.getWriter().print("applyError");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		//	return "redirect:"+Global.getAdminPath()+"/pd/pdPackage/?repage&delKey=applyError";
		}
		
		pdPackageService.delete(pdPackage);
		addMessage(redirectAttributes, "删除定数包表成功");
		try {
			response.getWriter().print("success");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	//	return "redirect:"+Global.getAdminPath()+"/pd/pdPackage/?repage";
	}

	/**
	 * 获取所有产品列表：有分页
	 * */
	@RequestMapping(value= "getAllProduct")
	public String getAllProduct(PdProduct pdProduct, HttpServletRequest request , HttpServletResponse response, Model model){
		
	/*	String number = request.getParameter("number") != null ? request.getParameter("number") : "";
		String name = request.getParameter("name") != null ? request.getParameter("name") : "";
		String categoryName = request.getParameter("categoryName") != null ? request.getParameter("categoryName") : "";
		String groupName = request.getParameter("groupName") != null ? request.getParameter("groupName") : "";
		String version = request.getParameter("version") != null ? request.getParameter("version") : "";
		String venderId = request.getParameter("venderId") != null ? request.getParameter("venderId") : "";
		String power = request.getParameter("power") != null ? request.getParameter("power") : "";
		String pageNo = request.getParameter("pageNo") != "" ? request.getParameter("pageNo") : "1";
		String pageSize = request.getParameter("pageSize") != "" ? request.getParameter("pageSize") : "10";
	
		
		PdProduct prod = new PdProduct();
		prod.setNumber(number);
		prod.setName(name);
		prod.setCategoryName(categoryName);
		prod.setGroupName(groupName);
		prod.setVersion(version);
		prod.setVenderName(venderId);
		prod.setPower(power); */
		
		Page<PdProduct> page = new Page<PdProduct>(request,response);
		/*	page.setPageNo(Integer.parseInt(pageNo));
		page.setPageSize(Integer.parseInt(pageSize));*/
		pdProduct.setIsUrgent("a0");//非紧急产品
		Page<PdProduct> findPage = pdProductService.basicFindPage(page, pdProduct);
	//	List<PdProduct> findList = pdProductService.findList(prod);
		

		//获取产品分类
		List<PdCategory> pdCategoryList = pdCategoryService.findList(new PdCategory());
		//获取产品组别
		List<PdGroup> pdGroupList = pdGroupService.findList(new PdGroup());
		//获取生产厂家
		List<PdVender> pdVenderList = pdVenderService.findSimpleList(new PdVender());
		
		model.addAttribute("pdCategoryList", pdCategoryList);
		model.addAttribute("pdGroupList", pdGroupList);
		model.addAttribute("pdVenderList", pdVenderList);
		model.addAttribute("page", findPage);
		model.addAttribute("pdProduct", pdProduct);
		
		return "hys/pd/box/pdPackageBox";
	}
	
	/**
	 * 调拨单添加产品
	 * @param pdProduct
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value= "getAllProductByAllocation")
	public String getAllProductByAllocation(String ids,PdProduct pdProduct,HttpServletRequest request , HttpServletResponse response,Model model){
		List<PdProduct> pdProductList = Lists.newArrayList();
		pdProduct.setSelfStoreroomId(UserUtils.getUser().getStoreroomId());
		//List<PdProduct> productList = pdProductService.findList(pdProduct);
		Page<PdProduct> page = pdProductService.findPage(new Page<PdProduct>(request, response), pdProduct);
		List<PdProduct> productList = page.getList();
		if(StringUtils.isNotBlank(ids)){
			String[] idArr = ids.split(",");
			for (PdProduct pd : productList) {
				if(!entityContains(pd,idArr)){
					pdProductList.add(pd);
				}
			}
		}else{
			pdProductList = productList;
		}
		page.setList(pdProductList);
		//判断出入库库房等级
		String outStoreroomId = pdProduct.getStoreroomId();
		String inStoreroomId = UserUtils.getUser().getStoreroomId();
		PdStoreroom outStoreroom = pdStoreroomService.get(outStoreroomId);
		PdStoreroom inStoreroom = pdStoreroomService.get(inStoreroomId);
		if(MinaGlobalConstants.STOREROOM_TYPE_1.equals(inStoreroom.getStoreroomType())){
			model.addAttribute("viewPermission", "1");
		}else if(inStoreroom.getStoreroomType().equals(outStoreroom.getStoreroomType())){
			model.addAttribute("viewPermission", "1");
		}else{
			model.addAttribute("viewPermission", "0");
		}
		model.addAttribute("pdProductList", pdProductList);
		model.addAttribute("pdProduct", pdProduct);
		model.addAttribute("page", page);
		model.addAttribute("ids", ids);
		return "hys/pd/pdAddAlloactionBox";
	}
	
	/**
	 * 调拨单添加定数包
	 * @param pdPackage
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("pdAllocationAddPackage")
	public String pdAllocationAddPackage(String ids,PdPackage pdPackage, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<PdPackage> packageList = Lists.newArrayList();
		List<PdPackage> packList = Lists.newArrayList();
		//List<PdPackage> pdPackageList = pdPackageService.findList(pdPackage);
		Page<PdPackage> page = pdPackageService.findPage(new Page<PdPackage>(request, response), pdPackage);
		List<PdPackage> pdPackageList = page.getList();
		if(StringUtils.isNotBlank(ids)){
			String[] idArr = ids.split(",");
			for (PdPackage pd : pdPackageList) {
				if(!entityContains(pd,idArr)){
					packList.add(pd);
				}
			}
		}else{
			packList = pdPackageList;
		}
		//设置定数包下面的产品集合
		if(null!=packList && packList.size()>0){
			for (PdPackage pdPack : packList) {
				String id = pdPack.getId();
				List<PdProduct> productList = pdPackageService.getProductByPackageId(id,pdPackage.getStoreroomId(),UserUtils.getUser().getStoreroomId());
				pdPack.setPdProductList(productList);
				packageList.add(pdPack);
			}
		}
		page.setList(packageList);
		//判断出入库库房等级
		String outStoreroomId = pdPackage.getStoreroomId();
		String inStoreroomId = UserUtils.getUser().getStoreroomId();
		PdStoreroom outStoreroom = pdStoreroomService.get(outStoreroomId);
		PdStoreroom inStoreroom = pdStoreroomService.get(inStoreroomId);
		if(MinaGlobalConstants.STOREROOM_TYPE_1.equals(inStoreroom.getStoreroomType())){
			model.addAttribute("viewPermission", "1");
		}else if(inStoreroom.getStoreroomType().equals(outStoreroom.getStoreroomType())){
			model.addAttribute("viewPermission", "1");
		}else{
			model.addAttribute("viewPermission", "0");
		}
		model.addAttribute("packageList", packageList);
		model.addAttribute("pdPackage", pdPackage);
		model.addAttribute("page", page);
		model.addAttribute("ids", ids);
		return "hys/pd/pdAddPackageAlloactionBox";
	}
	
	/**
	 * 添加定数包
	 * */
	/*@RequestMapping(value = "add")
	public void add(HttpServletRequest request , HttpServletResponse response){
		//接参
		String number = request.getParameter("number");
		String name = request.getParameter("name");
		String idsAndCounts = request.getParameter("idsAndCounts");
		//定数包产品数量
		int sum = 0;
		
		//解析idsAndCounts 格式：id,count;id,count;...
		String[] idAndCount = idsAndCounts.split(";");
		String[][] ic = new String[idAndCount.length][2];
		for(int i = 0 ; i < idAndCount.length - 1 ; i ++){
			ic[i][0] = idAndCount[i].split(",")[0];
			ic[i][1] = idAndCount[i].split(",")[1];
			//计算定数包产品总量
			sum += Integer.parseInt(ic[i][1]);
		}
		
		//插入定数包头部信息并返回id
		PdPackage pdPackage = new PdPackage(); 
		pdPackage.setNumber(number);
		pdPackage.setName(name);
		pdPackage.setSum(sum);
		String packageId = pdPackageService.saveReturnId(pdPackage);
		
		//循环插入定数包产品关联表
		PdProductMPackage middle = new PdProductMPackage();
		middle.setPackageId(packageId);
		for(int i = 0 ; i < ic.length - 1 ; i ++){
			middle.setProductId(ic[i][0]);
			middle.setProductCount(ic[i][1]);
			pdProductMPackageService.save(middle);
		}
	}*/
	
	/**
	 * 查看定数包详情 /修改定数包
	 * */
	@RequestMapping(value = "detail")
	public String detail(HttpServletRequest request){
		
		String id = request.getParameter("id");
		String flag = request.getParameter("flag");
		String delKey = request.getParameter("delKey") == null ? "" : request.getParameter("delKey");
		
		//取定数包头部信息
		PdPackage pdPackage = pdPackageService.get(id);
		
		//取子列表
		List<PdProductMPackage> list = pdPackageService.getAllByPackageId(id);
		
		pdPackage.setChild(list);
		
		/*//取定数包产品id集
		PdProductMPackage pdProductMPackage = new PdProductMPackage();
		pdProductMPackage.setPackageId(id);
		List<PdProductMPackage> findList = pdProductMPackageService.findList(pdProductMPackage);
		
		//取产品列表
		List<PdProductPackageVo> productList = new ArrayList<PdProductPackageVo>();  //产品列表
		for(int i = 0 ; i < findList.size() ; i ++){
			PdProduct pdProduct = pdProductService.get(findList.get(i).getProductId());
			PdProductPackageVo pojo = new PdProductPackageVo();
			pojo.setPdProduct(pdProduct);
			pojo.setCount(findList.get(i).getProductCount());
			productList.add(pojo);
		}*/
		if(flag != "copy"){
			request.setAttribute("pdPackage", pdPackage);
		}
		request.setAttribute("delKey", delKey);
		request.setAttribute("list", list);
		request.setAttribute("flag", flag);
		
		return "hys/pd/pdPackageForm";
	}
	
	private boolean entityContains(DataEntity<?> entity, String[] idArr) {
		for (String id : idArr) {
			if(id.equals(entity.getId())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @功能描述:创建文件夹
	 */
	public static void createDir(String path) {
		File file = new File(path);
		File parent = file.getParentFile();
		if (parent != null && !parent.exists()) {
			parent.mkdirs();
		}
	}
	
	/**
	 * AJAX响应方法：
	 * 生成编码
	 * 生成条形码
	 * */
	@RequestMapping(value = "generateNumber")
	public void generateNumber(HttpServletRequest request , HttpServletResponse response){
		String generateNumber = CommonUtils.generateNumber("96");//生成编码
		String path = Global.getUserfilesBaseDir()+"images/spd/barcode/"+generateNumber+".jpg";//图片生成地址
		createDir(path);
		File generateFile = BarcodeUtils.generateFile(generateNumber, path);//条形码生成
		//封装对象
		PdBarcodeImg barcodeBean = new PdBarcodeImg();
		barcodeBean.setNumber(generateNumber);
		barcodeBean.setSite(generateFile.getPath());
		
		pdBarcodeImgService.save(barcodeBean);//写入数据库
		
		String json = JSONObject.toJSONString(barcodeBean);//转换json
		
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * AJAX请求删除
	 * */
	//@RequiresPermissions("pd:pdProductMPackage:edit")
	@RequestMapping(value = "del")
	public void del(HttpServletRequest request , HttpServletResponse response) {
		
		String id = request.getParameter("id");
		PdProductMPackage p = new PdProductMPackage();
		p.setId(id);
		pdProductMPackageService.delete(p);
		
		try {
			response.getWriter().print("ok");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 跳转到产品详情页之前保存临时信息
	 * */
	@RequestMapping(value = "packageSession")
	public void packageSession(HttpServletRequest request , HttpServletResponse response){
	//	String prodId = request.getParameter("prodId");
		String packageNumber = request.getParameter("packageNumber");
		String info = request.getParameter("info");
		
		request.getSession().setAttribute("packageNumber", packageNumber);
		request.getSession().setAttribute("packageInfo", info);
		
		try {
			response.getWriter().print("yes");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * to productDetail 保存临时数据到session
	 * */
	@RequestMapping(value = "backToPackage")
	public String backToPackage(HttpServletRequest request){
		String packageNumber = request.getSession().getAttribute("packageNumber").toString();
		String info = request.getSession().getAttribute("packageInfo").toString();

		String s1 = new String(packageNumber);
		String s2 = new String(info);
		
		request.setAttribute("packageNumber", s1);
		request.setAttribute("info", s2);
		request.getSession().removeAttribute("packageNumber");
		request.getSession().removeAttribute("info");
		request.setAttribute("flag", "save");
		return "hys/pd/pdPackageForm";
	}
	
	/**
	 * back package 取出session中临时数据并删除
	 * */
	@RequestMapping(value = "backForm")
	public void backForm(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		PdProduct pdProduct = pdProductService.get(id);
		String json = JSONObject.toJSONString(pdProduct);
		
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}