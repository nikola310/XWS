package xmlweb.projekat.soap;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import xmlweb.projekat.model.dtos.MessageDTO;
import xmlweb.projekat.service.interfaces.MessageServiceInterface;
import xmlweb.projekat.soap.models.message.GetMessageRequest;
import xmlweb.projekat.soap.models.message.GetMessageResponse;
import xmlweb.projekat.soap.models.message.MessageRequest;
import xmlweb.projekat.soap.models.message.MessageSOAP;

/**
 * @author Nikola
 *
 */
@Endpoint
public class MessageEndpoint {

	private MessageServiceInterface service;
	
	@Autowired
	public MessageEndpoint(MessageServiceInterface service) {
		super();
		this.service = service;
	}

	@PayloadRoot(namespace = "http://xmlweb/projekat/soap/models/message", localPart = "getMessageRequest")
	@ResponsePayload
	public GetMessageResponse getMessage(@RequestPayload GetMessageRequest request) {
		GetMessageResponse response = new GetMessageResponse();
		
		ArrayList<MessageDTO> listaDTO = service.ReadAll();
		ArrayList<MessageSOAP> retVal = new ArrayList<>();
		
		for(MessageDTO dto : listaDTO) {
			MessageSOAP sp = new MessageSOAP();
			sp.setContent(dto.getContent());
			sp.setEntityVersion(dto.getVersion());
			sp.setMessageId(dto.getId());
			sp.setReceiverId(dto.getReceiver());
			sp.setSenderId(dto.getSender());
			retVal.add(sp);
		}
		
		for(MessageRequest req : request.getMessage()) {
			Iterator<MessageSOAP> itr = retVal.iterator();
			while (itr.hasNext()) {
				MessageSOAP u = itr.next();
				if (req.getEntityId() == u.getMessageId() && req.getEntityVersion() == u.getEntityVersion()) {
					itr.remove();
					break;
				}
			}
		}
		
		response.getMessage().addAll(retVal);
		return response;
	}	
	
}
