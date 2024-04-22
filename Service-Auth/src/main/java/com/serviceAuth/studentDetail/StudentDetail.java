package com.serviceAuth.studentDetail;



import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.serviceAuth.entity.Student_AdminEntity;
import com.serviceAuth.repository.StudentRepo;

@Component
@Configuration
public class StudentDetail implements UserDetailsService {
  private String role;
    
	
	public String getRole() {
	return role;
}


    public void setRole(String role) {
	this.role = role;
}


	@Autowired
	private StudentRepo studentRepo;

	

	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Student_AdminEntity studentName = studentRepo.findByName(username);
		
		if(studentName.getRole().equals("User") && studentName.getName().equals(username)) {
			
				
				return new User(username, passwordEncoder().encode(studentName.getPassword()), new ArrayList<>());
			
		
		}
		

		else if(studentName.getRole().equals("Admin") && studentName.getName().equals(username)) {
			
				return new User(username, passwordEncoder().encode(studentName.getPassword()), new ArrayList<>());
			
		
		}
		
		throw new UsernameNotFoundException("Inavalid username " + username);
	}

}

