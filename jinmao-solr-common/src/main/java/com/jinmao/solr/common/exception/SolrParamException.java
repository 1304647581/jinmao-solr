package com.jinmao.solr.common.exception;

/**
 * @描述: 参数异常
 * @创建者：lv
 * @创建时间： 2014-6-30上午10:46:23
 *
 */
public class SolrParamException extends Exception{

	private static final long serialVersionUID = -5856529281587081699L;

	public SolrParamException(String msg) {
		super(msg);
	}

	public SolrParamException(Throwable cause) {
		super(cause);
	}

	public SolrParamException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
