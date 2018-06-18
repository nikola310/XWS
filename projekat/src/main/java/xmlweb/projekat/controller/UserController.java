package xmlweb.projekat.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import xmlweb.projekat.model.UserType;
import xmlweb.projekat.model.dtos.AgentRequestDTO;
import xmlweb.projekat.model.dtos.UserDTO;
import xmlweb.projekat.security.TokenValidator;
import xmlweb.projekat.service.interfaces.UserServiceInterface;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4300" })
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

	@RequestMapping(value = "/by_type/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<UserDTO> getUserByType(@PathVariable UserType type) {
		return service.getUserByType(type);
	}

	@RequestMapping(value = "/agent", method = RequestMethod.POST)
	public boolean manageAgent(@RequestParam(name = "user", required = true) long user,
			@RequestHeader(value = "User-Agent", required = true) String userAgent,
			@RequestHeader(value = "Token", required = true) String token,
			@Validated @RequestBody AgentRequestDTO agent) {

		if (!TokenValidator.validateAdmin(userAgent, token)) {
			return false;
		}
		return service.manageAgent(agent, user);
	}

	@RequestMapping(value = "/activate", method = RequestMethod.POST)
	public boolean activateUser(@RequestParam("id") long id, @RequestParam("version") int version,
			@RequestHeader(value = "User-Agent", required = true) String userAgent,
			@RequestHeader(value = "Token", required = true) String token) {
		if (!TokenValidator.validateAdmin(userAgent, token)) {
			return false;
		}
		return service.manageUser(id, version, true);
	}

	@RequestMapping(value = "/block", method = RequestMethod.POST)
	public boolean blockUser(@RequestParam("id") long id, @RequestParam("version") int version,
			@RequestHeader(value = "User-Agent", required = true) String userAgent,
			@RequestHeader(value = "Token", required = true) String token) {
		if (!TokenValidator.validateAdmin(userAgent, token)) {
			return false;
		}
		return service.manageUser(id, version, false);
	}

}
