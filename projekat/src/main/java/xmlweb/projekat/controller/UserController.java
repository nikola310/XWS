package xmlweb.projekat.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xmlweb.projekat.model.dtos.UserDTO;
import xmlweb.projekat.service.interfaces.UserServiceInterface;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/user")
public class UserController {

	private UserServiceInterface service;

	@Autowired
	public UserController(UserServiceInterface service) {
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON)
	public boolean createUser(@Validated @RequestBody UserDTO u) {
		return service.Create(u);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public UserDTO readUser(@PathVariable long id) {
		return service.Read(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<UserDTO> readUsers() {
		return service.ReadAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public boolean updateUser(@Validated @RequestBody UserDTO u) {
		return service.Update(u);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean deleteUser(@PathVariable long id) {
		return service.Delete(id);
	}

	@RequestMapping(value = "/normal", method = RequestMethod.GET)
	public List<UserDTO> readNormalUsers() {
		return service.getNormalUsers();
	}
	
	@RequestMapping(value = "/admins", method = RequestMethod.GET)
	public List<UserDTO> readAdmins() {
		return service.getAgents();
	}

}
