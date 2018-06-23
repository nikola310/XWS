package xmlweb.agent.service.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xmlweb.agent.model.Picture;
import xmlweb.agent.repository.AccomodationRepository;
import xmlweb.agent.repository.PictureRepository;
import xmlweb.agent.service.interfaces.PictureServiceInterface;

@Service
public class PictureService implements PictureServiceInterface {

	@Autowired
	private PictureRepository repository;

	@Autowired
	private AccomodationRepository accomodationRepo;

	@Override
	public ArrayList<Picture> readAll() {
		return (ArrayList<Picture>) repository.findAll();
	}

	@Override
	public Picture readOne(Long id) {
		Optional<Picture> p = repository.findById(id);
		if(p.isPresent()) {
			return p.get();
		} else {
			return null;
		}
	}

	@Override
	public boolean Create(Picture p) {
		try {
			System.out.println("ID ADADDDMOE +" + p.getAccomodation().getId());
			repository.saveAndFlush(p);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean Update(Picture p) {
		try {
			if(!repository.findById(p.getId()).isPresent())
				return false;
			repository.save(p);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	
	

}
