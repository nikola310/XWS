package xmlweb.projekat.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xmlweb.projekat.model.dtos.UserDTO;
import xmlweb.projekat.service.impl.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	private UserService service;

	@Autowired
	public UserController(UserService service) {
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON)
	public boolean createUser(@Validated @RequestBody UserDTO u) {
		System.out.println("kontroler ->" + u.getUserName());
		return service.Create(u);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public UserDTO readUser(@PathVariable long id) {
		return service.Read(id);
	}

}
