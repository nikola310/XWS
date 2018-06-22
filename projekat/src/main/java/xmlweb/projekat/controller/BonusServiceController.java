package xmlweb.projekat.controller;

import java.util.List;

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

import xmlweb.projekat.model.dtos.BonusDTO;
import xmlweb.projekat.model.dtos.BonusServiceDTO;
import xmlweb.projekat.security.TokenValidator;
import xmlweb.projekat.service.interfaces.BonusServiceInterface;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4300" })
public class BonusServiceController {

	@Autowired
	private BonusServiceInterface service;

	@RequestMapping(value = "/bonusservices", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<BonusServiceDTO> findAllBonusServices() {

		return service.findAllBonusServices();
	}

	@RequestMapping(value = "/bonus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<BonusDTO> findForAdmin() {
		return service.forAdmin();
	}

	@RequestMapping(value = "/bonus", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
	public boolean edit(@Validated @RequestBody BonusDTO dto,
			@RequestHeader(value = "User-Agent", required = true) String userAgent,
			@RequestHeader(value = "Token", required = true) String token) {
		if (!TokenValidator.validateAdmin(userAgent, token)) {
			return false;
		}
		return service.edit(dto);
	}

	@RequestMapping(value = "/bonus", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_FORM_URLENCODED, produces = MediaType.APPLICATION_JSON)
	public boolean newBonus(@RequestParam("name") String name,
			@RequestHeader(value = "User-Agent", required = true) String userAgent,
			@RequestHeader(value = "Token", required = true) String token) {
		if (!TokenValidator.validateAdmin(userAgent, token)) {
			return false;
		}
		return service.create(name);
	}

	@RequestMapping(value = "/bonus/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON)
	public boolean delete(@PathVariable long id,
			@RequestHeader(value = "User-Agent", required = true) String userAgent,
			@RequestHeader(value = "Token", required = true) String token) {
		if (!TokenValidator.validateAdmin(userAgent, token)) {
			return false;
		}
		return service.delete(id);
	}
}
