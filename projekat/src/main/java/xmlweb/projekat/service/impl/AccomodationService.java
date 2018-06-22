package xmlweb.projekat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xmlweb.projekat.model.Accomodation;
import xmlweb.projekat.model.BonusService;
import xmlweb.projekat.model.Reservation;
import xmlweb.projekat.model.dtos.AccomodationBonusDTO;
import xmlweb.projekat.model.dtos.AccomodationDTO;
import xmlweb.projekat.repository.AccomodationRepository;
import xmlweb.projekat.repository.BonusServiceRepository;
import xmlweb.projekat.repository.ReservationRepository;
import xmlweb.projekat.service.interfaces.AccomodationServiceInterface;

@Service
public class AccomodationService implements AccomodationServiceInterface {

	@Autowired
	private AccomodationRepository accomodationRepository;

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private BonusServiceRepository bonusServiceRepository;

	@Override
	public List<AccomodationDTO> findAllAccomodation() {
		// TODO Auto-generated method stub
		List<Accomodation> accomodationList = accomodationRepository.findAll();
		List<AccomodationDTO> accomodationDTOList = new ArrayList<AccomodationDTO>();

		for (Accomodation a : accomodationList) {
			AccomodationDTO aDTO = new AccomodationDTO(a.getId(), a.getAccomodationName(), a.getAccomodationType(), a.getCategory(),
					a.getBonusServices(), a.getComments(), a.getPrices(), a.getCapacity(), a.getLocation());
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
			AccomodationDTO aDTO = new AccomodationDTO(a.getId(), a.getAccomodationName(), a.getAccomodationType(), a.getCategory(),
					a.getBonusServices(), a.getComments(), a.getPrices(), a.getCapacity(), a.getLocation());
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
				AccomodationDTO aDTO = new AccomodationDTO(a.getId(), a.getAccomodationName(), a.getAccomodationType(), a.getCategory(),
						a.getBonusServices(), a.getComments(), a.getPrices(), a.getCapacity(), a.getLocation());
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
			AccomodationDTO aDTO = new AccomodationDTO(a.getId(), a.getAccomodationName(), a.getAccomodationType(), a.getCategory(),
					a.getBonusServices(), a.getComments(), a.getPrices(), a.getCapacity(), a.getLocation());
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
			AccomodationDTO aDTO = new AccomodationDTO(a.getId(), a.getAccomodationName(), a.getAccomodationType(), a.getCategory(),
					a.getBonusServices(), a.getComments(), a.getPrices(), a.getCapacity(), a.getLocation());
			accomodationDTOList.add(aDTO);
		}

		return accomodationDTOList;
	}

	@Override
	public List<AccomodationBonusDTO> findAllAccomodationBonus() {
		List<AccomodationBonusDTO> retVal = new ArrayList<AccomodationBonusDTO>();
		
		for(BonusService bs : bonusServiceRepository.findAll()) {
			List<Accomodation> accList = accomodationRepository.findAccomodationByBonusService(bs.getId());
			for(Accomodation a : accList) {
				AccomodationBonusDTO ab = new AccomodationBonusDTO();
				ab.setAccomodationId(a.getId());
				ab.setBonusId(bs.getId());
				retVal.add(ab);
			}
		}
		
		return retVal;
	}

}
