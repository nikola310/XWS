package xmlweb.projekat.soap;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import xmlweb.projekat.model.dtos.LocationDTO;
import xmlweb.projekat.model.soap.GetLocationRequest;
import xmlweb.projekat.model.soap.GetLocationResponse;
import xmlweb.projekat.model.soap.LocationRequest;
import xmlweb.projekat.model.soap.LocationSOAP;
import xmlweb.projekat.service.interfaces.LocationServiceInterface;

/**
 * @author Nikola
 *
 */
@Endpoint
public class LocationEndpoint {
	
	private LocationServiceInterface service;

	@Autowired
	public LocationEndpoint(LocationServiceInterface service) {
		super();
		this.service = service;
	}
	
	@PayloadRoot(namespace = UserEndpoint.NAMESPACE_URI, localPart = "getLocationRequest")
	@ResponsePayload
	public GetLocationResponse getLocation(@RequestPayload GetLocationRequest request) {
		GetLocationResponse response = new GetLocationResponse();
		
		ArrayList<LocationDTO> listaDTO = service.ReadAll();
		ArrayList<LocationSOAP> retVal = new ArrayList<>();
		
		for(LocationDTO dto : listaDTO) {
			LocationSOAP sp = new LocationSOAP();
			sp.setCity(dto.getCity());
			sp.setEntityVersion(dto.getVersion());
			sp.setLocationId(dto.getId());
			sp.setState(dto.getState());
			sp.setStreetName(dto.getStreetName());
			sp.setStreetNumber(dto.getStreetNumber());
			retVal.add(sp);
		}
		
		for(LocationRequest req : request.getLocationRequest()) {
			Iterator<LocationSOAP> itr = retVal.iterator();
			while (itr.hasNext()) {
				LocationSOAP u = itr.next();
				if (req.getEntityId() == u.getLocationId() && req.getEntityVersion() == u.getEntityVersion()) {
					itr.remove();
					break;
				}
			}
		}
		
		response.getEntity().addAll(retVal);
		return response;
	}

}
