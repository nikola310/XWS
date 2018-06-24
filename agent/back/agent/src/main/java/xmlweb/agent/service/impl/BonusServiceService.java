package xmlweb.agent.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xmlweb.agent.model.BonusService;
import xmlweb.agent.repository.BonusServiceRepository;
import xmlweb.agent.service.interfaces.BonusServiceInterface;

@Service
public class BonusServiceService implements BonusServiceInterface{

	@Autowired
	private BonusServiceRepository repository;

	@Override
	public List<BonusService> findAllBonusServices() {
		return repository.findAll();
	}

	@Override
	public BonusService findByName(String bonusName) {
		List<BonusService> list = repository.findAll();
		for(BonusService b : list) {
			if(b.getName().equals(bonusName))
				return b;
		}
		return null;
	}

	@Override
	public boolean Create(BonusService b) {
		try {
			repository.save(b);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean Update(BonusService b) {
		try {
			if(repository.findById(b.getId()) == null)
				return false;
			repository.save(b);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public BonusService findById(long id) {
		Optional<BonusService> b = repository.findById(id);
		if(b.isPresent())
			return b.get();
		else
			return null;
	}
	
	
	
}
