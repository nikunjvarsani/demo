package com.serviceAuth.authImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.serviceAuth.entity.Student_AdminEntity;
import com.serviceAuth.proxies.JwtRequest;
import com.serviceAuth.proxies.JwtResponse;
import com.serviceAuth.proxies.StudentProxy;
import com.serviceAuth.repository.StudentRepo;
import com.serviceAuth.services.AuthService;
import com.serviceAuth.utils.ObjectMapperHelper;

@Component
public class AuthImpl implements AuthService{

	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private ObjectMapperHelper helper;
	
	@Override
	public Boolean ValidateToken( ) {
		// TODO Auto-generated method stub
		return Boolean.TRUE;
	}

	@Override
	public JwtResponse loginWithCredentials(JwtRequest jwtRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String UserRegister(StudentProxy studentProxy) {
		Student_AdminEntity dtoToEntityConversion = helper.DtoToEntityConversion(studentProxy);
		
		dtoToEntityConversion.setRole("User");
		studentRepo.save(dtoToEntityConversion);
		return "save";
	}

	@Override
	public String adminRegidter(StudentProxy studentProxy) {
		Student_AdminEntity dtoToEntityConversion = helper.DtoToEntityConversion(studentProxy);
		dtoToEntityConversion.setRole("Admin");
		studentRepo.save(dtoToEntityConversion);
		return "save";
	}

}
