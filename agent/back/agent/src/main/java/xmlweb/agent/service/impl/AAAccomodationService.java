package xmlweb.agent.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xmlweb.agent.model.Accomodation;
import xmlweb.agent.model.AccomodationType;
import xmlweb.agent.model.Location;
import xmlweb.agent.model.dtos.AAAccomodationDTO;
import xmlweb.agent.repository.AAAccomodationRepository;
import xmlweb.agent.repository.AALocationRepository;
import xmlweb.agent.repository.AccomodationTypeRepository;
import xmlweb.agent.service.interfaces.AAAccomodationServiceInterface;

@Service
public class AAAccomodationService implements AAAccomodationServiceInterface{
	
	@Autowired
	private AAAccomodationRepository repository;
	
	@Autowired
	private AccomodationTypeRepository typeRepo;
	
	@Autowired
	private AALocationRepository locationRepo;
	
	@Override
	public long createAccomodation(AAAccomodationDTO a) {
		// TODO Auto-generated method stub
		try {
			
			ModelMapper mapper = new ModelMapper();
			Accomodation acc = mapper.map(a, Accomodation.class);
			AccomodationType type = typeRepo.getOne(a.getType());
			System.out.println("================");
			Location loc = locationRepo.findById(a.getLocation());
			//Accomodation acc = accRepo.getOne(dto.getAccomodation());
			acc.setAccomodationType(type);
			acc.setLocation(loc);
			
			repository.save(acc);
			return acc.getId();
			
		} catch (Exception exc) {
			exc.printStackTrace();
		};
		return -1;
	}




	

}
