package com.pos.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pos.mode.SaleOrder;

public interface SaleDao {
	
	public List<SaleOrder> findAll();
	
	public List<SaleOrder> findByCustomerID(@Param("customerID")int customerID);
	
	public boolean insert(SaleOrder saleOrder);
	
	public boolean update(SaleOrder saleOrder);
	
	public boolean delete(@Param("saleOrderID")int saleOrderID);
}
