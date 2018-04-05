package com.pos.server;

import java.util.List;

import com.pos.mode.Purchaseorder;

public interface PurchaseServices {
	
	public List<Purchaseorder> findAll();
	
	public List<Purchaseorder> findByCustomerID(int customerID);
	
	public List<Purchaseorder> insert(Purchaseorder purchaseorder);
	
	public List<Purchaseorder> update(Purchaseorder purchaseorder);
	
	public List<Purchaseorder> delete(Purchaseorder purchaseorder);
}
