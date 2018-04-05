package com.pos.mode;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Warehouse implements Serializable{

	private int warehouseID;
	
	private String name;
	
	private String manager;
	
	private String address;
	
	private String status;
	
	private String creator;
	
	private String creattime;
	
	private String modifier;
	
	private String modifytime;
	
	public int getWarehouseID() {
		return warehouseID;
	}

	public void setWarehouseID(int warehouseID) {
		this.warehouseID = warehouseID;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreattime() {
		return creattime;
	}

	public void setCreattime(String creattime) {
		this.creattime = creattime;
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

	public String toString()
	{
		return warehouseID+"+"+name+"+"+manager+"+"+address+"+"+status+"+"+
				creator+"+"+creattime+"+"+modifier+"+"+modifytime;
	}

}
