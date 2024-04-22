package com.serviceAuth.services;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public interface emailService {

	public void sendEmail(String to, String subject, String body);

	public String userInfo(String email, Date dob);

	public String forgotPassword(String mail, String password, Integer otp, Date date);

	public String SendUrl(String mail,Date dob);
	
	public String UrlPassReset(String mail,Date date ,String pass);

}
