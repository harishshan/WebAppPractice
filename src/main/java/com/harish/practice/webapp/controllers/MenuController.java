package com.harish.practice.webapp.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.intuit.ctg.data2.entity.Login;

@Controller
public class MenuController {

	@RequestMapping(value="/menu", method = RequestMethod.GET)
	public @ResponseBody String getMenuList(HttpServletRequest request,HttpServletResponse response)
	{
		String message = "Hello World";
		try 
		{
		
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return message;
	}
}
