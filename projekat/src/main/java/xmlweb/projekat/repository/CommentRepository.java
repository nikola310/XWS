package xmlweb.projekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import xmlweb.projekat.model.Comment;

/**
 * @author Nikola
 *
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

	public List<Comment> getCommentByApprovedIsNull();
	
}
