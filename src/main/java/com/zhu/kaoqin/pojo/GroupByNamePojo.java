/**
 * 
 */
package com.zhu.kaoqin.pojo;

import java.io.Serializable;

/**
 *
 * @作者： 朱伟亮
 * @创建时间：2013-4-18 上午10:10:12
 */
public class GroupByNamePojo implements Serializable{

	/**
	 * 
	 * @作者：朱伟亮
	 * @创建时间：2013-4-18 下午1:40:22
	 */
	private static final long serialVersionUID = -6755654422331812156L;
	private String dep;
	private String ename;
	private Integer ckno;
	/**
	 * @return the dep
	 */
	public String getDep() {
		return dep;
	}
	/**
	 * @param dep the dep to set
	 */
	public void setDep(String dep) {
		this.dep = dep;
	}
	/**
	 * @return the ename
	 */
	public String getEname() {
		return ename;
	}
	/**
	 * @param ename the ename to set
	 */
	public void setEname(String ename) {
		this.ename = ename;
	}
	/**
	 * @return the ckno
	 */
	public Integer getCkno() {
		return ckno;
	}
	/**
	 * @param ckno the ckno to set
	 */
	public void setCkno(Integer ckno) {
		this.ckno = ckno;
	}
	/**
	 * @param dep
	 * @param ename
	 * @param ckno
	 */
	public GroupByNamePojo(String dep, String ename, Integer ckno) {
		super();
		this.dep = dep;
		this.ename = ename;
		this.ckno = ckno;
	}
	/**
	 * 
	 */
	public GroupByNamePojo() {
		super();
	}

	
}
