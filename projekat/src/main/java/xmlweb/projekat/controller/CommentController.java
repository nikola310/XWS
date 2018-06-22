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

import xmlweb.projekat.model.dtos.CommentDTO;
import xmlweb.projekat.security.TokenValidator;
import xmlweb.projekat.service.interfaces.CommentServiceInterface;

/**
 * @author Nikola
 *
 */
@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4300" })
@RequestMapping(value = "/comment")
public class CommentController {

	private CommentServiceInterface service;

	@Autowired
	public CommentController(CommentServiceInterface service) {
		super();
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
	public boolean createComment(@Validated @RequestBody CommentDTO c) {
		return service.Create(c);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public CommentDTO readComment(@PathVariable long id) {
		return service.Read(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<CommentDTO> readComments() {
		return service.ReadAll();
	}

	@RequestMapping(method = RequestMethod.PUT)
	public boolean updateComment(@Validated @RequestBody CommentDTO c) {
		return service.Update(c);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean deleteComment(@PathVariable long id) {
		return service.Delete(id);
	}

	@RequestMapping(value = "/to_manage", method = RequestMethod.GET)
	public List<CommentDTO> toComments() {
		return service.getNotManaged();
	}

	@RequestMapping(value = "/accept", method = RequestMethod.POST)
	public boolean accept(@RequestParam("id") long id, @RequestParam("version") int version,
			@RequestHeader(value = "User-Agent", required = true) String userAgent,
			@RequestHeader(value = "Token", required = true) String token) {
		if (!TokenValidator.validateAdmin(userAgent, token)) {
			return false;
		}
		return service.acceptComment(id, version);
	}

	@RequestMapping(value = "/reject", method = RequestMethod.POST)
	public boolean reject(@RequestParam("id") long id, @RequestParam("version") int version,
			@RequestHeader(value = "User-Agent", required = true) String userAgent,
			@RequestHeader(value = "Token", required = true) String token) {
		if (!TokenValidator.validateAdmin(userAgent, token)) {
			return false;
		}
		return service.rejectComment(id, version);
	}

}
