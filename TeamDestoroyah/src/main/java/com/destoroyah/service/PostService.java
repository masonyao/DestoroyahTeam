package com.destoroyah.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.destoroyah.model.Post;
import com.destoroyah.model.User;
import com.destoroyah.repo.PostDao;

@Service
public class PostService {
	
	private PostDao pd;
	
	public void insertPost(Post p) {
		pd.insert(p);
	}
	
	public List<Post> getAll(){
		return pd.findAll();
	}
		
	public PostDao getPd() {
		return pd;
	}

	@Autowired
	public void setPd(PostDao pd) {
		this.pd = pd;
	}
	
	

}
