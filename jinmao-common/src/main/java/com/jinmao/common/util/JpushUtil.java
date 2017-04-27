package com.jinmao.common.util;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

import com.tools.common.util.json.JsonUtil;

public class JpushUtil {
	private static final Log LOG = LogFactory.getLog(JpushUtil.class);
	private String masterSecret ;
	private String appKey ;
	private  Map<String,Map<String,String>> params;

	public String getMasterSecret() {
		return masterSecret;
	}

	public void setMasterSecret(String masterSecret) {
		this.masterSecret = masterSecret;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	/**
	 * 构建一个JPushClient对象
	 * @return
	 */
	private  JPushClient buildJPushClient(){
		return new JPushClient(params.get(JpushContextHolder.getJpushType()).get("masterSecret"), 
	               params.get(JpushContextHolder.getJpushType()).get("appKey"));
	}
	
	/**
	 * 所有平台，所有设备，内容为 content 的通知。
	 * @param content
	 */
	public  void jpushToAll(String content,String param){
		JpushContextHolder.setJpushType(param);
		JPushClient jpushClient = buildJPushClient();
		Map<String, Object> map = JsonUtil.fromJson(content,Map.class);
		PushPayload payload = buildPushObject_all_all_alert((String)map.get("content"));
		try {
	        PushResult result = jpushClient.sendPush(payload);
	        LOG.info("Got result - " + result);
	    } catch (APIConnectionException e) {
	        // Connection error, should retry later
	        LOG.error("Connection error, should retry later", e);
	    } catch (APIRequestException e) {
	        // Should review the error, and fix the request
	        LOG.error("Should review the error, and fix the request", e);
	        LOG.info("HTTP Status: " + e.getStatus());
	        LOG.info("Error Code: " + e.getErrorCode());
	        LOG.info("Error Message: " + e.getErrorMessage());
	    }finally{
	    	 JpushContextHolder.clearJpushType();
	    }
	}
	/**
	 * 所有平台，别名是alias，发送内容是content的通知
	 */
	public void jpushToAllForAlias(String content,String param,String... jpush_alias){
		JpushContextHolder.setJpushType(param);
		JPushClient jpushClient = buildJPushClient();
		try{
			  //给android发消息
			  PushPayload payload_android = buildPushObject_android_alias_message(content,jpush_alias);
		      PushResult result_android = jpushClient.sendPush(payload_android);
		      LOG.info("Got result - " + result_android);
		   } catch (APIConnectionException e) {
		      // Connection error, should retry later
		      LOG.error("Connection error, should retry later", e);
		   } catch (APIRequestException e) {
		      // Should review the error, and fix the request
		      LOG.error("Should review the error, and fix the request", e);
		      LOG.info("HTTP Status: " + e.getStatus());
		      LOG.info("Error Code: " + e.getErrorCode());
		      LOG.info("Error Message: " + e.getErrorMessage());
		   }finally{
			   JpushContextHolder.clearJpushType();
		   }
	}
	/**
	 * 
	 * @描述 : 极光推送android
	 * @创建者：qiwanzeng
	 * @创建时间： 2016-10-7下午3:05:17
	 *
	 * @param jpush_alias 推给谁
	 * @param content 推送内容
	 * @param key 推送需要替换的内容
	 * @param pushTitle 推送标题 
	 */
	public  void jpushToAndroid(String jpush_alias,String pushTitle,String content) {
		JPushClient jpushClient = buildJPushClient();
		PushPayload payload = buildPushObject_android_alias_alert(content,pushTitle,jpush_alias);
		try {	
			PushResult pushResult = jpushClient.sendPush(payload);
			LOG.info("Got result - " + pushResult);
		} catch (APIConnectionException e) {
			e.printStackTrace();
			LOG.info("Connection error, should retry later");
		} catch (APIRequestException e) {
			e.printStackTrace();
			LOG.info("Should review the error, and fix the request");
			LOG.info("HTTP Status: " + e.getStatus());
			LOG.info("Error Code: " + e.getErrorCode());
		}
	}
	/**
	 * 
	 * @描述 : 极光推送IOS
	 * @创建者：qiwanzeng
	 * @创建时间： 2016-10-7下午3:05:17
	 *
	 * @param jpush_alias 推给谁
	 * @param content 推送内容
	 * @param key 推送需要替换的内容
	 */
	public  void jpushToIOS(String jpush_alias,String content) {
		JPushClient jpushClient = buildJPushClient();
		PushPayload payload = buildPushObject_ios_alias_alert(content,jpush_alias);
		try {	
			PushResult pushResult = jpushClient.sendPush(payload);
			LOG.info("Got result - " + pushResult);
		} catch (APIConnectionException e) {
			e.printStackTrace();
			LOG.info("Connection error, should retry later");
		} catch (APIRequestException e) {
			e.printStackTrace();
			LOG.info("Should review the error, and fix the request");
			LOG.info("HTTP Status: " + e.getStatus());
			LOG.info("Error Code: " + e.getErrorCode());
		}
	}
	
	/**
	 * 构建一个 PushPayload 对象 
	 * 所有平台，所有设备，内容为 content 的通知
	 * @param content
	 * @return
	 */
	private static PushPayload buildPushObject_all_all_alert(String content) {
	    return PushPayload.alertAll(content);
	}
	
	/**
	 * Android平台，推送目标是别名为 jpush_alias，自定义消息内容为 content
	 * @param content 消息内容
	 * @param jpush_alias 设备别名
	 * @return
	 */
	private static PushPayload buildPushObject_android_alias_message(String content,String... jpush_alias) {
		Map<String, Object> map = JsonUtil.fromJson(content,Map.class);
		return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias(jpush_alias))
                .setMessage(Message.newBuilder()
                        .setMsgContent(content)
                        .addExtra("from", "JPush")
                        .build())
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert((String)map.get("content"))
                                .setSound("default")
                                .addExtra("content",(String)map.get("content"))
                                .addExtra("link",(String)map.get("link"))
                                .addExtra("type",map.get("type")+"")
                                .addExtra("code",(String)map.get("code"))
                                .build())
                        .build())
                .build();
		
	}
	
	/**
	 * android平台，推送目标是别名为 jpush_alias，通知内容为 content
	 * @param content  消息内容
	 * @param pushTitle  消息标题
	 * @param jpush_alias  设备别名
	 * @return
	 */
	private static PushPayload buildPushObject_android_alias_alert(String content,String pushTitle,String jpush_alias) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.alias(jpush_alias))
                .setNotification(Notification.android(content,pushTitle,null))
                .build();
	}
	
	/**
	 * ios平台，推送目标是别名为 jpush_alias，通知内容为 content
	 * @param content
	 * @param jpush_alias
	 * @return
	 */
	private static PushPayload buildPushObject_ios_alias_alert(String content,String... jpush_alias) {
		Map<String, Object> map = JsonUtil.fromJson(content,Map.class);
        return PushPayload.newBuilder()
        		.setPlatform(Platform.ios())
				.setAudience(Audience.alias(jpush_alias))
				.setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert((String)map.get("content"))
                                .addExtra("content",(String)map.get("content"))
                                .addExtra("link",(String)map.get("link"))
                                .addExtra("type",map.get("type")+"")
                                .addExtra("code",(String)map.get("code"))
                                .build())
                        .build())
				.build();
	}
	
	/**
	 * Android，目标是标签为  jpush_tag 的设备，内容是 content 通知，并且标题为 pushTitle。
	 * @param content
	 * @param pushTitle
	 * @param jpush_tag
	 * @return
	 */
	private static PushPayload buildPushObject_android_tag_alertWithTitle(String content,String pushTitle,String jpush_tag) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.tag(jpush_tag))
                .setNotification(Notification.android(content, pushTitle, null))
                .build();
    }
	
	
	
	
	/***********私有方法******************/
	public static void main(String[] args) {
		//jpushToAllForAlias("aaa","");
	}

	public Map<String, Map<String, String>> getParams() {
		return params;
	}

	public void setParams(Map<String, Map<String, String>> params) {
		this.params = params;
	}
	
	
}