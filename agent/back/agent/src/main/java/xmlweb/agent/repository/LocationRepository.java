package xmlweb.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xmlweb.agent.model.Location;

/**
 * @author Nikola
 *
 */
public interface LocationRepository extends JpaRepository<Location, Long> {

}
