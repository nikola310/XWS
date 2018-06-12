package xmlweb.projekat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xmlweb.projekat.model.BonusService;
import xmlweb.projekat.model.dtos.BonusServiceDTO;
import xmlweb.projekat.repository.BonusServiceRepository;
import xmlweb.projekat.service.interfaces.BonusServiceInterface;

@Service
public class BonusServiceService implements BonusServiceInterface{

	@Autowired
	private BonusServiceRepository repository;
	
	@Override
	public List<BonusServiceDTO> findAllBonusServices() {
		// TODO Auto-generated method stub
		List<BonusService> bonusServices = repository.findAll();
		List<BonusServiceDTO> bonusServicesDTO = new ArrayList<BonusServiceDTO>();
		
		for(BonusService bs : bonusServices){
			BonusServiceDTO bsDTO = new BonusServiceDTO(bs.getId(), bs.getName());
			bonusServicesDTO.add(bsDTO);
		}
		
		return bonusServicesDTO;
	}
	
	
}
