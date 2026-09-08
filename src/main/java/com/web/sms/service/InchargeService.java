package com.web.sms.service;

import java.util.List;

import com.web.sms.entity.Incharge;
import com.web.sms.entity.Student;

public interface InchargeService {

	public Incharge addIncharge(Incharge incharge);
	public Incharge updateIncharge(Incharge incharge);
	public Incharge getInchargeById(long id);
	public void deleteInchargeById(long id);
	public List<Incharge> getAllIncharges();
}
