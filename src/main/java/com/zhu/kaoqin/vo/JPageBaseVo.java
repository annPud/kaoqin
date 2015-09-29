/**
 * 
 */
package com.zhu.kaoqin.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.0
 */
public class JPageBaseVo extends GlobalRequestVo implements Serializable {
	private static final long serialVersionUID = 3658685237491035594L;
	/**
	 * 请求参数
	 */
	private Integer page;
	private Integer rows;
	private String sidx;
	private String sord;

	/**
	 * 应答参数
	 */

	private List list;
	private Long total;
	private Long records;

	/**
	 * @return the list
	 */
	public List getList() {
		return list;
	}

	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List list) {
		this.list = list;
	}

	/**
	 * @return the total
	 */
	public Long getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(Long total) {
		this.total = total;
	}

	/**
	 * @return the records
	 */
	public Long getRecords() {
		return records;
	}

	/**
	 * @param records
	 *            the records to set
	 */
	public void setRecords(Long records) {
		this.records = records;
	}

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

	/**
	 * @return the sidx
	 */
	public String getSidx() {
		return sidx;
	}

	/**
	 * @param sidx
	 *            the sidx to set
	 */
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	/**
	 * @return the sord
	 */
	public String getSord() {
		return sord;
	}

	/**
	 * @param sord
	 *            the sord to set
	 */
	public void setSord(String sord) {
		this.sord = sord;
	}

}
