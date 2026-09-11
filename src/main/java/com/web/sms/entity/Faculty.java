package com.web.sms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Faculty {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	@Column(name = "teacher_id")
    private String facultyId;
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private String address;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_board_point")
    private BoardingPoints facultyBoardPoint;
    private String department;
    private String designation;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_id")
    private Bus bus;
    @Enumerated(EnumType.STRING)
    @Column(name = "temporary_incharge_status", 
            columnDefinition = "ENUM('inactive','active') DEFAULT 'inactive'")
    private TemporaryInchargeStatus temporaryInchargeStatus = TemporaryInchargeStatus.inactive;

    public enum TemporaryInchargeStatus {
        inactive,
        active
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(String facultyId) {
		this.facultyId = facultyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BoardingPoints getFacultyBoardPoint() {
		return facultyBoardPoint;
	}

	public void setFacultyBoardPoint(BoardingPoints facultyBoardPoint) {
		this.facultyBoardPoint = facultyBoardPoint;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public TemporaryInchargeStatus getTemporaryInchargeStatus() {
		return temporaryInchargeStatus;
	}

	public void setTemporaryInchargeStatus(TemporaryInchargeStatus temporaryInchargeStatus) {
		this.temporaryInchargeStatus = temporaryInchargeStatus;
	}
    
    
}
