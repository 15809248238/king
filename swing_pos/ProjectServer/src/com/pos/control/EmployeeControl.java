package com.pos.control;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import org.springframework.context.ApplicationContext;
import com.pos.mode.Employee;
import com.pos.server.EmployeeServices;
import com.pos.server.impl.EmployeeServicesImpl;

public class EmployeeControl {

	public String msg;
	public Socket socket;
	public ApplicationContext ct;
	
	public EmployeeControl(Socket socket,String msg,ApplicationContext ct) {
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
			findByEmployeename(args);
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
		
		Employee employee = new Employee();
		employee.setEmployeeID(Integer.parseInt(args[2]));
		employee.setDepartmentName(args[3]);
		employee.setEmployeeName(args[4]);
		employee.setSex(args[5]);
		employee.setAddress(args[6]);
		employee.setPhone(args[7]);
		employee.setType(args[8]);
		employee.setEmail(args[9]);
		employee.setBirthday(args[10]);
		employee.setCreator(args[11]);
		employee.setCreattime(args[12]);
		employee.setModifier(args[13]);
		employee.setModifytime(args[14]);
		
		EmployeeServices employeeServices = (EmployeeServicesImpl)ct.getBean("employeeServicesImpl");
		List<Employee> list = employeeServices.update(employee);
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
		System.out.println(args.length);
		Employee employee = new Employee();
		employee.setEmployeeID(Integer.parseInt(args[2]));
		employee.setDepartmentName(args[3]);
		employee.setEmployeeName(args[4]);
		employee.setSex(args[5]);
		employee.setAddress(args[6]);
		employee.setPhone(args[7]);
		employee.setType(args[8]);
		employee.setEmail(args[9]);
		employee.setBirthday(args[10]);
		employee.setCreator(args[11]);
		employee.setCreattime(args[12]);
		employee.setModifier(args[13]);
		employee.setModifytime(args[14]);
		
		EmployeeServices employeeServices = (EmployeeServicesImpl)ct.getBean("employeeServicesImpl");
		List<Employee> list = employeeServices.insert(employee);
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
		
		EmployeeServices employeeServices = (EmployeeServicesImpl)ct.getBean("employeeServicesImpl");
		List<Employee> list = employeeServices.delete(Integer.parseInt(args[2]),args[3],args[4]);
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

	private void findByEmployeename(String[] args) {
		
		EmployeeServices employeeServices = (EmployeeServicesImpl)ct.getBean("employeeServicesImpl");
		List<Employee> list = employeeServices.findByEmployeename(args[2]);
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
		EmployeeServices employeeServices = (EmployeeServicesImpl)ct.getBean("employeeServicesImpl");
		List<Employee> list = employeeServices.findAll();
		
		System.out.println(list.size());
		
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
