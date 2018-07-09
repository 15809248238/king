package com.blog.entity;

import java.util.Date;

public class Post {

	private  int id;
	private String userid;
	private Date posttime;
	private String postcontent;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Date getPosttime() {
		return posttime;
	}
	public void setPosttime(Date posttime) {
		this.posttime = posttime;
	}
	public String getPostcontent() {
		return postcontent;
	}
	public void setPostcontent(String postcontent) {
		this.postcontent = postcontent;
	}
	@Override
	public String toString() {
		return "Post [id=" + id + ", userid=" + userid + ", posttime=" + posttime + ", postcontent=" + postcontent
				+ "]";
	}
	
}
