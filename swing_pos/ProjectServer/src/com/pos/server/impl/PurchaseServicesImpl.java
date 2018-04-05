package com.pos.server.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pos.dao.InventoryDao;
import com.pos.dao.PurchaseDao;
import com.pos.mode.Purchaseorder;
import com.pos.server.PurchaseServices;

@Transactional
@Component
public class PurchaseServicesImpl implements PurchaseServices{
	
	@Autowired
	private InventoryDao inventoryDao;
	
	@Autowired
	private PurchaseDao purchaseDao;
	

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
	public List<Purchaseorder> update(Purchaseorder purchaseorder) {
		List<Purchaseorder> list = null;
		
		if("uncommitted".equals(purchaseorder.getStatus()))
		{ 
			purchaseDao.update(purchaseorder);
			list = findAll();
		}
		else {
			
			inventoryDao.updateAmountBywarehouseID(purchaseorder.getWarehouseID(),purchaseorder.getCargoID(),purchaseorder.getAmount());
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
			purchaseDao.update(purchaseorder);
			list = findAll();
		}
		else {
			inventoryDao.updateAmountBywarehouseID(purchaseorder.getWarehouseID(),purchaseorder.getCargoID(),0-purchaseorder.getAmount());
			purchaseDao.update(purchaseorder);
			list=findAll();
		}
		return list;
	}

}
