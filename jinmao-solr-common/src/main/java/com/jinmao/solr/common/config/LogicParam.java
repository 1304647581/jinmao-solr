package com.jinmao.solr.common.config;


/***
 * 
 * @描述: 逻辑参数
 * @创建者：lvxuepeng
 * @创建时间： 2014-8-4下午1:47:39
 *
 */
public class LogicParam {
	
	/* 参数体 */
	private String param;
	/* 逻辑关系：AND OR - */
	private String type;
	
	public LogicParam() {
		super();
	}
	
	public LogicParam(String param, String type) {
		super();
		this.param = param;
		this.type = type;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
