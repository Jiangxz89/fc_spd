package com.thinkgem.hys.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.config.Global;
import org.apache.axiom.om.*;
import org.apache.axiom.soap.SOAP12Constants;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/** 
 * 描述：axis2调用webservice工具类
 * @author zhengjinlei 
 * @version 2017年10月20日 下午2:51:07 
 */
public class AxisUtils {
	
	private static String defaultNamespace;
	private static String drugInfoUrl;
	private static String chargeUrl;
	private static String chargeCodeUrl;
	
	static {
		defaultNamespace = Global.getConfig("HIS_DEFAULT_NAME_SPACE");
		drugInfoUrl = Global.getConfig("HIS_DRUG_INFO_URL");
		chargeUrl = Global.getConfig("HIS_CHARGE_URL");
		chargeCodeUrl = Global.getConfig("HIS_CHARGE_CODE_URL");
	}
	
	private static Logger logger = LoggerFactory.getLogger(AxisUtils.class);
	private static OMFactory fac = OMAbstractFactory.getOMFactory(); 
	private static OMElement value=null;
	
	//默认命名空间
	//public static final String defaultNamespace = "http://tempuri.org/";
	public static final String noneStr = "token=MD5(ZCLT8ZRE4gRo$LZg+2018-05-05)";
	
	//spd药品获取基本信息
	//public static final String drugInfoUrl = "http://spd.tunnel.qydev.com/GetBaseInfo.asmx?wsdl";
	//spd/药品获取入库信息
	//public static final String drugInfoUrl = "http://192.168.0.123:8080/GetBaseInfo.asmx?wsdl";
	//收费
	//public static final String chargeUrl = "http://192.168.0.123:8080/ChargeAndRefund.asmx?wsdl";
	
	//药品采购
	
	
	/**
	 * 获取发送客户端
	 * @param soapUrl 调用地址
	 * @param actionUrl 方法地址
	 * @return
	 * @throws AxisFault
	 */
	public static ServiceClient getClientSend(String soapUrl,String actionUrl) throws AxisFault{
		EndpointReference targetEPR = new EndpointReference(soapUrl);
		Options options = new Options();
		options.setManageSession(true);
		options.setTimeOutInMilliSeconds(9000L);
		options.setSoapVersionURI(SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI);// 设定SOAP版本soap1.2
		options.setProperty(HTTPConstants.REUSE_HTTP_CLIENT, true);
        options.setAction(actionUrl);
        options.setTo(targetEPR);  
        options.setProperty(HTTPConstants.CHUNKED, "false");  
        ServiceClient sender = new ServiceClient();  
	    sender.setOptions(options);  
	    return sender;
	}
	public static List<Map<String,String>> getLisData(List<String> list) throws AxisFault{
		String lisUrl="";		
		ServiceClient sender = getClientSend(lisUrl, "http://tempuri.org/LisGetRepList");
		OMNamespace omNs = fac.createOMNamespace("http://tempuri.org/", "");
		OMElement method = fac.createOMElement("LisGetRepList", omNs);
		OMElement name = fac.createOMElement("UserName", omNs);// 设置入参名称  
	    OMElement name2 = fac.createOMElement("UserPwd", omNs);// 设置入参名称  
	    OMElement name3 = fac.createOMElement("PatName", omNs);// 设置入参名称  
	    OMElement name4 = fac.createOMElement("PatID", omNs);// 设置入参名称  
	    
    	name.setText(list.get(0));// 设置入参值  
 	    name2.setText(list.get(1));  
 	    name3.setText(list.get(2));  
 	    name4.setText(list.get(3));
	   
	    method.addChild(name);
	    method.addChild(name2);
	    method.addChild(name3);
	    method.addChild(name4);
	    method.build();  
	    OMElement response = sender.sendReceive(method); 
	    //sender.cleanupTransport();
	    OMElement newDataSet = getTheNodeValue(response, "NewDataSet");
	    List<Map<String,String>> arrayList = new ArrayList<Map<String,String>>();
	    if(newDataSet==null){
	    	return arrayList;
	    }
	    Iterator iterator = newDataSet.getChildElements();
	    Map<String,String> results=new HashMap<String,String>();	    
	    while (iterator.hasNext()) {
			OMElement element = (OMElement) iterator.next();
			results = getResults(element);
			arrayList.add(results);
			System.out.println(JSON.toJSONString(results));
			
		}
	    return arrayList;
	}
	
	public static void main(String[] args) throws AxisFault, NoSuchAlgorithmException, UnsupportedEncodingException {
		/*System.out.println(getBaseInfo("1", "1000", ""));
		System.out.println(getBaseInfo("2", "10", ""));
		System.out.println(getBaseInfo("3", "90", ""));*/
		//System.out.println(exeCharge("446", "123", "3", "10001135", "0", "zhangsan", "2018-05-08 11:04:09", "", ""));
		//System.out.println(getDrugStorage("2018010200:00:00", "2018010223:59:59"));
		//System.out.println(getDrugStock());
		//System.out.println(getBaseInfo("1", "", ""));
		String result = "{'code':'0','msg':'查询成功','data':[{'手术编号':'11542','所属病区编码':'647','所属病区':'肝胆外科','所属科室编码':'647','所属科室':'肝胆外科','手术科室':'40','申请医生姓名编码':'1214','申请医生姓名':'刘新海','门诊号':'00060847','姓名':'李海宇','住院号':'00060847','床位号':'9830','性别':'男','登记日期':'2019/7/27 18:47:00'},{'手术编号':'11561','所属病区编码':'647','所属病区':'肝胆外科','所属科室编码':'647','所属科室':'肝胆外科','手术科室':'40','申请医生姓名编码':'1214','申请医生姓名':'刘新海','门诊号':'00060847','姓名':'李海宇','住院号':'00060847','床位号':'9830','性别':'男','登记日期':'2019/7/29 14:17:00'}]}";
		JSONObject json = JSONObject.parseObject(result);
		System.out.println(json);
	}
	
	
	public static String getJsonDataFromWebservice(String appointUrl,String appointNamespace,String appointMethod, Map<String,Object> params) throws AxisFault{
		ServiceClient sender = getClientSend(appointUrl, appointNamespace + appointMethod);
		OMNamespace omNs = fac.createOMNamespace(appointNamespace, "");
		OMElement method = fac.createOMElement(appointMethod, omNs);
		for(String key:params.keySet()){
			OMElement name = fac.createOMElement(key, omNs);// 设置入参名称  
			name.setText((String)params.get(key));
			method.addChild(name);
		}
		method.build();  
	    OMElement response = sender.sendReceive(method);
	    OMElement element = response.getFirstElement();
		return element.getText();
	}
	
	public static OMElement getTheNodeValue(OMElement omElement, String nodeName) {
        try {
            Iterator desc = omElement.getChildElements();
            value = null;
            while (desc.hasNext()) {
                OMElement element = (OMElement) desc.next();
                OMElement e = element.getFirstChildWithName(new QName(nodeName));
                if (e == null && element.getQName().equals(new QName(nodeName))) {
                    e = element;
                }
                if (e != null) {
                    value = e;
                    break;
                } else {
                    if (value == null) {
                        getTheNodeValue(element, nodeName);
                    }
                }
            }
            return value;

        } catch (Exception e) {
            return null;
        }

	}

	    /**
	     * 遍历全部节点，将节点放入Map返回
	     *
	     * @param element
	     * @return
	     */
    public static Map<String,String> getResults(OMElement element) {
        if (element == null) {
            return null;
        }
        Iterator iter = element.getChildElements();
        Map<String,String> map = new HashMap<String,String>();
        while (iter.hasNext()) {
            OMNode omNode = (OMNode) iter.next();
            if (omNode.getType() == OMNode.ELEMENT_NODE) {
                OMElement omElement = (OMElement) omNode;
                String key = omElement.getLocalName().trim();
                //System.out.println("sta: " + key);
                String value = omElement.getText().trim();
                map.put(key, value);
            }
        }
        return map;
    }
    
    /**
     * 收费接口 
     * @param chargeCode
     * @param productNo
     * @param productNum
     * @param inHospitalNo
     * @param chargeType    0：正常收费；1：补计费
     * @param oprtPeople
     * @param oprtDate
     * @param remark
     * @param token
     * @return
     */
    public static JSONObject exeCharge(String chargeCode, String productNo, String productNum,
    		String inHospitalNo, String chargeType, String oprtPeople, String oprtDate, String remark, String token,String ssbm) {
        JSONObject json = new JSONObject();
        //参数校验
        if(chargeCode==null ||"".equals(chargeCode)|| productNum==null ||"".equals(productNum) ||
				inHospitalNo==null ||"".equals(inHospitalNo)|| ssbm==null ||"".equals(ssbm)){
            json.put("code", "-200");
            json.put("msg", "参数异常");
            return json;
        }
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("cxbm", chargeCode);
    	params.put("cxlx", productNo);
    	params.put("cxsl", productNum);
    	params.put("zyh", inHospitalNo);
    	params.put("ssbm", ssbm);
    	params.put("sflx", chargeType);
    	params.put("czr", oprtPeople);
    	//params.put("czr", "1214");
    	params.put("czsj", oprtDate);
    	params.put("remark", remark);
    	params.put("token", token);
    	String result = null;
    	try {
			result = getJsonDataFromWebservice(chargeUrl, defaultNamespace, "Charges", params);
			json = JSONObject.parseObject(result);
			if (!"0".equals(json.get("code"))) {
    			json.put("code", "-200");
    			json.put("msg", "执行收费接口出错");
    		}
		} catch (AxisFault e) {
			e.printStackTrace();
			logger.info("******调用HIS收费接口出现错误！--->{}******", result);
			json.put("code", "-200");
			json.put("msg", "调用HIS收费接口出现错误");
		} catch (JSONException ee) {
    		ee.printStackTrace();
    		logger.info("******调用HIS收费接口JSON转换返回信息出现错误！--->{}******", result);
    		json.put("code", "-200");
    		json.put("msg", "调用HIS收费接口JSON转换返回信息出现错误！");
    	}
    	return json;
    }
    /**
     * 退费接口 
     * @param chargeCode
     * @param productNum
     * @param inHospitalNo
     * @param refundType 0：正常退费；
     * @param oprtPeople
     * @param oprtDate
     * @param remark
     * @param token
     * @return
     */
    public static JSONObject exeRefund(String chargeCode, String productNo,String productNum,
    		String inHospitalNo, String refundType, String oprtPeople, String oprtDate, String remark, String token,String ssbm) {
        //参数校验
        JSONObject json = new JSONObject();
		if(chargeCode==null ||"".equals(chargeCode)|| productNum==null ||"".equals(productNum) ||
				inHospitalNo==null ||"".equals(inHospitalNo)|| ssbm==null ||"".equals(ssbm)){
			json.put("code", "-200");
			json.put("msg", "参数异常");
			return json;
		}
    	Map<String, Object> params = new HashMap<String, Object>();
		params.put("cxbm", chargeCode);
		params.put("cxlx", productNo);
		params.put("cxsl", "-"+productNum);
		params.put("zyh", inHospitalNo);
		params.put("ssbm", ssbm);
		params.put("sflx", refundType);
		params.put("czr", oprtPeople);
		//params.put("czr", "1214");
		params.put("czsj", oprtDate);
		params.put("remark", remark);
		params.put("token", token);
    	String result = null;
    	try {
    		result = getJsonDataFromWebservice(chargeUrl, defaultNamespace, "Charges", params);
    		json = JSONObject.parseObject(result);
    		if (!"0".equals(json.get("code"))) {
    			json.put("code", "-200");
    		}
    	} catch (AxisFault e) {
    		e.printStackTrace();
    		json.put("code", "-200");
    		logger.info("******调用HIS退费接口出现错误！--->{}******", result);
    	} catch (JSONException ee) {
    		ee.printStackTrace();
    		logger.info("******调用HIS退费接口JSON转换返回信息出现错误！--->{}******", result);
    		json.put("code", "-200");
    	}
    	return json;
    }
    
    /**
     * 
     * @param queryType
     *  查询病人信息：1；
		查询医生信息：2；
		查询科室信息：3；
     * @param queryCode
     *  查询病人信息：1000；
		查询医生信息：10、11；
		查询科室信息：90；
     * @param remark
     * @return
     */
    public static JSONObject getBaseInfo(String queryType, String queryCode, String remark) {
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("cxlx", queryType);
    	params.put("cxbm", queryCode);
    	params.put("remark", remark);
    	JSONObject json = new JSONObject();
    	try {
    		String result = getJsonDataFromWebservice(drugInfoUrl, defaultNamespace, "GetBaseInformation", params);
    		json = JSONObject.parseObject(result);
    		if (!"0".equals(json.get("code"))) {
    			json.put("code", "-200");
    		}
    	} catch (AxisFault e) {
    		e.printStackTrace();
    		logger.info("******调用HIS查询基础信息接口出现错误！******");
    		json.put("code", "-200");
    	} catch (JSONException ee) {
    		ee.printStackTrace();
    		json.put("code", "-200");
    		logger.info("******调用HIS查询基础信息接口JSON转换返回信息出现错误！******");
    	}
    	return json;
    }
    
    
    //获取药品信息
    public static JSONObject getDrugInfo() {
    	Map<String, Object> params = new HashMap<String, Object>();
    	JSONObject json = new JSONObject();
    	try {
    		String result = getJsonDataFromWebservice(drugInfoUrl, defaultNamespace, "GetCDML", params);
    		json = JSONObject.parseObject(result);
    	} catch (AxisFault e) {
    		e.printStackTrace();
    		logger.info("******调用HIS查询药品基础信息接口出现错误！******");
    		json.put("code", "-200");
    	} catch (JSONException ee) {
    		ee.printStackTrace();
    		json.put("code", "-200");
    		logger.info("******调用HIS查询药品基础信息接口JSON转换返回信息出现错误！******");
    	}
    	return json;
    }
    
    //获取药品库存信息
    public static JSONObject getDrugStock() {
    	Map<String, Object> params = new HashMap<String, Object>();
    	JSONObject json = new JSONObject();
    	try {
    		String result = getJsonDataFromWebservice(drugInfoUrl, defaultNamespace, "GetYKKC", params);
    		json = JSONObject.parseObject(result);
    	} catch (AxisFault e) {
    		e.printStackTrace();
    		logger.info("******调用HIS查询药品库存信息接口出现错误！******");
    		json.put("code", "-200");
    	} catch (JSONException ee) {
    		ee.printStackTrace();
    		json.put("code", "-200");
    		logger.info("******调用HIS查询药品库存信息接口JSON转换返回信息出现错误！******");
    	}
    	return json;
    }
    //获取药品库存明细信息
    public static JSONObject getDrugStockDetail() {
    	Map<String, Object> params = new HashMap<String, Object>();
    	JSONObject json = new JSONObject();
    	try {
    		String result = getJsonDataFromWebservice(drugInfoUrl, defaultNamespace, "GetYKKCMX", params);
    		json = JSONObject.parseObject(result);
    	} catch (AxisFault e) {
    		e.printStackTrace();
    		logger.info("******调用HIS查询药品库存明细接口出现错误！******");
    		json.put("code", "-200");
    	} catch (JSONException ee) {
    		ee.printStackTrace();
    		json.put("code", "-200");
    		logger.info("******调用HIS查询药品库存明细接口JSON转换返回信息出现错误！******");
    	}
    	return json;
    }
    //获取药品退货账单信息
    public static JSONObject getDrugRefund(String startDate, String endDate) {
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("kssj", startDate);
    	params.put("jssj", endDate);
    	JSONObject json = new JSONObject();
    	try {
    		String result = getJsonDataFromWebservice(drugInfoUrl, defaultNamespace, "GetTHZD", params);
    		json = JSONObject.parseObject(result);
    	} catch (AxisFault e) {
    		e.printStackTrace();
    		logger.info("******调用HIS查询药品退货账单接口出现错误！******");
    		json.put("code", "-200");
    	} catch (JSONException ee) {
    		ee.printStackTrace();
    		json.put("code", "-200");
    		logger.info("******调用HIS查询药品退货账单接口JSON转换返回信息出现错误！******");
    	}
    	return json;
    }
    //获取药品退货账单明细信息
    public static JSONObject getDrugRefundDetail(String startDate, String endDate) {
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("kssj", startDate);
    	params.put("jssj", endDate);
    	JSONObject json = new JSONObject();
    	try {
    		String result = getJsonDataFromWebservice(drugInfoUrl, defaultNamespace, "GetTHMX", params);
    		json = JSONObject.parseObject(result);
    	} catch (AxisFault e) {
    		e.printStackTrace();
    		logger.info("******调用HIS查询药品退货账单明细接口出现错误！******");
    		json.put("code", "-200");
    	} catch (JSONException ee) {
    		ee.printStackTrace();
    		json.put("code", "-200");
    		logger.info("******调用HIS查询药品退货账单明细接口JSON转换返回信息出现错误！******");
    	}
    	return json;
    }
    //获取药品入库账单信息
    public static JSONObject getDrugStorage(String startDate, String endDate) {
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("kssj", startDate);
    	params.put("jssj", endDate);
    	JSONObject json = new JSONObject();
    	try {
    		String result = getJsonDataFromWebservice(drugInfoUrl, defaultNamespace, "GetRKZD", params);
    		json = JSONObject.parseObject(result);
    	} catch (AxisFault e) {
    		e.printStackTrace();
    		logger.info("******调用HIS查询药品入库账单接口出现错误！******");
    		json.put("code", "-200");
    	} catch (JSONException ee) {
    		ee.printStackTrace();
    		json.put("code", "-200");
    		logger.info("******调用HIS查询药品入库账单接口JSON转换返回信息出现错误！******");
    	}
    	return json;
    }
    //获取药品入库账单明细信息
    public static JSONObject getDrugStorageDetail(String startDate, String endDate) {
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("kssj", startDate);
    	params.put("jssj", endDate);
    	JSONObject json = new JSONObject();
    	try {
    		String result = getJsonDataFromWebservice(drugInfoUrl, defaultNamespace, "GetRKMX", params);
    		json = JSONObject.parseObject(result);
    	} catch (AxisFault e) {
    		e.printStackTrace();
    		logger.info("******调用HIS查询药品入库账单明细接口出现错误！******");
    		json.put("code", "-200");
    	} catch (JSONException ee) {
    		ee.printStackTrace();
    		json.put("code", "-200");
    		logger.info("******调用HIS查询药品入库账单明细接口JSON转换返回信息出现错误！******");
    	}
    	return json;
    }

	/**
	 * TODO 查询收费代码
	 * @return
	 */
	public static JSONObject getHisChargeCode() {
		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("cxlx", queryType);
//		params.put("cxbm", queryCode);
//		params.put("remark", remark);
		JSONObject json = new JSONObject();
		try {
			String result = getJsonDataFromWebservice(chargeCodeUrl, defaultNamespace, "getHisChargeCode", params);
			json = JSONObject.parseObject(result);
			if (!"0".equals(json.get("code"))) {
				json.put("code", "-200");
			}
		} catch (AxisFault e) {
			e.printStackTrace();
			logger.info("******调用HIS查询收费代码接口出现错误！******");
			json.put("code", "-200");
		} catch (JSONException ee) {
			ee.printStackTrace();
			json.put("code", "-200");
			logger.info("******调用HIS查询收费代码接口JSON转换返回信息出现错误！******");
		}
		return json;
	}


	/**
	 * 根据住院号查询病人信息
	 * @param inHospital
	 * @return
	 */
	public static JSONObject getBaseInfoByInHospital(String inHospital,String operativeNumber) {
		JSONObject json = new JSONObject();
        if(StringUtils.isEmpty(inHospital) &&  StringUtils.isEmpty(operativeNumber)){
            json.put("code", "-200");
            json.put("msg","住院号或申请编号不可为空");
            return json;
        }
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cxbm", inHospital);
		params.put("cxappno", operativeNumber);
		try {
			//String result = getJsonDataFromWebservice(chargeUrl, defaultNamespace, "GetSurgInfo", params);
			String result = getJsonDataFromWebservice(chargeUrl, defaultNamespace, "GetApplication", params);
			//String result="{'code':'0','msg':'查询成功','data':[{'手术编号':'12684','所属病区编码':'624','手术名称':'深静脉置管术','所属病区':'肝病科二区','所属科室编码':'624','所属科室':'肝病科二区','手术科室':'手术室','申请医生姓名编码':'749','申请医生姓名':'胡江玲','门诊号':'00065299','姓名':'刘荣祜','住院号':'00065299','床位号':'9451','性别':'男','登记日期':'2019/12/2 23:09:00','申请编号':'12684','住院标识':'1'},{'手术编号':'12696','所属病区编码':'624','手术名称':'无痛胃镜介入治疗','所属病区':'肝病科二区','所属科室编码':'624','所属科室':'肝病科二区','手术科室':'手术室','申请医生姓名编码':'749','申请医生姓名':'胡江玲','门诊号':'00065299','姓名':'刘荣祜','住院号':'00065299','床位号':'9451','性别':'男','登记日期':'2019/12/3 12:52:00','申请编号':'12696','住院标识':'1'}]}";
			//String result="{'code':'0','msg':'查询成功','data':[{'手术编号':'1124080','所属病区编码':'649','手术名称':'','所属病区':'内科三区','所属科室编码':'649','所属科室':'内科三区','手术科室':'','申请医生姓名编码':'413','申请医生姓名':'叶远飞','门诊号':'1500017512','姓名':'舒启凡','住院号':'1500017512','床位号':'','性别':'男','登记日期':'2019/12/4 8:16:52','申请编号':'2019120400022','住院标识':'2'}]}";
			json = JSONObject.parseObject(result);
			if (!"0".equals(json.get("code"))) {
				json.put("code", "-200");
                json.put("msg","查询失败，未找到记录");
			}
		} catch (AxisFault e) {
			e.printStackTrace();
			logger.info("******调用HIS根据住院号查询病人信息接口出现错误！******");
			json.put("code", "-200");
            json.put("msg","调用HIS根据住院号查询病人信息接口出现错误！");
		} catch (JSONException ee) {
			ee.printStackTrace();
			json.put("code", "-200");
			logger.info("******调用HIS根据住院号查询病人信息接口JSON转换返回信息出现错误！******");
            json.put("msg","调用HIS根据住院号查询病人信息接口JSON转换返回信息出现错误！");
		}
		return json;
	}


}
