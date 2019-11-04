package com.destoroyah.controller;

import java.sql.Timestamp;
import java.util.List;   

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.destoroyah.model.Photo;
import com.destoroyah.model.Post;
import com.destoroyah.model.User;
import com.destoroyah.service.PostService;
import com.destoroyah.service.UserService;



@Controller
@RequestMapping(value="/users")
@CrossOrigin(origins="*")
public class UserController {
	
	private UserService user;
	private PostService post;
	//from here down works
	
	@RequestMapping(value="/getUsername.app", method=RequestMethod.POST,
			produces="application/json", params= {"username"})
	public @ResponseBody User bringBackUsername(String username) {
		System.out.println(username);
		return user.getByUsername(username);
	}


	@RequestMapping(value="/putIn.app", method = RequestMethod.POST)
	public @ResponseBody User insert(@RequestBody User b) {
		System.out.println(b);
		user.insertUser(b);
		return b;
	}
	
	@RequestMapping(value="/login.app", method = RequestMethod.POST)
	public @ResponseBody User loginUser(@RequestBody User u) {
		System.out.println(u.getEmail() + u.getUserpass());
		String username = user.login(u);
		if(username!=null) {
		User unew =user.getByUsername(username);
		System.out.println(unew);
			return unew;
		}else 
		return null;
	}
	//update with post
	@RequestMapping(value="/postIn.app", method = RequestMethod.POST)
	public @ResponseBody Post insert(@RequestBody Post p) {
//		Photo ph = new Photo();
//		p.setPhoto(ph);
		System.out.println(p);
		User u = p.getUser();
		int id = u.getUser_id();
		System.out.println(id);
		System.out.println(u);
		u.getPost().add(p);
		System.out.println("succes");
//		post.insertBySql(p);
//		user.hibernateUpdate(u);
		post.insertPost(p);
		return p;
	}
	//not really working past this
	@RequestMapping(value="/update.app", method = RequestMethod.POST)
	public @ResponseBody void updateUser(@RequestBody User u) {
			user.update(u);
	}
	


	
	
	public PostService getPost() {
		return post;
	}

	@Autowired
	public void setPost(PostService post) {
		this.post = post;
	}


	public UserService getUser() {
		return user;
	}


	@Autowired
	public void setUser(UserService user) {
		this.user=user;
	}
	
	
//	public UserController() {
//		System.out.println("controller");
//	}
	
	

}
