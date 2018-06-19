package xmlweb.agent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import xmlweb.agent.model.Accomodation;

/**
 * @author Nikola
 *
 */
public interface AccomodationRepository extends JpaRepository<Accomodation, Long> {
	
	@Query("SELECT a FROM Accomodation a WHERE a.location.city like %?1% or a.location.state like %?1% or a.location.streetName like %?1%")
	List<Accomodation> findAccomodationByDestination(String destination);
	
	@Query("SELECT a FROM Accomodation a RIGHT JOIN a.bonusServices b WHERE b.id = ?1")
	List<Accomodation> findAccomodationByBonusService(long bonusServiceId);
	
	@Query("SELECT a FROM Accomodation a WHERE a.accomodationType.id = ?1")
	List<Accomodation> findAccomodationByType(long typeId);
	
	@Query("SELECT a FROM Accomodation a WHERE a.category = ?1")
	List<Accomodation> findAccomodationByCategory(int categoryId);
}
