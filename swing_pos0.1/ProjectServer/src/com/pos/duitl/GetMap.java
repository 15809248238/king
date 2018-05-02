package com.pos.duitl;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


public class GetMap {

	public static Map<Socket,String> map;
	
	public static synchronized Map<Socket,String> getSingleMap() {
		
		if(map==null)
		{
			map = new HashMap<Socket,String>();
		}
		return map;
	}
	
}
