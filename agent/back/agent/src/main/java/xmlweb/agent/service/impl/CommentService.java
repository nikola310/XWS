package xmlweb.agent.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xmlweb.agent.model.Comment;
import xmlweb.agent.repository.CommentRepository;
import xmlweb.agent.service.interfaces.CommentServiceInterface;

/**
 * @author Nikola
 *
 */
@Service
public class CommentService implements CommentServiceInterface {
	
	@Autowired
	private CommentRepository repository;

	@Override
	public List<Comment> getAll() {
		return repository.findAll();
	}

	@Override
	public Comment getById(Long id) {
		Optional<Comment> c = repository.findById(id);
	
		if(c.isPresent())
			return c.get();
		else
			return null;
	}

	@Override
	public boolean Create(Comment c) {
		try {
			repository.save(c);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean Update(Comment c) {
		try {
			repository.save(c);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	

}
