package com.web.sms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.sms.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

	// Basic finders
    Student findByStudentId(String studentId);
    Optional<Student> findByEmail(String email);
    Optional<Student> findByPhoneNumber(String phoneNumber);

    // Branch and year/semester
    List<Student> findByBranch(String branch);
    List<Student> findByBranchAndYear(String branch, Integer year);
    List<Student> findByBranchAndSemester(String branch, Integer semester);
    List<Student> findByBranchAndYearAndSemester(String branch, Integer year, Integer semester);

    // Name-based queries
    List<Student> findByName(String name);
    List<Student> findByNameStartingWith(String prefix);

    // Gender and blood group
    List<Student> findByGender(String gender);
    List<Student> findByBloodGroup(String bloodGroup);

    // Status
    List<Student> findByStatus(String status);

    // Combined queries
    List<Student> findByBranchAndGender(String branch, String gender);
    List<Student> findByBranchAndBloodGroup(String branch, String bloodGroup);
    List<Student> findByYearAndStatus(Integer year, String status);
    List<Student> findBySemesterAndStatus(Integer semester, String status);

    // Address queries
    List<Student> findByAddress(String address);
}
