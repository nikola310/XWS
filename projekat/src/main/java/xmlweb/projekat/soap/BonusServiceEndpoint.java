package xmlweb.projekat.soap;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import xmlweb.projekat.model.dtos.BonusServiceDTO;
import xmlweb.projekat.service.interfaces.BonusServiceInterface;
import xmlweb.projekat.soap.models.bonus_service.BonusServiceRequest;
import xmlweb.projekat.soap.models.bonus_service.BonusServiceSOAP;
import xmlweb.projekat.soap.models.bonus_service.GetBonusServiceRequest;
import xmlweb.projekat.soap.models.bonus_service.GetBonusServiceResponse;

@Endpoint
public class BonusServiceEndpoint {

	private BonusServiceInterface service;

	@Autowired
	public BonusServiceEndpoint(BonusServiceInterface service) {
		super();
		this.service = service;
	}
	
	@PayloadRoot(namespace = "http://xmlweb/projekat/soap/models/bonus_service", localPart = "getBonusServiceRequest")
	@ResponsePayload
	public GetBonusServiceResponse getBonusService(@RequestPayload GetBonusServiceRequest request) {
		GetBonusServiceResponse response = new GetBonusServiceResponse();
		
		ArrayList<BonusServiceDTO> lista = (ArrayList<BonusServiceDTO>) service.findAllBonusServices();
		ArrayList<BonusServiceSOAP> retVal = new ArrayList<>();
		
		for(BonusServiceDTO s : lista) {
			BonusServiceSOAP sp = new BonusServiceSOAP();
			sp.setBonusServiceId(s.getId());
			sp.setName(s.getName());
			sp.setEntityVersion(s.getVersion());
			retVal.add(sp);
		}
		
		for(BonusServiceRequest req : request.getBonusServiceRequest()) {
			Iterator<BonusServiceSOAP> itr = retVal.iterator();
			while(itr.hasNext()) {
				BonusServiceSOAP u = itr.next();
				if (req.getEntityId() == u.getBonusServiceId() && req.getEntityVersion() == u.getEntityVersion()) {
					itr.remove();
					break;
				}
			}
			
		}
		
		response.getEntity().addAll(retVal);
		return response;
	}
}
