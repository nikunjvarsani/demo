package com.admin.adminServiceImpl;

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

import com.admin.adminServices.AdminServices;
import com.admin.entity.Administartor;
import com.admin.proxy.AdministartorProxy;
import com.admin.proxy.StudentProxy;
import com.admin.repository.AdministartoRepo;
import com.admin.utils.ObjectMapperHelper;

@Component
public class AdministartorImpl implements AdminServices {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ObjectMapperHelper helper;

	@Autowired
	private AdministartoRepo administartoRepo;

	// @Autowired
	// private StudentRepo studentRepo;
	private Boolean TokenValidate(Map<String, String> Data) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Auth", Data.get("token"));

		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Object> httpEntity = new HttpEntity<>(null, headers);

		Boolean validaToken = restTemplate.postForObject("http://AUTH-SERVICE/Auth/auth/validate", httpEntity,
				Boolean.class);
		return validaToken;
	}

	@Override
	public String registerWithDetails(AdministartorProxy administartorProxy) {
		Administartor adminDtoToEntityConversion = helper.AdminDtoToEntityConversion(administartorProxy);
		if (adminDtoToEntityConversion != null) {
			administartoRepo.save(adminDtoToEntityConversion);
			return "Data Save";
		}
		return "Data Not Save";
	}

	@Override
	public String updateUserDetails(StudentProxy studentProxy, String id, Map<String, String> headerData) {

		Boolean tokenValidate = TokenValidate(headerData);
		if (tokenValidate.equals(true)) {
			restTemplate.postForEntity("http://USER-SERVICE/User/user/updateUser/" + id, studentProxy, String.class);
			/*
			 * String postForObject =
			 * restTemplate.postForObject("http://USER-SERVICE/User/user/updateUser/" + id,
			 * studentProxy, String.class);
			 */
			return "Data Save";
		}

		return "Data Not Save";
	}

	@Override
	public String updateAdminDetails(AdministartorProxy administartorProxy, Long id) {
		Optional<Administartor> byId = administartoRepo.findById(id);

		if (byId.isPresent()) {
			Administartor adminDtoToEntityConversion = helper.AdminDtoToEntityConversion(administartorProxy);
			administartoRepo.save(adminDtoToEntityConversion);
			return "Data Update";
		}
		return null;
	}

	@Override
	public String deleteUser(String id) {
		// System.out.println("data inside");
		
		  String postForObject =
		  restTemplate.getForObject("http://localhost:8093/User/user/delete"+id,
		  String.class);
		 

		return "Data Deleted";
	}
	// return "Data Not found"; }

	@Override
	public List<StudentProxy> getUsers(Map<String, String> HeaderData) {

		if (TokenValidate(HeaderData).equals(true)) {
			System.out.println("Header is " + TokenValidate(HeaderData).equals(true));
			ArrayList forObject = restTemplate.getForObject("http://USER-SERVICE/User/user/getAllUser",
					ArrayList.class);
			return forObject;
		}
		return null;
	}

	/*
	 * j
	 * 
	 * @Override public StudentProxy getUser(String id) {
	 * 
	 * Optional<Student> byId = studentRepo.findById(id); if (byId.isPresent()) {
	 * Student student = byId.get();
	 * 
	 * StudentProxy entityToDtoConversion = helper.EntityToDtoConversion(student);
	 * 
	 * return entityToDtoConversion; } return null; }
	 */

	@Override
	public AdministartorProxy getAdmin(Long id) {

		Optional<Administartor> byId = administartoRepo.findById(id);

		if (byId.isPresent()) {
			Administartor administartor = byId.get();
			AdministartorProxy adminEntityToDtoConversion = helper.AdminEntityToDtoConversion(administartor);

			return adminEntityToDtoConversion;
		}
		return null;
	}

}
