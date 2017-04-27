package com.jinmao.web.test;

import redis.clients.jedis.Jedis;

/**
 * @描述 : redis练习
 * @创建者 : HeZeMin
 * @创建时间 : 2016-8-15 下午6:28:40
 */
public class Dome {
	
	
	
	
	public static void main(String[] args) {
		
		Jedis jedis = new Jedis("192.168.1.61", 6379);
		jedis.auth("123456");
		System.out.println(jedis.ping());
		System.out.println(jedis.keys("*"));
	}

}
