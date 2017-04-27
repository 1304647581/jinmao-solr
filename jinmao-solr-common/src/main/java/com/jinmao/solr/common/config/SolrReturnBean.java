package com.jinmao.solr.common.config;

import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;

/**
 * @描述: 返回值实体
 * @创建者：lvxuepeng
 * @创建时间： 2014-6-30上午11:36:12
 *
 */
public class SolrReturnBean {
	
	private HttpSolrServer httpSolrServer;
	
	private QueryResponse queryResponse;
	
	private UpdateResponse UpdateResponse;
	
	/**
	 * @描述 : 
	 * @创建者：lvxuepeng
	 * @创建时间： 2014-6-30上午11:38:38
	 *
	 */
	public SolrReturnBean() {
		super();
	}

	/**
	 * @描述 : 
	 * @创建者：lvxuepeng
	 * @创建时间： 2014-6-30上午11:38:38
	 *
	 * @param httpSolrServer
	 * @param queryResponse
	 * @param updateResponse
	 */
	public SolrReturnBean(HttpSolrServer httpSolrServer,
			QueryResponse queryResponse,
			UpdateResponse updateResponse) {
		super();
		this.httpSolrServer = httpSolrServer;
		this.queryResponse = queryResponse;
		UpdateResponse = updateResponse;
	}

	public HttpSolrServer getHttpSolrServer() {
		return httpSolrServer;
	}

	public void setHttpSolrServer(HttpSolrServer httpSolrServer) {
		this.httpSolrServer = httpSolrServer;
	}

	public QueryResponse getQueryResponse() {
		return queryResponse;
	}

	public void setQueryResponse(QueryResponse queryResponse) {
		this.queryResponse = queryResponse;
	}

	public UpdateResponse getUpdateResponse() {
		return UpdateResponse;
	}

	public void setUpdateResponse(UpdateResponse updateResponse) {
		UpdateResponse = updateResponse;
	}

}
