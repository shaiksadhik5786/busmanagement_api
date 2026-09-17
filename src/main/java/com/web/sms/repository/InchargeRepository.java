package com.web.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.sms.entity.Incharge;

public interface InchargeRepository extends JpaRepository<Incharge, Long> {

	Incharge findByTeacherId(String teacherId);
}
