package xmlweb.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import xmlweb.projekat.model.AccomodationAgent;

/**
 * @author Nikola
 *
 */
public interface AccomodationAgentRepository extends JpaRepository<AccomodationAgent, Long> {

	@Query("SELECT a FROM AccomodationAgent a WHERE a.accomodation.id = ?1 and a.mainAgent = true")
	AccomodationAgent findMainAgentByAccomodation(long id);
}
