package com.zhu.kaoqin.view;

import java.io.Serializable;

/**
 * 名称分组
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 2.0
 */
public class NameView implements Serializable {
	private static final long serialVersionUID = 4727023736355336556L;
	private String dep;
	private String ename;
	private Integer ckno;

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Integer getCkno() {
		return ckno;
	}

	public void setCkno(Integer ckno) {
		this.ckno = ckno;
	}
}
