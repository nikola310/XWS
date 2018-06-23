package xmlweb.agent.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xmlweb.agent.model.Accomodation;
import xmlweb.agent.model.Price;
import xmlweb.agent.model.dtos.PriceDTO;
import xmlweb.agent.repository.AAAccomodationRepository;
import xmlweb.agent.repository.AAPriceRepository;
import xmlweb.agent.service.interfaces.AAPriceServiceInterface;

@Service
public class AAPriceService implements AAPriceServiceInterface{

	@Autowired
	private AAAccomodationRepository accRepository;
	
	@Autowired
	private AAPriceRepository repository;
	
	@Override
	public boolean addPrice(PriceDTO price) {
		// TODO Auto-generated method stub
		try{
			ModelMapper mapper = new ModelMapper();
			Price p = mapper.map(price, Price.class);
			Accomodation a = accRepository.findById(price.getAccomodation());
			p.setAccomodation(a);
			
			repository.save(p);
			return true;
		} catch (Exception exc) {
			exc.printStackTrace();
		};
		return false;
		
	}

}
