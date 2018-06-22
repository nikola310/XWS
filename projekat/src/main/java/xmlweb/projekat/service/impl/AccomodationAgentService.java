package xmlweb.projekat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xmlweb.projekat.model.AccomodationAgent;
import xmlweb.projekat.repository.AccomodationAgentRepository;
import xmlweb.projekat.service.interfaces.AccomodationAgentServiceInterface;

@Service
public class AccomodationAgentService implements AccomodationAgentServiceInterface {

	private AccomodationAgentRepository accomodationAgentRepo;
	
	@Autowired
	public AccomodationAgentService(AccomodationAgentRepository accomodationAgentRepo) {
		super();
		this.accomodationAgentRepo = accomodationAgentRepo;
	}
	
	@Override
	public List<AccomodationAgent> findAllAccomodationAgent() {
		return accomodationAgentRepo.findAll();
	}

	
}
