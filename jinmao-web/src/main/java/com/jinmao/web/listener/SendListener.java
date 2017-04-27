package com.jinmao.web.listener;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.rocketmq.client.producer.SendCallback;
import com.alibaba.rocketmq.client.producer.SendResult;
/**
 * @描述 : mq消息生产监听
 * @创建者 : HeZeMin
 * @创建时间 : 2017-3-6 下午5:21:50
 */
public class SendListener implements SendCallback{
	private static final Log LOG = LogFactory.getLog(SendListener.class);
	
	public void onException(Throwable throwable) {
		LOG.info(throwable + ":" + new Date());
	}

	public void onSuccess(SendResult sendResult) {
		LOG.info(sendResult + ":" + new Date());
	}
}
