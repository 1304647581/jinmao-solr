package com.jinmao.solr.common.config;


/**
 * @描述:  solr连接配置对象
 * @创建者：lvxuepeng
 * @创建时间： 2014-6-27下午4:53:44
 *
 */
public class SolrConfig {
	
	/**
	 * 写服务连接（master）
	 */
	public String URLWriter;
	/**
	 * 读服务连接（slave）
	 */
	public String URLReader;

	//socket读取超时时间
	private Integer soTimeOut;
	
	//连接超时时间
	private Integer connectionTimeOut;
	
	//每个主机默认最大连接数
	private Integer defaultMaxConnectionsPerHost;
	
	//最大连接数
	private Integer maxTotalConnections;
	
	//是否重定向，默认false
	private Boolean followRedirects;
	
	//是否允许压缩
	private Boolean allowCompression;
	
	//最大重试次数，默认0 推荐1
	private Integer maxRetries;
	
	//索引库名称List
	private String dbNames;


	public Integer getSoTimeOut() {
		return soTimeOut;
	}

	public void setSoTimeOut(Integer soTimeOut) {
		this.soTimeOut = soTimeOut;
	}

	public Integer getConnectionTimeOut() {
		return connectionTimeOut;
	}

	public void setConnectionTimeOut(Integer connectionTimeOut) {
		this.connectionTimeOut = connectionTimeOut;
	}

	public Integer getDefaultMaxConnectionsPerHost() {
		return defaultMaxConnectionsPerHost;
	}

	public void setDefaultMaxConnectionsPerHost(Integer defaultMaxConnectionsPerHost) {
		this.defaultMaxConnectionsPerHost = defaultMaxConnectionsPerHost;
	}

	public Integer getMaxTotalConnections() {
		return maxTotalConnections;
	}

	public void setMaxTotalConnections(Integer maxTotalConnections) {
		this.maxTotalConnections = maxTotalConnections;
	}

	public Boolean getFollowRedirects() {
		return followRedirects;
	}

	public void setFollowRedirects(Boolean followRedirects) {
		this.followRedirects = followRedirects;
	}

	public Boolean getAllowCompression() {
		return allowCompression;
	}

	public void setAllowCompression(Boolean allowCompression) {
		this.allowCompression = allowCompression;
	}

	public Integer getMaxRetries() {
		return maxRetries;
	}

	public void setMaxRetries(Integer maxRetries) {
		this.maxRetries = maxRetries;
	}
	
	public String getURLWriter() {
		return URLWriter;
	}

	public void setURLWriter(String uRLWriter) {
		URLWriter = uRLWriter;
	}

	public String getURLReader() {
		return URLReader;
	}

	public void setURLReader(String uRLReader) {
		URLReader = uRLReader;
	}
	
	public String getDbNames() {
		return dbNames;
	}

	public void setDbNames(String dbNames) {
		this.dbNames = dbNames;
	}
	
}
