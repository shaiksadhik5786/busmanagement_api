package com.web.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.sms.entity.Incharge;
import com.web.sms.repository.InchargeRepository;

@Service
public class InchargeServiceImpl implements InchargeService {

	@Autowired
	private InchargeRepository inchargeRepository;

	@Override
	public Incharge addIncharge(Incharge incharge) {
		return inchargeRepository.save(incharge);
	}

	@Override
	public Incharge updateIncharge(Incharge incharge) {
		return inchargeRepository.save(incharge);
	}

	@Override
	public Incharge getInchargeById(long id) {
		return inchargeRepository.findById(id).get();
	}

	@Override
	public void deleteInchargeById(long id) {
		inchargeRepository.deleteById(id);
	}

	@Override
	public List<Incharge> getAllIncharges() {
		return inchargeRepository.findAll();
	}
	
}
