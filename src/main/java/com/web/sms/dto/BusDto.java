package com.web.sms.dto;

public class BusDto {

    private long id;
    private String busNumber;
    private InchargeDto inchargeDto;
    private String startingPoint;
    private String endingPoint;
    private Integer totalSeats;
    private Integer availableSeats;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBusNumber() {
		return busNumber;
	}
	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}
	public InchargeDto getInchargeDto() {
		return inchargeDto;
	}
	public void setInchargeDto(InchargeDto inchargeDto) {
		this.inchargeDto = inchargeDto;
	}
	public String getStartingPoint() {
		return startingPoint;
	}
	public void setStartingPoint(String startingPoint) {
		this.startingPoint = startingPoint;
	}
	public String getEndingPoint() {
		return endingPoint;
	}
	public void setEndingPoint(String endingPoint) {
		this.endingPoint = endingPoint;
	}
	public Integer getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(Integer totalSeats) {
		this.totalSeats = totalSeats;
	}
	public Integer getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}
	
    
}
