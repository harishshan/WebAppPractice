package com.harish.practice.webapp.controllers;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.intuit.ctg.data2.bo.LoginBO;
import com.intuit.ctg.data2.entity.Login;
import com.intuit.ctg.data2.util.JsonUtil;


@Controller
public class LoginController {

	@Autowired
	private LoginBO loginBO;
	
	@Autowired
	private JsonUtil jsonUtil;
	
	private final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public @ResponseBody String changeLogLevel(HttpServletRequest request,HttpServletResponse response)
	{
		String message = "Hello World";
		try 
		{
			List<Login> loginList=loginBO.getLoginList();
			for(Login login:loginList){
				message = message+";"+jsonUtil.getJsonObject(login);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return message;
	}
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public @ResponseBody JsonObject uploadFileHandler(@RequestParam("uploadFile") MultipartFile file, @RequestParam("hasHeader") boolean hasHeader,@RequestParam("maxRowSize") int maxRowSize) {
		JsonObject jsonObject = new JsonObject();
		try{
			logger.info("Upload Files");			
			if (!file.isEmpty()) {
	            try {
	                byte[] bytes = file.getBytes();
	                String rootPath = System.getProperty("catalina.home");
	                File dir = new File(rootPath + File.separator + "uploadFiles");
	                if (!dir.exists())
	                    dir.mkdirs();
	                String fileFullName = dir.getAbsolutePath() + File.separator +  file.getOriginalFilename();
	                File serverFile = new File(fileFullName);	                
	                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
	                stream.write(bytes);
	                stream.close();
	                logger.info("Server File Location="+ serverFile.getAbsolutePath());
	                
	            	String cvsSplitBy = ",";
	            	JsonArray jsonArray = new JsonArray();
	                BufferedReader br = new BufferedReader(new FileReader(serverFile));
	                String[] headerNames =null;
	                String line = line = br.readLine();
	                int count=0;
	                if(line != null) {
	                	if(hasHeader){
	                		headerNames = line.split(cvsSplitBy);
		                }else {
		                	String[] cells = line.split(cvsSplitBy);
		                	headerNames = new String[cells.length];
		                	for(int i=0;i<headerNames.length;i++){
		                		headerNames[i] = "Column "+ i;
		                	}
		                	JsonObject json =new JsonObject();
		        			for(int i=0;i<headerNames.length && i<cells.length;i++){
		        				json.addProperty(headerNames[i], cells[i]);
		        			}
		        			jsonArray.add(json);
		        			count++;
		                }
	                	while ((line = br.readLine()) != null && count<maxRowSize) {
		        			String[] cells = line.split(cvsSplitBy);
		        			JsonObject json =new JsonObject();
		        			for(int i=0;i<headerNames.length && i<cells.length;i++){
		        				json.addProperty(headerNames[i], cells[i]);
		        			}
		        			jsonArray.add(json);
		        			count++;
		        		}
                	}
	                
	                
	                
	        		
	        		jsonObject.add("array", jsonArray);
	            } catch (Exception ex) {
	            	jsonObject.addProperty("Exception",ex.getMessage());
	            	jsonObject.addProperty("AdditionalInformation", "You failed to upload " + file.getName());
	            }
	        } else {
	        	jsonObject.addProperty("Exception","because the file was empty");
            	jsonObject.addProperty("AdditionalInformation", "You failed to upload " + file.getName());
	        }
		}catch(Exception ex){
			jsonObject.addProperty("Exception",ex.getMessage());
			logger.error("Exception",ex);
		}
        return jsonObject;
    }
	
	public LoginBO getLoginBO() {
		return loginBO;
	}
	public void setLoginBO(LoginBO loginBO) {
		this.loginBO = loginBO;
	}
	
}
