package com.web.sms.service;

import java.util.List;

import com.web.sms.dto.InchargeDto;
import com.web.sms.entity.Incharge;

public interface InchargeService {

	public Incharge addIncharge(Incharge incharge);
	public Incharge updateIncharge(Incharge incharge);
	public InchargeDto getInchargeById(long id);
	public void deleteInchargeById(long id);
	public List<InchargeDto> getAllIncharges();
}
