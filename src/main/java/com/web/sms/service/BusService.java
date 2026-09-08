package com.web.sms.service;

import java.util.List;

import com.web.sms.entity.Bus;

public interface BusService {

	public Bus addBus(Bus bus);
	public Bus updateBus(Bus bus);
	public Bus getBusById(long id);
	public void deleteBusById(long id);
	public List<Bus> getAllBuses();
}
