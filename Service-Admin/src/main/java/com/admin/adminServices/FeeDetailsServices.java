package com.admin.adminServices;

import org.springframework.stereotype.Service;

import com.admin.proxy.FeeDetailsProxy;

@Service
public interface FeeDetailsServices {
	
public String addFeeDetails(FeeDetailsProxy feeDetailsProxy);
	
	public FeeDetailsProxy getFeeDetails(Long id);
}
