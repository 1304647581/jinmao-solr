package com.jinmao.mq.web.listener;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.tools.common.util.json.JsonUtil;
/**
 * @描述 : mq消息消费
 * @创建者 : HeZeMin
 * @创建时间 : 2017-3-8 下午2:48:34
 */
public class MessageListener implements MessageListenerConcurrently {
	/*****************************声明区********************************************/
	private static final Log LOG = LogFactory.getLog(MessageListener.class);
	
	/*****************************方法区********************************************/
	
	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
		LOG.info("-----------------start---------------------");
		for (MessageExt msg : msgs) {
			/********接受消息**start*******/
			//提醒
			if (msg.getTopic().equals("jinmao_remind")) {
				receiveRemind(msg);
			}
			//操作日志
			if (msg.getTopic().equals("jinmao_operateLog")) {
				receiveOperateLog(msg);
			}
			/********接受消息**end*******/
		}
		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	}
	
	
	
	
	/**
	 * @描述 : 处理生产的提醒消息
	 * @创建者 : HeZeMin
	 * @创建时间 : 2017-3-8 下午2:52:39
	 * @param msg 消息体
	 */
	@SuppressWarnings("unchecked")
	private void receiveRemind(MessageExt msg){
		Map<String,Object> message = null;
		try {
			message = JsonUtil.fromJson(new String(msg.getBody(), "UTF-8"), Map.class);
			
		} catch (UnsupportedEncodingException e) {
			LOG.info("提醒消息插入失败编码格式异常",e);
		} catch (Exception e) {
			LOG.info("提醒消息插入失败" + JsonUtil.toJson(message),e);
		}
	}
	/**
	 * @描述 : 处理生产的操作日志消息
	 * @创建者 : HeZeMin
	 * @创建时间 : 2017-3-8 下午2:54:56
	 * @param msg	消息体
	 */
	@SuppressWarnings("unchecked")
	private void receiveOperateLog(MessageExt msg){
		Map<String,Object> message = null;
		try {
			message = JsonUtil.fromJson(new String(msg.getBody(), "UTF-8"), Map.class);
			
		} catch (UnsupportedEncodingException e) {
			LOG.error("接收操作日志编码格式异常",e);
		} catch (Exception e) {
			LOG.error("接收操作日志异常:"+JsonUtil.toJson(message),e);
		}
	}
	/*****************************get/set********************************************/
	
	
	
	
	
}