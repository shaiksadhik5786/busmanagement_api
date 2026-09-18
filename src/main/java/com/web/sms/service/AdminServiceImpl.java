package com.web.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.sms.dto.User;
import com.web.sms.entity.Admin;
import com.web.sms.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public Admin addAdmin(Admin admin) {
		return adminRepository.save(admin);
	}

	@Override
	public Admin getAdmin(String id) {
		return adminRepository.findByAdminId(id);
	}

	@Override
	public List<Admin> getAllAdmin() {
		return adminRepository.findAll();
	}

}
