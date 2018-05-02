package com.pos.control;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import org.springframework.context.ApplicationContext;
import com.pos.mode.SaleOrder;
import com.pos.server.SaleServices;
import com.pos.server.impl.SaleServicesImpl;

public class SaleControl {
	public Socket socket;
	public String msg;
	public ApplicationContext ct;

	public SaleControl(Socket socket,String msg,ApplicationContext ct) {
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
		SaleOrder saleOrder = new SaleOrder();
		saleOrder.setSaleorderID(Integer.parseInt(args[2]));
		saleOrder.setCustomerID(Integer.parseInt(args[3]));
		saleOrder.setWarehouseID(Integer.parseInt(args[4]));
		saleOrder.setCargoID(Integer.parseInt(args[5]));
		saleOrder.setAmount(Integer.parseInt(args[6]));
		saleOrder.setDate(args[7]);
		saleOrder.setStatus(args[8]);
		SaleServices  saleServices =(SaleServicesImpl)ct.getBean("saleServicesImpl");
		List<SaleOrder> list = saleServices.update(saleOrder);
		
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
		SaleOrder saleOrder = new SaleOrder();
		saleOrder.setSaleorderID(Integer.parseInt(args[2]));
		saleOrder.setCustomerID(Integer.parseInt(args[3]));
		saleOrder.setWarehouseID(Integer.parseInt(args[4]));
		saleOrder.setCargoID(Integer.parseInt(args[5]));
		saleOrder.setAmount(Integer.parseInt(args[6]));
		saleOrder.setDate(args[7]);
		saleOrder.setStatus(args[8]);
		SaleServices  saleServices =(SaleServicesImpl)ct.getBean("saleServicesImpl");
		List<SaleOrder> list = saleServices.insert(saleOrder);
		
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
		SaleOrder saleOrder = new SaleOrder();
		saleOrder.setSaleorderID(Integer.parseInt(args[2]));
		saleOrder.setCustomerID(Integer.parseInt(args[3]));
		saleOrder.setWarehouseID(Integer.parseInt(args[4]));
		saleOrder.setCargoID(Integer.parseInt(args[5]));
		saleOrder.setAmount(Integer.parseInt(args[6]));
		saleOrder.setDate(args[7]);
		saleOrder.setStatus(args[8]);
		SaleServices  saleServices =(SaleServicesImpl)ct.getBean("saleServicesImpl");
		List<SaleOrder> list = saleServices.delete(saleOrder);
		
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
		SaleServices  saleServices =(SaleServicesImpl)ct.getBean("saleServicesImpl");
		
		List<SaleOrder> list = saleServices.findByCustomerID(Integer.parseInt(args[2]));
		
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
		SaleServices  saleServices =(SaleServicesImpl)ct.getBean("saleServicesImpl");
		
		List<SaleOrder> list = saleServices.findAll();
		
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
