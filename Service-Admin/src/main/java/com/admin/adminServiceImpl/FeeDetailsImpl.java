package com.admin.adminServiceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.admin.adminServices.FeeDetailsServices;
import com.admin.entity.FeeDetails;
import com.admin.proxy.FeeDetailsProxy;
import com.admin.repository.FeeDetailsRepo;
import com.admin.utils.ObjectMapperHelper;

@Component
public class FeeDetailsImpl implements FeeDetailsServices {
	@Autowired
	private FeeDetailsRepo feeDetailsRepo;
	
	@Autowired
	private ObjectMapperHelper helper;
	@Override
	public String addFeeDetails(FeeDetailsProxy feeDetailsProxy) {
		FeeDetails feeDtoToEntityConversion = helper.FeeDtoToEntityConversion(feeDetailsProxy);
		feeDetailsRepo.save(feeDtoToEntityConversion);
		return "Save";
	}

	@Override
	public FeeDetailsProxy getFeeDetails(Long id) {
		Optional<FeeDetails> byId = feeDetailsRepo.findById(id);
		if(byId.isPresent())
		{
			FeeDetails feeDetails = byId.get();
		return	helper.FeeEntityToDtoConversion(feeDetails);
		}
		return null;
	}

}
