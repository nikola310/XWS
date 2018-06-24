package xmlweb.agent.service.interfaces;

import xmlweb.agent.model.Accomodation;
import xmlweb.agent.model.Message;
import xmlweb.agent.model.Reservation;

public interface SoapClientServiceInterface {
	
	boolean NewAccomodation(Accomodation a);
	boolean RealisedReservation(Long reservId);
	boolean ReserveAccomodation(Reservation r);
	boolean SendMessage(Message m);

}
