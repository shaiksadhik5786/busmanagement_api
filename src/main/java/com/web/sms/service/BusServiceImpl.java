package com.web.sms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.sms.dto.BusDto;
import com.web.sms.entity.Bus;
import com.web.sms.entity.Incharge;
import com.web.sms.exception.ResourceNotFoundException;
import com.web.sms.repository.BoardingPointsRepository;
import com.web.sms.repository.BusRepository;
import com.web.sms.repository.InchargeRepository;

import jakarta.transaction.Transactional;

@Service
public class BusServiceImpl implements BusService {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private InchargeRepository inchargeRepository;

    @Autowired
    private InchargeServiceImpl inchargeServiceImpl;
    
    @Autowired
    private BoardingPointsRepository bpr;
    
    // Convert Entity → DTO
    public BusDto toDto(Bus bus) {
        BusDto dto = new BusDto();
        dto.setId(bus.getId());
        dto.setBusNumber(bus.getBusNumber());
        dto.setStartingPoint(bus.getStartingPoint());
        dto.setEndingPoint(bus.getEndingPoint());
        dto.setTotalSeats(bus.getTotalSeats());
        dto.setAvailableSeats(bus.getAvailableSeats());

        if (bus.getIncharge() != null) {
            dto.setInchargeDto(inchargeServiceImpl.toDto(bus.getIncharge()));
        }

        return dto;
    }

    @Override
    public Bus addBus(Bus bus) {
        return busRepository.save(bus);
    }

    @Override
    public Bus updateBus(Bus bus) {
        Bus existingBus = busRepository.findById(bus.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found with id: " + bus.getId()));

        if (bus.getBusNumber() != null) {
            existingBus.setBusNumber(bus.getBusNumber());
        }
        if (bus.getIncharge() != null) {
            Incharge incharge = inchargeRepository.findById(bus.getIncharge().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Incharge not found with id: " + bus.getIncharge().getId()));
            existingBus.setIncharge(incharge);
        }
        if (bus.getStartingPoint() != null) {
            existingBus.setStartingPoint(bus.getStartingPoint());
        }
        if (bus.getEndingPoint() != null) {
            existingBus.setEndingPoint(bus.getEndingPoint());
        }
        if (bus.getTotalSeats() > 0) {
            existingBus.setTotalSeats(bus.getTotalSeats());
        }
        if (bus.getAvailableSeats() > 0) {
            existingBus.setAvailableSeats(bus.getAvailableSeats());
        }

        return busRepository.save(existingBus);
    }

    @Override
    public BusDto getBusById(long id) {
        Bus bus = busRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found with id: " + id));
        return toDto(bus);
    }

    @Override
    public void deleteBusById(long id) {
        if (!busRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete. Bus not found with id: " + id);
        }
        //List<BoardingPointsService> bps = bpr.
        busRepository.deleteById(id);
    }

    @Override
    public List<BusDto> getAllBuses() {
        List<Bus> buses = busRepository.findAll();
        if (buses.isEmpty()) {
            throw new ResourceNotFoundException("No buses found in the system.");
        }
        List<BusDto> dtos = new ArrayList<>();
        for (Bus bus : buses) {
            dtos.add(toDto(bus));
        }
        return dtos;
    }

    @Transactional
    @Override
    public boolean allocateSeat(long id) {
        Bus bus = busRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found with id: " + id));

        int availableSeats = bus.getAvailableSeats();
        if (availableSeats == 0) {
            throw new IllegalArgumentException("No available seats on bus with id: " + id);
        }

        bus.setAvailableSeats(availableSeats - 1);
        busRepository.save(bus);

        return true;
    }

    @Transactional
    @Override
    public boolean deallocateSeat(long id) {
        Bus bus = busRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found with id: " + id));

        int totalSeats = bus.getTotalSeats();
        int availableSeats = bus.getAvailableSeats();

        if (availableSeats == totalSeats) {
            throw new IllegalArgumentException("All seats are already free on bus with id: " + id);
        }

        bus.setAvailableSeats(availableSeats + 1);
        busRepository.save(bus);

        return true;
    }
}
