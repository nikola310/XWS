package xmlweb.projekat.soap;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import xmlweb.projekat.model.dtos.CommentDTO;
import xmlweb.projekat.service.interfaces.CommentServiceInterface;
import xmlweb.projekat.soap.models.comment.CommentRequest;
import xmlweb.projekat.soap.models.comment.CommentSOAP;
import xmlweb.projekat.soap.models.comment.GetCommentRequest;
import xmlweb.projekat.soap.models.comment.GetCommentResponse;

@Endpoint
public class CommentEndpoint {

	private CommentServiceInterface service;

	@Autowired
	public CommentEndpoint(CommentServiceInterface service) {
		super();
		this.service = service;
	}
	
	@PayloadRoot(namespace = "http://xmlweb/projekat/soap/models/comment", localPart = "getCommentRequest")
	@ResponsePayload
	public GetCommentResponse getComment(@RequestPayload GetCommentRequest request) {
		GetCommentResponse response = new GetCommentResponse();
		
		ArrayList<CommentDTO> lista = service.ReadAll();
		ArrayList<CommentSOAP> retVal = new ArrayList<CommentSOAP>();
		
		for(CommentDTO dto : lista) {
			CommentSOAP s = new CommentSOAP();
			s.setAccomodationId(dto.getAccomodation());
			s.setApproved(dto.getApproved());
			s.setAuthor((int) dto.getAuthor());
			s.setCommentId(dto.getId());
			s.setContent(dto.getContent());
			s.setEntityVersion(dto.getVersion());
			s.setRating(dto.getRating());
		}
		
		for(CommentRequest req : request.getComment()) {
			Iterator<CommentSOAP> itr = retVal.iterator();
			while(itr.hasNext()) {
				CommentSOAP u = itr.next();
				if(req.getEntityId() == u.getCommentId() && req.getEntityVersion() == u.getEntityVersion()) {
					itr.remove();
					break;
				}
			}
		
		}
		
		response.getComment().addAll(retVal);
		return response;
	}
	
	
}
