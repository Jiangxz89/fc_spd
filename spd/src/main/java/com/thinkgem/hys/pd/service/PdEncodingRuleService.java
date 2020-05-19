/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import com.thinkgem.hys.pd.dao.PdEncodingRuleDao;
import com.thinkgem.hys.pd.dao.PdEncodingRuleDetailDao;
import com.thinkgem.hys.pd.entity.PdEncodingRule;
import com.thinkgem.hys.pd.entity.PdEncodingRuleDetail;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 编码规则表Service
 * @author zxh
 * @version 2019-04-22
 */
@Service
@Transactional(readOnly = true)
public class PdEncodingRuleService extends CrudService<PdEncodingRuleDao, PdEncodingRule> {

	@Autowired
	private PdEncodingRuleDetailDao pdEncodingRuleDetailDao;

	public PdEncodingRule get(String id) {
		return super.get(id);
	}

	public List<PdEncodingRule> findList(PdEncodingRule pdEncodingRule) {
		return super.findList(pdEncodingRule);
	}

	public Page<PdEncodingRule> findPage(Page<PdEncodingRule> page, PdEncodingRule pdEncodingRule) {
		return super.findPage(page, pdEncodingRule);
	}

	public Page<PdEncodingRule> findPageTwo(Page<PdEncodingRule> page, PdEncodingRule pdEncodingRule) {
		page.setPageSize(100);
		return super.findPage(page, pdEncodingRule);
	}

	@Transactional(readOnly = false)
	public void save(PdEncodingRule pdEncodingRule) {
		super.save(pdEncodingRule);
	}

	@Transactional(readOnly = false)
	public void delete(PdEncodingRule pdEncodingRule) {
		super.delete(pdEncodingRule);
	}

	/**
	 * 查询编码规则包括编码详细信息
	 * @param pdEncodingRule
	 * @return
	 */
	public PdEncodingRule getpdEncodingRule(PdEncodingRule pdEncodingRule) {
		PdEncodingRuleDetail pdEncodingRuleDetail = new PdEncodingRuleDetail();
		pdEncodingRuleDetail.setCodeId(pdEncodingRule.getId());
		List<PdEncodingRuleDetail> pdEncodingRuleDetails = pdEncodingRuleDetailDao.findList(pdEncodingRuleDetail);
		pdEncodingRule.setPdEncodingRuleDetails(pdEncodingRuleDetails);
		return pdEncodingRule;
	}

	//保存编码规则
	@Transactional(readOnly = false)
	public void savePdEncodingRule(PdEncodingRule pdEncodingRule) {
		pdEncodingRule.preInsert();
		List<PdEncodingRuleDetail> pdEncodingRuleDetails = pdEncodingRule.getPdEncodingRuleDetails();
		if(pdEncodingRuleDetails!=null && pdEncodingRuleDetails.size()>0){
			//调整顺序前清空元素
			for (int m=0;m<pdEncodingRuleDetails.size();m++){
				if(pdEncodingRuleDetails.get(m).getIdentifier()==null){
					pdEncodingRuleDetails.remove(m);
				}
			}
			//调整顺序
			testComparatorSort(pdEncodingRuleDetails);
			//拼接完整编码
			String str ="";
			int totalDigit = 0;
			for (int m=0;m<pdEncodingRuleDetails.size();m++) {
				PdEncodingRuleDetail pdEncodingRuleDetail = pdEncodingRuleDetails.get(m);
				if(pdEncodingRuleDetail.getIdentifier()!=null){
					String kh = "";
					String ruleVule = pdEncodingRuleDetail.getValue();
					if(!"#".equals(ruleVule)){
						kh = "("+ruleVule+")";
					}
					int length = Integer.parseInt(pdEncodingRuleDetail.getLength());
					for(int i=0;i<length;i++){
						kh += "X";
					}
					str+=kh;
					if("#".equals(ruleVule)){
						totalDigit +=length+ruleVule.length()-1;
					}else{
						totalDigit +=length+ruleVule.length();
					}
					pdEncodingRuleDetail.preInsert();
					pdEncodingRuleDetail.setCodeId(pdEncodingRule.getId());
				}
			}
			pdEncodingRule.setCodeDesc(str);
			pdEncodingRule.setTotalDigit(String.valueOf(totalDigit));
			//保存编码规则
			dao.insert(pdEncodingRule);
			//批量添加应用标识符
			pdEncodingRuleDetailDao.batchSave(pdEncodingRuleDetails);

		}
	}

	/**
	 * 修改编码规则
	 * @param pdEncodingRule
	 */
	@Transactional(readOnly = false)
	public void updatePdEncodingRule(PdEncodingRule pdEncodingRule) {
		List<PdEncodingRuleDetail> pdEncodingRuleDetails = pdEncodingRule.getPdEncodingRuleDetails();
		if(pdEncodingRuleDetails!=null && pdEncodingRuleDetails.size()>0){
			//调整顺序前清空元素
			for (int m=0;m<pdEncodingRuleDetails.size();m++){
				if(pdEncodingRuleDetails.get(m).getIdentifier()==null){
					pdEncodingRuleDetails.remove(m);
				}
			}
			//调整顺序
			testComparatorSort(pdEncodingRuleDetails);
			//拼接完整编码
			String str ="";
			int totalDigit = 0;
			for (int m=0;m<pdEncodingRuleDetails.size();m++) {
				PdEncodingRuleDetail pdEncodingRuleDetail = pdEncodingRuleDetails.get(m);
				if(pdEncodingRuleDetail.getIdentifier()!=null){
					String kh = "";
					String ruleVule = pdEncodingRuleDetail.getValue();
					if(!"#".equals(ruleVule)){
						kh = "("+ruleVule+")";
					}
					int length = Integer.parseInt(pdEncodingRuleDetail.getLength());
					for(int i=0;i<length;i++){
						kh += "X";
					}
					str+=kh;
					if("#".equals(ruleVule)){
						totalDigit +=length+ruleVule.length()-1;
					}else{
						totalDigit +=length+ruleVule.length();
					}
					pdEncodingRuleDetail.preInsert();
					pdEncodingRuleDetail.setCodeId(pdEncodingRule.getId());
				}
			}
			pdEncodingRule.setCodeDesc(str);
			pdEncodingRule.setTotalDigit(String.valueOf(totalDigit));
			//保存编码规则
			dao.update(pdEncodingRule);
			//删除编码规则
			pdEncodingRuleDetailDao.deleteByPdEncodingRule(pdEncodingRule);
			//批量添加应用标识符
			pdEncodingRuleDetailDao.batchSave(pdEncodingRuleDetails);

		}

	}

	//集合对象排序
	public static List<PdEncodingRuleDetail> testComparatorSort(List<PdEncodingRuleDetail> pdEncodingRuleDetails){
		Collections.sort(pdEncodingRuleDetails,new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				if(o1 instanceof PdEncodingRuleDetail && o2 instanceof PdEncodingRuleDetail){
					PdEncodingRuleDetail e1 = (PdEncodingRuleDetail) o1;
					PdEncodingRuleDetail e2 = (PdEncodingRuleDetail) o2;
					return Integer.parseInt(e1.getCodeOrder()) - Integer.parseInt(e2.getCodeOrder());
				}
				throw new ClassCastException("不能转换为Emp类型");
			}
		});
		return pdEncodingRuleDetails;
	}



	/**
	 * 删除编码规则及详细信息
	 * @param pdEncodingRule
	 */
	@Transactional(readOnly = false)
	public void deletePdEncodingRule(PdEncodingRule pdEncodingRule) {
		dao.delete(pdEncodingRule);
		pdEncodingRuleDetailDao.updateByPdEncodingRule(pdEncodingRule);
	}

	//校验编码规则名称是否重复
	public List<PdEncodingRule> verify(PdEncodingRule pdEncodingRule) {
		return dao.verify(pdEncodingRule);
	}
}