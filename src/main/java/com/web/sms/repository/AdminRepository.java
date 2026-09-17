package com.web.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.sms.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{

	public Admin findByAdminId(String username);
}
