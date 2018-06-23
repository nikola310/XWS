package xmlweb.agent.service.interfaces;

import java.util.List;

import xmlweb.agent.model.AccomodationType;

public interface AccomodationTypeServiceInterface extends ServiceInterface<AccomodationType> {
	
	List<AccomodationType> findAllAccomodationTypes();
	
	AccomodationType findOne(Long id);
	
	AccomodationType finyByName(String typeName);
	
	
}
