package com.web.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.sms.entity.BoardingPoints;
import com.web.sms.entity.Bus;

public interface BoardingPointsRepository extends JpaRepository<BoardingPoints, Long>{

	public List<BoardingPoints> findAllByBus(Bus bus);
	List<BoardingPoints> findAllByBus_Id(Long busId);
}
