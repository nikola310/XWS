package xmlweb.agent.service.interfaces;

import java.util.List;

import xmlweb.agent.model.AccomodationAgent;

public interface AccomodationAgentServiceInterface {

	List<AccomodationAgent> readAll();
	AccomodationAgent getById(Long id);
	AccomodationAgent getByAccomodationId(Long id);
	AccomodationAgent getByAgentId(Long id);
	boolean Create(AccomodationAgent aa);
	boolean Update(AccomodationAgent aa);
	
}
