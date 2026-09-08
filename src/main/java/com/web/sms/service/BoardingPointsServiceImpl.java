package com.web.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.sms.entity.BoardingPoints;
import com.web.sms.entity.Bus;
import com.web.sms.repository.BoardingPointsRepository;

import jakarta.transaction.Transactional;

@Service
public class BoardingPointsServiceImpl implements BoardingPointsService {

	@Autowired
    private BoardingPointsRepository boardingPointsRepository;
	@Autowired
	private BusService busService;

    @Override
    @Transactional
    public BoardingPoints addBoardingPoints(BoardingPoints boardingPoints) {
    		Bus b = busService.getBusById(boardingPoints.getBus().getId());
    		boardingPoints.setBus(b);
        return boardingPointsRepository.save(boardingPoints);
    }

    @Override
    @Transactional
    public BoardingPoints updateBoardingPoints(BoardingPoints boardingPoints) {
        BoardingPoints existing = boardingPointsRepository.findById(boardingPoints.getId()).get();

            if (boardingPoints.getStationName() != null) {
                existing.setStationName(boardingPoints.getStationName());
            }
            if (boardingPoints.getFeeAmount() != null) {
                existing.setFeeAmount(boardingPoints.getFeeAmount());
            }
            if (boardingPoints.getBus() != null) {
            		Bus b = busService.updateBus(boardingPoints.getBus());
                existing.setBus(b);
                
            }

            return boardingPointsRepository.save(existing);
    }

    @Override
    public BoardingPoints getBoardingPointById(long id) {
        return boardingPointsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BoardingPoint not found"));
    }

    @Override
    public void deleteBoardingPoint(long id) {
        boardingPointsRepository.deleteById(id);
    }

    @Override
    public List<BoardingPoints> getAllBoardingPoints() {
        return boardingPointsRepository.findAll();
    }

}
