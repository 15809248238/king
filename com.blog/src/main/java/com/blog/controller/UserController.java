package com.blog.controller;

import java.io.IOException;
import java.io.Writer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.blog.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userdao;
	
	@RequestMapping(value="/userlogin",method=RequestMethod.POST)
	public void userselect (HttpServletRequest request,HttpServletResponse response){
		
		response.addHeader("Access-Control-Allow-Origin","*");
		String username = request.getParameter("username");
		System.out.println(username);
		String password = request.getParameter("password");
		System.out.println(password);
		JSONObject jsonObject=new JSONObject();
		
		if(username!=null&&password!=null&&!username.equals("")&&!password.equals(""))
		{
			int flag = userdao.UserLogin(username, password);
			if(flag == 3)
			{
				jsonObject.put("msgcode",200);
				jsonObject.put("success",3);//登录成功
			}
			else if (flag == 0) {
				jsonObject.put("msgcode",500);
				jsonObject.put("userIsNo", 0);//用户不存在
			}
			else if (flag == 1) {
				jsonObject.put("msgcode",500);
				jsonObject.put("passwordError",1);//密码错误
			}
			else {
				jsonObject.put("msgcode",500);
				jsonObject.put("error", 2);//登录失败
			}
		}
		
		else {
			jsonObject.put("msgcode",500);
			jsonObject.put("error", 2);//登录失败
		}
		
		Writer writer=null;
		try {
			writer = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			writer.write(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	@RequestMapping(value="/userregister",method=RequestMethod.POST)
	public void userregiste(HttpServletRequest request,HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin","*");
		String userid = "1";
		
		String username = request.getParameter("username");
		
		String password = request.getParameter("password");
		JSONObject jsonObject=new JSONObject();
		
		if(userid!=null&&username!=null&&password!=null&&!userid.equals("")&&!username.equals("")&&!password.equals(""))
		{
			int flag = userdao.InserUser("1",username, password);
			
			if(flag == 2)
			{
				jsonObject.put("msgcode",200);
				jsonObject.put("success",2);
			}
			else if (flag == 0) {
				jsonObject.put("msgcode",500);
				jsonObject.put("message","用户已存在！");
			}
			else {
				jsonObject.put("msgcode",500);
				jsonObject.put("error", 1);
			}

		}
		else {
			jsonObject.put("msgcode",500);
			jsonObject.put("error", 1);
		}
		Writer writer=null;
		try {
			writer = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			writer.write(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
