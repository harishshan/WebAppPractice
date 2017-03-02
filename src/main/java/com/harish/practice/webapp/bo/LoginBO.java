package com.harish.practice.webapp.bo;

import java.util.List;

import com.harish.practice.webapp.dao.ILoginDAO;
import com.harish.practice.webapp.entity.Login;

public class LoginBO implements ILoginBO {

	ILoginDAO loginDAO;
	
	public void save(Login login) {
		loginDAO.save(login);		
	}

	public List<Login> getLoginList() {
		return loginDAO.getLoginList();
	}

	public void update(Login login) {
		loginDAO.update(login);		
	}

	public void delete(Login login) {
		loginDAO.delete(login);		
	}

	public Login findByUsername(String username) {
		return loginDAO.findByUsername(username);
	}

	public ILoginDAO getLoginDAO() {
		return loginDAO;
	}

	public void setLoginDAO(ILoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}
}
