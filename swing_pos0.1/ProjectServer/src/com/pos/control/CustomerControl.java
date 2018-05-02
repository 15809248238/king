package com.pos.control;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import org.springframework.context.ApplicationContext;
import com.pos.mode.Customer;
import com.pos.server.CustomerServices;
import com.pos.server.impl.CustomerServicesImpl;

public class CustomerControl {

	public String msg;
	public Socket socket;
	public ApplicationContext ct;
	
	public CustomerControl(Socket socket,String msg,ApplicationContext ct) {
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
		Customer customer = new Customer();
		customer.setCustomerID(Integer.parseInt(args[2]));
		customer.setName(args[3]);
		customer.setSex(args[4]);
		customer.setAddress(args[5]);
		customer.setPhone(args[6]);
		customer.setEmail(args[7]);
		customer.setCreator(args[8]);
		customer.setCreattime(args[9]);
		customer.setModifier(args[10]);
		customer.setModifytime(args[11]);
		CustomerServices  customerServices =(CustomerServicesImpl)ct.getBean("customerServicesImpl");
		
		List<Customer> list = customerServices.update(customer);
		
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
		Customer customer = new Customer();
		customer.setCustomerID(Integer.parseInt(args[2]));
		customer.setName(args[3]);
		customer.setSex(args[4]);
		customer.setAddress(args[5]);
		customer.setPhone(args[6]);
		customer.setEmail(args[7]);
		customer.setCreator(args[8]);
		customer.setCreattime(args[9]);
		customer.setModifier(args[10]);
		customer.setModifytime(args[11]);
		CustomerServices  customerServices =(CustomerServicesImpl)ct.getBean("customerServicesImpl");
		
		List<Customer> list = customerServices.insert(customer);
		
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
		CustomerServices  customerServices =(CustomerServicesImpl)ct.getBean("customerServicesImpl");
		
		List<Customer> list = customerServices.delete(Integer.parseInt(args[2]));
		
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
		CustomerServices  customerServices =(CustomerServicesImpl)ct.getBean("customerServicesImpl");
		
		List<Customer> list = customerServices.findByCustomername(args[2]);
		
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
		
		CustomerServices  customerServices =(CustomerServicesImpl)ct.getBean("customerServicesImpl");
		
		List<Customer> list = customerServices.findAll();
		
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
