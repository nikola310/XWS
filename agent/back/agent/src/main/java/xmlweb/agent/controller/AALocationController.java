package xmlweb.agent.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xmlweb.agent.model.dtos.LocationDTO;
import xmlweb.agent.service.impl.AALocationService;

@RestController
@RequestMapping(value = "/locationagent")
public class AALocationController {
	
	@Autowired
	private AALocationService service;

	
	@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4300" })
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
	public long createLocation(@Validated @RequestBody LocationDTO u) {
		return service.createLoc(u);
	}
}
