package xmlweb.projekat.soap;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import xmlweb.projekat.model.dtos.AccomodationBonusDTO;
import xmlweb.projekat.service.interfaces.AccomodationServiceInterface;
import xmlweb.projekat.soap.models.accomodation_bonus_service.AccomodationBonusServiceRequest;
import xmlweb.projekat.soap.models.accomodation_bonus_service.AccomodationBonusServiceSOAP;
import xmlweb.projekat.soap.models.accomodation_bonus_service.GetAccomodationBonusServiceRequest;
import xmlweb.projekat.soap.models.accomodation_bonus_service.GetAccomodationBonusServiceResponse;


@Endpoint
public class AccomodationBonusServiceEndpoint {

	private AccomodationServiceInterface service;

	@Autowired
	public AccomodationBonusServiceEndpoint(AccomodationServiceInterface service) {
		super();
		this.service = service;
	}
	
	@PayloadRoot(namespace = "http://xmlweb/projekat/soap/models/accomodation_bonus_service", localPart = "getAccomodationBonusServiceRequest")
	@ResponsePayload
	public GetAccomodationBonusServiceResponse getAccomodationBonusService(@RequestPayload GetAccomodationBonusServiceRequest request) {
		GetAccomodationBonusServiceResponse response = new GetAccomodationBonusServiceResponse();
		ArrayList<AccomodationBonusDTO> lista = (ArrayList<AccomodationBonusDTO>) service.findAllAccomodationBonus();
		ArrayList<AccomodationBonusServiceSOAP> retVal = new ArrayList<AccomodationBonusServiceSOAP>();
		
		for(AccomodationBonusDTO dto : lista) {
			AccomodationBonusServiceSOAP a = new AccomodationBonusServiceSOAP();	
			a.setAccomodationId(dto.getAccomodationId());
			a.setBonusServiceId(dto.getBonusId());
			retVal.add(a);
		}
		
		for(AccomodationBonusServiceRequest req : request.getAccomodationBonusServiceRequest()) {
			Iterator<AccomodationBonusServiceSOAP> itr = retVal.iterator();
			while(itr.hasNext()) {
				AccomodationBonusServiceSOAP u = itr.next();
				if (req.getAccomodationId() == u.getAccomodationId() && req.getBonusServiceId() == u.getBonusServiceId()) {
					itr.remove();
					break;
				}
			}
			
		}
		
		response.getAccomodationBonusServiceResponse().addAll(retVal);
		return response;
	}

	
}
