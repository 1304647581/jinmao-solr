package com.jinmao.solr.common.factory;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.FactoryBean;

import com.jinmao.solr.common.client.SolrClient;
import com.jinmao.solr.common.config.SolrConfig;
import com.jinmao.solr.common.exception.SolrInitializerException;

/**
 * @描述: 
 * @创建者：lv
 * @创建时间： 2014-6-27下午4:25:28
 *
 */
public class SolrClientFactoryBean implements FactoryBean<SolrClient>{
	private SolrConfig solrConfig;

	public SolrClientFactoryBean() {
		this.solrConfig = new SolrConfig();
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.FactoryBean#getObject()
	 */
	@Override
	public SolrClient getObject() throws Exception {
		if(StringUtils.isNotBlank(this.solrConfig.getURLWriter())){
			return new SolrClient(this.solrConfig);
		}else {
			throw new SolrInitializerException(
					"SolrClient init parameter urlwriter is empty,please check spring config file!");
		}
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.FactoryBean#getObjectType()
	 */
	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return SolrClient.class;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.FactoryBean#isSingleton()
	 */
	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}

	public void setSoTimeOut(Integer soTimeOut) {
		this.solrConfig.setSoTimeOut(soTimeOut);
	}

	public void setConnectionTimeOut(Integer connectionTimeOut) {
		this.solrConfig.setConnectionTimeOut(connectionTimeOut);
	}

	public void setDefaultMaxConnectionsPerHost(Integer defaultMaxConnectionsPerHost) {
		this.solrConfig.setDefaultMaxConnectionsPerHost(defaultMaxConnectionsPerHost);
	}

	public void setMaxTotalConnections(Integer maxTotalConnections) {
		this.solrConfig.setMaxTotalConnections(maxTotalConnections);
	}

	public void setFollowRedirects(Boolean followRedirects) {
		this.solrConfig.setFollowRedirects(followRedirects);
	}

	public void setAllowCompression(Boolean allowCompression) {
		this.solrConfig.setAllowCompression(allowCompression);
	}

	public void setMaxRetries(Integer maxRetries) {
		this.solrConfig.setMaxRetries(maxRetries);
	}

	public void setURLWriter(String uRLWriter) {
		this.solrConfig.setURLWriter(uRLWriter);
	}

	public void setURLReader(String uRLReader) {
		this.solrConfig.setURLReader(uRLReader);
	}
	
	public void setDbNames(String dbNames) {
		this.solrConfig.setDbNames(dbNames);
	}
	

}
