package xmlweb.projekat.soap;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import xmlweb.projekat.model.dtos.ReservationDTO;
import xmlweb.projekat.service.interfaces.ReservationServiceInterface;
import xmlweb.projekat.soap.models.reservation.GetReservationRequest;
import xmlweb.projekat.soap.models.reservation.GetReservationResponse;
import xmlweb.projekat.soap.models.reservation.ReservationRequest;
import xmlweb.projekat.soap.models.reservation.ReservationSOAP;

/**
 * @author Nikola
 *
 */
@Endpoint
public class ReservationEndpoint {

	private ReservationServiceInterface service;

	@Autowired
	public ReservationEndpoint(ReservationServiceInterface service) {
		super();
		this.service = service;
	}
	
	@PayloadRoot(namespace = "http://xmlweb/projekat/soap/models/reservation", localPart = "getReservationRequest")
	@ResponsePayload
	public GetReservationResponse getReservation(@RequestPayload GetReservationRequest request) {
		GetReservationResponse response = new GetReservationResponse();
		
		ArrayList<ReservationDTO> listaDTO = service.ReadAll();
		ArrayList<ReservationSOAP> retVal = new ArrayList<>();
		
		for(ReservationDTO dto : listaDTO) {
			ReservationSOAP sp = new ReservationSOAP();
			sp.setAccomodation(dto.getAccomodation());
			sp.setEndDate(dto.getEndDate());
			sp.setStartDate(dto.getStartDate());
			sp.setReservationId(dto.getId());
			sp.setEntityVersion(dto.getVersion());
			sp.setNumberOfPersons(dto.getNumberOfPersons());
			sp.setRealised(dto.getRealized());
			sp.setUserId(dto.getUser());
			retVal.add(sp);
		}
		
		for(ReservationRequest req : request.getReservations()) {
			Iterator<ReservationSOAP> itr = retVal.iterator();
			while (itr.hasNext()) {
				ReservationSOAP u = itr.next();
				if (req.getEntityId() == u.getReservationId() && req.getEntityVersion() == u.getEntityVersion()) {
					itr.remove();
					break;
				}
			}
		}
		
		response.getReservations().addAll(retVal);
		return response;
	}

}
