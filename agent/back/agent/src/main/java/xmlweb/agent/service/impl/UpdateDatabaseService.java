package xmlweb.agent.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import xmlweb.agent.model.AccomodationType;
import xmlweb.agent.model.Location;
import xmlweb.agent.model.UserType;
import xmlweb.agent.model.dtos.BonusServiceDTO;
import xmlweb.agent.model.dtos.MessageDTO;
import xmlweb.agent.model.dtos.PictureDTO;
import xmlweb.agent.model.dtos.PriceDTO;
import xmlweb.agent.model.dtos.ReservationDTO;
import xmlweb.agent.model.dtos.UserDTO;
import xmlweb.agent.service.interfaces.AccomodationTypeServiceInterface;
import xmlweb.agent.service.interfaces.BonusServiceInterface;
import xmlweb.agent.service.interfaces.LocationServiceInterface;
import xmlweb.agent.service.interfaces.MessageServiceInterface;
import xmlweb.agent.service.interfaces.PictureServiceInterface;
import xmlweb.agent.service.interfaces.PriceServiceInterface;
import xmlweb.agent.service.interfaces.ReservationServiceInterface;
import xmlweb.agent.service.interfaces.UpdateDatabaseServiceInterface;
import xmlweb.agent.service.interfaces.UserServiceInterface;
import xmlweb.agent.soap.models.accomodation_type.AccomodationTypeRequest;
import xmlweb.agent.soap.models.accomodation_type.AccomodationTypeSOAP;
import xmlweb.agent.soap.models.accomodation_type.GetAccomodationTypeRequest;
import xmlweb.agent.soap.models.accomodation_type.GetAccomodationTypeResponse;
import xmlweb.agent.soap.models.bonus_service.BonusServiceRequest;
import xmlweb.agent.soap.models.bonus_service.BonusServiceSOAP;
import xmlweb.agent.soap.models.bonus_service.GetBonusServiceRequest;
import xmlweb.agent.soap.models.bonus_service.GetBonusServiceResponse;
import xmlweb.agent.soap.models.location.GetLocationRequest;
import xmlweb.agent.soap.models.location.GetLocationResponse;
import xmlweb.agent.soap.models.location.LocationRequest;
import xmlweb.agent.soap.models.location.LocationSOAP;
import xmlweb.agent.soap.models.message.GetMessageRequest;
import xmlweb.agent.soap.models.message.GetMessageResponse;
import xmlweb.agent.soap.models.message.MessageRequest;
import xmlweb.agent.soap.models.message.MessageSOAP;
import xmlweb.agent.soap.models.picture.GetPictureRequest;
import xmlweb.agent.soap.models.picture.GetPictureResponse;
import xmlweb.agent.soap.models.picture.PictureRequest;
import xmlweb.agent.soap.models.picture.PictureSOAP;
import xmlweb.agent.soap.models.price.GetPriceRequest;
import xmlweb.agent.soap.models.price.GetPriceResponse;
import xmlweb.agent.soap.models.price.PriceRequest;
import xmlweb.agent.soap.models.price.PriceSOAP;
import xmlweb.agent.soap.models.reservation.GetReservationRequest;
import xmlweb.agent.soap.models.reservation.GetReservationResponse;
import xmlweb.agent.soap.models.reservation.ReservationRequest;
import xmlweb.agent.soap.models.reservation.ReservationSOAP;
import xmlweb.agent.soap.models.user.GetUserRequest;
import xmlweb.agent.soap.models.user.GetUserResponse;
import xmlweb.agent.soap.models.user.UserRequest;
import xmlweb.agent.soap.models.user.UserSOAP;

@Service
public class UpdateDatabaseService extends WebServiceGatewaySupport implements UpdateDatabaseServiceInterface {

	@Autowired
	private LocationServiceInterface locationService;
	
	@Autowired
	private MessageServiceInterface messageService;
	
	@Autowired
	private UserServiceInterface userService;
	
	@Autowired
	private PictureServiceInterface pictureService;
	
	@Autowired
	private PriceServiceInterface priceService;
	
	@Autowired
	private ReservationServiceInterface reservationService;
	
	@Autowired
	private AccomodationTypeServiceInterface accomodationTypeService;
	
	@Autowired
	private BonusServiceInterface bonusService;
	
	@Override
	public void SyncDB() {
		
		this.UpdateAccomodationTypes();
		this.UpdateLocations();
		this.UpdateBonusServices();
		this.UpdateAccomodations();
		this.UpdateAccomodationBonusServices();
		this.UpdatePrices();
		this.UpdatePictures();
		this.UpdateUsers();
		this.UpdateReservations();
		this.UpdateAccomodationAgents();
		this.UpdateComments();
		this.UpdateMessages();
		
	}

	@Override
	public void UpdateLocations() {
		ArrayList<Location> queryList = locationService.ReadAll();
		GetLocationRequest glreq = new GetLocationRequest();
		
		for(Location location : queryList) {
			LocationRequest lr = new LocationRequest();
			lr.setEntityId((int) location.getId());
			lr.setEntityVersion(location.getVersion());
			glreq.getLocationRequest().add(lr);
		}
		
		WebServiceTemplate wst = new WebServiceTemplate();
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("xmlweb.agent.soap.models.location");
		wst.setDefaultUri("http://localhost:8089/booking/ws/location.wsdl");
		wst.setMarshaller(marshaller);
		wst.setUnmarshaller(marshaller);
		GetLocationResponse glres = (GetLocationResponse) wst.marshalSendAndReceive(glreq);
		for(LocationSOAP loc : glres.getEntity()) {
			
			Location dto = new Location();
			dto.setId(loc.getLocationId());
			dto.setVersion(loc.getEntityVersion());
			dto.setState(loc.getState());
			dto.setCity(loc.getCity());
			dto.setStreetName(loc.getStreetName());
			dto.setStreetNumber(loc.getStreetNumber());
			dto.setAccomodations(new ArrayList<>());
			
			if(locationService.Read(loc.getLocationId()) == null) {
				locationService.Create(dto);
			} else {
				locationService.Update(dto);
			}
		}
	}

	@Override
	public void UpdateMessages() {
		ArrayList<MessageDTO> queryList = messageService.ReadAll();
		GetMessageRequest glreq = new GetMessageRequest();
		
		for(MessageDTO m : queryList) {
			MessageRequest mr = new MessageRequest();
			mr.setEntityId((int) m.getId());
			mr.setEntityVersion(m.getVersion());
			glreq.getMessage().add(mr);
		}
		
		WebServiceTemplate wst = new WebServiceTemplate();
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("xmlweb.agent.soap.models.message");
		wst.setDefaultUri("http://localhost:8089/booking/ws/message.wsdl");
		wst.setMarshaller(marshaller);
		wst.setUnmarshaller(marshaller);
		
		GetMessageResponse glres = (GetMessageResponse) wst.marshalSendAndReceive(glreq);
		
		for(MessageSOAP m : glres.getMessage()) {
			MessageDTO mdto = new MessageDTO();
			mdto.setContent(m.getContent());
			mdto.setId(m.getMessageId());
			mdto.setReceiver(m.getReceiverId());
			mdto.setSender(m.getSenderId());
			mdto.setVersion(m.getEntityVersion());
			if(messageService.Read(m.getMessageId()) == null) {
				messageService.Create(mdto);
			} else {
				messageService.Update(mdto);
			}
		}
				
	}

	@Override
	public void UpdateUsers() {
		ArrayList<UserDTO> queryList = userService.ReadAll();
		GetUserRequest glreq =  new GetUserRequest();
		
		for(UserDTO us : queryList) {
			UserRequest ur = new UserRequest();
			ur.setEntityId((int) us.getId());
			ur.setEntityVersion(us.getVersion());
			glreq.getUser().add(ur);
		}
		
		WebServiceTemplate wst = new WebServiceTemplate();
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("xmlweb.agent.soap.models.user");
		wst.setDefaultUri("http://localhost:8089/booking/ws/user.wsdl");
		wst.setMarshaller(marshaller);
		wst.setUnmarshaller(marshaller);
		GetUserResponse glres = (GetUserResponse) wst.marshalSendAndReceive(glreq);
		for(UserSOAP us : glres.getUser()) {
			UserDTO udto = new UserDTO();
			udto.setActive(us.isActive());
			udto.setAgentLocation(us.getAgentAddress());
			udto.setFirstName(us.getFirstName());
			udto.setId(us.getUserId());
			udto.setLastName(us.getLastName());
			udto.setPassword(us.getPassword());
			udto.setPid(us.getPid());
			udto.setUserName(us.getUserName());
			udto.setUserType(UserType.valueOf(us.getUserType()));
			udto.setVersion(us.getEntityVersion());
			
			if(userService.Read(us.getUserId()) == null) {
				userService.Create(udto);
			} else {
				userService.Update(udto);
			}	
		}
				
	}

	@Override
	public void UpdatePictures() {
		ArrayList<PictureDTO> queryList = pictureService.ReadAll();
		GetPictureRequest glreq = new GetPictureRequest();
		
		for(PictureDTO p : queryList) {
			PictureRequest pr = new PictureRequest();
			pr.setEntityId((int) p.getId());
			pr.setEntityVersion(p.getVersion());
			glreq.getPictureRequest().add(pr);
		}
		
		WebServiceTemplate wst = new WebServiceTemplate();
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("xmlweb.agent.soap.models.picture");
		wst.setDefaultUri("http://localhost:8089/booking/ws/picture.wsdl");
		wst.setMarshaller(marshaller);
		wst.setUnmarshaller(marshaller);
		
		GetPictureResponse glres = (GetPictureResponse) wst.marshalSendAndReceive(glreq);
		for(PictureSOAP ps : glres.getEntity()) {
			PictureDTO pd = new PictureDTO();
			pd.setAccomodation(ps.getAccomodationId());
			pd.setContent(ps.getContent());
			pd.setId(ps.getPictureId());
			pd.setVersion(ps.getEntityVersion());
			
			if(pictureService.Read(ps.getPictureId()) == null) {
				pictureService.Create(pd);
			} else {
				pictureService.Update(pd);
			}
			
		}
		
	}

	@Override
	public void UpdatePrices() {
		ArrayList<PriceDTO> queryList = priceService.ReadAll();
		GetPriceRequest glreq = new GetPriceRequest();
		
		for(PriceDTO pd : queryList) {
			PriceRequest pr = new PriceRequest();
			pr.setEntityId((int) pd.getId());
			pr.setEntityVersion(pd.getVersion());
			glreq.getPriceRequest().add(pr);
		}
		
		WebServiceTemplate wst = new WebServiceTemplate();
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("xmlweb.agent.soap.models.price");
		wst.setDefaultUri("http://localhost:8089/booking/ws/price.wsdl");
		wst.setMarshaller(marshaller);
		wst.setUnmarshaller(marshaller);
		
		GetPriceResponse glres = (GetPriceResponse) wst.marshalSendAndReceive(glreq);
		for(PriceSOAP ps :  glres.getEntity()) {
			PriceDTO pd = new PriceDTO();
			pd.setAccomodation(ps.getAccomodationId());
			ps.setEndDate(ps.getEndDate());
			ps.setEntityVersion(ps.getEntityVersion());
			ps.setPrice(ps.getPrice());
			ps.setPriceId(ps.getPriceId());
			ps.setStartDate(ps.getStartDate());
			
			if(priceService.Read(ps.getPriceId()) == null) {
				priceService.Create(pd);
			} else {
				priceService.Update(pd);
			}
			
		}
		
	}

	@Override
	public void UpdateReservations() {
		ArrayList<ReservationDTO> queryList = reservationService.ReadAll();
		GetReservationRequest glreq = new GetReservationRequest();
		
		for(ReservationDTO rd : queryList) {
			ReservationRequest rr = new ReservationRequest();
			rr.setEntityId(rd.getId());
			rr.setEntityVersion(rd.getVersion());
			glreq.getReservations().add(rr);
		}
				
		WebServiceTemplate wst = new WebServiceTemplate();
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("xmlweb.agent.soap.models.reservation");
		wst.setDefaultUri("http://localhost:8089/booking/ws/reservation.wsdl");
		wst.setMarshaller(marshaller);
		wst.setUnmarshaller(marshaller);
		GetReservationResponse glres = (GetReservationResponse) wst.marshalSendAndReceive(glreq);
		for(ReservationSOAP rs : glres.getReservations()) {
			ReservationDTO rd = new ReservationDTO();
			rd.setAccomodation(rs.getAccomodation());
			rd.setEndDate(rs.getEndDate());
			rd.setId(rs.getReservationId());
			rd.setNumberOfPersons(rs.getNumberOfPersons());
			//rd.setRealized(rs.);
			rd.setStartDate(rs.getStartDate());
			//rd.setUser(rs);
			rd.setVersion(rs.getEntityVersion());
			
			if(reservationService.Read(rs.getReservationId()) == null) {
				reservationService.Create(rd);
			} else {
				reservationService.Update(rd);
			}	
		}
		
	}

	@Override
	public void UpdateAccomodationTypes() {
		ArrayList<AccomodationType> queryList = accomodationTypeService.ReadAll();
		GetAccomodationTypeRequest glreq = new GetAccomodationTypeRequest();
		
		for(AccomodationType at : queryList) {
			AccomodationTypeRequest atr = new AccomodationTypeRequest();
			atr.setEntityId((int) at.getId());
			atr.setEntityVersion(at.getVersion());	
			glreq.getRequestEntity().add(atr);
		}
		
		WebServiceTemplate wst = new WebServiceTemplate();
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("xmlweb.agent.soap.models.accomodation_type");
		wst.setDefaultUri("http://localhost:8089/booking/ws/accomodation_type.wsdl");
		wst.setMarshaller(marshaller);
		wst.setUnmarshaller(marshaller);
		GetAccomodationTypeResponse glres = (GetAccomodationTypeResponse) wst.marshalSendAndReceive(glreq);
		
		for(AccomodationTypeSOAP ats : glres.getEntity()) {
			AccomodationType at = new AccomodationType();
			at.setId(ats.getAccomodationTypeId());
			at.setName(ats.getName());
			at.setVersion(ats.getEntityVersion());
			
			if(accomodationTypeService.Read(ats.getAccomodationTypeId()) == null) {
				accomodationTypeService.Create(at);
			} else {
				accomodationTypeService.Update(at);
			}
			
		}

	}

	@Override
	public void UpdateBonusServices() {
		ArrayList<BonusServiceDTO> queryList = (ArrayList<BonusServiceDTO>) bonusService.findAllBonusServices();
		GetBonusServiceRequest glreq = new GetBonusServiceRequest();
		
		for(BonusServiceDTO bsd : queryList) {
			BonusServiceRequest bsr = new BonusServiceRequest();
			bsr.setEntityId((int) bsd.getId());
			bsr.setEntityVersion(bsd.getVersion());
			glreq.getBonusServiceRequest().add(bsr);
		}
		
		WebServiceTemplate wst = new WebServiceTemplate();
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("xmlweb.agent.soap.models.bonus_service");
		wst.setDefaultUri("http://localhost:8089/booking/ws/bonus_service.wsdl");
		wst.setMarshaller(marshaller);
		wst.setUnmarshaller(marshaller);
		GetBonusServiceResponse glres = (GetBonusServiceResponse) wst.marshalSendAndReceive(glreq);
		
		for(BonusServiceSOAP bs : glres.getEntity()) {
			BonusServiceDTO bst = new BonusServiceDTO(); 
			bst.setId(bs.getBonusServiceId());
			bst.setName(bs.getName());
			bst.setVersion(bs.getEntityVersion());
			
			if(bonusService.Read(bs.getBonusServiceId()) == null) {
				bonusService.Create(bst);
			} else {
				bonusService.Update(bst);
			}	
		}
		
	}

	@Override
	public void UpdateAccomodations() {
		ArrayList<Location> queryList = locationService.ReadAll();
		GetLocationRequest glreq = new GetLocationRequest();
		
		for(Location location : queryList) {
			LocationRequest lr = new LocationRequest();
			lr.setEntityId((int) location.getId());
			lr.setEntityVersion(location.getVersion());
			glreq.getLocationRequest().add(lr);
		}
		
		WebServiceTemplate wst = new WebServiceTemplate();
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("xmlweb.agent.soap.models.location");
		wst.setDefaultUri("http://localhost:8089/booking/ws/location.wsdl");
		wst.setMarshaller(marshaller);
		wst.setUnmarshaller(marshaller);
		GetLocationResponse glres = (GetLocationResponse) wst.marshalSendAndReceive(glreq);
		for(LocationSOAP loc : glres.getEntity()) {
			
			Location dto = new Location();
			dto.setId(loc.getLocationId());
			dto.setVersion(loc.getEntityVersion());
			dto.setState(loc.getState());
			dto.setCity(loc.getCity());
			dto.setStreetName(loc.getStreetName());
			dto.setStreetNumber(loc.getStreetNumber());
			dto.setAccomodations(new ArrayList<>());
			
			if(locationService.Read(loc.getLocationId()) == null) {
				locationService.Create(dto);
			} else {
				locationService.Update(dto);
			}
		}
		
	}

	@Override
	public void UpdateAccomodationBonusServices() {
		ArrayList<Location> queryList = locationService.ReadAll();
		GetLocationRequest glreq = new GetLocationRequest();
		
		for(Location location : queryList) {
			LocationRequest lr = new LocationRequest();
			lr.setEntityId((int) location.getId());
			lr.setEntityVersion(location.getVersion());
			glreq.getLocationRequest().add(lr);
		}
		
		WebServiceTemplate wst = new WebServiceTemplate();
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("xmlweb.agent.soap.models.location");
		wst.setDefaultUri("http://localhost:8089/booking/ws/location.wsdl");
		wst.setMarshaller(marshaller);
		wst.setUnmarshaller(marshaller);
		GetLocationResponse glres = (GetLocationResponse) wst.marshalSendAndReceive(glreq);
		for(LocationSOAP loc : glres.getEntity()) {
			
			Location dto = new Location();
			dto.setId(loc.getLocationId());
			dto.setVersion(loc.getEntityVersion());
			dto.setState(loc.getState());
			dto.setCity(loc.getCity());
			dto.setStreetName(loc.getStreetName());
			dto.setStreetNumber(loc.getStreetNumber());
			dto.setAccomodations(new ArrayList<>());
			
			if(locationService.Read(loc.getLocationId()) == null) {
				locationService.Create(dto);
			} else {
				locationService.Update(dto);
			}
		}
		
	}

	@Override
	public void UpdateAccomodationAgents() {
		ArrayList<Location> queryList = locationService.ReadAll();
		GetLocationRequest glreq = new GetLocationRequest();
		
		for(Location location : queryList) {
			LocationRequest lr = new LocationRequest();
			lr.setEntityId((int) location.getId());
			lr.setEntityVersion(location.getVersion());
			glreq.getLocationRequest().add(lr);
		}
		
		WebServiceTemplate wst = new WebServiceTemplate();
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("xmlweb.agent.soap.models.location");
		wst.setDefaultUri("http://localhost:8089/booking/ws/location.wsdl");
		wst.setMarshaller(marshaller);
		wst.setUnmarshaller(marshaller);
		GetLocationResponse glres = (GetLocationResponse) wst.marshalSendAndReceive(glreq);
		for(LocationSOAP loc : glres.getEntity()) {
			
			Location dto = new Location();
			dto.setId(loc.getLocationId());
			dto.setVersion(loc.getEntityVersion());
			dto.setState(loc.getState());
			dto.setCity(loc.getCity());
			dto.setStreetName(loc.getStreetName());
			dto.setStreetNumber(loc.getStreetNumber());
			dto.setAccomodations(new ArrayList<>());
			
			if(locationService.Read(loc.getLocationId()) == null) {
				locationService.Create(dto);
			} else {
				locationService.Update(dto);
			}
		}
		
	}

	@Override
	public void UpdateComments() {
		ArrayList<Location> queryList = locationService.ReadAll();
		GetLocationRequest glreq = new GetLocationRequest();
		
		for(Location location : queryList) {
			LocationRequest lr = new LocationRequest();
			lr.setEntityId((int) location.getId());
			lr.setEntityVersion(location.getVersion());
			glreq.getLocationRequest().add(lr);
		}
		
		WebServiceTemplate wst = new WebServiceTemplate();
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("xmlweb.agent.soap.models.location");
		wst.setDefaultUri("http://localhost:8089/booking/ws/location.wsdl");
		wst.setMarshaller(marshaller);
		wst.setUnmarshaller(marshaller);
		GetLocationResponse glres = (GetLocationResponse) wst.marshalSendAndReceive(glreq);
		for(LocationSOAP loc : glres.getEntity()) {
			
			Location dto = new Location();
			dto.setId(loc.getLocationId());
			dto.setVersion(loc.getEntityVersion());
			dto.setState(loc.getState());
			dto.setCity(loc.getCity());
			dto.setStreetName(loc.getStreetName());
			dto.setStreetNumber(loc.getStreetNumber());
			dto.setAccomodations(new ArrayList<>());
			
			if(locationService.Read(loc.getLocationId()) == null) {
				locationService.Create(dto);
			} else {
				locationService.Update(dto);
			}
		}
		
	}

	
}
