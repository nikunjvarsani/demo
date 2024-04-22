package com.admin.proxy;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdministartorProxy {
	private Long id;
	private String gender;
	private String branch;
	private Date dob;
	private String contact;
	private String address;
	private String city;
	private Integer pinCode;
	private String securityKey;
}
