package com.pos.server.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pos.dao.DepartDao;
import com.pos.dao.EmployeeDao;
import com.pos.dao.UserDao;
import com.pos.mode.Department;
import com.pos.mode.Employee;
import com.pos.mode.User;
import com.pos.server.EmployeeServices;

@Transactional
@Component
public class EmployeeServicesImpl implements EmployeeServices{

	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private DepartDao departDao;
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public List<Employee> findAll() {
		
		List<Employee> list = employeeDao.findAll();
		return list;
	
	}

	@Override
	public List<Employee> findByEmployeename(String employeeName) {
		
		employeeName = "%"+employeeName+"%";
		
		List<Employee> list = employeeDao.findByEmployeename(employeeName);
		
		return list;
	}

	@Override
	public List<Employee> insert(Employee employee) {
		List<Employee> list = null;
		
		updateDepartEmployeeCount(employee.getDepartmentName(),1);
		
		User user = new User();
		user.setUsername(employee.getPhone());
		user.setPassword("123456");
		userDao.insertUserByUser(user);
		
		if(employeeDao.insert(employee))
		{
			list = findAll();
		}
		
		return list;
	}

	@Override
	public List<Employee> update(Employee employee) {
		
		List<Employee> list = null;
		
		if(employeeDao.update(employee))
		{
			list = findAll();
		}
		
		return list;
	}

	@Override
	public List<Employee> delete(int employeeID,String departname) {
		List<Employee> list = null;
		updateDepartEmployeeCount(departname,-1);
		if(employeeDao.delete(employeeID))
		{
			list = findAll();
		}
		return list;
	}

	public void updateDepartEmployeeCount(String departName,int num)
	{
		departDao.updateEmployeeCount(departName,num);
		List<Department> list = departDao.findByDepartname(departName);
		
		if(!"".equals(list.get(0).getParentdepartname())) {
			updateDepartEmployeeCount(list.get(0).getParentdepartname(),num);
		}
	}
}
