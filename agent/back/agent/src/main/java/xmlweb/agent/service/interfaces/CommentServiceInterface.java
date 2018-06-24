package xmlweb.agent.service.interfaces;

import java.util.List;

import xmlweb.agent.model.Comment;

/**
 * 
 * @author Nikola
 *
 */
public interface CommentServiceInterface {

	List<Comment> getAll();
	Comment getById(Long id);
	boolean Create(Comment c);
	boolean Update(Comment c);
	
}
