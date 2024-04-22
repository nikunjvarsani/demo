package com.serviceAuth.utils;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serviceAuth.entity.Student_AdminEntity;
import com.serviceAuth.proxies.StudentProxy;

@Component
public class ObjectMapperHelper {

	
	ObjectMapper mapper=new ObjectMapper();
	public  StudentProxy EntityToDtoConversion(Student_AdminEntity student)
	{ 
	 
	   return mapper.convertValue(student, StudentProxy.class);	
	}
	
	public  Student_AdminEntity DtoToEntityConversion(StudentProxy studentProxy)
	{ 
		
	   return mapper.convertValue(studentProxy,Student_AdminEntity.class );
	}
}
