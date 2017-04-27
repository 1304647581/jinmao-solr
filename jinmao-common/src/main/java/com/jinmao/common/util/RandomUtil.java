package com.jinmao.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import com.tools.common.util.security.SecurityUtil;

public class RandomUtil {
	public static String getRandomCode() {
		String randomCode = null;
		UUID uuid = UUID.randomUUID();
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		randomCode = sDateFormat.format(new Date()) + uuid.hashCode();
		return randomCode.replace("-", "").substring(0, 20);
	}
	public static String getRandom() {
		String s=SecurityUtil.md5(getRandomCode());
		return s.substring(s.length()-10, s.length());
	}
	/** 
     * 生成订单编号 
     * @return 
     */  
    public static synchronized String getOrderNo() {  
        String str = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());  
        long orderNum=new Random().nextInt(10000);
        long orderNo = Long.parseLong((str)) * 10000;  
        orderNo += orderNum;;  
        return orderNo+"";  
    }  
	public static void main(String[] args) {
		System.out.println(getRandom());
		System.out.println(getRandom());
		System.out.println(getOrderNo());
	}
}
