package com.web.sms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.sms.dto.StudentBusRegistrationDto;
import com.web.sms.entity.StudentBusRegistration;
import com.web.sms.exception.ResourceNotFoundException;
import com.web.sms.repository.BusRepository;
import com.web.sms.repository.StudentBusRegistrationRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentBusRegistrationServiceImpl implements StudentBusRegistrationService {

    @Autowired
    private StudentBusRegistrationRepository sbr;
    @Autowired
    private BusService busService;
    @Autowired
    private BusRepository busRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private BoardingPointsService boardingPointsService;

    public StudentBusRegistrationDto toDto(StudentBusRegistration entity) {
        if (entity == null) {
            return null;
        }

        StudentBusRegistrationDto dto = new StudentBusRegistrationDto();
        dto.setId(entity.getId());
        dto.setStudentDto(studentService.getStudentById(entity.getStudent().getId()));
        dto.setBusDto(busService.getBusById(entity.getBus().getId()));
        dto.setBoardingPointDto(boardingPointsService.getBoardingPointById(entity.getBoardingPoint().getId()));
        dto.setStatus(entity.getStatus());
        return dto;
    }

    @Override
    public StudentBusRegistration addStudentBusRegistration(StudentBusRegistration studentBusRegistration) {
        return sbr.save(studentBusRegistration);
    }

    @Override
    public StudentBusRegistration updateStudentBusRegistration(StudentBusRegistration studentBusRegistration) {
        StudentBusRegistration sb = sbr.findById(studentBusRegistration.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Registration not found with id: " + studentBusRegistration.getId()));

        if (studentBusRegistration.getBoardingPoint() != null) {
            sb.setBoardingPoint(studentBusRegistration.getBoardingPoint());
        }
        if (studentBusRegistration.getBus() != null) {
            boolean value = busService.allocateSeat(studentBusRegistration.getBus().getId());
            if (!value) {
                throw new IllegalArgumentException("Unable to allocate seat. No available seats on bus with id: " + studentBusRegistration.getBus().getId());
            }
            sb.setBus(busRepository.findById(studentBusRegistration.getBus().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Bus not found with id: " + studentBusRegistration.getBus().getId())));
        }
        return sbr.save(sb);
    }

    @Override
    public StudentBusRegistrationDto getById(long id) {
        StudentBusRegistration s = sbr.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registration not found with id: " + id));
        return toDto(s);
    }

    @Override
    public void deleteById(long id) {
        if (!sbr.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete. Registration not found with id: " + id);
        }
        sbr.deleteById(id);
    }

    @Override
    public List<StudentBusRegistrationDto> getAll() {
        List<StudentBusRegistration> studentBusRegistrations = sbr.findAll();
        if (studentBusRegistrations.isEmpty()) {
            throw new ResourceNotFoundException("No student bus registrations found.");
        }
        List<StudentBusRegistrationDto> dtos = new ArrayList<>();
        for (StudentBusRegistration studentBusRegistration : studentBusRegistrations) {
            dtos.add(toDto(studentBusRegistration));
        }
        return dtos;
    }

    @Override
    public void deleteByStudentId(long id) {
        sbr.deleteByStudentId(id);
    }

    @Transactional
    @Override
    public String approveStatus(long id) {
        StudentBusRegistration s = sbr.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registration not found with id: " + id));

        boolean status = busService.allocateSeat(s.getBus().getId());
        if (!status) {
            throw new IllegalArgumentException("Unable to allocate seat. No available seats on bus with id: " + s.getBus().getId());
        }
        s.setStatus(StudentBusRegistration.Status.APPROVED);
        sbr.save(s);
        return "Seat allocation successful";
    }

    @Override
    public String rejectStudent(long id) {
        StudentBusRegistration s = sbr.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registration not found with id: " + id));

        s.setStatus(StudentBusRegistration.Status.REJECTED);
        sbr.save(s);
        return "Student rejected successfully";
    }
}
