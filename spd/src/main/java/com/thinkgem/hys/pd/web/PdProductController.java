/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.entity.*;
import com.thinkgem.hys.pd.entity.excel.ProductExcel;
import com.thinkgem.hys.pd.entity.excel.ProductExcelList;
import com.thinkgem.hys.pd.service.*;
import com.thinkgem.hys.pd.web.task.SyncProductDataTask;
import com.thinkgem.hys.utils.AxisUtils;
import com.thinkgem.hys.utils.CommonUtils;
import com.thinkgem.hys.utils.HisApiUtils;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.HysFileUploadUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.UploadUtil;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.utils.SnoGerUtil;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 产品表Controller
 * @author sutianqi
 * @version 2018-03-07
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdProduct")
public class PdProductController extends BaseController {

	@Autowired
	private PdProductService pdProductService;
	@Autowired
	private PdCategoryService pdCategoryService;
	@Autowired
	private PdGroupService pdGroupService;
	@Autowired
	private PdVenderService pdVenderService;
	@Autowired
	private PdBarcodeImgService pdBarcodeImgService;
	@Autowired
	private PdProductStockService pdProductStockService;
	@Autowired
	private PdStoreroomService pdStoreroomService;
	@Autowired
	private PdPurchaseDetailService pdPurchaseDetailService;
	@Autowired
	private PdSupplierService pdSupplierService;
	@Autowired
	private PdEncodingRuleDetailService pdEncodingRuleDetailService;
	@Autowired
	private PdUnitService pdUnitService;

	@Autowired
	private PdEncodingRuleService pdEncodingRuleService;
	@Autowired
	private SyncProductDataTask syncProductDataTask;
	
	@ModelAttribute
	public PdProduct get(@RequestParam(required=false) String id) {
		PdProduct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdProductService.get(id);
		}
		if (entity == null){
			entity = new PdProduct();
		}
		return entity;
	}

	/**
	 * 收费代码弹出层
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value= "showChargeCodeBox")
	public String showChargeCodeBox(String context,HttpServletRequest request , HttpServletResponse response, Model model){

		model.addAttribute("isHisInterface", "false");
		model.addAttribute("hisChargeCodeList", null);
		model.addAttribute("context", context);
		return "hys/pd/box/pdChargeCodeBox";
	}

	@RequiresPermissions("pd:pdProduct:view")
	@RequestMapping(value = {"hisChargeCodeList", ""})
	public String hisChargeCodeList(HisChargeCode hisChargeCode, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response, Model model) {

		if(hisChargeCode == null){
			return "hys/pd/hisChargeCodeList";
		}
		if(StringUtils.isBlank(hisChargeCode.getSFCODE()) && StringUtils.isBlank(hisChargeCode.getSFNAME())){
			return "hys/pd/hisChargeCodeList";
		}

        JSONObject chargeCodeJson = HisApiUtils.queryHisChargeCode(hisChargeCode.getSFCODE(),hisChargeCode.getSFNAME());
		if(chargeCodeJson == null){
			addMessage(redirectAttributes, "HIS返回数据为空，请重新查询或联系管理员！");
			return "redirect:"+Global.getAdminPath()+"/pd/pdProduct/hisChargeCodeList?saveKey=error";
		}
		if(!MinaGlobalConstants.SUCCESS.equals(chargeCodeJson.getString("statusCode"))){
			addMessage(redirectAttributes, "查询HIS项目收费代码失败，请重新查询或联系管理员！");
			return "redirect:"+Global.getAdminPath()+"/pd/pdProduct/hisChargeCodeList?saveKey=error";
		}

		JSONArray jsonArray = chargeCodeJson.getJSONArray("data");
		List<HisChargeCode> hisChargeCodeList = JSONArray.parseArray(jsonArray.toJSONString(),HisChargeCode.class);

		model.addAttribute("hisChargeCodeList", hisChargeCodeList);
		return "hys/pd/hisChargeCodeList";

	}


	/**
	 * 产品模糊查询
	 * @param pdProduct
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value= "findList")
	public List<PdProduct> findList(PdProduct pdProduct ,HttpServletRequest request , HttpServletResponse response, Model model){
		List<PdProduct> result = pdProductService.findSelectList(pdProduct);
		return result;
	}

	/**
	 * 供应商模糊查询
	 * @param pdProduct
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value= "findSupplierList")
	public List<PdSupplier> findSupplierList(PdSupplier supplier ,HttpServletRequest request , HttpServletResponse response, Model model){
		List<PdSupplier> supList = pdSupplierService.findSelectList(supplier);
		for (PdSupplier pdSupplier:supList){
			if(StringUtils.isNotEmpty(pdSupplier.getPinyin())){
				pdSupplier.setNameAndpinyin(pdSupplier.getSupplierName()+"("+pdSupplier.getPinyin()+")");
			}else{
				pdSupplier.setNameAndpinyin(pdSupplier.getSupplierName());
			}
		}
		return supList;
	}


	/**
	 * 产品列表
	 * */
	@RequiresPermissions("pd:pdProduct:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdProduct pdProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		//批量导入错误行号
		String importKey = request.getParameter("importKey")==null?"":request.getParameter("importKey");
		//保存错误key
		String saveKey = request.getParameter("saveKey")==null?"":request.getParameter("saveKey");
		//HIS未维护代码
		String chargeCode = request.getParameter("chargeCode");
		if(chargeCode!=null && !chargeCode.equals("")){
			pdProduct.setChargeCode(chargeCode);
			request.setAttribute("chargeCode", "a0");
		}
		pdProduct.setIsUrgent("a0");
		Page<PdProduct> page = pdProductService.basicFindListOne(new Page<PdProduct>(request, response), pdProduct);
		final String ifPlatform = Global.getConfig("IF_PLATFORM");//是否有中心平台标识1=没有，0=有
		
		//获取产品分类
		//List<PdCategory> pdCategoryList = pdCategoryService.findList(new PdCategory());2019年7月15日11:13:22取消自主查询
		//获取产品组别
		//List<PdGroup> pdGroupList = pdGroupService.findList(new PdGroup());2019年7月15日11:13:22取消自主查询
		//获取生产厂家
		//List<PdVender> pdVenderList = pdVenderService.findList(new PdVender());2019年7月15日11:13:22取消自主查询

		//model.addAttribute("pdCategoryList", pdCategoryList);
		//model.addAttribute("pdGroupList", pdGroupList);
		//model.addAttribute("pdVenderList", pdVenderList);
		model.addAttribute("page", page);
		model.addAttribute("pdProduct", pdProduct);
		model.addAttribute("ifPlatform", ifPlatform);
		pdProduct.setPage(null);
		model.addAttribute("exportDataList", JsonMapper.toJsonString(pdProductService.findList(pdProduct)));
		request.setAttribute("importKey", importKey);
		request.setAttribute("saveKey", saveKey);
		return "hys/pd/pdProductList";
	}

	/**
	 * 紧急产品列表
	 * */
	@RequestMapping(value = "urgentList")
	public String urgentList(PdProduct pdProduct, HttpServletRequest request, HttpServletResponse response, Model model){
		//保存错误key
		String saveKey = request.getParameter("saveKey")==null?"":request.getParameter("saveKey");
		
		pdProduct.setIsUrgent("a1");
		
		Page<PdProduct> page = pdProductService.basicFindPage(new Page<PdProduct>(request, response), pdProduct); 
		//获取产品分类
		List<PdCategory> pdCategoryList = pdCategoryService.findList(new PdCategory());
		//获取产品组别
		List<PdGroup> pdGroupList = pdGroupService.findList(new PdGroup());
		//获取生产厂家
		List<PdVender> pdVenderList = pdVenderService.findList(new PdVender());
		
		model.addAttribute("pdProduct", pdProduct);
		model.addAttribute("pdCategoryList", pdCategoryList);
		model.addAttribute("pdGroupList", pdGroupList);
		model.addAttribute("pdVenderList", pdVenderList);
		model.addAttribute("page", page);
		request.setAttribute("saveKey", saveKey);
		return "hys/pd/pdUrgentProductList";
	}
	
	@RequiresPermissions("pd:pdProduct:view")
	@RequestMapping(value = "form")
	public String form(PdProduct pdProduct, Model model, HttpServletRequest request) {
		//紧急产品标记
		String flag = request.getParameter("flag");
		String isUrgent = request.getParameter("isUrgent") != null ? request.getParameter("isUrgent") : "";
		
		//获取产品分类
		List<PdCategory> pdCategoryList = pdCategoryService.findList(new PdCategory());
		//获取产品组别
		List<PdGroup> pdGroupList = pdGroupService.findList(new PdGroup());
		//获取生产厂家
		List<PdVender> pdVenderList = pdVenderService.findList(new PdVender());
		//单位
		List<PdUnit> pdUnitList = pdUnitService.findList(new PdUnit());

		if(pdVenderList != null && pdVenderList.size() > 0){
            for (PdVender pdVender : pdVenderList) {
                pdVender.setNameAndpinyin(
                        pdVender.getPinyin()!=null && !"".equals(pdVender.getPinyin())
                                ? pdVender.getName()+"("+pdVender.getPinyin()+")" : pdVender.getName());
            }
        }
		if(flag.equals("update")){
			pdProduct = pdProductService.get(pdProduct.getId());
		}
		model.addAttribute("pdProduct", pdProduct);
		model.addAttribute("pdCategoryList", pdCategoryList);
		model.addAttribute("pdGroupList", pdGroupList);
		model.addAttribute("pdVenderList", pdVenderList);
		model.addAttribute("pdUnitList", pdUnitList);
		request.setAttribute("flag", flag);
		request.setAttribute("isUrgent", isUrgent);
		return "hys/pd/pdProductForm";
	}
	
	//跳转到修改页面
	@RequestMapping(value = "toUpdate")
	public String toUpdate(PdProduct pdProduct, Model model , HttpServletRequest request){
		
		String flag = request.getParameter("flag");
		String packageFlag = request.getParameter("packageFlag") == null ? "no" : request.getParameter("packageFlag");
		String isUrgent = request.getParameter("isUrgent");
		//获取产品分类
		List<PdCategory> pdCategoryList = pdCategoryService.findList(new PdCategory());
		//获取产品组别
		List<PdGroup> pdGroupList = pdGroupService.findList(new PdGroup());
		//获取生产厂家
		List<PdVender> pdVenderList = pdVenderService.findList(new PdVender());
		//单位
		List<PdUnit> pdUnitList = pdUnitService.findList(new PdUnit());

        if(pdVenderList != null && pdVenderList.size() > 0){
            for (PdVender pdVender : pdVenderList) {
                pdVender.setNameAndpinyin(
                        pdVender.getPinyin()!=null && !"".equals(pdVender.getPinyin())
                                ? pdVender.getName()+"("+pdVender.getPinyin()+")" : pdVender.getName());
            }
        }
		pdProduct = pdProductService.getOne(pdProduct.getId());
		model.addAttribute(pdProduct);
		model.addAttribute("pdCategoryList", pdCategoryList);
		model.addAttribute("pdGroupList", pdGroupList);
		model.addAttribute("pdVenderList", pdVenderList);
		model.addAttribute("pdUnitList", pdUnitList);
		request.setAttribute("flag",flag);
		request.setAttribute("packageFlag", packageFlag);
		request.setAttribute("isUrgent",isUrgent);
		return "hys/pd/pdProductUpdate";
	}
	
	//跳转到修改页面
		@RequestMapping(value = "toUpdateFromPackage")
		public String toUpdateFromPackage(PdProduct pdProduct, HttpServletRequest request){
			
			String flag = request.getParameter("flag");
			String packageFlag = request.getParameter("packageFlag") == null ? "no" : request.getParameter("packageFlag");
			String isUrgent = request.getParameter("isUrgent");
			//获取产品分类
			List<PdCategory> pdCategoryList = pdCategoryService.findList(new PdCategory());
			//获取产品组别
			List<PdGroup> pdGroupList = pdGroupService.findList(new PdGroup());
			//获取生产厂家
			List<PdVender> pdVenderList = pdVenderService.findList(new PdVender());
			//单位
			List<PdUnit> pdUnitList = pdUnitService.findList(new PdUnit());

			if(pdVenderList != null && pdVenderList.size() > 0){
				for (PdVender pdVender : pdVenderList) {
					pdVender.setNameAndpinyin(
							pdVender.getPinyin()!=null && !"".equals(pdVender.getPinyin())
									? pdVender.getName()+"("+pdVender.getPinyin()+")" : pdVender.getName());
				}
			}
					
			pdProduct = pdProductService.get(pdProduct.getId());
			request.setAttribute("pdProduct",pdProduct);
			request.setAttribute("pdCategoryList", pdCategoryList);
			request.setAttribute("pdGroupList", pdGroupList);
			request.setAttribute("pdVenderList", pdVenderList);
			request.setAttribute("pdUnitList", pdUnitList);
			request.setAttribute("flag",flag);
			request.setAttribute("packageFlag", packageFlag);
			request.setAttribute("isUrgent",isUrgent);
			return "hys/pd/pdProductUpdate";
		}
	
	
	
	/**
	 * 修改产品
	 * */
	@RequestMapping(value = "update")
	public String update(PdProduct pdProduct,@RequestParam MultipartFile[] imgAuthSiteUp,
						@RequestParam MultipartFile[] imgRegisterSite1Up,@RequestParam MultipartFile[] imgRegisterSite2Up,
						@RequestParam MultipartFile[] imgRegisterSite3Up,@RequestParam MultipartFile[] imgLicenseSite1Up,
						@RequestParam MultipartFile[] imgLicenseSite2Up,@RequestParam MultipartFile[] imgLicenseSite3Up,
						@RequestParam MultipartFile[] imgLicenseSite4Up,@RequestParam MultipartFile[] imgProduct1Up,
						@RequestParam MultipartFile[] imgProduct2Up,@RequestParam MultipartFile[] imgProduct3Up,Model model,MultipartHttpServletRequest request){
		
		
		
		//存入图片
				if(!isImgEmpty(imgAuthSiteUp)){//产品授权书图片
			String filePath = saveFile(imgAuthSiteUp);
			if(pdProduct.getImgAuthSite()!=null && !"".equals(pdProduct.getImgAuthSite())){
				//先删除再更新
				FileUtils.deleteFile(userfilesBasedir+"\\"+pdProduct.getImgAuthSite());
			}
			pdProduct.setImgAuthSite(filePath);
		}
		if(!isImgEmpty(imgRegisterSite1Up)){//产品注册证图片
			String filePath = saveFile(imgRegisterSite1Up);
			if(pdProduct.getImgRegisterSite1()!=null && !"".equals(pdProduct.getImgRegisterSite1())){
				//先删除再更新
				FileUtils.deleteFile(userfilesBasedir+"\\"+pdProduct.getImgRegisterSite1());
			}
			pdProduct.setImgRegisterSite1(filePath);
		}
		if(!isImgEmpty(imgRegisterSite2Up)){//产品注册证图片
			String filePath = saveFile(imgRegisterSite2Up);
			if(pdProduct.getImgRegisterSite2()!=null && !"".equals(pdProduct.getImgRegisterSite2())){
				//先删除再更新
				FileUtils.deleteFile(userfilesBasedir+"\\"+pdProduct.getImgRegisterSite2());
			}
			pdProduct.setImgRegisterSite2(filePath);
		}
		if(!isImgEmpty(imgRegisterSite3Up)){//产品注册证图片
			String filePath = saveFile(imgRegisterSite3Up);
			if(pdProduct.getImgRegisterSite3()!=null && !"".equals(pdProduct.getImgRegisterSite3())){
				//先删除再更新
				FileUtils.deleteFile(userfilesBasedir+"\\"+pdProduct.getImgRegisterSite3());
			}
			pdProduct.setImgRegisterSite3(filePath);
		}
		if(!isImgEmpty(imgLicenseSite1Up)){//供应商营业执照图片
			String filePath = saveFile(imgLicenseSite1Up);
			if(pdProduct.getImgLicenseSite1()!=null && !"".equals(pdProduct.getImgLicenseSite1())){
				//先删除再更新
				FileUtils.deleteFile(userfilesBasedir+"\\"+pdProduct.getImgLicenseSite1());
			}
			pdProduct.setImgLicenseSite1(filePath);
		}
		if(!isImgEmpty(imgLicenseSite2Up)){//供应商经营许可证图片
			String filePath = saveFile(imgLicenseSite2Up);
			if(pdProduct.getImgLicenseSite2()!=null && !"".equals(pdProduct.getImgLicenseSite2())){
				//先删除再更新
				FileUtils.deleteFile(userfilesBasedir+"\\"+pdProduct.getImgLicenseSite2());
			}
			pdProduct.setImgLicenseSite2(filePath);
		}
		if(!isImgEmpty(imgLicenseSite3Up)){//生产企业营业执照图片
			String filePath = saveFile(imgLicenseSite3Up);
			if(pdProduct.getImgLicenseSite3()!=null && !"".equals(pdProduct.getImgLicenseSite3())){
				//先删除再更新
				FileUtils.deleteFile(userfilesBasedir+"\\"+pdProduct.getImgLicenseSite3());
			}
			pdProduct.setImgLicenseSite3(filePath);
		}
		if(!isImgEmpty(imgLicenseSite4Up)){//生产企业生产许可证图片
			String filePath = saveFile(imgLicenseSite4Up);
			if(pdProduct.getImgLicenseSite4()!=null && !"".equals(pdProduct.getImgLicenseSite4())){
				//先删除再更新
				FileUtils.deleteFile(userfilesBasedir+"\\"+pdProduct.getImgLicenseSite4());
			}
			pdProduct.setImgLicenseSite4(filePath);
		}
		if(!isImgEmpty(imgProduct1Up)){//产品图片
			String filePath = saveFile(imgProduct1Up);
			if(pdProduct.getImgProduct1()!=null && !"".equals(pdProduct.getImgProduct1())){
				//先删除再更新
				FileUtils.deleteFile(userfilesBasedir+"\\"+pdProduct.getImgProduct1());
			}
			pdProduct.setImgProduct1(filePath);
		}
		if(!isImgEmpty(imgProduct2Up)){//产品图片
			String filePath = saveFile(imgProduct2Up);
			if(pdProduct.getImgProduct2()!=null && !"".equals(pdProduct.getImgProduct2())){
				//先删除再更新
				FileUtils.deleteFile(userfilesBasedir+"\\"+pdProduct.getImgProduct2());
			}
			pdProduct.setImgProduct2(filePath);
		}
		if(!isImgEmpty(imgProduct3Up)){//产品图片
			String filePath = saveFile(imgProduct3Up);
			if(pdProduct.getImgProduct3()!=null && !"".equals(pdProduct.getImgProduct3())){
				//先删除再更新
				FileUtils.deleteFile(userfilesBasedir+"\\"+pdProduct.getImgProduct3());
			}
			pdProduct.setImgProduct3(filePath);
		}
				
				String isUrgent = request.getParameter("isUrgent");
				pdProductService.update(pdProduct);
				model.addAttribute(pdProduct);
		
		if(isUrgent.equals("urgent")){
			return "redirect:"+Global.getAdminPath()+"/pd/pdProduct/urgentList";
		}
		return "redirect:"+Global.getAdminPath()+"/pd/pdProduct/?list";
	}
	
	/**
	 * 更新收费代码
	 * */
	@RequestMapping(value = "updateChargeCode")
	public String updateChargeCode( HttpServletRequest request){
		
		String flag = request.getParameter("flag") != null ? request.getParameter("flag") : "";
		
		String ids = request.getParameter("ids");
		ids = ids.substring(0,ids.length()-1);
		String[] id = ids.split(",");
		
		String chargeCode = request.getParameter("chargeCode");
		PdProduct pdProduct = new PdProduct();
		for(int i = 0 ; i < id.length ; i ++){
			pdProduct.setId(id[i]);
			pdProduct.setChargeCode(chargeCode);
			pdProductService.updateChargeCode(pdProduct);
		}
		
		if(flag.equals("urgent")){
			return "redirect:"+Global.getAdminPath()+"/pd/pdProduct/urgentList";
		}
		return "redirect:"+Global.getAdminPath()+"/pd/pdProduct/?list";
	}
	
	/**
	 * 添加产品
	 * */
	//@RequiresPermissions("pd:pdProduct:edit")
	@RequestMapping(value = "save",method=RequestMethod.POST)
	public String save(PdProduct pdProduct,@RequestParam MultipartFile[] imgAuthSiteUp,
			@RequestParam MultipartFile[] imgRegisterSite1Up,@RequestParam MultipartFile[] imgRegisterSite2Up,
			@RequestParam MultipartFile[] imgRegisterSite3Up,@RequestParam MultipartFile[] imgLicenseSite1Up,
			@RequestParam MultipartFile[] imgLicenseSite2Up,@RequestParam MultipartFile[] imgLicenseSite3Up,
			@RequestParam MultipartFile[] imgLicenseSite4Up,@RequestParam MultipartFile[] imgProduct1Up,
			@RequestParam MultipartFile[] imgProduct2Up,@RequestParam MultipartFile[] imgProduct3Up,Model model, RedirectAttributes redirectAttributes, MultipartHttpServletRequest request) {
		if (!beanValidator(model, pdProduct)){
			return form(pdProduct, model, request);
		}
		String flag = request.getParameter("flag")!=null?request.getParameter("flag"):"";
		if(flag.equals("urgent")){
			pdProduct.setIsUrgent("a1");
		}
		
		boolean repetitive = pdProductService.eliminateRepetitive(pdProduct);//重复编号校验
		if(!repetitive && !flag.equals("urgent")){
			addMessage(redirectAttributes, "编号不可重复，保存失败");
			return "redirect:"+Global.getAdminPath()+"/pd/pdProduct/?saveKey=error";
		}
		if(!repetitive && flag.equals("urgent")){
			addMessage(redirectAttributes, "编号不可重复，保存失败");
			return "redirect:"+Global.getAdminPath()+"/pd/pdProduct/urgentList?saveKey=";
		}

		boolean bol = isInteger(pdProduct.getNumber());
		if(!bol){
			addMessage(redirectAttributes, "编号格式不正确，请输入正确的类型");
			return "redirect:"+Global.getAdminPath()+"/pd/pdProduct/?saveKey=error";
		}
		//存入图片
		if(!isImgEmpty(imgAuthSiteUp)){//产品授权书图片
			pdProduct.setImgAuthSite(saveFile(imgAuthSiteUp));
		}
		if(!isImgEmpty(imgRegisterSite1Up)){//产品注册证图片
			pdProduct.setImgRegisterSite1(saveFile(imgRegisterSite1Up));
		}
		if(!isImgEmpty(imgRegisterSite2Up)){//产品注册证图片
			pdProduct.setImgRegisterSite2(saveFile(imgRegisterSite2Up));
		}
		if(!isImgEmpty(imgRegisterSite3Up)){//产品注册证图片
			pdProduct.setImgRegisterSite3(saveFile(imgRegisterSite3Up));
		}
		if(!isImgEmpty(imgLicenseSite1Up)){//供应商营业执照图片
			pdProduct.setImgLicenseSite1(saveFile(imgLicenseSite1Up));
		}
		if(!isImgEmpty(imgLicenseSite2Up)){//供应商经营许可证图片
			pdProduct.setImgLicenseSite2(saveFile(imgLicenseSite2Up));
		}
		if(!isImgEmpty(imgLicenseSite3Up)){//生产企业营业执照图片
			pdProduct.setImgLicenseSite3(saveFile(imgLicenseSite3Up));
		}
		if(!isImgEmpty(imgLicenseSite4Up)){//生产企业生产许可证图片
			pdProduct.setImgLicenseSite4(saveFile(imgLicenseSite4Up));
		}
		if(!isImgEmpty(imgProduct1Up)){//产品图片
			pdProduct.setImgProduct1(saveFile(imgProduct1Up));
		}
		if(!isImgEmpty(imgProduct2Up)){//产品图片
			pdProduct.setImgProduct2(saveFile(imgProduct2Up));
		}
		if(!isImgEmpty(imgProduct3Up)){//产品图片
			pdProduct.setImgProduct3(saveFile(imgProduct3Up));
		}
		
		pdProductService.save(pdProduct);
		addMessage(redirectAttributes, "保存产品表成功");
		
		if(flag.equals("urgent")){
			return "redirect:"+Global.getAdminPath()+"/pd/pdProduct/urgentList";
		}
		
		return "redirect:"+Global.getAdminPath()+"/pd/pdProduct/?repage";
	}

	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}
	
	//@RequiresPermissions("pd:pdProduct:edit")
	@RequestMapping(value = "delete")
	public void delete(PdProduct pdProduct, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
	//	String isUrgent = request.getParameter("isUrgent") != null ? request.getParameter("isUrgent") : "";
		
		String id = request.getParameter("id");
		PdProductStock productStock = new PdProductStock();
		productStock.setProductId(id);
		
		List<PdProductStock> findList = pdProductStockService.findList(productStock);
		
		int stockFlag = 0;	//该产品的总库存标识
		
		for(int i = 0 ; i < findList.size() ; i ++){
			Integer stockNum = findList.get(i).getStockNum();
			stockFlag += stockNum ;
		}
		
		PdPurchaseDetail pdPurchaseDetail = new PdPurchaseDetail();
		pdPurchaseDetail.setProdId(id);
		List<PdPurchaseDetail> findAllList = pdPurchaseDetailService.findList(pdPurchaseDetail);
		int pur = 0;
		if(findAllList.size() != 0){
			pur = 1;
		}
		
		try {//响应
			PrintWriter writer = response.getWriter();
			
			if(stockFlag == 0){
				if(pur == 0){
					pdProductService.delete(pdProduct);
					writer.write("yes");
				}
			}else if(stockFlag > 0 || pur > 0){
				writer.write("no");
			}else{
				writer.write("what");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addMessage(redirectAttributes, "删除产品表成功");
		/*if(isUrgent.equals("urgent")){
			return "redirect:"+Global.getAdminPath()+"/pd/pdProduct/urgentList";
		}*/
		//return "redirect:"+Global.getAdminPath()+"/pd/pdProduct/?repage";
	}

	/**
	 * 采购申请-添加产品
	 * @param pdProduct
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "chooseProductList")
	public String chooseProductList(PdProduct pdProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		String storeroomId = UserUtils.getUser().getStoreroomId();
		pdProduct.setStoreroomId(storeroomId);
		if ("fromApplyOrder".equals(pdProduct.getRemarks())) {//从申领单添加产品
			String firstLevelStoreroomId = pdStoreroomService.findFirstStoreroom();
			StringBuilder sb = new StringBuilder();
			sb.append(" AND a.id IN ( ")
			  .append(" SELECT product_id FROM pd_product_stock_total WHERE storeroom_id = '"+firstLevelStoreroomId+"' AND stock_num > 0 ")
			  .append(" ) ");
			pdProduct.getSqlMap().put("dsf", sb.toString());
		}
		Page<PdProduct> page = pdProductService.findPage(new Page<PdProduct>(request, response), pdProduct); 
		model.addAttribute("page", page);
		model.addAttribute("pdProduct", pdProduct);
        model.addAttribute("isLimitDown", pdProduct.getIsLimitDown());
		return "hys/pd/pdAddProBox";
	}
	/**
	 * 库房-添加产品
	 * @param pdProduct
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "chooseProductListForStoreroom")
	public String chooseProductListForStoreroom(PdProduct pdProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdProduct> page = pdProductService.findPage(new Page<PdProduct>(request, response), pdProduct); 
		model.addAttribute("page", page);
		return "hys/pd/pdAddProBoxForStoreroom";
	}
	
	/**
	 * 判断图片非空
	 * @param imgs
	 * @return
	 */
	private boolean isImgEmpty(MultipartFile[] imgs){
		for (int i=0; i<imgs.length; i++) {
			MultipartFile img = imgs[i];
			if(img.isEmpty()){
				return true;
			}
		}
		return false;
	}
	
	// 保存多个图片
	private String saveFile(MultipartFile[] imgs) {
		StringBuilder urls = new StringBuilder("");
		for (int i=0; i<imgs.length; i++) {
			MultipartFile img = imgs[i];
			String extName = img.getOriginalFilename().substring(img.getOriginalFilename().lastIndexOf("."));
			Long longTime = new Date().getTime();// 获取随机名称// 取时间长整型值
			String serialNumber = SnoGerUtil.getSerialNumber(5);
			String fullName = longTime + serialNumber + extName;
			String fullPath = Global.getUserfilesBaseDir()+UploadUtil.IMAGE_DIR +"spd/"+ fullName;
			String  url = UploadUtil.IMAGE_DIR+"spd/"+ fullName;//文件上传的url
			try {
				createDir(fullPath);
				img.transferTo(new File(fullPath));
				if(0==i){
					urls.append(url);
				}else{
					urls.append("&&&"+url);
				}
			} catch (IllegalStateException e) {
				e.printStackTrace();
				return  null;
			} catch (IOException e) {
				e.printStackTrace();
				return  null;
			}
		}
		return urls.toString();
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
	 * AJAX请求仓库列表
	 * */
	@RequestMapping(value = "scanCode")
	public void scanCode(HttpServletRequest request , HttpServletResponse response){
		String Barcode1 = request.getParameter("Barcode1");
		String Barcode2 = request.getParameter("Barcode2");
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			resultMap = pdProductService.getScanCode(Barcode1,Barcode2);
		}catch(Exception e){
			resultMap.put("code","500");
			resultMap.put("msg","解析失败，请检查绑定的规则是否正确");
			e.printStackTrace();
		}

		String json = JSONObject.toJSONString(resultMap);

		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	/**
	 * AJAX响应方法：
	 * 生成编码
	 * 生成条形码 Code93 系统生成
	 * */
	@RequestMapping(value = "generateNumber")
	public void generateNumber(HttpServletRequest request , HttpServletResponse response){
		String generateNumber = CommonUtils.generateNumber("93");//生成编码
		PdBarcodeImg barcodeBean = new PdBarcodeImg();
		barcodeBean.setNumber(generateNumber);
		pdBarcodeImgService.save(barcodeBean);//写入数据库
		String json = JSONObject.toJSONString(barcodeBean);//转换json
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		String generateNumber = CommonUtils.generateNumber("93");//生成编码
		String fullPath = Global.getUserfilesBaseDir()+UploadUtil.IMAGE_DIR+"spd/barcode/"+generateNumber+".jpg";//图片生成地址
		String path = UploadUtil.IMAGE_DIR+"spd/barcode/"+generateNumber+".jpg";
		createDir(fullPath);
		File generateFile = BarcodeUtils.generateFile(generateNumber, fullPath);//条形码生成
		
	*//*	FileWriter fw;
		try {
			fw = new FileWriter(generateFile.getAbsoluteFile());
			 BufferedWriter bw = new BufferedWriter(fw);
			   bw.write(path);
			   bw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*//*
		  
		
		//封装对象
		PdBarcodeImg barcodeBean = new PdBarcodeImg();
		barcodeBean.setNumber(generateNumber);
		barcodeBean.setSite(path.replace("\\","/"));
		
		pdBarcodeImgService.save(barcodeBean);//写入数据库
		
		String json = JSONObject.toJSONString(barcodeBean);//转换json
		
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	
	/**
	 * AJAX响应方法：
	 * 生成模板
	 * */
	@RequestMapping(value = "generateTemplate")
	public void generateTemplate(HttpServletRequest request , HttpServletResponse response){
		String [] head = {"产品编号","产品名称","规格","型号","单位","产品权限","产品类型","产品分类","产品组别","生产厂家","产品进价","产品出价","供应商","注册证","是否计费","备注"};
		ExportExcel exportExcel = new ExportExcel("产品列表",head);
		try {
			response.setCharacterEncoding("utf-8");
			exportExcel.write(response, "产品列表.xlsx");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * AJAX响应方法：
	 * 导出全部数据
	 * */
	@RequestMapping(value = "exportData")
	public void exportData(ProductExcelList productExcelList , HttpServletRequest request , HttpServletResponse response){
		//String head = request.getParameter("head");
		
		//String[] split = head.split(",");

		ExportExcel exportExcel = new ExportExcel("产品列表",ProductExcel.class);
		
		List<ProductExcel> list = productExcelList.getList();
		exportExcel.setDataList(list);
		try {
			exportExcel.write(response, "产品列表.xlsx");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * AJAX响应方法：
	 * 导入数据
	 * */
	@RequestMapping(value = "importData",method=RequestMethod.POST)
	public String importData(@RequestParam MultipartFile[] file,RedirectAttributes redirectAttributes, MultipartHttpServletRequest request , HttpServletResponse response){

	//	MultipartFile multipartFile = request.getFile("file");
		
		Map<String, MultipartFile> fileMap = request.getFileMap();
		MultipartFile multipartFile = fileMap.get("file");
		
		String originalFilename = multipartFile.getOriginalFilename();
		String fullUrl = "";
		try {
			fullUrl = HysFileUploadUtils.uploadAndReturnFullUrl(multipartFile, "excel");
			
		} catch (IllegalStateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(StringUtils.isBlank(originalFilename) || !originalFilename.endsWith(".xls") && !originalFilename.endsWith(".xlsx")){
			
		}else{
			try {
				ImportExcel ei = new ImportExcel(fullUrl, 1, 0);
				List<PdProduct> dataList = ei.getDataList(PdProduct.class);
				if(dataList!=null && dataList.size()>0){
					//查询所有的产品
					List<PdProduct> pdProducts = pdProductService.findList(new PdProduct());
					boolean bl = true;
					for(int i=0;i<dataList.size();i++){
						PdProduct pdProduct = dataList.get(i);
						//校验产品名称
						if(StringUtils.isEmpty(pdProduct.getNumber())){
							pdProduct.setNumber(CommonUtils.generateNumber("93"));//生成编码
						}else{
							if(checkNumber(pdProducts,pdProduct)){
								pdProducts.add(pdProduct);
							}else{
								addMessage(redirectAttributes, "导入失败,第"+(i+3)+"行产品编号不能重复");
								bl = false;
								break;
							}
						}
						//校验产品名称
						if(StringUtils.isEmpty(pdProduct.getName())){
							addMessage(redirectAttributes, "导入失败,第"+(i+3)+"行产品名称不能为空");
							bl = false;
							break;
						}
						//校验规格
						if(StringUtils.isEmpty(pdProduct.getSpec())){
							addMessage(redirectAttributes, "导入失败,第"+(i+3)+"行规格不能为空");
							bl = false;
							break;
						}
						//校验型号 modified by jiangxz 20190906 丰城人民医院需求，型号非必填
						/*if(StringUtils.isEmpty(pdProduct.getVersion())){
							addMessage(redirectAttributes, "导入失败,第"+(i+3)+"行型号不能为空");
							bl = false;
							break;
						}*/
						//校验单位
						if(StringUtils.isEmpty(pdProduct.getUnit())){
							addMessage(redirectAttributes, "导入失败,第"+(i+3)+"行单位不能为空");
							bl = false;
							break;
						}
						//校验产品权限
						String powerBl = checkPower(pdProduct);
						if("".equals(powerBl)){
							addMessage(redirectAttributes, "导入失败,第"+(i+3)+"行产品权限填写不正确");
							bl = false;
							break;
						}else{
							pdProduct.setPower(powerBl);
						}
						//校验产品类型
						String typeBl = checkType(pdProduct);
						if("".equals(typeBl)){
							addMessage(redirectAttributes, "导入失败,第"+(i+3)+"行产品类型填写不正确");
							bl = false;
							break;
						}else{
							pdProduct.setType(typeBl);
						}
						//校验产品分类
						String categoryBl = checkCategory(pdProduct);
						if("".equals(categoryBl)){
							addMessage(redirectAttributes, "导入失败,第"+(i+3)+"行产品分类填写不正确");
							bl = false;
							break;
						}else{
							pdProduct.setCategoryId(categoryBl);
						}
						//校验产品组别
						String gropBl = checkGroup(pdProduct);
						if("".equals(gropBl)){
							addMessage(redirectAttributes, "导入失败,第"+(i+3)+"行产品组别填写不正确");
							bl = false;
							break;
						}else{
							pdProduct.setGroupId(gropBl);
						}
						//校验生产厂家
						String venderBl = checkVender(pdProduct);
						if("".equals(venderBl)){
							addMessage(redirectAttributes, "导入失败,第"+(i+3)+"生产厂家填写不正确");
							bl = false;
							break;
						}else{
							pdProduct.setVenderId(venderBl);
						}
						//校验产品进价
						if(!isMoney(pdProduct.getPruPrice())){
							addMessage(redirectAttributes, "导入失败,第"+(i+3)+"行产品进价格式不正确");
							bl = false;
							break;
						}
						//校验产品出价 modified by jiangxz 20190906 丰城人民医院需求，产品出价非必填
						if(pdProduct.getSellingPrice() != null && !isMoney(pdProduct.getSellingPrice())){
							addMessage(redirectAttributes, "导入失败,第"+(i+3)+"行产品出价格式不正确");
							bl = false;
							break;
						}
						//校验供应商
						String supplierBl = checkSupplier(pdProduct);
						if("error".equals(supplierBl)){
							addMessage(redirectAttributes, "导入失败,第"+(i+3)+"行供应商填写不正确");
							bl = false;
							break;
						}else{
							pdProduct.setSupplierId(supplierBl);
						}
						//校验注册证  modified by jiangxz 20190906 丰城人民医院需求，注册证非必填
						/*if(StringUtils.isEmpty(pdProduct.getRegistration())){
							addMessage(redirectAttributes, "导入失败,第"+(i+3)+"行注册证不能为空");
							bl = false;
							break;
						}*/
                        //校验是否计费
                        String isCharge = checksCharge(pdProduct);
                        if("".equals(isCharge)){
                            addMessage(redirectAttributes, "导入失败,第"+(i+3)+"行是否计费不能为空");
                            bl = false;
                            break;
                        }
						//校验备注
						if(!checkRemark(pdProduct.getDescription())){
							addMessage(redirectAttributes, "导入失败,第"+(i+3)+"行备注超过字数限制");
							bl = false;
							break;
						}
					}
					if(bl){
						//批量添加产品
						if(pdProductService.saveBatchPdSupplier(dataList)){
							addMessage(redirectAttributes, "批量导入成功");	
						};
					}
				}else{
					addMessage(redirectAttributes, "批量导入失败,没有数据");
				}
			} catch (Exception e) {
				addMessage(redirectAttributes, "批量导入失败");
				e.printStackTrace();
			}
			
		}
		
		return "redirect:"+Global.getAdminPath()+"/pd/pdProduct/?repage";
	}
	
	/**
	 * AJAX响应方法：
	 * 产品分类列表
	 * */
	@RequestMapping(value="categoryList")
	public void categoryList(HttpServletRequest request , HttpServletResponse response){
		//获取产品分类
		List<PdCategory> pdCategoryList = pdCategoryService.findList(new PdCategory());
		
		String json = JSONObject.toJSONString(pdCategoryList);
		
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 新增收费代码
	 * @param productId
	 * @param chargeCode
	 * @return
	 */
	@RequestMapping(value = "addChargeCode", method=RequestMethod.POST)
	@ResponseBody
	public JSONObject addChargeCode(String productId, String chargeCode){
		JSONObject json = new JSONObject();
		try {
			PdProduct pdProduct = new PdProduct();
			pdProduct.setId(productId);
			pdProduct.setChargeCode(chargeCode);
			pdProductService.updateChargeCode(pdProduct);
			json.put("code", "200");
		} catch (Exception e) {
			e.printStackTrace();
			json.put("code", "-200");
		}
		return json;
	}
	
	/**
	 * 产品批量添加供应商
	 * @param ids
	 * @param supplierId
	 * @param supplierName
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "addSuppliers")
	public String addSupplers(String ids, String supplierId, String supplierName, RedirectAttributes redirectAttributes) {
		try {
			supplierName = URLDecoder.decode(supplierName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		pdProductService.updateSupplier(ids, supplierId, supplierName);
		addMessage(redirectAttributes, "添加供应商成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdProduct/?repage";
	}

	/**
	 * 产品编号重复校验
	 * @param number
	 * @return
	 */
	@RequestMapping(value = "eliminateRepetitive",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject eliminateRepetitive(String number) {

		JSONObject json = new JSONObject();
		boolean bol = isInteger(number);
		if(!bol){
			json.put("message", "产品编号格式不正确");
			json.put("code", "500");
			return json;
		}
		PdProduct pdProduct = new PdProduct();
		pdProduct.setNumber(number);
		boolean repetitive = pdProductService.eliminateRepetitive(pdProduct);//重复编号校验

		if(!repetitive){
			json.put("message", "产品编号不可重复");
			json.put("code", "500");
		}else{
			json.put("message", "");
			json.put("code", "200");
		}
		return json;
	}

	/**
	 * 校验名称是否重复
	 * @param pdProducts
	 * @param pdProduct
	 * @return
	 */
	private boolean checkNumber(List<PdProduct> pdProducts, PdProduct pdProduct) {
		for (PdProduct p : pdProducts) {
			if(p.getNumber().equals(pdProduct.getNumber().trim())){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 校验产品组别
	 * @param pdProduct
	 * @return
	 */
	private String checkGroup(PdProduct pdProduct) {
		PdGroup pdGroup = new PdGroup();
		pdGroup.setName(pdProduct.getGroupName().trim());
		try{
			pdGroup = pdGroupService.findByName(pdGroup);
		}catch(Exception e){
			e.printStackTrace();
			return StringUtils.EMPTY;
		}
		if(pdGroup!=null){
			return pdGroup.getId();
		}else{
			return StringUtils.EMPTY;
		}
	}
	
	/**
	 * 校验产品类型
	 * @param pdProduct
	 * @return
	 */
	private String checkType(PdProduct pdProduct) {
		if(MinaGlobalConstants.CATEGORY_TYPE_NAME_HIGH.equals(pdProduct.getType().trim())){//高值耗材
			return MinaGlobalConstants.CATEGORY_TYPE_HIGH;
		}else if(MinaGlobalConstants.CATEGORY_TYPE_NAME_LOW.equals(pdProduct.getType().trim())){
			return MinaGlobalConstants.CATEGORY_TYPE_LOW;
		}
		return StringUtils.EMPTY;
	}
	
	/**
	 * 校验产品分类是否输入正确
	 * @param pdProduct
	 * @return
	 */
	private String checkCategory(PdProduct pdProduct) {
		PdCategory pdCategory = new PdCategory();
		pdCategory.setName(pdProduct.getCategoryName().trim());
		try{
			pdCategory = pdCategoryService.findByName(pdCategory);
		}catch(Exception e){
			e.printStackTrace();
			return StringUtils.EMPTY;
		}
		if(pdCategory!=null){
			return pdCategory.getId();
		}else{
			return StringUtils.EMPTY;
		}
	}
	
	/**
	 * 校验生产厂家是否输入正确
	 * @param pdProduct
	 * @return
	 */
	private String checkVender(PdProduct pdProduct) {
		PdVender pdVender = new PdVender();
		pdVender.setName(pdProduct.getVenderName().trim());
		try{
			pdVender = pdVenderService.findByName(pdVender);
		}catch(Exception e){
			e.printStackTrace();
			return StringUtils.EMPTY;
		}
		if(pdVender!=null){
			return pdVender.getId();
		}else{
			return StringUtils.EMPTY;
		}
	}
	
    /**
     * 校验金额是否正确
     * @param purPrice
     * @return
     */
	public static boolean isMoney(Double purPrice) {
		if(purPrice!=null){
			 Pattern pattern=Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
		        Matcher match=pattern.matcher(purPrice.toString()); 
		        if(match.matches()==false) { 
		           return false; 
		        }else{ 
		           return true; 
		        }
		}else{
			return false;
		}
       
	}
	
	/**
	 * 校验长度是否合理
	 * @param description
	 * @return
	 */
	public static boolean checkRemark(String description) {
		if(description.length()>1000){
			return false;
		}else{
			return true;
		}
		
	}
	
	/**
	 * 校验产品权限是否填写正确
	 * @param pdProduct
	 * @return
	 */
	private String checkPower(PdProduct pdProduct) {
		if(MinaGlobalConstants.PRODUCT_POWER_PUBLIC_NAME.equals(pdProduct.getPower().trim())){//公共产品
			return MinaGlobalConstants.PRODUCT_POWER_PUBLIC;
		}else if(MinaGlobalConstants.PRODUCT_POWER_PRIVATE_NAME.equals(pdProduct.getPower().trim())){//自有产品
			return MinaGlobalConstants.PRODUCT_POWER_PRIVATE;
		}
		return StringUtils.EMPTY;
	}
	
	/**
	 * 校验产品供应商是否填写正确
	 * @param pdProduct
	 * @return
	 */
	private String checkSupplier(PdProduct pdProduct) {
		if(StringUtils.EMPTY.equals(pdProduct.getSupplierId())){
			return StringUtils.EMPTY; 
		}
		PdSupplier pdSupplier = new PdSupplier();
		pdSupplier.setSupplierName(pdProduct.getSupplierId().trim());
		try{
			pdSupplier = pdSupplierService.findByName(pdSupplier);
		}catch(Exception e){
			e.printStackTrace();
			return StringUtils.EMPTY;
		}
		if(pdSupplier!=null){
			return pdSupplier.getId();
		}else{
			return "error";
		}
	}

	private String checksCharge(PdProduct pdProduct){
	    String isCharge = pdProduct.getIsCharge().trim();
        if(StringUtils.EMPTY.equals(isCharge)){
            return StringUtils.EMPTY;
        }

        if("否".equals(isCharge)){
            pdProduct.setIsCharge("0");
        }else if("是".equals(isCharge)){
            pdProduct.setIsCharge("1");
        }else{
            return StringUtils.EMPTY;
        }

        return isCharge;
    }

	@RequestMapping(value = "synProduct")
	@ResponseBody
	public JSONObject synProduct(Model model,HttpServletRequest request, HttpServletResponse response) {

		return syncProductDataTask.synPdProductToCentralPlatform();
	}

	@RequestMapping(value = "synProduct2")
	public void synProduct2(Model model,HttpServletRequest request, HttpServletResponse response) {

		Map<String,Object> map = new HashMap<String,Object>();
		try {
			syncProductDataTask.synPdVenderToCentralPlatform();

			map.put("statusCode", "200");
			map.put("msg", "同步成功");
		} catch (Exception e) {
			map.put("statusCode", "500");
			map.put("msg", "同步失败");
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "synProduct4")
	public void synProduct4(Model model,HttpServletRequest request, HttpServletResponse response) {

		Map<String,Object> map = new HashMap<String,Object>();
		try {
			syncProductDataTask.synPdCategoryToCentralPlatform();

			map.put("statusCode", "200");
			map.put("msg", "同步成功");
		} catch (Exception e) {
			map.put("statusCode", "500");
			map.put("msg", "同步失败");
			e.printStackTrace();
		}
	}

    /**
     * 初始化产品编号
     */
    @RequestMapping(value = "initProductNumber")
    public void initProductNumber(Model model,HttpServletRequest request, HttpServletResponse response) {
        List<PdProduct> pdProducts = pdProductService.findList(new PdProduct());
        for (PdProduct pdProduct:pdProducts) {
            String generateNumber = CommonUtils.generateNumber("93");//生成编码
            pdProduct.setNumber(generateNumber);
            pdProductService.update(pdProduct);
        }
    }
}