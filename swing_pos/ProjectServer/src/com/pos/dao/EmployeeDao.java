package com.pos.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.pos.mode.Employee;

public interface EmployeeDao {

	public List<Employee> findAll();
	
	public List<Employee> findByEmployeename(@Param("employeeName")String employeeName);
	
	public boolean insert(Employee employee);
	
	public boolean update(Employee employee);
	
	public boolean delete(@Param("employeeID")int employeeID);
	
}
