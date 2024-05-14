package com.urekaserver.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;
import com.urekaserver.services.services;

@RestController
//@Controller
public class controller {
	
@Autowired
private services services;
	


	@GetMapping("/getname")
	public String hello(Model model)
	{model.addAttribute("name","nikunj");
		              
		return "welcome";
	}
	
	@GetMapping("pdf")
	public ResponseEntity<InputStreamResource> createPdf()
	{
		HttpHeaders headers=new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=data.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(services.createPdf()));
	}
	
	
	@GetMapping("newPdf")
	public void createPdf1(HttpServletResponse response) throws DocumentException, IOException
	{
//		response.setHeader("Content-Disposition","attachment;");
//		headers.add("Content-Disposition", "attachment; filename=data.pdf");
		services.createPdf1(response);
		
	}
	
	
	@GetMapping("sendMail")
	public String sendMail() 
	{
//		response.setHeader("Content-Disposition","attachment;");
//		headers.add("Content-Disposition", "attachment; filename=data.pdf");
		return services.sendMail();
		
	}
}
