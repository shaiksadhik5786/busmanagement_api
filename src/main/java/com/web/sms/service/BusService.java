package com.web.sms.service;

import java.util.List;

import com.web.sms.dto.BusDto;
import com.web.sms.entity.Bus;

public interface BusService {

	public Bus addBus(Bus bus);
	public Bus updateBus(Bus bus);
	public BusDto getBusById(long id);
	public void deleteBusById(long id);
	public List<BusDto> getAllBuses();
	public boolean deallocateSeat(long id);
	public boolean allocateSeat(long id);
}
