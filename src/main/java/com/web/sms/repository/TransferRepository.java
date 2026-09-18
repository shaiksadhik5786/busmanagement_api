package com.web.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.sms.entity.Transfer;
import com.web.sms.enums.InchargeStatus;
import com.web.sms.enums.TransferStatus;

public interface TransferRepository extends JpaRepository<Transfer, Long>{

	List<Transfer> findByStudentId(long studentId);

    List<Transfer> findByOldBusId(long oldBusId);

    List<Transfer> findByNewBusId(long newBusId);

    List<Transfer> findByStatus(TransferStatus status);

    List<Transfer> findByOldBusInchargeStatus(InchargeStatus status);

    List<Transfer> findByNewBusInchargeStatus(InchargeStatus status);
    
}
