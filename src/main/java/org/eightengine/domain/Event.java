package org.eightengine.domain;

import java.util.Date;

public class Event {
	
	private String idEx;
	private String title;
	private Date date;
	
	public String getIdEx() {
		return idEx;
	}
	public void setId(String idEx) {
		this.idEx = idEx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
