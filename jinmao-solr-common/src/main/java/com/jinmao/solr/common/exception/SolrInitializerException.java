package com.jinmao.solr.common.exception;

/***
 * 
 * @描述:  solr初始化异常
 * @创建者：lv
 * @创建时间： 2014-6-30上午8:10:55
 *
 */
public class SolrInitializerException extends ExceptionInInitializerError {

	private static final long serialVersionUID = -26307032130756735L;

	public SolrInitializerException(String msg) {
		super(msg);
	}

	public SolrInitializerException(Throwable cause) {
		super(cause);
	}

}
