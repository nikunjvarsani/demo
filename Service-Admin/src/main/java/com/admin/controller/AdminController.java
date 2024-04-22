package com.admin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.admin.adminServices.AdminServices;
import com.admin.adminServices.FeeDetailsServices;
import com.admin.proxy.AdministartorProxy;
import com.admin.proxy.EducationDetailsProxy;
import com.admin.proxy.FeeDetailsProxy;
import com.admin.proxy.StudentProxy;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private AdminServices administartorServices;

	// @Autowired
	// private EducationDetailsServices educationDetailsServices;

	@Autowired
	private FeeDetailsServices feeDetailsServices;

	@PostMapping("/register")
	public ResponseEntity<String> registerWithDetails(@RequestBody AdministartorProxy administartorProxy) {
		return new ResponseEntity<>(administartorServices.registerWithDetails(administartorProxy), HttpStatus.ACCEPTED);
	}

	@PostMapping("/updateUser/{id}")
	public ResponseEntity<String> updateUserDetails(@RequestBody StudentProxy studentProxy, @PathVariable String id,@RequestHeader Map<String, String> headerData) {
		return new ResponseEntity<>(administartorServices.updateUserDetails(studentProxy, id,headerData), HttpStatus.ACCEPTED);
	}

	@PostMapping("/updateAdmin/{id}")
	public ResponseEntity<String> updateAdminDetails(@RequestBody AdministartorProxy administartorProxy,
			@PathVariable Long id) {
		return new ResponseEntity<>(administartorServices.updateAdminDetails(administartorProxy, id),
				HttpStatus.ACCEPTED);
	}

	@GetMapping("/deleteUser/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable String id) {
		System.out.println("dasaaaaaaaaaaaa ++++++++++++++++++++++++");
		return new ResponseEntity<>(administartorServices.deleteUser(id), HttpStatus.ACCEPTED);
	}

    @GetMapping("/getUsers")
	public ResponseEntity<List<StudentProxy>> getUsers(@RequestHeader Map<String, String>  HeaderData ) {
		System.out.println("hello niknuj" +HeaderData);
		return new ResponseEntity<>(administartorServices.getUsers(HeaderData), HttpStatus.ACCEPTED);
	}

	@GetMapping("/getUser/{id}")
	public ResponseEntity<StudentProxy> getUser(@PathVariable String id) {
		System.out.println("dasa");
		StudentProxy forObject = restTemplate.getForObject("http://USER-SERVICE/User/user/getUser/" + id,
				StudentProxy.class);
		return new ResponseEntity<>(forObject, HttpStatus.ACCEPTED);
		
	}

	@GetMapping("/getAdmin/{id}")
	public ResponseEntity<AdministartorProxy> getAdmin(@PathVariable Long id) {
		return new ResponseEntity<>(administartorServices.getAdmin(id), HttpStatus.ACCEPTED);
	}

	@PostMapping("/addFeeDetails")
	public ResponseEntity<String> addFeeDetails(@RequestBody FeeDetailsProxy feeDetailsProxy) {
		return new ResponseEntity<>(feeDetailsServices.addFeeDetails(feeDetailsProxy), HttpStatus.ACCEPTED);
	}

	@GetMapping("/getFeeDetails/{id}")
	public ResponseEntity<FeeDetailsProxy> getFeeDetails(@PathVariable Long id) {
		return new ResponseEntity<>(feeDetailsServices.getFeeDetails(id), HttpStatus.ACCEPTED);
	}

	@GetMapping("/getEducationDetails/{id}")
	public ResponseEntity<EducationDetailsProxy> getEducationDetails(@PathVariable Long id) {

		return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
	}

}
