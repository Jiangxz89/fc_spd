/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.dao.*;
import com.thinkgem.hys.pd.entity.*;
import com.thinkgem.hys.pd.entity.bo.PdProductBO;
import com.thinkgem.hys.utils.CommonUtils;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.ChineseCharToEnUtils;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 产品表Service
 * @author sutianqi
 * @version 2018-03-07
 */
@Service
@Transactional(readOnly = true)
public class PdProductService extends CrudService<PdProductDao, PdProduct> {
	@Autowired
	PdProductDao pdProductDao ;
	@Autowired
	private PdDrugSupplierDao pdDrugSupplierDao;
	@Autowired
	private PdSupplierDao pdSupplierDao;
	@Autowired
	private PdEncodingRuleDao pdEncodingRuleDao;
	@Autowired
	private PdEncodingRuleDetailDao pdEncodingRuleDetailDao;
	@Value( "${userfiles.basedir}" )
	protected String userfilesBasedir;
	@Autowired
	private PdProductRuleDao pdProductRuleDao;

	@Autowired
	private SqlSession sqlsession;

	public PdProduct get(String id) {
		return super.get(id);
	}

	public PdProduct getProduct(PdProduct pdProduct) {
		return pdProductDao.getProduct(pdProduct);
	}

	public List<PdProduct> findList(PdProduct pdProduct) {
		return super.findList(pdProduct);
	}

	public List<PdProduct> basicFindList(PdProduct pdProduct) {
		return pdProductDao.basicFindList(pdProduct);
	}

	public List<PdProduct> findAllList(PdProduct pdProduct) {
		return pdProductDao.findAllList(pdProduct);
	}

	public Page<PdProduct> findPage(Page<PdProduct> page, PdProduct pdProduct) {
		return super.findPage(page, pdProduct);
	}

	public Page<PdProduct> basicFindPage(Page<PdProduct> page, PdProduct pdProduct) {
		pdProduct.setPage(page);
		page.setList(pdProductDao.basicFindList(pdProduct));
		return page;
	}

	@Transactional(readOnly = false)
	public void save(PdProduct pdProduct) {
		pdProduct.setSynFlag(MinaGlobalConstants.SYN_NONE);
		String encodingRule = pdProduct.getEncodingRule();
		pdProduct.setEncodingRule("");
		super.save(pdProduct);
		//产品绑定多个编码规则
		if(encodingRule!=null && !"".equals(encodingRule)){
			String [] strings = encodingRule.split(",");
			List<String> arrays = Arrays.asList(strings);
			List<PdProductRule> pdProductRules = new ArrayList<PdProductRule>();
			for(String str :arrays){
				PdProductRule pdProductRule = new PdProductRule();
				pdProductRule.setProductId(pdProduct.getId());
				pdProductRule.setRuleId(str);
				pdProductRule.setId(IdGen.uuid());
				pdProductRules.add(pdProductRule);
			}
			pdProductRuleDao.batchSave(pdProductRules);
		}
		PdDrugSupplier pds = new PdDrugSupplier();
		if (!StringUtils.isEmpty(pdProduct.getSupplierId())) {
			PdSupplier supplier = pdSupplierDao.get(pdProduct.getSupplierId());
			pds.setYpdm(pdProduct.getId());
			pds.setGhdwId(supplier.getId());
			pds.setGhdwMc(supplier.getSupplierName());
			pds.setType(MinaGlobalConstants.IS_DRUG_OR_CONSUMABLE_1);
			pdDrugSupplierDao.insert(pds);
		}else{
			pds.setYpdm(pdProduct.getId());
			pds.setGhdwId("");
			pds.setGhdwMc("");
			pds.setType(MinaGlobalConstants.IS_DRUG_OR_CONSUMABLE_1);
			pdDrugSupplierDao.insert(pds);
		}
	}

	@Transactional(readOnly = false)
	public void delete(PdProduct pdProduct) {
		super.delete(pdProduct);
		if(pdProduct.getImgAuthSite()!=null && !"".equals(pdProduct.getImgAuthSite())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdProduct.getImgAuthSite());
		}
		if(pdProduct.getImgRegisterSite1()!=null && !"".equals(pdProduct.getImgRegisterSite1())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdProduct.getImgRegisterSite1());
		}
		if(pdProduct.getImgRegisterSite2()!=null && !"".equals(pdProduct.getImgRegisterSite2())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdProduct.getImgRegisterSite2());
		}
		if(pdProduct.getImgRegisterSite3()!=null && !"".equals(pdProduct.getImgRegisterSite3())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdProduct.getImgRegisterSite3());
		}
		if(pdProduct.getImgLicenseSite1()!=null && !"".equals(pdProduct.getImgLicenseSite1())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdProduct.getImgLicenseSite1());
		}
		if(pdProduct.getImgLicenseSite2()!=null && !"".equals(pdProduct.getImgLicenseSite2())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdProduct.getImgLicenseSite2());
		}
		if(pdProduct.getImgLicenseSite3()!=null && !"".equals(pdProduct.getImgLicenseSite3())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdProduct.getImgLicenseSite3());
		}
		if(pdProduct.getImgLicenseSite4()!=null && !"".equals(pdProduct.getImgLicenseSite4())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdProduct.getImgLicenseSite4());
		}
		if(pdProduct.getImgProduct1()!=null && !"".equals(pdProduct.getImgProduct1())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdProduct.getImgProduct1());
		}
		if(pdProduct.getImgProduct2()!=null && !"".equals(pdProduct.getImgProduct2())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdProduct.getImgProduct2());
		}
		if(pdProduct.getImgProduct3()!=null && !"".equals(pdProduct.getImgProduct3())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdProduct.getImgProduct3());
		}
	}

	@Transactional(readOnly = false)
	public void update(PdProduct product) {
		PdDrugSupplier pds = pdDrugSupplierDao.getOne(product.getId(), MinaGlobalConstants.IS_DRUG_OR_CONSUMABLE_1);
		if (!StringUtils.isEmpty(product.getSupplierId())) {
			PdSupplier supplier = pdSupplierDao.get(product.getSupplierId());
			if (pds == null) {
				pds = new PdDrugSupplier();
				pds.setYpdm(product.getId());
				pds.setGhdwId(supplier.getId());
				pds.setGhdwMc(supplier.getSupplierName());
				pds.setType(MinaGlobalConstants.IS_DRUG_OR_CONSUMABLE_1);
				pdDrugSupplierDao.insert(pds);
			} else {
				pds.setGhdwId(supplier.getId());
				pds.setGhdwMc(supplier.getSupplierName());
				pdDrugSupplierDao.update(pds);
			}
		}else{
			if (pds == null) {
				pds = new PdDrugSupplier();
				pds.setYpdm(product.getId());
				pds.setGhdwId("");
				pds.setGhdwMc("");
				pds.setType(MinaGlobalConstants.IS_DRUG_OR_CONSUMABLE_1);
				pdDrugSupplierDao.insert(pds);
			} else {
				pds.setGhdwId("");
				pds.setGhdwMc("");
				pdDrugSupplierDao.update(pds);
			}
		}
		//产品绑定多个编码规则
		String encodingRule = product.getEncodingRule();
		product.setEncodingRule("");
		if(encodingRule!=null && !"".equals(encodingRule)){
			String [] strings = encodingRule.split(",");
			List<String> arrays = Arrays.asList(strings);
			List<PdProductRule> pdProductRules = new ArrayList<PdProductRule>();
			for(String str :arrays){
				PdProductRule pdProductRule = new PdProductRule();
				pdProductRule.setProductId(product.getId());
				pdProductRule.setRuleId(str);
				pdProductRule.setId(IdGen.uuid());
				pdProductRules.add(pdProductRule);
			}
			//先删除再添加
			pdProductRuleDao.deleteByProductId(product.getId());
			pdProductRuleDao.batchSave(pdProductRules);
		}
		PdProduct pdProduct = dao.get(product.getId());
		if(pdProduct.getImgAuthSite()!=null && !"".equals(pdProduct.getImgAuthSite()) &&"".equals(product.getImgAuthSite())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdProduct.getImgAuthSite());
		}
		if(pdProduct.getImgRegisterSite1()!=null && !"".equals(pdProduct.getImgRegisterSite1())&&"".equals(product.getImgRegisterSite1())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdProduct.getImgRegisterSite1());
		}
		if(pdProduct.getImgRegisterSite2()!=null && !"".equals(pdProduct.getImgRegisterSite2())&&"".equals(product.getImgRegisterSite2())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdProduct.getImgRegisterSite2());
		}
		if(pdProduct.getImgRegisterSite3()!=null && !"".equals(pdProduct.getImgRegisterSite3())&&"".equals(product.getImgRegisterSite3())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdProduct.getImgRegisterSite3());
		}
		if(pdProduct.getImgLicenseSite1()!=null && !"".equals(pdProduct.getImgLicenseSite1())&&"".equals(product.getImgLicenseSite1())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdProduct.getImgLicenseSite1());
		}
		if(pdProduct.getImgLicenseSite2()!=null && !"".equals(pdProduct.getImgLicenseSite2())&&"".equals(product.getImgLicenseSite2())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdProduct.getImgLicenseSite2());
		}
		if(pdProduct.getImgLicenseSite3()!=null && !"".equals(pdProduct.getImgLicenseSite3())&&"".equals(product.getImgLicenseSite3())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdProduct.getImgLicenseSite3());
		}
		if(pdProduct.getImgLicenseSite4()!=null && !"".equals(pdProduct.getImgLicenseSite4())&&"".equals(product.getImgLicenseSite4())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdProduct.getImgLicenseSite4());
		}
		if(pdProduct.getImgProduct1()!=null && !"".equals(pdProduct.getImgProduct1())&&"".equals(product.getImgProduct1())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdProduct.getImgProduct1());
		}
		if(pdProduct.getImgProduct2()!=null && !"".equals(pdProduct.getImgProduct2())&&"".equals(product.getImgProduct2())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdProduct.getImgProduct2());
		}
		if(pdProduct.getImgProduct3()!=null && !"".equals(pdProduct.getImgProduct3())&&"".equals(product.getImgProduct3())){
			//先删除再更新
			FileUtils.deleteFile(userfilesBasedir+"\\"+pdProduct.getImgProduct3());
		}
		//更新耗材
		product.setSynFlag(MinaGlobalConstants.SYN_NONE);
		product.preUpdate();
		pdProductDao.update(product);
	}

	@Transactional(readOnly = false)
	public void updateChargeCode(PdProduct pdProduct){
		pdProduct.setSynFlag(MinaGlobalConstants.SYN_NONE);
		pdProductDao.updateChargeCode(pdProduct);
	}

	@Transactional(readOnly = false)
	public String batchSave(List<PdProduct> list){
		List<PdProduct> l = eliminateRepeating(list);
		String rowsKey = (l.get(l.size()-1)).getRowsKey();
		l.remove(l.size()-1);
		for(int i = 0 ; i < l.size() ; i ++){
			l.get(i).setIsUrgent("a0");
			String id = CommonUtils.getRandomString(32);
			l.get(i).setId(id);
			l.get(i).setUpdateDate(new Date());
			l.get(i).setUpdateBy(UserUtils.getUser());
			l.get(i).setSynFlag(MinaGlobalConstants.SYN_NONE);
			//	pdProductDao.importSave(pdProduct);
		}
		try{
			pdProductDao.importSave(l);
		}catch(Exception e){
			System.out.println(e);
		}
		return rowsKey;
	}


	//根据Number去重
	private List<PdProduct> eliminateRepeating(List<PdProduct> list){
		List<PdProduct> idList = pdProductDao.findAllList(new PdProduct());
		PdProduct rowsProd = new PdProduct();
		String rowsKey = "";
		for(int i = 0 ; i < list.size() ; i ++){
			for(int k = 0 ; k < list.size() ; k ++){
				if(k != i){
					boolean c = list.get(i).getNumber().equals(list.get(k).getNumber());
					if(c){
						rowsKey += list.get(i).getRows()+",";
						list.remove(i);
						i -= 1;
						break;
					}
				}
			}

		}
		for(int i = 0 ; i < list.size() ; i ++){
			for(int j = 0 ; j < idList.size() ; j ++){
				String num1 = list.get(i).getNumber();
				String num2 = idList.get(j).getNumber();
				boolean b = num2.equals(num1);
				if(b){
					rowsKey += list.get(i).getRows()+",";
					list.remove(i);
					i -= 1;
					break;
				}
			}
		}
		rowsProd.setRowsKey(rowsKey);
		list.add(rowsProd);
		return list;
	}

	//扣减紧急产品剩余数量
	public Map<String,String> minusSurplusPurCount(PdProduct pdProduct){
		PdProduct prod = pdProductDao.get(pdProduct);
		Integer before = prod.getSurplusPurCount();
		Integer after = pdProduct.getSurplusPurCount();

		Map<String,String> result = new HashMap<String,String>();

		if(before >= after){//库存足够
			int now = before - after;
			pdProduct.setSurplusPurCount(now);
			pdProductDao.minusSurplusPurCount(pdProduct);
			result.put("code", "200");
			return result;
		}else{//库存不足
			result.put("code", "500");
			return result;
		}
	}

	//排重（number）
	public boolean eliminateRepetitive(PdProduct pdProduct){
		PdProduct prod = new PdProduct();
		String number = pdProduct.getNumber();
		prod.setNumber(number);
		List<PdProduct> findList = pdProductDao.check(prod);
		if(findList!=null && findList.size()!=0){
			return false;
		}else{
			return true;
		}
	}

	/**
	 * 获取产品详情包含供应商id
	 * @param id
	 * @return
	 */
	public PdProduct getOne(String id) {
		PdProduct product = pdProductDao.getOne(id);
		PdProductRule pdProductRule = new PdProductRule();
		pdProductRule.setProductId(id);
		List<PdProductRule> pdProductRules = pdProductRuleDao.findList(pdProductRule);
		//拼接多个编码规则
		String ruleId = "";
		String ruleName="";
		for(int i =0;i<pdProductRules.size();i++){
			ruleId +=pdProductRules.get(i).getRuleId();
			ruleName +=pdProductRules.get(i).getRuleName();
			if(i!=pdProductRules.size()-1){
				ruleId+=",";
				ruleName+=",";
			}
		}
		product.setEncodingRule(ruleId);
		product.setEncodingRuleName(ruleName);
		PdDrugSupplier pds = pdDrugSupplierDao.getOne(product.getId(), MinaGlobalConstants.IS_DRUG_OR_CONSUMABLE_1);
		if(pds!=null && pds.getGhdwId()!=null){
			product.setSupplierId(pds.getGhdwId());
		}
		return product;
	}

	/**
	 * 批量添加供应商
	 * @param ids
	 * @param supplierId
	 * @param supplierName
	 */
	@Transactional(readOnly = false)
	public void updateSupplier(String ids, String supplierId, String supplierName) {
		if (StringUtils.isEmpty(ids)
				|| StringUtils.isEmpty(supplierId)
				|| StringUtils.isEmpty(supplierName) ) {

		}else{
			HashMap<String, Object> map = new HashMap<String,Object>();
			map.put("supplierId", supplierId);
			map.put("supplierName", supplierName);
			map.put("ids", ids.split(","));
			map.put("type", MinaGlobalConstants.IS_DRUG_OR_CONSUMABLE_1);
			//先做批量删除
			pdDrugSupplierDao.batchDelete(map);
			List<PdDrugSupplier> drugSuppliers =  new ArrayList<PdDrugSupplier>();
			for(String id:ids.split(",")){
				PdDrugSupplier drugSupplier = new PdDrugSupplier();
				drugSupplier.setYpdm(id);
				drugSupplier.setGhdwId(supplierId);
				drugSupplier.setGhdwMc(supplierName);
				drugSupplier.setType(MinaGlobalConstants.IS_DRUG_OR_CONSUMABLE_1);
				drugSuppliers.add(drugSupplier);
			}
			//批量添加产品中间表
			if(drugSuppliers.size()>0){
				pdDrugSupplierDao.batchSave(drugSuppliers);
			}
		}

	}

	/**
	 * 批量导入
	 * @param dataList
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean saveBatchPdSupplier(List<PdProduct> dataList) {
		PdProductDao dao = sqlsession.getMapper(PdProductDao.class);
		PdSupplierDao supplierDao = sqlsession.getMapper(PdSupplierDao.class);
		PdDrugSupplierDao drugSupplierDao = sqlsession.getMapper(PdDrugSupplierDao.class);
		try{
			for (PdProduct pdProduct : dataList) {

				pdProduct.setSpm(ChineseCharToEnUtils.getAllFirstLetter(pdProduct.getName()));
				pdProduct.preInsert();
				pdProduct.setIsUrgent(MinaGlobalConstants.URGENT_TYPE_NO);
				pdProduct.setSynFlag(MinaGlobalConstants.SYN_NONE);
				dao.insert(pdProduct);
				PdDrugSupplier pds = new PdDrugSupplier();
				if (!StringUtils.isEmpty(pdProduct.getSupplierId())) {
					PdSupplier supplier = supplierDao.get(pdProduct.getSupplierId());
					pds.setYpdm(pdProduct.getId());
					pds.setGhdwId(supplier.getId());
					pds.setGhdwMc(supplier.getSupplierName());
					pds.setType(MinaGlobalConstants.IS_DRUG_OR_CONSUMABLE_1);
					drugSupplierDao.insert(pds);
				}else{
					pds.setYpdm(pdProduct.getId());
					pds.setGhdwId("");
					pds.setGhdwMc("");
					pds.setType(MinaGlobalConstants.IS_DRUG_OR_CONSUMABLE_1);
					drugSupplierDao.insert(pds);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 查询未同步列表
	 * @author jiangxz
	 * @date
	 * @param
	 * @return
	 */
	public List<PdProductBO> findUnsynchronizedList(PdProductBO pdProductBO){
		return pdProductDao.findUnsynchronizedList(pdProductBO);
	}

	/**
	 * 更新同步标识
	 * @param pdProductBO
	 */
	@Transactional(readOnly = false)
	public void updateSynFlag(PdProductBO pdProductBO){
		pdProductDao.updateSynFlag(pdProductBO);
	}

	//根据编号查询产品信息
	public PdProduct findByNumber(String productNumber) {
		return dao.findByNumber(productNumber);
	}

	//获取编码相关信息
	public Map<String, Object> getScanCode(String barcode1,String barcode2) throws  Exception{
		Map<String,Object> resultMap = new HashMap<String,Object>();
		if(barcode1 != null && !"".equals(barcode1) && barcode2 != null && !"".equals(barcode2)){
			//如果是一个条码，两个值相同，不同是两段条码
			String barcode;
			if(barcode1.equals(barcode2)){
				barcode = barcode1;
			}else{
				barcode = barcode1+barcode2;
			}
			int endIndex = getCodeLength(barcode);
			int startIndex = 2;
			if(endIndex==13){
				startIndex = 0;
			}
			String productNumber = barcode.substring(startIndex,endIndex);
			PdProduct pdProduct = this.findByNumber(productNumber);
			if(pdProduct!=null){
				PdProductRule pdProductRule = new PdProductRule();
				pdProductRule.setProductId(pdProduct.getId());
				List<PdProductRule> pdProductRules = pdProductRuleDao.findList(pdProductRule);
				if(pdProductRules!=null && pdProductRules.size()>0){
					Iterator it=pdProductRules.iterator();
					while(it.hasNext()) {
						PdProductRule pr = (PdProductRule) it.next();
						if(barcode.length()!=Integer.valueOf(pr.getTotalDigit())){
							it.remove();
						}
					}
					//如果绑定多条编码规则且编码规则长度一致 只能取其中一条作为扫码规则
					PdEncodingRule encodingRule = pdEncodingRuleDao.get(pdProductRules.get(0).getRuleId());
					PdEncodingRuleDetail pdEncodingRuleDetail = new PdEncodingRuleDetail();
					pdEncodingRuleDetail.setCodeId(pdProductRules.get(0).getRuleId());
					List<PdEncodingRuleDetail> pdEncodingRuleDetails = pdEncodingRuleDetailDao.findList(pdEncodingRuleDetail);
					//如果编码和条码为同一条
					if(barcode.length()==Integer.parseInt(encodingRule.getTotalDigit())){
						resultMap.put("code","200");
						int barLength = barcode.length();
						resultMap.put("secondCode",barcode.substring(endIndex,barLength));
						String temp = barcode;
						for(PdEncodingRuleDetail erd :pdEncodingRuleDetails){
							String key = erd.getValue();
							if(temp.startsWith(key)){
								int tempLength = temp.length();
								temp = temp.substring(key.length(),tempLength);
								if(MinaGlobalConstants.IDENTIFIER_TYPE_1.equals(erd.getType())){
									String value = temp.substring(0,Integer.parseInt(erd.getSize()));
									resultMap.put(key,value);
									tempLength = temp.length();
									temp = temp.substring(value.length(),tempLength);
								}else{
									String value = temp.substring(0,Integer.parseInt(erd.getLength()));
									resultMap.put(key,value);
									tempLength = temp.length();
									temp = temp.substring(value.length(),tempLength);
								}
							}else if ("#".equals(key)){
								int tempLength = temp.length();
								String value = temp.substring(0,Integer.parseInt(erd.getSize()));
								resultMap.put(key,value);
								tempLength = temp.length();
								temp = temp.substring(value.length(),tempLength);
							}
						}

					}
					else{
						resultMap.put("code","500");
						resultMap.put("msg","解析失败，扫描的编码与绑定的规则长度不一致");
					}
				}
				else{
					//没有绑定扫码规则
					resultMap.put("code","201");
				}
			}
			else{
				//没有绑定扫码规则
				resultMap.put("code","201");
			}

		}else{
			resultMap.put("code","500");
			resultMap.put("msg","解析失败，参数不正确");
		}
		return  resultMap;
	}

	//00(13)位为产品编号，//01(14)为产品编号//其他为(13)位为产品编号
	public static int getCodeLength(String barcode){
		String startStr = barcode.substring(0, 2);
		if("01".equals(startStr)){
			return 16;
		}
		if("00".equals(startStr)){
			return 20;
		}
		return 13;
	}

	/**
	 * 提供select2 查询的方法
	 * @param pdProduct
	 * @return
	 */
	public List<PdProduct> findSelectList(PdProduct pdProduct) {
		return  pdProductDao.findSelectList(pdProduct);
	}

	/**
	 * 解决产品列表编号查询bug
	 * @param page
	 * @param pdProduct
	 * @return
	 */
	public Page<PdProduct> basicFindListOne(Page<PdProduct> page, PdProduct pdProduct) {
		pdProduct.setPage(page);
		page.setList(pdProductDao.basicFindListOne(pdProduct));
		return page;

	}
}
