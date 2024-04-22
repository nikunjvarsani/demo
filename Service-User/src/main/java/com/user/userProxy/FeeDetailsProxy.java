package com.user.userProxy;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeeDetailsProxy {

	private Long id;
	private String student_enrollmentNo;
	private double feeAmount;
	private Date dueDate;
	private String updatedBy;
	private Date updatedDate;
}


