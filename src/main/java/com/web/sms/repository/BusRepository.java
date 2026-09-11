package com.web.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.sms.entity.Bus;

import jakarta.transaction.Transactional;

public interface BusRepository extends JpaRepository<Bus, Long> {

	@Modifying
    @Transactional
    @Query("UPDATE Bus b SET b.incharge = null WHERE b.incharge.id = :inchargeId")
    void clearIncharge(@Param("inchargeId") Long inchargeId);
}
