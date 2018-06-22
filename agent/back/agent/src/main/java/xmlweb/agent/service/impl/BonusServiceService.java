package xmlweb.agent.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xmlweb.agent.model.Accomodation;
import xmlweb.agent.model.BonusService;
import xmlweb.agent.model.dtos.BonusServiceDTO;
import xmlweb.agent.repository.BonusServiceRepository;
import xmlweb.agent.service.interfaces.BonusServiceInterface;

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
			BonusServiceDTO bsDTO = new BonusServiceDTO(bs.getId(), bs.getName(), bs.getVersion());
			bonusServicesDTO.add(bsDTO);
		}
		
		return bonusServicesDTO;
	}

	@Override
	public boolean Create(BonusServiceDTO dto) {
		BonusService bs = new BonusService();
		bs.setId(dto.getId());
		bs.setName(dto.getName());
		bs.setVersion(dto.getVersion());
		ArrayList<Accomodation> a = new ArrayList<Accomodation>();
		bs.setAccomodations(a);
		
		repository.save(bs);
		return false;
	}

	@Override
	public BonusServiceDTO Read(long id) {
		Optional<BonusService>  bs = repository.findById(id);
		if(bs.isPresent()) {
			BonusServiceDTO b = new BonusServiceDTO();
			b.setId(bs.get().getId());
			b.setName(bs.get().getName());
			b.setVersion(bs.get().getVersion());
			return b;
		}
		return null;	
	}

	@Override
	public ArrayList<BonusServiceDTO> ReadAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Update(BonusServiceDTO dto) {
		
		Optional<BonusService> bs = repository.findById(dto.getId());
		if(bs.isPresent()) {
			bs.get().setId(dto.getId());
			bs.get().setName(dto.getName());
			bs.get().setVersion(dto.getVersion());
			
			repository.save(bs.get());
			return true;
		}
		return false;
	}

	@Override
	public boolean Delete(long id) {
		try {
			repository.deleteById(id);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
}
