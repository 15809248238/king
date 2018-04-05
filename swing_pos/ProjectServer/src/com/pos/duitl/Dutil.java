package com.pos.duitl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Dutil {

	public static ApplicationContext ct;
	
	public static synchronized ApplicationContext getApplicationContext() {
		
		if(ct == null)
		{
			ct = new ClassPathXmlApplicationContext("spring.xml");
		}
		
		return ct;
	}
}
