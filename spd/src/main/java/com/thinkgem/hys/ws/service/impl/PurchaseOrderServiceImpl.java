package com.thinkgem.hys.ws.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.entity.HisPurchaseOrder;
import com.thinkgem.hys.pd.service.HisPurchaseOrderService;
import com.thinkgem.hys.ws.service.IPurchaseOrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * WS接口服务端
 *
 * @author jiangxz
 * @description 采购订单传送接口, HIS编制采购订单后向SPD平台发送采购订单信息
 * @date 2019-5-5
 */
@Service
@WebService(endpointInterface = "com.thinkgem.hys.ws.service.IPurchaseOrderService")
public class PurchaseOrderServiceImpl implements IPurchaseOrderService {

    @Autowired
    private HisPurchaseOrderService hisPurchaseOrderService;

    private static Logger logger = Logger.getLogger(PurchaseOrderServiceImpl.class);

    /**
     * 采购订单接口
     * @param purchaseOrder
     * @return
     */
    @Override
    public String sendPurchaseOrderToSpd(String purchaseOrder) {
        Map<String,Object> retMap = new HashMap<String,Object>();

        if(purchaseOrder == null || "".equals(purchaseOrder.trim())){
            retMap.put("code", MinaGlobalConstants.FAIL);
            retMap.put("msg", "推送数据为空");

            return JSON.toJSONString(retMap);
        }

        try{
            JSONArray orderArr = JSONObject.parseArray(purchaseOrder);
            if(orderArr != null && orderArr.size() > 0){
//            List<HisPurchaseOrder> list = JSONObject.parseArray(purchaseOrder,HisPurchaseOrder.class);
                hisPurchaseOrderService.saveList(orderArr);
                retMap.put("code", MinaGlobalConstants.SUCCESS);
                retMap.put("msg", "采购订单推送成功");
            }else{
                retMap.put("code", MinaGlobalConstants.FAIL);
                retMap.put("msg", "推送数据为空");
            }

            return JSON.toJSONString(retMap);
        }catch (Exception e){
            e.printStackTrace();
            retMap.put("code", MinaGlobalConstants.FAIL);
            retMap.put("msg", "采购订单推送失败，日志："+e.getMessage());
            //TODO 日志记录
            return JSON.toJSONString(retMap);
        }

//        HisPurchaseOrderBO bo = new HisPurchaseOrderBO();
//        bo.setId("1");
//        List<HisPurchaseOrderBO> list = hisPurchaseOrderService.findListAll(bo);
//        String str = JSON.toJSONString(list);

//        返回编码 code String (0-成功，-1-失败)
//        返回消息 msg String Y
    }

}
