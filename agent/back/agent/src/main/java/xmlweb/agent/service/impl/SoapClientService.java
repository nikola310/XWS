package xmlweb.agent.service.impl;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import xmlweb.agent.model.Accomodation;
import xmlweb.agent.model.Message;
import xmlweb.agent.model.Reservation;
import xmlweb.agent.service.interfaces.SoapClientServiceInterface;
import xmlweb.agent.soap.services.new_accomodation.GetAccomodationRequest;
import xmlweb.agent.soap.services.new_accomodation.GetNewAccomodationResponse;
import xmlweb.agent.soap.services.new_accomodation.NewAccomodationRequest;
import xmlweb.agent.soap.services.realised_reservation.GetRealisedReservationRequest;
import xmlweb.agent.soap.services.realised_reservation.GetRealisedReservationResponse;
import xmlweb.agent.soap.services.realised_reservation.RealisedReservationRequest;
import xmlweb.agent.soap.services.reserve_accomodation.GetReserveAccomodationRequest;
import xmlweb.agent.soap.services.reserve_accomodation.GetReserveAccomodationResponse;
import xmlweb.agent.soap.services.reserve_accomodation.ReserveAccomodationRequest;
import xmlweb.agent.soap.services.send_message.GetSendMessageRequest;
import xmlweb.agent.soap.services.send_message.GetSendMessageResponse;
import xmlweb.agent.soap.services.send_message.SendMessageRequest;

@Service
public class SoapClientService implements SoapClientServiceInterface {

	@Override
	public boolean NewAccomodation(Accomodation a) {
		GetAccomodationRequest gnar = new GetAccomodationRequest();
		
		NewAccomodationRequest nar = new NewAccomodationRequest();
		nar.setAccomodationId(a.getId());
		nar.setAccomodationName(a.getAccomodationName());
		nar.setAccomodationType(a.getAccomodationType().toString());
		nar.setAgentId(a.getAccomodationAgent().get(0).getId());
		nar.setCapacity(a.getCapacity());
		nar.setCategory(a.getCategory());
		nar.setLocationId(a.getLocation().getId());
		nar.setVersion(a.getVersion());
		
		gnar.setNewAccomodationRequest(nar);
		
		WebServiceTemplate wst = new WebServiceTemplate();
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("xmlweb.agent.soap.services.new_accomodation");
		wst.setDefaultUri("http://localhost:8089/booking/ws/new_accomodation.wsdl");
		wst.setMarshaller(marshaller);
		wst.setUnmarshaller(marshaller);
		GetNewAccomodationResponse gnas = (GetNewAccomodationResponse) wst.marshalSendAndReceive(gnar);

		return gnas.getEntity().isNewMessageResponse();
	}

	@Override
	public boolean RealisedReservation(Long reservId) {
		GetRealisedReservationRequest grrr = new GetRealisedReservationRequest();
		
		RealisedReservationRequest rrr = new RealisedReservationRequest();
		rrr.setReservationId(reservId);
		grrr.setRealisedReservationRequest(rrr);
		
		WebServiceTemplate wst = new WebServiceTemplate();
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("xmlweb.agent.soap.services.realised_reservation");
		wst.setDefaultUri("http://localhost:8089/booking/ws/realised_reservation.wsdl");
		wst.setMarshaller(marshaller);
		wst.setUnmarshaller(marshaller);
		GetRealisedReservationResponse gr = (GetRealisedReservationResponse) wst.marshalSendAndReceive(grrr);

		return gr.getRealisedReservationResponse().isRealisedReservationResponse();
	}

	@Override
	public boolean ReserveAccomodation(Reservation r) {
		GetReserveAccomodationRequest grar = new GetReserveAccomodationRequest();
		ReserveAccomodationRequest rar = new ReserveAccomodationRequest();
		
		rar.setAccomodation(r.getAccomodation().getId());
		rar.setEndDate(r.getEndDate());
		rar.setEntityVersion(r.getVersion());
		rar.setNumberOfPersons(r.getNumberOfPersons());
		rar.setReservationId(r.getId());
		rar.setStartDate(r.getStartDate());
		rar.setUserId(r.getUser().getId());
		
		grar.setReserveAccomodation(rar);
		
		WebServiceTemplate wst = new WebServiceTemplate();
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("xmlweb.agent.soap.services.reserve_accomodation");
		wst.setDefaultUri("http://localhost:8089/booking/ws/reserve_accomodation.wsdl");
		wst.setMarshaller(marshaller);
		wst.setUnmarshaller(marshaller);
		
		GetReserveAccomodationResponse gr = (GetReserveAccomodationResponse) wst.marshalSendAndReceive(grar);

		return gr.getReserveAccomodation().isReserveAccomodationResponse();
	}

	@Override
	public boolean SendMessage(Message m) {
		GetSendMessageRequest gsmr = new GetSendMessageRequest();
		
		SendMessageRequest smr = new SendMessageRequest();
		smr.setContent(m.getContent());
		smr.setEntityVersion(m.getVersion());
		smr.setMessageId(m.getId());
		smr.setReceiverId(m.getReciever().getId());
		smr.setSenderId(m.getSender().getId());
		
		gsmr.setSendMessage(smr);
		
		WebServiceTemplate wst = new WebServiceTemplate();
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("xmlweb.agent.soap.services.send_message");
		wst.setDefaultUri("http://localhost:8089/booking/ws/send_message.wsdl");
		wst.setMarshaller(marshaller);
		wst.setUnmarshaller(marshaller);
		
		GetSendMessageResponse gr = (GetSendMessageResponse) wst.marshalSendAndReceive(gsmr);
		
		return gr.getSendMessage().isMessageReceived();
	}

}
