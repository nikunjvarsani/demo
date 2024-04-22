package com.admin.utils;

import org.springframework.stereotype.Component;

import com.admin.entity.Administartor;
import com.admin.entity.FeeDetails;
import com.admin.proxy.AdministartorProxy;
import com.admin.proxy.FeeDetailsProxy;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class ObjectMapperHelper {
	ObjectMapper mapper=new ObjectMapper();
	public  AdministartorProxy AdminEntityToDtoConversion(Administartor administartor)
	{ 
	
	   return mapper.convertValue(administartor, AdministartorProxy.class);	
	}
	
	public  Administartor AdminDtoToEntityConversion(AdministartorProxy administartorProxy)
	{ 
		
	   return mapper.convertValue(administartorProxy, Administartor.class);	
	}
	
	
	public  FeeDetailsProxy FeeEntityToDtoConversion(FeeDetails feeDetails)
	{ 
	
	   return mapper.convertValue(feeDetails, FeeDetailsProxy.class);	
	}
	
	public  FeeDetails FeeDtoToEntityConversion(FeeDetailsProxy feeDetailsProxy)
	{ 
		
	   return mapper.convertValue(feeDetailsProxy, FeeDetails.class);	
	}
}
