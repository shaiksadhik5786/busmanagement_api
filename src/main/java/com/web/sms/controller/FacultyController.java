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

import com.web.sms.dto.FacultyDto;
import com.web.sms.entity.Faculty;
import com.web.sms.service.FacultyService;

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/faculty")
public class FacultyController {

	    @Autowired
	    private FacultyService facultyService;

	    @PostMapping("/")
	    public ResponseEntity<Faculty> addFaculty(@RequestBody Faculty faculty) {
	        Faculty savedFaculty = facultyService.addFaculty(faculty);
	        return ResponseEntity.ok(savedFaculty);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Faculty> updateFaculty(@PathVariable long id, @RequestBody Faculty faculty) {
	        faculty.setId(id); // ensure ID is set before update
	        Faculty updatedFaculty = facultyService.updateFaculty(faculty);
	        return ResponseEntity.ok(updatedFaculty);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<FacultyDto> getFacultyById(@PathVariable("id") long id) {
	        FacultyDto faculty = facultyService.getFacultyById(id);
	        return ResponseEntity.ok(faculty);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<String> deleteFacultyById(@PathVariable("id") long id) {
	        facultyService.deleteFacultyById(id);
	        return ResponseEntity.ok("Faculty deleted successfully with id: " + id);
	    }

	    @GetMapping("/")
	    public ResponseEntity<List<FacultyDto>> getAllFaculty() {
	        List<FacultyDto> facultyList = facultyService.getAllFaculty();
	        return ResponseEntity.ok(facultyList);
	    }
}
