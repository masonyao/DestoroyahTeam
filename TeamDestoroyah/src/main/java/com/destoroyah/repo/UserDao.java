package com.destoroyah.repo;


import java.util.List; 

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.destoroyah.mapper.UserMapper;
import com.destoroyah.model.User;


@Transactional
@Repository
public class UserDao{
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Autowired
	private DataSource dataSource;
	
	
	private SessionFactory sesFact;
	
	
	HibernateTemplate template;  
	

	public void setTemplate(HibernateTemplate template) {  
	    this.template = template;  
	}  
	
	public List<User> findAll() {
		Session ses = sesFact.getCurrentSession();
		return ses.createQuery("from User", User.class).list();

	}

	public User findById(int id) {
		Session ses = sesFact.getCurrentSession();
		User u = ses.get(User.class, id);
        return u;

	}
	
	 public User findBy(String username) {
//	        return sesFact.getCurrentSession().get(User.class, username);
			JdbcTemplate template = new JdbcTemplate(dataSource);

		 String sql = "select * from users where username=?";
		 return template.queryForObject(sql, new Object[] {username}, new BeanPropertyRowMapper<User>(User.class));
	    }
	
	 
	public void insert(User u) {
		sesFact.getCurrentSession().save(u);
		
	}

	public void delete(int id) {
		
		sesFact.getCurrentSession().delete(id);
	}
	
	public void update(User u) {
		
		
		sesFact.getCurrentSession().update(u);
		
	}
		
	public String checkLogin(User u) {
		String usern;
		try {
		JdbcTemplate template = new JdbcTemplate(dataSource);
		String sql = "select hash_dat(?,?)"; 
		 usern = (String) template.queryForObject(sql,new Object[] {u.getEmail(),u.getUserpass()},String.class);
		System.out.println(usern);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return usern;
		
		}
	
	public void makePost(int id) {
		
		User u = sesFact.getCurrentSession().get(User.class, id);
		
		sesFact.getCurrentSession().update(u);
	}
	
	public void updateHibernate(User u) {
		template.update(u);
		
	}
        
		@Autowired
		public UserDao(SessionFactory sesFact) {
			super();
			this.sesFact = sesFact;
    }
//	


}
