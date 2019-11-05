package com.destoroyah.repo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;
import javax.transaction.Transaction;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.destoroyah.model.Post;
import com.destoroyah.model.User;


@Transactional
@Repository
public class PostDao{
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
	
	public List<Post> findAll() {
		Session ses = sesFact.getCurrentSession();
		return ses.createQuery("from Post", Post.class).list();

	}
	
	

	public Post findById(int id) {
		Session ses = sesFact.getCurrentSession();
		Post p = ses.load(Post.class, id);
        return p;
	}

	

	public void insert(Post t) {
		sesFact.getCurrentSession().save(t);	
	}
	
	public void delete(int id) {
		sesFact.getCurrentSession().delete(id);
		
	}
	

	@Autowired
	public PostDao(SessionFactory sesFact) {
		super();
		this.sesFact = sesFact;
	}
	

}
