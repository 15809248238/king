package com.pos.control;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.pos.mode.Purchaseorder;
import com.pos.server.PurchaseServices;
import com.pos.server.impl.PurchaseServicesImpl;



public class PurchaseControl {

	public Socket socket;
	public String msg;
	public ApplicationContext ct;
	
	public PurchaseControl(Socket socket,String msg,ApplicationContext ct) {
	
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
			findByCustomerID(args);
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
		Purchaseorder purchaseorder = new Purchaseorder();
		purchaseorder.setPurchaseorderID(Integer.parseInt(args[2]));
		purchaseorder.setCustomerID(Integer.parseInt(args[3]));
		purchaseorder.setWarehouseID(Integer.parseInt(args[4]));
		purchaseorder.setCargoID(Integer.parseInt(args[5]));
		purchaseorder.setAmount(Integer.parseInt(args[6]));
		purchaseorder.setDate(args[7]);
		purchaseorder.setStatus(args[8]);
		PurchaseServices  purchaseServices =(PurchaseServicesImpl)ct.getBean("purchaseServicesImpl");
		
		List<Purchaseorder> list = purchaseServices.update(purchaseorder);
		
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
		Purchaseorder purchaseorder = new Purchaseorder();
		purchaseorder.setPurchaseorderID(Integer.parseInt(args[2]));
		purchaseorder.setCustomerID(Integer.parseInt(args[3]));
		purchaseorder.setWarehouseID(Integer.parseInt(args[4]));
		purchaseorder.setCargoID(Integer.parseInt(args[5]));
		purchaseorder.setAmount(Integer.parseInt(args[6]));
		purchaseorder.setDate(args[7]);
		purchaseorder.setStatus(args[8]);
		PurchaseServices  purchaseServices =(PurchaseServicesImpl)ct.getBean("purchaseServicesImpl");
		
		List<Purchaseorder> list = purchaseServices.insert(purchaseorder);
		
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
		Purchaseorder purchaseorder = new Purchaseorder();
		purchaseorder.setPurchaseorderID(Integer.parseInt(args[2]));
		purchaseorder.setCustomerID(Integer.parseInt(args[3]));
		purchaseorder.setWarehouseID(Integer.parseInt(args[4]));
		purchaseorder.setCargoID(Integer.parseInt(args[5]));
		purchaseorder.setAmount(Integer.parseInt(args[6]));
		purchaseorder.setDate(args[7]);
		purchaseorder.setStatus(args[8]);
		PurchaseServices  purchaseServices =(PurchaseServicesImpl)ct.getBean("purchaseServicesImpl");
		
		List<Purchaseorder> list = purchaseServices.delete(purchaseorder);
		
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

	private void findByCustomerID(String[] args) {
		PurchaseServices  purchaseServices =(PurchaseServicesImpl)ct.getBean("purchaseServicesImpl");
		
		List<Purchaseorder> list = purchaseServices.findByCustomerID(Integer.parseInt(args[2]));
		
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
		PurchaseServices  purchaseServices =(PurchaseServicesImpl)ct.getBean("purchaseServicesImpl");
		
		List<Purchaseorder> list = purchaseServices.findAll();
		
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
