package com.jinmao.solr.common.config;

import java.util.ArrayList;
import java.util.List;


/**
 * @描述: Solr操作参数对象
 * @创建者：lvxuepeng
 * @创建时间： 2014-6-30上午9:09:17
 *
 */
public class SolrBean {
	
	/*索引库名称 */
	private String dbName;
	/*页码 */
	private int page;
	/*每页条数 */
	private int pageSize;
	/*按sortKey排序 */
	//private String sortKey;
	/*排序规则：ASC,DESC*/
	//private String sortBy;
	/* 排序List */
	List<SortBean> sortList = new ArrayList<SortBean>();
	/*是否高亮 true,false */
	private boolean hight;
	/*高亮字段,多字段用","隔开 */
	private String hightKey;
	/*查询参数 */
	private List<LogicParam> selectParams;
	/*删除参数 */
	private String deleteParams;
	/* 是否使用统计 */
	private boolean facet;
	/* 统计字段 */
	private List<String> facetFields;
	private String flValue;
	/**
	 * @描述 : 
	 * @创建者：lvxuepeng
	 * @创建时间： 2014-6-30上午9:16:57
	 *
	 */
	public SolrBean() {
		super();
	}


	/**
	 * @描述 : 
	 * @创建者：lvxuepeng
	 * @创建时间： 2014-6-30上午9:16:38
	 *
	 * @param dbName
	 * @param page
	 * @param pageSize
	 * @param sortKey
	 * @param sortBy
	 * @param hight
	 * @param hightKey
	 * @param selectParams
	 * @param deleteParams
	 * @param facet
	 * @param facetFields
	 */
	public SolrBean(String dbName, int page, int pageSize,
			List<SortBean> sortList, boolean hight, String hightKey,
			List<LogicParam> selectParams,String deleteParams,boolean facet,
			List<String> facetFields,String flValue) {
		super();
		this.dbName = dbName;
		this.page = page;
		this.pageSize = pageSize;
		this.sortList = sortList;
		this.hight = hight;
		this.hightKey = hightKey;
		this.selectParams = selectParams;
		this.deleteParams = deleteParams;
		this.facet = facet;
		this.facetFields = facetFields;
		this.flValue=flValue;
	}
	
	public String getFlValue() {
		return flValue;
	}


	public void setFlValue(String flValue) {
		this.flValue = flValue;
	}


	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/*public String getSortKey() {
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
	}*/
	
	public List<SortBean> getSortList() {
		return sortList;
	}

	public void setSortList(List<SortBean> sortList) {
		this.sortList = sortList;
	}

	public boolean isHight() {
		return hight;
	}

	public void setHight(boolean hight) {
		this.hight = hight;
	}

	public String getHightKey() {
		return hightKey;
	}

	public void setHightKey(String hightKey) {
		this.hightKey = hightKey;
	}

	public List<LogicParam> getSelectParams() {
		return selectParams;
	}

	public void setSelectParams(List<LogicParam> selectParams) {
		this.selectParams = selectParams;
	}
	
	public String getDeleteParams() {
		return deleteParams;
	}

	public void setDeleteParams(String deleteParams) {
		this.deleteParams = deleteParams;
	}

	public boolean isFacet() {
		return facet;
	}

	public void setFacet(boolean facet) {
		this.facet = facet;
	}

	public List<String> getFacetFields() {
		return facetFields;
	}

	public void setFacetFields(List<String> facetFields) {
		this.facetFields = facetFields;
	}

}
