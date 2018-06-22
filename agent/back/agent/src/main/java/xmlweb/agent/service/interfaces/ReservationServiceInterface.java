package xmlweb.agent.service.interfaces;

import java.util.List;

import xmlweb.agent.model.dtos.ReservationDTO;

/**
 * @author Nikola
 *
 */
public interface ReservationServiceInterface extends ServiceInterface<ReservationDTO> {
	
	List<ReservationDTO> findReservationsBetweenDates(long checkInDate, long checkOutDate);
}
