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

import xmlweb.projekat.model.dtos.MessageDTO;
import xmlweb.projekat.service.interfaces.MessageServiceInterface;

@RestController
@RequestMapping(value = "/message")
public class MessageController {

	private MessageServiceInterface service;

	@Autowired
	public MessageController(MessageServiceInterface service) {
		super();
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON)
	public boolean createMessage(@Validated @RequestBody MessageDTO u) {
		return service.Create(u);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public MessageDTO readMessage(@PathVariable long id) {
		return service.Read(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<MessageDTO> readMessages() {
		return service.ReadAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public boolean updateMessage(@Validated @RequestBody MessageDTO u) {
		return service.Update(u);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean deleteMessage(@PathVariable long id) {
		return service.Delete(id);
	}
}
