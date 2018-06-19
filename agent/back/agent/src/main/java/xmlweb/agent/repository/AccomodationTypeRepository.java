package xmlweb.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xmlweb.agent.model.AccomodationType;

/**
 * @author Nikola
 *
 */
public interface AccomodationTypeRepository extends JpaRepository<AccomodationType, Long> {

}
