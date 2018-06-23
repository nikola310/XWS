package xmlweb.projekat.service.interfaces;

import java.util.List;

import xmlweb.projekat.model.Accomodation;
import xmlweb.projekat.model.Reservation;
import xmlweb.projekat.model.dtos.AccomodationAdminDTO;
import xmlweb.projekat.model.dtos.AccomodationBonusDTO;
import xmlweb.projekat.model.dtos.AccomodationDTO;
import xmlweb.projekat.model.dtos.CategoryDTO;

public interface AccomodationServiceInterface {

	List<AccomodationDTO> findAllAccomodation();
	
	List<AccomodationDTO> findAccomodationByDestination(String destination);
	
	List<Reservation> findReservationsByAccomodation(Long id);
	
	List<AccomodationDTO> findAccomodationsByBonusService(Long bonusService);
	
	List<AccomodationDTO> findAccomodationsByType(long typeId);
	
	List<AccomodationDTO> findAccomodationsByCategory(int categoryId);
	
	List<AccomodationAdminDTO> forAdmin();
	
	boolean setCategory(CategoryDTO dto);

	List<AccomodationBonusDTO> findAllAccomodationBonus();
	
	boolean createAccomodation(Accomodation acco);
	
	AccomodationDTO getOne(long id);
	
}
