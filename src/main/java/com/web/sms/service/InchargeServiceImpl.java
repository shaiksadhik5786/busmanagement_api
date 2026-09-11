package com.web.sms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.sms.dto.InchargeDto;
import com.web.sms.entity.Incharge;
import com.web.sms.exception.ResourceNotFoundException;
import com.web.sms.repository.BusRepository;
import com.web.sms.repository.InchargeRepository;

import jakarta.transaction.Transactional;

@Service
public class InchargeServiceImpl implements InchargeService {

    @Autowired
    private InchargeRepository inchargeRepository;

    @Autowired
    private BusRepository busRepository;

    // Convert Entity → DTO
    public static InchargeDto toDto(Incharge incharge) {
        InchargeDto dto = new InchargeDto();
        dto.setId(incharge.getId());
        dto.setTeacherId(incharge.getTeacherId());
        dto.setName(incharge.getName());
        dto.setPhoneNumber(incharge.getPhoneNumber());
        dto.setEmail(incharge.getEmail());
        dto.setAddress(incharge.getAddress());
        dto.setDepartment(incharge.getDepartment());
        dto.setDesignation(incharge.getDesignation());
        dto.setStatus(incharge.getStatus());
        return dto;
    }

    @Override
    public Incharge addIncharge(Incharge incharge) {
        return inchargeRepository.save(incharge);
    }

    @Override
    public Incharge updateIncharge(Incharge incharge) {
        Incharge existing = inchargeRepository.findById(incharge.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Incharge not found with id: " + incharge.getId()));

        if (incharge.getTeacherId() != null) existing.setTeacherId(incharge.getTeacherId());
        if (incharge.getName() != null) existing.setName(incharge.getName());
        if (incharge.getPhoneNumber() != null) existing.setPhoneNumber(incharge.getPhoneNumber());
        if (incharge.getEmail() != null) existing.setEmail(incharge.getEmail());
        if (incharge.getAddress() != null) existing.setAddress(incharge.getAddress());
        if (incharge.getDepartment() != null) existing.setDepartment(incharge.getDepartment());
        if (incharge.getDesignation() != null) existing.setDesignation(incharge.getDesignation());
        if (incharge.getStatus() != null) existing.setStatus(incharge.getStatus());

        return inchargeRepository.save(existing);
    }

    @Override
    public InchargeDto getInchargeById(long id) {
        Incharge incharge = inchargeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Incharge not found with id: " + id));
        return toDto(incharge);
    }

    @Transactional
    @Override
    public void deleteInchargeById(long id) {
        if (!inchargeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete. Incharge not found with id: " + id);
        }
        busRepository.clearIncharge(id);
        inchargeRepository.deleteById(id);
    }

    @Override
    public List<InchargeDto> getAllIncharges() {
        List<Incharge> incharges = inchargeRepository.findAll();
        if (incharges.isEmpty()) {
            throw new ResourceNotFoundException("No incharges found in the system.");
        }
        List<InchargeDto> dtos = new ArrayList<>();
        for (Incharge incharge : incharges) {
            dtos.add(toDto(incharge));
        }
        return dtos;
    }
}
