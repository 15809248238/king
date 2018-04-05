package com.pos.control;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.pos.mode.Inventory;
import com.pos.server.InventoryServices;
import com.pos.server.impl.InventoryServicesImpl;

public class InventoryControl {

	public Socket socket;
	public String msg;
	public ApplicationContext ct;
	
	public InventoryControl(Socket socket,String msg,ApplicationContext ct) {
		this.socket = socket;
		this.msg = msg;
		this.ct = ct;
		analysis();
	}

	private void analysis() {
		String[] args = msg.split("\\+");
		if ("select".equals(args[1])&&args.length==2)
		{
			findAll();
		}
		else if	("select".equals(args[1])&&args.length!=2){
			findByWarehouseID(args);
		}
		else if ("delete".equals(args[1])){
			delete(args);
		}
		else if ("save".equals(args[1])) {
			save(args);
		}
		else if ("update".equals(args[1])) {
			update(args);
		}
	}

	private void update(String[] args) {
		
		Inventory inventory = new Inventory();
		inventory.setInventoryID(Integer.parseInt(args[2]));
		inventory.setWarehouseID(Integer.parseInt(args[3]));
		inventory.setCargoID(Integer.parseInt(args[4]));
		inventory.setAmount(Integer.parseInt(args[5]));
		inventory.setCreator(args[6]);
		inventory.setCreattime(args[7]);
		inventory.setModifier(args[8]);
		inventory.setModifytime(args[9]);
		
		InventoryServices inventoryServices = (InventoryServicesImpl)ct.getBean("inventoryServicesImpl");
		
		List<Inventory> list = inventoryServices.update(inventory);
		
		OutputStream outputStream = null;
		ObjectOutputStream objectOutputStream = null;
		try {
			
			outputStream = socket.getOutputStream();
			objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(list);
			objectOutputStream.flush();
			
		} catch (Exception e) {
			
		}
		
	}

	private void save(String[] args) {
		
		Inventory inventory = new Inventory();
		inventory.setInventoryID(Integer.parseInt(args[2]));
		inventory.setWarehouseID(Integer.parseInt(args[3]));
		inventory.setCargoID(Integer.parseInt(args[4]));
		inventory.setAmount(Integer.parseInt(args[5]));
		inventory.setCreator(args[6]);
		inventory.setCreattime(args[7]);
		inventory.setModifier(args[8]);
		inventory.setModifytime(args[9]);
		
		InventoryServices inventoryServices = (InventoryServicesImpl)ct.getBean("inventoryServicesImpl");
		
		List<Inventory> list = inventoryServices.insert(inventory);
		
		OutputStream outputStream = null;
		ObjectOutputStream objectOutputStream = null;
		try {
			
			outputStream = socket.getOutputStream();
			objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(list);
			objectOutputStream.flush();
			
		} catch (Exception e) {
			
		}
		
	}

	private void delete(String[] args) {
		
		InventoryServices inventoryServices = (InventoryServicesImpl)ct.getBean("inventoryServicesImpl");
		
		List<Inventory> list = inventoryServices.delete(Integer.parseInt(args[2]));
		
		OutputStream outputStream = null;
		ObjectOutputStream objectOutputStream = null;
		try {
			
			outputStream = socket.getOutputStream();
			objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(list);
			objectOutputStream.flush();
			
		} catch (Exception e) {
			
		}
		
	}

	private void findByWarehouseID(String[] args) {
		
		InventoryServices inventoryServices = (InventoryServicesImpl)ct.getBean("inventoryServicesImpl");
		
		List<Inventory> list = inventoryServices.findByWarehouseID(Integer.parseInt(args[2]));
		
		OutputStream outputStream = null;
		ObjectOutputStream objectOutputStream = null;
		try {
			
			outputStream = socket.getOutputStream();
			objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(list);
			objectOutputStream.flush();
			
		} catch (Exception e) {
			
		}
		
	}

	private void findAll() {
		
		InventoryServices inventoryServices = (InventoryServicesImpl)ct.getBean("inventoryServicesImpl");
		
		List<Inventory> list = inventoryServices.findAll();
		
		OutputStream outputStream = null;
		ObjectOutputStream objectOutputStream = null;
		try {
			
			outputStream = socket.getOutputStream();
			objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(list);
			objectOutputStream.flush();
			
		} catch (Exception e) {
			
		}
		
	}
	
}
