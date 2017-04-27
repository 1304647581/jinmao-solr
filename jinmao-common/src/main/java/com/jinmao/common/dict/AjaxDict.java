package com.jinmao.common.dict;

/**
 * @描述 : ajax请求返回状态枚举
 * @创建者：lvxuepeng
 * @创建时间： 2014-5-20下午1:14:47
 * 
 */
public enum AjaxDict {
	/** 请求成功 **/
	REQUEST_SUCCESS("200"),
	/** 系统异常 **/
	SYSTEM_EXCEPTION("500"),
	/** 请求的内容为空 **/
	NO_CONTENT("204"),
	/** 请求失败 **/
	REQUEST_FAILURE("400"),
	/** 用户pin存在 **/
	YESPIN("201"),
	/** 无权限 **/
	NOPERMI("800"),
	/** 尚未登录 **/
	NOLOGIN("900"),
	/**加密的Key**/
	KEY("!q@w#E$R!q@w#E$R!q@w#E$R"),
	/**达到最大上限**/
	MAXNUM("300");
	
	private String state;

	private AjaxDict(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

}
