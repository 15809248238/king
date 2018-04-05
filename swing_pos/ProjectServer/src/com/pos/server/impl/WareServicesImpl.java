package com.pos.server.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.pos.dao.WareDao;
import com.pos.mode.Warehouse;
import com.pos.server.WareServices;

@Transactional
@Component
public class WareServicesImpl implements WareServices{

	@Autowired
	private WareDao wareDao;
	
	@Override
	public List<Warehouse> findAll() {
		List<Warehouse> list = null;
		
		list = wareDao.findAll();
		
		return list;
	}

	@Override
	public List<Warehouse> findByWarehousename(String warehouseName) {
		List<Warehouse> list = null;
		
		warehouseName = "%"+ warehouseName +"%";
		
		list = wareDao.findByWarehousename(warehouseName);
		
		return list;
	}

	@Override
	public List<Warehouse> insert(Warehouse warehouse) {
		List<Warehouse> list = null;
		
		if(wareDao.insert(warehouse))
		{
			list = findAll();
		}
		
		return list;
	}

	@Override
	public List<Warehouse> update(Warehouse warehouse) {
		List<Warehouse> list = null;
		
		if(wareDao.update(warehouse))
		{
			list = findAll();
		}
		
		return list;
	}

	@Override
	public List<Warehouse> delete(int warehouseID) {
		List<Warehouse> list = null;
		
		if(wareDao.delete(warehouseID))
		{
			list = findAll();
		}
		
		return list;
	}

}
