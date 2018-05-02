package com.pos.server;

import java.util.List;

import com.pos.mode.Warehouse;

public interface WareServices {

	public List<Warehouse> findAll();
	
	public List<Warehouse> findByWarehousename(String warehouseName);
	
	public List<Warehouse> insert(Warehouse warehouse);
	
	public List<Warehouse> update(Warehouse warehouse);
	
	public List<Warehouse> delete(int warehouseID);
	
}
