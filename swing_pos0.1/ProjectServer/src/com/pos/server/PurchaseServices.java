package com.pos.server;

import java.net.Socket;
import java.util.List;

import com.pos.mode.Purchaseorder;

public interface PurchaseServices {
	
	public List<Purchaseorder> findAll();
	
	public List<Purchaseorder> findByCustomerID(int customerID);
	
	public List<Purchaseorder> insert(Purchaseorder purchaseorder);
	
	public List<Purchaseorder> update(Purchaseorder purchaseorder,Socket socket);
	
	public List<Purchaseorder> delete(Purchaseorder purchaseorder);
}
