package com.pos.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.pos.mode.Department;

public interface DepartDao {

	public List<Department> findAll();
	
	public List<Department> findByDepartname(@Param("departname")String departname);
	
	public boolean insert(Department department);
	
	public boolean update(Department department);
	
	public boolean updateEmployeeCount(@Param("departname")String departname,@Param("num")int num);
	
	public boolean delete(@Param("departID")int departID);
}
