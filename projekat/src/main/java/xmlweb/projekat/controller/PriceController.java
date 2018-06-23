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

import xmlweb.projekat.model.dtos.PriceDTO;
import xmlweb.projekat.service.interfaces.PriceServiceInterface;

@RestController
@RequestMapping(value = "/price")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4300" })
public class PriceController {

	private PriceServiceInterface service;

	@Autowired
	public PriceController(PriceServiceInterface service) {
		super();
		this.service = service;
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON)
	public boolean createPrice(@Validated @RequestBody PriceDTO u) {
		return service.Create(u);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public PriceDTO readPrice(@PathVariable long id) {
		return service.Read(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<PriceDTO> readPrices() {
		return service.ReadAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public boolean updatePrice(@Validated @RequestBody PriceDTO u) {
		return service.Update(u);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean deletePrice(@PathVariable long id) {
		return service.Delete(id);
	}
}
