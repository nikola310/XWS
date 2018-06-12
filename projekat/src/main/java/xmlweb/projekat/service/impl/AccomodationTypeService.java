package xmlweb.projekat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xmlweb.projekat.model.AccomodationType;
import xmlweb.projekat.repository.AccomodationTypeRepository;
import xmlweb.projekat.service.interfaces.AccomodationTypeServiceInterface;

@Service
public class AccomodationTypeService implements AccomodationTypeServiceInterface {

	@Autowired
	private AccomodationTypeRepository repository;
	
	@Override
	public List<AccomodationType> findAllAccomodationTypes() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

}
