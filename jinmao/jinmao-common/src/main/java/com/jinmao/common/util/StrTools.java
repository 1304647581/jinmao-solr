package com.jinmao.common.util;
/**
 * @描述 : 字符串工具类
 * @创建者 : HeZeMin
 * @创建时间 : 2016-3-8 下午7:49:26
 */
public class StrTools {
	/**
	 * @描述 : 隐藏手机号
	 * @创建者 : HeZeMin
	 * @创建时间 : 2016-3-8 下午7:50:36
	 * @param tel	手机号
	 * @return	返回处理过的手机号,123***789
	 */
	public static String hideTel(String tel){
		if(null != tel && tel.length() == 11){
			String str1 = tel.substring(0, 3);
			String str2 = tel.substring(7, 11);
			StringBuffer sb = new StringBuffer();
			sb.append(str1).append("****").append(str2);
			return sb.toString();
		}
		return null;
	}
	/**
	 * @描述 : 隐藏身份证号码
	 * @创建者 : HeZeMin
	 * @创建时间 : 2016-3-8 下午7:53:28
	 * @param idNumber	身份证号码
	 * @return	返回处理过的身份证号码
	 */
	public static String hideIdNumber(String idNumber){
		if(null != idNumber && idNumber.length() == 18){
			String str1 = idNumber.substring(0, 3);
			String str2 = idNumber.substring(14, 18);
			StringBuffer sb = new StringBuffer();
			sb.append(str1).append("***********").append(str2);
			return sb.toString();                                                                                                            
		}
		return null;
	}
}