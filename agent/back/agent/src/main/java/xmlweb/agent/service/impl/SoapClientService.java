package xmlweb.agent.service.impl;

import org.springframework.oxm.Marshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import xmlweb.agent.service.interfaces.SoapClientServiceInterface;
import xmlweb.agent.soap.location.GetLocationRequest;
import xmlweb.agent.soap.location.GetLocationResponse;
import xmlweb.agent.soap.location.LocationSOAP;

@Service
public class SoapClientService extends WebServiceGatewaySupport implements SoapClientServiceInterface {

	@Override
	public void SyncDB() {
		
		this.UpdateLocations();
		
	}

	@Override
	public void UpdateLocations() {
		GetLocationRequest glreq = new GetLocationRequest();
		
		WebServiceTemplate wst = new WebServiceTemplate();
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		//marshaller.setJaxbContextProperties();
		//marshaller.setContextPath("http://localhost:8089/booking/ws/location.wsdl");
		marshaller.setContextPath("xmlweb.agent.soap.location");
		wst.setDefaultUri("http://localhost:8089/booking/ws/location.wsdl");
		wst.setMarshaller(marshaller);
		wst.setUnmarshaller(marshaller);
		GetLocationResponse glres = (GetLocationResponse) wst.marshalSendAndReceive(glreq);
		for(LocationSOAP loc : glres.getEntity()) {
			System.out.println(loc.getCity());
			System.out.println(loc.getState());
		}
		
	}

	
}
