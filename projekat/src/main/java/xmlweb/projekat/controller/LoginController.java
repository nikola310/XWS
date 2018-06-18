package xmlweb.projekat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xmlweb.projekat.model.User;
import xmlweb.projekat.model.dtos.UserDTO;
import xmlweb.projekat.security.TokenGenerator;
import xmlweb.projekat.service.interfaces.UserServiceInterface;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	private UserServiceInterface userService;
	
	private String secret = "1w4j9w81i0l15li6g3tyy7op02ft";
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String login(@RequestHeader(value="User-Agent") String userAgent, @RequestBody UserDTO u){
		System.out.println(u.getUserName());
		User user = userService.getRegularUserByUsername(u.getUserName());
		System.out.println("user agent" + userAgent);
		
		if(user == null) {
			return "{ \"status\":\"failed\", \"info\":\"Invalid username.\"}";
		}
		
		if(!user.getPassword().equals(u.getPassword())){
			return "{ \"status\":\"failed\", \"info\":\"Invalid username or password.\"}";
		}
		
		
		String token = TokenGenerator.createJWT(user.getUserName(), 7200000, userAgent);
		System.out.println(token);
		
		
		return "{ \"status\":\"success\", \"info\":\"" + token + "\"}";
	}
}
