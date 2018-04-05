package com.pos.server.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.pos.dao.DepartDao;
import com.pos.mode.Department;
import com.pos.server.DepartServices;

@Transactional
@Component
public class DepartServicesImpl implements DepartServices{
	
	@Autowired
	private DepartDao departDao;

	@Override
	public List<Department> findAll() {
		
		List<Department> list = null;
		
		list = departDao.findAll();
		
		return list;
	}

	@Override
	public List<Department> findByDepartname(String departname) {
		
		List<Department> list = null;
		
		departname = "%"+departname+"%";
		
		list = departDao.findByDepartname(departname);

		return list;
	}
	
	@Override
	public List<Department> insert(Department department) {
		List<Department> list = null;
		
		if(departDao.insert(department))
		{
			list = findAll();
		}
		return list;
	}
	
	@Override
	public List<Department> update(Department department) {
		List<Department> list = null;
		
		if(departDao.update(department))
		{
			list = findAll();
		}
		return list;
	}
	
	@Override
	public List<Department> delete(int departID) {
		List<Department> list = null;
		
		if(departDao.delete(departID))
		{
			list = findAll();
		}
		return list;
	}

}
