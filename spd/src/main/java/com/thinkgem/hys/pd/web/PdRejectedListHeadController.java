/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.hys.pd.dao.PdProductStockDao;
import com.thinkgem.jeesite.common.utils.DateUtils;
import jersey.repackaged.com.google.common.collect.Sets;
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
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.hys.pd.entity.PdProduct;
import com.thinkgem.hys.pd.entity.PdProductStock;
import com.thinkgem.hys.pd.entity.PdRejectedListChild;
import com.thinkgem.hys.pd.entity.PdRejectedListHead;
import com.thinkgem.hys.pd.entity.vo.ProductStockVo;
import com.thinkgem.hys.pd.service.PdProductService;
import com.thinkgem.hys.pd.service.PdProductStockService;
import com.thinkgem.hys.pd.service.PdProductStockTotalService;
import com.thinkgem.hys.pd.service.PdRejectedListChildService;
import com.thinkgem.hys.pd.service.PdRejectedListHeadService;
import com.thinkgem.hys.utils.CommonUtils;

/**
 * 退货单单头表Controller
 * @author sutianqi
 * @version 2018-03-07
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdRejectedListHead")
public class PdRejectedListHeadController extends BaseController {

    @Autowired
    private PdRejectedListHeadService pdRejectedListHeadService;
    @Autowired
    private PdRejectedListChildService pdRejectedListChildService;
    @Autowired
    private PdProductStockService pdProductStockService;
    @Autowired
    private PdProductService pdProductService;
    @Autowired
    private PdProductStockTotalService pdProductStockTotalService;
    @Autowired
    private PdProductStockDao pdProductStockDao;

    @ModelAttribute
    public PdRejectedListHead get(@RequestParam(required = false) String id) {
        PdRejectedListHead entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = pdRejectedListHeadService.get(id);
        }
        if (entity == null) {
            entity = new PdRejectedListHead();
        }
        return entity;
    }

    @RequiresPermissions("pd:pdRejectedListHead:view")
    @RequestMapping(value = {"list", ""})
    public String list(PdRejectedListHead pdRejectedListHead, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<PdRejectedListHead> page;

        String param = request.getParameter("date") == null ? "" : request.getParameter("date");

        if (!param.equals("")) {
            pdRejectedListHead.setRejectedDate(CommonUtils.getFormatDates(CommonUtils.stringToDate(param.split(" - ")[0]), CommonUtils.stringToDate(param.split(" - ")[1])));
        }

        page = pdRejectedListHeadService.findPage(new Page<PdRejectedListHead>(request, response), pdRejectedListHead);

        pdRejectedListHead.setRejectedDate(param);
        request.setAttribute("pdRejectedListHead", pdRejectedListHead);
        model.addAttribute("page", page);
        return "hys/pd/pdRejectedListHeadList";
    }

    @RequiresPermissions("pd:pdRejectedListHead:view")
    @RequestMapping(value = "form")
    public String form(PdRejectedListHead pdRejectedListHead, Model model, HttpServletRequest request) {

        String number = CommonUtils.generateOrderNoByType("TH");
        request.setAttribute("number", number);
        model.addAttribute("pdRejectedListHead", pdRejectedListHead);
        return "hys/pd/pdRejectedListHeadForm";
    }

    @RequestMapping(value = "newSave")
    public String newSave(PdRejectedListHead pdRejectedListHead, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        //获取当前用户
        User user = UserUtils.getUser();
        pdRejectedListHead.setOperPerson(user.getName());

        //获取当前库房名称id
        pdRejectedListHead.setRejectedRepoId(user.getStoreroomId());
        pdRejectedListHead.setRejectedRepoName(user.getStoreroomName());

        //获取当前时间
        pdRejectedListHead.setRejectedDate(CommonUtils.formatDate("SHORT"));
        //校验
        List<PdRejectedListChild> childs = pdRejectedListHead.getChild();
        if (childs != null && childs.size() > 0) {
            //校验数据的合法性
            Iterator<PdRejectedListChild> it = childs.iterator();
            while(it.hasNext()){
                PdRejectedListChild child = it.next();
                if(child.getRejectedCount()==0 || child.getProductStockId()==null){
                    it.remove();
                }
            }
            if(childs!=null && childs.size()>0){
                pdRejectedListHeadService.saveNew(pdRejectedListHead,childs);
                addMessage(redirectAttributes, "保存退货单成功");
                return "redirect:" + Global.getAdminPath() + "/pd/pdRejectedListHead/?repage";
            }else{
                addMessage(redirectAttributes, "数据校验有误请重新保存");
                return "redirect:" + Global.getAdminPath() + "/pd/pdRejectedListHead/?repage";
            }
        }
        addMessage(redirectAttributes, "数据校验有误请重新保存");
        return "redirect:" + Global.getAdminPath() + "/pd/pdRejectedListHead/?repage";
    }
    /**
     * 添加
     */
    //@RequiresPermissions("pd:pdRejectedListHead:edit")
    @RequestMapping(value = "save")
    public String save(PdRejectedListHead pdRejectedListHead, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        if (!beanValidator(model, pdRejectedListHead)) {
            return form(pdRejectedListHead, model, request);
        }

        //获取当前用户
        User user = UserUtils.getUser();
        pdRejectedListHead.setOperPerson(user.getName());

        //获取当前库房名称id
        pdRejectedListHead.setRejectedRepoId(user.getStoreroomId());
        pdRejectedListHead.setRejectedRepoName(user.getStoreroomName());

        //获取当前时间
        pdRejectedListHead.setRejectedDate(CommonUtils.formatDate("SHORT"));

        //校验
        List<PdRejectedListChild> childs = pdRejectedListHead.getChild();
        if (childs != null && childs.size() > 0) {
            List<PdRejectedListChild> afterDealList = dealRepeatData(childs);
            PdProductStock pps = new PdProductStock();
            pps.setStoreroomId(UserUtils.getUser().getStoreroomId());
            //保存
            pdRejectedListHeadService.save(pdRejectedListHead);
            for (PdRejectedListChild plc : afterDealList) {
                //校验不合法数据和大于库存数据
                pps.setBatchNo(plc.getBatchNo());
                pps.setProductId(plc.getProdId());
                pps.setProductNo(plc.getProdNo());
                pps.setProductBarCode(plc.getBarcode());
                PdProductStock tempPps = pdProductStockDao.searchOneStock(pps);
                if (null == tempPps || plc.getRejectedCount() > tempPps.getStockNum()) {
                    continue;
                }
                plc.setHeadId(pdRejectedListHead.getId());
                pdRejectedListChildService.save(plc);
            }
            pdProductStockTotalService.updateRejectionStock(pdRejectedListHead.getRejectedRepoId(), pdRejectedListHead);
            addMessage(redirectAttributes, "保存退货单成功");
            return "redirect:" + Global.getAdminPath() + "/pd/pdRejectedListHead/?repage";
        }else {
            addMessage(redirectAttributes, "退货数不能为0");
            return "redirect:" + Global.getAdminPath() + "/pd/pdRejectedListHead/?repage";
        }
    }


    //合并相同的退货量
    private List<PdRejectedListChild> dealRepeatData(final List<PdRejectedListChild> list){
        List<PdRejectedListChild> tempArray = new ArrayList<PdRejectedListChild>();
        Set<String> pids = new HashSet<String>();
        if(list != null && list.size() > 0){
            for(PdRejectedListChild temp : list){
                if (temp == null || StringUtils.isEmpty(temp.getProdId()) || StringUtils.isEmpty(temp.getProdNo())
                        || StringUtils.isEmpty(temp.getBarcode()) || StringUtils.isEmpty(temp.getBatchNo())) {
                    continue;
                }
                StringBuilder sb = new StringBuilder();
                sb.append(temp.getProdId()).append(temp.getProdNo()).append(temp.getBarcode()).append(temp.getBatchNo());
                if(pids.contains(sb.toString())){
                    continue;
                }
                int rejectedTotal = 0;
                for(PdRejectedListChild tp : list){
                    if ( tp != null) {
                        if(temp.getBatchNo().equals(tp.getBatchNo())
                                && temp.getProdNo().equals(tp.getProdNo())
                                && temp.getProdNo().equals(tp.getProdNo())
                                && temp.getProdId().equals(tp.getProdId())){
                            pids.add(sb.toString());
                            rejectedTotal += tp.getRejectedCount();
                        }
                    }
                }
                temp.setRejectedCount(rejectedTotal);
                tempArray.add(temp);
            }
        }
        return tempArray;
    }

	/**
	 * 查看详情
	 * */
	@RequestMapping(value = "detail")
	public String detail(HttpServletRequest request , HttpServletResponse response){
		String id = request.getParameter("id");
		PdRejectedListHead pdRejectedListHead = pdRejectedListHeadService.get(id);
		
		//查子表集合
		PdRejectedListChild pdRejectedListChild = new PdRejectedListChild();
		pdRejectedListChild.setHeadId(id);
		List<PdRejectedListChild> childList = pdRejectedListChildService.findList(pdRejectedListChild);
		
		//查子表  库存 | 产品 实体
		for(int i = 0 ; i < childList.size() ; i ++){
			PdProductStock pdProductStock = pdProductStockService.get(childList.get(i).getProductStockId());
			childList.get(i).setProductStockBean(pdProductStock);
			
			PdProduct pdProduct = pdProductService.get(pdProductStock.getProductId());
			childList.get(i).setProductBean(pdProduct);
		}
		
		pdRejectedListHead.setChild(childList);
        int productTotNum = 0;

        for(PdRejectedListChild p:childList){
            productTotNum = p.getRejectedCount() + productTotNum;
        }
        pdRejectedListHead.setProductTotNum(productTotNum);
        request.setAttribute("head", pdRejectedListHead);
		return "hys/pd/pdRejectedListHeadDetail";
	}
	
	/**
	 * 逻辑删除
	 * */
	//@RequiresPermissions("pd:pdRejectedListHead:edit")
	@RequestMapping(value = "delete")
	public String delete(PdRejectedListHead pdRejectedListHead, RedirectAttributes redirectAttributes) {
		pdRejectedListHeadService.delete(pdRejectedListHead);
		addMessage(redirectAttributes, "删除退货单单头表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdRejectedListHead/?repage";
	}
	
	/**
	 * AJAX响应方法
	 * 取库存
	 * */
	@RequestMapping(value = "getStock")
	public void getStock(HttpServletRequest request, HttpServletResponse response){
		String number = request.getParameter("number");
		String productBarCode = request.getParameter("productBarCode");
		
		//条形码封装进对象
		PdProductStock pdProductStock = new PdProductStock();
		pdProductStock.setProductNo(number);
		pdProductStock.setProductBarCode(productBarCode);
		//库房id封装进对象
		User user = UserUtils.getUser();
		String storeroomId = user.getStoreroomId();
		pdProductStock.setStoreroomId(storeroomId);
		
		//获取库存信息
		List<PdProductStock> prodStockList = pdProductStockService.getByProductBarCode(pdProductStock);
		
		List<ProductStockVo> voList = new ArrayList<ProductStockVo>();
		
		for(int i = 0 ; i < prodStockList.size() ; i ++){
			//根据库存内 产品主键id获取产品信息
			PdProduct pdProduct = pdProductService.get(prodStockList.get(i).getProductId());
			//封装进Vo
			ProductStockVo productStockVo = new ProductStockVo(prodStockList.get(i) , pdProduct);
			
			voList.add(productStockVo);
		}
		
		
		
		
		//转换json
		String jsonString = JSONObject.toJSONString(voList);
		
		//响应
		try {
			System.out.println(jsonString);
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(jsonString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * AJAX响应方法
	 * 取单条库存
	 * */
	@RequestMapping(value = "getOneStock")
	public void getOneStock(HttpServletRequest request, HttpServletResponse response){
		String number = request.getParameter("number");
		String productBarCode = request.getParameter("barcode");
		String batchNo = request.getParameter("batchNo");
		
		PdProductStock stock = new PdProductStock();
		stock.setProductNo(number);
		stock.setProductBarCode(productBarCode);
		stock.setBatchNo(batchNo);
		//库房id封装进对象
		User user = UserUtils.getUser();
		String storeroomId = user.getStoreroomId();
		stock.setStoreroomId(storeroomId);
		
		PdProductStock pdProductStock = pdProductStockService.getOneStock(stock);
		
		String json = JSONObject.toJSONString(pdProductStock);
		
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 统计查询---院外退货明细
	 * @param pdRejectedListHead
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="pdHospitaloutsideReturnDetail")
	public String pdHospitaloutsideReturnDetail(PdRejectedListHead pdRejectedListHead, HttpServletRequest request, HttpServletResponse response, Model model){
		User user = UserUtils.getUser();		
		if(!StringUtils.isNotEmpty(pdRejectedListHead.getRejectedRepoId())){
			pdRejectedListHead.setRejectedRepoId(user.getStoreroomId());  //所属库房
		}
		
		//导出excel需要的数据
		model.addAttribute("exportDataList", JsonMapper.toJsonString(pdRejectedListHeadService.findHospitaloutsideReturnDetail(pdRejectedListHead)));
		Page<PdRejectedListHead> page = pdRejectedListHeadService.findHospitaloutsideReturnDetail(new Page<PdRejectedListHead>(request, response), pdRejectedListHead); 
		model.addAttribute("page", page);
		model.addAttribute("pdRejectedListHead", pdRejectedListHead);	
		model.addAttribute("user", user);
		return "hys/pd/query/hospitaloutsideReturnDetail";
	}



    /**
     * 在退货管理中取库存中产品的信息
     */
    @RequestMapping(value = "getProductInfoFromDosage")
    @ResponseBody
    public Object getProductInfoFromDosage(PdProductStock pdProductStock){
        pdProductStock.setStoreroomId(UserUtils.getUser().getStoreroomId());
        List<PdProductStock> list = pdProductStockService.getByProductBarCode(pdProductStock);
        Set<String> batchNoList = Sets.newHashSet();
        JSONObject stockMap = new JSONObject();//批号对应的库存
        JSONObject validDateMap = new JSONObject();//批号对应的有效期
        if(list != null && list.size() > 0){
            for(PdProductStock ps : list){
                batchNoList.add(ps.getBatchNo());
                stockMap.put(ps.getBatchNo(), ps.getStockNum());
                validDateMap.put(ps.getBatchNo(), DateUtils.formatDate(ps.getValidDate(), "yyyy-MM-dd"));
            }
            PdProductStock temp = list.get(0);
            String chargeCodes = temp.getPdProduct().getChargeCode();
            if(StringUtils.isNotEmpty(chargeCodes)){
                temp.setChargeCodeList(Arrays.asList(chargeCodes.split(",")));
            }
            temp.setStockMap(JSONObject.toJSONString(stockMap));
            temp.setValidDateMap(JSONObject.toJSONString(validDateMap));
            temp.setBatchNoList(batchNoList);
            return temp;
        }

        return null;
    }
	

}