/**
 * 
 */
package com.zhu.kaoqin.vo;

/**
 * 
 * @作者： 朱伟亮
 * @创建时间：2013-4-22 上午10:30:27
 */
public class EDetailByNameVo extends EPageRequestVo {

	/**
	 * 
	 * @作者：朱伟亮
	 * @创建时间：2013-4-22 上午10:30:53
	 */
	private static final long serialVersionUID = -605537555421387770L;

	private String ename;

	private String offTime;

	/**
	 * @return the offTime
	 */
	public String getOffTime() {
		return offTime;
	}

	/**
	 * @param offTime
	 *            the offTime to set
	 */
	public void setOffTime(String offTime) {
		this.offTime = offTime;
	}

	/**
	 * @return the ename
	 */
	public String getEname() {
		return ename;
	}

	/**
	 * @param ename
	 *            the ename to set
	 */
	public void setEname(String ename) {
		this.ename = ename;
	}

}
