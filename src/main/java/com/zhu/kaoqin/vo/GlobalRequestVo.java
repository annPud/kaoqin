/**
 * 
 */
package com.zhu.kaoqin.vo;

import java.io.Serializable;

/**
 * 
 * @作者： 朱伟亮
 * @创建时间：2013-4-18 上午9:55:00
 */
public class GlobalRequestVo implements Serializable {

	/**
	 * 
	 * @作者：朱伟亮
	 * @创建时间：2013-4-18 上午9:56:02
	 */
	private static final long serialVersionUID = 348059632804958380L;
	private String monthDate;

	/**
	 * @return the monthDate
	 */
	public String getMonthDate() {
		return monthDate;
	}

	/**
	 * @param monthDate
	 *            the monthDate to set
	 */
	public void setMonthDate(String monthDate) {
		this.monthDate = monthDate;
	}

}
