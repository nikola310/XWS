package xmlweb.agent.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xmlweb.agent.model.AccomodationAgent;
import xmlweb.agent.repository.AccomodationAgentRepository;
import xmlweb.agent.service.interfaces.AccomodationAgentServiceInterface;

@Service
public class AccomodationAgentService implements AccomodationAgentServiceInterface {

	@Autowired
	private AccomodationAgentRepository repository;
	
	@Override
	public List<AccomodationAgent> readAll() {
		return repository.findAll();
	}

	@Override
	public AccomodationAgent getById(Long id) {
		Optional<AccomodationAgent> a = repository.findById(id);
		
		if(a.isPresent())
			return a.get();
		else
			return null;
	}

	@Override
	public AccomodationAgent getByAccomodationId(Long id) {
		List<AccomodationAgent> alist = repository.findAll();
		for(AccomodationAgent a : alist) {
			if(id.equals(a.getAccomodation().getId())) {
				return a;
			}
		}
		return null;
	}

	@Override
	public AccomodationAgent getByAgentId(Long id) {
		List<AccomodationAgent> alist = repository.findAll();
		for(AccomodationAgent a : alist) {
			if(id.equals(a.getAgent().getId())) {
				return a;
			}
		}
		return null;
	}

	@Override
	public boolean Create(AccomodationAgent aa) {
		try {
			repository.save(aa);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean Update(AccomodationAgent aa) {
		try {
			repository.save(aa);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
