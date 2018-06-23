package xmlweb.projekat.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import xmlweb.projekat.model.dtos.MessageDTO;
import xmlweb.projekat.service.interfaces.MessageServiceInterface;
import xmlweb.projekat.soap.services.send_message.GetSendMessageRequest;
import xmlweb.projekat.soap.services.send_message.GetSendMessageResponse;
import xmlweb.projekat.soap.services.send_message.SendMessageRequest;
import xmlweb.projekat.soap.services.send_message.SendMessageSOAP;

@Endpoint
public class SendMessageEndpoint {

	private MessageServiceInterface service;

	@Autowired
	public SendMessageEndpoint(MessageServiceInterface service) {
		super();
		this.service = service;
	}

	@PayloadRoot(namespace = "http://xmlweb/projekat/soap/services/send_message", localPart = "getSendMessageRequest")
	@ResponsePayload
	public GetSendMessageResponse getReservation(@RequestPayload GetSendMessageRequest request) {
		GetSendMessageResponse response = new GetSendMessageResponse();

		SendMessageRequest msg = request.getSendMessage();
		MessageDTO dto = new MessageDTO();
		dto.setContent(msg.getContent());
		dto.setReceiver(msg.getReceiverId());
		dto.setSender(msg.getSenderId());

		SendMessageSOAP soap = new SendMessageSOAP();
		soap.setMessageReceived(service.Create(dto));

		response.setSendMessage(soap);
		return response;
	}

}
