package com.blog.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blog.dao.PostDao;
import com.blog.entity.Post;

@Controller
@RequestMapping("/post")
public class PostController {

	@Resource
	private PostDao postdao;
	
	@RequestMapping(value="/addpost",/* method=RequestMethod.POST,*/ produces="application/json;charset=UTF-8")
	public void addpost(HttpServletRequest request,HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin","*");
		String userid=request.getParameter("username");
		String posttime=request.getParameter("posttime");
		String postcontent=request.getParameter("postcontent");
		Post post=new Post();
		post.setPostcontent(postcontent);
		post.setUserid(userid);
		post.setPosttime(new Date());
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("msgcode",200);
		try{
			postdao.insert(post);
		}catch (Exception e) {
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
	@RequestMapping(value="/postlist", /*method=RequestMethod.POST,*/ produces="application/json;charset=UTF-8")
	public void postlist(HttpServletRequest request,HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin","*");
		String pagenum=request.getParameter("pagenum");
		String pagesize=request.getParameter("pagesize");
		
		List<Post> listpost=null;
		Map map=new HashMap<String, Integer>();
		
		if(pagenum!=null&&pagesize!=null&&!pagenum.equals("")&&!pagesize.equals("")) {
			
			map.put("pagenum", Integer.valueOf(pagenum));
			map.put("pagesize",Integer.valueOf(pagesize));
		}else {
			map.put("pagenum", 1);
			map.put("pagesize",10);
		}
		listpost=postdao.getpostlist(map);
		JSONArray jsonarray=(JSONArray) JSONArray.toJSON(listpost);
		
		JSONObject jsonObject=new JSONObject();
		Writer writer=null;
		if(listpost!=null){
			jsonObject.put("msgcode",200);
			jsonObject.put("postlist",jsonarray);
		}else{
			jsonObject.put("msgcode",500);
		}
		
		try {
			response.setCharacterEncoding("UTF-8");
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
