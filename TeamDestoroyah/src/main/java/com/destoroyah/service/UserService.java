package com.destoroyah.service;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.destoroyah.model.User;
import com.destoroyah.repo.UserDao;

@Service
public class UserService {
	
	
	private UserDao ud;
	
	public User getByUsername(String username) {
        return ud.findBy(username);
    }
	
	public List<User> getAll(){
		return ud.findAll();
	}
	
	public User getById(int id) {
		return ud.findById(id);
	}
	
	public void update(User u) {
		ud.update(u);
	}
	
	public void insertUser(User u) {
		ud.insert(u);
	}
	
	public String login(User u) {
		return ud.checkLogin(u);
	}
	
	public void postMan(int id) {
		ud.makePost(id);
	}
	
	public void hibernateUpdate(User u) {
		ud.updateHibernate(u);
	}
	
	public UserDao getUd() {
		return ud;
	}
	
	@Autowired
	public void setUd(UserDao ud) {
		this.ud=ud;
	}
	
	
	

}
