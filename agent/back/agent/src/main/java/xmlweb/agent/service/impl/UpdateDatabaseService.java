package xmlweb.agent.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import xmlweb.agent.model.Accomodation;
import xmlweb.agent.model.AccomodationAgent;
import xmlweb.agent.model.AccomodationType;
import xmlweb.agent.model.BonusService;
import xmlweb.agent.model.Comment;
import xmlweb.agent.model.Location;
import xmlweb.agent.model.UserType;
import xmlweb.agent.model.dtos.MessageDTO;
import xmlweb.agent.model.dtos.PictureDTO;
import xmlweb.agent.model.dtos.PriceDTO;
import xmlweb.agent.model.dtos.ReservationDTO;
import xmlweb.agent.model.dtos.UserDTO;
import xmlweb.agent.repository.AccomodationRepository;
import xmlweb.agent.service.interfaces.AccomodationBonusServiceServiceInterface;
import xmlweb.agent.service.interfaces.AccomodationServiceInterface;
import xmlweb.agent.service.interfaces.AccomodationTypeServiceInterface;
import xmlweb.agent.service.interfaces.BonusServiceInterface;
import xmlweb.agent.service.interfaces.LocationServiceInterface;
import xmlweb.agent.service.interfaces.MessageServiceInterface;
import xmlweb.agent.service.interfaces.PictureServiceInterface;
import xmlweb.agent.service.interfaces.PriceServiceInterface;
import xmlweb.agent.service.interfaces.ReservationServiceInterface;
import xmlweb.agent.service.interfaces.UpdateDatabaseServiceInterface;
import xmlweb.agent.service.interfaces.UserServiceInterface;
import xmlweb.agent.soap.models.accomodation.AccomodationRequest;
import xmlweb.agent.soap.models.accomodation.AccomodationSOAP;
import xmlweb.agent.soap.models.accomodation.GetAccomodationRequest;
import xmlweb.agent.soap.models.accomodation.GetAccomodationResponse;
import xmlweb.agent.soap.models.accomodation_bonus_service.AccomodationBonusServiceRequest;
import xmlweb.agent.soap.models.accomodation_bonus_service.AccomodationBonusServiceSOAP;
import xmlweb.agent.soap.models.accomodation_bonus_service.GetAccomodationBonusServiceRequest;
import xmlweb.agent.soap.models.accomodation_bonus_service.GetAccomodationBonusServiceResponse;
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
	
	@Autowired
	private AccomodationBonusServiceServiceInterface accomodationBonusServiceService;
	
	@Autowired
	private AccomodationServiceInterface accomodationService;
	
	@Autowired
	private AccomodationRepository accomodationRepo;
	
	@Override
	public void SyncDB() {	
		this.UpdateAccomodationTypes();
		this.UpdateLocations();
		this.UpdateBonusServices();
		this.UpdateAccomodations();
		System.out.println("Puca 4");
		//this.UpdateAccomodationBonusServices();
		System.out.println("Puca 5");
		this.UpdatePrices();
		System.out.println("Puca 6");
		//this.UpdatePictures();
		System.out.println("Puca 7");
//		this.UpdateUsers();
//		this.UpdateReservations();
//		this.UpdateAccomodationAgents();
//		this.UpdateComments();
		//this.UpdateMessages();
		
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
		System.out.println("ocacea");
		ArrayList<MessageDTO> queryList = messageService.ReadAll();
		System.out.println("puba 00");
		GetMessageRequest glreq = new GetMessageRequest();
		
		for(MessageDTO m : queryList) {
			MessageRequest mr = new MessageRequest();
			mr.setEntityId((int) m.getId());
			mr.setEntityVersion(m.getVersion());
			glreq.getMessage().add(mr);
		}
		System.out.println("pucoo 01");
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
			System.out.println("kraj puca 012");
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
		System.out.println("puca 000");
		ArrayList<PriceDTO> queryList = priceService.ReadAll();
		GetPriceRequest glreq = new GetPriceRequest();
		
		for(PriceDTO pd : queryList) {
			PriceRequest pr = new PriceRequest();
			pr.setEntityId((int) pd.getId());
			pr.setEntityVersion(pd.getVersion());
			glreq.getPriceRequest().add(pr);
		}
		System.out.println("puca 001");
		WebServiceTemplate wst = new WebServiceTemplate();
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("xmlweb.agent.soap.models.price");
		wst.setDefaultUri("http://localhost:8089/booking/ws/price.wsdl");
		wst.setMarshaller(marshaller);
		wst.setUnmarshaller(marshaller);
		System.out.println("puca 002");
		GetPriceResponse glres = (GetPriceResponse) wst.marshalSendAndReceive(glreq);
		System.out.println("puca 0022");
		for(PriceSOAP ps :  glres.getEntity()) {
			PriceDTO pd = new PriceDTO();
			pd.setAccomodation(ps.getAccomodationId());
			ps.setEndDate(ps.getEndDate());
			ps.setEntityVersion(ps.getEntityVersion());
			ps.setPrice(ps.getPrice());
			ps.setPriceId(ps.getPriceId());
			ps.setStartDate(ps.getStartDate());
			System.out.println("puca 003");
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
			
			if(accomodationTypeService.findOne(ats.getAccomodationTypeId()) == null) {
				accomodationTypeService.Create(at);
			} else {
				accomodationTypeService.Update(at);
			}
		}
	}

	@Override
	public void UpdateBonusServices() {
		ArrayList<BonusService> queryList = (ArrayList<BonusService>) bonusService.findAllBonusServices();
		GetBonusServiceRequest glreq = new GetBonusServiceRequest();
		
		for(BonusService bsd : queryList) {
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
			BonusService bst = new BonusService(); 
			bst.setId(bs.getBonusServiceId());
			bst.setName(bs.getName());
			bst.setVersion(bs.getEntityVersion());
			
			if(bonusService.findById(bs.getBonusServiceId()) == null) {
				bonusService.Create(bst);
			} else {
				bonusService.Update(bst);
			}	
		}
		
	}
	
	@Override
	public void UpdateAccomodations() {
		ArrayList<Accomodation> queryList = (ArrayList<Accomodation>) accomodationRepo.findAll();
		GetAccomodationRequest glreq = new GetAccomodationRequest();
		for(Accomodation ad : queryList) {
			AccomodationRequest ar = new AccomodationRequest();
			ar.setEntityId((int) ad.getId());
			ar.setEntityVersion(ad.getVersion());
			glreq.getAccomodationRequest().add(ar);
		}
		
		WebServiceTemplate wst = new WebServiceTemplate();
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("xmlweb.agent.soap.models.accomodation");
		wst.setDefaultUri("http://localhost:8089/booking/ws/accomodation.wsdl");
		wst.setMarshaller(marshaller);
		wst.setUnmarshaller(marshaller);
		GetAccomodationResponse glres = (GetAccomodationResponse) wst.marshalSendAndReceive(glreq);
		
		for(AccomodationSOAP as : glres.getEntity()) {
			Accomodation ad = new Accomodation();
			
			
			ad.setAccomodationAgent(new ArrayList<AccomodationAgent>());
			ad.setAccomodationName(as.getAccomodationName());
			ad.setAccomodationType(accomodationTypeService.finyByName(as.getAccomodationType()));
			ad.setBonusServices(new ArrayList<BonusService>());
			ad.setCapacity(as.getCapacity());
			ad.setCategory(as.getCategory());
			ad.setComments(new ArrayList<Comment>());
			
			ad.setLocation(locationService.Read(as.getLocationId()));
			//ad.setPrices(new ArrayList<Price>());
			ad.setVersion((int) as.getVersion());
			
			if(accomodationService.findOne(as.getAccomodationId()) == null) {
				accomodationService.Create(ad);
			} else {
				accomodationService.Update(ad);
			}
		}
	}
	
	@Override
	public void UpdateAccomodationBonusServices() {
		HashMap<Long, ArrayList<Long>> queryList = accomodationBonusServiceService.findAll();
		GetAccomodationBonusServiceRequest glreq = new GetAccomodationBonusServiceRequest();
		
		System.out.println("lista duzina " + queryList.size());
		for(Long accL : queryList.keySet()) {
			for(Long bnsL : queryList.get(accL)) {
				AccomodationBonusServiceRequest as = new AccomodationBonusServiceRequest();
				as.setAccomodationId(accL);
				as.setBonusServiceId(bnsL);
				glreq.getAccomodationBonusServiceRequest().add(as);
			}
		}
		
		WebServiceTemplate wst = new WebServiceTemplate();
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("xmlweb.agent.soap.models.accomodation_bonus_service");
		wst.setDefaultUri("http://localhost:8089/booking/ws/accomodation_bonus_service.wsdl");
		wst.setMarshaller(marshaller);
		wst.setUnmarshaller(marshaller);
		
		System.out.println("puca 00000");
		GetAccomodationBonusServiceResponse glres =  (GetAccomodationBonusServiceResponse) wst.marshalSendAndReceive(glreq);
		
		System.out.println("puca 000111");
		for(AccomodationBonusServiceSOAP abs : glres.getAccomodationBonusServiceResponse()) {
			
			System.out.println("puca 1111111");
			accomodationBonusServiceService.Create(abs.getAccomodationId(), abs.getBonusServiceId());
			
		}
	}

	@Override
	public void UpdateAccomodationAgents() {
//		ArrayList<AccomodationAgent> queryList = (ArrayList<AccomodationAgent>) accomodationAgentRepo.findAll();
//		GetAccomodationAgentRequest glreq = new GetAccomodationAgentRequest();
//		
//		for(AccomodationAgent aa : queryList) {
//			AccomodationAgentRequest aar = new AccomodationAgentRequest();
//			aar.setEntityId((int) aa.getId());
//			aar.setEntityVersion(aa.getVersion());
//			glreq.getAccomodationAgent().add(aar);
//		}
//		
//		WebServiceTemplate wst = new WebServiceTemplate();
//		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//		marshaller.setContextPath("xmlweb.agent.soap.models.accomodation_agent");
//		wst.setDefaultUri("http://localhost:8089/booking/ws/accomodation_agent.wsdl");
//		wst.setMarshaller(marshaller);
//		wst.setUnmarshaller(marshaller);
//		
//		GetAccomodationAgentResponse glres = (GetAccomodationAgentResponse) wst.marshalSendAndReceive(glreq);
//		for(AccomodationAgentSOAP aas : glres.getAccomodationAgent()) {
//			AccomodationAgent aa = new AccomodationAgent();
//			aa.setAccomodation(accomodationRepo.getOne(aas.getAccomodationId()));
//			aa.setAgent(userRepo.getOne(aas.getAccomodationAgentId()));
//			aa.setId(aas.getAccomodationAgentId());
//			aa.setMainAgent(aas.isMainAgent());
//			aa.setVersion(aas.getEntityVersion());
//			accomodationAgentRepo.save(aa);
//		}
//		
	}
	
	@Override
	public void UpdateComments() {
//		ArrayList<Comment> queryList = (ArrayList<Comment>) commentRepo.findAll();
//		GetCommentRequest glreq = new GetCommentRequest();
//		
//		for(Comment c : queryList) {
//			CommentRequest cr = new CommentRequest();
//			cr.setEntityId((int) c.getId());
//			cr.setEntityVersion(c.getVersion());
//			glreq.getComment().add(cr);
//		}
//		
//		WebServiceTemplate wst = new WebServiceTemplate();
//		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//		marshaller.setContextPath("xmlweb.agent.soap.models.comment");
//		wst.setDefaultUri("http://localhost:8089/booking/ws/comment.wsdl");
//		wst.setMarshaller(marshaller);
//		wst.setUnmarshaller(marshaller);
//		
//		GetCommentResponse glres = (GetCommentResponse) wst.marshalSendAndReceive(glreq);
//		
//		for(CommentSOAP cs : glres.getComment()) {
//			Comment c = new Comment();
//			
//			c.setAccomodation(accomodationRepo.getOne(cs.getAccomodationId()));
//			//c.setApproved(aaa);
//			c.setAuthor(userRepo.getOne((long) cs.getAuthor()));
//			c.setContent(cs.getContent());
//			c.setId(cs.getCommentId());
//			c.setRating(cs.getRating());
//			c.setVersion(cs.getEntityVersion());
//			
//			commentRepo.save(c);
//		}
//		
	}
	
}
