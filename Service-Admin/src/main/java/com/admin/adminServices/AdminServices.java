package com.admin.adminServices;

import java.util.List;
import java.util.Map;

import com.admin.proxy.AdministartorProxy;
import com.admin.proxy.StudentProxy;

public interface AdminServices {

public String registerWithDetails(AdministartorProxy administartorProxy);
	
	public String updateUserDetails(StudentProxy studentProxy,String id,Map<String, String> headerData);
	
	public String updateAdminDetails(AdministartorProxy administartorProxy,Long id);
	
	public String deleteUser(String id);
	
	public List<StudentProxy> getUsers( Map<String, String> HeaderData);
	
	//public StudentProxy getUser(String id);
	
	public AdministartorProxy getAdmin(Long id);
}
