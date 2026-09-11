package com.web.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.sms.dto.StudentBusRegistrationDto;
import com.web.sms.entity.StudentBusRegistration;
import com.web.sms.service.StudentBusRegistrationService;

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/student-bus-registration")
public class StudentBusRegistrationController {

	    @Autowired
	    private StudentBusRegistrationService studentBusRegistrationService;

	    @PostMapping("/")
	    public ResponseEntity<StudentBusRegistration> addStudentBusRegistration(
	            @RequestBody StudentBusRegistration studentBusRegistration) {
	        StudentBusRegistration saved = studentBusRegistrationService.addStudentBusRegistration(studentBusRegistration);
	        return ResponseEntity.ok(saved);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<StudentBusRegistration> updateStudentBusRegistration(@PathVariable long id,
	            @RequestBody StudentBusRegistration studentBusRegistration) {
	    	
	        studentBusRegistration.setId(id);
	        StudentBusRegistration updated = studentBusRegistrationService.updateStudentBusRegistration(studentBusRegistration);
	        return ResponseEntity.ok(updated);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<StudentBusRegistrationDto> getById(@PathVariable Long id) {
	    	StudentBusRegistrationDto registration = studentBusRegistrationService.getById(id);
	        return ResponseEntity.ok(registration);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
	        studentBusRegistrationService.deleteById(id);
	        return ResponseEntity.noContent().build();
	    }

	    @GetMapping("/")
	    public ResponseEntity<List<StudentBusRegistrationDto>> getAll() {
	        List<StudentBusRegistrationDto> registrations = studentBusRegistrationService.getAll();
	        return ResponseEntity.ok(registrations);
	    }
}
