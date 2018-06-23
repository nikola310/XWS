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

import xmlweb.projekat.model.dtos.LocationDTO;
import xmlweb.projekat.service.interfaces.LocationServiceInterface;

@RestController
@RequestMapping(value = "/location")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4300" })
public class LocationController {

	private LocationServiceInterface service;

	@Autowired
	public LocationController(LocationServiceInterface service) {
		super();
		this.service = service;
	}
	

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON)
	public boolean createLocation(@Validated @RequestBody LocationDTO u) {
		return service.Create(u);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public LocationDTO readLocation(@PathVariable long id) {
		return service.Read(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<LocationDTO> readLocations() {
		return service.ReadAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public boolean updateLocation(@Validated @RequestBody LocationDTO u) {
		return service.Update(u);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean deleteLocation(@PathVariable long id) {
		return service.Delete(id);
	}
}
