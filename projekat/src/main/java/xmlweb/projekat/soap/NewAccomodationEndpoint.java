package xmlweb.projekat.soap;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import xmlweb.projekat.model.Accomodation;
import xmlweb.projekat.model.AccomodationAgent;
import xmlweb.projekat.model.AccomodationType;
import xmlweb.projekat.service.interfaces.AccomodationServiceInterface;
import xmlweb.projekat.service.interfaces.UserServiceInterface;
import xmlweb.projekat.soap.services.new_accomodation.GetAccomodationRequest;
import xmlweb.projekat.soap.services.new_accomodation.GetNewAccomodationResponse;
import xmlweb.projekat.soap.services.new_accomodation.NewAccomodationRequest;
import xmlweb.projekat.soap.services.new_accomodation.NewAccomodationSOAP;

@Endpoint
public class NewAccomodationEndpoint {

	private AccomodationServiceInterface accomodationService;
	
	private UserServiceInterface userService;

	@Autowired
	public NewAccomodationEndpoint(AccomodationServiceInterface accomodationService, UserServiceInterface userService) {
		super();
		this.accomodationService = accomodationService;
		this.userService = userService;
	}
	
	@PayloadRoot(namespace = "http://xmlweb/projekat/soap/services/new_accomodation", localPart = "getNewAccomodationRequest")
	@ResponsePayload
	public GetNewAccomodationResponse getNewAccomodation(@RequestPayload GetAccomodationRequest request) {
		GetNewAccomodationResponse response = new GetNewAccomodationResponse();
		NewAccomodationSOAP nas = new NewAccomodationSOAP();
		NewAccomodationRequest nar = request.getNewAccomodationRequest();
		
		
		Accomodation a = new Accomodation();
		
		//a.setAccomodationAgent(accomodationAgent);
		a.setAccomodationName(nar.getAccomodationName());
		//a.setAccomodationType(AccomodationType);
		//a.setBonusServices(bonusServices);
		
		nas.setNewMessageResponse(accomodationService.createAccomodation(a));
		response.setEntity(nas);
		return response;
	}
	
	
	
	
}
