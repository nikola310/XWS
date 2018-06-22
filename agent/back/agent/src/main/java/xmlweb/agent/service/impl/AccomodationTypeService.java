package xmlweb.agent.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xmlweb.agent.model.AccomodationType;
import xmlweb.agent.model.Location;
import xmlweb.agent.repository.AccomodationTypeRepository;
import xmlweb.agent.service.interfaces.AccomodationTypeServiceInterface;

@Service
public class AccomodationTypeService implements AccomodationTypeServiceInterface {

	@Autowired
	private AccomodationTypeRepository repository;
	
	@Override
	public List<AccomodationType> findAllAccomodationTypes() {
		return repository.findAll();
	}

	@Override
	public AccomodationType findOne(Long id) {
		Optional<AccomodationType> at = repository.findById(id);
		
		if(at.isPresent())
			return at.get();
		else
			return null;
	}

	@Override
	public boolean Create(AccomodationType dto) {
		try {
			repository.save(dto);
			return true;
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return false;
	}

	@Override
	public AccomodationType Read(long id) {
		return repository.getOne(id);
	}

	@Override
	public ArrayList<AccomodationType> ReadAll() {
		return (ArrayList<AccomodationType>)repository.findAll();
	}

	@Override
	public boolean Update(AccomodationType dto) {
		AccomodationType toUpdate = repository.getOne(dto.getId());

		try {
			if (toUpdate.getVersion() != dto.getVersion()) {
				return false;
			}
			toUpdate.setName(dto.getName());

			repository.save(toUpdate);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean Delete(long id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
