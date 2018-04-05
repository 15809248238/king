package com.pos.server.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pos.dao.CustomerDao;
import com.pos.mode.Customer;
import com.pos.server.CustomerServices;

@Transactional
@Component
public class CustomerServicesImpl implements CustomerServices{

	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public List<Customer> findAll() {
		
		List<Customer> list = null;
		
		list = customerDao.findAll();
		
		return list;
	}

	@Override
	public List<Customer> findByCustomername(String customerName) {
		
		List<Customer> list = null;
		
		customerName = "%"+customerName+"%";
		
		list = customerDao.findByCustomername(customerName);
		
		return list;
	}

	@Override
	public List<Customer> insert(Customer customer) {
		
		List<Customer> list = null;
		
		if(customerDao.insert(customer))
		{
			list = findAll();
		}
		
		return list;
	}

	@Override
	public List<Customer> update(Customer customer) {
		List<Customer> list = null;
		
		if(customerDao.update(customer))
		{
			list = findAll();
		}
		
		return list;
	}

	@Override
	public List<Customer> delete(int customerID) {
		List<Customer> list = null;
		
		if(customerDao.delete(customerID))
		{
			list = findAll();
		}
		
		return list;
	}

}
