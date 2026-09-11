package com.web.sms.service;

import java.util.List;

import com.web.sms.dto.StudentBusRegistrationDto;
import com.web.sms.entity.StudentBusRegistration;

public interface StudentBusRegistrationService {

	public StudentBusRegistration addStudentBusRegistration(StudentBusRegistration studentBusRegistration);
	public StudentBusRegistration updateStudentBusRegistration(StudentBusRegistration studentBusRegistration);
	public StudentBusRegistrationDto getById(long id);
	public void deleteById(long id);
	public List<StudentBusRegistrationDto> getAll();
	public void deleteByStudentId(long id);
	public String approveStatus(long id);
	public String rejectStudent(long id);
}
