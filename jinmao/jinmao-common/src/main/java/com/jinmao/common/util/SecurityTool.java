package com.jinmao.common.util;

import java.util.Map;

import com.jinmao.common.dict.AjaxDict;
import com.tools.common.struts.result.Result;
import com.tools.common.util.json.JsonUtil;
import com.tools.common.util.security.SecurityUtil;
/**
 * @描述 : 加密工具
 * @创建时间 : 2016-3-6 下午12:12:41
 */
public class SecurityTool {
	/** 加密 **/
	public static String encrypt(String data) {
		Result result = new Result();
		String time = RandomUtil.getRandomCode();
		result.addModel("time", time);
		result.addModel("data", data);
		return SecurityUtil.encrypt(result.resultJson(),
				AjaxDict.KEY.getState());
	}

	/** 解密 **/
	@SuppressWarnings("unchecked")
	public static String decrypt(String data) {
		String json = SecurityUtil.decrypt(data, AjaxDict.KEY.getState());
		Map<String, String> map = JsonUtil.fromJson(json, Map.class);
		return map.get("data");
	}


	public static void main(String[] args) {
		System.out.println(encrypt("13047978787848@qq.com").length());
		System.out
				.println(decrypt("4C14CAAAAF9EA1909A2E0DB8DA1EE88E4924BD62A321A92D4054565F6DF64F9F5B6784DF62C52A9E2773FD84B2FA8427772C227718566EE63764220D8EAD9584"));
	}
}
