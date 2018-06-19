package xmlweb.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xmlweb.agent.model.Message;

/**
 * @author Nikola
 *
 */
public interface MessageRepository extends JpaRepository<Message, Long> {

}
