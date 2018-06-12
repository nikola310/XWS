package xmlweb.projekat.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xmlweb.projekat.model.dtos.BonusServiceDTO;
import xmlweb.projekat.service.interfaces.BonusServiceInterface;

@RestController
public class BonusServiceController {
	
	@Autowired
	private BonusServiceInterface service;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/bonusservices", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<BonusServiceDTO> findAllBonusServices() {

		return service.findAllBonusServices();
	}
}
