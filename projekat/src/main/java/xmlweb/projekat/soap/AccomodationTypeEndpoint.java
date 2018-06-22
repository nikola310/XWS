package xmlweb.projekat.soap;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import xmlweb.projekat.model.AccomodationType;
import xmlweb.projekat.service.interfaces.AccomodationTypeServiceInterface;
import xmlweb.projekat.soap.models.accomodation_type.AccomodationTypeRequest;
import xmlweb.projekat.soap.models.accomodation_type.AccomodationTypeSOAP;
import xmlweb.projekat.soap.models.accomodation_type.GetAccomodationTypeRequest;
import xmlweb.projekat.soap.models.accomodation_type.GetAccomodationTypeResponse;

@Endpoint
public class AccomodationTypeEndpoint {

	private AccomodationTypeServiceInterface service;
	
	@Autowired
	public AccomodationTypeEndpoint(AccomodationTypeServiceInterface service) {
		super();
		this.service = service;
	}
	
	@PayloadRoot(namespace = "http://xmlweb/projekat/soap/models/accomodation_type", localPart = "getAccomodationTypeRequest")
	@ResponsePayload
	public GetAccomodationTypeResponse getAccomodationType(@RequestPayload GetAccomodationTypeRequest request) {
		GetAccomodationTypeResponse response = new GetAccomodationTypeResponse();
		ArrayList<AccomodationType>   lista = (ArrayList<AccomodationType>) service.findAllAccomodationTypes();
		ArrayList<AccomodationTypeSOAP> retVal = new ArrayList<>();
		
		for(AccomodationType at : lista) {
			AccomodationTypeSOAP ats = new AccomodationTypeSOAP();
			ats.setAccomodationTypeId(at.getId());
			ats.setEntityVersion(at.getVersion());
			ats.setName(at.getName());
			retVal.add(ats);
		}
		
		for(AccomodationTypeRequest req : request.getRequestEntity()) {
			Iterator<AccomodationTypeSOAP> itr = retVal.iterator();
			while (itr.hasNext()) {
				AccomodationTypeSOAP u = itr.next();
				if (req.getEntityId() == u.getAccomodationTypeId() && req.getEntityVersion() == u.getEntityVersion()) {
					itr.remove();
					break;
				}
			}
			
		}
		
		
		
		response.getEntity().addAll(retVal);
		return response;
	}
	
}
