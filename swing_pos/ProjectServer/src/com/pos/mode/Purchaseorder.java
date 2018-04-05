package com.pos.mode;

import java.io.Serializable;


@SuppressWarnings("serial")
public class Purchaseorder implements Serializable{
	
	private int purchaseorderID;
	
	private int customerID;
	
	private int warehouseID;
	
	private int cargoID;
	
	private int amount;
	
	private String date;
	
	private String status;
	
	public int getPurchaseorderID() {
		return purchaseorderID;
	}

	public void setPurchaseorderID(int purchaseorderID) {
		this.purchaseorderID = purchaseorderID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String toString()
	{
		return purchaseorderID+"+"+customerID+"+"+warehouseID+"+"+cargoID+"+"+amount+"+"+date+"+"+status;
	}

}
