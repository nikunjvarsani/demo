package com.user.userServices;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.user.userProxy.EducationDetailsProxy;
import com.user.userProxy.FeeDetailsProxy;
import com.user.userProxy.StudentProxy;

@Service
public interface StudentServices {

public String registerWithDetails (StudentProxy studentProxy);
	
	public String updateUserDetails(StudentProxy studentProxy,Long id);
	
	public StudentProxy getUser(Long id,Map<String, String> hData);
	
	public FeeDetailsProxy getFeeDetails(Long id,Map<String, String> headerData);

	public String addEducationDetails(EducationDetailsProxy educationDetailsProxy);
	
	public String updateEducationDetails(Long id,EducationDetailsProxy educationDetailsProxy);
	
	public List<StudentProxy> getAllUser();
	
	public String getSavefakeData();
	
	public String getDeleteUser(Long id);
}

