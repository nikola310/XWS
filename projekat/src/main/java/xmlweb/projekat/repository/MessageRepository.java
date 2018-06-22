package xmlweb.projekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import xmlweb.projekat.model.Message;

/**
 * @author Nikola
 *
 */
public interface MessageRepository extends JpaRepository<Message, Long> {
	
	@Query("SELECT m FROM Message m where m.sender.id=?1 or m.reciever.id=?1")
	List<Message> findMessagesByUser(long id);
}
