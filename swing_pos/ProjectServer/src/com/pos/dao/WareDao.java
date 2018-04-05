package com.pos.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pos.mode.Warehouse;

public interface WareDao {
	
	public List<Warehouse> findAll();
	
	public List<Warehouse> findByWarehousename(@Param("warehouseName")String warehouseName);
	
	public boolean insert(Warehouse warehouse);
	
	public boolean update(Warehouse warehouse);
	
	public boolean delete(@Param("warehouseID")int warehouseID);
}
