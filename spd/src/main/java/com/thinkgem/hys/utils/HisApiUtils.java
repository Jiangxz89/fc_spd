package com.thinkgem.hys.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.entity.HisChargeCode;
import com.thinkgem.hys.pd.entity.HisPatient;
import com.thinkgem.hys.pd.entity.PdDosage;
import com.thinkgem.hys.pd.entity.PdDosageDetail;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.HttpUtil;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jiangxz
 * @description His接口
 * @date 2020-5-21
 */
public class HisApiUtils {

    private static String HIS_DEFAULT_NAME_SPACE = "";
    private static String HIS_CHARGE_CODE_URL = ""; // his收费代码
    private static String HIS_PATIENT_URL = "";     // his病人
    private static String HIS_CHARGE_URL = "";      // his收/退费

    static {
        HIS_DEFAULT_NAME_SPACE = Global.getConfig("HIS_DEFAULT_NAME_SPACE");
        HIS_CHARGE_CODE_URL = Global.getConfig("HIS_CHARGE_CODE_URL");
        HIS_PATIENT_URL = Global.getConfig("HIS_PATIENT_URL");
        HIS_CHARGE_URL = Global.getConfig("HIS_CHARGE_URL");
    }

    private static Logger logger = Logger.getLogger(HisApiUtils.class);

    public static JSONObject exeCharge(PdDosage pdDosage,List<PdDosageDetail> dosageDetailList){
        JSONObject returnJson = new JSONObject();
        if(pdDosage == null || StringUtils.isBlank(pdDosage.getVisitNo()) || CollectionUtils.isEmpty(dosageDetailList)){
            returnJson.put("statusCode", "-200");
            returnJson.put("msg", "参数异常");
            return returnJson;
        }

        try{
            JSONArray array = new JSONArray();
            for(PdDosageDetail detail : dosageDetailList){
                JSONObject item = new JSONObject();
                item.put("bby01",detail.getChargeCode());
                item.put("vaj25",detail.getDosageCount());
                item.put("vaj38",detail.getAmountMoney());
                item.put("bck01d",pdDosage.getExeDeptId());
                item.put("prodNo",detail.getProdNo());
                array.add(item);
            }

            JSONObject requestJson = new JSONObject();
            requestJson.put("vaa07",pdDosage.getVisitNo());
            requestJson.put("vak08",pdDosage.getAmountMoney());
            requestJson.put("bce02b", UserUtils.getUser().getName());
            requestJson.put("Item", array);

            // TODO
//          returnJson = HttpUtil.httpPost(HIS_DEFAULT_NAME_SPACE + HIS_CHARGE_URL, requestJson.toJSONString());
            returnJson = getTestChargeJson(pdDosage,dosageDetailList);

            if (!MinaGlobalConstants.SUCCESS.equals(returnJson.get("code"))) {
                returnJson.put("statusCode", "-200");
                returnJson.put("msg","计费失败，请重新计费或联系管理员！");
            }else{
                returnJson.put("statusCode", MinaGlobalConstants.SUCCESS);
            }

        } catch (JSONException ee) {
            ee.printStackTrace();
            returnJson.put("statusCode", "-200");
            returnJson.put("msg","调用HIS收费接口JSON转换返回信息出现错误！");
            logger.error("******调用HIS收费接口JSON转换返回信息出现错误！******");
        }

//        {"vaa07":"116326","vak08":"65","bce02b":"9909",
//    "Item":[{"bby01":"143","vaj25":"1","vaj38":"5","bck01d":"39","prodNo":"0001"},
//            {"bby01":"4294","vaj25":"1","vaj38":"60","bck01d":"39","prodNo":"0002"}]}

        return returnJson;
    }

    /**
     * 查询HIS项目收费代码
     * @param SFCODE
     * @param SFNAME
     * @return
     */
    public static JSONObject queryHisChargeCode(String SFCODE,String SFNAME) {
        JSONObject returnJson = new JSONObject();
        JSONObject requestJson = new JSONObject();
        requestJson.put("SFCODE",SFCODE);
        requestJson.put("SFNAME",SFNAME);

        // TODO
//        returnJson = HttpUtil.httpPost(HIS_DEFAULT_NAME_SPACE + HIS_CHARGE_CODE_URL, requestJson.toJSONString());
        returnJson = getTestChargeCodeJson(); // 测试数据

        return returnJson;
    }

    /**
     * 查询HIS病人信息
     * @param hitaionNo
     * @return
     */
    public static JSONObject queryHisPatientInfo(String hitaionNo) {
        JSONObject returnJson = new JSONObject();
        if(StringUtils.isBlank(hitaionNo)){
            returnJson.put("code", "-200");
            returnJson.put("msg","住院号不可为空");
            return returnJson;
        }

        JSONObject requestJson = new JSONObject();
        requestJson.put("hitaionNo",hitaionNo);

        try{
            // TODO
//            returnJson = HttpUtil.httpPost(HIS_DEFAULT_NAME_SPACE + HIS_PATIENT_URL, requestJson.toJSONString());
            returnJson = getTestPatientJson(); // 测试数据

            if (!MinaGlobalConstants.SUCCESS.equals(returnJson.get("code"))) {
                returnJson.put("statusCode", "-200");
                returnJson.put("msg","查询失败，未找到记录");
            }else{
                returnJson.put("statusCode", MinaGlobalConstants.SUCCESS);
            }

        } catch (JSONException ee) {
            ee.printStackTrace();
            returnJson.put("statusCode", "-200");
            returnJson.put("msg","调用HIS根据住院号查询病人信息接口JSON转换返回信息出现错误！");
            logger.info("******调用HIS根据住院号查询病人信息接口JSON转换返回信息出现错误！******");
        }
        return returnJson;
    }

    /**
     * 收费测试
     * @return
     */
    public static JSONObject getTestChargeJson(PdDosage pdDosage,List<PdDosageDetail> dosageDetailList){

        JSONArray array = new JSONArray();
        for(PdDosageDetail detail : dosageDetailList){
            JSONObject item = new JSONObject();
            item.put("vaa07",pdDosage.getVisitNo());// 就诊流水号
            item.put("vai01","10325748");// 计费单据id
            item.put("vaj01","33118756");// 计费单据明细ID（退费入参需要）
            item.put("prodNo",detail.getProdNo());// 产品编号
            array.add(item);
        }

        JSONObject json = new JSONObject();
        json.put("code","0");
        json.put("msg","成功");
        json.put("data",array);

        return json;
    }

    /**
     * 收费代码测试
     * @return
     */
    public static JSONObject getTestChargeCodeJson(){
//        {"code":"0","msg":"成功","data": [
//            {"SFCODE":"3","SFNO":"90103","SFNAME":"CT增强"}]}
        List<HisChargeCode> data = new ArrayList<>();
        HisChargeCode entity1 = new HisChargeCode();
        entity1.setSFCODE("111111");
        entity1.setSFNO("123123");
        entity1.setSFNAME("CT增强1");
        HisChargeCode entity2 = new HisChargeCode();
        entity2.setSFCODE("22222");
        entity2.setSFNO("1qazxsw2");
        entity2.setSFNAME("CT增强2");
        data.add(entity1);
        data.add(entity2);

        JSONObject json = new JSONObject();
        json.put("code","0");
        json.put("msg","成功");
        json.put("data",JSON.toJSONString(data));
        return json;
    }

    /**
     * 病人测试
     * @return
     */
    public static JSONObject getTestPatientJson(){
//        {"code":"0","msg":"成功","data": [
//        {"vaa07":"116326","operNo":"","blngDptmNo":"01000424","blngDptmName":"妇产二科病区",
//        "blngNo":"040004","blngName":"妇产科","type":"1","projectName":"","departName":"",
//        "createDate":"2018-11-01 10:12:28","doctorCode":"9216","doctorName":"熊清文",
//        "outpatCode":"","sex":"女","patientName":"陈婧","hitaionNo":"201814572","bedCode":"09"}
//        ]}
        JSONArray jsonArray = new JSONArray();
        JSONObject hisPatient = new JSONObject();
        hisPatient.put("vaa07","116326");                   //His患者就诊流水号
        hisPatient.put("operNo","");                        //手术编号（如果没有手术，可为空）
        hisPatient.put("blngDptmNo","01000424");            //所属病区编码
        hisPatient.put("blngDptmName","妇产二科病区");       //所属病区
        hisPatient.put("blngNo","040004");                  //所属科室编码
        hisPatient.put("blngName","妇产科");                //所属科室
        hisPatient.put("type","1");                        //住院标识（1：是  2：否）
        hisPatient.put("projectName","");                  //项目名称
        hisPatient.put("departName","");                   //手术或检查项目科室（如果没有手术，则是检查项目科室）
        hisPatient.put("createDate","2018-11-01 10:12:28");//登记日期
        hisPatient.put("doctorCode","9216");               //申请医生编码
        hisPatient.put("doctorName","熊清文");             //申请医生姓名
        hisPatient.put("outpatCode","");                   //门诊号
        hisPatient.put("sex","女");
        hisPatient.put("patientName","陈婧");             //病人姓名
        hisPatient.put("hitaionNo","201814572");          //住院号（如果不是住院，可为空）
        hisPatient.put("bedCode","09");                   //床位号（如果没有，可为空）

//        JSONObject hisPatient2 = new JSONObject();
//        hisPatient2.put("vaa07","1163261");                   //His患者就诊流水号
//        hisPatient2.put("operNo","1");                        //手术编号（如果没有手术，可为空）
//        hisPatient2.put("blngDptmNo","010004241");            //所属病区编码
//        hisPatient2.put("blngDptmName","妇产二科病区1");       //所属病区
//        hisPatient2.put("blngNo","0400041");                  //所属科室编码
//        hisPatient2.put("blngName","妇产科1");                //所属科室
//        hisPatient2.put("type","11");                        //住院标识（1：是  2：否）
//        hisPatient2.put("projectName","1");                  //项目名称
//        hisPatient2.put("departName","1");                   //手术或检查项目科室（如果没有手术，则是检查项目科室）
//        hisPatient2.put("createDate","2018-11-01 10:12:28");//登记日期
//        hisPatient2.put("doctorCode","92161");               //申请医生编码
//        hisPatient2.put("doctorName","熊清文1");             //申请医生姓名
//        hisPatient2.put("outpatCode","1");                   //门诊号
//        hisPatient2.put("sex","女");
//        hisPatient2.put("patientName","陈婧1");             //病人姓名
//        hisPatient2.put("hitaionNo","2018145721");          //住院号（如果不是住院，可为空）
//        hisPatient2.put("bedCode","091");                   //床位号（如果没有，可为空）

        jsonArray.add(hisPatient);
//        jsonArray.add(hisPatient2);

        JSONObject json = new JSONObject();
        json.put("code","0");
        json.put("msg","成功");
        json.put("data",jsonArray);

        return json;
    }

    public static void main(String[] args) {
        JSONObject json = getTestPatientJson();
        System.out.println("json = " + json);
        System.out.println("code = " + json.get("code"));
        System.out.println("msg = " + json.get("msg"));


        JSONArray jsonArray = json.getJSONArray("data");

//        HisPatient bean = (HisPatient) JSONObject.parseObject(jsonArray.getJSONObject(0).toJSONString(), HisPatient.class);

//        List<HisChargeCode> hisChargeCodeList = JSONArray.parseArray(jsonArray.toJSONString(),HisChargeCode.class);
        System.out.println("json = " + json.get("data"));
    }

}
