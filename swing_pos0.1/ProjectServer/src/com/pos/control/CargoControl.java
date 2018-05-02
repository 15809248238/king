package com.pos.control;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import org.springframework.context.ApplicationContext;
import com.pos.mode.Cargo;
import com.pos.server.CargoServices;
import com.pos.server.impl.CargoServicesImpl;

public class CargoControl {
	
	public String msg;
	public Socket socket;
	public ApplicationContext ct;
	
	public CargoControl(Socket socket,String msg,ApplicationContext ct) {
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
		Cargo cargo = new Cargo();
		cargo.setCargoID(Integer.parseInt(args[2]));
		cargo.setCargoname(args[3]);
		cargo.setCargotypename(args[4]);
		cargo.setInprice(Integer.parseInt(args[5]));
		cargo.setOutprice(Integer.parseInt(args[6]));
		cargo.setCreator(args[7]);
		cargo.setCreattime(args[8]);
		cargo.setModifier(args[9]);
		cargo.setModifytime(args[10]);
		
		CargoServices  cargoServices =(CargoServicesImpl)ct.getBean("cargoServicesImpl");
		
		List<Cargo> list = cargoServices.update(cargo);
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
		
		Cargo cargo = new Cargo();
		cargo.setCargoID(Integer.parseInt(args[2]));
		cargo.setCargoname(args[3]);
		cargo.setCargotypename(args[4]);
		cargo.setInprice(Integer.parseInt(args[5]));
		cargo.setOutprice(Integer.parseInt(args[6]));
		cargo.setCreator(args[7]);
		cargo.setCreattime(args[8]);
		cargo.setModifier(args[9]);
		cargo.setModifytime(args[10]);
		
		CargoServices  cargoServices =(CargoServicesImpl)ct.getBean("cargoServicesImpl");
		
		List<Cargo> list = cargoServices.insert(cargo);
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
		CargoServices  cargoServices =(CargoServicesImpl)ct.getBean("cargoServicesImpl");
		
		List<Cargo> list = cargoServices.delete(Integer.parseInt(args[2]));
		
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
		CargoServices  cargoServices =(CargoServicesImpl)ct.getBean("cargoServicesImpl");
		
		List<Cargo> list = cargoServices.findByCargoname(args[2]);
		
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
		CargoServices  cargoServices =(CargoServicesImpl)ct.getBean("cargoServicesImpl");
		
		List<Cargo> list = cargoServices.findAll();
		
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
