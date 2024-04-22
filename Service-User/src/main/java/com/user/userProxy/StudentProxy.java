package com.user.userProxy;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentProxy {
	private Long enrollmentNo;
	private String gender;
	private String branch;
	private Date dob;
	private String contact;
	private String address;
	private String city;
	private Integer pinCode;

}
