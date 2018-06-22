package xmlweb.projekat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xmlweb.projekat.model.Accomodation;
import xmlweb.projekat.model.Location;
import xmlweb.projekat.model.Reservation;
import xmlweb.projekat.model.dtos.AccomodationAdminDTO;
import xmlweb.projekat.model.dtos.AccomodationDTO;
import xmlweb.projekat.model.dtos.CategoryDTO;
import xmlweb.projekat.repository.AccomodationRepository;
import xmlweb.projekat.repository.ReservationRepository;
import xmlweb.projekat.service.interfaces.AccomodationServiceInterface;

@Service
public class AccomodationService implements AccomodationServiceInterface {

	@Autowired
	private AccomodationRepository accomodationRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	@Override
	public List<AccomodationDTO> findAllAccomodation() {
		// TODO Auto-generated method stub
		List<Accomodation> accomodationList = accomodationRepository.findAll();
		List<AccomodationDTO> accomodationDTOList = new ArrayList<AccomodationDTO>();

		for (Accomodation a : accomodationList) {
			AccomodationDTO aDTO = new AccomodationDTO(a.getId(), a.getAccomodationName(), a.getAccomodationType(),
					a.getCategory(), a.getBonusServices(), a.getComments(), a.getPrices(), a.getCapacity(),
					a.getLocation());
			accomodationDTOList.add(aDTO);
		}
		return accomodationDTOList;
	}

	@Override
	public List<AccomodationDTO> findAccomodationByDestination(String destination) {
		// TODO Auto-generated method stub
		List<Accomodation> accomodationList = accomodationRepository.findAccomodationByDestination(destination);
		List<AccomodationDTO> accomodationDTOList = new ArrayList<AccomodationDTO>();

		for (Accomodation a : accomodationList) {
			AccomodationDTO aDTO = new AccomodationDTO(a.getId(), a.getAccomodationName(), a.getAccomodationType(),
					a.getCategory(), a.getBonusServices(), a.getComments(), a.getPrices(), a.getCapacity(),
					a.getLocation());
			accomodationDTOList.add(aDTO);
		}
		return accomodationDTOList;
	}

	@Override
	public List<Reservation> findReservationsByAccomodation(Long id) {
		// TODO Auto-generated method stub
		return reservationRepository.findReservationsByAccomodation(id);
	}

	@Override
	public List<AccomodationDTO> findAccomodationsByBonusService(Long bonusService) {
		// TODO Auto-generated method stub
		List<Accomodation> accomodationList = new ArrayList<Accomodation>();
		accomodationList = accomodationRepository.findAccomodationByBonusService(bonusService);

		List<AccomodationDTO> accomodationDTOList = new ArrayList<AccomodationDTO>();

		if (accomodationList.get(0) != null) {
			for (Accomodation a : accomodationList) {
				AccomodationDTO aDTO = new AccomodationDTO(a.getId(), a.getAccomodationName(), a.getAccomodationType(),
						a.getCategory(), a.getBonusServices(), a.getComments(), a.getPrices(), a.getCapacity(),
						a.getLocation());
				accomodationDTOList.add(aDTO);
			}
		}
		return accomodationDTOList;
	}

	@Override
	public List<AccomodationDTO> findAccomodationsByType(long typeId) {
		// TODO Auto-generated method stub
		List<Accomodation> accomodationList = new ArrayList<Accomodation>();
		accomodationList = accomodationRepository.findAccomodationByType(typeId);
		List<AccomodationDTO> accomodationDTOList = new ArrayList<AccomodationDTO>();

		for (Accomodation a : accomodationList) {
			AccomodationDTO aDTO = new AccomodationDTO(a.getId(), a.getAccomodationName(), a.getAccomodationType(),
					a.getCategory(), a.getBonusServices(), a.getComments(), a.getPrices(), a.getCapacity(),
					a.getLocation());
			accomodationDTOList.add(aDTO);
		}

		return accomodationDTOList;
	}

	@Override
	public List<AccomodationDTO> findAccomodationsByCategory(int categoryId) {
		// TODO Auto-generated method stub
		List<Accomodation> accomodationList = new ArrayList<Accomodation>();
		accomodationList = accomodationRepository.findAccomodationByCategory(categoryId);
		List<AccomodationDTO> accomodationDTOList = new ArrayList<AccomodationDTO>();

		for (Accomodation a : accomodationList) {
			AccomodationDTO aDTO = new AccomodationDTO(a.getId(), a.getAccomodationName(), a.getAccomodationType(),
					a.getCategory(), a.getBonusServices(), a.getComments(), a.getPrices(), a.getCapacity(),
					a.getLocation());
			accomodationDTOList.add(aDTO);
		}

		return accomodationDTOList;
	}

	@Override
	public List<AccomodationAdminDTO> forAdmin() {
		try {
			List<Accomodation> entityList = accomodationRepository.findAll();
			ArrayList<AccomodationAdminDTO> dtoList = new ArrayList<>();
			for (Accomodation acc : entityList) {
				AccomodationAdminDTO dto = new AccomodationAdminDTO();
				dto.setAccomodationName(acc.getAccomodationName());
				dto.setCapacity(acc.getCapacity());
				dto.setCategory(acc.getCategory());
				dto.setId(acc.getId());
				dto.setVersion(acc.getVersion());
				dto.setAccomdoationType(acc.getAccomodationType().getName());
				Location loc = acc.getLocation();
				dto.setLocation(
						loc.getState() + " " + loc.getCity() + " " + loc.getStreetName() + " " + loc.getStreetNumber());
				dtoList.add(dto);
			}
			return dtoList;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean setCategory(CategoryDTO dto) {
		try {
			Accomodation toUpdate = accomodationRepository.getOne(dto.getId());

			if (toUpdate.getVersion() != dto.getVersion()) {
				return false;
			}

			toUpdate.setCategory(dto.getCategory());
			accomodationRepository.save(toUpdate);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
