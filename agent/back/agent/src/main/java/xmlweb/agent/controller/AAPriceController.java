package xmlweb.agent.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xmlweb.agent.model.dtos.PriceDTO;
import xmlweb.agent.service.interfaces.AAPriceServiceInterface;

@RestController
@RequestMapping(value="/priceagent")
public class AAPriceController {
	
	@Autowired
	private AAPriceServiceInterface service;
	
	@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4300" })
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
	public boolean createPrice(@Validated @RequestBody PriceDTO p) {
		return service.addPrice(p);
	}
}
