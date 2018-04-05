package com.pos.server;

import java.util.List;
import com.pos.mode.Customer;

public interface CustomerServices {
	
	public List<Customer> findAll();
	
	public List<Customer> findByCustomername(String customerName);
	
	public List<Customer> insert(Customer customer);
	
	public List<Customer> update(Customer customer);
	
	public List<Customer> delete(int customerID);
}
