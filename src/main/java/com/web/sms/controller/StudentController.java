package com.web.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.sms.entity.Student;
import com.web.sms.service.StudentService;

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@PostMapping("/")
	public ResponseEntity<Student> addStudent(@RequestBody Student student)
	{
		Student std = studentService.addStudent(student);
		return new ResponseEntity<>(std,HttpStatus.CREATED);
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") long id, @RequestBody Student student)
	{
		student.setId(id);
		Student std = studentService.updateStudent(student);
		return ResponseEntity.ok(std);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable("id") long id)
	{
		Student std = studentService.getStudentById(id);
		return ResponseEntity.ok(std);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteStudentById(@PathVariable("id") long id)
	{
		studentService.deleteStudentById(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Student>> getAllStudents()
	{
		List<Student> students = studentService.getAllStudents();
		return ResponseEntity.ok(students);
	}
}
