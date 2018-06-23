package xmlweb.projekat.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xmlweb.projekat.model.dtos.UserDTO;
import xmlweb.projekat.service.interfaces.UserServiceInterface;

@RestController
@RequestMapping(value = "/register")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4300" })
public class RegistrationController {
	
	@Autowired
	private UserServiceInterface service;

	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
	public boolean createRegularUser(@Validated @RequestBody UserDTO u) {
		return service.Create(u);
	}
}
