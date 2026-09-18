package com.web.sms.entity;

import com.web.sms.enums.InchargeStatus;
import com.web.sms.enums.TransferStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Transfer {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "student_id", nullable = false)
    private long studentId;

    @Column(name = "old_bus_id", nullable = false)
    private long oldBusId;

    @Column(name = "old_bus_incharge_status")
    private InchargeStatus oldBusInchargeStatus = InchargeStatus.pending;

    @Column(name = "new_bus_id", nullable = false)
    private long newBusId;

    @Column(name = "new_bus_incharge_status")
    private InchargeStatus newBusInchargeStatus = InchargeStatus.pending;

    @Column(name = "newboarding_point")
    private long newBoardingPoint;

    @Column(name = "status")
    private TransferStatus status = TransferStatus.incomplete;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public long getOldBusId() {
		return oldBusId;
	}

	public void setOldBusId(long oldBusId) {
		this.oldBusId = oldBusId;
	}

	public InchargeStatus getOldBusInchargeStatus() {
		return oldBusInchargeStatus;
	}

	public void setOldBusInchargeStatus(InchargeStatus oldBusInchargeStatus) {
		this.oldBusInchargeStatus = oldBusInchargeStatus;
	}

	public long getNewBusId() {
		return newBusId;
	}

	public void setNewBusId(long newBusId) {
		this.newBusId = newBusId;
	}

	public InchargeStatus getNewBusInchargeStatus() {
		return newBusInchargeStatus;
	}

	public void setNewBusInchargeStatus(InchargeStatus newBusInchargeStatus) {
		this.newBusInchargeStatus = newBusInchargeStatus;
	}

	public long getNewBoardingPoint() {
		return newBoardingPoint;
	}

	public void setNewBoardingPoint(long newBoardingPoint) {
		this.newBoardingPoint = newBoardingPoint;
	}

	public TransferStatus getStatus() {
		return status;
	}

	public void setStatus(TransferStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Transfer [id=" + id + ", studentId=" + studentId + ", oldBusId=" + oldBusId + ", oldBusInchargeStatus="
				+ oldBusInchargeStatus + ", newBusId=" + newBusId + ", newBusInchargeStatus=" + newBusInchargeStatus
				+ ", newBoardingPoint=" + newBoardingPoint + ", status=" + status + "]";
	}

	
    
}
