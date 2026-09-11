package com.web.sms.dto;


public class BoardingPointsDto {

    private long id;
    private BusDto busDto;
    private String stationName;
    private long feeAmount;
    
    public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public BusDto getBusDto() {
		return busDto;
	}
	public void setBusDto(BusDto busDto) {
		this.busDto = busDto;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public long getFeeAmount() {
		return feeAmount;
	}
	public void setFeeAmount(long feeAmount) {
		this.feeAmount = feeAmount;
	}
	
	
    
}
