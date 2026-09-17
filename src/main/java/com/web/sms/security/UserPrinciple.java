package com.web.sms.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.web.sms.entity.Admin;
import com.web.sms.entity.Incharge;
import com.web.sms.entity.Student;
import com.web.sms.enums.Role;

public class UserPrinciple implements UserDetails {

    private Long id;
    private String loginId;
    private String password;
    private Role role;
    private String name;
    private String status;

    // Constructor
    public UserPrinciple(
            Long id,
            String loginId,
            String password,
            Role role,
            String name,
            String status) {

        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.role = role;
        this.name = name;
        this.status = status;
    }

    // Create UserPrinciple from Student
    public static UserPrinciple fromStudent(Student s) {

        return new UserPrinciple(
                s.getId(),
                s.getStudentId(),
                s.getPassword(),
                Role.STUDENT,
                s.getName(),
                s.getStatus()
        );
    }

    // Create UserPrinciple from Incharge
    public static UserPrinciple fromIncharge(Incharge i) {

        return new UserPrinciple(
                i.getId(),
                i.getTeacherId(),
                i.getPassword(),
                Role.INCHARGE,
                i.getName(),
                i.getStatus()
        );
    }

    // Create UserPrinciple from Admin
    public static UserPrinciple fromAdmin(Admin a) {

        return new UserPrinciple(
                a.getId(),
                a.getAdminId(),
                a.getPassword(),
                Role.ADMIN,
                a.getName(),
                a.getStatus()
        );
    }

    // Return user role
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    // Return password
    @Override
    public String getPassword() {
        return password;
    }

    // Return login ID
    @Override
    public String getUsername() {
        return loginId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status == null ||
               "ACTIVE".equalsIgnoreCase(status);
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getLoginId() {
        return loginId;
    }

    public Role getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }
}
