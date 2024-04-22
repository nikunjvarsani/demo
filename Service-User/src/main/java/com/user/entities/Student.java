package com.user.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	@Id
	private Long enrollmentNo;
	private String gender;
	private String branch;
	private Date dob;
	private String contact;
	private String address;
	private String city;
	private Integer pinCode;

	

}
