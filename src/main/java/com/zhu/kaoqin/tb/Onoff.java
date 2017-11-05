package com.zhu.kaoqin.tb;

import java.time.LocalDate;
import java.time.LocalTime;

public class Onoff {

	String id;
	LocalDate start;
	LocalDate end;
	LocalTime amon;
	LocalTime amoff;
	LocalTime pmon;
	LocalTime pmoff;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}

	public LocalTime getAmon() {
		return amon;
	}

	public void setAmon(LocalTime amon) {
		this.amon = amon;
	}

	public LocalTime getAmoff() {
		return amoff;
	}

	public void setAmoff(LocalTime amoff) {
		this.amoff = amoff;
	}

	public LocalTime getPmon() {
		return pmon;
	}

	public void setPmon(LocalTime pmon) {
		this.pmon = pmon;
	}

	public LocalTime getPmoff() {
		return pmoff;
	}

	public void setPmoff(LocalTime pmoff) {
		this.pmoff = pmoff;
	}

}
