package com.web.sms.dto;

import com.web.sms.entity.BoardingPoints;
import com.web.sms.entity.Bus;
import com.web.sms.entity.Faculty.TemporaryInchargeStatus;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class FacultyDto {

    private long id;
    private String facultyId;
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private BoardingPointsDto BoardPointDto;
    private String department;
    private String designation;
    private BusDto busDto;
    private TemporaryInchargeStatus temporaryInchargeStatus;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public BoardingPointsDto getBoardPointDto() {
		return BoardPointDto;
	}
	public void setBoardPointDto(BoardingPointsDto boardPointDto) {
		BoardPointDto = boardPointDto;
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
	public BusDto getBusDto() {
		return busDto;
	}
	public void setBusDto(BusDto busDto) {
		this.busDto = busDto;
	}
	public TemporaryInchargeStatus getTemporaryInchargeStatus() {
		return temporaryInchargeStatus;
	}
	public void setTemporaryInchargeStatus(TemporaryInchargeStatus temporaryInchargeStatus) {
		this.temporaryInchargeStatus = temporaryInchargeStatus;
	}
    
    

}
