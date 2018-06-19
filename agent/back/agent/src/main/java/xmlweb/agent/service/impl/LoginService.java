package xmlweb.agent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xmlweb.agent.service.interfaces.LoginServiceInterface;
import xmlweb.agent.service.interfaces.SoapClientServiceInterface;

@Service
public class LoginService implements LoginServiceInterface {

	@Autowired
	private SoapClientServiceInterface soapService;
	
	@Override
	public String loginUser(String username, String password) {
		// ako je ok generisemo token i updateujemo bazu
		
		soapService.SyncDB();
		
		//ako nije vracamo null
		
		// return token or null 
		return null;
	}

	
}
