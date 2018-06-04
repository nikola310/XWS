package xmlweb.projekat.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xmlweb.projekat.model.dtos.ReservationDTO;
import xmlweb.projekat.service.interfaces.ReservationServiceInterface;

@RestController
@RequestMapping(value = "/reservation")
public class ReservationController {

	private ReservationServiceInterface service;

	@Autowired
	public ReservationController(ReservationServiceInterface service) {
		super();
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON)
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

	@RequestMapping(method = RequestMethod.POST)
	public boolean updateReservation(@Validated @RequestBody ReservationDTO u) {
		return service.Update(u);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean deleteReservation(@PathVariable long id) {
		return service.Delete(id);
	}
}
