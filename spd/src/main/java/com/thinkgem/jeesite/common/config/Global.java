/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.common.config;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.core.io.DefaultResourceLoader;

import com.ckfinder.connector.ServletContextFactory;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.utils.PropertiesLoader;
import com.thinkgem.jeesite.common.utils.StringUtils;

/**
 * 全局配置类
 * @author ThinkGem
 * @version 2014-06-25
 */
public class Global {

	/**
	 * 当前对象实例
	 */
	private static Global global = new Global();
	
	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = Maps.newHashMap();
	
	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader loader = new PropertiesLoader("jeesite.properties");

	/**
	 * 显示/隐藏
	 */
	public static final String SHOW = "1";
	public static final String HIDE = "0";

	/**
	 * 是/否
	 */
	public static final String YES = "1";
	public static final String NO = "0";
	
	/**
	 * 对/错
	 */
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	
	/**
	 * 上传文件基础虚拟路径
	 */
	public static final String USERFILES_BASE_URL = "/userfiles/";
	
	/**
	 * 获取当前对象实例
	 */
	public static Global getInstance() {
		return global;
	}
	
	/**
	 * 获取配置
	 * @see ${fns:getConfig('adminPath')}
	 */
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null){
			value = loader.getProperty(key);
			map.put(key, value != null ? value : StringUtils.EMPTY);
		}
		return value;
	}
	
	/**
	 * 获取管理端根路径
	 */
	public static String getAdminPath() {
		return getConfig("adminPath");
	}
	
	/**
	 * 获取前端根路径
	 */
	public static String getFrontPath() {
		return getConfig("frontPath");
	}
	
	/**
	 * 获取对外路径
	 */
	public static String getOutPath() {
		return getConfig("outPath");
	}
	
	/**
	 * 门户路径
	 * @return
	 */
	public static String getPortalPath() {
		return getConfig("portalPath");
	}
	
	/**
	 * app模块
	 * @return
	 */
	public static String getAppPaths() {
		return getConfig("appPath");
	}
	
	/**
	 * 支付模块
	 * @return
	 */
	public static String getPayPath() {
		return getConfig("payPath");
	}
	
	/**
	 * 注册环信账号时，生产环境和测试 注册账号的 标示不一样
	 * @return
	 */
	public static String getIMRegisterHead() {
		return getConfig("im_register_head");
	}
	
	/**
	 * 区分前后台标示 前台
	 * @return
	 */
	public static String getPortal() {
		String portal=getConfig("portalPath");
		portal=portal.substring( 1 );
		return portal;
	}
	
	/**
	 * 区分前后台标示  后台 
	 * @return
	 */
	public static String getAdmin() {
		String admin=getConfig("adminPath");
		admin=admin.substring( 1 );
		return admin;
	}
	
	/**
	 * 回调系统根路径
	 * @return
	 */
	public static String getCallBackPath() {
	  return getConfig("callbackPath");
	}
	
	/**
	 * 物流订阅回调url
	 * @return
	 */
	public static String getPostCallbackUrl() {
	  return getConfig("post_callback_url");
	}
	
	/**
	 * 获取前台首页地址
	 * @return
	 */
	public static String getPortalIndex() {
		return getConfig("portal.view.index");
	}
	
	/**
	 * 定义无Controller的path<->view直接映射
	 * @return
	 */
	public static String getWebViewIndex() {
		return getConfig("web.view.index");
	}
	
	/**
	 * app路径
	 * @return
	 */
	public static String getAppPath() {
		String appPath=getConfig("appPath");
		appPath=appPath.substring( 1 );
		return appPath;
	}
	
	/**
	 * 获得客服信息
	 * @return
	 */
	public static String getGoodKeFu() {
		return getConfig("good_kefu_id");
	}
	
	
	/**
	 * 获取热销商品数量
	 */
	public static String gethotGoodsCount() {
		return getConfig("hotGoods_count");
	}
	
	public static String getUrlSuffix() {
		return getConfig("urlSuffix");
	}
	
	/**
	 * 是否是演示模式，演示模式下不能修改用户、角色、密码、菜单、授权
	 */
	public static Boolean isDemoMode() {
		String dm = getConfig("demoMode");
		return "true".equals(dm) || "1".equals(dm);
	}
	
	public static String getSmsApiUrl() {
		return getConfig("sms.api.url");
	}
	
	public static String getSmsSId() {
		return getConfig("sms.sid");
	}
	
	public static String getSmsToken() {
		return getConfig("sms.token");
	}
	
	public static String getSmsAppId() {
		return getConfig("sms.appid");
	}
	
	/**
	 * #手机号每秒发送发5条短信加入黑名单
	 * @return
	 */
	public static String getSecLimitCount() {
		return getConfig("sec.limit.count");
	}
	/**
	 * #手机号每分钟发送发5条短信冻结
	 * @return
	 */
	public static String getMinLimitCount() {
		return getConfig("min.limit.count");
	}
	/**
	 * #手机号每天发送发20条短信冻结
	 * @return
	 */
	public static String getDayLimitCount() {
		return getConfig("day.limit.count");
	}
	/**
	 * #手机号每分钟发送发5条短信冻结1分钟
	 * @return
	 */
	public static String getLockPhoneMin() {
		return getConfig("lock.phone.min");
	}
	/**
	 * #手机号每天发送发20条短信冻结6小时
	 * @return
	 */
	public static String getLockPhoneHour() {
		return getConfig("lock.phone.hour");
	}
	
	
	/**
	 * 在修改系统用户和角色时是否同步到Activiti
	 */
	public static Boolean isSynActivitiIndetity() {
		String dm = getConfig("activiti.isSynActivitiIndetity");
		return "true".equals(dm) || "1".equals(dm);
	}
    
	/**
	 * 页面获取常量
	 * @see ${fns:getConst('YES')}
	 */
	public static Object getConst(String field) {
		try {
			return Global.class.getField(field).get(null);
		} catch (Exception e) {
			// 异常代表无配置，这里什么也不做
		}
		return null;
	}

	/**
	 * 获取上传文件的根目录
	 * @return
	 */
	public static String getUserfilesBaseDir() {
		String dir = getConfig("userfiles.basedir");
		if (StringUtils.isBlank(dir)){
			try {
				dir = ServletContextFactory.getServletContext().getRealPath("/");
			} catch (Exception e) {
				return "";
			}
		}
		if(!dir.endsWith("/")) {
			dir += "/";
		}
//		System.out.println("userfiles.basedir: " + dir);
		return dir;
	}
	
    /**
     * 获取工程路径
     * @return
     */
    public static String getProjectPath(){
    	// 如果配置了工程路径，则直接返回，否则自动获取。
		String projectPath = Global.getConfig("projectPath");
		if (StringUtils.isNotBlank(projectPath)){
			return projectPath;
		}
		try {
			File file = new DefaultResourceLoader().getResource("").getFile();
			if (file != null){
				while(true){
					File f = new File(file.getPath() + File.separator + "src" + File.separator + "main");
					if (f == null || f.exists()){
						break;
					}
					if (file.getParentFile() != null){
						file = file.getParentFile();
					}else{
						break;
					}
				}
				projectPath = file.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return projectPath;
    }
    
	/**
	 * 获取上传文件url
	 * @return
	 */
	public static String getUserfilesBaseUrl() {
		String url = getConfig("userfiles.baseurl");
		String type = getConfig("userfiles.type");
		if (StringUtils.isBlank(url)){
			try {
				url = ServletContextFactory.getServletContext().getRealPath("/");
			} catch (Exception e) {
				return "";
			}
		}else{
			url = url + type;
		}
		
		return url;
	}
	
	
	/**
	 * 获取延长收货的天数
	 * @return
	 */
	public static String getExpreesPrlongTime() {
		return getConfig("exprees_prlong_time");
	}
	
	/**
	 * 自动收货时间
	 * @return
	 */
	public static String getExpreesAutoDeliveryTime() {
		return getConfig("exprees_auto_delivery_time");
	}
	
	/**
	 * 延长收货的时间
	 * @return
	 */
	public static String getExpreesPrlongDeliveryTime() {
		return getConfig("exprees_prlong_delivery_time");
	}
	
	/**
	 * 公司名称
	 * @return
	 */
	public static String getCompanyName() {
		return getConfig("company_name");
	}
	
	/**
	 * 公司电话
	 * @return
	 */
	public static String getCompanyTel() {
		return getConfig("company_tel");
	}
	
	/**
	 * 公司地址
	 * @return
	 */
	public static String getCompanyAddr() {
		return getConfig("company_addr");
	}
	
	/**
	 * 公司开户银行
	 * @return
	 */
	public static String getCompanyBank() {
		return getConfig("company_bank");
	}
	
	/**
	 * 银行帐号
	 * @return
	 */
	public static String getCompanyBankNo() {
		return getConfig("company_bank_no");
	}
	
}
