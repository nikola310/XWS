package xmlweb.agent.service.interfaces;

import java.util.List;

import xmlweb.agent.model.dtos.BonusServiceDTO;

public interface BonusServiceInterface extends ServiceInterface<BonusServiceDTO>{

	List<BonusServiceDTO> findAllBonusServices();
	
	
}
