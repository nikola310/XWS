package xmlweb.projekat.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import xmlweb.projekat.model.dtos.ReservationDTO;
import xmlweb.projekat.service.interfaces.ReservationServiceInterface;
import xmlweb.projekat.soap.services.realised_reservation.GetRealisedReservationRequest;
import xmlweb.projekat.soap.services.realised_reservation.GetRealisedReservationResponse;
import xmlweb.projekat.soap.services.realised_reservation.RealisedReservationSOAP;

@Endpoint
public class RealisedReservationEndpoint {

	private ReservationServiceInterface service;

	@Autowired
	public RealisedReservationEndpoint(ReservationServiceInterface service) {
		super();
		this.service = service;
	}

	@PayloadRoot(namespace = "http://xmlweb/projekat/soap/services/realised_reservation", localPart = "getRealisedReservationRequest")
	@ResponsePayload
	public GetRealisedReservationResponse getRealisedReservation(
			@RequestPayload GetRealisedReservationRequest request) {
		GetRealisedReservationResponse response = new GetRealisedReservationResponse();

		ReservationDTO r = service.Read(request.getRealisedReservationRequest().getReservationId());
		r.setRealized(true);
		boolean flag = service.Update(r);
		RealisedReservationSOAP soap = new RealisedReservationSOAP();
		soap.setRealisedReservationResponse(flag);
		response.setRealisedReservationResponse(soap);
		return response;
	}
}
