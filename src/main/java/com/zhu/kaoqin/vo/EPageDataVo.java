/**
 * 
 */
package com.zhu.kaoqin.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * 
 */
public class EPageDataVo<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4145752231018583497L;

	public EPageDataVo() {
	}

	public EPageDataVo(List<T> rows, Long total) {
		this.rows = rows;
		this.total = total;
	}

	private List<T> rows = new ArrayList<T>();
	private Long total;

	/**
	 * @return the rows
	 */
	public List<T> getRows() {
		return rows;
	}

	/**
	 * @param rows
	 *            the rows to set
	 */
	public void setRows(List<T> rows) {
		this.rows = rows;
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

}
