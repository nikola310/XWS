package xmlweb.projekat.security;

import org.springframework.beans.factory.annotation.Autowired;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import xmlweb.projekat.service.interfaces.UserServiceInterface;

public class TokenValidator {
	
	private static String signingKey = "1w4j9w81i0l15li6g3tyy7op02ft";
	
	@Autowired
	private UserServiceInterface service;
	
	public boolean validateToken(String userAgent, String token){
		
		Claims claims = Jwts.parser()         
			       .setSigningKey(signingKey)
			       .parseClaimsJws(token).getBody();
		  System.out.println("issuer " + claims.getIssuer());
		  System.out.println(claims.get("user-agent"));
		  
		  if(userAgent.equals(claims.get("user-agent"))){
			  if(service.getRegularUserByUsername(claims.get("username").toString()) != null)
				  return true;
		  }
		return false;
	}
}
