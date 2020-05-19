package com.thinkgem.jeesite.common.jpush.util;

import io.netty.handler.codec.http.HttpMethod;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.ServiceHelper;
import cn.jiguang.common.connection.NettyHttpClient;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jiguang.common.resp.ResponseWrapper;

import com.thinkgem.jeesite.common.jpush.api.JPushClient;
import com.thinkgem.jeesite.common.jpush.api.push.PushResult;
import com.thinkgem.jeesite.common.jpush.api.push.model.Message;
import com.thinkgem.jeesite.common.jpush.api.push.model.Options;
import com.thinkgem.jeesite.common.jpush.api.push.model.Platform;
import com.thinkgem.jeesite.common.jpush.api.push.model.PushPayload;
import com.thinkgem.jeesite.common.jpush.api.push.model.audience.Audience;
import com.thinkgem.jeesite.common.jpush.api.push.model.notification.AndroidNotification;
import com.thinkgem.jeesite.common.jpush.api.push.model.notification.IosNotification;
import com.thinkgem.jeesite.common.jpush.api.push.model.notification.Notification;



public class JpushUtil {
	private static Logger log = LoggerFactory.getLogger(JpushUtil.class);
	
	  protected static final String APP_KEY ="a09f7074c97d3a4f41c01ce4";
	  protected static final String MASTER_SECRET = "babcaafb0bd46aee3804fc45";
	  
	  
	  /**
	   * (快捷地构建推送对象：所有平台，所有设备，内容为 alert 的通知)
	   * @param alert
	   * @return
	   */
	  public static PushPayload buildPushObject_all_all_alert(String alert) {
		    return PushPayload.alertAll(alert);
	  }
	  
		
		/**
		 *  使用 NettyHttpClient 异步接口发送请求
		 */
	    public static void testSendPushWithCallback(String alert,List<String> alialist) {
	        ClientConfig clientConfig = ClientConfig.getInstance();
	        String host = (String) clientConfig.get(ClientConfig.PUSH_HOST_NAME);
	        final NettyHttpClient client = new NettyHttpClient(ServiceHelper.getBasicAuthorization(APP_KEY, MASTER_SECRET),
	                null, clientConfig);
	        try {
	            URI uri = new URI(host + clientConfig.get(ClientConfig.PUSH_PATH));
	            PushPayload payload = buildPushObject_all_alias_alert(alert,alialist);
	            client.sendRequest(HttpMethod.POST, payload.toString(), uri, new NettyHttpClient.BaseCallback() {
	                @Override
	                public void onSucceed(ResponseWrapper responseWrapper) {
	                	log.info("Got result: " + responseWrapper.responseContent);
	                }
	            });
	        } catch (URISyntaxException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public static void sendPushWithCustomConfig(String alert) {
	        ClientConfig config = ClientConfig.getInstance();
	        // Setup the custom hostname
	        config.setPushHostName("https://api.jpush.cn");

	        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, config);

	        // For push, all you need do is to build PushPayload object.
	        PushPayload payload = buildPushObject_all_all_alert(alert);

	        try {
	            PushResult result = jpushClient.sendPush(payload);
	            log.info("Got result - " + result);

	        } catch (APIConnectionException e) {
	        	log.error("Connection error. Should retry later. ", e);

	        } catch (APIRequestException e) {
	            log.error("Error response from JPush server. Should review and fix it. ", e);
	            log.info("HTTP Status: " + e.getStatus());
	            log.info("Error Code: " + e.getErrorCode());
	            log.info("Error Message: " + e.getErrorMessage());
	            log.info("Msg ID: " + e.getMsgId());
	        }
	    }

	    
	    public static PushPayload buildPushObject_all_alias_alert(String alert,List<String> alias) {
	        return PushPayload.newBuilder()
	                .setPlatform(Platform.all())
	                .setAudience(Audience.alias(alias))
	                .setNotification(Notification.alert(alert))
	                .build();
	    }
	    
	    
	    /** 
	     * 通知  部分用户
	     * @Title: buildPushObjectAliasAlert 
	     * @Description: (所有平台，推送目标是别名为 alias，通知内容为 alert) 
	     * @param @param alert
	     * @param @param alias
	     * @param @return    设定文件 
	     * @return PushPayload    返回类型 
	     * @throws 
	     */
		public static PushPayload buildPushObjectAliasAlert(String days,String title,String alert,List<String> aliasList,Map<String,String> paramMap) {
			return PushPayload.newBuilder()
					.setPlatform(Platform.android_ios())
					.setAudience(Audience.alias(aliasList))
					.setNotification(Notification.newBuilder().setAlert(alert).addPlatformNotification
							                        (AndroidNotification.newBuilder().setTitle(title).addExtras(paramMap)//.addExtra("id", id).addExtra("type",type)
													.build())
													.addPlatformNotification(IosNotification.newBuilder().addExtras(paramMap).setContentAvailable(true)//.addExtra("id", id).addExtra("type",type)
													.build()).build()).setOptions
													(Options.newBuilder().setApnsProduction(true)
													.setTimeToLive(Long.valueOf(Integer.valueOf(days) * 86400)).build()).build();
		}
		
		 /** 
		 * 通知   所有用户
	     * @Title: sendAndroidAndIosMessageAndNotification 
	     * @Description: 推送消息和通知，发送给所有安卓和IOS用户 
	     * @param @param title
	     * @param @param alert
	     * @param @return    设定文件 
	     * @return PushPayload    返回类型 
	     * @throws
	     *  
	     *  
	     */  
	     public static PushPayload sendAndroidAndIosMessageAndNotification(String days,String messagetitle,String alert,Map<String,String> paramMap) {
				return PushPayload.newBuilder()
						.setPlatform(Platform.android_ios())
						.setAudience(Audience.all())
						.setNotification(Notification.newBuilder()
								                     .setAlert(alert) //弹出消息的内容
								                     .addPlatformNotification(AndroidNotification.newBuilder().addExtras(paramMap) //.addExtra("id",id).addExtra("type", type)
								                     .setTitle(messagetitle)	//弹出消息的标题	 
								                     .build()).addPlatformNotification(IosNotification.newBuilder().addExtras(paramMap).setContentAvailable(true)//.addExtra("id",id).addExtra("type", type)
								                     .build()).build()).setOptions(Options.newBuilder().setApnsProduction(true)
													 .setTimeToLive(Long.valueOf(Integer.valueOf(days) * 86400)).build()).build();
			}
	    
	    
	    
	    /** 
	     * 用户自定义消息类型 message
         * @Title: sendAndroidMessageWithAlias 
         * @Description: 无附加属性信息，发送给安卓和IOS特定用户 
         * @param @param title
         * @param @param msgContent
         * @param @param alias
         * @param @return
         * @param @throws APIConnectionException
         * @param @throws APIRequestException    设定文件 
         * @return PushPayload    返回类型 
         * message：自定义消息
		 * 应用内消息。或者称作：自定义消息，透传消息。
		 * 此部分内容不会展示到通知栏上，JPush SDK 收到消息内容后透传给 App。需要 App 自行处理。
		 * iOS 平台上，此部分内容在推送应用内消息通道（非APNS）获取。Windows Phone 暂时不支持应用内消息。
         * 
         * @throws 
         */
         public static PushPayload sendAndroidAndIosMessageWithAlias(String days,Map<String,String> extras,String title, String msgContent, List<String> aliasList) 
                 throws APIConnectionException, APIRequestException {
                     return PushPayload.newBuilder()
                    		 .setPlatform(Platform.android_ios())
                             .setAudience(Audience.alias(aliasList))
                             .setMessage(Message.newBuilder().setTitle(title).setMsgContent(msgContent).addExtras(extras).build())
                             .setOptions(Options.newBuilder().setApnsProduction(true).setTimeToLive(Long.valueOf(Integer.valueOf(days)*86400)).build())
                             .build();
         }
	    
         
         /**
          * 向部分用户发送消息
          * @param title
          * @param content
          * @param aliaList
          * @param type
          * @param id
          */
         public static void sendPushInfo(String title,String content,List<String> aliaList,Map<String,String> paramMap) {
        	 JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        	 try {
        		//消息存活时间为 1 天
				PushResult result = jpushClient.sendPush(buildPushObjectAliasAlert("1",title,content,aliaList,paramMap));
				log.info("Got result - " + result);

 	        } catch (APIConnectionException e) {
 	        	log.error("Connection error. Should retry later. ", e);

 	        } catch (APIRequestException e) {
 	            log.error("Error response from JPush server. Should review and fix it. ", e);
 	            log.info("HTTP Status: " + e.getStatus());
 	            log.info("Error Code: " + e.getErrorCode());
 	            log.info("Error Message: " + e.getErrorMessage());
 	            log.info("Msg ID: " + e.getMsgId());
 	        }
         }
         
         /**
          * 向所有用户发送消息
          * @param title
          * @param content
          * @param aliaList
          * @param type
          * @param id
          */
         public static void sendALLInfo(String title,String content,Map<String,String> paraMap) {
        	 JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        	 try {
        		//消息存活时间为 1 天
				PushResult result = jpushClient.sendPush(sendAndroidAndIosMessageAndNotification("1",title,content,paraMap));
				log.info("Got result - " + result);

 	        } catch (APIConnectionException e) {
 	        	log.error("Connection error. Should retry later. ", e);

 	        } catch (APIRequestException e) {
 	            log.error("Error response from JPush server. Should review and fix it. ", e);
 	            log.info("HTTP Status: " + e.getStatus());
 	            log.info("Error Code: " + e.getErrorCode());
 	            log.info("Error Message: " + e.getErrorMessage());
 	            log.info("Msg ID: " + e.getMsgId());
 	        }
         }
     	
	    
	    
	    public static void main(String[] args) throws APIConnectionException, APIRequestException {
	    	JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
	    	// PushResult result = jpushClient.sendPush(buildPushObject_all_all_alert("我是测试数据"));
	    	//sendPushWithCustomConfig("我是测试数据config");
	    	
	    	//自定义消息
	    	Map<String,String> paramMap = new HashMap<String,String>();
	    	List<String> phoneList = new ArrayList<String>();
	    	phoneList.add("18600730736");
	    	phoneList.add("15810610128");
	    	//phoneList.add("18800187132");
	    	paramMap.put("id", "9e7e2f9535df4f8b8ce4b64dcc49f5c4");
	    	paramMap.put("type","activity");
	    	paramMap.put("actType","BUYGIFT");
	    	paramMap.put("msgTitle","我是标题");
	    	
	    	PushResult result = jpushClient.sendPush(buildPushObjectAliasAlert("1", "我是标题","我是消息内容2", phoneList, paramMap));//("1",paramMap , "我是标题", "我是消息内容", phoneList));//("1","我是标题","我是消息内容",paramMap));
	    	log.info("Got result - " + result);
	    	//sendALLInfo("title", "content","abcdef", "typetypetype");
		}

}
