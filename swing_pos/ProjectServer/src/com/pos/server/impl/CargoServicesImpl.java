package com.pos.server.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.pos.dao.CargoDao;
import com.pos.mode.Cargo;
import com.pos.server.CargoServices;

@Transactional
@Component
public class CargoServicesImpl implements CargoServices{

	@Autowired
	private CargoDao cargoDao;
	
	@Override
	public List<Cargo> findAll() {
		List<Cargo> list = null;
		
		list = cargoDao.findAll();
		
		return list;
	}

	@Override
	public List<Cargo> findByCargoname(String cargoName) {
		List<Cargo> list = null;
		
		cargoName = "%"+cargoName+"%";
		
		list = cargoDao.findByCargorname(cargoName);
		
		return list;
	}

	@Override
	public List<Cargo> insert(Cargo cargo) {
		List<Cargo> list = null;
		
		if(cargoDao.insert(cargo))
		{
			list = findAll();
		}
		return list;
	}

	@Override
	public List<Cargo> update(Cargo cargo) {
		List<Cargo> list = null;
		
		if(cargoDao.update(cargo))
		{
			list = findAll();
		}
		return list;
	}

	@Override
	public List<Cargo> delete(int cargoID) {
		List<Cargo> list = null;
		
		if(cargoDao.delete(cargoID))
		{
			list = findAll();
		}
		return list;
	}

}
