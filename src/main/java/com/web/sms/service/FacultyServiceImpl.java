package com.web.sms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.sms.dto.FacultyDto;
import com.web.sms.entity.Faculty;
import com.web.sms.repository.FacultyRepository;

@Service
public class FacultyServiceImpl implements FacultyService {

	@Autowired
	private FacultyRepository facultyRepository;
	@Autowired
	private BusService busService;
	@Autowired
	private BoardingPointsService boardingPointsService;
	
	    // Entity → DTO
	    public FacultyDto toDto(Faculty faculty) {
	        if (faculty == null) {
	            return null;
	        }

	        FacultyDto dto = new FacultyDto();
	        dto.setId(faculty.getId());
	        dto.setFacultyId(faculty.getFacultyId());
	        dto.setName(faculty.getName());
	        dto.setPhoneNumber(faculty.getPhoneNumber());
	        dto.setEmail(faculty.getEmail());
	        dto.setAddress(faculty.getAddress());
	        dto.setDepartment(faculty.getDepartment());
	        dto.setDesignation(faculty.getDesignation());
	        dto.setTemporaryInchargeStatus(faculty.getTemporaryInchargeStatus());

	        if (faculty.getFacultyBoardPoint() != null) {
	            dto.setBoardPointDto(boardingPointsService.getBoardingPointById(faculty.getFacultyBoardPoint().getId()));
	        }
	        if (faculty.getBus() != null) {
	            dto.setBusDto(busService.getBusById(faculty.getBus().getId()));
	        }

	        return dto;
	    }
	
	@Override
	public Faculty addFaculty(Faculty faculty) {
		return facultyRepository.save(faculty);
	}

	@Override
	public Faculty updateFaculty(Faculty faculty) {
		return facultyRepository.save(faculty);
	}

	@Override
	public FacultyDto getFacultyById(long id) {
		return toDto(facultyRepository.findById(id).get());
	}

	@Override
	public void deleteFacultyById(long id) {
		facultyRepository.deleteById(id);
	}

	@Override
	public List<FacultyDto> getAllFaculty() {
		List<Faculty> facultys = facultyRepository.findAll();
		List<FacultyDto> dtos = new ArrayList<>();
		for (Faculty faculty : facultys) {
			dtos.add(toDto(faculty));
		}
		
		return dtos;
		
	}

}
