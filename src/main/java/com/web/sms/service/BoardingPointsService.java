package com.web.sms.service;

import java.util.List;

import com.web.sms.entity.BoardingPoints;

public interface BoardingPointsService {

	public BoardingPoints addBoardingPoints(BoardingPoints boardingPoints);
	public BoardingPoints updateBoardingPoints(BoardingPoints boardingPoints);
	public BoardingPoints getBoardingPointById(long id);
	public void deleteBoardingPoint(long id);
	public List<BoardingPoints> getAllBoardingPoints();
}
