package com.jinmao.solr.common.config;

/***
 * 
 * @描述: 排序实体
 * @创建者：lvxuepeng
 * @创建时间： 2014-9-10上午11:50:06
 *
 */
public class SortBean {

	/*按sortKey排序 */
	private String sortKey;
	/*排序规则：ASC,DESC*/
	private String sortBy;
	
	public SortBean() {
		super();
	}
	
	public SortBean(String sortKey, String sortBy) {
		super();
		this.sortKey = sortKey;
		this.sortBy = sortBy;
	}

	public String getSortKey() {
		return sortKey;
	}

	public void setSortKey(String sortKey) {
		this.sortKey = sortKey;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

}
