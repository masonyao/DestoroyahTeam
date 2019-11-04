package com.destoroyah.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.destoroyah.model.Photo;
import com.destoroyah.model.Post;
import com.destoroyah.model.User;
import com.destoroyah.service.PostService;
import com.destoroyah.service.UserService;

@Controller
@RequestMapping(value="/post")
@CrossOrigin(origins="*")
public class PostController {
	
	private PostService poster;
	private UserService user;
	
	@RequestMapping(value="/postIn.app", method = RequestMethod.POST)
	public @ResponseBody Post insert(@RequestBody Post p) {
		Photo ph = new Photo();
//		p.setPhoto(ph);
		System.out.println(p);
		User u = p.getUser();
		int id = u.getUser_id();
		System.out.println(id);
		System.out.println(u);
		u.getPost().add(p);
		System.out.println("succes");
		user.postMan(id);
		return p;
	}

}
