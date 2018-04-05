package com.pos.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pos.mode.Inventory;

public interface InventoryDao {
	
	public List<Inventory> findAll();
	
	public List<Inventory> findByWarehouseID(@Param("warehouseID")int warehouseID);
	
	public List<Inventory> findByAmount(@Param("warehouseID")int warehouseID,@Param("cargoID")int cargoID,@Param("amount")int amount);
	
	public boolean insert(Inventory inventory);
	
	public boolean update(Inventory inventory);
	
	public boolean updateAmountBywarehouseID(@Param("warehouseID")int warehouseID,@Param("cargoID")int cargoID,@Param("amount")int amount);
			
	public boolean delete(@Param("inventoryID")int inventoryID);
}
