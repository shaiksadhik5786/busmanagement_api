package com.web.sms.service;

import java.util.List;

import com.web.sms.dto.StudentDto;
import com.web.sms.entity.Student;

public interface StudentService {

	public Student addStudent(Student student);
	public Student updateStudent(Student student);
	public StudentDto getStudentById(long id);
	public void deleteStudentById(long id);
	public List<StudentDto> getAllStudents();
}
