
package com.web.sms.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.sms.dto.User;
import com.web.sms.entity.Student;
import com.web.sms.repository.StudentRepository;
import com.web.sms.security.JwtUtil;
import com.web.sms.security.UserPrinciple;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            AuthenticationManager authenticationManager,
            JwtUtil jwtUtil,
            StudentRepository studentRepository,
            PasswordEncoder passwordEncoder) {

        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // STUDENT REGISTRATION
    @Transactional
    public Student registerStudent(Student student) {

        // Check duplicate student ID
        if (studentRepository.findByStudentId(student.getStudentId()) != null) {
            throw new RuntimeException("Student ID already exists");
        }

        // Check duplicate email
        if (studentRepository.findByEmail(student.getEmail()) != null) {
            throw new RuntimeException("Email already exists");
        }

        // Encode password before saving
        student.setPassword(passwordEncoder.encode(student.getPassword()));

        // Set default status
        student.setStatus("ACTIVE");

        return studentRepository.save(student);
    }

    // LOGIN
    public User login(User user) {

    	System.out.println(user + "------------ 1");
        Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                        		user.getUsername(),
                        		user.getPassword()
                        )
                );
        	System.out.println("1---1");
        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();
        System.out.println("1---1");
        String token = jwtUtil.generateToken(userPrincipal);
        System.out.println("1---1");
        User response = new User();

        response.setId(userPrincipal.getId());
        response.setUsername(userPrincipal.getUsername());
        response.setToken(token);
        	
        System.out.println(user + "------------ 2");
        
        return response;
    }
}