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

import xmlweb.projekat.model.dtos.PictureDTO;
import xmlweb.projekat.service.interfaces.PictureServiceInterface;

@RestController
@RequestMapping(value = "/picture")
public class PictureController {

	private PictureServiceInterface service;

	@Autowired
	public PictureController(PictureServiceInterface service) {
		super();
		this.service = service;
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON)
	public boolean createPicture(@Validated @RequestBody PictureDTO u) {
		return service.Create(u);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public PictureDTO readPicture(@PathVariable long id) {
		return service.Read(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<PictureDTO> readPictures() {
		return service.ReadAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public boolean updateUser(@Validated @RequestBody PictureDTO u) {
		return service.Update(u);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean deletePicture(@PathVariable long id) {
		return service.Delete(id);
	}
}
