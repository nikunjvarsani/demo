package com.serviceAuth.services;

import org.springframework.stereotype.Service;

import com.serviceAuth.proxies.JwtRequest;
import com.serviceAuth.proxies.JwtResponse;
import com.serviceAuth.proxies.StudentProxy;

@Service
public interface AuthService {

     public Boolean ValidateToken( );
	
	 public JwtResponse loginWithCredentials(JwtRequest jwtRequest);
	 
	 public String UserRegister(StudentProxy studentProxy);
	 
	 public String adminRegidter(StudentProxy studentProxy);

}

