package xmlweb.agent.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xmlweb.agent.model.Accomodation;
import xmlweb.agent.model.AccomodationAgent;
import xmlweb.agent.model.User;
import xmlweb.agent.model.dtos.AAAccomodationAgentDTO;
import xmlweb.agent.repository.AAAccomodationAgentRepository;
import xmlweb.agent.repository.AAAccomodationRepository;
import xmlweb.agent.repository.UserRepository;
import xmlweb.agent.service.interfaces.AAAccomodationAgentServiceInterface;

@Service
public class AAAccomodationAgentService implements AAAccomodationAgentServiceInterface{

	
	@Autowired
	private AAAccomodationAgentRepository repository;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AAAccomodationRepository accRepo;
	
	@Override
	public boolean addAgent(AAAccomodationAgentDTO a) {
		// TODO Auto-generated method stub
		try {
			ModelMapper mapper = new ModelMapper();
			AccomodationAgent aa = mapper.map(a, AccomodationAgent.class);
			User u = userService.getById(a.getAgent());
			Accomodation acc = accRepo.findById(a.getAccomodation());
			aa.setAgent(u);
			aa.setAccomodation(acc);
			repository.save(aa);

			return true;
			
		} catch (Exception exc) {
			exc.printStackTrace();
		};
		return false;
	}

}
