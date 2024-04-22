package com.user.utils;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.entities.EducationDetails;
import com.user.entities.Student;
import com.user.userProxy.EducationDetailsProxy;
import com.user.userProxy.StudentProxy;

@Component
public class ObjectMapperHelper {

	ObjectMapper mapper=new ObjectMapper();
	public  StudentProxy EntityToDtoConversion(Student student)
	{ 
	
	   return mapper.convertValue(student, StudentProxy.class);	
	}
	
	public  Student DtoToEntityConversion(StudentProxy studentProxy)
	{ 
		
	   return mapper.convertValue(studentProxy, Student.class);	
	}
	
	
	public  EducationDetailsProxy EduEntityToDtoConversion(EducationDetails educationDetails)
	{ 
	
	   return mapper.convertValue(educationDetails, EducationDetailsProxy.class);	
	}
	
	public  EducationDetails EduDtoToEntityConversion(EducationDetailsProxy educationDetailsProxy)
	{ 
		
	   return mapper.convertValue(educationDetailsProxy, EducationDetails.class);	
	}
}
