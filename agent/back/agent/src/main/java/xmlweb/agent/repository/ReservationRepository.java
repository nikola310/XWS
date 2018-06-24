package xmlweb.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xmlweb.agent.model.Reservation;

/**
 * @author Nikola
 *
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	
}
