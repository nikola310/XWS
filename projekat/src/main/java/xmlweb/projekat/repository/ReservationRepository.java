package xmlweb.projekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import xmlweb.projekat.model.Reservation;

/**
 * @author Nikola
 *
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	@Query("SELECT r FROM Reservation r WHERE r.accomodation.id = ?1")
	List<Reservation> findReservationsByAccomodation(Long id);
}
