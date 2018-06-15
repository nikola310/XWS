package xmlweb.projekat.service.interfaces;

import java.util.List;

import xmlweb.projekat.model.dtos.ReservationDTO;

/**
 * @author Nikola
 *
 */
public interface ReservationServiceInterface extends ServiceInterface<ReservationDTO> {
	
	List<ReservationDTO> findReservationsBetweenDates(long checkInDate, long checkOutDate);
}
