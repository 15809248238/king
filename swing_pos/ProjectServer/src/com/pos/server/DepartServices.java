package com.pos.server;

import java.util.List;
import com.pos.mode.Department;

public interface DepartServices {

	public List<Department> findAll();
	
	public List<Department> findByDepartname(String departname);
	
	public List<Department> insert(Department department);
	
	public List<Department> update(Department department);
	
	public List<Department> delete(int departID);
	
}
