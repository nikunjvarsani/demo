package com.serviceAuth.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor(staticName = "StudentCon")
@NoArgsConstructor
public class Student_AdminEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String password;
	private String role;
	private String name;
	private String address;
	private String email;
	private Date dob;
    private Integer otp;
    private java.util.Date currentTimeAndDate;
    private String URL;
}
