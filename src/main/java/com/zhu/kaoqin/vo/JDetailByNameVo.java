package com.zhu.kaoqin.vo;

import java.util.List;

import com.zhu.kaoqin.tb.Kaoqin;

/**
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.0
 */
public class JDetailByNameVo extends GlobalRequestVo {

	private static final long serialVersionUID = 1783920573043832892L;

	private String ename;

	private List<Kaoqin> list;

	public List<Kaoqin> getList() {
		return list;
	}

	public void setList(List<Kaoqin> list) {
		this.list = list;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}
}
