package com.user.userServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.github.javafaker.Faker;
import com.user.entities.EducationDetails;
import com.user.entities.Student;
import com.user.repository.EducationDetailsRepo;
import com.user.repository.StudentRepo;
import com.user.userProxy.EducationDetailsProxy;
import com.user.userProxy.FeeDetailsProxy;
import com.user.userProxy.StudentProxy;
import com.user.userServices.StudentServices;
import com.user.utils.ObjectMapperHelper;
@Component
public class StudentImpl implements StudentServices {
	
	@Autowired
	private ObjectMapperHelper helper;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private Faker faker;
	
	
	
	//@Autowired
	//private FeeDetailsRepo feeDetailsRepo;
	
	@Autowired
	private EducationDetailsRepo educationDetailsRepo;

	@Override
	public String registerWithDetails(StudentProxy studentProxy) {
		
	Student dtoToEntityConversion = helper.DtoToEntityConversion(studentProxy);
	if(dtoToEntityConversion != null)
	{
		studentRepo.save(dtoToEntityConversion);
		return "Data save";}
	return "Data not save";
	}
	
	
	@Override
	public String updateUserDetails(StudentProxy studentProxy,Long id) {
		// TODO Auto-generated method stub
		Optional<Student> byId = studentRepo.findById(id);
		if(byId.isPresent())
		{
			
			Student dtoToEntityConversion = helper.DtoToEntityConversion(studentProxy);
			studentRepo.save(dtoToEntityConversion);
			return"Save";
		}
		
		return "Data not save";
	}


	@Override
	public StudentProxy getUser(Long id,Map<String, String> hData) {
		// TODO Auto-generated method stub
		
		Optional<Student> byId = studentRepo.findById(id);
		if(byId.isPresent())
		{
			Student student = byId.get();
		return	helper.EntityToDtoConversion(student);
		}
		return null;
	}


	@Override
	public FeeDetailsProxy getFeeDetails(Long id,Map<String, String> headerData) {
		HttpHeaders headers=new HttpHeaders();
		headers.set("Auth", headerData.get("token"));
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Object>entity=new HttpEntity<>(null,headers);
		Boolean validaToken = restTemplate.postForObject("http://AUTH-SERVICE/Auth/auth/validate", entity, Boolean.class);
		if(validaToken.equals(true)) {
		return restTemplate.getForObject("http://ADMIN-SERVICE/admin/getFeeDetails/"+id, FeeDetailsProxy.class);}
		return null;
	}


	@Override
	public String addEducationDetails(EducationDetailsProxy educationDetailsProxy) {
		// TODO Auto-generated method stub
		EducationDetails eduDtoToEntityConversion = helper.EduDtoToEntityConversion(educationDetailsProxy);
		educationDetailsRepo.save(eduDtoToEntityConversion);
		return "save";
	}


	@Override
	public String updateEducationDetails(Long id,EducationDetailsProxy educationDetailsProxy) {
		// TODO Auto-generated method stub
		Optional<EducationDetails> byId = educationDetailsRepo.findById(id);
		if (byId.isPresent()) {
			EducationDetails eduDtoToEntityConversion = helper.EduDtoToEntityConversion(educationDetailsProxy);
			educationDetailsRepo.save(eduDtoToEntityConversion);
			return "updated";
			
			
			
		}
		return "Not Update";
	}


	@Override
	public List<StudentProxy> getAllUser() {
		ArrayList<StudentProxy> list=new ArrayList<>();
		List<Student> all = (List<Student>) studentRepo.findAll();
		for (Student student : all) {
			StudentProxy entityToDtoConversion = helper.EntityToDtoConversion(student);
			list.add(entityToDtoConversion);
		}
		
		return list;
	}


	@Override
	public String getSavefakeData() {
	
		for (int i = 0; i < 1000; i++) {
			Student student =new Student(faker.number().randomNumber(),"male",faker.commerce().department(),faker.date().birthday(),
					faker.phoneNumber().cellPhone(),faker.address().country(),faker.address().cityName(),faker.number().randomDigit());
			studentRepo.save(student);
			
			
		}
		return null;
	}


	@Override
	public String getDeleteUser(Long id) {
		studentRepo.deleteById(id);
		return "delete";
	}

}



