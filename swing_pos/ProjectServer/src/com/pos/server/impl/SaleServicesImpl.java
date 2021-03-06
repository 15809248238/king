package com.pos.server.impl;

import java.util.List;

import com.pos.dao.InventoryDao;
import com.pos.dao.SaleDao;
import com.pos.mode.Inventory;
import com.pos.mode.SaleOrder;
import com.pos.server.SaleServices;

public class SaleServicesImpl implements SaleServices{
	
	
	private SaleDao saleDao;
	
	
	public SaleDao getSaleDao() {
		return saleDao;
	}

	public void setSaleDao(SaleDao saleDao) {
		this.saleDao = saleDao;
	}

	private InventoryDao inventoryDao;
	
	public InventoryDao getInventoryDao() {
		return inventoryDao;
	}

	public void setInventoryDao(InventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
	}

	@Override
	public List<SaleOrder> findAll() {
		List<SaleOrder> list = null;
		
		list = saleDao.findAll();
		
		return list;
	}

	@Override
	public List<SaleOrder> findByCustomerID(int customerID) {
		List<SaleOrder> list = null;
		
		list = saleDao.findByCustomerID(customerID);
		
		return list;
	}

	@Override
	public List<SaleOrder> insert(SaleOrder saleOrder) {
		List<SaleOrder> list = null;
		if(saleDao.insert(saleOrder))
		{
			list = findAll();
		}
		
		return list;
	}

	@Override
	public List<SaleOrder> update(SaleOrder saleOrder) {
		List<SaleOrder> list = null;
		
		if("uncommitted".equals(saleOrder.getStatus()))
		{ 
			saleDao.update(saleOrder);
			list = findAll();
		}
		else {
			List<Inventory> ilist = inventoryDao.findByAmount(saleOrder.getWarehouseID(),saleOrder.getCargoID(),saleOrder.getAmount());
			if(ilist.size()>0)
			{
				inventoryDao.updateAmountBywarehouseID(saleOrder.getWarehouseID(),saleOrder.getCargoID(),0-saleOrder.getAmount());
				saleDao.update(saleOrder);
			}
			list=findAll();
		}
		return list;
	}

	@Override
	public List<SaleOrder> delete(SaleOrder saleOrder) {
		List<SaleOrder> list = null;
		if("uncommitted".equals(saleOrder.getStatus()))
		{
			saleDao.delete(saleOrder.getSaleorderID());
			list = findAll();
		}
		else {
			inventoryDao.updateAmountBywarehouseID(saleOrder.getWarehouseID(),saleOrder.getCargoID(),saleOrder.getAmount());
			saleDao.delete(saleOrder.getSaleorderID());
			list = findAll();
		}
		return list;
	}
	
}
