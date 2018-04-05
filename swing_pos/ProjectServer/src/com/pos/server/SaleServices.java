package com.pos.server;

import java.util.List;

import com.pos.mode.SaleOrder;

public interface SaleServices {
	
	public List<SaleOrder> findAll();
	
	public List<SaleOrder> findByCustomerID(int customerID);
	
	public List<SaleOrder> insert(SaleOrder saleOrder);
	
	public List<SaleOrder> update(SaleOrder saleOrder);
	
	public List<SaleOrder> delete(SaleOrder saleOrder);
}
