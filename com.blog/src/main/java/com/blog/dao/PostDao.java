package com.blog.dao;

import java.util.List;
import java.util.Map;

import com.blog.entity.Post;

public interface PostDao {
	void insert(Post post);
	List<Post> getpostlist(Map map);

}
