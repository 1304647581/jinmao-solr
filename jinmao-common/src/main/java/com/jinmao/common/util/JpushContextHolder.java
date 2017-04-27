package com.jinmao.common.util;

public class JpushContextHolder {
	private static final ThreadLocal contextHolder = new ThreadLocal();  
    public static void setJpushType(String jpushType) {  
        contextHolder.set(jpushType);  
    }  
  
    public static String getJpushType() {  
        return (String) contextHolder.get();  
    }  
  
    public static void clearJpushType() {  
        contextHolder.remove();  
    }  
  
}
