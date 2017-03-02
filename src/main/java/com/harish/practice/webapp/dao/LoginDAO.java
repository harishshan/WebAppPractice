package com.harish.practice.webapp.dao;

import java.util.List;

import com.harish.practice.webapp.entity.Login;

public class LoginDAO implements ILoginDAO{

	private SessionFactory sessionFactory;
	
	public void save(Login login) {
		Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(login);
        tx.commit();
        session.close();
	}

	public List<Login> getLoginList() {
		Session session = this.sessionFactory.openSession();
        List<Login> loginList = session.createQuery("from Login").list();
        session.close();
        return loginList;
	}


	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void update(Login login) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Login login) {
		// TODO Auto-generated method stub
		
	}

	public Login findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
