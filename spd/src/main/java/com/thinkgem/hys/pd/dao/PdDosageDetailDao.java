/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import com.thinkgem.hys.pd.entity.PdDosageDetail;
import com.thinkgem.hys.pd.entity.PdDosagertDetail;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import java.util.HashMap;
import java.util.List;

/**
 * 器械用量详情DAO接口
 * @author wg
 * @version 2018-03-06
 */
@MyBatisDao
public interface PdDosageDetailDao extends CrudDao<PdDosageDetail> {
	/**
	 * 批量插入
	 */
	public int batchInsert(List<PdDosageDetail> list);
	/**
	 * 批量更新收费产品
	 */
	public int updateIsCharge(HashMap<String,String> map);

	/**
	 * 更新HIS计费ID
	 * @param pdDosageDetail
	 * @return
	 */
	public int updateHisChargeId(PdDosageDetail pdDosageDetail);

	/**
	 * 批量更新可退数量
	 * @param list
	 * @return
	 */
	public int updateLeftRefundNum(List<PdDosagertDetail> list);
	/**
	 * 查询可退回数大于0的数据
	 * @param pdDosageDetail
	 * @return
	 */
	public List<PdDosageDetail> findListByLeftRefundNum(PdDosageDetail pdDosageDetail);

    List<PdDosageDetail> findPdDosageDetailByIds(HashMap<String,String> map);
}