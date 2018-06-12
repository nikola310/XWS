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
	
	@Query("SELECT r FROM Reservation r WHERE (r.startDate >= ?1 and r.startDate < ?2) or (r.endDate < ?2 and r.endDate > ?1)")
	List<Reservation> findReservationsBetweenDates(long checkInDate, long checkOutDate);
}
