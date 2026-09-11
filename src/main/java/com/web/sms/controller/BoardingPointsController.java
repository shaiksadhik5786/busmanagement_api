package com.web.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.sms.service.BoardingPointsService;
import com.web.sms.dto.BoardingPointsDto;
import com.web.sms.entity.BoardingPoints;

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/boarding-points")
public class BoardingPointsController {

	@Autowired
    private BoardingPointsService boardingPointsService;

    @PostMapping("/")
    public ResponseEntity<BoardingPoints> addBoardingPoints(@RequestBody BoardingPoints boardingPoints) {
        return ResponseEntity.ok(boardingPointsService.addBoardingPoints(boardingPoints));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardingPoints> updateBoardingPoints(@PathVariable("id") long id,
                                                               @RequestBody BoardingPoints boardingPoints) {
        return ResponseEntity.ok(boardingPointsService.updateBoardingPoints(boardingPoints));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardingPointsDto> getBoardingPointById(@PathVariable("id") long id) {
    	BoardingPointsDto boarding = boardingPointsService.getBoardingPointById(id);
        return ResponseEntity.ok(boarding);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoardingPoint(@PathVariable long id) {
        boardingPointsService.deleteBoardingPoint(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<BoardingPointsDto>> getAllBoardingPoints() {
        return ResponseEntity.ok(boardingPointsService.getAllBoardingPoints());
    }
    
    @GetMapping("/by-bus/{id}")
    public ResponseEntity<List<BoardingPoints>> getAllBusesByBusId(@PathVariable long id)
    {
    		List<BoardingPoints> bps = boardingPointsService.getBoardingPointsByBusId(id);
    		
    		return ResponseEntity.ok(bps);
    }
}
