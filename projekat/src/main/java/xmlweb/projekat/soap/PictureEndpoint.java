package xmlweb.projekat.soap;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import xmlweb.projekat.model.dtos.PictureDTO;
import xmlweb.projekat.service.interfaces.PictureServiceInterface;
import xmlweb.projekat.soap.models.picture.GetPictureRequest;
import xmlweb.projekat.soap.models.picture.GetPictureResponse;
import xmlweb.projekat.soap.models.picture.PictureRequest;
import xmlweb.projekat.soap.models.picture.PictureSOAP;

/**
 * @author Nikola
 *
 */
@Endpoint
public class PictureEndpoint {

	private PictureServiceInterface service;

	@Autowired
	public PictureEndpoint(PictureServiceInterface service) {
		super();
		this.service = service;
	}
	
	@PayloadRoot(namespace = "http://xmlweb/projekat/soap/models/picture", localPart = "getPictureRequest")
	@ResponsePayload
	public GetPictureResponse getPicture(@RequestPayload GetPictureRequest request) {
		GetPictureResponse response = new GetPictureResponse();
		
		ArrayList<PictureDTO> listaDTO = service.ReadAll();
		ArrayList<PictureSOAP> retVal = new ArrayList<>();
		
		for(PictureDTO dto : listaDTO) {
			PictureSOAP sp = new PictureSOAP();
			sp.setAccomodationId(dto.getAccomodation());
			//sp.setContent(dto.getContent());
			sp.setEntityVersion(dto.getVersion());
			sp.setPictureId(dto.getId());
			retVal.add(sp);
		}
		
		for(PictureRequest req : request.getPictureRequest()) {
			Iterator<PictureSOAP> itr = retVal.iterator();
			while (itr.hasNext()) {
				PictureSOAP u = itr.next();
				if (req.getEntityId() == u.getPictureId() && req.getEntityVersion() == u.getEntityVersion()) {
					itr.remove();
					break;
				}
			}
		}
		
		response.getEntity().addAll(retVal);
		return response;
	}
}
