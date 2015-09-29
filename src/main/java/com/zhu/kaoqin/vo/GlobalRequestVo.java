package com.zhu.kaoqin.vo;

import java.io.Serializable;

/**
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.0
 */
public class GlobalRequestVo implements Serializable {

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
