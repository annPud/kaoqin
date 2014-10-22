/**
 * 
 */
package com.zhu.kaoqin.vo;

/**
 *
 * @作者： 朱伟亮
 * @创建时间：2013-4-17 下午2:32:09
 */
public class EComelateVo extends EPageRequestVo{

	/**
	 * 
	 * @作者：朱伟亮
	 * @创建时间：2013-4-17 下午2:32:29
	 */
	private static final long serialVersionUID = -211777005954082370L;
	
	private String onClock;
	
	private String offClock;
	

	/**
	 * @return the onClock
	 */
	public String getOnClock() {
		return onClock;
	}

	/**
	 * @param onClock the onClock to set
	 */
	public void setOnClock(String onClock) {
		this.onClock = onClock;
	}

	/**
	 * @return the offClock
	 */
	public String getOffClock() {
		return offClock;
	}

	/**
	 * @param offClock the offClock to set
	 */
	public void setOffClock(String offClock) {
		this.offClock = offClock;
	}

	
}
