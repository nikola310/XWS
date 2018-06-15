package xmlweb.projekat.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import xmlweb.projekat.booking.soap.GetUserRequest;
import xmlweb.projekat.booking.soap.GetUserResponse;
import xmlweb.projekat.booking.soap.UserProbni;
import xmlweb.projekat.model.dtos.UserDTO;
import xmlweb.projekat.repository.UserRepository;
import xmlweb.projekat.service.interfaces.UserServiceInterface;

@Endpoint
public class UserEndpoint {

	private static final String NAMESPACE_URI = "http://xmlweb/projekat/booking/soap";

	@Autowired
	private UserServiceInterface service;

	@Autowired
	public UserEndpoint(UserServiceInterface service) {
		this.service = service;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
	@ResponsePayload
	public GetUserResponse getUser(@RequestPayload GetUserRequest request) {
		GetUserResponse response = new GetUserResponse();
		UserDTO dto = service.Read(1);
		UserProbni upr = new UserProbni();
		upr.setIme(dto.getFirstName());
		upr.setPrezime(dto.getLastName());
		response.setKorisnik(upr);

		return response;
	}

}
