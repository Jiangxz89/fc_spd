package com.thinkgem.hys.pd.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.dao.HisPurchaseOrderDao;
import com.thinkgem.hys.pd.dao.HisPurchaseOrderItemDao;
import com.thinkgem.hys.pd.entity.HisPurchaseOrder;
import com.thinkgem.hys.pd.entity.HisPurchaseOrderItem;
import com.thinkgem.hys.pd.entity.bo.HisPurchaseOrderBO;
import com.thinkgem.hys.pd.entity.bo.HisPurchaseOrderItemBO;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jiangxz
 * @description
 * @date 2019-5-6
 */
@Service
@Transactional(readOnly = true)
public class HisPurchaseOrderService extends CrudService<HisPurchaseOrderDao, HisPurchaseOrder> {

    @Autowired
    private HisPurchaseOrderItemDao hisPurchaseOrderItemDao;

    @Autowired
    private HisPurchaseOrderDao hisPurchaseOrderDao;
    //医院编号
    private static final String HOSPITAL_NUMBER = Global.getConfig("HOSPITAL_NUMBER");

    private static final String BASE_URL = Global.getConfig("SPD_CP_BASE_PRE_URL");
    private static final String SYNC_HIS_PURCHASE_ORDER_URL = Global.getConfig("SYNC_HIS_PURCHASE_ORDER_URL");

    @Transactional(rollbackFor = Exception.class)
    public void saveList(JSONArray orderArr) throws RuntimeException {

        List<HisPurchaseOrder> orderList = JSONArray.parseArray(orderArr.toJSONString(),HisPurchaseOrder.class);
        //1.入SPD库
        for (HisPurchaseOrder order : orderList) {
            super.save(order);
            List<HisPurchaseOrderItem> itemList = order.getItemList();
            if(itemList != null && itemList.size() > 0){
                for (HisPurchaseOrderItem item : itemList){
                    item.setPId(order.getId());
                    hisPurchaseOrderItemDao.insert(item);
                }
            }
        }
        //2.同步到中心平台
        syncPurchaseOrder();
    }

    public void syncPurchaseOrder() throws RuntimeException {

        List<HisPurchaseOrderBO> orderBoList = hisPurchaseOrderDao.findUnsynchronizedList(new HisPurchaseOrderBO());
        for(HisPurchaseOrderBO orderBO : orderBoList){
            List<HisPurchaseOrderItemBO> itemList = hisPurchaseOrderItemDao.findListByPid(orderBO.getId());
            orderBO.setItemList(itemList);
        }
        Map<String,Object> synMap = new HashMap<String,Object>();
        synMap.put("orderList",orderBoList);
        synMap.put("hospitalNumber",HOSPITAL_NUMBER);//医院代码

        String jsonPar = JSONObject.toJSONString(synMap);

        //同步接口
        JSONObject json = HttpUtil.httpPost(BASE_URL+SYNC_HIS_PURCHASE_ORDER_URL, jsonPar);

        if(json != null && json.containsKey("statusCode")){
            if(MinaGlobalConstants.SYNC_STATE_SUCCESS.equals(json.get("statusCode"))){
                //成功
                logger.info("采购订单同步成功");
                for (HisPurchaseOrderBO orderBO : orderBoList) {
                    orderBO.setSynFlag(MinaGlobalConstants.SYN_SUCCESS);
                    hisPurchaseOrderDao.updateSynFlag(orderBO);
                }
            }else{
                //失败
                logger.error("采购订单同步失败");
                throw new RuntimeException("采购订单同步失败");
            }
        }else{
            //失败
            logger.error("采购订单同步失败");
            throw new RuntimeException("采购订单同步失败");
        }
    }

    public List<HisPurchaseOrderBO> findListAll(HisPurchaseOrderBO bo) {
        List<HisPurchaseOrderBO> orderList = hisPurchaseOrderDao.findListForBusiness(bo);
        if(orderList != null && orderList.size() > 0){
            for (HisPurchaseOrderBO order : orderList){
//                HisPurchaseOrderItem item = new HisPurchaseOrderItem();
//                item.setPId(order.getId());
                List<HisPurchaseOrderItemBO> itemList = hisPurchaseOrderItemDao.findListByPid(order.getId());
                order.setItemList(itemList);
            }
        }
        return orderList;
    }

}
