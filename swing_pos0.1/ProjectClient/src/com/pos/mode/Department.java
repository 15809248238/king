package com.pos.mode;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Department implements Serializable{
	
	private int departmentID;
	
	private String parentdepartname;
	
	private String name;
	
	private String manager;
	
	private String departmentphone;
	
	private String creator;
	
	private String creattime;
	
	private String modifier;
	
	private String modifytime;
	
	private int employeecount;
	
	public Department() {
		
	}

	public String getCreattime() {
		return creattime;
	}

	public void setCreattime(String creattime) {
		this.creattime = creattime;
	}

	public int getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}

	public String getParentdepartname() {
		return parentdepartname;
	}

	public void setParentdepartname(String parentdepartname) {
		this.parentdepartname = parentdepartname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getDepartmentphone() {
		return departmentphone;
	}

	public void setDepartmentphone(String departmentphone) {
		this.departmentphone = departmentphone;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getModifytime() {
		return modifytime;
	}

	public void setModifytime(String modifytime) {
		this.modifytime = modifytime;
	}

	public int getEmployeecount() {
		return employeecount;
	}

	public void setEmployeecount(int employeecount) {
		this.employeecount = employeecount;
	}

	public String toString()
	{
		return departmentID+"+"+parentdepartname+"+"+name+"+"+manager+"+"+departmentphone+"+"+
				employeecount+"+"+creator+"+"+creattime+"+"+modifier+"+"+modifytime;
		
	}
}
