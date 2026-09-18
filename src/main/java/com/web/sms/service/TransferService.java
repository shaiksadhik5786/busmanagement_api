package com.web.sms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.web.sms.entity.Transfer;

@Service
public interface TransferService {

	Transfer createTransfer(Transfer transfer);

    Transfer getTransferById(long id);

    List<Transfer> getAllTransfers();

    List<Transfer> getTransfersByStudentId(long studentId);

    List<Transfer> getTransfersByOldBusId(long oldBusId);

    List<Transfer> getTransfersByNewBusId(long newBusId);

    Transfer updateTransfer(long id, Transfer transfer);

    void deleteTransfer(long id);
}
