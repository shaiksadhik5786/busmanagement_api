package com.web.sms.dto;

import com.web.sms.entity.StudentBusRegistration.Status;

public class StudentBusRegistrationDto {

    private long id;
    private StudentDto studentDto;
    private BusDto busDto;
    private BoardingPointsDto boardingPointDto;
    private Status status;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public StudentDto getStudentDto() {
		return studentDto;
	}
	public void setStudentDto(StudentDto studentDto) {
		this.studentDto = studentDto;
	}
	public BusDto getBusDto() {
		return busDto;
	}
	public void setBusDto(BusDto busDto) {
		this.busDto = busDto;
	}
	public BoardingPointsDto getBoardingPointDto() {
		return boardingPointDto;
	}
	public void setBoardingPointDto(BoardingPointsDto boardingPointDto) {
		this.boardingPointDto = boardingPointDto;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
    
}
