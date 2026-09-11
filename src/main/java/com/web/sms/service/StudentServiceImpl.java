package com.web.sms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.sms.dto.StudentDto;
import com.web.sms.entity.Student;
import com.web.sms.exception.ResourceNotFoundException;
import com.web.sms.repository.StudentBusRegistrationRepository;
import com.web.sms.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentBusRegistrationRepository sbr;

    // Convert Entity → DTO
    public static StudentDto toDto(Student student) {
        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setStudentId(student.getStudentId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setPhoneNumber(student.getPhoneNumber());
        dto.setDob(student.getDob());
        dto.setGender(student.getGender());
        dto.setAddress(student.getAddress());
        dto.setBranch(student.getBranch());
        dto.setYear(student.getYear());
        dto.setSemester(student.getSemester());
        dto.setBloodGroup(student.getBloodGroup());
        dto.setStatus(student.getStatus());
        dto.setParentPhoneNumber(student.getParentPhoneNumber());
        return dto;
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student) {
        Student existing = studentRepository.findById(student.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + student.getId()));

        if (student.getStudentId() != null) existing.setStudentId(student.getStudentId());
        if (student.getName() != null) existing.setName(student.getName());
        if (student.getEmail() != null) existing.setEmail(student.getEmail());
        if (student.getPhoneNumber() != null) existing.setPhoneNumber(student.getPhoneNumber());
        if (student.getDob() != null) existing.setDob(student.getDob());
        if (student.getGender() != null) existing.setGender(student.getGender());
        if (student.getAddress() != null) existing.setAddress(student.getAddress());
        if (student.getPassword() != null) existing.setPassword(student.getPassword());
        if (student.getBranch() != null) existing.setBranch(student.getBranch());
        if (student.getYear() != 0) existing.setYear(student.getYear());
        if (student.getSemester() != 0) existing.setSemester(student.getSemester());
        if (student.getBloodGroup() != null) existing.setBloodGroup(student.getBloodGroup());
        if (student.getStatus() != null) existing.setStatus(student.getStatus());
        if (student.getParentPhoneNumber() != null) existing.setParentPhoneNumber(student.getParentPhoneNumber());

        return studentRepository.save(existing);
    }

    @Override
    public StudentDto getStudentById(long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        return toDto(student);
    }

    @Transactional
    @Override
    public void deleteStudentById(long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete. Student not found with id: " + id);
        }
        sbr.deleteByStudentId(id);
        studentRepository.deleteById(id);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty()) {
            throw new ResourceNotFoundException("No students found in the system.");
        }
        List<StudentDto> std = new ArrayList<>();
        for (Student student : students) {
            std.add(toDto(student));
        }
        return std;
    }
}
