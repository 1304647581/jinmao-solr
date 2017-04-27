package com.jinmao.mq.web.test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jinmao.domain.log.OperateLog;
import com.tools.common.rocketmq.client.RocketmqClient;
import com.tools.common.util.json.JsonUtil;
/**
 * @描述 : 测试
 * @创建者 : HeZeMin
 * @创建时间 : 2017-3-8 下午3:14:17
 */
public class Test {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext(new String[] { "/spring/spring-config-mq.xml" }); 
		RocketmqClient client= (RocketmqClient) appContext.getBean("rocketmqClient");
		OperateLog operateLog = new OperateLog();
		operateLog.setIp("5456");
		client.sendMessage("log", "jinmao_operateLog", "tt", JsonUtil.toJson(operateLog));
		
		//System.out.println(sendResult+":"+ new Date());
		//List<String> ss=client.revMessage("topicxxxxxxxxxx");
		//System.out.println(ss.get(0).trim()+":"+new Date());
	}
}