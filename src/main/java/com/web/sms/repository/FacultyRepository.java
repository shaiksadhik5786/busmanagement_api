package com.web.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.sms.entity.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

}
