package xmlweb.projekat.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import xmlweb.projekat.model.AccomodationAgent;
import xmlweb.projekat.model.Picture;
import xmlweb.projekat.model.Price;
import xmlweb.projekat.model.Reservation;
import xmlweb.projekat.model.dtos.AccomodationAdminDTO;
import xmlweb.projekat.model.dtos.AccomodationDTO;
import xmlweb.projekat.model.dtos.AvailableAccomodationDTO;
import xmlweb.projekat.model.dtos.CategoryDTO;
import xmlweb.projekat.model.dtos.ReservationDTO;
import xmlweb.projekat.repository.AccomodationAgentRepository;
import xmlweb.projekat.security.TokenValidator;
import xmlweb.projekat.service.interfaces.AccomodationServiceInterface;
import xmlweb.projekat.service.interfaces.PictureServiceInterface;
import xmlweb.projekat.service.interfaces.ReservationServiceInterface;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4300" })
public class AccomodationController {

	@Autowired
	private AccomodationServiceInterface service;

	@Autowired
	private PictureServiceInterface pictureService;

	@Autowired
	private ReservationServiceInterface reservationService;
	
	@Autowired
	private AccomodationAgentRepository aaRepository;

	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<AvailableAccomodationDTO> findAccomodationsByParameters(
			@RequestParam(value = "destination", required = true) String destination,
			@RequestParam(value = "checkin", required = false) String checkin,
			@RequestParam(value = "checkout", required = false) String checkout,
			@RequestParam(value = "guests", required = false) String guests,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "category", required = false) String category,
			@RequestParam(value = "bonus", required = false) String bonus) {

		List<AccomodationDTO> accomodations = service.findAccomodationByDestination(destination);

		// =====================provjera bonus servisa=======================
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
		// =====================kraj provjere bonus servisa=======================

		// =====================provjera tipa smjestaja===========================
		if (type != null) {
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
		// =====================kraj provjere tipa smjestaja======================

		// =====================provjera kategorije smjestaja=====================
		if (category != null) {
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
		// =====================kraj provjere kategorije smjestaja=====================

		// =====================provjera slobodnih smjestaja===========================
		long checkInDate = Long.parseLong(checkin);
		long checkOutDate = Long.parseLong(checkout);

		List<ReservationDTO> reservations = reservationService.findReservationsBetweenDates(checkInDate, checkOutDate);
		Map<AccomodationDTO, List<ReservationDTO>> accomodationReservations = new HashMap<AccomodationDTO, List<ReservationDTO>>();

		for (AccomodationDTO a : accomodations) {
			List<ReservationDTO> res = new ArrayList<ReservationDTO>();
			for (int i = 0; i < reservations.size(); i++) {
				if (a.getId() == reservations.get(i).getAccomodation()) {
					res.add(reservations.get(i));
				}
			}

			accomodationReservations.put(a, res);
		}

		List<AccomodationDTO> freeAccomodations = new ArrayList<AccomodationDTO>();

		for (Map.Entry<AccomodationDTO, List<ReservationDTO>> entry : accomodationReservations.entrySet()) {
			boolean flag = true;
			for (long i = checkInDate; i < checkOutDate; i += 86400000) {
				int capacity = entry.getKey().getCapacity();
				List<ReservationDTO> validReservations = new ArrayList<ReservationDTO>();

				for (ReservationDTO r : entry.getValue()) {
					if (r.getStartDate() <= i && r.getEndDate() > i) {
						validReservations.add(r);
					}
				}

				int reserved = 0;
				for (ReservationDTO r : validReservations) {
					reserved += r.getNumberOfPersons();
				}

				int free = capacity - reserved;

				if (free < Integer.parseInt(guests)) {
					flag = false;
				}
			}
			if (flag == true) {
				freeAccomodations.add(entry.getKey());
			}

		}

		System.out.println("Koliko ima slobodnih smjestaja? " + freeAccomodations.size());
		for (int i = 0; i < freeAccomodations.size(); i++) {
			System.out.println("Smjestaj " + freeAccomodations.get(i).getId());
		}

		for (int i = accomodations.size() - 1; i >= 0; i--) {
			boolean flag3 = false;
			for (int j = 0; j < freeAccomodations.size(); j++) {
				if (freeAccomodations.get(j).getId() == accomodations.get(i).getId()) {
					flag3 = true;
					break;
				}
			}

			if (flag3 == false) {
				System.out.println("Brisem: " + accomodations.get(i).getId());
				accomodations.remove(i);
			}
		}

		// =====================kraj provjere slobodnih
		// smjestaja==========================

		// =====================racunanje cijene
		// smjestaja=================================
		List<AvailableAccomodationDTO> availableAccomodations = new ArrayList<AvailableAccomodationDTO>();

		for (AccomodationDTO a : accomodations) {
			int accPrice = 0;
			for (long i = checkInDate; i < checkOutDate; i += 86400000) {
				for (Price p : a.getPrices()) {
					if ((i >= p.getStartDate()) && (i < p.getEndDate())) {
						accPrice += p.getPrice();
						break;
					}
				}

			}
			System.out.println("Cijena smjestaja: " + accPrice);
			List<Picture> pictures = pictureService.findPicturesByAccomodation(a.getId());
			accPrice = accPrice * Integer.parseInt(guests);
			AvailableAccomodationDTO aa = new AvailableAccomodationDTO(a.getId(), a.getName(), a.getType(),
					a.getCategory(), a.getBonusServices(), a.getComments(), accPrice, a.getLocation(), pictures);
			availableAccomodations.add(aa);
		}

		return availableAccomodations;

	}

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

	@RequestMapping(value = "/reservationsdates", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<ReservationDTO> findAllReservationsBetweenDates(
			@RequestParam(value = "checkin", required = false) String checkin,
			@RequestParam(value = "checkout", required = false) String checkout) {

		long checkInDate = Long.parseLong(checkin);
		long checkOutDate = Long.parseLong(checkout);

		return reservationService.findReservationsBetweenDates(checkInDate, checkOutDate);
	}

	@RequestMapping(value = "/acc_admin", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<AccomodationAdminDTO> getAccomodations() {
		return service.forAdmin();
	}

	@RequestMapping(value = "/acc_category", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
	public boolean setCategory(@RequestHeader(value = "User-Agent", required = true) String userAgent,
			@RequestHeader(value = "Token", required = true) String token, @Validated @RequestBody CategoryDTO dto) {

		if (!TokenValidator.validateAdmin(userAgent, token)) {
			return false;
		}
		return service.setCategory(dto);
	}
	
	
	@RequestMapping(value = "/{id}/agent", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public long findMainAgentByAccomodation(@PathVariable long id) {
		return aaRepository.findMainAgentByAccomodation(id).getAgent().getId();
	}


}
