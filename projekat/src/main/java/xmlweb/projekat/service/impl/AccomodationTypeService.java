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

	@Override
	public boolean Create(String name) {
		try {
			AccomodationType acc = new AccomodationType();
			acc.setName(name);

			repository.save(acc);

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean Update(AccomodationType acc) {
		try {
			AccomodationType toUpdate = repository.getOne(acc.getId());
			if (toUpdate.getVersion() != acc.getVersion()) {
				return false;
			}
			toUpdate.setName(acc.getName());
			repository.save(toUpdate);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean Delete(long id) {
		try {
			repository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
