package com.web.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.sms.entity.StudentBusRegistration;

public interface StudentBusRegistrationRepository extends JpaRepository<StudentBusRegistration, Long> {


    void deleteByStudentId(Long studentId);
}
