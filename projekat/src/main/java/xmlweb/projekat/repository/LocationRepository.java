package xmlweb.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xmlweb.projekat.model.Location;

/**
 * @author Nikola
 *
 */
public interface LocationRepository extends JpaRepository<Location, Integer> {

}
