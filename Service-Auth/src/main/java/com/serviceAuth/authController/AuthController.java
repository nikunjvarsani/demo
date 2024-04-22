package com.serviceAuth.authController;

import java.sql.Date;

import org.apache.commons.lang.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serviceAuth.proxies.JwtRequest;
import com.serviceAuth.proxies.JwtResponse;
import com.serviceAuth.proxies.StudentProxy;
import com.serviceAuth.services.AuthService;
import com.serviceAuth.services.emailService;
import com.serviceAuth.studentDetail.StudentDetail;
import com.serviceAuth.utils.JwtUtil;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private StudentDetail studentDetail;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private emailService emailService;

	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> loginWithCredentials(@RequestBody JwtRequest jwtRequest)
			throws UsernameNotFoundException {
		System.out.println("dataa " + jwtRequest);
		try {
			studentDetail.setRole(jwtRequest.getRole());

			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getName(), jwtRequest.getPassword()));

		} catch (Exception e) {

			throw new UsernameNotFoundException("invalid user " + jwtRequest.getName());
		}

		// token
		UserDetails userDetails = studentDetail.loadUserByUsername(jwtRequest.getName());
		String token = jwtUtil.generateToken(userDetails);

		return new ResponseEntity<JwtResponse>(new JwtResponse(token), HttpStatus.ACCEPTED);
	}

	@PostMapping("/validate")
	public ResponseEntity<Boolean> ValidateToken() {

		return new ResponseEntity<>(authService.ValidateToken(), HttpStatus.ACCEPTED);
	}

	@PostMapping("/userRegister")
	public ResponseEntity<String> UserRegister(@RequestBody StudentProxy studentProxy) {
		System.out.println("data is " + studentProxy);
		return new ResponseEntity<>(authService.UserRegister(studentProxy), HttpStatus.ACCEPTED);
	}

	@PostMapping("/adminRegister")
	public ResponseEntity<String> adminRegister(@RequestBody StudentProxy studentProxy) {
		return new ResponseEntity<>(authService.adminRegidter(studentProxy), HttpStatus.ACCEPTED);
	}

	@GetMapping("send-otp/{mail}/{dob}")
	public ResponseEntity<String> SendOtp(@PathVariable String mail, @PathVariable Date dob) {

		return new ResponseEntity<>(emailService.userInfo(mail, dob), HttpStatus.ACCEPTED);
	}

	@GetMapping("forgot-password/{mail}/{password}/{otp}/{date}")
	public ResponseEntity<String> forgotPassword(@PathVariable String mail, @PathVariable String password,
			@PathVariable Integer otp, @PathVariable Date date) {
		

		return new ResponseEntity<>(emailService.forgotPassword(mail, password, otp, date), HttpStatus.ACCEPTED);
	}

	@GetMapping("URLPassReset/{mail}/{dob}")
	public ResponseEntity<String> SendUrl(@PathVariable String mail, @PathVariable Date dob) {

		if (mail != null && dob != null) {
			return new ResponseEntity<>(emailService.SendUrl(mail, dob), HttpStatus.ACCEPTED);

		}
		return new ResponseEntity<>("All Flieds Required", HttpStatus.BAD_GATEWAY);
	}

	@GetMapping("URL-forgot-password/{mail}/{date}/{pass}")
	public ResponseEntity<String> URLforgotPassword(@PathVariable String mail, @PathVariable Date date,
			@PathVariable String pass) {
		
		if (pass != null && date != null && mail != null) {
			return new ResponseEntity<>(emailService.UrlPassReset(mail, date, pass), HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>("All Flieds Required", HttpStatus.BAD_GATEWAY);
	}

}
