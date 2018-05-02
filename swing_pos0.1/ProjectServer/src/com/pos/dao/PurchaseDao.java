package com.pos.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pos.mode.Purchaseorder;

public interface PurchaseDao {
	
	public List<Purchaseorder> findAll();
	
	public List<Purchaseorder> findByCustomerID(@Param("customerID")int customerID);
	
	public boolean insert(Purchaseorder purchaseorder);
	
	public boolean update(Purchaseorder purchaseorder);
	
	public boolean delete(@Param("purchaseorderID")int purchaseorderID);
	
	
}
