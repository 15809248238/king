package com.pos.server.impl;

import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.pos.dao.InventoryDao;
import com.pos.dao.PurchaseDao;
import com.pos.duitl.GetMap;
import com.pos.mode.Inventory;
import com.pos.mode.Purchaseorder;
import com.pos.server.PurchaseServices;

public class PurchaseServicesImpl implements PurchaseServices{
	
	
	private InventoryDao inventoryDao;
	
	
	public InventoryDao getInventoryDao() {
		return inventoryDao;
	}

	public void setInventoryDao(InventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
	}

	private PurchaseDao purchaseDao;
	

	public PurchaseDao getPurchaseDao() {
		return purchaseDao;
	}

	public void setPurchaseDao(PurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;
	}

	@Override
	public List<Purchaseorder> findAll() {
		List<Purchaseorder> list = null;
		
		list = purchaseDao.findAll();
		
		return list;
	}

	@Override
	public List<Purchaseorder> findByCustomerID(int customerID) {
		List<Purchaseorder> list = null;
		
		list = purchaseDao.findByCustomerID(customerID);
		
		return list;
	}

	@Override
	public List<Purchaseorder> insert(Purchaseorder purchaseorder) {
		List<Purchaseorder> list = null;
		
		if(purchaseDao.insert(purchaseorder))
		{
			list = findAll();
		}
		
		return list;
	}

	@Override
	public List<Purchaseorder> update(Purchaseorder purchaseorder,Socket socket) {
		List<Purchaseorder> list = null;
		
		if("uncommitted".equals(purchaseorder.getStatus()))
		{ 
			purchaseDao.update(purchaseorder);
			list = findAll();
		}
		else {
			List<Inventory> iList = inventoryDao.findByWarehouseIDAndCargo(purchaseorder.getWarehouseID(),purchaseorder.getCargoID());
			if(iList.size()>0) {
				inventoryDao.updateAmountBywarehouseID(purchaseorder.getWarehouseID(),purchaseorder.getCargoID(),purchaseorder.getAmount());
			}
			else {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Inventory inventory = new Inventory();
				inventory.setWarehouseID(purchaseorder.getWarehouseID());
				inventory.setCargoID(purchaseorder.getCargoID());
				inventory.setAmount(purchaseorder.getAmount());
				Map<Socket,String> map = GetMap.getSingleMap();
				inventory.setModifier(map.get(socket));
				inventory.setModifytime(df.format(new Date()));
				inventoryDao.insert(inventory);
			}
			purchaseDao.update(purchaseorder);
			list=findAll();
			
		}
		return list;
	}

	@Override
	public List<Purchaseorder> delete(Purchaseorder purchaseorder) {
		List<Purchaseorder> list = null;
		
		if("uncommitted".equals(purchaseorder.getStatus()))
		{ 
			purchaseDao.delete(purchaseorder.getPurchaseorderID());
			list = findAll();
		}
		else {
			inventoryDao.updateAmountBywarehouseID(purchaseorder.getWarehouseID(),purchaseorder.getCargoID(),0-purchaseorder.getAmount());
			purchaseDao.delete(purchaseorder.getPurchaseorderID());
			list=findAll();
		}
		return list;
	}

}
