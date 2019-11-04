package com.destoroyah.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.destoroyah.model.Photo;
import com.destoroyah.model.Post;
import com.destoroyah.model.User;

@Transactional
@Repository
public class PhotoDao{
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	private SessionFactory sesFact;

	public List<Photo> findAll() {
		Session ses = sesFact.getCurrentSession();
		return ses.createQuery("from Photo", Photo.class).list();

	}

	public Photo findById(int id) {
		Session ses = sesFact.getCurrentSession();
		Photo u = ses.load(Photo.class, id);
        return u;
	}

	public List<Photo> findAllByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	public void insert(Photo t) {
		sesFact.getCurrentSession().save(t);
		
	}

	public void delete(int id) {
		sesFact.getCurrentSession().delete(id);
	}

	@Autowired
	public PhotoDao(SessionFactory sesFact) {
		super();
		this.sesFact = sesFact;
	}

}
