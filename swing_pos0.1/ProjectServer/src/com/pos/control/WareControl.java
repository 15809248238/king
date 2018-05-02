package com.pos.control;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import org.springframework.context.ApplicationContext;
import com.pos.mode.Warehouse;
import com.pos.server.WareServices;
import com.pos.server.impl.WareServicesImpl;

public class WareControl {

	public Socket socket;
	public String msg;
	public ApplicationContext ct;
	
	public WareControl(Socket socket,String msg,ApplicationContext ct) {
		this.msg = msg;
		this.socket = socket;
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
			findByCustomername(args);
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
		
		Warehouse warehouse = new Warehouse();
		warehouse.setWarehouseID(Integer.parseInt(args[2]));
		warehouse.setName(args[3]);
		warehouse.setManager(args[4]);
		warehouse.setAddress(args[5]);
		warehouse.setStatus(args[6]);
		warehouse.setCreator(args[7]);
		warehouse.setCreattime(args[8]);
		warehouse.setModifier(args[9]);
		warehouse.setModifytime(args[10]);
		
		WareServices  wareServices =(WareServicesImpl)ct.getBean("wareServicesImpl");
		
		List<Warehouse> list = wareServices.update(warehouse);
		
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
		Warehouse warehouse = new Warehouse();
		warehouse.setWarehouseID(Integer.parseInt(args[2]));
		warehouse.setName(args[3]);
		warehouse.setManager(args[4]);
		warehouse.setAddress(args[5]);
		warehouse.setStatus(args[6]);
		warehouse.setCreator(args[7]);
		warehouse.setCreattime(args[8]);
		warehouse.setModifier(args[9]);
		warehouse.setModifytime(args[10]);
		
		WareServices  wareServices =(WareServicesImpl)ct.getBean("wareServicesImpl");
		
		List<Warehouse> list = wareServices.insert(warehouse);
		
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
		
		WareServices  wareServices =(WareServicesImpl)ct.getBean("wareServicesImpl");
		
		List<Warehouse> list = wareServices.delete(Integer.parseInt(args[2]));
		
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

	private void findByCustomername(String[] args) {
		
		WareServices  wareServices =(WareServicesImpl)ct.getBean("wareServicesImpl");
		
		List<Warehouse> list = wareServices.findByWarehousename(args[2]);
		
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
		WareServices  wareServices =(WareServicesImpl)ct.getBean("wareServicesImpl");
		
		List<Warehouse> list = wareServices.findAll();
		
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
