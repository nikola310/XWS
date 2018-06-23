package xmlweb.agent.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xmlweb.agent.model.Accomodation;
import xmlweb.agent.model.Reservation;
import xmlweb.agent.model.dtos.AccomodationDTO;
import xmlweb.agent.repository.AccomodationRepository;
import xmlweb.agent.repository.ReservationRepository;
import xmlweb.agent.service.interfaces.AccomodationServiceInterface;

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
	public Accomodation findOne(long id) {
		Optional<Accomodation> a = accomodationRepository.findById(id);
		
		if(a.isPresent())
			return a.get();
		else
			return null;	
	}

	@Override
	public boolean Create(Accomodation a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Update(Accomodation a) {
		// TODO Auto-generated method stub
		return false;
	}



}
