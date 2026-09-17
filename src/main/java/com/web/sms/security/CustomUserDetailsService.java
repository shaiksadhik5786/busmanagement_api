
package com.web.sms.security;

import com.web.sms.entity.Admin;
import com.web.sms.entity.Incharge;
import com.web.sms.entity.Student;
import com.web.sms.repository.AdminRepository;
import com.web.sms.repository.InchargeRepository;
import com.web.sms.repository.StudentRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final StudentRepository studentRepo;
    private final InchargeRepository inchargeRepo;
    private final AdminRepository adminRepo;

    public CustomUserDetailsService(
            StudentRepository studentRepo,
            InchargeRepository inchargeRepo,
            AdminRepository adminRepo) {

        this.studentRepo = studentRepo;
        this.inchargeRepo = inchargeRepo;
        this.adminRepo = adminRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String loginId)
            throws UsernameNotFoundException {

        if (loginId == null || loginId.trim().isEmpty()) {
            throw new UsernameNotFoundException(
                    "Login ID cannot be empty"
            );
        }

        String id = loginId.trim();

        // Check student
        Student student = studentRepo.findByStudentId(id);

        if (student == null) {
            student = studentRepo.findByStudentId(id.toUpperCase());
        }

        if (student != null) {
            return UserPrinciple.fromStudent(student);
        }

        // Check incharge
        Incharge incharge = inchargeRepo.findByTeacherId(id);

        if (incharge == null) {
            incharge = inchargeRepo.findByTeacherId(id.toUpperCase());
        }

        if (incharge != null) {
            return UserPrinciple.fromIncharge(incharge);
        }

        // Check admin
        Admin admin = adminRepo.findByAdminId(id);

        if (admin == null) {
            admin = adminRepo.findByAdminId(id.toLowerCase());
        }

        if (admin != null) {
            return UserPrinciple.fromAdmin(admin);
        }

        throw new UsernameNotFoundException(
                "User not found with loginId: " + loginId
        );
    }
}