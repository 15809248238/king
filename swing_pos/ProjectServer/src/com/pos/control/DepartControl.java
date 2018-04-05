package com.pos.control;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import org.springframework.context.ApplicationContext;
import com.pos.mode.Department;
import com.pos.server.DepartServices;
import com.pos.server.impl.DepartServicesImpl;

public class DepartControl {
	
	public String msg;
	public Socket socket;
	public ApplicationContext ct;
	
	public DepartControl(Socket socket,String msg,ApplicationContext ct)
	{
		this.msg = msg;
		this.socket = socket;
		this.ct = ct;
		analysis();
	}
	
	public void analysis()
	{
		
		String[] args = msg.split("\\+");
		if ("select".equals(args[1])&&args.length==2)
		{
			findAll();
		}
		else if	("select".equals(args[1])&&args.length!=2){
			findByDepartname(args);
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
		Department department = new Department();
		department.setDepartmentID(Integer.parseInt(args[2]));
		department.setParentdepartname(args[3]);
		department.setName(args[4]);
		department.setManager(args[5]);
		department.setDepartmentphone(args[6]);
		department.setEmployeecount(Integer.parseInt(args[7]));
		department.setCreator(args[8]);
		department.setCreattime(args[9]);
		department.setModifier(args[10]);
		department.setModifytime(args[11]);
		DepartServices departServices =(DepartServicesImpl)ct.getBean("departServicesImpl");
		
		List<Department> list = departServices.update(department);
		
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
		Department department = new Department();
		department.setDepartmentID(Integer.parseInt(args[2]));
		department.setParentdepartname(args[3]);
		department.setName(args[4]);
		department.setManager(args[5]);
		department.setDepartmentphone(args[6]);
		department.setEmployeecount(Integer.parseInt(args[7]));
		department.setCreator(args[8]);
		department.setCreattime(args[9]);
		department.setModifier(args[10]);
		department.setModifytime(args[11]);
		DepartServices departServices =(DepartServicesImpl)ct.getBean("departServicesImpl");
		
		List<Department> list = departServices.insert(department);
		
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
		
		DepartServices departServices =(DepartServicesImpl)ct.getBean("departServicesImpl");
		
		List<Department> list = departServices.delete(Integer.parseInt(args[2]));
		
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

	private void findByDepartname(String[] args) {
		
		DepartServices departServices =(DepartServicesImpl)ct.getBean("departServicesImpl");
		
		List<Department> list = departServices.findByDepartname(args[2]);
		
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
		
		DepartServices departServices =(DepartServicesImpl)ct.getBean("departServicesImpl");
	
		List<Department> list = departServices.findAll();
		
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
