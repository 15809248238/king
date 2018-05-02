package com.pos.server.impl;

import java.util.List;
import com.pos.dao.DepartDao;
import com.pos.mode.Department;
import com.pos.server.DepartServices;


public class DepartServicesImpl implements DepartServices{
	
	
	private DepartDao departDao;

	public DepartDao getDepartDao() {
		return departDao;
	}

	public void setDepartDao(DepartDao departDao) {
		this.departDao = departDao;
	}

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
