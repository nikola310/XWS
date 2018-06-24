package xmlweb.projekat.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xmlweb.projekat.model.Reservation;
import xmlweb.projekat.model.dtos.ReservationDTO;
import xmlweb.projekat.service.interfaces.ReservationServiceInterface;

@RestController
@RequestMapping(value = "/reservation")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4300" })
public class ReservationController {

	@Autowired
	private ReservationServiceInterface service;

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
	public boolean createReservation(@Validated @RequestBody ReservationDTO u) {
		return service.Create(u);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ReservationDTO readReservations(@PathVariable long id) {
		return service.Read(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<ReservationDTO> readReservation() {
		return service.ReadAll();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.PUT)
	public boolean updateReservation(@Validated @RequestBody ReservationDTO u) {
		return service.Update(u);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean deleteReservation(@PathVariable long id) {
		return service.Delete(id);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/myreservations/{id}", method = RequestMethod.GET)
	public List<Reservation> findReservationsByUser(@PathVariable long id) {
		return service.findReservationsByUser(id);
	}
}
