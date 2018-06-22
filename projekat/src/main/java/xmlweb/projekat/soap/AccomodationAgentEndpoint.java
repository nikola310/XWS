package xmlweb.projekat.soap;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import xmlweb.projekat.model.AccomodationAgent;
import xmlweb.projekat.service.interfaces.AccomodationAgentServiceInterface;
import xmlweb.projekat.soap.models.accomodation_agent.AccomodationAgentRequest;
import xmlweb.projekat.soap.models.accomodation_agent.AccomodationAgentSOAP;
import xmlweb.projekat.soap.models.accomodation_agent.GetAccomodationAgentRequest;
import xmlweb.projekat.soap.models.accomodation_agent.GetAccomodationAgentResponse;

@Endpoint
public class AccomodationAgentEndpoint {

	private AccomodationAgentServiceInterface service;

	@Autowired
	public AccomodationAgentEndpoint(AccomodationAgentServiceInterface service) {
		super();
		this.service = service;
	}
	
	@PayloadRoot(namespace = "http://xmlweb/projekat/soap/models/accomodation_agent", localPart = "getAccomodationAgentRequest")
	@ResponsePayload
	public GetAccomodationAgentResponse getAccomodationAgent(@RequestPayload GetAccomodationAgentRequest request) {
		GetAccomodationAgentResponse response = new GetAccomodationAgentResponse();
		
		ArrayList<AccomodationAgent> lista = (ArrayList<AccomodationAgent>) service.findAllAccomodationAgent();
		ArrayList<AccomodationAgentSOAP> retVal = new ArrayList<AccomodationAgentSOAP>();
		
		for(AccomodationAgent a : lista) {
			AccomodationAgentSOAP as = new AccomodationAgentSOAP();
			as.setAccomodationAgentId(a.getId());
			as.setAccomodationId(a.getAccomodation().getId());
			as.setAgentId(a.getAgent().getId());
			as.setEntityVersion(a.getVersion());
			as.setMainAgent(a.isMainAgent());
			retVal.add(as);
		}
		
		for(AccomodationAgentRequest req : request.getAccomodationAgent()) {
			Iterator<AccomodationAgentSOAP> itr = retVal.iterator();
			while(itr.hasNext()) {
				AccomodationAgentSOAP u = itr.next();
				if(req.getEntityId() == u.getAccomodationAgentId() &&  req.getEntityVersion() == u.getEntityVersion()) {
					itr.remove();
					break;
				}
			}
		}
		
		response.getAccomodationAgent().addAll(retVal);
		return response;
	}
	
	
}
