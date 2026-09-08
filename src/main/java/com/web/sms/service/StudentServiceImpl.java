package com.web.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.sms.entity.Student;
import com.web.sms.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	@Override
	public Student addStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Student updateStudent(Student student) {
		
		Student existing = studentRepository.findById(student.getId()).get();
		
		if (student.getStudentId() != null) {
	        existing.setStudentId(student.getStudentId());
	    }
	    if (student.getName() != null) {
	        existing.setName(student.getName());
	    }
	    if (student.getEmail() != null) {
	        existing.setEmail(student.getEmail());
	    }
	    if (student.getPhoneNumber() != null) {
	        existing.setPhoneNumber(student.getPhoneNumber());
	    }
	    if (student.getDob() != null) {
	        existing.setDob(student.getDob());
	    }
	    if (student.getGender() != null) {
	        existing.setGender(student.getGender());
	    }
	    if (student.getAddress() != null) {
	        existing.setAddress(student.getAddress());
	    }
	    if (student.getPassword() != null) {
	        existing.setPassword(student.getPassword()); 
	        // optionally encode here if using Spring Security
	    }
	    if (student.getBranch() != null) {
	        existing.setBranch(student.getBranch());
	    }
	    if (student.getYear() != 0) { // primitive int, check against default
	        existing.setYear(student.getYear());
	    }
	    if (student.getSemester() != 0) {
	        existing.setSemester(student.getSemester());
	    }
	    if (student.getBloodGroup() != null) {
	        existing.setBloodGroup(student.getBloodGroup());
	    }
	    if (student.getStatus() != null) {
	        existing.setStatus(student.getStatus());
	    }
	    if (student.getParentPhoneNumber() != null) {
	        existing.setParentPhoneNumber(student.getParentPhoneNumber());
	    }

		return studentRepository.save(existing);
	}

	@Override
	public Student getStudentById(long id) {
		return studentRepository.findById(id).get();
	}

	@Override
	public void deleteStudentById(long id) {
		studentRepository.deleteById(id);
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}
}
