package com.thinkgem.hys.pd.dao;

import com.thinkgem.hys.pd.entity.HisPurchaseOrder;
import com.thinkgem.hys.pd.entity.bo.HisPurchaseOrderBO;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * his_purchase_orderDAO接口
 * @author jiangxz
 * @version 2019-05-06
 */
@MyBatisDao
public interface HisPurchaseOrderDao extends CrudDao<HisPurchaseOrder> {

    List<HisPurchaseOrderBO> findListForBusiness(HisPurchaseOrderBO bo);

    List<HisPurchaseOrderBO> findUnsynchronizedList(HisPurchaseOrderBO bo);

    int updateSynFlag(HisPurchaseOrderBO bo);
}