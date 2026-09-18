
package com.web.sms.service;

import com.web.sms.entity.BoardingPoints;
import com.web.sms.entity.StudentBusRegistration;
import com.web.sms.entity.Transfer;
import com.web.sms.repository.BoardingPointsRepository;
import com.web.sms.repository.StudentBusRegistrationRepository;
import com.web.sms.repository.TransferRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;
    @Autowired
    private StudentBusRegistrationRepository sbr;
    @Autowired
    private BoardingPointsRepository bpr;
    @Autowired
    private BusService busService;

    public TransferServiceImpl(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @Override
    public Transfer createTransfer(Transfer transfer) {
        return transferRepository.save(transfer);
    }

    @Override
    public Transfer getTransferById(long id) {
        return transferRepository.findById(id).get();
           
    }

    @Override
    public List<Transfer> getAllTransfers() {
        return transferRepository.findAll();
    }

    @Override
    public List<Transfer> getTransfersByStudentId(long studentId) {
        return transferRepository.findByStudentId(studentId);
    }

    @Override
    public List<Transfer> getTransfersByOldBusId(long oldBusId) {
        return transferRepository.findByOldBusId(oldBusId);
    }

    @Override
    public List<Transfer> getTransfersByNewBusId(long newBusId) {
        return transferRepository.findByNewBusId(newBusId);
    }

    @Override
    public Transfer updateTransfer(long id, Transfer transfer) {

    	Transfer existingTransfer = getTransferById(id);

        if (transfer.getStudentId() > 0) {
            existingTransfer.setStudentId(transfer.getStudentId());
        }
        if (transfer.getOldBusId() > 0) {
            existingTransfer.setOldBusId(transfer.getOldBusId());
        }
        if (transfer.getOldBusInchargeStatus() != null) {
            existingTransfer.setOldBusInchargeStatus(transfer.getOldBusInchargeStatus());
        }
        if (transfer.getNewBusId() > 0) {
            existingTransfer.setNewBusId(transfer.getNewBusId());
        }
        if (transfer.getNewBusInchargeStatus() != null) {
            existingTransfer.setNewBusInchargeStatus(transfer.getNewBusInchargeStatus());
        }
        if (transfer.getNewBoardingPoint() > 0) {
            existingTransfer.setNewBoardingPoint(transfer.getNewBoardingPoint());
        }
        if (transfer.getStatus() != null) {
            existingTransfer.setStatus(transfer.getStatus());
        }

        return transferRepository.save(existingTransfer);
    }

    @Override
    public void deleteTransfer(long id) {
        Transfer existingTransfer = getTransferById(id);
        transferRepository.delete(existingTransfer);
    }
    
    
    @Transactional
    public Transfer transferSucess(Transfer transfer)
    {
    		StudentBusRegistration std = sbr.findByStudentId(transfer.getStudentId());
    		BoardingPoints b = bpr.findById(transfer.getNewBoardingPoint()).get();
    		std.setBoardingPoint(b);
    		sbr.save(std);
    		busService.allocateSeat(transfer.getNewBusId());
    		busService.deallocateSeat(transfer.getOldBusId());
    		return transfer;
    }
}