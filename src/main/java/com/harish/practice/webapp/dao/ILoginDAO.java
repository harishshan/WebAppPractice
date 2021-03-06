package com.harish.practice.webapp.dao;

import java.util.List;

import com.harish.practice.webapp.entity.Login;

public interface ILoginDAO {
	public void save(Login login);
	
	public List<Login> getLoginList();

	public void update(Login login);
	
	public void delete(Login login);
	
	public Login findByUsername(String username);
}
