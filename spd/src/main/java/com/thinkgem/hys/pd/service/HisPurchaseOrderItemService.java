package com.thinkgem.hys.pd.service;

import com.thinkgem.hys.pd.dao.HisPurchaseOrderItemDao;
import com.thinkgem.hys.pd.entity.HisPurchaseOrderItem;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jiangxz
 * @description
 * @date 2019-5-6
 */
@Service
@Transactional(readOnly = true)
public class HisPurchaseOrderItemService extends CrudService<HisPurchaseOrderItemDao, HisPurchaseOrderItem> {
}
