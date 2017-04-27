package com.jinmao.mq.web.init;

import com.tools.common.rocketmq.client.RocketmqClient;
/**
 * @描述 : 消息实体
 * @创建者 : HeZeMin
 * @创建时间 : 2017-3-8 下午2:47:16
 */
public class InitRocketMq {
	/**********************声明区********************************/
	private RocketmqClient rocketmqClient;
	/**********************get/set********************************/
	public RocketmqClient getRocketmqClient() {
		return rocketmqClient;
	}
	public void setRocketmqClient(RocketmqClient rocketmqClient) {
		this.rocketmqClient = rocketmqClient;
	}
}