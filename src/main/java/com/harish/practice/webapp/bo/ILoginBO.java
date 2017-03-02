package com.harish.practice.webapp.bo;

import java.util.List;

import com.harish.practice.webapp.entity.Login;

public interface ILoginBO {
	public void save(Login login);
	
	public List<Login> getLoginList();

	public void update(Login login);
	
	public void delete(Login login);
	
	public Login findByUsername(String username);
}
