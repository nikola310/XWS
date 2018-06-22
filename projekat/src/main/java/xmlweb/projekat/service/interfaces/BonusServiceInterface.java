package xmlweb.projekat.service.interfaces;

import java.util.List;

import xmlweb.projekat.model.dtos.BonusDTO;
import xmlweb.projekat.model.dtos.BonusServiceDTO;

public interface BonusServiceInterface {

	List<BonusServiceDTO> findAllBonusServices();
	
	List<BonusDTO> forAdmin();
	
	boolean edit(BonusDTO dto);
	
	boolean create(String name);
	
	boolean delete(long id);
}
