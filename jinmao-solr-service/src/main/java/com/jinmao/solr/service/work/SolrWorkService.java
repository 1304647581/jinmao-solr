package com.jinmao.solr.service.work;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.jinmao.solr.common.client.SolrClient;

@Service("solrWorkService")
public class SolrWorkService {
	/*******************************声明区****************************************/
	private static final Log LOG = LogFactory.getLog("----------- SolrWorkService Error -----------");
	//注入Solr模型
	private SolrClient solrClient;
	/*******************************方法区****************************************/
	/***
	 * @描述 : Solr自动更新索引
	 * @创建者：lvxuepeng
	 * @创建时间： 2014-8-19上午9:25:58
	 */
	public void solrWork(){
		try {
			solrClient.work();
		} catch (Exception e) {
			LOG.error("###### solrWork error #######", e);
		}
	}
	/*******************************get/set****************************************/
	public SolrClient getSolrClient() {
		return solrClient;
	}

	public void setSolrClient(SolrClient solrClient) {
		this.solrClient = solrClient;
	}
}
