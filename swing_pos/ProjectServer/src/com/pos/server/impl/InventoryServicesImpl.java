package com.pos.server.impl;

import java.util.List;

import com.pos.dao.InventoryDao;
import com.pos.mode.Inventory;
import com.pos.server.InventoryServices;


public class InventoryServicesImpl implements InventoryServices{

	
	private InventoryDao inventoryDao;
	
	
	public InventoryDao getInventoryDao() {
		return inventoryDao;
	}

	public void setInventoryDao(InventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
	}

	@Override
	public List<Inventory> findAll() {
		
		List<Inventory> list = null;
		
		list = inventoryDao.findAll();
		
		return list;
	}

	@Override
	public List<Inventory> findByWarehouseID(int warehouseID) {
		
		List<Inventory> list = null;
		
		list = inventoryDao.findByWarehouseID(warehouseID);
		
		return list;
	}

	@Override
	public List<Inventory> insert(Inventory inventory) {
		
		List<Inventory> list = null;
	
		if(inventoryDao.insert(inventory))
		{
			list = findAll();
		}
		
		return list;
	}

	@Override
	public List<Inventory> update(Inventory inventory) {
		
		List<Inventory> list = null;
		
		if(inventoryDao.update(inventory))
		{
			list = findAll();
		}
		
		return list;
	}

	@Override
	public List<Inventory> delete(int inventoryID) {
		List<Inventory> list = null;
		
		if(inventoryDao.delete(inventoryID))
		{
			list = findAll();
		}
		
		return list;
	}

}
