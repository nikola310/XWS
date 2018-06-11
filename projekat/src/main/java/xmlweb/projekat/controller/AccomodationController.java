package xmlweb.projekat.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import xmlweb.projekat.model.Reservation;
import xmlweb.projekat.model.dtos.AccomodationDTO;
import xmlweb.projekat.service.interfaces.AccomodationServiceInterface;

@RestController
public class AccomodationController {

	@Autowired
	private AccomodationServiceInterface service;


	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<AccomodationDTO> findAccomodationsByParameters(
			@RequestParam(value = "destination", required = true) String destination,
			@RequestParam(value = "checkin", required = false) String checkin,
			@RequestParam(value = "checkout", required = false) String checkout,
			@RequestParam(value = "guests", required = false) String guests,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "category", required = false) String category,
			@RequestParam(value = "bonus", required = false) String bonus) {

		List<AccomodationDTO> accomodations = service.findAccomodationByDestination(destination);

		//=====================provjera bonus servisa=======================
		if (bonus != null) {

			String[] bservices = bonus.split(",");
			List<AccomodationDTO> accomodationsByBonusServices = new ArrayList<AccomodationDTO>();

			for (int i = 0; i < bservices.length; i++) {
				List<AccomodationDTO> acc = service.findAccomodationsByBonusService(Long.parseLong(bservices[i]));
				if (i == 0) {
					accomodationsByBonusServices.addAll(acc);
				} else {
					for (int j = accomodationsByBonusServices.size() - 1; j >= 0; j--) {
						boolean flag1 = false;
						for (int k = 0; k < acc.size(); k++) {
							if (acc.get(k).getId() == accomodationsByBonusServices.get(j).getId()) {
								flag1 = true;
							}
						}
						if (flag1 == false) {
							accomodationsByBonusServices.remove(j);
						}
					}
				}
			}

			for (int i = accomodations.size() - 1; i >= 0; i--) {
				boolean flag2 = false;
				for (int j = 0; j < accomodationsByBonusServices.size(); j++) {
					if (accomodationsByBonusServices.get(j).getId() == accomodations.get(i).getId()) {
						flag2 = true;
						break;
					}
				}

				if (flag2 == false) {
					System.out.println("brisem: " + accomodations.get(i).getId());
					accomodations.remove(i);
				}
			}
		}
		//=====================kraj provjere bonus servisa=======================
		
		
		
		//=====================provjera tipa smjestaja===========================		
		if(type != null){
			List<AccomodationDTO> accomodationsByType = new ArrayList<AccomodationDTO>();
			accomodationsByType = service.findAccomodationsByType(Long.parseLong(type));
			
			for (int i = accomodations.size() - 1; i >= 0; i--) {
				boolean flag2 = false;
				for (int j = 0; j < accomodationsByType.size(); j++) {
					if (accomodationsByType.get(j).getId() == accomodations.get(i).getId()) {
						flag2 = true;
						break;
					}
				}

				if (flag2 == false) {
					System.out.println("brisem: " + accomodations.get(i).getId());
					accomodations.remove(i);
				}
			}
		}
		//=====================kraj provjere tipa smjestaja======================
		
		
		//=====================provjera kategorije smjestaja=====================
		if(category != null){
			List<AccomodationDTO> accomodationsByCategory = new ArrayList<AccomodationDTO>();
			accomodationsByCategory = service.findAccomodationsByCategory(Integer.parseInt(category));
			
			for (int i = accomodations.size() - 1; i >= 0; i--) {
				boolean flag2 = false;
				for (int j = 0; j < accomodationsByCategory.size(); j++) {
					if (accomodationsByCategory.get(j).getId() == accomodations.get(i).getId()) {
						flag2 = true;
						break;
					}
				}

				if (flag2 == false) {
					System.out.println("brisem: " + accomodations.get(i).getId());
					accomodations.remove(i);
				}
			}
		}
		//=====================kraj provjere kategorije smjestaja=====================
		
		return accomodations;

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/accomodations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<AccomodationDTO> findAllAccomodations() {

		return service.findAllAccomodation();
	}

	@RequestMapping(value = "/accomodationsbydestination", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<AccomodationDTO> findAccomodationsByDestination(
			@RequestParam(value = "destination", required = true) String destination) {

		return service.findAccomodationByDestination(destination);
	}

	@RequestMapping(value = "/reservationsbyaccomodation", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<Reservation> findReservationsByAccomodation(
			@RequestParam(value = "destination", required = true) long destination) {

		return service.findReservationsByAccomodation(destination);
	}

	@RequestMapping(value = "/accomodationsbybonusservice", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<AccomodationDTO> findAccomodationsByBonusService(
			@RequestParam(value = "bonus", required = true) String bonusService) {

		String[] bservices = bonusService.split(",");
		List<AccomodationDTO> accomodations = new ArrayList<AccomodationDTO>();
		for (int i = 0; i < bservices.length; i++) {
			List<AccomodationDTO> acc = service.findAccomodationsByBonusService(Long.parseLong(bservices[i]));
			if (i == 0) {
				accomodations.addAll(acc);
			} else {
				for (int j = 0; j < accomodations.size(); j++) {
					boolean flag = false;

					for (int k = 0; k < acc.size(); k++) {
						if (acc.get(k).getId() == accomodations.get(j).getId()) {
							flag = true;
						}
					}
					if (flag == false) {
						accomodations.remove(j);
					}
				}
			}
		}
		return accomodations;
	}
}
