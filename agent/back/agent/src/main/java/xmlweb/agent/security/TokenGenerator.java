package xmlweb.agent.security;

import java.util.Date;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;    

public class TokenGenerator {
	
	private static String signingKey = "1w4j9w81i0l15li6g3tyy7op02ft";
	
	public static String createJWT(String issuer, long ttlMillis, String userAgent){
		
		
		
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		 
	    long nowMillis = System.currentTimeMillis() +7200000;
	    Date now = new Date(nowMillis);
	 
	 
	    //Let's set the JWT Claims
	    JwtBuilder builder = Jwts.builder().setIssuedAt(now)
	                                .claim("username",issuer)
	                                .claim("user-agent", userAgent)
	                                .signWith(signatureAlgorithm, signingKey);
	 
	    //if it has been specified, let's add the expiration
	    if (ttlMillis >= 0) {
	    long expMillis = nowMillis + ttlMillis;
	    System.out.println("now millis " + nowMillis);
	    System.out.println("exp milis" + expMillis);
	        Date exp = new Date(expMillis);
	        builder.setExpiration(exp);
	    }
	 
	    //Builds the JWT and serializes it to a compact, URL-safe string
	    return builder.compact();
	}

}
