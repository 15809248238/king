package com.pos.mode;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Inventory implements Serializable{

	private int inventoryID;
	
	private int warehouseID;
	
	private int cargoID;
	
	private int amount;
	
	private String creator;
	
	private String creattime;
	
	private String modifier;
	
	private String modifytime;
	
	public int getInventoryID() {
		return inventoryID;
	}

	public void setInventoryID(int inventoryID) {
		this.inventoryID = inventoryID;
	}
	
	public int getWarehouseID() {
		return warehouseID;
	}

	public void setWarehouseID(int warehouseID) {
		this.warehouseID = warehouseID;
	}

	public int getCargoID() {
		return cargoID;
	}

	public void setCargoID(int cargoID) {
		this.cargoID = cargoID;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
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
		return inventoryID+"+"+warehouseID+"+"+cargoID+"+"+amount+"+"+
			   creator+"+"+creattime+"+"+modifier+"+"+modifytime;
	}
	
}
