package xmlweb.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xmlweb.projekat.model.Message;

/**
 * @author Nikola
 *
 */
public interface MessageRepository extends JpaRepository<Message, Long> {

}
