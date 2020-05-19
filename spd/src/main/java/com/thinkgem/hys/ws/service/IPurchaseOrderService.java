package com.thinkgem.hys.ws.service;

import com.alibaba.fastjson.JSONObject;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * WS接口服务端
 * @author jiangxz
 * @description 采购订单传送接口,HIS编制采购订单后向SPD平台发送采购订单信息
 * @date 2019-5-5
 */
@WebService
public interface IPurchaseOrderService {

    public String sendPurchaseOrderToSpd(@WebParam(name="purchaseOrder") String purchaseOrder);
}
