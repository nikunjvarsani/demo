package com.user.userController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.userProxy.EducationDetailsProxy;
import com.user.userProxy.FeeDetailsProxy;
import com.user.userProxy.StudentProxy;
import com.user.userServices.StudentServices;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private StudentServices studentServices;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerWithDetails(@RequestBody StudentProxy studentProxy)
	{
		return new ResponseEntity<>(studentServices.registerWithDetails(studentProxy), HttpStatus.OK);
	}
	
	@PostMapping("/updateUser/{id}")
	public ResponseEntity<String> updateUserDetails(@RequestBody StudentProxy studentProxy,@PathVariable Long id )
	{
		return new ResponseEntity<>(studentServices.updateUserDetails(studentProxy,id), HttpStatus.OK);
	}
	
	@GetMapping("/getUser/{Id}")
	public ResponseEntity<StudentProxy>getUser(@PathVariable Long Id,@RequestHeader Map<String, String> hdata)
	{
		return new ResponseEntity<>(studentServices.getUser(Id,hdata), HttpStatus.OK);
	}

	
	  @GetMapping("/getFeeDetails/{Id}")
	  public ResponseEntity<FeeDetailsProxy> getFeeDetails(@PathVariable Long Id,@RequestHeader Map<String, String> headerData) { 
		  System.out.println("++++++++++++++++++++++++++++++++++++");
		  return new
	  ResponseEntity<>(studentServices.getFeeDetails(Id,headerData), HttpStatus.OK); }
	 
	@PostMapping("/addEducationDetails")
	public ResponseEntity<String>addEducationDetails(@RequestBody EducationDetailsProxy educationDetailsProxy)
	{
		return new ResponseEntity<>(studentServices.addEducationDetails(educationDetailsProxy), HttpStatus.OK);
	}
	
	@PostMapping("/updateEducationDetails/{id}")
	public ResponseEntity<String>updateEducationDetails(@PathVariable Long id,@RequestBody EducationDetailsProxy educationDetailsProxy)
	{
		return new ResponseEntity<>(studentServices.updateEducationDetails(id,educationDetailsProxy), HttpStatus.OK);
	}
	
	@GetMapping("/getAllUser")
	public ResponseEntity<List<StudentProxy>>getAllUser()
	{
		
		return new ResponseEntity<>(studentServices.getAllUser(), HttpStatus.OK);
	}
	
	@GetMapping("/delete/{id}")
	public ResponseEntity<String>getUserDelete(@PathVariable Long id)
	{
		
		return new ResponseEntity<>(studentServices.getDeleteUser(id), HttpStatus.OK);
	}
	
	@GetMapping("/g")
	public ResponseEntity<String>getAllUser1()
	{
		
		return new ResponseEntity<>(studentServices.getSavefakeData(), HttpStatus.OK);
	}
}

