package com.pos.server.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.pos.dao.InventoryDao;
import com.pos.mode.Inventory;
import com.pos.server.InventoryServices;

@Transactional
@Component
public class InventoryServicesImpl implements InventoryServices{

	@Autowired
	private InventoryDao inventoryDao;
	
	
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
