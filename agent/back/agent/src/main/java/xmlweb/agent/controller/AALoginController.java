package xmlweb.agent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xmlweb.agent.model.User;
import xmlweb.agent.model.dtos.UserDTO;
import xmlweb.agent.repository.AAUserRepository;
import xmlweb.agent.security.TokenGenerator;

@RestController
@RequestMapping(value = "/loginagent")
public class AALoginController {
	
	@Autowired
	private AAUserRepository repo;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String login(@RequestBody UserDTO u) {
		System.out.println(u.getUserName());
		User user = repo.findAdminByUsername(u.getUserName());

		if (user == null) {
			return "{ \"status\":\"failed\", \"info\":\"Invalid username.\"}";
		}

		if (!user.getPassword().equals(u.getPassword())) {
			return "{ \"status\":\"failed\", \"info\":\"Invalid username or password.\"}";
		}


		return "{ \"status\":\"success\", \"info\":" + user.getId() +"}";
	}
}
