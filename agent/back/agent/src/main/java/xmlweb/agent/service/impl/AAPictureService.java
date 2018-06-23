package xmlweb.agent.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xmlweb.agent.model.Accomodation;
import xmlweb.agent.model.Picture;
import xmlweb.agent.model.dtos.PictureDTO;
import xmlweb.agent.repository.AAAccomodationRepository;
import xmlweb.agent.repository.AAPictureRepository;
import xmlweb.agent.service.interfaces.AAPictureServiceInterface;

@Service
public class AAPictureService implements AAPictureServiceInterface {
	
	@Autowired
	private AAPictureRepository repository;
	
	@Autowired
	private AAAccomodationRepository accRepository;
	
	@Override
	public boolean addPicture(PictureDTO p) {
		// TODO Auto-generated method stub
		try {
			ModelMapper mapper = new ModelMapper();
			Picture pic = mapper.map(p, Picture.class);
			Accomodation a = accRepository.findById(p.getAccomodation());
			pic.setAccomodation(a);
			repository.save(pic);
			return true;
			
		} catch (Exception exc) {
			exc.printStackTrace();
		};
		return false;
		
	}
	
	
}
