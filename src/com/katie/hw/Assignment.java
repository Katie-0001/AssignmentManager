package com.katie.hw;

import java.util.Date;

public class Assignment {

	private Date dueDate;
	private Date assignedDate;
	private String name;
	private boolean isCompleted = false;
		
	public Assignment(Date dueDate, Date assignedDate, String name) {
		super();
		this.dueDate = dueDate;
		this.assignedDate = assignedDate;
		this.name = name;
	}
	
	public Date getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isCompleted() {
		return isCompleted;
	}
	
	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	
	public Date getAssignedDate() {
		return assignedDate;
	}
	
	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}
}
