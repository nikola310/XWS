package xmlweb.agent.service.impl;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xmlweb.agent.model.Location;
import xmlweb.agent.model.dtos.LocationDTO;
import xmlweb.agent.repository.AALocationRepository;
import xmlweb.agent.service.interfaces.AALocationServiceInterface;

@Service
@Transactional
public class AALocationService implements AALocationServiceInterface{
	
	@Autowired
	private AALocationRepository repository;
	
	@Override
	public long createLoc(LocationDTO dto) {
		try {
			ModelMapper mapper = new ModelMapper();
			Location loc = mapper.map(dto, Location.class);
			repository.save(loc);
			return loc.getId();

			
		} catch (Exception exc) {
			exc.printStackTrace();
		};
		return -1;
		
		
	}

}
