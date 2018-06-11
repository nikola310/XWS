package xmlweb.projekat.service.interfaces;

import java.util.HashMap;
import java.util.List;

import xmlweb.projekat.model.Accomodation;
import xmlweb.projekat.model.Reservation;
import xmlweb.projekat.model.dtos.AccomodationDTO;

public interface AccomodationServiceInterface {

	List<AccomodationDTO> findAllAccomodation();
	
	List<AccomodationDTO> findAccomodationByDestination(String destination);
	
	List<Reservation> findReservationsByAccomodation(Long id);
	
	List<AccomodationDTO> findAccomodationsByBonusService(Long bonusService);
	
	List<AccomodationDTO> findAccomodationsByType(long typeId);
	
	List<AccomodationDTO> findAccomodationsByCategory(int categoryId);
}