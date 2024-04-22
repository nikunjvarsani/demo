package com.serviceAuth.proxies;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentProxy {

	private Long id;
	private String password;
	private String role;
	private String name;
	private String address;
	private String email;
	private Date dob;
	private String otp;
}
