/**
 * 
 */
package com.zhu.kaoqin.vo;

import java.io.Serializable;
import java.util.List;

import com.zhu.kaoqin.entity.KaoqinEntity;

/**
 *
 * @作者： 朱伟亮
 * @创建时间：2013-5-7 上午9:16:21
 */
public class JDetailByNameVo extends GlobalRequestVo{

	/**
	 * 
	 * @作者：朱伟亮
	 * @创建时间：2013-5-7 上午9:18:54
	 */
	private static final long serialVersionUID = 1783920573043832892L;

	private String ename;

//	private String offTime;
	
	private List<KaoqinEntity> list;

	public List<KaoqinEntity> getList() {
		return list;
	}

	public void setList(List<KaoqinEntity> list) {
		this.list = list;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	// public String getOffTime() {
	// return offTime;
	// }
	//
	// public void setOffTime(String offTime) {
	// this.offTime = offTime;
	// }
	


}
