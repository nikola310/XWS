package xmlweb.projekat.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import xmlweb.projekat.model.AccomodationType;
import xmlweb.projekat.security.TokenValidator;
import xmlweb.projekat.service.interfaces.AccomodationTypeServiceInterface;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4300" })
public class AccomodationTypeController {

	@Autowired
	private AccomodationTypeServiceInterface service;

	@RequestMapping(value = "/types", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<AccomodationType> findAllAccomodationTypes() {
		return service.findAllAccomodationTypes();
	}

	@RequestMapping(value = "/types", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public boolean updateType(@RequestBody AccomodationType a,
			@RequestHeader(value = "User-Agent", required = true) String userAgent,
			@RequestHeader(value = "Token", required = true) String token) {
		if (!TokenValidator.validateAdmin(userAgent, token)) {
			return false;
		}
		return service.Update(a);
	}

	@RequestMapping(value = "/types", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_FORM_URLENCODED, produces = MediaType.APPLICATION_JSON)
	public boolean create(@RequestParam("name") String name,
			@RequestHeader(value = "User-Agent", required = true) String userAgent,
			@RequestHeader(value = "Token", required = true) String token) {
		return service.Create(name);
	}

	@RequestMapping(value = "/types/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON)
	public boolean delete(@PathVariable long id,
			@RequestHeader(value = "User-Agent", required = true) String userAgent,
			@RequestHeader(value = "Token", required = true) String token) {
		return service.Delete(id);
	}

}
