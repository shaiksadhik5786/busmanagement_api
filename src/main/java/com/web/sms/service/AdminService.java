package com.web.sms.service;

import java.util.List;

import com.web.sms.dto.User;
import com.web.sms.entity.Admin;

public interface AdminService {
	
	public Admin addAdmin(Admin admin);
	public Admin getAdmin(String id);
	public List<Admin> getAllAdmin();
}
