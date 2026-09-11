package com.web.sms.service;

import java.util.List;

import com.web.sms.dto.FacultyDto;
import com.web.sms.entity.Faculty;

public interface FacultyService {

	public Faculty addFaculty(Faculty faculty);
	public Faculty updateFaculty(Faculty faculty);
	public FacultyDto getFacultyById(long id);
	public void deleteFacultyById(long id);
	List<FacultyDto> getAllFaculty();
}
