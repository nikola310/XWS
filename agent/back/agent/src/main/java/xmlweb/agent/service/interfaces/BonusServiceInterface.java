package xmlweb.agent.service.interfaces;

import java.util.List;

import xmlweb.agent.model.BonusService;

public interface BonusServiceInterface{

	List<BonusService> findAllBonusServices();
	BonusService findByName(String bonusName);
	BonusService findById(long id);
	boolean Create(BonusService b);
	boolean Update(BonusService b);
	
	
}
