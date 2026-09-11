package com.web.sms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.sms.dto.BoardingPointsDto;
import com.web.sms.entity.BoardingPoints;
import com.web.sms.entity.Bus;
import com.web.sms.repository.BoardingPointsRepository;
import com.web.sms.repository.BusRepository;

import jakarta.transaction.Transactional;

@Service
public class BoardingPointsServiceImpl implements BoardingPointsService {

	@Autowired
    private BoardingPointsRepository boardingPointsRepository;
	@Autowired
	private BusRepository busRepository;
	@Autowired
	private BusService busService;

	public BoardingPointsDto toDto(BoardingPoints boardingPoints) {
        BoardingPointsDto dto = new BoardingPointsDto();
        dto.setId(boardingPoints.getId());
        dto.setStationName(boardingPoints.getStationName());
        dto.setFeeAmount(boardingPoints.getFeeAmount());

        
        dto.setBusDto(busService.getBusById(boardingPoints.getBus().getId()));

        return dto;
    }
	
    @Override
    @Transactional
    public BoardingPoints addBoardingPoints(BoardingPoints boardingPoints) {
    		Bus b = busRepository.findById(boardingPoints.getBus().getId()).get();
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
            	Bus b = busRepository.findById(boardingPoints.getBus().getId()).get();
                existing.setBus(b);
                
            }

            return boardingPointsRepository.save(existing);
    }

    @Override
    public BoardingPointsDto getBoardingPointById(long id) {
        BoardingPoints bp = boardingPointsRepository.findById(id).get();
        return toDto(bp);
    }

    @Override
    public void deleteBoardingPoint(long id) {
        boardingPointsRepository.deleteById(id);
    }

    @Override
    public List<BoardingPointsDto> getAllBoardingPoints() {
        List<BoardingPoints> boardingPoints = boardingPointsRepository.findAll();
        List<BoardingPointsDto> dtos = new ArrayList<>();
        for (BoardingPoints bp : boardingPoints) {
			dtos.add(toDto(bp));
		}
        return dtos;
    }
    
    @Override
    public List<BoardingPoints> getBoardingPointsByBusId(long id)
    {
    		return boardingPointsRepository.findAllByBus_Id(id);
    }

}
