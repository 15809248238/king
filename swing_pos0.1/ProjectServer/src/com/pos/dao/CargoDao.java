package com.pos.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pos.mode.Cargo;

public interface CargoDao {

	public List<Cargo> findAll();
	
	public List<Cargo> findByCargorname(@Param("cargoName")String cargoName);
	
	public boolean insert(Cargo cargo);
	
	public boolean update(Cargo cargo);
	
	public boolean delete(@Param("cargoID")int cargoID);
}
