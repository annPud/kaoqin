package com.zhu.kaoqin.tb;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据库表对象
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 2.0
 */
public class Kaoqin implements Serializable{
	private static final long serialVersionUID = -166124388715437354L;
	private Long id;
	private String dep;
	private String ename;
	private Integer ckno;
	private Date clock;
	private Integer mac;
	private Integer no;
	private String way;
	private Integer cdno;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Date getClock() {
		return clock;
	}

	public void setClock(Date clock) {
		this.clock = clock;
	}

	public Integer getMac() {
		return mac;
	}

	public void setMac(Integer mac) {
		this.mac = mac;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public String getWay() {
		return way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	public Integer getCdno() {
		return cdno;
	}

	public void setCdno(Integer cdno) {
		this.cdno = cdno;
	}
}
