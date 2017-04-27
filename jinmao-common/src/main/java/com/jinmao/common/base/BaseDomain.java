package com.jinmao.common.base;

import java.io.Serializable;
import java.util.Date;

import com.tools.common.util.json.JsonUtil;

/**
 * @描述 : 基础domain
 * @创建者 : HeZeMin
 * @创建时间 : 2017-3-8 下午3:34:18
 */
@SuppressWarnings("serial")
public class BaseDomain implements Serializable {
	/** 创建时间 **/
	private Date createTime;
	/** 修改时间 **/
	private Date updateTime;
	/** 创建人 **/
	private String createName;
	/** 修改人 **/
	private String updateName;

	/** 起始页默认1 **/
	private int pageIndex = 1;
	/** 起始行数 **/
	private int startRow = 0;
	/** 每页显示条数 **/
	private int pageSize = 10;
	/**** ip ****/
	private String ip;
	private int isDelete;
	private String querySortName="id";// 排序字段
	private String querySortBy="desc";// 排序值desc\asc

	
	
	public BaseDomain() {
	}

	/**
	 * @描述 : 返回实体json串
	 * @创建者 : HeZeMin
	 * @创建时间 : 2017-3-8 下午3:35:02
	 */
	public String toJson() {
		return JsonUtil.toJson(this);
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getPageSize() {
		return pageSize;
	}

	public String getQuerySortName() {
		return querySortName;
	}

	public void setQuerySortName(String querySortName) {
		this.querySortName = querySortName;
	}

	public String getQuerySortBy() {
		return querySortBy;
	}

	public void setQuerySortBy(String querySortBy) {
		this.querySortBy = querySortBy;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	
}
