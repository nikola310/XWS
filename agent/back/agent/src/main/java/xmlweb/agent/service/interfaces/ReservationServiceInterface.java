package xmlweb.agent.service.interfaces;

import java.util.List;

import xmlweb.agent.model.Reservation;
import xmlweb.agent.model.dtos.ReservationDTO;

/**
 * @author Nikola
 *
 */
public interface ReservationServiceInterface {
	
	List<ReservationDTO> findReservationsBetweenDates(long checkInDate, long checkOutDate);
	List<Reservation> readAll();
	Reservation getById(Long id);
	boolean Create(Reservation r);
	boolean Update(Reservation r);
}
