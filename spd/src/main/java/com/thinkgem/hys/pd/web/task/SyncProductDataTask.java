package com.thinkgem.hys.pd.web.task;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.entity.PdGroup;
import com.thinkgem.hys.pd.entity.bo.PdCategoryBO;
import com.thinkgem.hys.pd.entity.bo.PdGroupBO;
import com.thinkgem.hys.pd.entity.bo.PdProductBO;
import com.thinkgem.hys.pd.entity.bo.PdVenderBO;
import com.thinkgem.hys.pd.service.PdCategoryService;
import com.thinkgem.hys.pd.service.PdGroupService;
import com.thinkgem.hys.pd.service.PdProductService;
import com.thinkgem.hys.pd.service.PdVenderService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.HttpUtil;
import com.thinkgem.jeesite.common.utils.UploadUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jiangxz
 * @description
 * @date 2019-4-28
 */
@Service
public class SyncProductDataTask {
    @Autowired
    private PdGroupService pdGroupService;
    @Autowired
    private PdCategoryService pdCategoryService;
    @Autowired
    private PdVenderService pdVenderService;
    @Autowired
    private PdProductService pdProductService;

    private static Logger logger = Logger.getLogger(SyncProductDataTask.class);

    /**
     * 删除标记（0：正常；1：删除；2：审核；）
     */
    public static final String DEL_FLAG_NORMAL = "0";
    public static final String DEL_FLAG_DELETE = "1";
    public static final String DEL_FLAG_AUDIT = "2";

    //医院编号
    private static final String HOSPITAL_NUMBER = Global.getConfig("HOSPITAL_NUMBER");

    private static final String BASE_URL = Global.getConfig("SPD_CP_BASE_PRE_URL");
    //产品组别URL
    private static final String SYNC_PLATFORM_GROUP_URL = Global.getConfig("SYNC_PLATFORM_GROUP_URL");
    //产品分类URL
    private static final String SYNC_PLATFORM_TYPE_URL = Global.getConfig("SYNC_PLATFORM_TYPE_URL");
    //产品厂家URL
    private static final String SYNC_PLATFORM_VENDER_URL = Global.getConfig("SYNC_PLATFORM_VENDER_URL");
    private static final String SYNC_PLATFORM_VENDER_FILE_URL = Global.getConfig("SYNC_PLATFORM_VENDER_FILE_URL");
    //产品URL
    private static final String SYNC_PLATFORM_PRODUCT_URL = Global.getConfig("SYNC_PLATFORM_PRODUCT_URL");
    private static final String SYNC_PLATFORM_PRODUCT_FILE_URL = Global.getConfig("SYNC_PLATFORM_PRODUCT_FILE_URL");

    /**
     * 同步产品信息至中心平台 TODO
     * @author jiangxz
     */
    public JSONObject synPdProductToCentralPlatform() {
        logger.info("**********************提交产品信息定时任务开始*****************************");
        JSONObject jsonRet = new JSONObject();
        try{
            List<PdProductBO> pdProductBOList = pdProductService.findUnsynchronizedList(new PdProductBO());
            if(pdProductBOList != null && pdProductBOList.size() > 0){

                Map<String,Object> synMap = new HashMap<String,Object>();
                synMap.put("pdProductBOList",pdProductBOList);
                synMap.put("hospitalNumber",HOSPITAL_NUMBER);//医院代码

                String jsonPar = JSONObject.toJSONString(synMap);

                //同步接口
                JSONObject json = HttpUtil.httpPost(BASE_URL+SYNC_PLATFORM_PRODUCT_URL, jsonPar);

                if(json != null && json.containsKey("statusCode")){
                    if(MinaGlobalConstants.SYNC_STATE_SUCCESS.equals(json.get("statusCode"))){
                        //成功

                        //同步证照图片
                        synPdProductFileToCentralPlatform(pdProductBOList);

                        logger.info("提交产品信息定时任务成功");
                        for (PdProductBO productBO : pdProductBOList) {
                            productBO.setSynFlag(MinaGlobalConstants.SYN_SUCCESS);
                            pdProductService.updateSynFlag(productBO);
                        }
                        jsonRet.put("code", "200");
                    }else{
                        //失败
                        logger.error("提交产品信息定时任务失败");
                        jsonRet.put("code", "-200");
                    }
                }else{
                    //失败
                    logger.error("提交产品信息定时任务失败");
                    jsonRet.put("code", "-200");
                }
            }
            logger.info("**********************提交产品信息定时任务结束*****************************");

            return jsonRet;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("提交产品信息定时任务失败："+e.getMessage());
            jsonRet.put("code", "-200");
            return jsonRet;
        }

    }

    /**
     * 同步产品证件图片
     * @param pdProductBOList
     * @return
     */
    private void synPdProductFileToCentralPlatform(List<PdProductBO> pdProductBOList){
        for (final PdProductBO pdProductBO : pdProductBOList) {
            new Thread() {
                public void run() {
                    doPostUploadForPdProduct(pdProductBO);
                }
            }.start();
        }
    }

    /**
     * post请求
     * @param url 请求地址
     * @param fileParams 文件类型的参数
     * @param params 其他参数
     * @return 请求返回的信息
     */
    private synchronized void doPostUploadForPdProduct(PdProductBO pdProductBO) {
        String url = BASE_URL+SYNC_PLATFORM_PRODUCT_FILE_URL;
        if(StringUtils.isNotEmpty(pdProductBO.getImgProduct1())){
            HttpUtil.httpPostUpload(url,Global.getUserfilesBaseDir()+pdProductBO.getImgProduct1());
        }
        if(StringUtils.isNotEmpty(pdProductBO.getImgProduct2())){
            HttpUtil.httpPostUpload(url,Global.getUserfilesBaseDir()+pdProductBO.getImgProduct2());
        }
        if(StringUtils.isNotEmpty(pdProductBO.getImgProduct3())){
            HttpUtil.httpPostUpload(url,Global.getUserfilesBaseDir()+pdProductBO.getImgProduct3());
        }
        if(StringUtils.isNotEmpty(pdProductBO.getImgAuthSite())){
            HttpUtil.httpPostUpload(url,Global.getUserfilesBaseDir()+pdProductBO.getImgAuthSite());
        }
        if(StringUtils.isNotEmpty(pdProductBO.getImgLicenseSite1())){
            HttpUtil.httpPostUpload(url,Global.getUserfilesBaseDir()+pdProductBO.getImgLicenseSite1());
        }
        if(StringUtils.isNotEmpty(pdProductBO.getImgLicenseSite2())){
            HttpUtil.httpPostUpload(url,Global.getUserfilesBaseDir()+pdProductBO.getImgLicenseSite2());
        }
        if(StringUtils.isNotEmpty(pdProductBO.getImgLicenseSite3())){
            HttpUtil.httpPostUpload(url,Global.getUserfilesBaseDir()+pdProductBO.getImgLicenseSite3());
        }
        if(StringUtils.isNotEmpty(pdProductBO.getImgLicenseSite4())){
            HttpUtil.httpPostUpload(url,Global.getUserfilesBaseDir()+pdProductBO.getImgLicenseSite4());
        }
        if(StringUtils.isNotEmpty(pdProductBO.getImgRegisterSite1())){
            HttpUtil.httpPostUpload(url,Global.getUserfilesBaseDir()+pdProductBO.getImgRegisterSite1());
        }
        if(StringUtils.isNotEmpty(pdProductBO.getImgRegisterSite2())){
            HttpUtil.httpPostUpload(url,Global.getUserfilesBaseDir()+pdProductBO.getImgRegisterSite2());
        }
        if(StringUtils.isNotEmpty(pdProductBO.getImgRegisterSite3())){
            HttpUtil.httpPostUpload(url,Global.getUserfilesBaseDir()+pdProductBO.getImgRegisterSite3());
        }
    }

    /**
     * 同步产品组别信息至中心平台
     * @author jiangxz
     */
    public JSONObject synPdGroupToCentralPlatform() {
        logger.info("**********************提交产品组别信息定时任务开始*****************************");
        JSONObject jsonRet = new JSONObject();
        try{
            PdGroupBO pdGroupBO = new PdGroupBO();
//            pdGroupBO.setDelFlag(DEL_FLAG_NORMAL);
            List<PdGroupBO> pdGroupBOList = pdGroupService.findUnsynchronizedList(pdGroupBO);
            if(pdGroupBOList != null && pdGroupBOList.size() > 0){

//                String jsonPar = JSONArray.toJSONString(pdGroupBOList);
                Map<String,Object> synMap = new HashMap<String,Object>();
                synMap.put("pdGroupBOList",pdGroupBOList);
                synMap.put("hospitalNumber",HOSPITAL_NUMBER);
                String jsonPar = JSONObject.toJSONString(synMap);

                //同步接口
                JSONObject json = HttpUtil.httpPost(BASE_URL+SYNC_PLATFORM_GROUP_URL, jsonPar);

                if(json != null && json.containsKey("statusCode")){
                    if(MinaGlobalConstants.SYNC_STATE_SUCCESS.equals(json.get("statusCode"))){
                        //成功
                        for (PdGroupBO groupBO : pdGroupBOList) {
                            groupBO.setSynFlag(MinaGlobalConstants.SYN_SUCCESS);
                            pdGroupService.updateSynFlag(groupBO);
                        }

                        logger.info("提交产品组别信息定时任务成功");
                        jsonRet.put("code", "200");
                    }else{
                        //失败
                        logger.error("提交产品组别信息定时任务失败");
                        jsonRet.put("code", "-200");
                    }
                }else{
                    //失败
                    logger.error("提交产品组别信息定时任务失败");
                    jsonRet.put("code", "-200");
                }
            }

            logger.info("**********************提交产品组别信息定时任务结束*****************************");
            return jsonRet;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("提交产品组别信息定时任务失败："+e.getMessage());
            jsonRet.put("code", "-200");
            return jsonRet;
        }
    }

    /**
     * 同步产品分类信息至中心平台
     * @author jiangxz
     */
    public JSONObject synPdCategoryToCentralPlatform() {
        logger.info("**********************提交产品分类信息定时任务开始*****************************");
        JSONObject jsonRet = new JSONObject();
        try{
            PdCategoryBO pdCategoryBO = new PdCategoryBO();
//            pdCategoryBO.setDelFlag(DEL_FLAG_NORMAL);
            List<PdCategoryBO> pdCategoryBOList = pdCategoryService.findUnsynchronizedList(pdCategoryBO);
            if(pdCategoryBOList != null && pdCategoryBOList.size() > 0){

                Map<String,Object> synMap = new HashMap<String,Object>();
                synMap.put("pdCategoryBOList",pdCategoryBOList);
                synMap.put("hospitalNumber",HOSPITAL_NUMBER);

                String jsonPar = JSONObject.toJSONString(synMap);
                //同步接口
                JSONObject json = HttpUtil.httpPost(BASE_URL+SYNC_PLATFORM_TYPE_URL, jsonPar);

                if(json != null && json.containsKey("statusCode")){
                    if(MinaGlobalConstants.SYNC_STATE_SUCCESS.equals(json.get("statusCode"))){
                        //成功
                        for (PdCategoryBO categoryBO : pdCategoryBOList) {
                            categoryBO.setSynFlag(MinaGlobalConstants.SYN_SUCCESS);
                            pdCategoryService.updateSynFlag(categoryBO);
                        }
                        logger.info("提交产品分类信息定时任务成功");
                        jsonRet.put("code", "200");
                    }else{
                        //失败
                        logger.error("提交产品分类信息定时任务失败");
                        jsonRet.put("code", "-200");
                    }
                }else{
                    //失败
                    logger.error("提交产品分类信息定时任务失败");
                    jsonRet.put("code", "-200");
                }
            }
            logger.info("**********************提交产品分类信息定时任务结束*****************************");
            return jsonRet;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("提交产品分类信息定时任务失败："+e.getMessage());
            jsonRet.put("code", "-200");
            return jsonRet;
        }
        
    }

    /**
     * 同步产品厂家信息至中心平台
     * @author jiangxz
     */
    public JSONObject synPdVenderToCentralPlatform() {
        logger.info("**********************提交产品厂家信息定时任务开始*****************************");
        JSONObject jsonRet = new JSONObject();
        try{
            List<PdVenderBO> pdVenderBOList = pdVenderService.findUnsynchronizedList(new PdVenderBO());
            if(pdVenderBOList != null && pdVenderBOList.size() > 0){

                Map<String,Object> synMap = new HashMap<String,Object>();
                synMap.put("pdVenderBOList",pdVenderBOList);
                synMap.put("hospitalNumber",HOSPITAL_NUMBER);//医院代码

                String jsonPar = JSONObject.toJSONString(synMap);

                //同步接口
                JSONObject json = null;
                json = HttpUtil.httpPost(BASE_URL+SYNC_PLATFORM_VENDER_URL, jsonPar);

                if(json != null && json.containsKey("statusCode")){
                    if(MinaGlobalConstants.SYNC_STATE_SUCCESS.equals(json.get("statusCode"))){
                        //成功

                        //同步图片
                        synPdVenderFileToCentralPlatform(pdVenderBOList);

                        logger.info("提交产品厂家信息定时任务成功");
                        for (PdVenderBO venderBO : pdVenderBOList) {
                            venderBO.setSynFlag(MinaGlobalConstants.SYN_SUCCESS);
                            pdVenderService.updateSynFlag(venderBO);
                        }
                        logger.info("提交产品厂家信息定时任务成功");
                        jsonRet.put("code", "200");
                    }else{
                        //失败
                        logger.error("提交产品厂家信息定时任务失败");
                        jsonRet.put("code", "-200");
                    }
                }else{
                    //失败
                    logger.error("提交产品厂家信息定时任务失败");
                    jsonRet.put("code", "-200");
                }
            }
            logger.info("**********************提交产品厂家信息定时任务结束*****************************");

            return jsonRet;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("提交产品厂家信息定时任务失败："+e.getMessage());
            jsonRet.put("code", "-200");
            return jsonRet;
        }

    }

    /**
     * 同步厂家图片
     * @param pdVenderBOList
     * @return
     */
    private void synPdVenderFileToCentralPlatform(List<PdVenderBO> pdVenderBOList){
        for (final PdVenderBO pdVenderBO : pdVenderBOList) {
            new Thread() {
                public void run() {
                    doPostUploadForPdVender(pdVenderBO);
                }
            }.start();
        }
    }

    /**
     * post请求
     * @param url 请求地址
     * @param fileParams 文件类型的参数
     * @param params 其他参数
     * @return 请求返回的信息
     */
    private synchronized void doPostUploadForPdVender(PdVenderBO pdVenderBO) {
        String url = BASE_URL+SYNC_PLATFORM_VENDER_FILE_URL;
        if(StringUtils.isNotEmpty(pdVenderBO.getLicenceSite1())){
            HttpUtil.httpPostUpload(url,Global.getUserfilesBaseDir()+pdVenderBO.getLicenceSite1());
        }
        if(StringUtils.isNotEmpty(pdVenderBO.getLicenceSite2())){
            HttpUtil.httpPostUpload(url,Global.getUserfilesBaseDir()+pdVenderBO.getLicenceSite2());
        }
        if(StringUtils.isNotEmpty(pdVenderBO.getLicenceSite3())){
            HttpUtil.httpPostUpload(url,Global.getUserfilesBaseDir()+pdVenderBO.getLicenceSite3());
        }
        if(StringUtils.isNotEmpty(pdVenderBO.getLicenceSite4())){
            HttpUtil.httpPostUpload(url,Global.getUserfilesBaseDir()+pdVenderBO.getLicenceSite4());
        }
        if(StringUtils.isNotEmpty(pdVenderBO.getLicenceSite5())){
            HttpUtil.httpPostUpload(url,Global.getUserfilesBaseDir()+pdVenderBO.getLicenceSite5());
        }
        if(StringUtils.isNotEmpty(pdVenderBO.getLicenceSite6())){
            HttpUtil.httpPostUpload(url,Global.getUserfilesBaseDir()+pdVenderBO.getLicenceSite6());
        }
        if(StringUtils.isNotEmpty(pdVenderBO.getLicenceSite7())){
            HttpUtil.httpPostUpload(url,Global.getUserfilesBaseDir()+pdVenderBO.getLicenceSite7());
        }
        if(StringUtils.isNotEmpty(pdVenderBO.getLicenceSite8())){
            HttpUtil.httpPostUpload(url,Global.getUserfilesBaseDir()+pdVenderBO.getLicenceSite8());
        }
        if(StringUtils.isNotEmpty(pdVenderBO.getLicenceSite9())){
            HttpUtil.httpPostUpload(url,Global.getUserfilesBaseDir()+pdVenderBO.getLicenceSite9());
        }
        if(StringUtils.isNotEmpty(pdVenderBO.getLicenceSite10())){
            HttpUtil.httpPostUpload(url,Global.getUserfilesBaseDir()+pdVenderBO.getLicenceSite10());
        }
        if(StringUtils.isNotEmpty(pdVenderBO.getLicenceSite11())){
            HttpUtil.httpPostUpload(url,Global.getUserfilesBaseDir()+pdVenderBO.getLicenceSite11());
        }
        if(StringUtils.isNotEmpty(pdVenderBO.getLicenceSite12())){
            HttpUtil.httpPostUpload(url,Global.getUserfilesBaseDir()+pdVenderBO.getLicenceSite12());
        }
    }

}
