package xmlweb.agent.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xmlweb.agent.model.dtos.AAAccomodationAgentDTO;
import xmlweb.agent.model.dtos.AAAccomodationDTO;
import xmlweb.agent.service.interfaces.AAAccomodationAgentServiceInterface;
import xmlweb.agent.service.interfaces.AAAccomodationServiceInterface;

@RestController
@RequestMapping(value="/accomodationagent")
public class AAAccomodationController {

	@Autowired
	private AAAccomodationServiceInterface service;
	
	@Autowired
	private AAAccomodationAgentServiceInterface agentService;

	@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4300" })
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
	public long createAccomodation(@RequestBody AAAccomodationDTO a) {
		return service.createAccomodation(a);
	}
	
	@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4300" })
	@RequestMapping(value="/agent", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
	public boolean addAccomodationAgent(@RequestBody AAAccomodationAgentDTO a) {
		return agentService.addAgent(a);
	}
	
}
