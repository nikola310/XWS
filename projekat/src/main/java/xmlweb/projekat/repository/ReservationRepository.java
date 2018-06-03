package xmlweb.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xmlweb.projekat.model.Reservation;

/**
 * @author Nikola
 *
 */
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

}
