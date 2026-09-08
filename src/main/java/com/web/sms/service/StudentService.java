package com.web.sms.service;

import java.util.List;

import com.web.sms.entity.Student;

public interface StudentService {

	public Student addStudent(Student student);
	public Student updateStudent(Student student);
	public Student getStudentById(long id);
	public void deleteStudentById(long id);
	public List<Student> getAllStudents();
}
