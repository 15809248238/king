package com.blog.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blog.dao.Rrealation;
import com.blog.entity.Realationv1;


@Controller
@RequestMapping("/realation")
public class RelationController {

	@Resource
	private Rrealation myRrealation;
	
	@RequestMapping("/follow")
	public void InsertFriend(HttpServletRequest request,HttpServletResponse response){
		String userid=request.getParameter("userid");
		String friend=request.getParameter("friend");
		
		
		/*Realationv1 MyRealation=new Realationv1();
		MyRealation.setUserid(userid);
		MyRealation.setFriend(friend);*/
		Map map =new HashMap<String, String>();
		map.put("userid",userid);
		map.put("friend",friend);
	    Realationv1 Shiled=myRrealation.SelectShield(map);//锟斤拷询锟斤拷锟阶拷锟斤拷锟斤拷嵌锟斤拷锟斤拷锟斤拷锟斤拷约锟�
	    if(Shiled==null){
	    	try {
	    		myRrealation.InsertFriend(map);
	    		
			} catch (Exception e) {
				
			}
	    		
	    }
	    else{
	    if(Shiled.getShield()==0)//没锟斤拷锟斤拷锟斤拷锟皆硷拷
	     {   myRrealation.Follow(map);
	         	
	        
	     }}
	    JSONObject jsonObject=new JSONObject();
	    jsonObject.put("msgcode",200);
	    Writer writer=null;
		try {
			writer = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			writer.write(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	//取锟斤拷锟斤拷注
	@RequestMapping("/AbolishFollow")
	public void AbolishFollow(HttpServletRequest request,HttpServletResponse response){
		String userid=request.getParameter("userid");
		String friend=request.getParameter("friend");
		
		/*Realationv1 MyRealation=new Realationv1();
		MyRealation.setUserid(userid);
		MyRealation.setFriend(friend);*/
		Map map =new HashMap<String, String>();
		map.put("userid",userid);
		map.put("friend",friend);
	    if(myRrealation.AbolishFollow(map)>1){
	    	
	    }
	    
	    JSONObject jsonObject=new JSONObject();
	    jsonObject.put("msgcode",200);
	    Writer writer=null;
		try {
			writer = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			writer.write(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	@RequestMapping("/SelectAllFriend")//锟斤拷锟斤拷锟叫憋拷
	public void SelectAllFriend(HttpServletRequest request,HttpServletResponse response){
		String userid=request.getParameter("userid");		
		/*Realationv1 MyRealation=new Realationv1();
		MyRealation.setUserid(userid);*/
		Map map =new HashMap<String, String>();
		map.put("userid",userid);

		 List<Realationv1> list1 = null;
	    list1= myRrealation.SelectAllFriend(map);
	    JSONArray jsonarray=(JSONArray) JSONArray.toJSON(list1);
	    JSONObject jsonObject=new JSONObject();
	    if(list1!=null){
			jsonObject.put("msgcode",200);
			jsonObject.put("postlist",jsonarray);
		}else{
			jsonObject.put("msgcode",500);
		}
	    Writer writer=null;
		try {
			writer = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			writer.write(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	 
		
	}
}
