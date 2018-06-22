package xmlweb.projekat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xmlweb.projekat.model.BonusService;
import xmlweb.projekat.model.dtos.BonusDTO;
import xmlweb.projekat.model.dtos.BonusServiceDTO;
import xmlweb.projekat.repository.BonusServiceRepository;
import xmlweb.projekat.service.interfaces.BonusServiceInterface;

@Service
public class BonusServiceService implements BonusServiceInterface {

	@Autowired
	private BonusServiceRepository repository;

	@Override
	public List<BonusServiceDTO> findAllBonusServices() {
		// TODO Auto-generated method stub
		List<BonusService> bonusServices = repository.findAll();
		List<BonusServiceDTO> bonusServicesDTO = new ArrayList<BonusServiceDTO>();

		for (BonusService bs : bonusServices) {
			BonusServiceDTO bsDTO = new BonusServiceDTO(bs.getId(), bs.getName());
			bonusServicesDTO.add(bsDTO);
		}

		return bonusServicesDTO;
	}

	@Override
	public List<BonusDTO> forAdmin() {
		List<BonusService> entityList = repository.findAll();
		List<BonusDTO> dtoList = new ArrayList<BonusDTO>();

		for (BonusService bs : entityList) {
			BonusDTO bsDTO = new BonusDTO(bs.getId(), bs.getName(), bs.getVersion());
			dtoList.add(bsDTO);
		}

		return dtoList;
	}

	@Override
	public boolean edit(BonusDTO dto) {
		try {
			BonusService bs = repository.getOne(dto.getId());
			if (bs.getVersion() != dto.getVersion()) {
				return false;
			}
			bs.setName(dto.getName());
			repository.save(bs);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean create(String name) {
		try {
			BonusService bs = new BonusService();
			bs.setName(name);
			repository.save(bs);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(long id) {
		try {
			repository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
