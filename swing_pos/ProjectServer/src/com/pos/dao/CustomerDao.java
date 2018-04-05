package com.pos.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.pos.mode.Customer;

public interface CustomerDao {
	public List<Customer> findAll();
	
	public List<Customer> findByCustomername(@Param("customerName")String customerName);
	
	public boolean insert(Customer customer);
	
	public boolean update(Customer customer);
	
	public boolean delete(@Param("customerID")int customerID);
}
