package com.admin.proxy;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationDetailsProxy {



	private Long id;
	private String student_enrollmentNo;
	private String educationType;
	private boolean qualified;
	private String percentage;
	private String updatedBy;
	private Date updatedDate;

}
