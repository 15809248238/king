package com.pos.server;

import java.util.List;
import com.pos.mode.Employee;

public interface EmployeeServices {
	
	public List<Employee> findAll();
	
	public List<Employee> findByEmployeename(String employeeName);
	
	public List<Employee> insert(Employee employee);
	
	public List<Employee> update(Employee employee);
	
	public List<Employee> delete(int employeeID,String departname);
	
}
