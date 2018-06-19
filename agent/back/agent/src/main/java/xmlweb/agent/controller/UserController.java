package xmlweb.agent.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xmlweb.agent.model.dtos.LoginRequestDTO;
import xmlweb.agent.model.dtos.LoginResponseDTO;
import xmlweb.agent.service.interfaces.LoginServiceInterface;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin(origins = "${angular.cross.agent.url}")
public class UserController {

	@Autowired
	private LoginServiceInterface loginService;
	
	@RequestMapping(value="/login",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	public LoginResponseDTO loginUser(@RequestBody LoginRequestDTO dto) {
		System.out.println(dto.getUsername() + " " + dto.getPassword());
		String token = loginService.loginUser(dto.getUsername(), dto.getPassword());
		return new LoginResponseDTO("success", "token");
	}
}
