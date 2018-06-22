package xmlweb.projekat.soap;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import xmlweb.projekat.model.dtos.AccomodationDTO;
import xmlweb.projekat.service.interfaces.AccomodationServiceInterface;
import xmlweb.projekat.soap.models.accomodation.AccomodationRequest;
import xmlweb.projekat.soap.models.accomodation.AccomodationSOAP;
import xmlweb.projekat.soap.models.accomodation.GetAccomodationRequest;
import xmlweb.projekat.soap.models.accomodation.GetAccomodationResponse;

@Endpoint
public class AccomodationEndpoint {

	private AccomodationServiceInterface service;

	@Autowired
	public AccomodationEndpoint(AccomodationServiceInterface service) {
		super();
		this.service = service;
	}
	
	@PayloadRoot(namespace = "http://xmlweb/projekat/soap/models/accomodation", localPart = "getAccomodationRequest")
	@ResponsePayload
	public GetAccomodationResponse getAccomodation(@RequestPayload GetAccomodationRequest request) {
		GetAccomodationResponse response = new GetAccomodationResponse();
		ArrayList<AccomodationDTO> lista = (ArrayList<AccomodationDTO>) service.findAllAccomodation();
		ArrayList<AccomodationSOAP> retVal = new ArrayList<>();
		
		for(AccomodationDTO a : lista) {
			AccomodationSOAP as = new AccomodationSOAP();
			as.setAccomodationId(a.getId());
			as.setAccomodationName(a.getName());
			as.setCapacity(a.getCapacity());
			as.setCategory(a.getCategory());
			as.setLocationId(a.getLocation().getId());
			as.setAccomodationType(a.getType());
			as.setVersion(a.getVersion());
			retVal.add(as);
		}
		
		
		for(AccomodationRequest req : request.getAccomodationRequest()) {
			Iterator<AccomodationSOAP> itr = retVal.iterator();
			while (itr.hasNext()) {
				AccomodationSOAP u = itr.next();
				if (req.getEntityId() == u.getAccomodationId() && req.getEntityVersion() == u.getVersion()) {
					itr.remove();
					break;
				}
			}
		}
		
		response.getEntity().addAll(retVal);
		return response;
	}
	
	
}
