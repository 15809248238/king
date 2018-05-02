package com.pos.mode;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Cargo implements Serializable{

	private int cargoID;
	
	private String cargoname;
	
	private String cargotypename;
	
	private int inprice;
	
	private int outprice;
	
	private String creator;
	
	private String creattime;
	
	private String modifier;
	
	private String modifytime;
	
	public int getCargoID() {
		return cargoID;
	}

	public void setCargoID(int cargoID) {
		this.cargoID = cargoID;
	}

	public String getCargoname() {
		return cargoname;
	}

	public void setCargoname(String cargoname) {
		this.cargoname = cargoname;
	}

	public String getCargotypename() {
		return cargotypename;
	}

	public void setCargotypename(String cargotypename) {
		this.cargotypename = cargotypename;
	}

	public int getInprice() {
		return inprice;
	}

	public void setInprice(int inprice) {
		this.inprice = inprice;
	}

	public int getOutprice() {
		return outprice;
	}

	public void setOutprice(int outprice) {
		this.outprice = outprice;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreattime() {
		return creattime;
	}

	public void setCreattime(String creattime) {
		this.creattime = creattime;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getModifytime() {
		return modifytime;
	}

	public void setModifytime(String modifytime) {
		this.modifytime = modifytime;
	}

	public String toString()
	{
		return cargoID+"+"+cargoname+"+"+cargotypename+"+"+inprice+"+"+outprice+"+"+
				creator+"+"+creattime+"+"+modifier+"+"+modifytime;
	}
	
}
