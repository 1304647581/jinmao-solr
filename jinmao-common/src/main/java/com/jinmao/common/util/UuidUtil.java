package com.jinmao.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @描述 : 唯一标识uuid工具类
 * @创建者：liushengsong
 * @创建时间： 2014-5-5下午5:30:05
 */
public class UuidUtil {

	/**
	 * @描述 : 获取一个UUID标识
	 * @创建者：liushengsong
	 * @创建时间： 2014-5-5下午5:31:53
	 * 
	 * @return
	 */
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}

	/**
	 * 
	 * @描述 : 获取时间类型的随机字符串
	 * @创建者：lvxuepeng
	 * @创建时间： 2014-5-15下午3:49:29
	 * 
	 * @return
	 */
	public static String getRandomCode() {
		String randomCode = null;
		UUID uuid = UUID.randomUUID();
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		randomCode = sDateFormat.format(new Date()) + uuid.hashCode();
		return randomCode.replace("-", "").substring(0, 20);
	}
}
