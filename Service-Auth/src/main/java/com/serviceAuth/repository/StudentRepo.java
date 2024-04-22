package com.serviceAuth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.serviceAuth.entity.Student_AdminEntity;







@Repository
public interface StudentRepo extends CrudRepository<Student_AdminEntity, Long>{

	Student_AdminEntity findByName(String name);
	
	Student_AdminEntity findByEmailAndDob(String email, java.util.Date dob);
	

	
}

