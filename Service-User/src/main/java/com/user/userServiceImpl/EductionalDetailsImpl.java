package com.user.userServiceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.user.entities.EducationDetails;
import com.user.repository.EducationDetailsRepo;
import com.user.userProxy.EducationDetailsProxy;
import com.user.userServices.EducationDetailsServices;
import com.user.utils.ObjectMapperHelper;

public class EductionalDetailsImpl implements EducationDetailsServices {
	@Autowired
	private EducationDetailsRepo educationDetailsRepo;
	
	@Autowired
	private ObjectMapperHelper helper;
	
	
	@Override
	public EducationDetailsProxy getEducationDetails(Long id) {
		Optional<EducationDetails> byId = educationDetailsRepo.findById(id);
		if(byId.isPresent())
		{
			EducationDetails educationDetails = byId.get();
		  return	helper.EduEntityToDtoConversion(educationDetails);
		}
		return null;
	}
}
