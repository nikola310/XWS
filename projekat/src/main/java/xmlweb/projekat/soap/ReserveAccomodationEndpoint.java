package xmlweb.projekat.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import xmlweb.projekat.model.dtos.ReservationDTO;
import xmlweb.projekat.service.interfaces.ReservationServiceInterface;
import xmlweb.projekat.soap.services.reserve_accomodation.GetReserveAccomodationRequest;
import xmlweb.projekat.soap.services.reserve_accomodation.GetReserveAccomodationResponse;
import xmlweb.projekat.soap.services.reserve_accomodation.ReserveAccomodationRequest;
import xmlweb.projekat.soap.services.reserve_accomodation.ReserveAccomodationSOAP;

@Endpoint
public class ReserveAccomodationEndpoint {

	private ReservationServiceInterface reservationService;

	@Autowired
	public ReserveAccomodationEndpoint(ReservationServiceInterface reservationService) {
		super();
		this.reservationService = reservationService;
	}

	@PayloadRoot(namespace = "http://xmlweb/projekat/soap/services/reserve_accomodation", localPart = "getReserveAccomodationRequest")
	@ResponsePayload
	public GetReserveAccomodationResponse getRealisedReservation(
			@RequestPayload GetReserveAccomodationRequest request) {
		GetReserveAccomodationResponse response = new GetReserveAccomodationResponse();

		ReserveAccomodationRequest soap = request.getReserveAccomodation();
		ReservationDTO res = new ReservationDTO();

		res.setAccomodation(soap.getAccomodation());
		res.setEndDate(soap.getEndDate());
		res.setStartDate(soap.getStartDate());
		res.setNumberOfPersons(soap.getNumberOfPersons());
		res.setReviewed(false);
		res.setUser(soap.getUserId());
		res.setPrice(0);

		ReserveAccomodationSOAP sp = new ReserveAccomodationSOAP();
		sp.setReserveAccomodationResponse(reservationService.Create(res));
		response.setReserveAccomodation(sp);
		return response;
	}
}
