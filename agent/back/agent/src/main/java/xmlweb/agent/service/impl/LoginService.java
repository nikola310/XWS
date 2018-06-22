package xmlweb.agent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xmlweb.agent.service.interfaces.LoginServiceInterface;
import xmlweb.agent.service.interfaces.UpdateDatabaseServiceInterface;

@Service
public class LoginService implements LoginServiceInterface {

	@Autowired
	private UpdateDatabaseServiceInterface updateDatabaseService;
	
	@Override
	public String loginUser(String username, String password) {
		// ako je ok generisemo token i updateujemo bazu
		
		updateDatabaseService.SyncDB();
		
		//ako nije vracamo null
		
		// return token or null 
		return null;
	}

	
}
