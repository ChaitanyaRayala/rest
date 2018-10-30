package org.chaitanya.test.rest.resources.beans;

import javax.ws.rs.QueryParam;

public class MessageFilterBean {

	@QueryParam("year") 
	private int year;
	
	@QueryParam("start")
	private int startPoint;
	
	@QueryParam("size") 
	private int size;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(int startPoint) {
		this.startPoint = startPoint;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
