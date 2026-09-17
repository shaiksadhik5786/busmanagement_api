
package com.web.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.web.sms.dto.User;
import com.web.sms.entity.Student;
import com.web.sms.service.AuthService;

@RestController
@RequestMapping("/auth/user")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    // STUDENT REGISTRATION
    @PostMapping("/register")
    public ResponseEntity<Student> register(@RequestBody Student student) {

        Student savedStudent = authService.registerStudent(student);

        return ResponseEntity.ok(savedStudent);
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {

        User response = authService.login(user);

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/")
    public String get()
    {
    		return "the Busmanagement project is on live!";
    }
}