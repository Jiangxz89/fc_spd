/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.hys.pd.entity.PdDosageDetail;
import com.thinkgem.hys.pd.entity.PdDosagertDetail;
import com.thinkgem.hys.pd.entity.PdProductStock;
import com.thinkgem.hys.pd.entity.PdProductStockTotal;
import com.thinkgem.hys.pd.entity.PdRejectedDetail;
import com.thinkgem.hys.pd.entity.PdRejectedListChild;
import com.thinkgem.hys.pd.entity.PdRejectedListHead;
import com.thinkgem.hys.pd.entity.PdStockRecordProduct;
import com.thinkgem.hys.pd.dao.PdProductStockDao;
import com.thinkgem.hys.pd.dao.PdProductStockTotalDao;

/**
 * 库存总记录Service
 * @author changjundong
 * @version 2018-03-07
 */
@Service
@Transactional(readOnly = true)
public class PdProductStockTotalService extends CrudService<PdProductStockTotalDao, PdProductStockTotal> {

	@Autowired
	PdProductStockTotalDao pdProductStockTotalDao;
	@Autowired
	PdProductStockDao pdProductStockDao;
	
	public PdProductStockTotal get(String id) {
		return super.get(id);
	}
	
	public List<PdProductStockTotal> findList(PdProductStockTotal pdProductStockTotal) {
		return super.findList(pdProductStockTotal);
	}
	
	public Page<PdProductStockTotal> findPage(Page<PdProductStockTotal> page, PdProductStockTotal pdProductStockTotal) {
		return super.findPage(page, pdProductStockTotal);
	}
	
	@Transactional(readOnly = false)
	public void save(PdProductStockTotal pdProductStockTotal) {
		super.save(pdProductStockTotal);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdProductStockTotal pdProductStockTotal) {
		super.delete(pdProductStockTotal);
	}
	
	public PdProductStockTotal getByProductBarCode(PdProductStockTotal PdProductStockTotal){
		return pdProductStockTotalDao.getByProductBarCode(PdProductStockTotal);
	}
	
	//更新库存
	@Transactional(readOnly = false)
	public void updateStockNum(PdProductStockTotal pdProductStockTotal){
		pdProductStockTotalDao.updateStockNum(pdProductStockTotal);
	}
	
	/***
	 * 	库存入库更新库存信息
	 * 
	 * @param  outStoreroomId       出库库房ID，允许为空
	 * @param  inStoreroomId        入库库房ID，不允许为空
	 * @param  inStoreroomType      入库库房类型，不允许为空
	 * @param  String supplierId    供应商ID
	 * @param stockRecordProducts  入库明细列表，不允许为空
	 * @return  String   更新库存结果  入库成功，返回字符串“true”，否则返回错误信息
	 */
	@Transactional(readOnly = false)
	public String updateInStock(String outStoreroomId, String inStoreroomId, 
			String inStoreroomType, String supplierId, List<PdStockRecordProduct> stockRecordProducts){
		
		if(StringUtils.isEmpty(inStoreroomId) || stockRecordProducts == null 
				|| stockRecordProducts.size() == 0){
			return "参数有误";
		}
		
		for(PdStockRecordProduct stockRecordProduct:stockRecordProducts){
			String productId = stockRecordProduct.getProductId();            //产品ID
			String productBarCode = stockRecordProduct.getProductBarCode();  //产品条码
			String productName = stockRecordProduct.getProductName();  //产品名称
			String batchNo = stockRecordProduct.getBatchNo();
			String productNo=stockRecordProduct.getNumber();
			Integer productNum_i = stockRecordProduct.getProductNum();  //数量
			Double inPrice = stockRecordProduct.getInPrice();//入库单价 add by jiangxz 20190902
			int productNum = productNum_i.intValue();
			
			/***  入库时不处理出库
			//1、如果为二级库存入库，扣减出库库存，扣减出库库存明细
			if(MinaGlobalConstants.STOREROOM_TYPE_1.equals(inStoreroomType)){
				PdProductStockTotal stockTotalq = new PdProductStockTotal();
				stockTotalq.setStoreroomId(outStoreroomId);
				stockTotalq.setProductId(productId);
				List<PdProductStockTotal> productStockTotals = pdProductStockTotalDao.findList(stockTotalq);
				if(productStockTotals != null && productStockTotals.size() == 1){
					PdProductStockTotal productStockTotal = productStockTotals.get(0);
					Integer stockNum = productStockTotal.getStockNum();
					Integer num = stockNum.intValue() - productNum;
					productStockTotal.setStockNum(num);
					pdProductStockTotalDao.updateStockNum(productStockTotal);
				}
				
				PdProductStock o_productStockq = new PdProductStock();
				o_productStockq.setStoreroomId(outStoreroomId);
				o_productStockq.setProductId(productId);
				o_productStockq.setProductBarCode(productBarCode);
				o_productStockq.setBatchNo(batchNo);
				List<PdProductStock> productStocks = pdProductStockDao.findList(o_productStockq);
				if(productStocks != null && productStocks.size() >= 0){
					PdProductStock productStock = productStocks.get(0);
					Integer stockNum = productStock.getStockNum();
					Integer num = stockNum.intValue() - productNum;
					productStock.setStockNum(num);
					pdProductStockDao.updateStockNum(productStock);
				}else{
					
				}
			}
			***/
			
			//2、增加入库库存
			PdProductStockTotal stockTotalqi = new PdProductStockTotal();
			stockTotalqi.setStoreroomId(inStoreroomId);
			stockTotalqi.setProductId(productId);
			List<PdProductStockTotal> i_productStockTotals = pdProductStockTotalDao.findForUpdate(stockTotalqi);
			//如果库存总表不存在产品，则新增产品库存总表信息
			if(i_productStockTotals == null || i_productStockTotals.size() == 0){
				PdProductStockTotal productStockTotal = new PdProductStockTotal();
				productStockTotal.setStoreroomId(inStoreroomId);  //库房
				productStockTotal.setProductId(productId);    //产品ID
				productStockTotal.setStockNum(productNum);    //入库数量
				if(StringUtils.isNotEmpty(supplierId)){
					productStockTotal.setSupplierId(supplierId);   //供应商
				}
				
				super.save(productStockTotal);
			}
			//如果库存总表存在，则增加库存数量
			else{
				PdProductStockTotal productStockTotal = i_productStockTotals.get(0);
				productStockTotal.setStockNum(productNum);
				pdProductStockTotalDao.addStock(productStockTotal);
			}
			
			//增加入库库存明细
			PdProductStock i_productStockq = new PdProductStock();
			i_productStockq.setStoreroomId(inStoreroomId);
			i_productStockq.setProductId(productId);
			i_productStockq.setProductBarCode(productBarCode);//2019年7月24日16:53:43 放开
			i_productStockq.setBatchNo(batchNo);
			i_productStockq.setProductNo(productNo);
			List<PdProductStock> i_productStocks = pdProductStockDao.findForUpdate(i_productStockq);
			//如果库存明细表不存在，则新增
			if(i_productStocks == null || i_productStocks.size() == 0){
				PdProductStock productStock = new PdProductStock();
				productStock.setStoreroomId(inStoreroomId);
				productStock.setProductId(productId);
				productStock.setProductBarCode(productBarCode);
				productStock.setStockNum(productNum);
				productStock.setProductName(productName);
				productStock.setBatchNo(batchNo);
				productStock.setProductNo(productNo);
				productStock.setValidDate(stockRecordProduct.getLimitDate());
				if(StringUtils.isNotEmpty(supplierId)){
					productStock.setSupplierId(supplierId);
				}
				productStock.preInsert();
				pdProductStockDao.insert(productStock);
			}
			//存在，则增加库存数量
			else{
				PdProductStock productStock = i_productStocks.get(0);
				productStock.setStockNum(productNum);
				pdProductStockDao.addStock(productStock);
			}
		}
		
		//3、登记日志
		
		
		return "true";
	}
	
	/***
	 * 	库存出库更新库存信息
	 * 
	 * @param  outStoreroomId       出库库房ID，允许为空
	 * @param  inStoreroomId        入库库房ID，不允许为空
	 * @param  inStoreroomType      出库类型，不允许为空
	 * @param stockRecordProducts   出库明细列表，不允许为空
	 * @return  String   更新库存结果  入库成功，返回字符串“true”，否则返回错误信息
	 */
	@Transactional(readOnly = false)
	public Map updateOutStock(String outStoreroomId, String inStoreroomId, 
			String inStoreroomType, List<PdStockRecordProduct> stockRecordProducts){
		Map rtMap = new HashMap<String, String>();
		if(StringUtils.isEmpty(inStoreroomId) || stockRecordProducts == null 
				|| stockRecordProducts.size() == 0){
			rtMap.put("code", "201");
			rtMap.put("msg", "传入参数有误");
			return rtMap;
		}
		
		for(PdStockRecordProduct stockRecordProduct:stockRecordProducts){
			String productId = stockRecordProduct.getProductId();     //产品ID
			String productNo=stockRecordProduct.getNumber();          //产品编号
			String productBarCode = stockRecordProduct.getProductBarCode();  //产品条码
			String batchNo = stockRecordProduct.getBatchNo();          //批次号
			Integer productNum_i = stockRecordProduct.getProductNum();  //数量
			int productNum = productNum_i.intValue();
			
			//1、
			PdProductStockTotal stockTotalq = new PdProductStockTotal();
			stockTotalq.setStoreroomId(outStoreroomId);
			stockTotalq.setProductId(productId);
			List<PdProductStockTotal> productStockTotals = pdProductStockTotalDao.findList(stockTotalq);
			if(productStockTotals != null && productStockTotals.size() == 1){
				PdProductStockTotal productStockTotal = productStockTotals.get(0);
				Integer stockNum = productStockTotal.getStockNum();
				Integer num = stockNum.intValue() - productNum;
				productStockTotal.setStockNum(num);
				pdProductStockTotalDao.updateStockNum(productStockTotal);
			}
				
			PdProductStock o_productStockq = new PdProductStock();
			o_productStockq.setStoreroomId(outStoreroomId);
			o_productStockq.setProductId(productId);
			o_productStockq.setProductBarCode(productBarCode);
			o_productStockq.setBatchNo(batchNo);
			List<PdProductStock> productStocks = pdProductStockDao.findList(o_productStockq);
			if(productStocks != null && productStocks.size() >= 1){
				PdProductStock productStock = productStocks.get(0);
				Integer stockNum = productStock.getStockNum();
				Integer num = stockNum.intValue() - productNum;
				productStock.setStockNum(num);
				pdProductStockDao.updateStockNum(productStock);
			}else{
				
			}
		}
		
		//3、登记日志
		
		rtMap.put("code", "200");
		return rtMap;
	}
	
	/***
	 * 	器械使用更新库存信息
	 * @param storeroomId     库房ID
	 * @param dosageDetails   使用明细
	 * @return  String   更新库存结果
	 */
	@Transactional(readOnly = false)
	public String updateUseStock(String storeroomId, List<PdDosageDetail> dosageDetails){
		
		//1、扣减出库库存，扣减出库库存明细
		for(PdDosageDetail dosageDetail:dosageDetails){
			String productId = dosageDetail.getProdId();    //产品ID
			String prodBarcode = dosageDetail.getProdBarcode();                  //条码
			String batchNo = dosageDetail.getBatchNo();
			Integer productNum_i = dosageDetail.getDosageCount();  //数量
			int productNum = productNum_i.intValue();
			
			//1、扣减出库库存，扣减出库库存明细
			PdProductStockTotal stockTotalq = new PdProductStockTotal();
			stockTotalq.setStoreroomId(storeroomId);
			stockTotalq.setProductId(productId);
			List<PdProductStockTotal> productStockTotals = pdProductStockTotalDao.findList(stockTotalq);
			if(productStockTotals != null && productStockTotals.size() == 1){
				PdProductStockTotal productStockTotal = productStockTotals.get(0);
				Integer stockNum = productStockTotal.getStockNum();
				Integer num = stockNum.intValue() - productNum;
				productStockTotal.setStockNum(num);
				pdProductStockTotalDao.updateStockNum(productStockTotal);
			}
			
			PdProductStock o_productStockq = new PdProductStock();
			o_productStockq.setStoreroomId(storeroomId);
			o_productStockq.setProductId(productId);
			o_productStockq.setProductBarCode(prodBarcode);
			o_productStockq.setBatchNo(batchNo);
			List<PdProductStock> productStocks = pdProductStockDao.findList(o_productStockq);
			if(productStocks != null && productStocks.size() >= 0){
				PdProductStock productStock = productStocks.get(0);
				Integer stockNum = productStock.getStockNum();
				Integer num = stockNum.intValue() - productNum;
				productStock.setStockNum(num);
				pdProductStockDao.updateStockNum(productStock);
			}
		}
		
		//2、登记日志
		
		
		return "true";
	}
	
	/***
	 * 	用量退回更新库存信息
	 * @param  storeroomId     退回库房ID
	 * @param  dosageDetail    退回用量明细
	 * @return  String        更新库存结果
	 */
	@Transactional(readOnly = false)
	public String updateRetunuseStock(String storeroomId, List<PdDosagertDetail> detailList){
		
		//1、增加库存，增加库存明细
		for(PdDosagertDetail drt:detailList){
			String productId = drt.getProdId();      //产品ID
			String productNo = drt.getProdNo();      //产品编码
			String productBarCode = drt.getProdBarcode();  //产品条码
			String batchNo = drt.getBatchNo();       //产品批号
			Integer productNum_i = drt.getRtCount();  //退回数量
			int rtNum = productNum_i.intValue();
			
			//2、增加入库库存
			PdProductStockTotal stockTotalqi = new PdProductStockTotal();
			stockTotalqi.setStoreroomId(storeroomId);
			stockTotalqi.setProductId(productId);
			List<PdProductStockTotal> i_productStockTotals = pdProductStockTotalDao.findForUpdate(stockTotalqi);
			//如果库存总表不存在产品，则新增产品库存总表信息
			if(i_productStockTotals == null || i_productStockTotals.size() == 0){

			}
			//如果库存总表存在，则增加库存数量
			else{
				PdProductStockTotal productStockTotal = i_productStockTotals.get(0);
				productStockTotal.setStockNum(rtNum);
				pdProductStockTotalDao.addStock(productStockTotal);
			}
			
			//增加入库库存明细
			PdProductStock i_productStockq = new PdProductStock();
			i_productStockq.setStoreroomId(storeroomId);
			i_productStockq.setProductId(productId);
			i_productStockq.setProductBarCode(productBarCode);
			i_productStockq.setBatchNo(batchNo);
			i_productStockq.setProductNo(productNo);
			List<PdProductStock> i_productStocks = pdProductStockDao.findForUpdate(i_productStockq);
			//如果库存明细表不存在，则新增
			if(i_productStocks == null || i_productStocks.size() == 0){

			}
			//存在，则增加库存数量
			else{
				PdProductStock productStock = i_productStocks.get(0);
				productStock.setStockNum(rtNum);
				pdProductStockDao.addStock(productStock);
			}
		}
		
		//2、登记日志
		
		
		return "";
	}
	
	/***
	 * 	退货更新库存信息
	 * 
	 * @param  outStoreroomId       出库库房ID，允许为空
	 * @param  rejectedListChild    退货明细列表，不允许为空
	 * @return Map      更新库存结果  入库成功，返回字符串“true”，否则返回错误信息
	 */
	@Transactional(readOnly = false)
	public Map updateRejectionStock(String outStoreroomId, PdRejectedListHead pdRejectedListHead){
		List<PdRejectedListChild> rejectedDetails = pdRejectedListHead.getChild();
		Map rtMap = new HashMap<String, String>();
		if(StringUtils.isEmpty(outStoreroomId) || rejectedDetails == null 
				|| rejectedDetails.size() == 0){
			rtMap.put("code", "201");
			rtMap.put("msg", "传入参数有误");
			return rtMap;
		}
		
		for(PdRejectedListChild rejectedDetail:rejectedDetails){
			String productId = rejectedDetail.getProdId();     //产品ID
			String productNo = rejectedDetail.getProdNo();          //产品编号
			String productBarCode = rejectedDetail.getBarcode();  //产品条码
			String batchNo = rejectedDetail.getBatchNo();          //批次号
			int rejectedNum = rejectedDetail.getRejectedCount();  //退货数量
			
			//1、
			PdProductStockTotal stockTotalq = new PdProductStockTotal();
			stockTotalq.setStoreroomId(outStoreroomId);
			stockTotalq.setProductId(productId);
			List<PdProductStockTotal> productStockTotals = pdProductStockTotalDao.findForUpdate(stockTotalq);
			if(productStockTotals != null && productStockTotals.size() == 1){
				PdProductStockTotal productStockTotal = productStockTotals.get(0);
				Integer stockNum = productStockTotal.getStockNum();
				Integer num = stockNum.intValue() - rejectedNum;
				if(num < 0){
					
				}
				productStockTotal.setStockNum(num);
				pdProductStockTotalDao.updateStockNum(productStockTotal);
			}
				
			PdProductStock o_productStockq = new PdProductStock();
			o_productStockq.setStoreroomId(outStoreroomId);
			o_productStockq.setProductId(productId);
			o_productStockq.setProductNo(productNo);
			o_productStockq.setProductBarCode(productBarCode);
			o_productStockq.setBatchNo(batchNo);
			List<PdProductStock> productStocks = pdProductStockDao.findForUpdate(o_productStockq);
			if(productStocks != null && productStocks.size() >= 1){
				PdProductStock productStock = productStocks.get(0);
				Integer stockNum = productStock.getStockNum();
				Integer num = stockNum.intValue() - rejectedNum;
				if(num < 0){
					
				}
				productStock.setStockNum(num);
				pdProductStockDao.updateStockNum(productStock);
			}
		}
		
		//3、登记日志
		
		rtMap.put("code", "200");
		return rtMap;
	}
	
	/**
	 * 库存查询
	 * @param page
	 * @param pdProductStock
	 * @return
	 */
	public Page<PdProductStockTotal> findListForQuery(Page<PdProductStockTotal> page, PdProductStockTotal pdProductStockTotal) {
		pdProductStockTotal.setPage(page);
		page.setList(dao.findListForQuery(pdProductStockTotal));
		return page;
	}
	
	/**
	 * 库存查询不分页
	 * @param page
	 * @param pdProductStock
	 * @return
	 */
	public List<PdProductStockTotal> findListForQuery(PdProductStockTotal pdProductStockTotal) {
		
		return dao.findListForQuery(pdProductStockTotal);
	}
	
	@Transactional(readOnly = false)
	public Map<String, Object> findPdCount(PdProductStockTotal pdProductStockTotal){
		return pdProductStockTotalDao.findPdCount(pdProductStockTotal);
	}
	
	@Transactional(readOnly = false)
	public void updateProductStock(PdProductStockTotal stockTotal) {
		// TODO Auto-generated method stub
		pdProductStockTotalDao.updateProductStock(stockTotal);
	}
	
	
	/**
	 * 院外退货更新库存信息
	 * @param pdProductStockTotal
	 * @return
	 * */
	@Transactional(readOnly = false)
	public Map<String,String> updateStockNumByProdIdAndStoreroomId(PdProductStockTotal stockTotal){
		List<PdProductStockTotal> totalList = pdProductStockTotalDao.findForUpdate(stockTotal);
		PdProductStockTotal total = totalList.get(0);
		Map<String,String> result = new HashMap<String,String>();
		
		int before = total.getStockNum();
		int after = stockTotal.getStockNum();
		
		if(before >= after){
			stockTotal.setStockNum(before - after);
			pdProductStockTotalDao.updateForDosagert(stockTotal);
			result.put("code", "200");
			return result;
		}else{
			result.put("code", "500");
			return result;
		}
		
		
		
	}
	
	/**
	 * 进销存表查询库存总表的数据
	 * @param pdProductStockTotal
	 * @return
	 */
	public List<PdProductStockTotal> findListTask(PdProductStockTotal pdProductStockTotal) {
		return dao.findListTask(pdProductStockTotal);
	}
	
}