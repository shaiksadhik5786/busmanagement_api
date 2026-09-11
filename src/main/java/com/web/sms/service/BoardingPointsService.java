package com.web.sms.service;

import java.util.List;

import com.web.sms.dto.BoardingPointsDto;
import com.web.sms.entity.BoardingPoints;

public interface BoardingPointsService {

	public BoardingPoints addBoardingPoints(BoardingPoints boardingPoints);
	public BoardingPoints updateBoardingPoints(BoardingPoints boardingPoints);
	public BoardingPointsDto getBoardingPointById(long id);
	public void deleteBoardingPoint(long id);
	public List<BoardingPointsDto> getAllBoardingPoints();
	public List<BoardingPoints> getBoardingPointsByBusId(long id);
}
