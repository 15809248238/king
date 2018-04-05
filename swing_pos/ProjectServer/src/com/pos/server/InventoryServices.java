package com.pos.server;

import java.util.List;
import com.pos.mode.Inventory;


public interface InventoryServices {
	
	public List<Inventory> findAll();
	
	public List<Inventory> findByWarehouseID(int warehouseID);
	
	public List<Inventory> insert(Inventory inventory);
	
	public List<Inventory> update(Inventory inventory);
	
	public List<Inventory> delete(int inventoryID);
}
