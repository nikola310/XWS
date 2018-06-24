package xmlweb.agent.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xmlweb.agent.model.AccomodationType;
import xmlweb.agent.repository.AAAccomodationTypeRepository;

@RestController
@RequestMapping(value="/accomodationtypeagent")
public class AAAccomodationTypeController {
	
	@Autowired
	private AAAccomodationTypeRepository repository;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<AccomodationType> readLocation() {
		return repository.findAll();
	}
}
