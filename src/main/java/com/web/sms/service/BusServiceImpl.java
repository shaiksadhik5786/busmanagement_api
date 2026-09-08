package com.web.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.sms.entity.Bus;
import com.web.sms.repository.BusRepository;

@Service
public class BusServiceImpl implements BusService {

	@Autowired
	private BusRepository busRepository;
	
	@Override
	public Bus addBus(Bus bus) {
		return busRepository.save(bus);
	}

	@Override
	public Bus updateBus(Bus bus) {
		Bus existingBus = busRepository.findById(bus.getId()).get();

	        if (bus.getBusNumber() != null) {
	            existingBus.setBusNumber(bus.getBusNumber());
	        }
	        if (bus.getIncharge() != null) {
	            existingBus.setIncharge(bus.getIncharge());
	        }
	        if (bus.getStartingPoint() != null) {
	            existingBus.setStartingPoint(bus.getStartingPoint());
	        }
	        if (bus.getEndingPoint() != null) {
	            existingBus.setEndingPoint(bus.getEndingPoint());
	        }
	        if (bus.getTotalSeats() != null) {
	            existingBus.setTotalSeats(bus.getTotalSeats());
	        }
	        if (bus.getAvailableSeats() != null) {
	            existingBus.setAvailableSeats(bus.getAvailableSeats());
	        }

	        return busRepository.save(existingBus);
	}

	@Override
	public Bus getBusById(long id) {
		return busRepository.findById(id).get();
	}

	@Override
	public void deleteBusById(long id) {
		busRepository.deleteById(id);
	}

	@Override
	public List<Bus> getAllBuses() {
		return busRepository.findAll();
	}

}
