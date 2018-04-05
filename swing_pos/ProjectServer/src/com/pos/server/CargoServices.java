package com.pos.server;

import java.util.List;

import com.pos.mode.Cargo;

public interface CargoServices {

	public List<Cargo> findAll();
	
	public List<Cargo> findByCargoname(String cargoName);
	
	public List<Cargo> insert(Cargo cargo);
	
	public List<Cargo> update(Cargo cargo);
	
	public List<Cargo> delete(int cargoID);
	
}
