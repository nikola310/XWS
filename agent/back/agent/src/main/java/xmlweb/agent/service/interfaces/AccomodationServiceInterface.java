package xmlweb.agent.service.interfaces;

import java.util.List;

import xmlweb.agent.model.Accomodation;
import xmlweb.agent.model.Reservation;
import xmlweb.agent.model.dtos.AccomodationDTO;

public interface AccomodationServiceInterface {

	List<AccomodationDTO> findAllAccomodation();
	
	List<AccomodationDTO> findAccomodationByDestination(String destination);
	
	List<Reservation> findReservationsByAccomodation(Long id);
	
	List<AccomodationDTO> findAccomodationsByBonusService(Long bonusService);
	
	List<AccomodationDTO> findAccomodationsByType(long typeId);
	
	List<AccomodationDTO> findAccomodationsByCategory(int categoryId);
	
	Accomodation findOne(long id);
	
	void Create(Accomodation a);
	
	void Update(Accomodation a);
	
}
