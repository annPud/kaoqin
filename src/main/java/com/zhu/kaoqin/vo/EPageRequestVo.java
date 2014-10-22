/**
 * 
 */
package com.zhu.kaoqin.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * 
 */
public class EPageRequestVo extends GlobalRequestVo{

	private static final long serialVersionUID = 3707143841178832779L;
	/**
	 * page 当前请求页面数
	 */
	private Integer page = 1;

	/**
	 * rows 每页显示行数
	 */
	private Integer rows = 10;

	/**
	 * queryConditionMap 查询条件
	 */
	private Map<String, Object> queryParams = new HashMap<String, Object>();



	/**
	 * @return the page
	 */
	public Integer getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(Integer page) {
		this.page = page;
	}

	/**
	 * @return the queryParams
	 */
	public Map<String, Object> getQueryParams() {
		return queryParams;
	}

	/**
	 * @param queryParams
	 *            the queryParams to set
	 */
	public void setQueryParams(Map<String, Object> queryParams) {
		this.queryParams = queryParams;
	}

	/**
	 * @return the rows
	 */
	public Integer getRows() {
		return rows;
	}

	/**
	 * @param rows
	 *            the rows to set
	 */
	public void setRows(Integer rows) {
		this.rows = rows;
	}

}
