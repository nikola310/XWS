package xmlweb.projekat.soap;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import xmlweb.projekat.model.dtos.PriceDTO;
import xmlweb.projekat.service.interfaces.PriceServiceInterface;
import xmlweb.projekat.soap.models.price.GetPriceRequest;
import xmlweb.projekat.soap.models.price.GetPriceResponse;
import xmlweb.projekat.soap.models.price.PriceRequest;
import xmlweb.projekat.soap.models.price.PriceSOAP;

/**
 * @author Nikola
 *
 */
public class PriceEndpoint {

	private PriceServiceInterface service;

	@Autowired
	public PriceEndpoint(PriceServiceInterface service) {
		super();
		this.service = service;
	}

	@PayloadRoot(namespace = "http://xmlweb/projekat/soap/models/price", localPart = "getPriceRequest")
	@ResponsePayload
	public GetPriceResponse getPrice(@RequestPayload GetPriceRequest request) {
		GetPriceResponse response = new GetPriceResponse();

		ArrayList<PriceDTO> listaDTO = service.ReadAll();
		ArrayList<PriceSOAP> retVal = new ArrayList<>();

		for (PriceDTO dto : listaDTO) {
			PriceSOAP sp = new PriceSOAP();
			sp.setAccomodationId(dto.getAccomodation());
			sp.setEndDate(dto.getEndDate());
			sp.setEntityVersion(dto.getVersion());
			sp.setPrice(dto.getPrice());
			sp.setPriceId(dto.getId());
			sp.setStartDate(dto.getStartDate());
			retVal.add(sp);
		}

		for (PriceRequest req : request.getPriceRequest()) {
			Iterator<PriceSOAP> itr = retVal.iterator();
			while (itr.hasNext()) {
				PriceSOAP u = itr.next();
				if (req.getEntityId() == u.getPriceId() && req.getEntityVersion() == u.getEntityVersion()) {
					itr.remove();
					break;
				}
			}
		}

		response.getEntity().addAll(retVal);
		return response;
	}
}
